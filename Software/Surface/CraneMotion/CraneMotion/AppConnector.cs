using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CraneMotion
{
    public abstract class AppConnector
    {
        public AppConnector() { }
        abstract public void receivedData(Point3D point);
    }
}
