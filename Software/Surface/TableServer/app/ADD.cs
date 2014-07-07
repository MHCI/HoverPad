using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using TableServer;

namespace app
{
    public partial class ADD : Form
    {
        GUI gui;
        public ADD(GUI gui)
        {
            InitializeComponent();
            this.gui = gui;
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            String name = dname.Text.ToString();
            String ip = ip1.Text.ToString() + "." + ip2.Text.ToString() + "." + ip3.Text.ToString()+"."+ip4.Text.ToString();
            String port = dport.Text.ToString();
            String dclass = cname.Text.ToString();
            gui.getJsonCom().addManual(new ReceiverDevice(name, ip, port, dclass));
            gui.refreshList();
            this.Close();
        }

        private void cancelButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
