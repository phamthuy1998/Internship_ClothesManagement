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
            // TODO: This line of code loads data into the 'clothesDataSet.Color' table. You can move, or remove it, as needed.
            this.colorTableAdapter.Fill(this.clothesDataSet.Color);
            // TODO: This line of code loads data into the 'clothesDataSet.Size' table. You can move, or remove it, as needed.
            this.sizeTableAdapter.Fill(this.clothesDataSet.Size);
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            this.productTableAdapter.Fill(this.clothesDataSet.Product);
            // TODO: This line of code loads data into the 'clothesDataSet.Product' table. You can move, or remove it, as needed.
            // TODO: This line of code loads data into the 'clothesDataSet.Provider' table. You can move, or remove it, as needed.
            this.providerTableAdapter.Fill(this.clothesDataSet.Provider);
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            // TODO: This line of code loads data into the 'clothesDataSet.ProductSizeColor' table. You can move, or remove it, as needed.
            this.productSizeColorTableAdapter.Fill(this.clothesDataSet.ProductSizeColor);
        }


    }
}