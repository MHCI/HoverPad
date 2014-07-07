using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using System.Net;
using System.Windows.Forms;


namespace TableServer
{
    public class JsonCommunication
    {
        private AppConnector connector;
        private Server server;
        private static LinkedList<ReceiverDevice> allowedDevices;
        
        private bool notImpl;
        
        public JsonCommunication(AppConnector connector, int port)
        {
            this.connector = connector;
            this.server = new Server(this,port);
            notImpl = true;
            allowedDevices = new LinkedList<ReceiverDevice>();
        }

        public Response sendRequest(Request request) 
        {
            if (Request.Method.GET.Equals(request.getMethod()) && request.getData() == null)
            {
                return this.server.sendRequest(request);
            }
            else if (Request.Method.POST.Equals(request.getMethod()) && request.getData() != null)
            {
                return this.server.sendRequest(request);
            }
            else {
                throw new Exception("No valid request");
            }

        }

        public Response receivedRequest(Request request)
        {
            return connector.receivedData(request);
        }

        public bool newDeviceToAccept(ReceiverDevice tmp)
        {
            if (notImpl)
            {
                String details = "http://"+tmp.getIP()+":"+tmp.getPort()+" DeviceClass: "+tmp.getDeviceclass();
                String msg = "Device \"" + tmp.getName() + "\" want to connect to Server.\n"+details+"\nDo you want to connect?";
                DialogResult dialogResult = MessageBox.Show(msg, "Incomming connection", MessageBoxButtons.YesNo);

                if (dialogResult == DialogResult.Yes)
                {
                    allowedDevices.AddLast(tmp);
                    return true;
                }
                else if (dialogResult == DialogResult.No)
                {
                    return false;
                }
                else
                    return false;
            }
            else
                return false;
        }

        public ReceiverDevice getDeviceByName(String name){
                       
            foreach(ReceiverDevice rd in allowedDevices){
                if (rd.getName().Equals(name))
                    return rd;
            }
 
            return null;
        }

        public LinkedList<ReceiverDevice> getAllDevices() 
        {
            return allowedDevices;
        }

        public void deleteDevice(ReceiverDevice toDelete)
        {
            allowedDevices.Remove(toDelete);
        }
        //Done + TODO nur für Lib zugänglich machen
        public ReceiverDevice getDeviceByIp(String id)
        {
            String check;
            foreach (ReceiverDevice rd in allowedDevices)
            {
                check = rd.getIP();
                if (check.Equals(id))
                    return rd;
            }
            return null;
        }
        //Done + TODO nur für Lib zugänglich machen 
        public void addManual(ReceiverDevice receiverDevice)
        {
            allowedDevices.AddLast(receiverDevice);
        }
    } 
}
