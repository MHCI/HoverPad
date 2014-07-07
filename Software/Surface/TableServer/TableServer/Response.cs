using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;


namespace TableServer
{
    public class Response
    {
        private HttpStatusCode statusCode;
        private String data;

        public Response(HttpStatusCode status, String data)
        {
            this.data = data;
            this.statusCode = status;
        }
        public Response(HttpStatusCode status)
        {
            this.statusCode = status;
            this.data = null;
        }
        private Response() { }

        public void setStatusCode(HttpStatusCode status) { this.statusCode = status; }
        public void setData(String data) { this.data = data; }

        public HttpStatusCode getStatusCode() { return statusCode; }
        public String getData() { return data; }
    }
}
