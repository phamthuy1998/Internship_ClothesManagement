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
        private String tenDevice;
        private int banSaoLuu = 0;
        private String databaseName = "ClothesManament";
        public BackupRestoreForm()
        {
            InitializeComponent();
        }

        private void btnBackup_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            {
                if (tenDevice == null || tenDevice == "")
                {
                    MessageBox.Show("Error with device, device not exist");
                    return;
                }
                String strBackup = "BACKUP DATABASE " + databaseName + " TO " + tenDevice;
                if (cbDelAll.Checked == true)
                    if (MessageBox.Show("Do you sure you want to delete previous backup file?", "Warning", MessageBoxButtons.OKCancel) == DialogResult.OK)
                        strBackup = strBackup + " WITH INIT";
                    else return;
                int err = Program.ExecSqlNonQuery(strBackup, Program.connstr, "");
                if (err != 0)
                {
                    MessageBox.Show("Error connect to database " + databaseName);
                    return;
                }
                cbDelAll.Checked = false;
                //Tải lại các bản sao lưu, gồm cả bản vừa mới sao lưu
                if (LoadCacBanSaoLuu())
                    MessageBox.Show("Backup successful!");
            }
        }
        private bool LoadCacBanSaoLuu()
        {
            try
            {
                this.getBackupTableAdapter.Fill(this.clothesDataSet.GetBackup);
                if (getBackupBindingSource.Count == 0) banSaoLuu = 0;
                else banSaoLuu = int.Parse(((DataRowView)getBackupBindingSource[0])["position"].ToString());
                return true;
            }
            catch (System.Exception ex)
            {
                MessageBox.Show(ex.Message);
                return false;
            }
        }

        private void btnRestore_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (this.getBackupBindingSource.Count == 0)
            {
                MessageBox.Show("Have no backup file yet", "Error", MessageBoxButtons.OK);
                return;
            }
            if (banSaoLuu == 0)
            {
                MessageBox.Show("Please choose one backup file in table to restore", "Error", MessageBoxButtons.OK);
                return;
            }
            if (Program.conn != null && Program.conn.State == ConnectionState.Open)
                Program.conn.Close(); // đóng kết nối

             if (tenDevice == null || tenDevice == "")
                {
                    MessageBox.Show("Error with device, device not exist");
                    return;
                }
            // Phục hồi về thời điểm đã sao lưu
            if (cbThamSoTheoTg.Checked == false)
            {
                String strRestore = "ALTER DATABASE " + databaseName
                    + " SET SINGLE_USER WITH ROLLBACK IMMEDIATE " +
                    " USE tempdb RESTORE DATABASE " + databaseName
                    + " FROM   " + tenDevice + " WITH FILE =  " + banSaoLuu + ", REPLACE "
                    + "ALTER DATABASE  " + databaseName + " SET MULTI_USER";

                if (MessageBox.Show("Are you sure you want to restore  " + databaseName + " to file  " + banSaoLuu + "?"
                  , "Warning", MessageBoxButtons.OKCancel) == DialogResult.OK)
                {
                    int err = Program.ExecSqlNonQuery(strRestore, Program.connstr, "Lỗi phục hồi cơ sở dữ liệu.");
                    if (err == 0)
                    {
                        MessageBox.Show("Restore data successfull", "Congratulatio", MessageBoxButtons.OK);
                    }
                }
            }
            // Backup đên 1 thời gian người dùng nhập
            else
            {
                // Ngày giờ stop at > thời điểm sao lưu và nhỏ hơn thời điểm hiện tại ít nhất 3p\
                // Ngày giờ của bản backup được chọn
                DateTime ngaygioBK = (DateTime)((DataRowView)getBackupBindingSource[getBackupBindingSource.Position])["backup_start_date"];

                // ngày h của user nhập
                String strThoiDiemStopAt = dateStop.DateTime.Year + "-" + dateStop.DateTime.Month + "-" + dateStop.DateTime.Day + " " +
                    timeStop.Time.Hour + ":" + timeStop.Time.Minute + ":" + timeStop.Time.Second;

                DateTime thoiDiemStopAt = DateTime.Now;

                try
                {
                    // format Ngày giờ người dùng nhập
                    thoiDiemStopAt = DateTime.Parse(strThoiDiemStopAt);
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error convert date time: " + ex.Message, "", MessageBoxButtons.OK);
                }

                // Kiểm tra sau thời điểm hiện tại
                if (thoiDiemStopAt > DateTime.Now)
                {
                    MessageBox.Show("The time when you restore must be before current time", "Error", MessageBoxButtons.OK);
                    return;
                }

                //if (dateStop.DateTime.Date <= ngaygioBK.Date
                //    || thoiDiemStopAt.TimeOfDay.Ticks < ngaygioBK.TimeOfDay.Ticks)
                if (DateTime.Compare(thoiDiemStopAt, ngaygioBK) <= 0)
                {
                    MessageBox.Show("The time when you restore must be after the time that you selected", "Error", MessageBoxButtons.OK);
                    return;
                }

                if (MessageBox.Show("Are you sure you want to restore database " + databaseName + " to date " + thoiDiemStopAt + "?"
                    , "Warning!", MessageBoxButtons.OKCancel) == DialogResult.OK)
                {
                    try
                    {
                        // Restore về 1 thời điểm người dùng nhập
                        String strRestore = "ALTER DATABASE " + databaseName + " SET SINGLE_USER WITH ROLLBACK IMMEDIATE \n" +
                               " BACKUP LOG " + databaseName + " TO DISK = '" + Program.defaultPath + "DEVICE_" + databaseName + ".trn' WITH INIT, NORECOVERY; \n " +
                               " USE tempdb \n" +
                               " RESTORE DATABASE " + databaseName + " FROM  DEVICE_" + databaseName + " WITH FILE =" + banSaoLuu + ", NORECOVERY; \n" +
                               " RESTORE DATABASE " + databaseName + " FROM DISK = '" + Program.defaultPath + "DEVICE_" + databaseName + ".trn' " +
                               " WITH STOPAT= '" + thoiDiemStopAt + "' \ndatabaseNameProgram.databaseName" + " SET MULTI_USER ";
                        MessageBox.Show(strRestore);
                        int err = Program.ExecSqlNonQuery(strRestore, Program.connstr, "Lỗi phục hồi cơ sở dữ liệu.");
                        if (err == 0)
                        {
                            MessageBox.Show("Restore database successful", "Noti", MessageBoxButtons.OK);
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Ee Restore:\n" + ex, "Something went wrong ", MessageBoxButtons.OK);
                    }
                }
            }
        }

        private void btnTime_CheckedChanged(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            if (btnTime.Checked == true)
                panelDatetime.Visible = true;
            else panelDatetime.Visible = false;
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

        private void HideCreateDevice()
        {
            btnSaoLuu.Enabled = btnPhucHoi.Enabled = cbThamSoTheoTg.Enabled = btnThoat.Enabled = true;
            btnCreateDevice.Enabled = false;
        }

        private void ShowCreateDevice()
        {
            btnSaoLuu.Enabled = btnPhucHoi.Enabled = cbThamSoTheoTg.Enabled = false;
            btnCreateDevice.Enabled = btnThoat.Enabled = true;
        }

        private void TaoDevice()
        {
            // Lưu trên disk
            String strTaoDevice = "EXEC sp_addumpdevice 'disk', 'DEVICE_" + databaseName + "', " +
                "'" + Program.defaultPath + "DEVICE_" + databaseName + ".bak' ";

            int err = Program.ExecSqlNonQuery(strTaoDevice, Program.connstr, "Create fail");
            if (err == 0)
            {
                // Show message diaglog create device successfull
                MessageBox.Show("Create device successfully!", "Message", MessageBoxButtons.OK);
                HideCreateDevice();
            }
            else
            {
                ShowCreateDevice();
            }
        }

        private void btnExit_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            DialogResult dr = MessageBox.Show("Are you sure you want to exit the program", "Warning", MessageBoxButtons.YesNo);
            if (dr == DialogResult.Yes)
            {
                Application.ExitThread();
            }
        }

        private void BackupRestoreForm_Load(object sender, EventArgs e)
        {
            try
            {
                this.getBackupTableAdapter.Fill(this.clothesDataSet.GetBackup);

                databasesGridControl_Click(sender, e);
                dateStop.DateTime = DateTime.Now.Date;
                timeStop.Time = DateTime.Now;

                // Hide views
                panelDatetime.Visible = cbDelAll.Checked = false;

                // show hide view
                CheckDeviceExist();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message, "Error", MessageBoxButtons.OK);
            }
        }

        private void databasesGridControl_Click(object sender, EventArgs e)
        {

            if (getBackupBindingSource.Count == 0) banSaoLuu = 0;
            else banSaoLuu = int.Parse(((DataRowView)getBackupBindingSource[0])["position"].ToString());

        }

        // Check device exist? 
        private void CheckDeviceExist()
        {
            String strTenDevice = "select  COUNT(name) from  sys.backup_devices WHERE name = N'DEVICE_"
               + databaseName + "'";

            Program.myReader = Program.ExecSqlDataReader(strTenDevice);
            if (Program.myReader == null) return;

            Program.myReader.Read();

            //Nếu đã có device, hiện các button khác, ẩn button tạo device đi
            if (Program.myReader.GetInt32(0) > 0)
            {
                HideCreateDevice();
                tenDevice = "DEVICE_" + databaseName;
            }
            //Nếu chưa có device, ẩn các button khác, chỉ hiện button device và button thoát
            else
                ShowCreateDevice();
            Program.myReader.Close();

        }

        private void btnXoaBackup_Click_1(object sender, EventArgs e)
        {
            if (getBackupBindingSource.Count == 0)
            {
                MessageBox.Show("Don't have any backup file to delete!", "Warning", MessageBoxButtons.OK);
                return;
            }

            if (MessageBox.Show("Are you sure you want to delete backup " + ((DataRowView)this.getBackupBindingSource.Current).Row["name"].ToString() + "?", "", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                try
                {

                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error when delete backup file " + ex.Message, "", MessageBoxButtons.OK);
                }
            }
        }
    }
}