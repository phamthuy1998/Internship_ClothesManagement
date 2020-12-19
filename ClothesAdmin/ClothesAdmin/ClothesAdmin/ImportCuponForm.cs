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
    public partial class ImportCuponForm : DevExpress.XtraEditors.XtraForm
    {
        public ImportCuponForm()
        {
            InitializeComponent();

        }

        //private void importCouponBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        //{
        //    this.Validate();
        //    this.importCouponBindingSource.EndEdit();
        //    this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        //}

        private void ImportCuponForm_Load(object sender, EventArgs e)
        {
            if (Program.accountLogin.roleId == 1)
            {
                lbEmployee.Visible = true;
                cbbEmployee.Visible = true;
            }
            else if (Program.accountLogin.roleId == 2)
            {
                lbEmployee.Visible = false;
                cbbEmployee.Visible = false;
            }
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);

            loadData();
        }

        private void loadData()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Employee' table. You can move, or remove it, as needed.
            try
            {
                this.employeeTableAdapter.Fill(this.clothesDataSet.Employee);
            }
            catch (Exception ex) { }


            if (providerIdTextBox.Text != "")
                this.productTableAdapter.FillBy1(this.clothesDataSet.Product, Convert.ToInt16(providerIdTextBox.Text));

            cbbEmployee.SelectedValue = Program.accountLogin.idEmployee;
            if (cbbEmployee.SelectedIndex > 0)
            {
                this.importCouponTableAdapter.Fill(this.clothesDataSet.ImportCoupon);
            }
            else
            {
                try
                {
                    this.importCouponTableAdapter.FillBy(this.clothesDataSet.ImportCoupon, Convert.ToInt32(cbbEmployee.SelectedValue));

                }
                catch (Exception ex) { }
            }
            loadDataItem();
        }

        private void loadDataItem()
        {
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            this.sizeTableAdapter.FillBy(this.clothesDataSet.Size, 
                Convert.ToInt16(productComboBox.SelectedValue));
            this.colorTableAdapter.FillBy(this.clothesDataSet.Color, 
                Convert.ToInt16(productComboBox.SelectedValue)
                , Convert.ToInt16(sizeComboBox.SelectedValue));
        }

        private void btnSaveIport_Click(object sender, EventArgs e)
        {
            if (dateDateEdit.Text.ToString() == "")
            {
                MessageBox.Show("Please select an import date", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (employeeIdSpinEdit.Text.ToString() == "")
            {
                MessageBox.Show("Can't load employee id, please choose an employeee!", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (providerIdTextBox.Text.ToString() == "")
            {
                MessageBox.Show("Please select a provider", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (productBindingSource.Count <= 0)
            {
                MessageBox.Show("This supplier does not have any products yet, please enter the supplier's product first", "Error", MessageBoxButtons.OK);
            }
            else
            {
                try
                {
                    this.Validate();
                    this.importCouponBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    Program.showToastSave();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error import cupon " + ex.Message, "", MessageBoxButtons.OK);

                }
            }
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (importCouponBindingSource.Count == 0)
            {
                MessageBox.Show("Không có phiếu nhập để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (importCouponDetailBindingSource.Count > 0)
                {
                    MessageBox.Show("Phiếu nhập đã có chi tiết, không thể xóa, category sẽ được chuyển qua trạng thái đã xóa", "", MessageBoxButtons.OK);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa phiếu nhập này?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        importCouponBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.importCouponTableAdapter.Update(this.clothesDataSet.ImportCoupon);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa phiếu nhập" + ex.Message, "", MessageBoxButtons.OK);
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

        private void btnCancelImport_Click(object sender, EventArgs e)
        {
            this.importCouponBindingSource.CancelEdit();
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            importCouponBindingSource.AddNew();
            employeeIdSpinEdit.EditValue = Program.accountLogin.idEmployee;
        }

        private void employeeComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            employeeIdSpinEdit.Value = Convert.ToInt32(employeeComboBox.SelectedValue);
        }

        private void employeeIdSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            try
            {
                employeeComboBox.SelectedValue = employeeIdSpinEdit.Value;
            }
            catch (Exception ex) { }

        }

        private void btnCancelAddItem_Click(object sender, EventArgs e)
        {
            addItem = false;
            importCouponDetailBindingSource.CancelEdit();
        }

        private void btnSaveAddItem_Click(object sender, EventArgs e)
        {
            if (idProductSpinEdit.Value <= 0)
            {
                MessageBox.Show("Please select a product!", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (sizeIDSpinEdit.Value <= 0)
            {
                MessageBox.Show("Please select a size for product", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (colorIdSpinEdit.Value <= 0)
            {
                MessageBox.Show("Please select a color for product", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (quantitySpinEdit.Text.Trim() == "")
            {
                MessageBox.Show("Please input quantity ", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (quantitySpinEdit.Text.Trim() == "")
            {
                MessageBox.Show("Please input quantity ", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (quantitySpinEdit.Value <= 0)
            {
                MessageBox.Show("Please enter quantity greater than 0", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (priceSpinEdit.Value <= 0)
            {
                MessageBox.Show("Please enter price greater than 0", "Error", MessageBoxButtons.OK);
                return;
            }
            else
            {
                try
                {
                    this.Validate();
                    this.importCouponDetailBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    Program.showToastSave();
                }
                catch (Exception ex) { MessageBox.Show(ex.Message, "THÔNG BÁO", MessageBoxButtons.OK); }

            }
        }

        private Boolean addItem = false;

        private void addProductPromoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            importCouponDetailBindingSource.AddNew();
            if (productComboBox.SelectedValue != null && int.Parse(productComboBox.SelectedValue.ToString()) > 0)
                idProductSpinEdit.Value = Convert.ToInt32(productComboBox.SelectedValue);
            if (sizeComboBox.SelectedValue != null && int.Parse(sizeComboBox.SelectedValue.ToString()) > 0)
                sizeIDSpinEdit.Text = sizeComboBox.SelectedValue.ToString();
            if (colorComboBox.SelectedValue != null && int.Parse(colorComboBox.SelectedValue.ToString()) > 0)
                colorIdSpinEdit.Text = colorComboBox.SelectedValue.ToString();
            addItem = true;
        }

        private void deleteProductPromoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (importCouponDetailBindingSource.Count == 0)
            {
                MessageBox.Show("Không có chi tiết phiếu nhập để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (MessageBox.Show("Bạn có chắc chắn muốn xóa chi tiết phiếu nhập này?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        importCouponDetailBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.importCouponDetailTableAdapter.Update(this.clothesDataSet.ImportCouponDetail);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa chi tiết phiếu nhập" + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void reloadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            loadDataItem();
        }

        private void idProductSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            //if (Convert.ToInt32(idProductSpinEdit.Value) > 0)
            //    productComboBox.SelectedValue = Convert.ToInt32(idProductSpinEdit.Value);
        }

        private void productComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (productComboBox.SelectedValue != null && int.Parse(productComboBox.SelectedValue.ToString()) > 0)
            {
                idProductSpinEdit.Value = Convert.ToInt32(productComboBox.SelectedValue);
                this.sizeTableAdapter.FillBy(this.clothesDataSet.Size,
                    Convert.ToInt16(productComboBox.SelectedValue));
               
            }
        }

        private void employeeComboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (Program.accountLogin.idEmployee == Convert.ToInt32(cbbEmployee.SelectedValue))
            {
                btnSaveIport.Visible = true;
                btnCancelImport.Visible = true;
                providerComboBox.Enabled = true;
                dateDateEdit.Enabled = true;
                btnSaveAddItem.Visible = true;
                btnCancelAddItem.Visible = true;

                productComboBox.Enabled = true;
                sizeComboBox.Enabled = true;
                colorComboBox.Enabled = true;
                quantitySpinEdit.Enabled = true;
                priceSpinEdit.Enabled = true;
            }
            else
            {
                btnSaveIport.Visible = false;
                btnCancelImport.Visible = false;
                providerComboBox.Enabled = false;
                dateDateEdit.Enabled = false;
                btnSaveAddItem.Visible = false;
                btnCancelAddItem.Visible = false;
                productComboBox.Enabled = false;
                sizeComboBox.Enabled = false;
                colorComboBox.Enabled = false;
                quantitySpinEdit.Enabled = false;
                priceSpinEdit.Enabled = false;
            }
            try
            {
                this.importCouponTableAdapter.FillBy(this.clothesDataSet.ImportCoupon, Convert.ToInt32(cbbEmployee.SelectedValue));
            }
            catch (Exception ex) { }
        }

        private void sizeIDTextBox_TextChanged(object sender, EventArgs e)
        {
            //if (sizeIDTextBox.Text!=""&&Convert.ToInt32(sizeIDTextBox.Text)>0 )
            //     sizeComboBox.SelectedValue = Convert.ToInt32(sizeIDTextBox.Text);
        }

        private void colorIdTextBox_TextChanged(object sender, EventArgs e)
        {
            //if (colorIdTextBox.Text != "" && Convert.ToInt32(colorIdTextBox.Text) > 0)
            //    colorComboBox.SelectedValue = Convert.ToInt32(colorIdTextBox.Text);
        }


        private void cbbSize_MouseClick(object sender, MouseEventArgs e)
        {
            if (addItem == true && (productComboBox.SelectedValue == null || int.Parse(productComboBox.SelectedValue.ToString()) <= 0))
            {
                MessageBox.Show("You must select product before select size", "Error", MessageBoxButtons.OK);
                sizeComboBox.SelectedValue = -1;
            }
        }

        private void sizeComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
           if (sizeComboBox.SelectedValue != null)
            {
                sizeIDSpinEdit.Text = sizeComboBox.SelectedValue.ToString();
                this.colorTableAdapter.FillBy(this.clothesDataSet.Color,
                   Convert.ToInt16(productComboBox.SelectedValue)
                   , Convert.ToInt16(sizeComboBox.SelectedValue));
            }
        }

        private void cbbColor_MouseClick(object sender, MouseEventArgs e)
        {
            if (addItem == true && (productComboBox.SelectedValue == null || int.Parse(productComboBox.SelectedValue.ToString()) <= 0 ||
                (colorComboBox.SelectedValue == null || int.Parse(colorComboBox.SelectedValue.ToString()) <= 0)))
            {
                MessageBox.Show("You must select product and size before select color", "Error", MessageBoxButtons.OK);
                colorComboBox.SelectedValue = -1;
            }
        }

        private void colorComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            
            if (colorComboBox.SelectedValue != null)
                colorIdSpinEdit.Text = colorComboBox.SelectedValue.ToString();
        }

        private void providerComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (providerComboBox.SelectedValue != null)
                providerIdTextBox.Text = providerComboBox.SelectedValue.ToString();
        }

        private void providerIdTextBox_TextChanged(object sender, EventArgs e)
        {
            if (providerIdTextBox.Text != "")
                this.productTableAdapter.FillBy1(this.clothesDataSet.Product, Convert.ToInt16(providerIdTextBox.Text));

        }

        private void btnExport_Click(object sender, EventArgs e)
        {

        }

        private void sizeIDSpinEdit_EditValueChanged(object sender, EventArgs e)
        {

        }
    }
}