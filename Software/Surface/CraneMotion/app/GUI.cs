using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using CraneMotion;
using System.Text.RegularExpressions;

namespace app
{
    public partial class GUI : Form
    {
        USBCommunication myArduino;
        private AppConnectorImplemention impl;
        delegate void SetTextCallback(String text);
        private Point3D tabletPos = new Point3D();

        public GUI()
        {
            InitializeComponent();

            this.FormClosing += app_Closing;

            impl = new AppConnectorImplemention(this);

            myArduino = null;

            btn_select.Enabled = false;
            btn_open.Enabled = false;
            btn_close.Enabled = false;

            btn_init.Enabled = false;
            btn_park.Enabled = false;
            btn_send.Enabled = false;
   
        }

        private void btn_autosearch_Click(object sender, EventArgs e)
        {
            myArduino = new USBCommunication(impl, "Arduino");
            box_selectedDevice.Text = myArduino.getName();
            box_list.Items.Clear();
            btn_open.Enabled = true;
            btn_refreshList.Enabled = false;
            btn_autosearch.Enabled = false;
        }

        private void btn_refreshList_Click(object sender, EventArgs e)
        {
            box_list.Items.Clear();

            myArduino = new USBCommunication(impl);
            LinkedList<string> listOfDev = new LinkedList<string>();
            listOfDev = myArduino.getUSBDevices();

            if (listOfDev != null)
            {
                foreach (string var in listOfDev)
                {
                    box_list.Items.Add(var);
                }
                box_list.SelectedIndex = 0;
                btn_select.Enabled = true;
            }
            btn_select.Enabled = true;
            btn_open.Enabled = false;
            btn_close.Enabled = false;
            btn_autosearch.Enabled = false;
            btn_refreshList.Enabled = false;
            btn_init.Enabled = false;
            btn_park.Enabled = false;
            btn_send.Enabled = false;
        }

        private void btn_open_Click(object sender, EventArgs e)
        {
            if (myArduino.openCom())
            {
                box_state.Text = "Connection is open";
                btn_select.Enabled = false;
                btn_open.Enabled = false;
                btn_close.Enabled = true;
                btn_autosearch.Enabled = false;
                btn_refreshList.Enabled = false;
                btn_init.Enabled = true;
                btn_park.Enabled = true;
                btn_send.Enabled = true;
               
            }
            else
            {
                box_state.Text = "Can't establish connection";
            }
        }


        private void btn_close_Click(object sender, EventArgs e)
        {
            if (myArduino.closeCom())
            {
                box_state.Text = "Connection is closed";
                btn_select.Enabled = false;
                btn_open.Enabled = false;
                btn_close.Enabled = false;
                btn_autosearch.Enabled = true;
                btn_refreshList.Enabled = true;
                btn_init.Enabled = false;
                btn_park.Enabled = false;
                btn_send.Enabled = false;
                box_selectedDevice.Clear();
            }
            else
            {
                box_state.Text = "Can't close connection";
            }
        }

        private void btn_send_Click(object sender, EventArgs e)
        {
            String cmd = box_sendCMD.Text.ToString();

            String[] parts = cmd.Split(' ');

            if (parts.Length == 3)
            {
                myArduino.sendRelativePoint3D(new Point3D(float.Parse(parts[0]), float.Parse(parts[1]), float.Parse(parts[2])));
            }
            else {
                MessageBox.Show("No correct input:\nseperate coordinates with 'space' and use '.' for float numbers.");
            }
           
        }
        public void pushNotification(String msg)
        {
            if(this.box_receivedData.InvokeRequired)
            {
                SetTextCallback d = new SetTextCallback(pushNotification);
                this.Invoke(d, new object[]{msg});
            }else
            {            
                this.box_absolutePos.Text = msg;
            }
            
        }

        private void btn_init_Click(object sender, EventArgs e)
        {
            myArduino.init();
            this.box_receivedData.AppendText("Please wait while system is initializing.\n");
            this.box_receivedData.SelectionStart = this.box_receivedData.Text.Length;
            this.box_receivedData.ScrollToCaret();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.box_receivedData.AppendText("Please wait while system is parking.\n Meanwhile you can close the Application. \n Do not power off the controllerbox under the MSSurface.\n");
            this.box_receivedData.SelectionStart = this.box_receivedData.Text.Length;
            this.box_receivedData.ScrollToCaret();
            myArduino.park();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            myArduino = new USBCommunication(impl, box_list.SelectedItem.ToString());
            box_selectedDevice.Text = myArduino.getName();
            box_list.Items.Clear();
            btn_select.Enabled = false;
            btn_open.Enabled = true;
            btn_close.Enabled = false;
            btn_autosearch.Enabled = false;
            btn_refreshList.Enabled = false;
            btn_init.Enabled = false;
            btn_park.Enabled = false;
            btn_send.Enabled = false;
        }

        private void app_Closing(object sender, FormClosingEventArgs e) {
            
            if (myArduino != null)
                myArduino.closeCom();
        }

        private void btn_clear_Click(object sender, EventArgs e)
        {
            box_receivedData.Clear();
        }

        private void btn_sendAbs_Click(object sender, EventArgs e)
        {
            String cmd = box_sendCMD.Text.ToString();

            String[] parts = cmd.Split(' ');

            if (parts.Length == 3)
            {
                myArduino.sendAbsolutePoint3D(new Point3D(float.Parse(parts[0]), float.Parse(parts[1]), float.Parse(parts[2])));
            }
            else
            {
                MessageBox.Show("No correct input:\nseperate coordinates with 'space' and use '.' for float numbers.");
            }
        }

    }
}
