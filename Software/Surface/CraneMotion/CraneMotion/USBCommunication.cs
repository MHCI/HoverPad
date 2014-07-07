using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Management;
using System.IO.Ports;
using System.IO;


namespace CraneMotion
{
    public class USBCommunication
    {
        //TODO: diskutieren fälle finden ob man das braucht
        //private static int arduinoIgnoreValue = 123;
        private static int baud = 9600;
        private Point3D globalPos = new Point3D(0, 0, 0);
        private Point3D nextPos = new Point3D(0, 0, 0);

        private String name;
        private SerialPort device;
        private Thread thread;
        private AppConnector connector;

        private bool xException = false;
        private bool yException = false;
        private bool zException = false;

        public USBCommunication(AppConnector connector, string name)
        {
            this.connector = connector;
            thread = new Thread(new ThreadStart(listenToCom));

            ManagementObjectCollection collection;
            using (var searcher = new ManagementObjectSearcher(@"Select * From Win32_SerialPort"))
                collection = searcher.Get();

            foreach (var dev in collection)
            {
                if (dev.GetPropertyValue("Description").ToString().Contains(name))
                {
                    this.name = dev.GetPropertyValue("Description").ToString();
                    device = new System.IO.Ports.SerialPort(dev.GetPropertyValue("DeviceID").ToString(), baud);
                }
            }
        }
        public USBCommunication(AppConnector connector) {
            this.connector = connector;
            thread = new Thread(new ThreadStart(listenToCom));
        }
        public LinkedList<string> getUSBDevices() {

            LinkedList<string> devices = new LinkedList<string>();

            ManagementObjectCollection collection;
            using (var searcher = new ManagementObjectSearcher(@"Select * From Win32_SerialPort"))
            collection = searcher.Get();

            foreach (var dev in collection)
            {
                devices.AddLast(dev.GetPropertyValue("Description").ToString());
            }
            return devices;
        }
       
        public String getName()
        {
            return name;
        }
        public String getCOMPort()
        {
            return device.PortName;
        }

        public bool openCom()
        {
            if (device == null)
                return false;
            if (!device.IsOpen)
            {
                device.Open();
                thread.Start();
                return true;
            }
            return false;
        }
        public bool closeCom()
        {
            if (device.IsOpen)
            {
                device.Close();
                thread.Abort();
                return true;
            }
            return false;
        }
       
        public void init()
        {
            sendRelativePoint3D(new Point3D(-200, -200, 200));
        }
        public void park()
        {
            sendRelativePoint3D(new Point3D(200, -200, 200));
            setAllFalse();
        }

        public bool sendAbsolutePoint3D(Point3D point3D) 

        {
            if (point3D.X >= 0 && point3D.Y >= 0 && point3D.Z >= 0 && point3D.X < 92 && point3D.Y < 61 && point3D.Z < 108)
            {
                point3D.X = point3D.X - globalPos.X;
                point3D.Y = point3D.Y - globalPos.Y;
                point3D.Z = point3D.Z - globalPos.Z;

                sendRelativePoint3D(point3D);
                return true;
            }
            return false;
        }

        public void sendRelativePoint3D(Point3D point3D)
        {
            int tmpx = Convert.ToInt32(point3D.X * 36.26f);
            int tmpy = Convert.ToInt32(point3D.Y * 55.59f);
            int tmpz = Convert.ToInt32(point3D.Z * 139.09f);

            device.Write(tmpx.ToString() + "," + tmpy.ToString() + "," + tmpz.ToString());
        }    

        public Point3D getGlobalPosition()
        {
            return globalPos;
        }

        private void listenToCom()
        {
            String receivedData = null;
            String[] parts = null;

            while (true)
            {
                try
                {
                    receivedData = device.ReadLine();
                }
                catch (IOException e)
                {
                    Console.WriteLine("IOException: " + e.Message);
                    continue;
                }
                catch (InvalidOperationException e)
                {
                    Console.WriteLine("InvalidOperation: " + e.Message);
                    device.Open();
                    System.Threading.Thread.Sleep(10000);
                    continue;
                }
                parts = receivedData.Split('_');
                //TODO throw Event: exception
                if (parts[0].Equals("E"))
                {
                    if (parts[1].Equals("X"))
                    {
                        if (parts[2].Equals("1"))
                        {
                            globalPos.X = 0;
                            xException = true;
                        }
                        else 
                        {
                            xException = false;
                        }
                    }
                    else if (parts[1].Equals("Y"))
                    {
                        if (parts[2].Equals("1")) 
                        {
                            yException = true;
                            globalPos.Y = 0; 
                        }
                        else
                        {
                            yException = false;
                        }
                    }
                    else if (parts[1].Equals("Z"))
                    {
                        if (parts[2].Equals("1")) 
                        {
                            zException = true;
                            globalPos.Z = 0; 
                        }
                        else
                        {
                            zException = false;
                        }
                    }
                }
                else if (parts[0].Equals("A"))
                {
                    if (parts.Length == 5)
                    {
                        try
                        {
                            nextPos.X = (float) Math.Round((float.Parse(parts[1])) / 36.26f,2);
                            nextPos.Y = (float) Math.Round((float.Parse(parts[2])) / 55.59f,2);
                            nextPos.Z = (float) Math.Round((float.Parse(parts[3])) / 139.09f,2);
                        }
                        catch (Exception e) { }
                    }
                }
                ////TODO throw Event: init complete
                if (xException && yException && zException)
                {
                    setAllFalse();
                }
                if(globalPos.X != nextPos.X || globalPos.Y != nextPos.Y || globalPos.Z != nextPos.Z)
                {
                    globalPos.X = nextPos.X;
                    globalPos.Y = nextPos.Y;
                    globalPos.Z = nextPos.Z;
                    connector.receivedData(globalPos);
                }
            }
        }
        private void setAllFalse()
        {
            xException = false;
            yException = false;
            zException = false;
        }
    }
}
