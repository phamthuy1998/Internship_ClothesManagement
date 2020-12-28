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
    public partial class ProductNewForm : DevExpress.XtraEditors.XtraForm
    {
        public ProductNewForm()
        {
            InitializeComponent();
        }

        private void fillToolStripButton_Click(object sender, EventArgs e)
        {
            try
            {
                this.sP_GetProductTableAdapter.Fill(this.clothesDataSet.SP_GetProduct, new System.Nullable<int>(((int)(System.Convert.ChangeType(providerIdToolStripTextBox.Text, typeof(int))))), new System.Nullable<int>(((int)(System.Convert.ChangeType(categoryIDToolStripTextBox.Text, typeof(int))))));
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }

        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (sP_GetProductBindingSource.Count == 0)
            {
                MessageBox.Show("Không có product để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                    try
                    {
                    //phải chạy lệnh del from where mới chính xác
                    sP_GetProductBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        //this.sP_GetProductTableAdapter.Update(this.clothesDataSet.sq);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa product " + ex.Message, "", MessageBoxButtons.OK);
                    }
            }
        }

        private void fillToolStripButton_Click_1(object sender, EventArgs e)
        {
            try
            {
                this.sP_GetProductTableAdapter.Fill(this.clothesDataSet.SP_GetProduct, new System.Nullable<int>(((int)(System.Convert.ChangeType(providerIdToolStripTextBox.Text, typeof(int))))), new System.Nullable<int>(((int)(System.Convert.ChangeType(categoryIDToolStripTextBox.Text, typeof(int))))));
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }

        }
    }
}