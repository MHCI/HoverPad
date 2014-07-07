using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using TableServer;
using System.Threading;
namespace app
{
    public partial class GUI : Form
    {
        private JsonCommunication jC;
        private AppConnectorImplemention impl;
        private ReceiverDevice selectedDevice;
        private LinkedList<ReceiverDevice> allDevices;
        delegate void SetTextCallback(String text);
        
        public GUI()
        {
            InitializeComponent();
            impl = new AppConnectorImplemention(this);
            jC = new JsonCommunication(impl, 8080);
            sendButton.Enabled = false;
            deleteButton.Enabled = false;
        }
        //Done
        private void sendButton_Click(object sender, EventArgs e)
        {
            String data = inputTextBox.Text.ToString();
            
            Request question = new Request(selectedDevice, Request.Method.POST, "", data);
            Response answer = jC.sendRequest(question);
            
            showResponseBox.Text = answer.getStatusCode().ToString() + "\n" + answer.getData();
        }
        //Done
        public void textChanged(String text)
        {
            setText(text);   
        }
        //Done
        private void setText(string text)
        {
            // InvokeRequired required compares the thread ID of the
            // calling thread to the thread ID of the creating thread.
            // If these threads are different, it returns true.
            if (this.showResponseBox.InvokeRequired)
            {
                SetTextCallback d = new SetTextCallback(setText);
                this.Invoke(d, new object[] { text });
            }
            else
            {
                this.showResponseBox.Text = text;
            }
        }
        //
        private void deviceBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            selectedDevice = jC.getDeviceByName(deviceBox.SelectedItem.ToString());
        }
        //
        private void refreshButton_Click(object sender, EventArgs e)
        {
            refreshList();
        }

        public void refreshList()
        {
            allDevices = jC.getAllDevices();
            deviceBox.Items.Clear();
            if (allDevices.Count > 0)
            {
                deleteButton.Enabled = true;
                sendButton.Enabled = true;
                foreach (ReceiverDevice rd in allDevices)
                {
                    deviceBox.Items.Add(rd.getName());
                }
                deviceBox.SelectedIndex = 0;
            }
            else
            {
                deleteButton.Enabled = false;
                sendButton.Enabled = false;
            }
        }
        //Done
        private void deleteButton_Click(object sender, EventArgs e)
        {
            ReceiverDevice toDelete = jC.getDeviceByName(deviceBox.SelectedItem.ToString());
            jC.deleteDevice(toDelete);
            refreshList(); 
        }

        private void addDevice_Click(object sender, EventArgs e)
        {
            var addForm = new ADD(this);
            addForm.Show();
        }
        //Done
        public JsonCommunication getJsonCom() { return jC; }

        private void clearButton_Click(object sender, EventArgs e)
        {
            showResponseBox.Clear();
        }
    }
}
