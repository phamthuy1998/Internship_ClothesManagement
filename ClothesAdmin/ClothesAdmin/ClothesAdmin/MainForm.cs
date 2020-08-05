using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevExpress.XtraBars;

namespace ClothesAdmin
{
    public partial class MainForm : DevExpress.XtraBars.Ribbon.RibbonForm
    {
        private Form form;
        private ProductForm productForm = null;
        private CategoryForm categoryForm = null;
        private AccountsForm accountsForm = null;
        public MainForm()
        {
            InitializeComponent();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {

        }

        private void btnProduct_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(ProductForm));
            if (form == null)
            {
                IsMdiContainer = true;
                productForm = new ProductForm();
                productForm.MdiParent = this;
                productForm.Show();
            }
            else form.Activate();
        }

        private Form CheckExists(Type ftype)
        {
            foreach (Form f in this.MdiChildren)
                if (f.GetType() == ftype)
                    return f;
            return null;
        }

        private void backstageViewClientControl1_Load(object sender, EventArgs e)
        {

        }

        private void btnCategory_ItemClick(object sender, ItemClickEventArgs e)
        {
            form = this.CheckExists(typeof(CategoryForm));
            if (form == null)
            {
                IsMdiContainer = true;
                categoryForm = new CategoryForm();
                categoryForm.MdiParent = this;
                categoryForm.Show();
            }
            else form.Activate();
        }
    }
}