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
    public partial class ProfitForm : DevExpress.XtraEditors.XtraForm
    {
        public ProfitForm()
        {
            InitializeComponent();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void loadData()
        {
            try
            {
                Nullable<int> type = null, year = null, month = null, providerId = null, categoryId = null;
                String beginDate = "", endDate = "";
                if (rbDate.Checked)
                {
                    type = 0;
                    beginDate = dateBegin.Text;
                    endDate = dateEnd.Text;
                }
                else if (radMonthYear.Checked)
                {
                    type = 1;
                    month = int.Parse(cbbMonth.Text);
                    year = int.Parse(cbbYear.Text);
                }
                if (cbCategory.Checked) categoryId = int.Parse(categoryComboBox.SelectedValue.ToString());
                if (cbProvider.Checked) providerId = int.Parse(providerComboBox.SelectedValue.ToString());
                this.sP_ProfitDataMonthYearTableAdapter.Fill(
                    this.clothesDataSet.SP_ProfitDataMonthYear,
                    type,
                    year,
                    month,
                    beginDate,
                    endDate,
                    categoryId,
                    providerId
                    );
            }
            catch (System.Exception ex)
            {
                MessageBox.Show("Error " + ex.Message, "Error", MessageBoxButtons.OK);
            }
        }
        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadData();
            Program.showToastReload();
        }

        private void ProfitForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            dateEnd.DateTime = DateTime.Today;

            cbbMonth.Items.Add(new { Text = 1 });
            cbbMonth.Items.Add(new { Text = 2 });
            cbbMonth.Items.Add(new { Text = 3 });
            cbbMonth.Items.Add(new { Text = 4 });
            cbbMonth.Items.Add(new { Text = 5 });
            cbbMonth.Items.Add(new { Text = 6 });
            cbbMonth.Items.Add(new { Text = 7 });
            cbbMonth.Items.Add(new { Text = 8 });
            cbbMonth.Items.Add(new { Text = 9 });
            cbbMonth.Items.Add(new { Text = 10 });
            cbbMonth.Items.Add(new { Text = 11 });
            cbbMonth.Items.Add(new { Text = 12 });
            cbbMonth.ValueMember = "Text";
            cbbMonth.DisplayMember = "Text";
            cbbMonth.SelectedIndex = 1;

            int year = DateTime.Now.Year;
            cbbYear.Items.Add(new { Text = (year - 2) });
            cbbYear.Items.Add(new { Text = (year - 1) });
            cbbYear.Items.Add(new { Text = (year) });
            cbbYear.ValueMember = "Text";
            cbbYear.DisplayMember = "Text";
            cbbYear.SelectedIndex = 2;

            loadData();
        }

        private void rbDate_CheckedChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void dateBegin_EditValueChanged(object sender, EventArgs e)
        {
            if (rbDate.Checked)
                loadData();
        }

        private void dateEnd_EditValueChanged(object sender, EventArgs e)
        {
            if (dateBegin.DateTime >= dateEnd.DateTime)
                MessageBox.Show("Ngày kết thúc phải lớn hơn ngày bắt đầu", "Error", MessageBoxButtons.OK);
            else if (rbDate.Checked)
                loadData();
        }
        private void spinEdit1_EditValueChanged(object sender, EventArgs e)
        {

            if (radMonthYear.Checked)
                loadData();
        }

        private void cbCategory_CheckedChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void cbProvider_CheckedChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void categoryComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cbCategory.Checked) loadData();
        }

        private void providerComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cbProvider.Checked) loadData();
        }

        private void cbbMonth_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (radMonthYear.Checked)
            {
                loadData();
            }
        }

        private void cbbYear_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (radMonthYear.Checked)
            {
                loadData();
            }
        }

        private void btnExport_Click(object sender, EventArgs e)
        {
            if (sP_ProfitDataMonthYearBindingSource.Count<=0|| (totalInvoiceSpinEdit.Value!=null &&
                int.Parse(totalInvoiceSpinEdit.Value.ToString()) <= 0))
            {
                MessageBox.Show("Không có dữ liệu để xuất file", "Lỗi", MessageBoxButtons.OK);

            }
            else
            {
                Nullable<int> year = null, month = null, providerID = null, categoryId = null;
                String beginDate = "", endDate = "";


                if (cbCategory.Checked)
                    categoryId = int.Parse(categoryComboBox.SelectedValue.ToString());
                if (cbProvider.Checked)
                    providerID = int.Parse(providerComboBox.SelectedValue.ToString());

                ProfitReport report = new ProfitReport();
                report.lbNhanVien.Text = "Nhân viên tạo phiếu: " + Program.accountLogin.name;
                string title = "";
                if (providerID == null && categoryId == null)
                    title = "Doanh thu ";
                else if (providerID != null && categoryId == null)
                    title = "Doanh thu của nhà cung cấp " + providerComboBox.Text;
                else if (providerID == null && categoryId != null)
                    title = "Doanh thu mặt hàng " + categoryComboBox.Text + " trong kho ";
                else if (providerID != null && categoryId != null)
                    title = "Doanh thu mặt hàng " + categoryComboBox.Text + " trong kho của nhà cung cấp " + providerComboBox.Text;

                if (rbDate.Checked)
                {
                    beginDate = dateBegin.Text;
                    endDate = dateEnd.Text;
                    title += " từ ngày " + beginDate + " đến ngày " + endDate;
                }
                else if (radMonthYear.Checked)
                {
                    month = int.Parse(cbbMonth.Text);
                    year = int.Parse(cbbYear.Text);
                    title += " tháng " + month + " năm " + year;
                }

                report.lbProduct.Text = "Tổng số lượng sản phẩm đã bán: " + totalProductSpinEdit.Text;
                report.lbInvoices.Text = "Tổng số lượng đơn hàng: " + totalInvoiceSpinEdit.Text;
                report.lbPrice.Text = "Tổng doanh thu: " + totalPriceSpinEdit.Text;
                report.lbProfit.Text = "Tổng lợi nhuận: " + profitSpinEdit.Text;

                report.lbTitle.Text = title;
                ReportPrintTool reportTool = new ReportPrintTool(report);
                reportTool.ShowPreviewDialog();
            }
        }
    }
}