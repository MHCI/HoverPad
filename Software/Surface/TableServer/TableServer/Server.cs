using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.IO;
using Newtonsoft.Json;
using System.Threading;



namespace TableServer
{
    class Server
    {
        private static Thread listenThread;
        private static HttpListener listener;
        private static String address;
        private int port;
        
        JsonCommunication jC;
       
        public Server(JsonCommunication jC, int port)
        {
            this.jC = jC;
            this.port = port;

            //Get ipv4 adress from localhost
            String localPCName = Dns.GetHostName();
            IPAddress[] localIP = Dns.GetHostAddresses(localPCName);

            // the address we want to listen
            IPAddress myIp = null;
            foreach (IPAddress ip in localIP)
            {
                if (ip.AddressFamily == System.Net.Sockets.AddressFamily.InterNetwork)
                {
                    myIp = ip;
                }
            }
            if (myIp == null)
            {
                throw new Exception("No IPv4 Adress was found. Be sure that IPv4 is enabled.");
            }
            address = "http://" + myIp.ToString() + ":" + port +"/";
            Console.WriteLine(address);
            // setup thread
            listenThread = new Thread(Worker);
            listenThread.IsBackground = true;
            listenThread.Priority = ThreadPriority.Normal;

            // setup listener
            listener = new HttpListener();
            listener.Prefixes.Add(address);

            // Gogogo
            listenThread.Start(null);


        }

        private void Worker(object state)
        {
            // start listening
            listener.Start();

            JsonObject jo = null;
            HttpListenerContext context = null;
            HttpListenerRequest httpRequest = null;
            HttpListenerResponse httpResponse = null;
            ReceiverDevice device = null;
            Stream output = null;

            String resource;
            // request -> response loop
            while (true)
            {
                context = listener.GetContext();
                //From unknown
                httpRequest = context.Request;
                //To unknown
                httpResponse = context.Response;

                device = lookUpDevice(httpRequest);
                resource = httpRequest.RawUrl.ToString();

                Response response = new Response(HttpStatusCode.OK);

                if (resource.Equals("/auth"))
                {
                    response = deviceAuthentification(httpRequest);
                }
                else if (device != null)
                {
                    switch (resource)
                    {
                        case "/":
                            if (httpRequest.HttpMethod == "POST")
                            {
                                response = getResponseFromPost(httpRequest);
                            }
                            else if (httpRequest.HttpMethod == "GET")
                            {
                                response = getResponseFromGet(httpRequest);
                            }
                            break;
                        default:
                            response = new Response(HttpStatusCode.NotFound);
                            break;
                    }
                }
                else
                {
                    response = new Response(HttpStatusCode.Unauthorized);
                }

                /*
                 * response to the request is send
                 */
               
                if (response.getStatusCode().Equals(HttpStatusCode.OK))
                {
                    jo = new JsonObject(JsonObject.Type.data, "authorization successful");
                }
                else
                {
                    jo = new JsonObject(JsonObject.Type.data, "access denied");
                }
            
                byte[] data = Encoding.UTF8.GetBytes(JsonConvert.SerializeObject(jo));

                httpResponse.ContentType = "text/json";
                httpResponse.ContentLength64 = data.Length;
                httpResponse.StatusCode = (int)response.getStatusCode();
               
                using (output = httpResponse.OutputStream)
                {
                    output.Write(data, 0, data.Length);
                }
            }
        }

        internal Response sendRequest(Request request)
        {
            ReceiverDevice rD = request.getReciever();
            String uri = "http://" + rD.getIP() + ":" + rD.getPort();
            HttpWebResponse httpResponse = null;
            Response response = null;

            HttpWebRequest httpRequest = (HttpWebRequest)WebRequest.Create(uri);
            httpRequest.ContentType = "text/json";
            httpRequest.Method = request.getMethod().ToString();
            httpRequest.KeepAlive = true;
            httpRequest.Proxy = null;

            // Set Body data in request
            ASCIIEncoding encoding = new ASCIIEncoding();
            JsonObject jO = new JsonObject(JsonObject.Type.data, request.getData());

            byte[] bdata = encoding.GetBytes(JsonConvert.SerializeObject(jO));

            httpRequest.ContentLength = bdata.Length;

            try
            {
                Stream newStream = httpRequest.GetRequestStream();
                newStream.Write(bdata, 0, bdata.Length);
                newStream.Close();
                httpResponse = (HttpWebResponse)httpRequest.GetResponse();
                // Get the stream containing content returned by the server.
                Stream dataStream = httpResponse.GetResponseStream();
                // Open the stream using a StreamReader for easy access.
                StreamReader reader = new StreamReader(dataStream);
                // Read the content.
                string responseData = reader.ReadToEnd();

                //Return to JsonCommunication
                response = new Response(httpResponse.StatusCode, responseData);

                // Clean up the streams and the response.
                reader.Close();
                httpResponse.Close();
            }catch(WebException e)
            {
                response = new Response(HttpStatusCode.RequestTimeout,e.Message.ToString());
            }            
            return response;
        }

        private Response getResponseFromGet(HttpListenerRequest httpRequest)
        {
            ReceiverDevice dev = lookUpDevice(httpRequest);
            String path = httpRequest.RawUrl.ToString();
            JsonObject jO = getJsonObjectFromRequest(httpRequest);
            if (jO.type.Equals(JsonObject.Type.data))
            {
                Request request = new Request(dev, Request.Method.GET, path, jO.data);
                return jC.receivedRequest(request);
            }

            return new Response(HttpStatusCode.BadRequest);
        }

        private Response getResponseFromPost(HttpListenerRequest httpRequest)
        {
            ReceiverDevice dev = lookUpDevice(httpRequest);
            String path = httpRequest.RawUrl.ToString();
            JsonObject jO = getJsonObjectFromRequest(httpRequest);
            if(jO.type.Equals(JsonObject.Type.data))
            {
                Request request = new Request(dev, Request.Method.POST,path,jO.data);
                return jC.receivedRequest(request);
            }
            
            return new Response(HttpStatusCode.BadRequest);
        }

        private Response deviceAuthentification(HttpListenerRequest request) {

            if (request.HttpMethod == "POST")
            {
                JsonObject jO = getJsonObjectFromRequest(request);
               
                if (jO!=null && jO.type.ToString() == "auth")
                {
                    ReceiverDevice newDevice = getDeviceFromJsonObject(jO);

                    if (jC.newDeviceToAccept(newDevice))
                    {
                        return new Response(HttpStatusCode.OK);
                    }
                    return new Response(HttpStatusCode.Forbidden);
                }
                return new Response(HttpStatusCode.BadRequest);
            }
            return new Response(HttpStatusCode.MethodNotAllowed); //GET REQUEST
        }

        private ReceiverDevice getDeviceFromJsonObject(JsonObject jO)
        {
            ReceiverDevice dev = JsonConvert.DeserializeObject<ReceiverDevice>(jO.data);
            return dev;
        }

        private ReceiverDevice lookUpDevice(HttpListenerRequest httpRequest)
        {
            return jC.getDeviceByIp(httpRequest.RemoteEndPoint.Address.ToString());
        }

        private JsonObject getJsonObjectFromRequest(HttpListenerRequest request) 
        {
            Stream body = request.InputStream;
            StreamReader reader = new StreamReader(body, request.ContentEncoding);
            String data = reader.ReadToEnd();
            
            JsonObject obj = JsonConvert.DeserializeObject<JsonObject>(data);
            return obj;
        }
    }
}
