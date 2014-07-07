namespace app
{
    public partial class GUI
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
            this.inputTextBox = new System.Windows.Forms.TextBox();
            this.sendButton = new System.Windows.Forms.Button();
            this.showResponseBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.refreshButton = new System.Windows.Forms.Button();
            this.deleteButton = new System.Windows.Forms.Button();
            this.deviceBox = new System.Windows.Forms.ComboBox();
            this.addDevice = new System.Windows.Forms.Button();
            this.clearButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // inputTextBox
            // 
            this.inputTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.inputTextBox.Location = new System.Drawing.Point(12, 284);
            this.inputTextBox.Multiline = true;
            this.inputTextBox.Name = "inputTextBox";
            this.inputTextBox.Size = new System.Drawing.Size(374, 21);
            this.inputTextBox.TabIndex = 0;
            // 
            // sendButton
            // 
            this.sendButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.sendButton.Location = new System.Drawing.Point(392, 284);
            this.sendButton.Name = "sendButton";
            this.sendButton.Size = new System.Drawing.Size(103, 21);
            this.sendButton.TabIndex = 1;
            this.sendButton.Text = "Send";
            this.sendButton.UseVisualStyleBackColor = true;
            this.sendButton.Click += new System.EventHandler(this.sendButton_Click);
            // 
            // showResponseBox
            // 
            this.showResponseBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.showResponseBox.Location = new System.Drawing.Point(12, 25);
            this.showResponseBox.Multiline = true;
            this.showResponseBox.Name = "showResponseBox";
            this.showResponseBox.Size = new System.Drawing.Size(483, 145);
            this.showResponseBox.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(9, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(119, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Respone massage data";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(9, 214);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(127, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "List of authorized devices";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(9, 266);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(116, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "Request message data";
            // 
            // refreshButton
            // 
            this.refreshButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.refreshButton.Location = new System.Drawing.Point(174, 230);
            this.refreshButton.Name = "refreshButton";
            this.refreshButton.Size = new System.Drawing.Size(103, 21);
            this.refreshButton.TabIndex = 10;
            this.refreshButton.Text = "Refresh list";
            this.refreshButton.UseVisualStyleBackColor = true;
            this.refreshButton.Click += new System.EventHandler(this.refreshButton_Click);
            // 
            // deleteButton
            // 
            this.deleteButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.deleteButton.ForeColor = System.Drawing.Color.Maroon;
            this.deleteButton.Location = new System.Drawing.Point(283, 230);
            this.deleteButton.Name = "deleteButton";
            this.deleteButton.Size = new System.Drawing.Size(103, 21);
            this.deleteButton.TabIndex = 11;
            this.deleteButton.Text = "Delete selected";
            this.deleteButton.UseVisualStyleBackColor = false;
            this.deleteButton.Click += new System.EventHandler(this.deleteButton_Click);
            // 
            // deviceBox
            // 
            this.deviceBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.deviceBox.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.deviceBox.FormattingEnabled = true;
            this.deviceBox.Location = new System.Drawing.Point(12, 230);
            this.deviceBox.Name = "deviceBox";
            this.deviceBox.Size = new System.Drawing.Size(156, 21);
            this.deviceBox.TabIndex = 9;
            this.deviceBox.SelectedIndexChanged += new System.EventHandler(this.deviceBox_SelectedIndexChanged);
            // 
            // addDevice
            // 
            this.addDevice.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.addDevice.ForeColor = System.Drawing.Color.OliveDrab;
            this.addDevice.Location = new System.Drawing.Point(392, 230);
            this.addDevice.Name = "addDevice";
            this.addDevice.Size = new System.Drawing.Size(103, 21);
            this.addDevice.TabIndex = 12;
            this.addDevice.Text = "Add manual";
            this.addDevice.UseVisualStyleBackColor = true;
            this.addDevice.Click += new System.EventHandler(this.addDevice_Click);
            // 
            // clearButton
            // 
            this.clearButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.clearButton.Location = new System.Drawing.Point(12, 176);
            this.clearButton.Name = "clearButton";
            this.clearButton.Size = new System.Drawing.Size(103, 21);
            this.clearButton.TabIndex = 13;
            this.clearButton.Text = "Clear textfield";
            this.clearButton.UseVisualStyleBackColor = true;
            this.clearButton.Click += new System.EventHandler(this.clearButton_Click);
            // 
            // GUI
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(508, 320);
            this.Controls.Add(this.clearButton);
            this.Controls.Add(this.addDevice);
            this.Controls.Add(this.deleteButton);
            this.Controls.Add(this.refreshButton);
            this.Controls.Add(this.deviceBox);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.showResponseBox);
            this.Controls.Add(this.sendButton);
            this.Controls.Add(this.inputTextBox);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "GUI";
            this.Text = "DCC - Device Connection Controller";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox inputTextBox;
        private System.Windows.Forms.Button sendButton;
        private System.Windows.Forms.TextBox showResponseBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button refreshButton;
        private System.Windows.Forms.Button deleteButton;
        private System.Windows.Forms.ComboBox deviceBox;
        private System.Windows.Forms.Button addDevice;
        private System.Windows.Forms.Button clearButton;
    }
}

