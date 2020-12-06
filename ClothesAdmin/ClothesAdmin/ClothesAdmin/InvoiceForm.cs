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
    public partial class InvoiceForm : DevExpress.XtraEditors.XtraForm
    {
        private List<StatusOrder> statusList;
        public InvoiceForm()
        {
            InitializeComponent();
        }

        private void invoiceBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.invoiceBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void InvoiceForm_Load(object sender, EventArgs e)
        {
            loadData();
            initStatusOrder();
        }

        private void initStatusOrder()
        {
            statusList = new List<StatusOrder>();
            statusList.Add(new StatusOrder(1, "Đơn hàng đã được tiếp nhận"));
            statusList.Add(new StatusOrder(2, "Đơn hàng đang giao"));
            statusList.Add(new StatusOrder(3, "Đơn hàng đã giao"));
            statusList.Add(new StatusOrder(4, "Đơn hàng đã hủy"));

            statusOrderCombobox.DataSource = statusList;

            statusOrderCombobox.ValueMember = "id";
            statusOrderCombobox.DisplayMember = "Text";

            statusOrderCombobox.SelectedIndex = Convert.ToInt32(statusOrderIdSpinEdit.Value - 1);

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
        private void loadData()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Size' table. You can move, or remove it, as needed.
            this.sizeTableAdapter.Fill(this.clothesDataSet.Size);
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.Fill(this.clothesDataSet.Color);
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.InvoiceItem' table. You can move, or remove it, as needed.
            this.invoiceItemTableAdapter.Fill(this.clothesDataSet.InvoiceItem);

            if (cbbStatusOrder.SelectedIndex == 0)
            {
                // TODO: This line of code loads data into the 'clothesDataSet.Invoice' table. You can move, or remove it, as needed.
                this.invoiceTableAdapter.Fill(this.clothesDataSet.Invoice);
            }
            else
            {
                this.invoiceTableAdapter.FillBy(this.clothesDataSet.Invoice, cbbStatusOrder.SelectedIndex);
            }
            if (invoiceBindingSource.Count > 0)
            {
                productComboBox.SelectedValue = ((DataRowView)this.invoiceItemBindingSource.Current).Row["productId"].ToString();
                sizeComboBox.SelectedValue = ((DataRowView)this.sizeBindingSource.Current).Row["id"].ToString();
                colorComboBox.SelectedValue = ((DataRowView)this.colorBindingSource.Current).Row["id"].ToString();
            }
        }

        private void loadDataInvoiceItem()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Size' table. You can move, or remove it, as needed.
            this.sizeTableAdapter.Fill(this.clothesDataSet.Size);
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.Fill(this.clothesDataSet.Color);
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.InvoiceItem' table. You can move, or remove it, as needed.
            this.invoiceItemTableAdapter.Fill(this.clothesDataSet.InvoiceItem);
            productComboBox.SelectedValue = ((DataRowView)this.invoiceItemBindingSource.Current).Row["productId"].ToString();
            sizeComboBox.SelectedValue = ((DataRowView)this.sizeBindingSource.Current).Row["id"].ToString();
            colorComboBox.SelectedValue = ((DataRowView)this.colorBindingSource.Current).Row["id"].ToString();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadData();
            Program.showToastReload();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            statusOrderIdSpinEdit.EditValue = statusOrderCombobox.SelectedValue;
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            updateDateDateEdit.EditValue = DateTime.Now;
            employeeIdSpinEdit.EditValue = Convert.ToDecimal(Program.accountLogin.idEmployee);
            this.Validate();
            this.invoiceBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            this.invoiceBindingSource.CancelEdit();
        }

        private void reloadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            loadDataInvoiceItem();
            Program.showToastReload();
        }

        private void invoiceGridControl_Click(object sender, EventArgs e)
        {

        }

        private void cbbStatusOrder_SelectedIndexChanged(object sender, EventArgs e)
        {
            loadData();
        }

        private void productComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void productIdSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            productComboBox.SelectedValue = productIdSpinEdit.Value;
        }

        private void colorIdSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            colorComboBox.SelectedValue = colorIdSpinEdit.Value;
        }

        private void sizeIdSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            if (sizeIdSpinEdit.Value > 0)
                sizeComboBox.SelectedValue = sizeIdSpinEdit.Value;
        }

        private void statusOrderIdSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            statusOrderCombobox.SelectedIndex = Convert.ToInt32(statusOrderIdSpinEdit.Value - 1);
            if (statusOrderIdSpinEdit.Value == 1)
            {
                btnChangeStatus.Visible = true;
            }
            else btnChangeStatus.Visible = false;
        }

        private void statusOrderCombobox_SelectedIndexChanged(object sender, EventArgs e)
        {
            statusOrderIdSpinEdit.Value = statusOrderCombobox.SelectedIndex + 1;
        }

        private void btnSaveAddProvider_Click_1(object sender, EventArgs e)
        {
            this.Validate();
            this.invoiceBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
            Program.showToastSave();
        }

        private void btnCancelAddProvider_Click_1(object sender, EventArgs e)
        {
            this.invoiceBindingSource.CancelEdit();
        }

        private void btnExport_Click(object sender, EventArgs e)
        {
            if (invoiceItemBindingSource.Count > 0)
            {
                var orderId = orderIdSpinEdit.Value;
                if (orderId == null || orderId <= 0)
                {
                    MessageBox.Show("Không có invoice item!", "THÔNG BÁO", MessageBoxButtons.OK);
                    return;
                }
                try
                {
                    var address = ((DataRowView)this.invoiceBindingSource.Current).Row["address"].ToString();
                    var name = ((DataRowView)this.invoiceBindingSource.Current).Row["name"].ToString();
                    var phone = ((DataRowView)this.invoiceBindingSource.Current).Row["phone"].ToString();
                    InvoiceXReport invoiceReport = new InvoiceXReport(Convert.ToInt16(orderId));

                    Program.myReader = Program.ExecSqlDataReader("SELECT format(SUM(unitPrice), 'N0')AS totalProduct FROM  InvoiceItem WHERE orderId =" + idSpinEdit.Value);
                    if (Program.myReader == null) return;
                    Program.myReader.Read();
                    if (Program.myReader == null) return;
                    var totalprice = Program.myReader.GetString(0);

                    invoiceReport.lbName.Text = "Name: " + name;
                    invoiceReport.lbAddress.Text = "Address: " + address;
                    invoiceReport.lbPhone.Text = "Phone: " + phone;
                    invoiceReport.lbPrice.Text = "Total price: "+ totalprice;
                    invoiceReport.lbPayment.Text = "Payment: " + paymentTextBox.Text;
                    invoiceReport.lbIsPaid.Text = "Status: " + tvStatus.Text;

                    ReportPrintTool report = new ReportPrintTool(invoiceReport);
                    report.ShowPreviewDialog();
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK);
                }
            }
            else
            {
                MessageBox.Show("Không có invoice item!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
        }

        private void isPaidTextBox_TextChanged(object sender, EventArgs e)
        {
            if (isPaidTextBox.Text == "1")
                tvStatus.Text = " Paid";
            else tvStatus.Text = "UnPaid";
        }

        private void btnChangeStatus_Click(object sender, EventArgs e)
        {
            statusOrderIdSpinEdit.Value = 2;
            employeeIdSpinEdit.Value =Int32.Parse( Program.accountLogin.idEmployee.ToString());
        }

        private void colorComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void sizeComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}