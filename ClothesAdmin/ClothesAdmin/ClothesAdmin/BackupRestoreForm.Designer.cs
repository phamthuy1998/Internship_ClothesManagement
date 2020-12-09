namespace ClothesAdmin
{
    partial class BackupRestoreForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(BackupRestoreForm));
            this.barManager1 = new DevExpress.XtraBars.BarManager(this.components);
            this.barDockControlTop = new DevExpress.XtraBars.BarDockControl();
            this.barDockControlBottom = new DevExpress.XtraBars.BarDockControl();
            this.barDockControlLeft = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl1 = new DevExpress.XtraBars.BarDockControl();
            this.btnSaoLuu = new DevExpress.XtraBars.BarButtonItem();
            this.btnPhucHoi = new DevExpress.XtraBars.BarButtonItem();
            this.cbThamSoTheoTg = new DevExpress.XtraBars.BarCheckItem();
            this.btnTaoDeviceSaoLuu = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControlRight = new DevExpress.XtraBars.BarDockControl();
            this.btnThoat = new DevExpress.XtraBars.BarButtonItem();
            this.btnDangXuat = new DevExpress.XtraBars.BarButtonItem();
            this.barManager2 = new DevExpress.XtraBars.BarManager(this.components);
            this.bar1 = new DevExpress.XtraBars.Bar();
            this.btnBackup = new DevExpress.XtraBars.BarButtonItem();
            this.btnRestore = new DevExpress.XtraBars.BarButtonItem();
            this.btnTime = new DevExpress.XtraBars.BarCheckItem();
            this.btnCreateDevice = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControl2 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl3 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl4 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl5 = new DevExpress.XtraBars.BarDockControl();
            this.btnGhiBD = new DevExpress.XtraBars.BarButtonItem();
            this.btnXoaBD = new DevExpress.XtraBars.BarButtonItem();
            this.btnSuaBD = new DevExpress.XtraBars.BarButtonItem();
            this.btnThamSoThoiGian = new DevExpress.XtraBars.BarButtonItem();
            this.btnInDSBD = new DevExpress.XtraBars.BarButtonItem();
            this.barToggleSwitchItem1 = new DevExpress.XtraBars.BarToggleSwitchItem();
            this.barCheckItem2 = new DevExpress.XtraBars.BarCheckItem();
            this.barCheckItem3 = new DevExpress.XtraBars.BarCheckItem();
            this.barButtonItem6 = new DevExpress.XtraBars.BarButtonItem();
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.getBackupBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.getBackupTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.GetBackupTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.ctxMenuBackup = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.btnXoaBackup = new System.Windows.Forms.ToolStripMenuItem();
            this.panel1 = new System.Windows.Forms.Panel();
            this.cbDelAll = new System.Windows.Forms.CheckBox();
            this.panelDatetime = new System.Windows.Forms.Panel();
            this.timeStop = new DevExpress.XtraEditors.TimeEdit();
            this.richTextBox1 = new System.Windows.Forms.RichTextBox();
            this.dateStop = new DevExpress.XtraEditors.DateEdit();
            this.label2 = new System.Windows.Forms.Label();
            this.getBackupGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colposition = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colname = new DevExpress.XtraGrid.Columns.GridColumn();
            this.Date = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coluser_name = new DevExpress.XtraGrid.Columns.GridColumn();
            ((System.ComponentModel.ISupportInitialize)(this.barManager1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.getBackupBindingSource)).BeginInit();
            this.ctxMenuBackup.SuspendLayout();
            this.panel1.SuspendLayout();
            this.panelDatetime.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.timeStop.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateStop.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateStop.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.getBackupGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // barManager1
            // 
            this.barManager1.DockControls.Add(this.barDockControlTop);
            this.barManager1.DockControls.Add(this.barDockControlBottom);
            this.barManager1.DockControls.Add(this.barDockControlLeft);
            this.barManager1.DockControls.Add(this.barDockControl1);
            this.barManager1.Form = this;
            this.barManager1.Items.AddRange(new DevExpress.XtraBars.BarItem[] {
            this.btnSaoLuu,
            this.btnPhucHoi,
            this.cbThamSoTheoTg,
            this.btnTaoDeviceSaoLuu});
            // 
            // barDockControlTop
            // 
            this.barDockControlTop.CausesValidation = false;
            this.barDockControlTop.Dock = System.Windows.Forms.DockStyle.Top;
            this.barDockControlTop.Location = new System.Drawing.Point(0, 50);
            this.barDockControlTop.Manager = this.barManager1;
            this.barDockControlTop.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControlTop.Size = new System.Drawing.Size(1188, 0);
            // 
            // barDockControlBottom
            // 
            this.barDockControlBottom.CausesValidation = false;
            this.barDockControlBottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControlBottom.Location = new System.Drawing.Point(0, 644);
            this.barDockControlBottom.Manager = this.barManager1;
            this.barDockControlBottom.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControlBottom.Size = new System.Drawing.Size(1188, 0);
            // 
            // barDockControlLeft
            // 
            this.barDockControlLeft.CausesValidation = false;
            this.barDockControlLeft.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControlLeft.Location = new System.Drawing.Point(0, 50);
            this.barDockControlLeft.Manager = this.barManager1;
            this.barDockControlLeft.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControlLeft.Size = new System.Drawing.Size(0, 594);
            // 
            // barDockControl1
            // 
            this.barDockControl1.CausesValidation = false;
            this.barDockControl1.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl1.Location = new System.Drawing.Point(1188, 50);
            this.barDockControl1.Manager = this.barManager1;
            this.barDockControl1.Size = new System.Drawing.Size(0, 594);
            // 
            // btnSaoLuu
            // 
            this.btnSaoLuu.Caption = "Sao lưu";
            this.btnSaoLuu.Id = 0;
            this.btnSaoLuu.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnSaoLuu.ImageOptions.Image")));
            this.btnSaoLuu.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnSaoLuu.ImageOptions.LargeImage")));
            this.btnSaoLuu.Name = "btnSaoLuu";
            // 
            // btnPhucHoi
            // 
            this.btnPhucHoi.Caption = "Phục hồi";
            this.btnPhucHoi.Id = 4;
            this.btnPhucHoi.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnPhucHoi.ImageOptions.Image")));
            this.btnPhucHoi.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnPhucHoi.ImageOptions.LargeImage")));
            this.btnPhucHoi.Name = "btnPhucHoi";
            // 
            // cbThamSoTheoTg
            // 
            this.cbThamSoTheoTg.Caption = "Tham số theo thời gian";
            this.cbThamSoTheoTg.CheckBoxVisibility = DevExpress.XtraBars.CheckBoxVisibility.BeforeText;
            this.cbThamSoTheoTg.Id = 16;
            this.cbThamSoTheoTg.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("cbThamSoTheoTg.ImageOptions.Image")));
            this.cbThamSoTheoTg.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("cbThamSoTheoTg.ImageOptions.LargeImage")));
            this.cbThamSoTheoTg.Name = "cbThamSoTheoTg";
            // 
            // btnTaoDeviceSaoLuu
            // 
            this.btnTaoDeviceSaoLuu.Caption = "Tạo device sao lưu";
            this.btnTaoDeviceSaoLuu.Id = 8;
            this.btnTaoDeviceSaoLuu.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnTaoDeviceSaoLuu.ImageOptions.Image")));
            this.btnTaoDeviceSaoLuu.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnTaoDeviceSaoLuu.ImageOptions.LargeImage")));
            this.btnTaoDeviceSaoLuu.Name = "btnTaoDeviceSaoLuu";
            // 
            // barDockControlRight
            // 
            this.barDockControlRight.CausesValidation = false;
            this.barDockControlRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControlRight.Location = new System.Drawing.Point(1188, 50);
            this.barDockControlRight.Manager = this.barManager1;
            this.barDockControlRight.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControlRight.Size = new System.Drawing.Size(0, 594);
            // 
            // btnThoat
            // 
            this.btnThoat.Caption = "Thoát";
            this.btnThoat.Id = 7;
            this.btnThoat.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnThoat.ImageOptions.Image")));
            this.btnThoat.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnThoat.ImageOptions.LargeImage")));
            this.btnThoat.Name = "btnThoat";
            // 
            // btnDangXuat
            // 
            this.btnDangXuat.Caption = "Đăng xuất";
            this.btnDangXuat.Id = 17;
            this.btnDangXuat.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnDangXuat.ImageOptions.Image")));
            this.btnDangXuat.Name = "btnDangXuat";
            // 
            // barManager2
            // 
            this.barManager2.Bars.AddRange(new DevExpress.XtraBars.Bar[] {
            this.bar1});
            this.barManager2.DockControls.Add(this.barDockControl2);
            this.barManager2.DockControls.Add(this.barDockControl3);
            this.barManager2.DockControls.Add(this.barDockControl4);
            this.barManager2.DockControls.Add(this.barDockControl5);
            this.barManager2.Form = this;
            this.barManager2.Items.AddRange(new DevExpress.XtraBars.BarItem[] {
            this.btnBackup,
            this.btnGhiBD,
            this.btnXoaBD,
            this.btnSuaBD,
            this.btnRestore,
            this.btnThamSoThoiGian,
            this.btnInDSBD,
            this.btnCreateDevice,
            this.barToggleSwitchItem1,
            this.barCheckItem2,
            this.barCheckItem3,
            this.barButtonItem6,
            this.btnTime});
            this.barManager2.MainMenu = this.bar1;
            this.barManager2.MaxItemId = 18;
            // 
            // bar1
            // 
            this.bar1.BarName = "Main menu";
            this.bar1.DockCol = 0;
            this.bar1.DockRow = 0;
            this.bar1.DockStyle = DevExpress.XtraBars.BarDockStyle.Top;
            this.bar1.LinksPersistInfo.AddRange(new DevExpress.XtraBars.LinkPersistInfo[] {
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnBackup, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnRestore, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnTime, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnCreateDevice, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph)});
            this.bar1.OptionsBar.MultiLine = true;
            this.bar1.OptionsBar.UseWholeRow = true;
            this.bar1.Text = "Main menu";
            // 
            // btnBackup
            // 
            this.btnBackup.Caption = "Backup";
            this.btnBackup.Id = 0;
            this.btnBackup.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnBackup.ImageOptions.Image")));
            this.btnBackup.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnBackup.ImageOptions.LargeImage")));
            this.btnBackup.Name = "btnBackup";
            this.btnBackup.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnBackup_ItemClick);
            // 
            // btnRestore
            // 
            this.btnRestore.Caption = "Restore";
            this.btnRestore.Id = 4;
            this.btnRestore.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnRestore.ImageOptions.Image")));
            this.btnRestore.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnRestore.ImageOptions.LargeImage")));
            this.btnRestore.Name = "btnRestore";
            this.btnRestore.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnRestore_ItemClick);
            // 
            // btnTime
            // 
            this.btnTime.Caption = "Timestamp";
            this.btnTime.CheckBoxVisibility = DevExpress.XtraBars.CheckBoxVisibility.BeforeText;
            this.btnTime.Id = 16;
            this.btnTime.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnTime.ImageOptions.Image")));
            this.btnTime.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnTime.ImageOptions.LargeImage")));
            this.btnTime.Name = "btnTime";
            this.btnTime.CheckedChanged += new DevExpress.XtraBars.ItemClickEventHandler(this.btnTime_CheckedChanged);
            // 
            // btnCreateDevice
            // 
            this.btnCreateDevice.Caption = "Create device";
            this.btnCreateDevice.Id = 8;
            this.btnCreateDevice.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnCreateDevice.ImageOptions.Image")));
            this.btnCreateDevice.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnCreateDevice.ImageOptions.LargeImage")));
            this.btnCreateDevice.Name = "btnCreateDevice";
            this.btnCreateDevice.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnCreateDevice_ItemClick);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Top;
            this.barDockControl2.Location = new System.Drawing.Point(0, 0);
            this.barDockControl2.Manager = this.barManager2;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControl2.Size = new System.Drawing.Size(1188, 50);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl3.Location = new System.Drawing.Point(0, 644);
            this.barDockControl3.Manager = this.barManager2;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControl3.Size = new System.Drawing.Size(1188, 0);
            // 
            // barDockControl4
            // 
            this.barDockControl4.CausesValidation = false;
            this.barDockControl4.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl4.Location = new System.Drawing.Point(0, 50);
            this.barDockControl4.Manager = this.barManager2;
            this.barDockControl4.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControl4.Size = new System.Drawing.Size(0, 594);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1188, 50);
            this.barDockControl5.Manager = this.barManager2;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(1);
            this.barDockControl5.Size = new System.Drawing.Size(0, 594);
            // 
            // btnGhiBD
            // 
            this.btnGhiBD.Caption = "Ghi";
            this.btnGhiBD.Id = 1;
            this.btnGhiBD.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnGhiBD.ImageOptions.Image")));
            this.btnGhiBD.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnGhiBD.ImageOptions.LargeImage")));
            this.btnGhiBD.Name = "btnGhiBD";
            // 
            // btnXoaBD
            // 
            this.btnXoaBD.Caption = "Xóa";
            this.btnXoaBD.Id = 2;
            this.btnXoaBD.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnXoaBD.ImageOptions.Image")));
            this.btnXoaBD.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnXoaBD.ImageOptions.LargeImage")));
            this.btnXoaBD.Name = "btnXoaBD";
            // 
            // btnSuaBD
            // 
            this.btnSuaBD.Caption = "Sửa";
            this.btnSuaBD.Id = 3;
            this.btnSuaBD.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnSuaBD.ImageOptions.Image")));
            this.btnSuaBD.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnSuaBD.ImageOptions.LargeImage")));
            this.btnSuaBD.Name = "btnSuaBD";
            // 
            // btnThamSoThoiGian
            // 
            this.btnThamSoThoiGian.Caption = "Tham số theo thời gian";
            this.btnThamSoThoiGian.Id = 5;
            this.btnThamSoThoiGian.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnThamSoThoiGian.ImageOptions.Image")));
            this.btnThamSoThoiGian.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnThamSoThoiGian.ImageOptions.LargeImage")));
            this.btnThamSoThoiGian.Name = "btnThamSoThoiGian";
            // 
            // btnInDSBD
            // 
            this.btnInDSBD.Caption = "In danh sách";
            this.btnInDSBD.Id = 6;
            this.btnInDSBD.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnInDSBD.ImageOptions.Image")));
            this.btnInDSBD.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnInDSBD.ImageOptions.LargeImage")));
            this.btnInDSBD.Name = "btnInDSBD";
            // 
            // barToggleSwitchItem1
            // 
            this.barToggleSwitchItem1.Caption = "cb";
            this.barToggleSwitchItem1.Id = 11;
            this.barToggleSwitchItem1.Name = "barToggleSwitchItem1";
            // 
            // barCheckItem2
            // 
            this.barCheckItem2.Caption = "cb";
            this.barCheckItem2.Id = 12;
            this.barCheckItem2.Name = "barCheckItem2";
            // 
            // barCheckItem3
            // 
            this.barCheckItem3.Caption = "barCheckItem2";
            this.barCheckItem3.Id = 13;
            this.barCheckItem3.Name = "barCheckItem3";
            // 
            // barButtonItem6
            // 
            this.barButtonItem6.Caption = "barButtonItem1";
            this.barButtonItem6.Id = 15;
            this.barButtonItem6.Name = "barButtonItem6";
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // getBackupBindingSource
            // 
            this.getBackupBindingSource.DataMember = "GetBackup";
            this.getBackupBindingSource.DataSource = this.clothesDataSet;
            // 
            // getBackupTableAdapter
            // 
            this.getBackupTableAdapter.ClearBeforeFill = true;
            // 
            // tableAdapterManager
            // 
            this.tableAdapterManager.AccountTableAdapter = null;
            this.tableAdapterManager.AddressTableAdapter = null;
            this.tableAdapterManager.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager.CategoryTableAdapter = null;
            this.tableAdapterManager.ColorTableAdapter = null;
            this.tableAdapterManager.Connection = null;
            this.tableAdapterManager.CustomerTableAdapter = null;
            this.tableAdapterManager.EmployeeTableAdapter = null;
            this.tableAdapterManager.FavoriteProductTableAdapter = null;
            this.tableAdapterManager.ImageTableAdapter = null;
            this.tableAdapterManager.ImportCouponDetailTableAdapter = null;
            this.tableAdapterManager.ImportCouponTableAdapter = null;
            this.tableAdapterManager.InvoiceItemTableAdapter = null;
            this.tableAdapterManager.InvoiceStatusTableAdapter = null;
            this.tableAdapterManager.InvoiceTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = null;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.QuestionTableAdapter = null;
            this.tableAdapterManager.RatingTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.ShopInfoTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // ctxMenuBackup
            // 
            this.ctxMenuBackup.ImageScalingSize = new System.Drawing.Size(40, 40);
            this.ctxMenuBackup.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.btnXoaBackup});
            this.ctxMenuBackup.Name = "ctxMenuBackup";
            this.ctxMenuBackup.Size = new System.Drawing.Size(102, 28);
            // 
            // btnXoaBackup
            // 
            this.btnXoaBackup.Name = "btnXoaBackup";
            this.btnXoaBackup.Size = new System.Drawing.Size(101, 24);
            this.btnXoaBackup.Text = "Del";
            this.btnXoaBackup.Click += new System.EventHandler(this.btnXoaBackup_Click_1);
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.cbDelAll);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 50);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1188, 69);
            this.panel1.TabIndex = 42;
            // 
            // cbDelAll
            // 
            this.cbDelAll.AutoSize = true;
            this.cbDelAll.Location = new System.Drawing.Point(53, 24);
            this.cbDelAll.Margin = new System.Windows.Forms.Padding(1);
            this.cbDelAll.Name = "cbDelAll";
            this.cbDelAll.Size = new System.Drawing.Size(406, 21);
            this.cbDelAll.TabIndex = 28;
            this.cbDelAll.Text = "Delete all backups in the file before backing up the new one";
            this.cbDelAll.UseVisualStyleBackColor = true;
            // 
            // panelDatetime
            // 
            this.panelDatetime.Controls.Add(this.timeStop);
            this.panelDatetime.Controls.Add(this.richTextBox1);
            this.panelDatetime.Controls.Add(this.dateStop);
            this.panelDatetime.Controls.Add(this.label2);
            this.panelDatetime.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelDatetime.Location = new System.Drawing.Point(0, 119);
            this.panelDatetime.Name = "panelDatetime";
            this.panelDatetime.Size = new System.Drawing.Size(1188, 137);
            this.panelDatetime.TabIndex = 43;
            // 
            // timeStop
            // 
            this.timeStop.EditValue = new System.DateTime(2020, 4, 23, 0, 0, 0, 0);
            this.timeStop.Location = new System.Drawing.Point(600, 11);
            this.timeStop.Margin = new System.Windows.Forms.Padding(1);
            this.timeStop.Name = "timeStop";
            this.timeStop.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.timeStop.Size = new System.Drawing.Size(117, 24);
            this.timeStop.TabIndex = 11;
            // 
            // richTextBox1
            // 
            this.richTextBox1.Location = new System.Drawing.Point(163, 53);
            this.richTextBox1.Margin = new System.Windows.Forms.Padding(1);
            this.richTextBox1.Name = "richTextBox1";
            this.richTextBox1.Size = new System.Drawing.Size(555, 61);
            this.richTextBox1.TabIndex = 2;
            this.richTextBox1.Text = resources.GetString("richTextBox1.Text");
            // 
            // dateStop
            // 
            this.dateStop.EditValue = null;
            this.dateStop.Location = new System.Drawing.Point(405, 13);
            this.dateStop.Margin = new System.Windows.Forms.Padding(1);
            this.dateStop.Name = "dateStop";
            this.dateStop.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateStop.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateStop.Size = new System.Drawing.Size(163, 22);
            this.dateStop.TabIndex = 10;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(161, 15);
            this.label2.Margin = new System.Windows.Forms.Padding(1, 0, 1, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(176, 17);
            this.label2.TabIndex = 0;
            this.label2.Text = "Date and time for recovery";
            // 
            // getBackupGridControl
            // 
            this.getBackupGridControl.DataSource = this.getBackupBindingSource;
            this.getBackupGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.getBackupGridControl.Location = new System.Drawing.Point(0, 256);
            this.getBackupGridControl.MainView = this.gridView1;
            this.getBackupGridControl.Name = "getBackupGridControl";
            this.getBackupGridControl.Size = new System.Drawing.Size(1188, 388);
            this.getBackupGridControl.TabIndex = 44;
            this.getBackupGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colposition,
            this.colname,
            this.Date,
            this.coluser_name});
            this.gridView1.GridControl = this.getBackupGridControl;
            this.gridView1.GroupPanelText = "Backup detail";
            this.gridView1.Name = "gridView1";
            // 
            // colposition
            // 
            this.colposition.FieldName = "position";
            this.colposition.MinWidth = 25;
            this.colposition.Name = "colposition";
            this.colposition.Visible = true;
            this.colposition.VisibleIndex = 0;
            this.colposition.Width = 68;
            // 
            // colname
            // 
            this.colname.Caption = "Description";
            this.colname.FieldName = "name";
            this.colname.MinWidth = 25;
            this.colname.Name = "colname";
            this.colname.Visible = true;
            this.colname.VisibleIndex = 1;
            this.colname.Width = 334;
            // 
            // Date
            // 
            this.Date.Caption = "Backup time";
            this.Date.FieldName = "backup_start_date";
            this.Date.MinWidth = 25;
            this.Date.Name = "Date";
            this.Date.Visible = true;
            this.Date.VisibleIndex = 2;
            this.Date.Width = 334;
            // 
            // coluser_name
            // 
            this.coluser_name.Caption = "User backup";
            this.coluser_name.FieldName = "user_name";
            this.coluser_name.MinWidth = 25;
            this.coluser_name.Name = "coluser_name";
            this.coluser_name.Visible = true;
            this.coluser_name.VisibleIndex = 3;
            this.coluser_name.Width = 339;
            // 
            // BackupRestoreForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1188, 644);
            this.Controls.Add(this.getBackupGridControl);
            this.Controls.Add(this.panelDatetime);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControlRight);
            this.Controls.Add(this.barDockControlLeft);
            this.Controls.Add(this.barDockControl1);
            this.Controls.Add(this.barDockControlBottom);
            this.Controls.Add(this.barDockControlTop);
            this.Controls.Add(this.barDockControl4);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl2);
            this.Name = "BackupRestoreForm";
            this.Text = "Backup and Restore";
            this.Load += new System.EventHandler(this.BackupRestoreForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.getBackupBindingSource)).EndInit();
            this.ctxMenuBackup.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.panelDatetime.ResumeLayout(false);
            this.panelDatetime.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.timeStop.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateStop.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateStop.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.getBackupGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraBars.BarManager barManager1;
        private DevExpress.XtraBars.BarButtonItem btnSaoLuu;
        private DevExpress.XtraBars.BarButtonItem btnPhucHoi;
        private DevExpress.XtraBars.BarCheckItem cbThamSoTheoTg;
        private DevExpress.XtraBars.BarButtonItem btnTaoDeviceSaoLuu;
        private DevExpress.XtraBars.BarDockControl barDockControlTop;
        private DevExpress.XtraBars.BarDockControl barDockControlBottom;
        private DevExpress.XtraBars.BarDockControl barDockControlLeft;
        private DevExpress.XtraBars.BarDockControl barDockControl1;
        private DevExpress.XtraBars.BarDockControl barDockControlRight;
        private DevExpress.XtraBars.BarDockControl barDockControl4;
        private DevExpress.XtraBars.BarManager barManager2;
        private DevExpress.XtraBars.Bar bar1;
        private DevExpress.XtraBars.BarButtonItem btnBackup;
        private DevExpress.XtraBars.BarButtonItem btnRestore;
        private DevExpress.XtraBars.BarCheckItem btnTime;
        private DevExpress.XtraBars.BarButtonItem btnCreateDevice;
        private DevExpress.XtraBars.BarDockControl barDockControl2;
        private DevExpress.XtraBars.BarDockControl barDockControl3;
        private DevExpress.XtraBars.BarDockControl barDockControl5;
        private DevExpress.XtraBars.BarButtonItem btnGhiBD;
        private DevExpress.XtraBars.BarButtonItem btnXoaBD;
        private DevExpress.XtraBars.BarButtonItem btnSuaBD;
        private DevExpress.XtraBars.BarButtonItem btnThamSoThoiGian;
        private DevExpress.XtraBars.BarButtonItem btnInDSBD;
        private DevExpress.XtraBars.BarToggleSwitchItem barToggleSwitchItem1;
        private DevExpress.XtraBars.BarCheckItem barCheckItem2;
        private DevExpress.XtraBars.BarCheckItem barCheckItem3;
        private DevExpress.XtraBars.BarButtonItem barButtonItem6;
        private DevExpress.XtraBars.BarButtonItem btnThoat;
        private DevExpress.XtraBars.BarButtonItem btnDangXuat;
        private System.Windows.Forms.BindingSource getBackupBindingSource;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.GetBackupTableAdapter getBackupTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private System.Windows.Forms.ContextMenuStrip ctxMenuBackup;
        private System.Windows.Forms.ToolStripMenuItem btnXoaBackup;
        private DevExpress.XtraGrid.GridControl getBackupGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colposition;
        private DevExpress.XtraGrid.Columns.GridColumn colname;
        private DevExpress.XtraGrid.Columns.GridColumn Date;
        private DevExpress.XtraGrid.Columns.GridColumn coluser_name;
        private System.Windows.Forms.Panel panelDatetime;
        private DevExpress.XtraEditors.TimeEdit timeStop;
        private System.Windows.Forms.RichTextBox richTextBox1;
        private DevExpress.XtraEditors.DateEdit dateStop;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.CheckBox cbDelAll;
    }
}