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
    public partial class SizeForm : DevExpress.XtraEditors.XtraForm
    {
        public SizeForm()
        {
            InitializeComponent();
        }

        private void SizeForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.Fill(this.clothesDataSet.ProductSizeColor);
            // TODO: This line of code loads data into the 'clothesDataSet.Size' table. You can move, or remove it, as needed.
            this.sizeTableAdapter.Fill(this.clothesDataSet.Size);

        }

        private void sizeBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.sizeBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void activeLabel_Click(object sender, EventArgs e)
        {

        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            sizeBindingSource.AddNew();
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (sizeBindingSource.Count == 0)
            {
                MessageBox.Show("Không có Size để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (productSizeColorBindingSource.Count > 0)
                {
                    MessageBox.Show("Size đã có sản phẩm, không thể xóa, trạng thái của Size sẽ được chuyển qua đã xóa", "", MessageBoxButtons.OK);
                    activeSpinEdit.EditValue = 1;
                    this.Validate();
                    this.productSizeColorBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa " + ((DataRowView)this.sizeBindingSource.Current).Row["sizeName"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        sizeBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.sizeTableAdapter.Update(this.clothesDataSet.Size);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa Size" + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.Fill(this.clothesDataSet.ProductSizeColor);
            // TODO: This line of code loads data into the 'clothesDataSet.Size' table. You can move, or remove it, as needed.
            this.sizeTableAdapter.Fill(this.clothesDataSet.Size);
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.sizeBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            sizeBindingSource.CancelEdit();
        }
    }
}