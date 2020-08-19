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
    public partial class EmployeeForm : DevExpress.XtraEditors.XtraForm
    {
        private bool isAdd = false;

        public EmployeeForm()
        {
            InitializeComponent();
        }

        private void EmployeeForm_Load(object sender, EventArgs e)
        {
            loadData();
        }

        private void loadData()
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Account' table. You can move, or remove it, as needed.
            this.sP_GetAllEmployeeTableAdapter.Fill(this.clothesDataSet.SP_GetAllEmployee, Program.accountLogin.accountId, null);
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Convert.ToInt32(idAccountSpinEdit.Value));
            setAvatar();
        }
        private void avatarTextEdit_EditValueChanged(object sender, EventArgs e)
        {

        }

        private void sP_GetAllEmployeeGridControl_Click(object sender, EventArgs e)
        {
            setAvatar();
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Convert.ToInt32(idAccountSpinEdit.Value));
        }

        private void setAvatar()
        {
            if (String.IsNullOrEmpty(avatarTextEdit.Text))
            {
                picAvater.Image = Properties.Resources.no_image;
            }
            else
            {
                picAvater.ImageLocation = avatarTextEdit.Text;
            }
        }

        private void btnSearchEmployee_Click(object sender, EventArgs e)
        {
            if (String.IsNullOrEmpty(edtSearch.Text))
            {
                this.sP_GetAllEmployeeTableAdapter.Fill(this.clothesDataSet.SP_GetAllEmployee, Program.accountLogin.accountId, null);
                this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Convert.ToInt32(idAccountSpinEdit.Value));
                setAvatar();
            }
            else
            {
                this.sP_GetAllEmployeeTableAdapter.Fill(this.clothesDataSet.SP_GetAllEmployee, Program.accountLogin.accountId, edtSearch.Text);
                this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Convert.ToInt32(idAccountSpinEdit.Value));
                setAvatar();
            }

        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            isAdd = true;
            sP_GetAccEmployeeInfoBindingSource.AddNew();
            sP_GetAllEmployeeBindingSource.AddNew();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            isAdd = false;
            this.Validate();
            this.sP_GetAccEmployeeInfoBindingSource.CancelEdit();
            this.sP_GetAllEmployeeBindingSource.CancelEdit();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            if (isAdd)
            {
                String birthday = null, beginDate = null, endDate = null;
                if (birthdayDateEdit.Text.Trim() == null)
                    birthday = "'null'";
                else
                    birthday = "'" + birthdayDateEdit.EditValue.ToString() + "'";

                if (String.IsNullOrEmpty(dateBeginDateEdit.Text))
                    beginDate = "'null'";
                else
                    beginDate = "'" + dateBeginDateEdit.EditValue.ToString() + "'";

                if (String.IsNullOrEmpty(dateEndDateEdit.Text))
                    endDate = "'null'";
                else
                    endDate = "'" + dateEndDateEdit.EditValue.ToString() + "'";

                String sql = "EXEC SP_AddEmployee N'"
                    + firstNameTextEdit.Text.Trim() + "', N'"
                    + lastNameTextEdit.Text.Trim() + "', N'"
                    + phoneTextEdit.Text.Trim() + "', N'"
                    + addressTextEdit.Text.Trim() + "', N'"
                    + avatarTextEdit.Text.Trim() + "', "
                    + birthday + ", "
                    + beginDate + ","
                    + endDate + ", "
                    + Convert.ToInt32(isWorkingSpinEdit.Value) + ", N'"
                    + emailTextEdit.Text.Trim() + "',"
                    + roleIdSpinEdit.Text.Trim() + ", N'"
                    + passwordTextEdit.Text.Trim() + "', N'"
                    + usernameTextEdit.Text.Trim() + "'";
                Program.myReader = Program.ExecSqlDataReader(sql);
                if (Program.myReader == null) return;
                Program.myReader.Read();
                if (Program.myReader == null) return;
                int statusEdit = Program.myReader.GetInt32(0);
                String message = Program.myReader.GetString(1);
                MessageBox.Show(statusEdit + " " + message, "THÔNG BÁO", MessageBoxButtons.OK);
                Program.showToastSave();
            }
            else
            {

                String birthday = null, beginDate = null, endDate = null;
                if (birthdayDateEdit.Text.Trim() == null)
                    birthday = "'null'";
                else
                    birthday = "'" + birthdayDateEdit.EditValue.ToString() + "'";

                if (String.IsNullOrEmpty(dateBeginDateEdit.Text))
                    beginDate = "'null'";
                else
                    beginDate = "'" + dateBeginDateEdit.EditValue.ToString() + "'";

                if (String.IsNullOrEmpty(dateEndDateEdit.Text ))
                    endDate = "'null'";
                else
                    endDate = "'" + dateEndDateEdit.EditValue.ToString() + "'";

                String sql = "EXEC SP_UpdateEmployee "
                    + Convert.ToInt32(idSpinEdit.Value) + ", N'"
                    + firstNameTextEdit.Text.Trim() + "', N'"
                    + lastNameTextEdit.Text.Trim() + "', N'"
                    + phoneTextEdit.Text.Trim() + "', N'"
                    + addressTextEdit.Text.Trim() + "', N'"
                    + avatarTextEdit.Text.Trim() + "', "
                    + birthday + ", "
                    + beginDate + ","
                    + endDate + ", "
                    + Convert.ToInt32(isWorkingSpinEdit.Value) + ", N'"
                    + emailTextEdit.Text.Trim() + "',"
                    + roleIdSpinEdit.Text.Trim() + ", N'"
                    + passwordTextEdit.Text.Trim() + "', N'"
                    + usernameTextEdit.Text.Trim() + "', "
                    + idAccountSpinEdit.Text.Trim();
                MessageBox.Show(sql, "sql", MessageBoxButtons.OK);
                Program.myReader = Program.ExecSqlDataReader(sql);
                if (Program.myReader == null) return;
                Program.myReader.Read();
                if (Program.myReader == null) return;
                int statusEdit = Program.myReader.GetInt32(0);
                String message = Program.myReader.GetString(1);
                MessageBox.Show(statusEdit + " " + message, "THÔNG BÁO", MessageBoxButtons.OK);
                Program.showToastUpdate();
            }
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (sP_GetAllEmployeeBindingSource.Count == 0)
            {
                MessageBox.Show("Không có employee để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (MessageBox.Show("Bạn có chắc chắn muốn xóa " + ((DataRowView)this.sP_GetAllEmployeeBindingSource.Current).Row["firstName"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    String sql = "EXEC SP_DelEmployee " + Convert.ToInt32(idSpinEdit.Value);
                    Program.myReader = Program.ExecSqlDataReader(sql);
                    loadData();
                    Program.showToastDel();

                }
            }
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            isAdd = false;
            loadData();
            Program.showToastReload();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }
    }
}