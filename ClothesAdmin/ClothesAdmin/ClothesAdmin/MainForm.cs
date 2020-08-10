using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevExpress.XtraBars;

namespace ClothesAdmin
{
    public partial class MainForm : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        private Form form;
        private ProductForm productForm = null;
        private CategoryForm categoryForm = null;
        private ColorForm colorForm = null;
        private EmployeeForm employeeForm = null;
        private SizeForm sizeForm = null;
        private SizeColorForm sizeColorForm = null;
        private AccountsForm accountsForm = null;
        private ProviderForm providerForm = null;
        public MainForm()
        {
            InitializeComponent();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
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

        private void btnInvoice_ItemClick(object sender, ItemClickEventArgs e)
        {
            DialogResult dr = MessageBox.Show("Bạn có chắc muốn đăng xuất?", "", MessageBoxButtons.OKCancel);
            if (dr == DialogResult.OK)
            {
                Program.mainForm.Hide();
                Program.frmLogin = new LoginForm();
                Invoke((Action)(() => { Program.frmLogin.ShowDialog(); }));
                this.Close();
            }
        }

        private void barButtonItem4_ItemClick(object sender, ItemClickEventArgs e)
        {

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

        private void btnProductSizeColor_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(SizeColorForm));
            if (form == null)
            {
                IsMdiContainer = true;
                sizeColorForm = new SizeColorForm();
                sizeColorForm.MdiParent = this;
                sizeColorForm.Show();
            }
            else form.Activate();
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
    }
}