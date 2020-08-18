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
    public partial class ProductImageForm : DevExpress.XtraEditors.XtraForm
    {
        private int productID;
        public ProductImageForm(int productId)
        {
            InitializeComponent();
            this.productID = productId;
        }

        private void imageBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.imageBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void imageBindingNavigatorSaveItem_Click_1(object sender, EventArgs e)
        {
            this.Validate();
            this.imageBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void ProductImageForm_Load(object sender, EventArgs e)
        {
            
            loadData();

        }

        private void loadData()
        {
            this.imageTableAdapter.FillBy(this.clothesDataSet.Image, productID);// TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            productIdTextBox.Text = productID.ToString();
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (imageBindingSource.Count > 10)
            {
                MessageBox.Show("Sản phẩm chỉ được thêm tối đa 10 hình", "THÔNG BÁO", MessageBoxButtons.OK);
                return;

            }
            imageBindingSource.AddNew();
            productIdTextBox.Text = productID.ToString();
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (imageBindingSource.Count == 0)
            {
                MessageBox.Show("Không có hình để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (MessageBox.Show("Bạn có chắc chắn muốn xóa hình này không? ", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        imageBindingSource.RemoveCurrent();
                        this.imageTableAdapter.Update(this.clothesDataSet.Image);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa hình " + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadData();
            Program.showToastReload();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            productIdTextBox.Text = productID.ToString();
            this.Validate();
            this.imageBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
            Program.showToastSave();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            imageBindingSource.CancelEdit();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void imageUrlTextBox_TextChanged(object sender, EventArgs e)
        {
            setImageThumbnail();
        }
        private void setImageThumbnail()
        {
            if (String.IsNullOrEmpty(imageUrlTextBox.Text))
            {
                picImage.Image = Properties.Resources.no_image;
            }
            else
            {
                picImage.ImageLocation = (imageUrlTextBox.Text);
            }
        }

        private void productIdTextBox_TextChanged(object sender, EventArgs e)
        {
            try
            {
                productComboBox.SelectedValue = Convert.ToInt32( productIdTextBox.Text);
            }catch(Exception ex) { }
        }
    }
}