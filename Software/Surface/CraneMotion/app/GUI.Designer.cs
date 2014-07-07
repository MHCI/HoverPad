namespace app
{
    partial class GUI
    {
        /// <summary>
        /// Erforderliche Designervariable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Verwendete Ressourcen bereinigen.
        /// </summary>
        /// <param name="disposing">True, wenn verwaltete Ressourcen gelöscht werden sollen; andernfalls False.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Vom Windows Form-Designer generierter Code

        /// <summary>
        /// Erforderliche Methode für die Designerunterstützung.
        /// Der Inhalt der Methode darf nicht mit dem Code-Editor geändert werden.
        /// </summary>
        private void InitializeComponent()
        {
            this.btn_send = new System.Windows.Forms.Button();
            this.btn_open = new System.Windows.Forms.Button();
            this.btn_close = new System.Windows.Forms.Button();
            this.box_sendCMD = new System.Windows.Forms.TextBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btn_select = new System.Windows.Forms.Button();
            this.btn_autosearch = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.box_selectedDevice = new System.Windows.Forms.TextBox();
            this.box_state = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.btn_refreshList = new System.Windows.Forms.Button();
            this.box_list = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.btn_sendAbs = new System.Windows.Forms.Button();
            this.btn_clear = new System.Windows.Forms.Button();
            this.btn_park = new System.Windows.Forms.Button();
            this.box_absolutePos = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.btn_init = new System.Windows.Forms.Button();
            this.box_receivedData = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.SuspendLayout();
            // 
            // btn_send
            // 
            this.btn_send.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_send.Location = new System.Drawing.Point(12, 195);
            this.btn_send.Name = "btn_send";
            this.btn_send.Size = new System.Drawing.Size(75, 23);
            this.btn_send.TabIndex = 0;
            this.btn_send.Text = "Relative";
            this.btn_send.UseVisualStyleBackColor = true;
            this.btn_send.Click += new System.EventHandler(this.btn_send_Click);
            // 
            // btn_open
            // 
            this.btn_open.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_open.ForeColor = System.Drawing.Color.OliveDrab;
            this.btn_open.Location = new System.Drawing.Point(6, 298);
            this.btn_open.Name = "btn_open";
            this.btn_open.Size = new System.Drawing.Size(75, 23);
            this.btn_open.TabIndex = 1;
            this.btn_open.Text = "Open";
            this.btn_open.UseVisualStyleBackColor = true;
            this.btn_open.Click += new System.EventHandler(this.btn_open_Click);
            // 
            // btn_close
            // 
            this.btn_close.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_close.ForeColor = System.Drawing.Color.Maroon;
            this.btn_close.Location = new System.Drawing.Point(147, 298);
            this.btn_close.Name = "btn_close";
            this.btn_close.Size = new System.Drawing.Size(75, 23);
            this.btn_close.TabIndex = 2;
            this.btn_close.Text = "Close";
            this.btn_close.UseVisualStyleBackColor = true;
            this.btn_close.Click += new System.EventHandler(this.btn_close_Click);
            // 
            // box_sendCMD
            // 
            this.box_sendCMD.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.box_sendCMD.Location = new System.Drawing.Point(12, 160);
            this.box_sendCMD.Name = "box_sendCMD";
            this.box_sendCMD.Size = new System.Drawing.Size(213, 20);
            this.box_sendCMD.TabIndex = 3;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btn_select);
            this.groupBox1.Controls.Add(this.btn_autosearch);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.box_selectedDevice);
            this.groupBox1.Controls.Add(this.box_state);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.btn_refreshList);
            this.groupBox1.Controls.Add(this.btn_open);
            this.groupBox1.Controls.Add(this.box_list);
            this.groupBox1.Controls.Add(this.btn_close);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(12, 13);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(231, 376);
            this.groupBox1.TabIndex = 9;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Select Device";
            // 
            // btn_select
            // 
            this.btn_select.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_select.Location = new System.Drawing.Point(9, 195);
            this.btn_select.Name = "btn_select";
            this.btn_select.Size = new System.Drawing.Size(75, 23);
            this.btn_select.TabIndex = 8;
            this.btn_select.Text = "Select";
            this.btn_select.UseVisualStyleBackColor = true;
            this.btn_select.Click += new System.EventHandler(this.button2_Click);
            // 
            // btn_autosearch
            // 
            this.btn_autosearch.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_autosearch.Location = new System.Drawing.Point(9, 50);
            this.btn_autosearch.Name = "btn_autosearch";
            this.btn_autosearch.Size = new System.Drawing.Size(75, 23);
            this.btn_autosearch.TabIndex = 7;
            this.btn_autosearch.Text = "Autosearch";
            this.btn_autosearch.UseVisualStyleBackColor = true;
            this.btn_autosearch.Click += new System.EventHandler(this.btn_autosearch_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 236);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(86, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Selected Device";
            // 
            // box_selectedDevice
            // 
            this.box_selectedDevice.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.box_selectedDevice.Location = new System.Drawing.Point(9, 258);
            this.box_selectedDevice.Name = "box_selectedDevice";
            this.box_selectedDevice.ReadOnly = true;
            this.box_selectedDevice.Size = new System.Drawing.Size(208, 20);
            this.box_selectedDevice.TabIndex = 5;
            // 
            // box_state
            // 
            this.box_state.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.box_state.Location = new System.Drawing.Point(9, 345);
            this.box_state.Name = "box_state";
            this.box_state.ReadOnly = true;
            this.box_state.Size = new System.Drawing.Size(213, 20);
            this.box_state.TabIndex = 4;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(6, 329);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(32, 13);
            this.label4.TabIndex = 3;
            this.label4.Text = "State";
            // 
            // btn_refreshList
            // 
            this.btn_refreshList.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_refreshList.Location = new System.Drawing.Point(9, 113);
            this.btn_refreshList.Name = "btn_refreshList";
            this.btn_refreshList.Size = new System.Drawing.Size(75, 23);
            this.btn_refreshList.TabIndex = 4;
            this.btn_refreshList.Text = "Refresh list";
            this.btn_refreshList.UseVisualStyleBackColor = true;
            this.btn_refreshList.Click += new System.EventHandler(this.btn_refreshList_Click);
            // 
            // box_list
            // 
            this.box_list.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.box_list.FormattingEnabled = true;
            this.box_list.Location = new System.Drawing.Point(9, 141);
            this.box_list.Name = "box_list";
            this.box_list.Size = new System.Drawing.Size(208, 21);
            this.box_list.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(6, 96);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(102, 13);
            this.label2.TabIndex = 2;
            this.label2.Text = "List of USB Devices";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(6, 27);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(134, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Autosearch for first Arduino";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.btn_sendAbs);
            this.groupBox2.Controls.Add(this.btn_clear);
            this.groupBox2.Controls.Add(this.btn_park);
            this.groupBox2.Controls.Add(this.box_absolutePos);
            this.groupBox2.Controls.Add(this.label7);
            this.groupBox2.Controls.Add(this.btn_init);
            this.groupBox2.Controls.Add(this.box_receivedData);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Controls.Add(this.label5);
            this.groupBox2.Controls.Add(this.box_sendCMD);
            this.groupBox2.Controls.Add(this.btn_send);
            this.groupBox2.Location = new System.Drawing.Point(258, 13);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(231, 376);
            this.groupBox2.TabIndex = 10;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Communication";
            // 
            // btn_sendAbs
            // 
            this.btn_sendAbs.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_sendAbs.Location = new System.Drawing.Point(150, 195);
            this.btn_sendAbs.Name = "btn_sendAbs";
            this.btn_sendAbs.Size = new System.Drawing.Size(75, 23);
            this.btn_sendAbs.TabIndex = 13;
            this.btn_sendAbs.Text = "Absolute";
            this.btn_sendAbs.UseVisualStyleBackColor = true;
            this.btn_sendAbs.Click += new System.EventHandler(this.btn_sendAbs_Click);
            // 
            // btn_clear
            // 
            this.btn_clear.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_clear.Location = new System.Drawing.Point(150, 236);
            this.btn_clear.Name = "btn_clear";
            this.btn_clear.Size = new System.Drawing.Size(75, 23);
            this.btn_clear.TabIndex = 12;
            this.btn_clear.Text = "clear history";
            this.btn_clear.UseVisualStyleBackColor = true;
            this.btn_clear.Click += new System.EventHandler(this.btn_clear_Click);
            // 
            // btn_park
            // 
            this.btn_park.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_park.Location = new System.Drawing.Point(150, 27);
            this.btn_park.Name = "btn_park";
            this.btn_park.Size = new System.Drawing.Size(75, 23);
            this.btn_park.TabIndex = 11;
            this.btn_park.Text = "Park mode";
            this.btn_park.UseVisualStyleBackColor = true;
            this.btn_park.Click += new System.EventHandler(this.button1_Click);
            // 
            // box_absolutePos
            // 
            this.box_absolutePos.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.box_absolutePos.Location = new System.Drawing.Point(12, 88);
            this.box_absolutePos.Name = "box_absolutePos";
            this.box_absolutePos.ReadOnly = true;
            this.box_absolutePos.Size = new System.Drawing.Size(213, 20);
            this.box_absolutePos.TabIndex = 10;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(9, 65);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(88, 13);
            this.label7.TabIndex = 9;
            this.label7.Text = "Ablosute location";
            // 
            // btn_init
            // 
            this.btn_init.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btn_init.Location = new System.Drawing.Point(12, 27);
            this.btn_init.Name = "btn_init";
            this.btn_init.Size = new System.Drawing.Size(75, 23);
            this.btn_init.TabIndex = 8;
            this.btn_init.Text = "Initiate";
            this.btn_init.UseVisualStyleBackColor = true;
            this.btn_init.Click += new System.EventHandler(this.btn_init_Click);
            // 
            // box_receivedData
            // 
            this.box_receivedData.Location = new System.Drawing.Point(12, 265);
            this.box_receivedData.Multiline = true;
            this.box_receivedData.Name = "box_receivedData";
            this.box_receivedData.ReadOnly = true;
            this.box_receivedData.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.box_receivedData.Size = new System.Drawing.Size(213, 100);
            this.box_receivedData.TabIndex = 7;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(9, 236);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(99, 13);
            this.label6.TabIndex = 6;
            this.label6.Text = "Messages received";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(9, 141);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(117, 13);
            this.label5.TabIndex = 5;
            this.label5.Text = "Send command ( x,y,z )";
            // 
            // GUI
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(500, 399);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "GUI";
            this.Text = "ACC - Arduino Connection Controller";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btn_send;
        private System.Windows.Forms.Button btn_open;
        private System.Windows.Forms.Button btn_close;
        private System.Windows.Forms.TextBox box_sendCMD;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox box_selectedDevice;
        private System.Windows.Forms.Button btn_refreshList;
        private System.Windows.Forms.ComboBox box_list;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.TextBox box_state;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button btn_autosearch;
        private System.Windows.Forms.TextBox box_receivedData;
        private System.Windows.Forms.TextBox box_absolutePos;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Button btn_init;
        private System.Windows.Forms.Button btn_park;
        private System.Windows.Forms.Button btn_select;
        private System.Windows.Forms.Button btn_clear;
        private System.Windows.Forms.Button btn_sendAbs;
    }
}

