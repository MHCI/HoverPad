using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TableServer
{
    public class ReceiverDevice
    {
        private String name;
        private String ip;
        private String port;
        private String deviceclass;

        public ReceiverDevice(String name, String ip, String port, String deviceclass)
        {
            this.name = name;
            this.ip = ip;
            this.port = port;
            this.deviceclass = deviceclass;
        }

        public void setName(String name) 
        {
            this.name = name;
        }
        public void setIP(String ip)
        {
            this.ip = ip;
        }
        public void setPort(String port)
        {
            this.port = port;
        }
        public void setdeviceclass(String deviceclass)
        {
            this.deviceclass = deviceclass;
        }

        public String getName() 
        {
            return name;
        }
        public String getIP()
        {
            return ip;
        }
        public String getPort()
        {
            return port;
        }
        public String getDeviceclass()
        {
            return deviceclass;
        }
    }
}
