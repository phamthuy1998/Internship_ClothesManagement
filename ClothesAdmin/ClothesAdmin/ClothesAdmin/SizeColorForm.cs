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
    public partial class SizeColorForm : DevExpress.XtraEditors.XtraForm
    {
        public SizeColorForm()
        {
            InitializeComponent();
        }

        private void productSizeColorBindingNavigatorSaveItem_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.productSizeColorBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);

        }

        private void SizeColorForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.Fill(this.clothesDataSet.ProductSizeColor);
            getData();
        }

        private void getData()
        {
            int categoryId;
            bool parseCategoryOK = Int32.TryParse(cbbCategory.SelectedValue.ToString(), out categoryId);
            int providerId;
            bool parseProviderOK = Int32.TryParse(cbbProvider.SelectedValue.ToString(), out providerId);
            if (parseCategoryOK && parseProviderOK)
                this.sP_GetProductColorSizeTableAdapter.Fill(this.clothesDataSet.SP_GetProductColorSize, categoryId, providerId);

        }

        private void cbbCategory_SelectedIndexChanged(object sender, EventArgs e)
        {
            getData();
        }

        private void cbbProvider_SelectedIndexChanged(object sender, EventArgs e)
        {
            getData();
        }

        private void btnAddProvider_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            sP_GetProductColorSizeBindingSource.AddNew();
        }

        private void btnSaveAddProvider_Click(object sender, EventArgs e)
        {
            this.Validate();
            this.sP_GetProductColorSizeBindingSource.EndEdit();
            this.tableAdapterManager.UpdateAll(this.clothesDataSet);
        }

        private void btnCloseForm_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }
    }
}