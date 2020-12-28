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
    public partial class ProductStatisticForm : DevExpress.XtraEditors.XtraForm
    {
        public ProductStatisticForm()
        {
            InitializeComponent();
        }

        private void ProductStatisticForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            initTypeFilter();
            providerComboBox.SelectedIndex = 0;
            categoryComboBox.SelectedIndex = 0;
            dateBegin.DateTime = DateTime.Now;
            dateEnd.DateTime = DateTime.Now;
        }

        private void initTypeFilter()
        {
            List<StatusOrder> statusOrder = new List<StatusOrder>();
            statusOrder.Add(new StatusOrder(1, "Sản phẩm bán chạy nhất (trong khoảng thời gian)"));
            statusOrder.Add(new StatusOrder(2, "Sản phẩm bán được nhiều nhất"));
            statusOrder.Add(new StatusOrder(3, "Sản phẩm mới nhất"));
            cbbType.DataSource = statusOrder;
            cbbType.ValueMember = "id";
            cbbType.DisplayMember = "Text";
            cbbType.SelectedIndex = 0;
        }
        private void loadData()
        {
            try
            {
                if (cbCategory.Checked && cbProvider.Checked)
                    this.sP_StatisticProductTableAdapter.Fill(this.clothesDataSet.SP_StatisticProduct,
                        Convert.ToInt16(cbbType.SelectedValue),
                        Convert.ToInt16(tvTop.Text),
                        Convert.ToInt16(categoryComboBox.SelectedValue),
                        Convert.ToInt16(providerComboBox.SelectedValue),
                        dateBegin.DateTime,
                        dateEnd.DateTime);
                else if (cbCategory.Checked && !cbProvider.Checked)
                {
                    this.sP_StatisticProductTableAdapter.Fill(this.clothesDataSet.SP_StatisticProduct,
                       Convert.ToInt16(cbbType.SelectedValue),
                       Convert.ToInt16(tvTop.Text),
                       Convert.ToInt16(categoryComboBox.SelectedValue),
                       null,
                       dateBegin.DateTime,
                       dateEnd.DateTime);
                }
                else if (!cbCategory.Checked && cbProvider.Checked)
                {
                    this.sP_StatisticProductTableAdapter.Fill(this.clothesDataSet.SP_StatisticProduct,
                       Convert.ToInt16(cbbType.SelectedValue),
                       Convert.ToInt16(tvTop.Text),
                       null,
                       Convert.ToInt16(providerComboBox.SelectedValue),
                       dateBegin.DateTime,
                       dateEnd.DateTime);
                }
                else if (!cbCategory.Checked && !cbProvider.Checked)
                {
                    this.sP_StatisticProductTableAdapter.Fill(this.clothesDataSet.SP_StatisticProduct,
                       Convert.ToInt16(cbbType.SelectedValue),
                       Convert.ToInt16(tvTop.Text),
                       null,
                       null,
                       dateBegin.DateTime,
                       dateEnd.DateTime);
                }
            }
            catch (System.Exception ex)
            {
               System.Windows.Forms.MessageBox.Show(ex.Message);
            }
        }

        private void cbbType_SelectedIndexChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void dateBegin_EditValueChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void dateEnd_EditValueChanged(object sender, EventArgs e)
        {
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

        private void tvTop_TextChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void btnExport_Click(object sender, EventArgs e)
        {
            if (sP_StatisticProductBindingSource.Count <= 0)
            {
                MessageBox.Show("Không có dữ liệu để xuất file", "Lỗi", MessageBoxButtons.OK);
            }
            else
            {

                ProductReport productRp = null;
                if (cbCategory.Checked && cbProvider.Checked)
                {
                    productRp = new ProductReport(
                        Convert.ToInt16(cbbType.SelectedValue),
                       Convert.ToInt16(tvTop.Text),
                       Convert.ToInt16(categoryComboBox.SelectedValue),
                       Convert.ToInt16(providerComboBox.SelectedValue),
                       dateBegin.DateTime,
                       dateEnd.DateTime);
                }
                   
                else if (cbCategory.Checked && !cbProvider.Checked)
                {
                    productRp = new ProductReport(
                        Convert.ToInt16(cbbType.SelectedValue),
                       Convert.ToInt16(tvTop.Text),
                       Convert.ToInt16(categoryComboBox.SelectedValue),
                       null,
                       dateBegin.DateTime,
                       dateEnd.DateTime);
                }
                else if (!cbCategory.Checked && cbProvider.Checked)
                {
                    productRp = new ProductReport(
                       Convert.ToInt16(cbbType.SelectedValue),
                      Convert.ToInt16(tvTop.Text),
                      null,
                      Convert.ToInt16(providerComboBox.SelectedValue),
                      dateBegin.DateTime,
                      dateEnd.DateTime);
                }
                else if (!cbCategory.Checked && !cbProvider.Checked)
                {
                    productRp = new ProductReport(
                       Convert.ToInt16(cbbType.SelectedValue),
                      Convert.ToInt16(tvTop.Text),
                      null,
                      null,
                      dateBegin.DateTime,
                      dateEnd.DateTime);
                }
                productRp.lbNhanVien.Text = Program.accountLogin.name;
                productRp.lbTitle.Text = cbbType.Text + " từ ngày "
                    + dateBegin.DateTime + " đến ngày "+ dateEnd.DateTime;
                 ReportPrintTool report = new ReportPrintTool(productRp);
                report.ShowPreviewDialog();
            }
        }

        private void cbProvider_CheckedChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void cbCategory_CheckedChanged(object sender, EventArgs e)
        {
            loadData();
        }
    }
}