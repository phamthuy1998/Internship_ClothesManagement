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
    public partial class ShopInfoForm : DevExpress.XtraEditors.XtraForm
    {
        public ShopInfoForm()
        {
            InitializeComponent();
        }

        private void ShopInfoForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ShopInfo' table. You can move, or remove it, as needed.
            this.shopInfoTableAdapter.Fill(this.clothesDataSet.ShopInfo);

        }

        private void shopInfoBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.shopInfoBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void btnSaveIport_Click(object sender, EventArgs e)
        {
            if (shopNameTextEdit.Text.ToString() == "")
            {
                MessageBox.Show("Please input shop name", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (addressTextEdit.Text.ToString() == "")
            {
                MessageBox.Show("Please input address", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (addressDetailTextEdit.Text.ToString() == "")
            {
                MessageBox.Show("Please input address detail", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (phoneNumberTextEdit.Text.ToString() == "")
            {
                MessageBox.Show("Please input phone number", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (versionAppTextEdit.Text.ToString() == "")
            {
                MessageBox.Show("Please input version app", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (descriptionRichTextBox.Text.ToString() == "")
            {
                MessageBox.Show("Please input info about shop", "Error", MessageBoxButtons.OK);
                return;
            }
            else
            {
                try
                {
                    this.Validate();
                    this.shopInfoBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    Program.showToastSave();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error import shop info " + ex.Message, "", MessageBoxButtons.OK);

                }
            }
        }

        private void btnCancelImport_Click(object sender, EventArgs e)
        {
            this.shopInfoBindingSource.CancelEdit();
        }
    }
}
