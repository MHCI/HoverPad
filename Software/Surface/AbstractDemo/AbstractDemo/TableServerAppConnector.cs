using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using TableServer;

namespace AbstractDemo
{
    class TableServerAppConnector : AppConnector
    {
        private SurfaceWindow1 gui;
        public TableServerAppConnector(SurfaceWindow1 gui)
        {
            this.gui = gui;
        }
        public override Response receivedData(Request response)
        {
            String data = response.getData();
            Console.WriteLine(data);
            gui.textChanged(data);
            return new Response(HttpStatusCode.OK);
        }
    }
}
