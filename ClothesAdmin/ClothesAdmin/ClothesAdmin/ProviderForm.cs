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
using System.Drawing.Imaging;
using Firebase.Storage;

namespace ClothesAdmin
{
    public partial class ProviderForm : DevExpress.XtraEditors.XtraForm
    {
        public ProviderForm()
        {
            InitializeComponent();
        }

        private void ProviderForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);    // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);

        }

        private void providerBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.providerBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void btnSaveProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (providerBindingSource.Count == 0)
            {
                MessageBox.Show("Không có providers để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (productBindingSource.Count > 0)
                {
                    MessageBox.Show("Nhà cung cấp đã có sản phẩm, không thể xóa, trạng thái của nhà cung cấp sẽ được chuyển qua đã xóa", "", MessageBoxButtons.OK);
                    activeSpinEdit.EditValue = 1;
                    this.Validate();
                    this.providerBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa " + ((DataRowView)this.providerBindingSource.Current).Row["brandName"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        providerBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.providerTableAdapter.Update(this.clothesDataSet.Provider);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa Provider" + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnRedoProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
        }

        private void btnExitProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            DialogResult dr = MessageBox.Show("Bạn có chắc muốn thoát chương trình provider", "", MessageBoxButtons.YesNo);
            if (dr == DialogResult.Yes)
            {
                Application.ExitThread();
            }
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            providerBindingSource.AddNew();
            //ProviderAddEditForm provierForm = new ProviderAddEditForm();
            //provierForm.FormClosed += new FormClosedEventHandler(provierForm.frm3_FormClosed());
            //provierForm.Show();

            //using (ProviderAddEditForm provierForm = new ProviderAddEditForm())
            //{
            //    var dlgResult = provierForm.ShowDialog(); // show form 3 as a modal dialog box
            //                                              // halt this procedure until form3 is closed
            //                                              // handle the result of form3:
            //    if (dlgResult == DialogResult.OK)
            //    {
            //        // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            //        this.productTableAdapter.Fill(this.clothesDataSet.Product);
            //        // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            //        this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            //    }
            //}


        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            setImage();
            Program.showToastReload();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            try
            {
                providerBindingSource.CancelEdit();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error " + ex.Message, "", MessageBoxButtons.OK);
            }
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            try
            {
                this.Validate();
                this.providerBindingSource.EndEdit();
                this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                Program.showToastSave();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error add database: " + ex.Message, "", MessageBoxButtons.OK);
            }
        }

        private void setImage()
        {
            if (String.IsNullOrEmpty(tvImageUrlProvider.Text))
            {
                imageProvider.Image = Properties.Resources.no_image;
            }
            else
            {
                imageProvider.ImageLocation = tvImageUrlProvider.Text;
            }
        }

        private void imageUrlTextEdit_EditValueChanged(object sender, EventArgs e)
        {
            setImage();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            DialogResult dr = MessageBox.Show("Bạn có chắc muốn thoát chương trình provider", "", MessageBoxButtons.YesNo);
            if (dr == DialogResult.Yes)
            {
                this.Close();
            }
        }

        private void providerGridControl_Click(object sender, EventArgs e)
        {

        }

        private void btnChangeImg_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Title = "Select image";
            ofd.Filter = "Image Files(*.jpg|*.jpg|*.png|*.jpeg";
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                System.Drawing.Image img = new Bitmap(ofd.FileName);
                imageProvider.Image = img.GetThumbnailImage(393, 300, null, new IntPtr());
            }
        }

        private async void btnSaveImg_Click(object sender, EventArgs e)
        {
            if (tvImageUrlProvider.Text == null || imageProvider.Image == null || imageProvider.Image == Properties.Resources.no_image)
            {
                MessageBox.Show("Chưa có hình ảnh, không thể upload", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            var stream = new System.IO.MemoryStream();
            imageProvider.Image.Save(stream, ImageFormat.Jpeg);
            stream.Position = 0;
            var task = new FirebaseStorage("ptshop-8b8b3.appspot.com")
                            .Child("provider")
                            .Child("provider_" + brandNameTextEdit.Text + ".jpg")
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
                tvImageUrlProvider.Text = downloadUrl;
                MessageBox.Show("Upload thành công!\n Link: " + downloadUrl, "THÔNG BÁO", MessageBoxButtons.OK);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Err: " + ex.Message, "THÔNG BÁO", MessageBoxButtons.OK);

            }
        }
    }
}