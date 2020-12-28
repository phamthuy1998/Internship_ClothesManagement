using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevExpress.XtraEditors;

namespace ClothesAdmin
{
    public partial class ShopDataForm : DevExpress.XtraEditors.XtraForm
    {
        public ShopDataForm()
        {
            InitializeComponent();
        }

        private void shopDataBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.shopDataBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void ShopDataForm_Load(object sender, EventArgs e)
        {
            loadData();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            try
            {
                this.Validate();
                this.shopDataBindingSource.EndEdit();
                this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                Program.showToastSave();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error save data: " + ex.Message, "Error", MessageBoxButtons.OK);
            }
        }

        private void loadData()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ShopData' table. You can move, or remove it, as needed.
            this.shopDataTableAdapter.FillByFirstRow(this.clothesDataSet.ShopData);
            Program.showToastReload();
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadData();
        }

        private void barButtonItem1_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }
    }
}