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
    public partial class InvoiceStatisticFrom : DevExpress.XtraEditors.XtraForm
    {
        public InvoiceStatisticFrom()
        {
            InitializeComponent();
        }

        private void invoiceBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.invoiceBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void invoiceBindingNavigatorSaveItem_Click_1(object sender, EventArgs e)
        {
            this.Validate();
            this.invoiceBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void InvoiceStatisticFrom_Load(object sender, EventArgs e)
        {
            initStatusOrder();
            dateBegin.DateTime = DateTime.Now;
            dateEnd.DateTime = DateTime.Now;
            loadData();

        }

        private void loadData()
        {
            try
            {
                // TODO: This line of code loads data into the 'clothesDataSet.InvoiceItem' table. You can move, or remove it, as needed.
                this.invoiceItemTableAdapter.Fill(this.clothesDataSet.InvoiceItem);
                // TODO: This line of code loads data into the 'clothesDataSet.Invoice' table. You can move, or remove it, as needed.
                this.invoiceTableAdapter.FillBy1(this.clothesDataSet.Invoice, Convert.ToInt16(cbbStatusOrder.SelectedValue), dateBegin.Text, dateEnd.Text);
                showTotalInvoice();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error "+ex.Message, "Error", MessageBoxButtons.OK);
            }
        }

        private void showTotalPrice()
        {
          
        }

        private void initStatusOrder()
        {
            List<StatusOrder> statusList = new List<StatusOrder>();
            statusList.Add(new StatusOrder(1, "Đơn hàng đã được tiếp nhận"));
            statusList.Add(new StatusOrder(2, "Đơn hàng đang giao"));
            statusList.Add(new StatusOrder(3, "Đơn hàng đã giao"));
            statusList.Add(new StatusOrder(4, "Đơn hàng đã hủy"));

            cbbStatusOrder.DataSource = statusList;

            cbbStatusOrder.ValueMember = "id";
            cbbStatusOrder.DisplayMember = "Text";

            cbbStatusOrder.SelectedIndex = Convert.ToInt32(0);

            List<StatusOrder> statusOrder = new List<StatusOrder>();
            statusOrder.Add(new StatusOrder(0, "Tất cả"));
            statusOrder.Add(new StatusOrder(1, "Đơn hàng đã được tiếp nhận"));
            statusOrder.Add(new StatusOrder(2, "Đơn hàng đang giao"));
            statusOrder.Add(new StatusOrder(3, "Đơn hàng đã giao"));
            statusOrder.Add(new StatusOrder(4, "Đơn hàng đã hủy"));
            cbbStatusOrder.DataSource = statusOrder;
            cbbStatusOrder.ValueMember = "id";
            cbbStatusOrder.DisplayMember = "Text";
            cbbStatusOrder.SelectedIndex = 0;
        }

        private void dateBegin_EditValueChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void dateEnd_EditValueChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void cbbStatusOrder_SelectedIndexChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void showTotalInvoice()
        {
            try
            {
                this.sP_GetInvoiceTableAdapter.Fill(this.clothesDataSet.SP_GetInvoice, Convert.ToInt16(cbbStatusOrder.SelectedValue), dateBegin.Text, dateEnd.Text);
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }
        }
    }
}