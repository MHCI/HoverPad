using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Microsoft.Surface;
using Microsoft.Surface.Presentation;
using Microsoft.Surface.Presentation.Controls;
using Microsoft.Surface.Presentation.Input;
using CraneMotion;
using TableServer;
using Helpers;

namespace AbstractDemo
{
    /// <summary>
    /// Interaction logic for SurfaceWindow1.xaml
    /// </summary>
    public partial class SurfaceWindow1 : SurfaceWindow
    {
        /// <summary>
        /// Default constructor.
        /// </summary>
        /// 

        //Local vars

        private JsonCommunication jC;
        private TableServerAppConnector tS;
        private ReceiverDevice device;
        private ReceiverDevice device1;

        private CraneMotionAppConnector cM;
        USBCommunication myArduino;

        String[] action;

        int x = 0;
        int y = 0;
        int z = 0;

        String pitch, roll, yaw;

        public SurfaceWindow1()
        {
            InitializeComponent();

            tS = new TableServerAppConnector(this);
            jC = new JsonCommunication(tS, 8080);
            addNexus();
            
            cM = new CraneMotionAppConnector(this);
            myArduino = new USBCommunication(cM, "Arduino");
            myArduino.openCom();

            // Add handlers for window availability events
            AddWindowAvailabilityHandlers();

            this.MakeFullScreen(1);
        }

        /// <summary>
        /// Occurs when the window is about to close. 
        /// </summary>
        /// <param name="e"></param>
        protected override void OnClosed(EventArgs e)
        {
            base.OnClosed(e);

            // Remove handlers for window availability events
            RemoveWindowAvailabilityHandlers();
        }

        /// <summary>
        /// Adds handlers for window availability events.
        /// </summary>
        private void AddWindowAvailabilityHandlers()
        {
            // Subscribe to surface window availability events
            ApplicationServices.WindowInteractive += OnWindowInteractive;
            ApplicationServices.WindowNoninteractive += OnWindowNoninteractive;
            ApplicationServices.WindowUnavailable += OnWindowUnavailable;
        }

        /// <summary>
        /// Removes handlers for window availability events.
        /// </summary>
        private void RemoveWindowAvailabilityHandlers()
        {
            // Unsubscribe from surface window availability events
            ApplicationServices.WindowInteractive -= OnWindowInteractive;
            ApplicationServices.WindowNoninteractive -= OnWindowNoninteractive;
            ApplicationServices.WindowUnavailable -= OnWindowUnavailable;
        }

        /// <summary>
        /// This is called when the user can interact with the application's window.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnWindowInteractive(object sender, EventArgs e)
        {
            //TODO: enable audio, animations here
        }

        /// <summary>
        /// This is called when the user can see but not interact with the application's window.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnWindowNoninteractive(object sender, EventArgs e)
        {
            //TODO: Disable audio here if it is enabled

            //TODO: optionally enable animations here
        }

        /// <summary>
        /// This is called when the application's window is not visible or interactive.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnWindowUnavailable(object sender, EventArgs e)
        {
            //TODO: disable audio, animations here
        }

       
        private void setText(string text)
        {
            action = text.Split(';');

            if (action[0].Equals("start"))
            {
                if (action[1].Equals("left"))
                {
                    x = -200;
                }
                if (action[1].Equals("right"))
                {
                    x = 200;
                }
                if (action[1].Equals("forward"))
                {
                    y = -200;
                }
                if (action[1].Equals("backward"))
                {
                    y = 200;
                }
                if (action[1].Equals("up"))
                {
                    z = 200;
                }
                if (action[1].Equals("down"))
                {
                    z = -200;
                }
                myArduino.sendRelativePoint3D(new Point3D(x, y, z));
            }
            else if (action[0].Equals("stop"))
            {
                if (action[1].Equals("left"))
                {
                    x = 0;
                }
                if (action[1].Equals("right"))
                {
                    x = 0;
                }
                if (action[1].Equals("forward"))
                {
                    y = 0;
                }
                if (action[1].Equals("backward"))
                {
                    y = 0;
                }
                if (action[1].Equals("up"))
                {
                    z = 0;
                }
                if (action[1].Equals("down"))
                {
                    z = 0;
                }
                if (action[1].Equals("all"))
                {
                    x = 0;
                    y = 0;
                    z = 0;
                }
                myArduino.sendRelativePoint3D(new Point3D(x, y, z));
            } else {

                pitch = action[0];
                roll = action[1];
                yaw = action[2];

                this.Dispatcher.BeginInvoke((Action)delegate()
                {
                    label11.Content = pitch;
                    label12.Content = roll;
                    label13.Content = yaw;
                });
            }

        }
        internal void textChanged(string p)
        {
            setText(p);
        }
        internal void pushNotification(String msg)
        {
            String [] koords = msg.Split(' ');

            this.Dispatcher.BeginInvoke((Action)delegate()
            {
                label14.Content = koords[0];
                label15.Content = koords[1];
                label16.Content = koords[2];
            });
            
            msg = "Koords;"+koords[0]+";"+koords[1]+";"+koords[2];
            try
            {
               Request question = new Request(device, Request.Method.POST, "", msg);
               Response answer = jC.sendRequest(question);
               // Request question = new Request(device1, Request.Method.POST, "", msg);
               // Response answer = jC.sendRequest(question);
            }
            catch (Exception e1)
            {
            }
        }
        private void addNexus()
        {
            String name = "Nexus10";
            String ip = "192.168.1.2";
            String port = "8080";
            String dclass = "Tablet";
            jC.addManual(new ReceiverDevice(name, ip, port, dclass));
            device = jC.getDeviceByName("Nexus10");
            /*
            String name = "Nexus4";
            String ip = "192.168.1.5";
            String port = "8080";
            String dclass = "Smartphone";
            jC.addManual(new ReceiverDevice(name, ip, port, dclass));
            device1 = jC.getDeviceByName("Nexus4");*/
        }

        private void SurfaceWindow_Loaded(object sender, RoutedEventArgs e)
        {

        }

        private void surfaceButton9_TouchEnter(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;Start;Up"));
        }

        private void surfaceButton9_TouchLeave(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;End;Up"));
        }
        private void surfaceButton10_TouchEnter(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;Start;Down"));
        }
        private void surfaceButton10_TouchLeave(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;End;Down"));
        }

        private void surfaceButton7_TouchEnter(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;Start;Left"));
        }

        private void surfaceButton7_TouchLeave(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;End;Left"));
        }

        private void surfaceButton8_TouchLeave(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;End;Right"));
        }

        private void surfaceButton8_TouchEnter(object sender, TouchEventArgs e)
        {
            jC.sendRequest(new Request(device, Request.Method.POST, "", "Motion;Start;Right"));
        }

        

    }
}