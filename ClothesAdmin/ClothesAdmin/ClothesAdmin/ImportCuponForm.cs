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

        private void importCouponBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.importCouponBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void ImportCuponForm_Load(object sender, EventArgs e)
        {
            employeeComboBox1.SelectedIndex = -1;
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
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCoupon' table. You can move, or remove it, as needed.
            if (employeeComboBox1.SelectedIndex > 0)
            {
                this.importCouponTableAdapter.Fill(this.clothesDataSet.ImportCoupon);
            }
            else
            {
                try
                {
                    this.importCouponTableAdapter.FillBy(this.clothesDataSet.ImportCoupon, Convert.ToInt32(employeeComboBox1.SelectedValue));

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
        }

        private void btnSaveIport_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.importCouponBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
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
            try
            {
                this.Validate();
                this.importCouponDetailBindingSource.EndEdit();
                this.tableAdapterManager.UpdateAll(this.clothesDataSet);
            }
            catch (Exception ex) { MessageBox.Show(ex.Message, "THÔNG BÁO", MessageBoxButtons.OK); }
        }

        private void addProductPromoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            importCouponDetailBindingSource.AddNew();
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
            try
            {
                productComboBox.SelectedValue = Convert.ToInt32(idProductSpinEdit.Value);
            }
            catch (Exception ex) { }
        }

        private void productComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            idProductSpinEdit.Value = Convert.ToInt32(productComboBox.SelectedValue);
        }

        private void employeeComboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                this.importCouponTableAdapter.FillBy(this.clothesDataSet.ImportCoupon, Convert.ToInt32(employeeComboBox1.SelectedValue));

            }
            catch (Exception ex) { }
        }
    }
}