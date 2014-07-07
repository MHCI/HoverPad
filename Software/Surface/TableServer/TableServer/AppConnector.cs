using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;

namespace TableServer
{
    public abstract class AppConnector
    {
        public AppConnector() { }
        abstract public Response receivedData(Request request);
    }
}
