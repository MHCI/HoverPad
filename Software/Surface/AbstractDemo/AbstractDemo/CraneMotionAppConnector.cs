using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using CraneMotion;

namespace AbstractDemo
{
    class CraneMotionAppConnector : AppConnector
    {
        private SurfaceWindow1 gui;
        public CraneMotionAppConnector(SurfaceWindow1 gui)
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
