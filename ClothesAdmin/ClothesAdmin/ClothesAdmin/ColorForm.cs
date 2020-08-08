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
    public partial class ColorForm : DevExpress.XtraEditors.XtraForm
    {
        public ColorForm()
        {
            InitializeComponent();
        }

        private void colorBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.colorBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void ColorForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.Fill(this.clothesDataSet.ProductSizeColor);
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.Fill(this.clothesDataSet.Color);

        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.colorBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void colorHexTextEdit_EditValueChanged(object sender, EventArgs e)
        {
            System.Drawing.Color color = ColorTranslator.FromHtml(colorHexTextEdit.Text);
            btnChooseColor.BackColor = color;
        }

        private void btnChooseColor_Click(object sender, EventArgs e)
        {
            ColorDialog dlg = new ColorDialog();
            var dialog = dlg.ShowDialog();
            if (dialog == DialogResult.OK)
            {
                btnChooseColor.BackColor = dlg.Color;
                colorHexTextEdit.Text = "#" + (dlg.Color.ToArgb() & 0x00FFFFFF).ToString("X6");
            }
        }

        private void lbChooseColor_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {

            ColorDialog dlg = new ColorDialog();
            var dialog = dlg.ShowDialog();
            if (dialog == DialogResult.OK)
            {
                btnChooseColor.BackColor = dlg.Color;
                colorHexTextEdit.Text = "#" + (dlg.Color.ToArgb() & 0x00FFFFFF).ToString("X6");
            }
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            colorBindingSource.AddNew();
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (colorBindingSource.Count == 0)
            {
                MessageBox.Show("Không có providers để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (productSizeColorBindingSource.Count > 0)
                {
                    MessageBox.Show("Màu đã có sản phẩm, không thể xóa, trạng thái của bảng màu sẽ được chuyển qua đã xóa", "", MessageBoxButtons.OK);
                    activeSpinEdit.EditValue = 1;
                    this.Validate();
                    this.productSizeColorBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa " + ((DataRowView)this.colorBindingSource.Current).Row["colorName"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        colorBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.colorTableAdapter.Update(this.clothesDataSet.Color);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa Color" + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.Fill(this.clothesDataSet.ProductSizeColor);
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.Fill(this.clothesDataSet.Color);
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            colorBindingSource.CancelEdit();
        }
    }
}