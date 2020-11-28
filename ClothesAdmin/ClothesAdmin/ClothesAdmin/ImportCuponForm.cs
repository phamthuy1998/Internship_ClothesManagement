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
            if (Program.accountLogin.roleId == 1)
            {
                lbEmployee.Visible = true;
                cbbEmployee.Visible = true;
            }
            else if (Program.accountLogin.roleId == 1)
            {
                lbEmployee.Visible = false;
                cbbEmployee.Visible = false;
            }
        }

        //private void importCouponBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        //{
        //    this.Validate();
        //    this.importCouponBindingSource.EndEdit();
        //    this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        //}

        private void ImportCuponForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            cbbEmployee.SelectedValue = Program.accountLogin.idEmployee;
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


            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            if (providerIdTextBox.Text != "")
                this.productTableAdapter.FillBy1(this.clothesDataSet.Product, Convert.ToInt16(providerIdTextBox.Text));
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCoupon' table. You can move, or remove it, as needed.
            // TODO: This line of code loads data into the 'clothesDataSet.Size' table. You can move, or remove it, as needed.

            this.sizeTableAdapter.Fill(this.clothesDataSet.Size);
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.Fill(this.clothesDataSet.Color);// , Convert.ToInt16(productComboBox.SelectedValue), Convert.ToInt16(sizeComboBox.SelectedValue)

            if (cbbEmployee.SelectedIndex > 0)
            {
                this.importCouponTableAdapter.Fill(this.clothesDataSet.ImportCoupon);
            }
            else
            {
                try
                {
                    this.importCouponTableAdapter.FillBy(this.clothesDataSet.ImportCoupon, Convert.ToInt32(cbbEmployee .SelectedValue));

                }
                catch (Exception ex) { }
            }

        }

        private void loadDataItem()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCoupon' table. You can move, or remove it, as needed.
            this.sizeTableAdapter.FillBy(this.clothesDataSet.Size, Convert.ToInt16(productComboBox.SelectedValue));
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.FillBy(this.clothesDataSet.Color, Convert.ToInt16(productComboBox.SelectedValue), Convert.ToInt16(sizeComboBox.SelectedValue));
        }

        private void btnSaveIport_Click(object sender, EventArgs e)
        {
            if (dateDateEdit.Text.ToString() == "")
            {
                MessageBox.Show("Please select an import date", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (employeeComboBox.SelectedIndex <= 0)
            {
                MessageBox.Show("Can't load employee id, please choose an employeee!", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (providerComboBox.SelectedIndex <= 0)
            {
                MessageBox.Show("Please select a provider", "Error", MessageBoxButtons.OK);
                return;
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
            importCouponDetailBindingSource.CancelEdit();
        }

        private void btnSaveAddItem_Click(object sender, EventArgs e)
        {
            if (productComboBox.SelectedIndex <= 0)
            {
                MessageBox.Show("Please select a product!", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (sizeComboBox.SelectedIndex <= 0)
            {
                MessageBox.Show("Please select a size for product", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (colorComboBox.SelectedIndex <= 0)
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
            else if (priceSpinEdit.Value <= 0)
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

        private void addProductPromoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            importCouponDetailBindingSource.AddNew();
            if (productComboBox.SelectedValue != null)
                idProductSpinEdit.Value = Convert.ToInt32(productComboBox.SelectedValue);
            if (sizeComboBox.SelectedValue != null)
                sizeIDTextBox.Text = sizeComboBox.SelectedValue.ToString();
            if (colorComboBox.SelectedValue != null)
                colorIdTextBox.Text = colorComboBox.SelectedValue.ToString();

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
                if (MessageBox.Show("Bạn có chắc chắn muốn xóa phiếu nhập này?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        importCouponBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.importCouponTableAdapter.Update(this.clothesDataSet.ImportCoupon);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa chi phiếu nhập" + ex.Message, "", MessageBoxButtons.OK);
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
            if (productComboBox.SelectedValue != null)
                idProductSpinEdit.Value = Convert.ToInt32(productComboBox.SelectedValue);

        }

        private void employeeComboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
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

        private void sizeComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (sizeComboBox.SelectedValue != null)
                sizeIDTextBox.Text = sizeComboBox.SelectedValue.ToString();
        }

        private void colorComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (colorComboBox.SelectedValue != null)
                colorIdTextBox.Text = colorComboBox.SelectedValue.ToString();
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
    }
}