using DevExpress.XtraBars;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ClothesAdmin
{
    public partial class LoginForm : DevExpress.XtraEditors.XtraForm
    {
        private bool isLoginSuccess = false;
        public LoginForm()
        {
            InitializeComponent();
        }

        private void FormLogin_Load(object sender, EventArgs e)
        {

        }

        private void btnDangNhap_Click(object sender, EventArgs e)
        {
            if (txUsername.Text.Trim() == "")
            {
                MessageBox.Show("Bạn chưa nhập email hoặc username ", "Thông báo", MessageBoxButtons.OK);
                return;
            }
            else if (txPassword.Text.Trim() == "")
            {
                MessageBox.Show("Bạn chưa nhập mật khẩu", "Thông báo", MessageBoxButtons.OK);
                return;
            }
            int resultConnect = Program.KetNoi();
            if (resultConnect == 0) return;

            String sql = "EXEC SP_AdminLogin N'" + txUsername.Text.Trim() + "', N'" + txPassword.Text.Trim() + "'";

            Program.myReader = Program.ExecSqlDataReader(sql);
            if (Program.myReader == null) return;
            Program.myReader.Read();
            if (Program.myReader == null) return;
            int statusLogin = Program.myReader.GetInt32(0);

            if (statusLogin == -1)
            {
                MessageBox.Show("Email hoặc username của bạn không tồn tại trong hệ thống", "", MessageBoxButtons.OK);
                this.ActiveControl = txUsername;
                return;
            }
            else if (statusLogin == -2)
            {
                MessageBox.Show("Mật khẩu của bạn không chính xác", "", MessageBoxButtons.OK);
                this.ActiveControl = txPassword;
                return;
            }
            else if (statusLogin == -3)
            {
                MessageBox.Show("Tài khoản của bạn chưa được xác thực, vui lòng kiểm tra email và xác thực tài khoản trước khi đăng nhập!.", "", MessageBoxButtons.OK);
                return;
            }
            else if (statusLogin > 0)
            {
                isLoginSuccess = true;
                this.Hide();
                String avatar = null;
                if (!Program.myReader.IsDBNull(6))
                {
                    avatar = Program.myReader?.GetString(6) ?? null;
                }
                Program.accountLogin = new AccountLogin()
                {
                    accountId = statusLogin,
                    roleId = Program.myReader.GetInt32(1),
                    roleName = Program.myReader.GetString(2).ToString() ?? "",
                    email = Program.myReader.GetString(3).ToString() ?? "",
                    username = Program.myReader.GetString(4).ToString() ?? "",
                    name = Program.myReader?.GetString(5).ToString() ?? "",
                    avatar = avatar,
                    idEmployee = Program.myReader?.GetInt32(7) ?? null
                };
                Program.mainForm = new MainForm();
                Program.mainForm.tvUserName.Text ="Username: "+ Program.accountLogin.username+" ";
                Program.mainForm.tvName.Text = "Name: "+Program.accountLogin.name + " ";
                Program.mainForm.tvRoleName.Text ="Role: "+ Program.accountLogin.roleName + " ";
                Program.mainForm.tvIdEmployee.Text = "Employee Id: " + Program.accountLogin.idEmployee + " ";
                if (Program.accountLogin.roleId ==1)
                {
                    Program.mainForm.btnEmployee.Visibility = BarItemVisibility.Always;
                    Program.mainForm.rbSttatistic.Visible = true;
                }
                else if (Program.accountLogin.roleId == 2)
                {
                    Program.mainForm.btnEmployee.Visibility = BarItemVisibility.Never;
                    Program.mainForm.rbSttatistic.Visible = false;
                }
                else{
                    MessageBox.Show("Sai thông tin đăng nhập, vui lòng kiểm tra lại", "", MessageBoxButtons.OK);
                    return;
                }
                Program.mainForm.Activate();
                Invoke((Action)(() => { Program.mainForm.ShowDialog(); }));
                this.Close();
            }
            else
            {
                MessageBox.Show("Lỗi đăng nhập, vui lòng  kiểm tra lại thông tin.", "", MessageBoxButtons.OK);
                return;
            }
        }

        private void btnThoat_Click(object sender, EventArgs e)
        {
            DialogResult dr = MessageBox.Show("Bạn có chắc muốn thoát chương trình", "Thông báo", MessageBoxButtons.YesNo);
            if (dr == DialogResult.Yes)
            {
                Application.ExitThread();
            }
            else return;
        }

        private void frmDangNhap_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (!isLoginSuccess)
            {
                DialogResult dr = MessageBox.Show("Bạn có chắc muốn thoát chương trình login", "Thông báo", MessageBoxButtons.YesNo);
                if (dr == DialogResult.Yes)
                {
                    Application.ExitThread();
                }
                else e.Cancel = true;
            }
            else return;
        }
    }
}
