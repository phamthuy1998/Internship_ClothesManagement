﻿using Firebase.Storage;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClothesAdmin
{
    public partial class ProductForm : DevExpress.XtraEditors.XtraForm
    {
        private string filePath = "";
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
            loadData(0, 0);

        }

        private void loadData(int categoryId, int providerID)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.FillByCategoryProvider(this.clothesDataSet.Product, categoryId, providerID);
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadDataProduct();
            Program.showToastReload();
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            activeSpinEdit.Text = "1";
            soldTextBox.Text = "0";
            ratingTextBox.Text = "0";
            productBindingSource.AddNew();
            DateTime dateTime = DateTime.UtcNow.Date;
            addDateDateEdit.Text = dateTime.ToString("yyyy-MM-dd");
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
                    activeSpinEdit.Text = "0";
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
                        this.productTableAdapter.Update(this.clothesDataSet.Product);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa product " + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
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

        private async void setImageThumbnail()
        {
            if (String.IsNullOrEmpty(thumnailTextBox.Text))
            {
                await Task.Run(() => thumbnailProduct.Image = Properties.Resources.no_image);
            }
            else
            {
                try
                {
                    var request = await Task.Run(() => WebRequest.Create(thumnailTextBox.Text));
                    using (var response = request.GetResponse())
                    using (var stream = response.GetResponseStream())
                        thumbnailProduct.Image = Bitmap.FromStream(stream);
                    await Task.Run(() => thumbnailProduct.ImageLocation = (thumnailTextBox.Text));
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Lỗi load hình: " + ex.Message, "Thông báo", MessageBoxButtons.OK);
                }

            }
        }

        private void thumbnailProduct_Click(object sender, EventArgs e)
        {

        }

        private void loadDataProduct()
        {
            if (cbCategory.Checked && cbProvider.Checked && cbbCategory.SelectedValue != null && cbbProvider.SelectedValue != null)
                loadData(int.Parse(cbbProvider.SelectedValue.ToString()), int.Parse(cbbCategory.SelectedValue.ToString()));
            else if (cbCategory.Checked && !cbProvider.Checked && cbbCategory.SelectedValue != null)
                loadData(0, int.Parse(cbbCategory.SelectedValue.ToString()));
            else if (!cbCategory.Checked && cbProvider.Checked && providerComboBox.SelectedValue != null)
                loadData(int.Parse(cbbProvider.SelectedValue.ToString()), 0);
            else loadData(0, 0);

        }

        private void cbCategory_CheckedChanged(object sender, EventArgs e)
        {
            loadDataProduct();
        }

        private void cbProvider_CheckedChanged(object sender, EventArgs e)
        {
            loadDataProduct();
        }

        private void btnSaveAddProvider_Click_1(object sender, EventArgs e)
        {
            if (activeSpinEdit.Text == "") activeSpinEdit.Text= "1";
            if (soldTextBox.Text == "") soldTextBox.Text = "0";
            if (ratingTextBox.Text == "") ratingTextBox.Text = "0";

            try
            {
                this.Validate();
                this.productBindingSource.EndEdit();
                this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                Program.showToastSave();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error add product: " + ex.Message, "Error", MessageBoxButtons.OK);
            }
        }

        private void cbbCategory_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cbCategory.Checked) loadDataProduct();
        }

        private void cbbProvider_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cbProvider.Checked) loadDataProduct();
        }

        private void btnCancelAddProvider_Click_1(object sender, EventArgs e)
        {
            try
            {
                productBindingSource.CancelEdit();
                setImageThumbnail();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error " + ex.Message, "Error", MessageBoxButtons.OK);
            }
        }

        private void btnImageList_Click_1(object sender, EventArgs e)
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

        private void btnSizeColor_Click_1(object sender, EventArgs e)
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

        private async void btnSaveImg_Click_1(object sender, EventArgs e)
        {
            if (thumnailTextBox.Text == null || thumbnailProduct.Image == null || thumbnailProduct.Image == Properties.Resources.no_image)
            {
                MessageBox.Show("Chưa có hình ảnh, không thể upload", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }

            // await the task to wait until upload completes and get the download url
            try
            {
                var stream = new System.IO.MemoryStream();
                await Task.Run(() => thumbnailProduct.Image.Save(stream, ImageFormat.Jpeg));
                stream.Position = 0;
                var task = new FirebaseStorage("ptshop-8b8b3.appspot.com")
                                .Child("product")
                                .Child("thumbnail_" + idTextBox.Text + ".jpg")
                                .PutAsync(stream);
                //// Track progress of the upload
                //task.Progress.ProgressChanged += (s, ex) =>
                //{
                //    Console.WriteLine($"Progress: {ex.Percentage} %");
                //};

                var downloadUrl = await task;
                thumnailTextBox.Text = downloadUrl;
                MessageBox.Show("Upload thành công!\n Link: " + downloadUrl, "THÔNG BÁO", MessageBoxButtons.OK);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Err: " + ex.Message, "THÔNG BÁO", MessageBoxButtons.OK);

            }
        }

        private async void btnChangeImg_LinkClicked_1(object sender, LinkLabelLinkClickedEventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Title = "Select image";
            ofd.Filter = "Image Files(*.jpg|*.jpg|*.png|*.jpeg";
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                System.Drawing.Image img = await Task.Run(() => new Bitmap(ofd.FileName));
                await Task.Run(() => thumbnailProduct.Image = img.GetThumbnailImage(351, 469, null, new IntPtr()));
            }
        }

        private void thumnailTextBox_TextChanged_1(object sender, EventArgs e)
        {
            setImageThumbnail();
        }

        private void categoryComboBox_SelectedIndexChanged_1(object sender, EventArgs e)
        {
            try
            {
                categoryIDTextBox.Text = categoryComboBox.SelectedValue.ToString();
            }
            catch (Exception ex) { }
        }

        private void providerComboBox_SelectedIndexChanged_1(object sender, EventArgs e)
        {
            try
            {
                providerIdTextBox.Text = providerComboBox.SelectedValue.ToString();
            }
            catch (Exception ex) { }
        }

    }
}
