using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClothesAdmin
{
    public partial class CategoryForm : DevExpress.XtraEditors.XtraForm
    {
        public CategoryForm()
        {
            InitializeComponent();
        }

        private void CategoryForm_Load(object sender, EventArgs e)
        {
            using(ClothesEntities db  = new ClothesEntities())
            {
                categoryBindingSource.DataSource = db.Categories.ToList();
            }
        }
    }
}
