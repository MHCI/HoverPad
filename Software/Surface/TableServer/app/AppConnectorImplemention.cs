using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using TableServer;

namespace app
{
    class AppConnectorImplemention : AppConnector
    {
        private GUI gui;
        public AppConnectorImplemention(GUI gui) {
            this.gui = gui;
        }
        public override Response receivedData(Request response)
        {
            gui.textChanged(response.getData());
            return new Response(HttpStatusCode.OK);
        }
    }
}
