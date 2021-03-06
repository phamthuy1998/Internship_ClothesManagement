﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevExpress.XtraBars;
using System.Drawing.Imaging;
using Firebase.Storage;

namespace ClothesAdmin
{
    public partial class MainForm : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        private Form form;
        private ProductForm productForm = null;
        private CategoryForm categoryForm = null;
        private ColorForm colorForm = null;
        private InvoiceForm invoiceForm = null;
        private EmployeeForm employeeForm = null;
        private SizeForm sizeForm = null;
        private ImportCuponForm importCuponForm = null;
        private InvoiceStatisticFrom  invoiceStatisticFrom = null;
        private SizeColorForm sizeColorForm = null;
        private PromotionForm promotionForm = null;
        private ShopInfoForm shopInfoForm = null;
        private ProviderForm providerForm = null;
        private BackupRestoreForm backupRestoreForm = null;
        private ProfitForm profitForm = null;
        private RevenueForm revenueForm = null;
        private InventoryForm inventoryForm = null;
        private ShopDataForm shopData = null; 
        private ProductNewForm productNewForm = null;
        private ProductStatisticForm productStatisticForm = null;
         private WarehouseFormcs warehouseFormcs = null;
        public MainForm()
        {
            InitializeComponent();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            FormBorderStyle = FormBorderStyle.None;
            Left = Top = 0;
            Width = Screen.PrimaryScreen.WorkingArea.Width;
            Height = Screen.PrimaryScreen.WorkingArea.Height;
            // TODO: This line of code loads data into the 'clothesDataSet.Account' table. You can move, or remove it, as needed.
            this.accountTableAdapter.Fill(this.clothesDataSet.Account);
            // TODO: This line of code loads data into the 'clothesDataSet.Employee' table. You can move, or remove it, as needed.
            this.employeeTableAdapter.Fill(this.clothesDataSet.Employee);
            // TODO: This line of code loads data into the 'clothesDataSet.Employee' table. You can move, or remove it, as needed.
            this.employeeTableAdapter.Fill(this.clothesDataSet.Employee);

        }

        private void btnProduct_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ProductForm));
            if (form == null)
            {
                IsMdiContainer = true;
                productForm = new ProductForm();
                productForm.MdiParent = this;
                productForm.Show();
            }
            else form.Activate();
        }

        private Form CheckExists(Type ftype)
        {
            foreach (Form f in this.MdiChildren)
                if (f.GetType() == ftype)
                    return f;
            return null;
        }

        private void backstageViewClientControl1_Load(object sender, EventArgs e)
        {

        }

        private void btnCategory_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(CategoryForm));
            if (form == null)
            {
                IsMdiContainer = true;
                categoryForm = new CategoryForm();
                categoryForm.MdiParent = this;
                categoryForm.Show();
            }
            else form.Activate();
        }

        private void btnProvider_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ProviderForm));
            if (form == null)
            {
                IsMdiContainer = true;
                providerForm = new ProviderForm();
                providerForm.MdiParent = this;
                providerForm.Show();
            }
            else form.Activate();
        }

        private void backstageViewClientControl1_Load_1(object sender, EventArgs e)
        {
            if (String.IsNullOrEmpty(tvAvatar.Text))
            {
                picAvater.Image = Properties.Resources.no_image;
            }
            else
            {
                picAvater.ImageLocation = tvAvatar.Text;
            }

        }

        private void employeeBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.employeeBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void employeeBindingNavigatorSaveItem_Click_1(object sender, EventArgs e)
        {
            this.Validate();
            this.employeeBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void btnSaveInfo_Click(object sender, EventArgs e)
        {
            try
            {
                employeeBindingSource.EndEdit();
                //lấy dữ liệu hiện tại của control phía dưới lưu lên bên trên
                employeeBindingSource.ResetCurrentItem();
                //ghi dữ liệu tạm về server, fill là ghi tạm, update là ghi thật
                // lệnh này sẽ lưu tất cả các giáo viên có thay đổi thông tin về server
                this.employeeTableAdapter.Update(this.clothesDataSet.Employee);
                MessageBox.Show("Cập nhật thông tin thành công", "Thông báo", MessageBoxButtons.OK);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Lỗi lưu thông tin" + ex.Message, "", MessageBoxButtons.OK);
            }
        }

        private void linkChangeAvatar_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Title = "Select image";
            ofd.Filter = "Image Files(*.jpg|*.jpg|*.png|*.jpeg";
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                System.Drawing.Image img = new Bitmap(ofd.FileName);
                picAvater.Image = img.GetThumbnailImage(153, 182, null, new IntPtr());
            }
        }

        private void recentItemControl1_SelectedTabChanged(object sender, DevExpress.XtraBars.Ribbon.RecentItemEventArgs e)
        {

        }

        private void cbChangePass_CheckedChanged(object sender, EventArgs e)
        {
            if (cbChangePass.Checked)
            {
                gbChangePass.Visible = true;
            }
            else
            {
                gbChangePass.Visible = false;
            }
        }

        private void backstageViewClientControl2_Load(object sender, EventArgs e)
        {
            gbChangePass.Visible = false;
            cbChangePass.Checked = false;
        }

        private void btnSaveSeting_Click(object sender, EventArgs e)
        {
            if (cbChangePass.Checked)
            {
                if (String.IsNullOrEmpty(tvCurrentPass.Text.Trim()) || String.IsNullOrEmpty(tvNewPass.Text.Trim())
                    || String.IsNullOrEmpty(tvConfirmPass.Text.Trim()))
                {
                    MessageBox.Show("Bạn chưa nhập đủ thông tin!", "Thông báo", MessageBoxButtons.OK);
                    return;
                }
                else if (!tvNewPass.Text.Trim().Equals(tvConfirmPass.Text.Trim()))
                {
                    MessageBox.Show("Mật khẩu cũ và mật khẩu mưới không khớp!", "Thông báo", MessageBoxButtons.OK);
                    return;
                }
                String sql = "EXEC ChangePassword " + Program.accountLogin.accountId + ", N'" + tvCurrentPass.Text.Trim() + "', N'" + tvNewPass.Text.Trim() + "'";
                Program.myReader = Program.ExecSqlDataReader(sql);
                if (Program.myReader == null) return;
                Program.myReader.Read();
                int statusLogin = Program.myReader.GetInt32(0);

                if (statusLogin == -1)
                {
                    MessageBox.Show("Mật khẩu hiện tại không đúng", "Thông báo", MessageBoxButtons.OK);
                    return;
                }
                else
                {
                    MessageBox.Show("Thay đổi mật khẩu thành công", "Thông báo", MessageBoxButtons.OK);
                }

            }
            else
            {
                MessageBox.Show("Chưa có thay đổi", "Thông báo", MessageBoxButtons.OK);
            }
        }

        private void btnLogOut_ItemClick(object sender, DevExpress.XtraBars.Ribbon.BackstageViewItemEventArgs e)
        {
            DialogResult dr = MessageBox.Show("Are you sure you want to sign out?", "Warning", MessageBoxButtons.OKCancel);
            if (dr == DialogResult.OK)
            {
                backstageViewControl1.Hide();
                Program.mainForm.Hide();
                Program.frmLogin = new LoginForm();
                Invoke((Action)(() => { Program.frmLogin.ShowDialog(); }));
                this.Close();
            }
        }

        private void btnInvoice_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(InvoiceForm));
            if (form == null)
            {
                IsMdiContainer = true;
                invoiceForm = new InvoiceForm();
                invoiceForm.MdiParent = this;
                invoiceForm.Show();
            }
            else form.Activate();
        }

        private void btnColor_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ColorForm));
            if (form == null)
            {
                IsMdiContainer = true;
                colorForm = new ColorForm();
                colorForm.MdiParent = this;
                colorForm.Show();
            }
            else form.Activate();
        }

        private void btnSize_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(SizeForm));
            if (form == null)
            {
                IsMdiContainer = true;
                sizeForm = new SizeForm();
                sizeForm.MdiParent = this;
                sizeForm.Show();
            }
            else form.Activate();
        }

        private void btnSignout_ItemClick(object sender, ItemClickEventArgs e)
        {
            DialogResult dr = MessageBox.Show("Bạn có chắc muốn đăng xuất?", "", MessageBoxButtons.OKCancel);
            if (dr == DialogResult.OK)
            {
                backstageViewControl1.Hide();
                Program.mainForm.Hide();
                Program.frmLogin = new LoginForm();
                Invoke((Action)(() => { Program.frmLogin.ShowDialog(); }));
                this.Close();
            }
        }

        private void btnEmloyee_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(EmployeeForm));
            if (form == null)
            {
                IsMdiContainer = true;
                employeeForm = new EmployeeForm();
                employeeForm.MdiParent = this;
                employeeForm.Show();
            }
            else form.Activate();
        }

        private void tvAvatar_EditValueChanged(object sender, EventArgs e)
        {

        }

        private void btnPromo_ItemClick(object sender, ItemClickEventArgs e)
        {
             form = this.CheckExists(typeof(PromotionForm));
            if (form == null)
            {
                IsMdiContainer = true;
                promotionForm = new PromotionForm();
                promotionForm.MdiParent = this;
                promotionForm.Show();
            }
            else form.Activate();
        }

        private void barButtonItem4_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ImportCuponForm));
            if (form == null)
            {
                IsMdiContainer = true;
                importCuponForm = new ImportCuponForm();
                importCuponForm.MdiParent = this;
                importCuponForm.Show();
            }
            else form.Activate();
        }

        private void btnInvoiceStatis_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(InvoiceStatisticFrom));
            if (form == null)
            {
                IsMdiContainer = true;
                invoiceStatisticFrom = new InvoiceStatisticFrom();
                invoiceStatisticFrom.MdiParent = this;
                invoiceStatisticFrom.Show();
            }
            else form.Activate();
        }

        private void btnProductStatistic_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ProductStatisticForm));
            if (form == null)
            {
                IsMdiContainer = true;
                productStatisticForm = new ProductStatisticForm();
                productStatisticForm.MdiParent = this;
                productStatisticForm.Show();
            }
            else form.Activate();
        }

        private async void btnSaveImg_Click(object sender, EventArgs e)
        {
            if (tvAvatar.Text == null || picAvater.Image == null || picAvater.Image == Properties.Resources.no_image)
            {
                MessageBox.Show("Chưa có hình ảnh, không thể upload", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            var stream = new System.IO.MemoryStream();
            picAvater.Image.Save(stream, ImageFormat.Jpeg);
            stream.Position = 0;
            var task = new FirebaseStorage("ptshop-8b8b3.appspot.com")
                            .Child("account")
                            .Child("account_" + Program.accountLogin.idEmployee + ".jpg")
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
                tvAvatar.Text = downloadUrl;
                MessageBox.Show("Upload thành công!\n Link: " + downloadUrl, "THÔNG BÁO", MessageBoxButtons.OK);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Err: " + ex.Message, "THÔNG BÁO", MessageBoxButtons.OK);

            }
        }

        private void picAvater_Click(object sender, EventArgs e)
        {

        }

        private void btnStatistic1_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ProductStatisticForm));
            if (form == null)
            {
                IsMdiContainer = true;
                productStatisticForm = new ProductStatisticForm();
                productStatisticForm.MdiParent = this;
                productStatisticForm.Show();
            }
            else form.Activate();
        }

        private void btnWarehouse_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(WarehouseFormcs));
            if (form == null)
            {
                IsMdiContainer = true;
                warehouseFormcs = new WarehouseFormcs();
                warehouseFormcs.MdiParent = this;
                warehouseFormcs.Show();
            }
            else form.Activate();
        }

        private void btnEmployee_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(EmployeeForm));
            if (form == null)
            {
                IsMdiContainer = true;
                employeeForm = new EmployeeForm();
                employeeForm.MdiParent = this;
                employeeForm.Show();
            }
            else form.Activate();
        }

        private void btnShopInfo_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ShopInfoForm));
            if (form == null)
            {
                IsMdiContainer = true;
                shopInfoForm = new ShopInfoForm();
                shopInfoForm.MdiParent = this;
                shopInfoForm.Show();
            }
            else form.Activate();
        }

        private void btnBackupRestore_ItemClick(object sender, ItemClickEventArgs e)
        {

            form = this.CheckExists(typeof(BackupRestoreForm));
            if (form == null)
            {
                IsMdiContainer = true;
                backupRestoreForm = new BackupRestoreForm();
                backupRestoreForm.MdiParent = this;
                backupRestoreForm.Show();
            }
            else form.Activate();
        }

        private void barButtonItem15_ItemClick(object sender, ItemClickEventArgs e)
        {
            DialogResult dr = MessageBox.Show("Are you sure you want to sign out?", "Warning", MessageBoxButtons.OKCancel);
            if (dr == DialogResult.OK)
            {
                backstageViewControl1.Hide();
                Program.mainForm.Hide();
                Program.frmLogin = new LoginForm();
                Invoke((Action)(() => { Program.frmLogin.ShowDialog(); }));
                this.Close();
            }
        }

        private void btnProfit_ItemClick(object sender, ItemClickEventArgs e)
        {

            form = this.CheckExists(typeof(ProfitForm));
            if (form == null)
            {
                IsMdiContainer = true;
                profitForm = new ProfitForm();
                profitForm.MdiParent = this;
                profitForm.Show();
            }
            else form.Activate();
        }

        private void btnRevenue_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(RevenueForm));
            if (form == null)
            {
                IsMdiContainer = true;
                revenueForm = new RevenueForm();
                revenueForm.MdiParent = this;
                revenueForm.Show();
            }
            else form.Activate();
        }

        private void btnWareHouse1_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(InventoryForm));
            if (form == null)
            {
                IsMdiContainer = true;
                inventoryForm = new InventoryForm();
                inventoryForm.MdiParent = this;
                inventoryForm.Show();
            }
            else form.Activate();
        }

        private void barButtonItem19_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ShopDataForm));
            if (form == null)
            {
                IsMdiContainer = true;
                shopData = new ShopDataForm();
                shopData.MdiParent = this;
                shopData.Show();
            }
            else form.Activate();
        }

        private void barButtonItem20_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ProductNewForm));
            if (form == null)
            {
                IsMdiContainer = true;
                productNewForm = new ProductNewForm();
                productNewForm.MdiParent = this;
                productNewForm.Show();
            }
            else form.Activate();
        }
    }
}