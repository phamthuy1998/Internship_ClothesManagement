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
using System.Drawing.Imaging;
using Firebase.Storage;

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
            Program.showToastSave();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            this.categoryBindingSource.CancelEdit();
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
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa category" + ex.Message, "", MessageBoxButtons.OK);
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
            Program.showToastReload();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnChangeImg_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Title = "Select image";
            ofd.Filter = "Image Files(*.jpg|*.jpg|*.png|*.jpeg";
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                System.Drawing.Image img = new Bitmap(ofd.FileName);
                picImageIcon.Image = img.GetThumbnailImage(187, 218, null, new IntPtr());
            }
        }

        private async void btnSaveImg_Click(object sender, EventArgs e)
        {
            if (imageUrlTextEdit.Text == null || picImageIcon.Image == null || picThumbnail.Image == Properties.Resources.no_image)
            {
                MessageBox.Show("Chưa có hình ảnh, không thể upload", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            var stream = new System.IO.MemoryStream();
            picImageIcon.Image.Save(stream, ImageFormat.Jpeg);
            stream.Position = 0;
            var task = new FirebaseStorage("ptshop-8b8b3.appspot.com")
                            .Child("category")
                            .Child("category_icon_" + nameTextEdit.Text + ".jpg")
                            .PutAsync(stream);
            // Track progress of the upload
            task.Progress.ProgressChanged += (s, ex) =>
            {
                Console.WriteLine($"Progress: {ex.Percentage} %");
            };

            // await the task to wait until upload completes and get the download url
            try
            {
                var downloadUrl = await task;
                imageUrlTextEdit.Text = downloadUrl;
                MessageBox.Show("Upload thành công!\n Link: " + downloadUrl, "THÔNG BÁO", MessageBoxButtons.OK);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Err: " + ex.Message, "THÔNG BÁO", MessageBoxButtons.OK);

            }
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Title = "Select image";
            ofd.Filter = "Image Files(*.jpg|*.jpg|*.png|*.jpeg";
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                System.Drawing.Image img = new Bitmap(ofd.FileName);
                picThumbnail.Image = img.GetThumbnailImage(454, 316, null, new IntPtr());
            }
        }

        private async void button1_Click(object sender, EventArgs e)
        {
            if (thumnailTextEdit.Text == null || picThumbnail.Image == null || picThumbnail.Image == Properties.Resources.no_image)
            {
                MessageBox.Show("Chưa có hình ảnh, không thể upload", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            var stream = new System.IO.MemoryStream();
            picThumbnail.Image.Save(stream, ImageFormat.Jpeg);
            stream.Position = 0;
            var task = new FirebaseStorage("ptshop-8b8b3.appspot.com")
                            .Child("category")
                            .Child("category_thumbnail_" + nameTextEdit.Text + ".jpg")
                            .PutAsync(stream);
            // Track progress of the upload
            task.Progress.ProgressChanged += (s, ex) =>
            {
                Console.WriteLine($"Progress: {ex.Percentage} %");
            };

            // await the task to wait until upload completes and get the download url
            try
            {
                var downloadUrl = await task;
                thumnailTextEdit.Text = downloadUrl;
                MessageBox.Show("Upload thành công!\n Link: " + downloadUrl, "THÔNG BÁO", MessageBoxButtons.OK);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Err: " + ex.Message, "THÔNG BÁO", MessageBoxButtons.OK);

            }
        }
    }
}