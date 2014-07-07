namespace app
{
    partial class ADD
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.addButton = new System.Windows.Forms.Button();
            this.cancelButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.dname = new System.Windows.Forms.TextBox();
            this.ip1 = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.ip2 = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.ip3 = new System.Windows.Forms.TextBox();
            this.dport = new System.Windows.Forms.TextBox();
            this.cname = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.ip4 = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // addButton
            // 
            this.addButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.addButton.Location = new System.Drawing.Point(12, 148);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(75, 23);
            this.addButton.TabIndex = 0;
            this.addButton.Text = "add";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // cancelButton
            // 
            this.cancelButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.cancelButton.Location = new System.Drawing.Point(104, 148);
            this.cancelButton.Name = "cancelButton";
            this.cancelButton.Size = new System.Drawing.Size(75, 23);
            this.cancelButton.TabIndex = 1;
            this.cancelButton.Text = "cancel";
            this.cancelButton.UseVisualStyleBackColor = true;
            this.cancelButton.Click += new System.EventHandler(this.cancelButton_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(9, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(67, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Devicename";
            // 
            // dname
            // 
            this.dname.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.dname.Location = new System.Drawing.Point(12, 29);
            this.dname.Name = "dname";
            this.dname.Size = new System.Drawing.Size(167, 20);
            this.dname.TabIndex = 3;
            // 
            // ip1
            // 
            this.ip1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ip1.Location = new System.Drawing.Point(12, 73);
            this.ip1.MaxLength = 3;
            this.ip1.Name = "ip1";
            this.ip1.Size = new System.Drawing.Size(24, 20);
            this.ip1.TabIndex = 4;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(9, 57);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "IP adress";
            // 
            // ip2
            // 
            this.ip2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ip2.Location = new System.Drawing.Point(42, 73);
            this.ip2.MaxLength = 3;
            this.ip2.Name = "ip2";
            this.ip2.Size = new System.Drawing.Size(24, 20);
            this.ip2.TabIndex = 6;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(140, 57);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(26, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "Port";
            // 
            // ip3
            // 
            this.ip3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ip3.Location = new System.Drawing.Point(72, 73);
            this.ip3.MaxLength = 3;
            this.ip3.Name = "ip3";
            this.ip3.Size = new System.Drawing.Size(24, 20);
            this.ip3.TabIndex = 8;
            // 
            // dport
            // 
            this.dport.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.dport.Location = new System.Drawing.Point(143, 73);
            this.dport.MaxLength = 5;
            this.dport.Name = "dport";
            this.dport.Size = new System.Drawing.Size(36, 20);
            this.dport.TabIndex = 10;
            // 
            // cname
            // 
            this.cname.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cname.Location = new System.Drawing.Point(12, 119);
            this.cname.Name = "cname";
            this.cname.Size = new System.Drawing.Size(167, 20);
            this.cname.TabIndex = 11;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(9, 103);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(65, 13);
            this.label4.TabIndex = 12;
            this.label4.Text = "Deviceclass";
            // 
            // ip4
            // 
            this.ip4.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ip4.Location = new System.Drawing.Point(102, 73);
            this.ip4.MaxLength = 3;
            this.ip4.Name = "ip4";
            this.ip4.Size = new System.Drawing.Size(24, 20);
            this.ip4.TabIndex = 9;
            // 
            // ADD
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(190, 187);
            this.Controls.Add(this.ip4);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.cname);
            this.Controls.Add(this.dport);
            this.Controls.Add(this.ip3);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.ip2);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.ip1);
            this.Controls.Add(this.dname);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.cancelButton);
            this.Controls.Add(this.addButton);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "ADD";
            this.Text = "add new device";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button cancelButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox dname;
        private System.Windows.Forms.TextBox ip1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox ip2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox ip3;
        private System.Windows.Forms.TextBox dport;
        private System.Windows.Forms.TextBox cname;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox ip4;
    }
}