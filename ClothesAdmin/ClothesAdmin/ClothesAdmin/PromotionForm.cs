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
        private Boolean checkFirstTime = true;
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
            try
            {
                // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
                this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
                // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
                this.categoryTableAdapter.Fill(this.clothesDataSet.Category);

                this.productTableAdapter.FillByPromo(this.clothesDataSet.Product, int.Parse(idSpinEdit.Text));
                // TODO: This line of code loads data into the 'clothesDataSet.PromotionItem' table. You can move, or remove it, as needed.
                this.promotionItemTableAdapter.Fill(this.clothesDataSet.PromotionItem);
                // TODO: This line of code loads data into the 'clothesDataSet.Promotion' table. You can move, or remove it, as needed.
                this.promotionTableAdapter.Fill(this.clothesDataSet.Promotion);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error ", "Error", MessageBoxButtons.OK);
                return;
            }
        }


        private void loadDataItem()
        {
            try
            {
                this.productTableAdapter.FillByPromo(this.clothesDataSet.Product, int.Parse(idSpinEdit.Text));
                // TODO: This line of code loads data into the 'clothesDataSet.PromotionItem' table. You can move, or remove it, as needed.
                this.promotionItemTableAdapter.Fill(this.clothesDataSet.PromotionItem);
                // TODO: This line of code loads data into the 'clothesDataSet.Promotion' table. You can move, or remove it, as needed.
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error ", "Error", MessageBoxButtons.OK);
                return;
            }
        }

        private void PromotionForm_Load(object sender, EventArgs e)
        {

            check++;
            loadData();
            cbbType.Items.Add(new { Text = "percent" });
            cbbType.Items.Add(new { Text = "absolute" });

            cbbType.ValueMember = "Text";
            cbbType.DisplayMember = "Text";
            if (checkFirstTime)
            {
                cbbType.Text = typeTextBox.Text;
                checkFirstTime = false;
            }


            providerComboBox.SelectedIndex = 0;
            categoryComboBox.SelectedIndex = 0;
        }

        private void addProductPromoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                radAddOne.Enabled =productComboBox.Enabled= true;
                this.productTableAdapter.FillByPromo(this.clothesDataSet.Product, int.Parse(idSpinEdit.Text));
                promotionItemBindingSource.AddNew();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error ", "Error", MessageBoxButtons.OK);
                return;
            }
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
            providerComboBox.SelectedIndex = 0;
            categoryComboBox.SelectedIndex = 0;
            loadDataItem();
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            promotionBindingSource.AddNew();
            typeTextBox.Text = "1";
            activeSpinEdit.Text = "1";
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
                        MessageBox.Show("Error " + ex.Message, "", MessageBoxButtons.OK);
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
            providerComboBox.SelectedIndex = 0;
            categoryComboBox.SelectedIndex = 0;
            loadData();
            Program.showToastReload();
        }

        private void btnCancelAddProvider_Click(object sender, EventArgs e)
        {
            this.promotionBindingSource.CancelEdit();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            if (beginDateDateEdit.Text == null || beginDateDateEdit.Text == "")
            {
                MessageBox.Show("Vui lòng nhập ngày bắt đầu", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (endDateDateEdit.Text == null || endDateDateEdit.Text == "")
            {
                MessageBox.Show("VUi lòng chọn ngày kết thúc", "Error", MessageBoxButtons.OK);
                return;
            }
            DateTime dtBegin = DateTime.Parse(beginDateDateEdit.Text);
            DateTime dtEnd = DateTime.Parse(endDateDateEdit.Text);
            DateTime curentDate = DateTime.Now;

            if (dtBegin.Date >= dtEnd.Date)
            {
                MessageBox.Show("Ngày kết thúc phải lớn hơn ngày bắt đầu", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (curentDate.Date > dtEnd.Date)
            {
                MessageBox.Show("Ngày kết thúc chương trình khuyến mãi phải lớn hơn ngày hiện tại", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (nameTextEdit.Text == "")
            {
                MessageBox.Show("Tên chương trình khuyến mãi không được bỏ trống", "Error", MessageBoxButtons.OK);
                return;
            }
            else if (float.Parse(valueSpinEdit.Text) >= 1 || float.Parse(valueSpinEdit.Text) < 0)
            {
                MessageBox.Show("Gía trị khuyến mãi phải bé hơn 1 và lớn hơn 0", "Error", MessageBoxButtons.OK);
                return;
            }
            try
            {
                if (activeSpinEdit.Text == "")
                {
                    activeSpinEdit.Text = "1";
                }
                this.Validate();
                this.promotionBindingSource.EndEdit();
                this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                Program.showToastSave();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error add database: " + ex.Message, "", MessageBoxButtons.OK);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (idSpinEdit.Text == "")
            {
                MessageBox.Show("Vui lòng nhập sản phẩm ", "Error", MessageBoxButtons.OK);
                return;
            }

            try
            {
                if (radProvider.Checked)
                {
                    try
                    {
                        String sql = "EXEC SP_InsertPromoProvider  " + idSpinEdit.Text+", "+providerID.Text;

                        Program.myReader = Program.ExecSqlDataReader(sql);
                        if (Program.myReader == null) return;
                        else
                            Program.showToastDel();
                        loadDataItem();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Error " + ex.Message, "Error", MessageBoxButtons.OK);
                    }
                }
                else if (radCategory.Checked)
                {
                    try
                    {
                        String sql = "EXEC SP_InsertPromoCategory  " + idSpinEdit.Text + ", " + categoryID.Text;

                        Program.myReader = Program.ExecSqlDataReader(sql);
                        if (Program.myReader == null) return;
                        else
                            Program.showToastDel();
                        loadDataItem();
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Error " + ex.Message, "Error", MessageBoxButtons.OK);
                    }
                }
                else if (radPrice.Checked)
                {

                    if (priceFrom.Text == "" || priceTo.Text == "")
                    {
                        MessageBox.Show("Vui lòng nhập giá", "Error", MessageBoxButtons.OK);
                        return;
                    }
                    float priceFromCalue = float.Parse(priceFrom.Text);
                    float priceEnd = float.Parse(priceTo.Text);
                    if (priceFromCalue < 0)
                    {
                        MessageBox.Show("Vui lòng nhập giá bắt đầu lớn hơn 0", "Error", MessageBoxButtons.OK);
                        return;
                    }
                    else if (priceEnd < 0)
                    {
                        MessageBox.Show("Vui lòng nhập giá kết thúc lớn hơn 0", "Error", MessageBoxButtons.OK);
                        return;
                    }
                    else if (priceFromCalue > priceEnd)
                    {
                        MessageBox.Show("Vui lòng nhập giá kết thúc lớn hơn giá bắt đầu", "Error", MessageBoxButtons.OK);
                        return;
                    }
                    else
                    {
                        try
                        {
                            String sql = "EXEC SP_InsertPromoPriceInRange  " 
                                + idSpinEdit.Text + ", " + priceFromCalue + "," + priceEnd;

                            Program.myReader = Program.ExecSqlDataReader(sql);
                            if (Program.myReader == null) return;
                            else
                                Program.showToastDel();
                            loadDataItem();
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show("Error " + ex.Message, "Error", MessageBoxButtons.OK);
                        }
                        return;
                    }
                }
                else if (radAddOne.Checked)
                {
                    this.Validate();
                    this.promotionItemBindingSource.EndEdit();
                    this.tableAdapterManager.UpdateAll(this.clothesDataSet);
                }else
                {
                    MessageBox.Show("Vui lòng chọn loại cần thêm và nhập thông tin ", "Error", MessageBoxButtons.OK);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error add database: " + ex.Message, "", MessageBoxButtons.OK);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {

            radAddOne.Enabled = productComboBox.Enabled = false;
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

        private void cbbType_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (!checkFirstTime)
                typeTextBox.Text = cbbType.Text.ToString();
        }

        private void typeTextBox_TextChanged(object sender, EventArgs e)
        {
            cbbType.Text = typeTextBox.Text;
        }

        private void clearAllProductToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (promotionItemBindingSource.Count == 0)
            {
                MessageBox.Show("Không có product promotion để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }
            else if (promotionItemBindingSource.Count == 0)
            {
                MessageBox.Show("Không có product promotion để xóa!", "THÔNG BÁO", MessageBoxButtons.OK);
                return;
            }

            if (MessageBox.Show("Bạn có chắc chắn muốn xóa hết tất cả sản phẩm khỏi danh sách khuyến mãi", "Cảnh báo", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                try
                {
                    String sql = "DELETE PromotionItem where idPromo = " + idSpinEdit.Text;

                    Program.myReader = Program.ExecSqlDataReader(sql);
                    if (Program.myReader == null) return;
                    else
                        Program.showToastDel();
                    loadDataItem();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Lỗi xóa " + ex.Message, "Error", MessageBoxButtons.OK);
                }
            }
        }

        private void providerComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            providerID.Text = providerComboBox.SelectedValue.ToString();
        }

        private void categoryComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            categoryID.Text = categoryComboBox.SelectedValue.ToString();
        }
    }
}