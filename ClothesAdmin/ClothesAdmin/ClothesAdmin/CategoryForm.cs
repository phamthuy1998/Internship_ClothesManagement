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
using System.Net;
using System.IO;

namespace ClothesAdmin
{
    public partial class CategoryForm : DevExpress.XtraEditors.XtraForm
    {
        public CategoryForm()
        {
            InitializeComponent();
        }

        private void categoryBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.categoryBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void CategoryForm1_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);

        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.categoryBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.categoryBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void setIcon()
        {
            if (String.IsNullOrEmpty(imageUrlTextEdit.Text))
            {
                picImageIcon.Image = Properties.Resources.no_image;
            }
            else
            {
                picImageIcon.ImageLocation=(imageUrlTextEdit.Text);
            }
        }

        private void setThumbnailImage()
        {
            if (String.IsNullOrEmpty(thumnailTextEdit.Text))
            {
                picThumbnail.Image = Properties.Resources.no_image;
            }
            else
            {
                picThumbnail.ImageLocation = (thumnailTextEdit.Text);
            }
        }

        private void imageUrlTextEdit_EditValueChanged(object sender, EventArgs e)
        {
            setIcon();
        }

        private void thumnailTextEdit_EditValueChanged(object sender, EventArgs e)
        {
            setThumbnailImage();
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            categoryBindingSource.AddNew();
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (categoryBindingSource.Count == 0)
            {
                MessageBox.Show("Không có category để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (productBindingSource.Count > 0)
                {
                    MessageBox.Show("Category đã có sản phẩm, không thể xóa, category sẽ được chuyển qua trạng thái đã xóa", "", MessageBoxButtons.OK);
                    activeSpinEdit.EditValue = 1;
                    this.Validate();
                    this.categoryBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa " + ((DataRowView)this.categoryBindingSource.Current).Row["name"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        categoryBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.categoryTableAdapter.Update(this.clothesDataSet.Category);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa khoa" + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }
    }
}