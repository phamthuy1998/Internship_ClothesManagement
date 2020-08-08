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
            // TODO: This line of code loads data into the 'clothesDataSet.Category' table. You can move, or remove it, as needed.
            this.categoryTableAdapter.Fill(this.clothesDataSet.Category);
            setIcon();
            setThumbnailImage();
        }

        private void btnAdd_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void setIcon()
        {
            if (String.IsNullOrEmpty(imageUrlTextEdit.Text))
            {
                picImageIcon.Image = Properties.Resources.no_image;
            }
            else
            {
                picImageIcon.ImageLocation = imageUrlTextEdit.Text;
            }
        }

        private void setThumbnailImage()
        {
            if (String.IsNullOrEmpty(thumnailTextEdit.Text))
            {
                picThumbnail.Image = Properties.Resources.no_image;
            }
            else
            {
                picThumbnail.ImageLocation = thumnailTextEdit.Text;
            }
        }

        private void imageUrlTextEdit_EditValueChanged(object sender, EventArgs e)
        {
            setIcon();
        }

        private void thumnailTextEdit_EditValueChanged(object sender, EventArgs e)
        {
            setThumbnailImage();
        }
    }
}
