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
using System.IO;

namespace ClothesAdmin
{
    public partial class BackupRestoreForm : DevExpress.XtraEditors.XtraForm
    {
        public BackupRestoreForm()
        {
            InitializeComponent();
        }

        private void btnBackup_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void btnRestore_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void btnTime_CheckedChanged(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void btnCreateDevice_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

            //Check folder default
            // if exist 
            if (Directory.Exists(Program.defaultPath) == true)
            {
                TaoDevice();
            }
            // If doesn't exist --> allow user choose a path to create device
            else
            {
                OpenFileDialog folderBrowser = new OpenFileDialog();
                // Set validate names and check file exists to false otherwise windows will
                // not let you select "Folder Selection."
                folderBrowser.ValidateNames = false;
                folderBrowser.CheckFileExists = false;
                folderBrowser.CheckPathExists = true;
                // Always default to Folder Selection.
                folderBrowser.FileName = "Folder Selection.";
                if (folderBrowser.ShowDialog() == DialogResult.OK)
                {
                    string folderPath = Path.GetDirectoryName(folderBrowser.FileName);
                    Program.defaultPath = folderPath + "\\";
                    TaoDevice();
                }

            }
        }

        private void TaoDevice()
        {
            // Lưu trên disk
            String strTaoDevice = "EXEC sp_addumpdevice 'disk', 'DEVICE_" + "ClothesManament" + "', " +
                "'" + Program.defaultPath + "DEVICE_" + "ClothesManament" + ".bak' ";

            int err = Program.ExecSqlNonQuery(strTaoDevice, Program.connstr, "Lỗi tạo device.");
            if (err == 0)
            {
                progress.Visible = true;
                for (int i = progress.Minimum; i <= this.progress.Maximum; i++)
                    progress.Value = i;
                progress.Visible = false;

                // Show message diaglog create device successfull
                MessageBox.Show("Tạo device thành công!", "Thông báo", MessageBoxButtons.OK);
                HideCreateDevice();
            }
            else
            {
                ShowCreateDevice();
                progress.Visible = false; return;
            }
        }

        private void btnExit_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {

        }

        private void BackupRestoreForm_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'clothesDataSet.GetBackup' table. You can move, or remove it, as needed.
            this.getBackupTableAdapter.Fill(this.clothesDataSet.GetBackup);

        }
    }
}