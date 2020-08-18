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
    public partial class PromotionForm : DevExpress.XtraEditors.XtraForm
    {
        private int check = 0;
        public PromotionForm()
        {
            InitializeComponent();
        }

        private void promotionBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.promotionBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void loadData()
        {
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.PromotionItem' table. You can move, or remove it, as needed.
            this.promotionItemTableAdapter.Fill(this.clothesDataSet.PromotionItem);
            // TODO: This line of code loads data into the 'clothesDataSet.Promotion' table. You can move, or remove it, as needed.
            this.promotionTableAdapter.Fill(this.clothesDataSet.Promotion);
        }


        private void loadDataItem()
        {
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.PromotionItem' table. You can move, or remove it, as needed.
            this.promotionItemTableAdapter.Fill(this.clothesDataSet.PromotionItem);
            // TODO: This line of code loads data into the 'clothesDataSet.Promotion' table. You can move, or remove it, as needed.
        }

        private void PromotionForm_Load(object sender, EventArgs e)
        {
            check++;
            loadData();
        }

        private void addProductPromoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            promotionItemBindingSource.AddNew();
        }

        private void deleteProductPromoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (promotionItemBindingSource.Count == 0)
            {
                MessageBox.Show("Không có product promotion để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }

            if (MessageBox.Show("Bạn có chắc chắn muốn xóa " + ((DataRowView)this.promotionBindingSource.Current).Row["name"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                try
                {
                    promotionItemBindingSource.RemoveCurrent();
                    this.promotionItemTableAdapter.Update(this.clothesDataSet.PromotionItem);
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Lỗi xóa Promotion" + ex.Message, "", MessageBoxButtons.OK);
                }
            }
        }

        private void reloadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            loadDataItem();
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            promotionBindingSource.AddNew();
        }

        private void btnDelProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (promotionBindingSource.Count == 0)
            {
                MessageBox.Show("Không có promotion để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else
            {
                if (promotionItemBindingSource.Count > 0)
                {
                    MessageBox.Show("Promotion đã có sản phẩm giảm giá, không thể xóa", "", MessageBoxButtons.OK);
                    return;
                }

                if (MessageBox.Show("Bạn có chắc chắn muốn xóa " + ((DataRowView)this.promotionBindingSource.Current).Row["name"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    try
                    {
                        //phải chạy lệnh del from where mới chính xác
                        promotionBindingSource.RemoveCurrent();
                        //đẩy dữ liệu về adapter
                        this.promotionTableAdapter.Update(this.clothesDataSet.Promotion);
                        Program.showToastDel();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Lỗi xóa Promotion" + ex.Message, "", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }

        private void btnReloadProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            loadData();
            Program.showToastReload();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            this.promotionBindingSource.CancelEdit();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.promotionBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
            Program.showToastSave();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.promotionItemBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.promotionItemBindingSource.CancelEdit();
        }

        private void productComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            idProductSpinEdit.Value = Convert.ToInt32(productComboBox.SelectedValue);
        }

        private void idProductSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            productComboBox.SelectedValue = idProductSpinEdit.Value;
        }

        private void promotionComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            idPromoSpinEdit.Value = Convert.ToInt32(promotionComboBox.SelectedValue);
        }

        private void idPromoSpinEdit_EditValueChanged(object sender, EventArgs e)
        {
            try
            {
                promotionComboBox.SelectedValue = idPromoSpinEdit.Value;
            }
            catch (Exception ex) { }
        }
    }
}