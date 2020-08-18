using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClothesAdmin
{
    public partial class ProductForm : DevExpress.XtraEditors.XtraForm
    {
        public ProductForm()
        {
            InitializeComponent();
        }

        private void productBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.productBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void productBindingNavigatorSaveItem_Click_1(object sender, EventArgs e)
        {
            this.Validate();
            this.productBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void ProductForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            // TODO: This line of code loads data into the 'clothesDataSet.PromotionItem' table. You can move, or remove it, as needed.
            this.promotionItemTableAdapter.Fill(this.clothesDataSet.PromotionItem);
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.Fill(this.clothesDataSet.ProductSizeColor);
            // TODO: This line of code loads data into the 'clothesDataSet.ImportCouponDetail' table. You can move, or remove it, as needed.
            this.importCouponDetailTableAdapter.Fill(this.clothesDataSet.ImportCouponDetail);
            // TODO: This line of code loads data into the 'clothesDataSet.Image' table. You can move, or remove it, as needed.
            this.imageTableAdapter.Fill(this.clothesDataSet.Image);
            // TODO: This line of code loads data into the 'clothesDataSet.FavoriteProduct' table. You can move, or remove it, as needed.
            this.favoriteProductTableAdapter.Fill(this.clothesDataSet.FavoriteProduct);
            loadData();

        }

        private void loadData()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
        }

        private void btnListImages_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if (String.IsNullOrEmpty(idTextBox.Text))
            {
                MessageBox.Show("Không có sản phẩm, không thể xem danh sách hình ảnh", "Thông báo", MessageBoxButtons.OK);
                return;
            }
            using (ProductImageForm provierForm = new ProductImageForm(Convert.ToInt16(idTextBox.Text)))
            {
                var dlgResult = provierForm.ShowDialog(); // show form 3 as a modal dialog box
                                                          // halt this procedure until form3 is closed
                                                          // handle the result of form3:
                if (dlgResult == DialogResult.OK)
                {
                    // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
                    this.productTableAdapter.Fill(this.clothesDataSet.Product);
                    // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
                }
            }
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            productBindingSource.CancelEdit();
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

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            productBindingSource.AddNew();
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
                if (favoriteProductBindingSource.Count > 0
                    || imageBindingSource.Count > 0
                    || importCouponDetailBindingSource.Count > 0
                    || productSizeColorBindingSource.Count > 0
                    || promotionItemBindingSource.Count > 0)
                {
                    MessageBox.Show("Product đã tồn tại ở bảng khác, không thể xóa, product sẽ được chuyển qua trạng thái đã xóa", "", MessageBoxButtons.OK);
                    activeTextBox.Text = "0";
                    this.Validate();
                    this.categoryBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa product " + ((DataRowView)this.productBindingSource.Current).Row["title"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        productBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.categoryTableAdapter.Update(this.clothesDataSet.Category);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa product " + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.productBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
            Program.showToastSave();
        }

        private void categoryComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                categoryIDTextBox.Text = categoryComboBox.SelectedValue.ToString();
            }
            catch(Exception ex) { }
        }

        private void providerIdTextBox_TextChanged(object sender, EventArgs e)
        {
            try
            {
                providerComboBox.SelectedValue = Convert.ToInt32(providerIdTextBox.Text);
            }
            catch (Exception ex) { }
        }

        private void categoryIDTextBox_TextChanged(object sender, EventArgs e)
        {
            try
            {
                categoryComboBox.SelectedValue = Convert.ToInt32(categoryIDTextBox.Text);
            }
            catch (Exception ex) { }
        }

        private void providerComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                providerIdTextBox.Text = providerComboBox.SelectedValue.ToString();
            }
            catch(Exception ex) { }
        }

        private void thumnailTextBox_TextChanged(object sender, EventArgs e)
        {
            setImageThumbnail();
        }

        private void setImageThumbnail()
        {
            if (String.IsNullOrEmpty(thumnailTextBox.Text))
            {
                thumbnailProduct.Image = Properties.Resources.no_image;
            }
            else
            {
                thumbnailProduct.ImageLocation = (thumnailTextBox.Text);
            }
        }

        private void btnSizeColor_Click(object sender, EventArgs e)
        {
            if (String.IsNullOrEmpty(idTextBox.Text))
            {
                MessageBox.Show("Không có sản phẩm, không thể xem danh sách size color", "Thông báo", MessageBoxButtons.OK);
                return;
            }
            using (SizeColorForm sizeColorForm = new SizeColorForm(Convert.ToInt16(idTextBox.Text)))
            {
                var dlgResult = sizeColorForm.ShowDialog(); // show form 3 as a modal dialog box
                                                          // halt this procedure until form3 is closed
                                                          // handle the result of form3:
                if (dlgResult == DialogResult.OK)
                {
                    // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
                    this.productTableAdapter.Fill(this.clothesDataSet.Product);
                    // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
                }
            }
        }
    }
}
