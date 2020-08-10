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
        public EmployeeForm()
        {
            InitializeComponent();
        }

        private void EmployeeForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Account' table. You can move, or remove it, as needed.
            this.sP_GetAllEmployeeTableAdapter.Fill(this.clothesDataSet.SP_GetAllEmployee, Program.accountLogin.accountId,null);
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Program.accountLogin.accountId);
            setAvatar();
        }

        private void avatarTextEdit_EditValueChanged(object sender, EventArgs e)
        {

        }

        private void sP_GetAllEmployeeGridControl_Click(object sender, EventArgs e)
        {
            setAvatar();
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, idAccountSpinEdit.Value );
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
            this.sP_GetAllEmployeeTableAdapter.Fill(this.clothesDataSet.SP_GetAllEmployee, Program.accountLogin.accountId, edtSearch.Text);
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Program.accountLogin.accountId);
            setAvatar();
        }

     
    }
}