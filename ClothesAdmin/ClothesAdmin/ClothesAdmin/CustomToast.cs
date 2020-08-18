using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClothesAdmin
{
    public partial class CustomToast : Form
    {
        public CustomToast(String message, System.Drawing.Color bgColor)
        {
            InitializeComponent();
            BackColor = bgColor;
            lbMessage.Text = message;
        }

        private void CustomToast_Load(object sender, EventArgs e)
        {
            this.CenterToScreen();
            timerClose.Start();
        }

        private void timerClose_Tick(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
