using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CraneMotion;

namespace app
{
    class AppConnectorImplemention : AppConnector
    {
        private GUI gui;
        public AppConnectorImplemention(GUI gui)
        {
            this.gui = gui;
        }
        public override void receivedData(Point3D point)
        {
            String answer = point.X.ToString()+ " " + point.Y.ToString()+ " " + point.Z.ToString();
            gui.pushNotification(answer);
        }
    }
}
