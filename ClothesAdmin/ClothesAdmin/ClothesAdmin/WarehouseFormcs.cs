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
using DevExpress.XtraReports.UI;

namespace ClothesAdmin
{
    public partial class WarehouseFormcs : DevExpress.XtraEditors.XtraForm
    {
        private Nullable<int> providerID = null;
        private Nullable<int> categoryId = null;
        public WarehouseFormcs()
        {
            InitializeComponent();
        }

        private void fillToolStripButton_Click(object sender, EventArgs e)
        {
            try
            {
                this.sP_InventoryProductTableAdapter.Fill(this.clothesDataSet.SP_InventoryProduct, new System.Nullable<int>(((int)(System.Convert.ChangeType(categoryIdToolStripTextBox.Text, typeof(int))))), new System.Nullable<int>(((int)(System.Convert.ChangeType(providerIDToolStripTextBox.Text, typeof(int))))));
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }

        }

        private void fillToolStripButton_Click_1(object sender, EventArgs e)
        {
            try
            {
                this.sP_InventoryProductTableAdapter.Fill(this.clothesDataSet.SP_InventoryProduct, new System.Nullable<int>(((int)(System.Convert.ChangeType(categoryIdToolStripTextBox.Text, typeof(int))))), new System.Nullable<int>(((int)(System.Convert.ChangeType(providerIDToolStripTextBox.Text, typeof(int))))));
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }

        }

        private void loadData()
        {
            if (cbCategory.Checked && cbProvider.Checked)
            {
                providerID = Convert.ToInt16(providerComboBox.SelectedValue);
                categoryId = Convert.ToInt16(categoryComboBox.SelectedValue);
            }
            else if (cbCategory.Checked && !cbProvider.Checked)
            {
                providerID = null;
                categoryId = Convert.ToInt16(categoryComboBox.SelectedValue);
            }
            else if (!cbCategory.Checked && cbProvider.Checked)
            {
                providerID = Convert.ToInt16(providerComboBox.SelectedValue);
                categoryId = null;
            }
            else if (!cbCategory.Checked && !cbProvider.Checked)
            {
                providerID = null;
                categoryId = null;
            }
            this.sP_InventoryProductTableAdapter.Fill(this.clothesDataSet.SP_InventoryProduct, categoryId, providerID);
        }

        private void WarehouseFormcs_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            loadData();
        }

        private void categoryComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void providerComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadData();
        }

        private void cbCategory_CheckedChanged_1(object sender, EventArgs e)
        {
            loadData();
        }

        private void cbProvider_CheckedChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnExport_Click(object sender, EventArgs e)
        {
            if (sP_InventoryProductBindingSource.Count <= 0)
            {
                MessageBox.Show("Không có dữ liệu để xuất file", "Lỗi", MessageBoxButtons.OK);

            }
            else
            {
                WarehouseReport warehouseReport = new WarehouseReport(categoryId, providerID);
                warehouseReport.lbNhanVien.Text = Program.accountLogin.name;
                string title = "";
                if (providerID == null && categoryId == null)
                    title = "Danh sách tất cả sản phẩm trong kho";
                else if (providerID != null && categoryId == null)
                    title = "Danh sách tất cả sản phẩm trong kho của nhà cung cấp " + providerComboBox.Text;
                else if (providerID == null && categoryId != null)
                    title = "Danh sách tất cả " + categoryComboBox.Text + " trong kho ";
                else if (providerID != null && categoryId != null)
                    title = "Danh sách tất cả " + categoryComboBox.Text + " trong kho của nhà cung cấp " + providerComboBox.Text;
                warehouseReport.lbTitle.Text = title;
                ReportPrintTool report = new ReportPrintTool(warehouseReport);
                report.ShowPreviewDialog();
            }
        }
    }
}