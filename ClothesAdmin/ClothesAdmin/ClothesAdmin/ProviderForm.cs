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
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            providerBindingSource.CancelEdit();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.providerBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
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
    }
}