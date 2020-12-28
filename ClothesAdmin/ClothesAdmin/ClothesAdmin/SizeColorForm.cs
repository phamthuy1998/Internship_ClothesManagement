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
    public partial class SizeColorForm : DevExpress.XtraEditors.XtraForm
    {
        private int productID;
        public SizeColorForm(int productId)
        {
            InitializeComponent();
            productID = productId;
        }

        private void productSizeColorBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.productSizeColorBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void SizeColorForm_Load(object sender, EventArgs e)
        {
            loadData();
            productIDTextBox.Text = productID.ToString();
            productComboBox.SelectedValue = productID;
        }

        private void loadData()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.InvoiceItem' table. You can move, or remove it, as needed.
            this.invoiceItemTableAdapter.Fill(this.clothesDataSet.InvoiceItem);
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.Fill(this.clothesDataSet.Color);
            // TODO: This line of code loads data into the 'clothesDataSet.Size' table. You can move, or remove it, as needed.
            this.sizeTableAdapter.Fill(this.clothesDataSet.Size);
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.FillBy(this.clothesDataSet.ProductSizeColor, productID);

        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            try
            {
                quantityTextBox.Text = "1";
                productSizeColorBindingSource.AddNew();
                sizeComboBox.SelectedIndex = -1;
                colorComboBox.SelectedIndex = -1;
                productIDTextBox.Text = productID.ToString();
            }
            catch (Exception ex) { }
        }

        private void sizeComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                sizeIdTextBox.Text = sizeComboBox.SelectedValue.ToString();
            }
            catch (Exception ex) { }
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (productBindingSource.Count == 0)
            {
                MessageBox.Show("Không có product để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (invoiceItemBindingSource.Count > 0
                    || importCouponDetailBindingSource.Count > 0)
                {
                    MessageBox.Show("Product đã tồn tại ở bảng khác, không thể xóa, product sẽ được chuyển qua trạng thái đã xóa", "", MessageBoxButtons.OK);
                    this.Validate();
                    this.productSizeColorBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa product size color" + ((DataRowView)this.productBindingSource.Current).Row["title"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        productSizeColorBindingSource.RemoveCurrent();
                        this.productSizeColorTableAdapter.Update(this.clothesDataSet.ProductSizeColor);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa product size color" + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadData();
            Program.showToastReload();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            try
            {
                if (quantityTextBox.Text == "")
                {
                    quantityTextBox.Text = "0";
                }
             
                this.Validate();
                this.productSizeColorBindingSource.EndEdit();
                this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                Program.showToastSave();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Lỗi thêm" + ex.Message, "", MessageBoxButtons.OK);
            }
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            try
            {
                productSizeColorBindingSource.EndEdit();
            }
            catch(Exception ex)
            {
                MessageBox.Show("Error " + ex.Message, "Error", MessageBoxButtons.OK);
            }
        }

        private void colorComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                colorIDTextBox.Text = colorComboBox.SelectedValue.ToString();

            }
            catch (Exception ex) { }
        }
    }
}