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
    public partial class InventoryForm : DevExpress.XtraEditors.XtraForm
    {
        public InventoryForm()
        {
            InitializeComponent();
        }

        private void cbCategory_CheckedChanged(object sender, EventArgs e)
        {
            getData();
        }

        private void cbProvider_CheckedChanged(object sender, EventArgs e)
        {
            getData();
        }

        private void categoryComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            getData();
        }

        private void providerComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            getData();
        } 

        private void btnExport_Click(object sender, EventArgs e)
        {
          
            if (sP_InventoryProductNewBindingSource.Count <= 0)
            {
                MessageBox.Show("Không có dữ liệu để xuất file", "Lỗi", MessageBoxButtons.OK);

            }
            else
            {
                Nullable<int> categoryId = null, providerID = null;
                if (cbCategory.Checked)
                    categoryId = int.Parse(categoryComboBox.SelectedValue.ToString());
                if (cbProvider.Checked)
                    providerID = int.Parse(providerComboBox.SelectedValue.ToString());

                InventoryReport report = new InventoryReport(categoryId, providerID);
                report.lbNhanVien.Text ="Nhân viên tạo phiếu: " +Program.accountLogin.name;
                string title = "";
                if (providerID == null && categoryId == null)
                    title = "Danh sách tất cả sản phẩm trong kho";
                else if (providerID != null && categoryId == null)
                    title = "Danh sách tất cả sản phẩm trong kho của nhà cung cấp " + providerComboBox.Text;
                else if (providerID == null && categoryId != null)
                    title = "Danh sách tất cả " + categoryComboBox.Text + " trong kho ";
                else if (providerID != null && categoryId != null)
                    title = "Danh sách tất cả " + categoryComboBox.Text + " trong kho của nhà cung cấp " + providerComboBox.Text;
                report.lbTitle.Text = title;
                ReportPrintTool reportTool = new ReportPrintTool(report);
                reportTool.ShowPreviewDialog();
            }
        }

        private void getData()
        {
            try
            {
                Nullable<int> categoryId = null, providerID = null;
                if (cbCategory.Checked)
                    categoryId = int.Parse(categoryComboBox.SelectedValue.ToString());
                if (cbProvider.Checked)
                    providerID = int.Parse(providerComboBox.SelectedValue.ToString());
                this.sP_InventoryProductNewTableAdapter.Fill(this.clothesDataSet.SP_InventoryProductNew, categoryId, providerID);
            }
            catch (System.Exception ex)
            {
                MessageBox.Show("Error " + ex.Message, "Error", MessageBoxButtons.OK);

            }
        }

        private void InventoryForm_Load(object sender, EventArgs e)
        {
            loadDataForm();
        }

        private void loadDataForm()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            getData();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadDataForm();
            Program.showToastReload();
        }
    }
}