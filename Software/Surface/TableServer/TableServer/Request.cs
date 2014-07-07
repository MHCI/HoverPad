using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;

namespace TableServer
{
    public class Request
    {
        public enum Method { POST, GET};

        private ReceiverDevice reciever;
        private Method method;
        private String path;    
        private String data;
        
        public Request(ReceiverDevice reciever, Method method, String path, String data)
        {
            this.reciever = reciever;
            this.method = method;
            this.path = path;
            this.data = data;
        }
        
        public void setReciever(ReceiverDevice reciever) { this.reciever = reciever; }
        public void setMethod(Method method) { this.method = method; }
        public void setPath(String path) { this.path = path; }
        public void setData(String data) { this.data = data; }

        public ReceiverDevice getReciever() { return reciever; }
        public Method getMethod() { return method; }
        public String getPath() { return path; }
        public String getData() { return data; }
    }
}
