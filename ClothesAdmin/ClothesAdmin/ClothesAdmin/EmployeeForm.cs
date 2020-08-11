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
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Convert.ToInt32(idAccountSpinEdit.Value));
            setAvatar();
        }

        private void avatarTextEdit_EditValueChanged(object sender, EventArgs e)
        {

        }

        private void sP_GetAllEmployeeGridControl_Click(object sender, EventArgs e)
        {
            setAvatar();
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Convert.ToInt32(idAccountSpinEdit.Value ));
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
            sP_GetAccEmployeeInfoBindingSource.AddNew();
            sP_GetAllEmployeeBindingSource.AddNew();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.sP_GetAccEmployeeInfoBindingSource.EndEdit();
            this.sP_GetAllEmployeeBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {

        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Account' table. You can move, or remove it, as needed.
            this.sP_GetAllEmployeeTableAdapter.Fill(this.clothesDataSet.SP_GetAllEmployee, Program.accountLogin.accountId, null);
            this.sP_GetAccEmployeeInfoTableAdapter.Fill(this.clothesDataSet.SP_GetAccEmployeeInfo, Convert.ToInt32(idAccountSpinEdit.Value));
            setAvatar();
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }
    }
}