namespace ClothesAdmin
{
    partial class EmployeeForm
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
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label idLabel;
            System.Windows.Forms.Label firstNameLabel;
            System.Windows.Forms.Label lastNameLabel;
            System.Windows.Forms.Label phoneLabel;
            System.Windows.Forms.Label addressLabel;
            System.Windows.Forms.Label birthdayLabel;
            System.Windows.Forms.Label avatarLabel;
            System.Windows.Forms.Label dateBeginLabel;
            System.Windows.Forms.Label dateEndLabel;
            System.Windows.Forms.Label isWorkingLabel;
            System.Windows.Forms.Label idAccountLabel;
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label idLabel1;
            System.Windows.Forms.Label emailLabel;
            System.Windows.Forms.Label roleIdLabel;
            System.Windows.Forms.Label passwordLabel;
            System.Windows.Forms.Label usernameLabel;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(EmployeeForm));
            this.barManager = new DevExpress.XtraBars.BarManager(this.components);
            this.bar2 = new DevExpress.XtraBars.Bar();
            this.btnAddProvider = new DevExpress.XtraBars.BarButtonItem();
            this.btnDelProvider = new DevExpress.XtraBars.BarButtonItem();
            this.btnReloadProvider = new DevExpress.XtraBars.BarButtonItem();
            this.btnCloseForm = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControl1 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl2 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl3 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl5 = new DevExpress.XtraBars.BarDockControl();
            this.btnSua = new DevExpress.XtraBars.BarButtonItem();
            this.btnTimKiem = new DevExpress.XtraBars.BarButtonItem();
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.sP_GetAllEmployeeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sP_GetAllEmployeeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_GetAllEmployeeTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.panel3 = new System.Windows.Forms.Panel();
            this.sP_GetAllEmployeeGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.collastName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colfirstName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colphone = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coladdress = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colbirthday = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colavatar = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldateBegin = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldateEnd = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colisWorking = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colidAccount = new DevExpress.XtraGrid.Columns.GridColumn();
            this.panel1 = new System.Windows.Forms.Panel();
            this.btnSearchEmployee = new System.Windows.Forms.Button();
            this.edtSearch = new System.Windows.Forms.TextBox();
            this.panel2 = new System.Windows.Forms.Panel();
            this.idSpinEdit1 = new DevExpress.XtraEditors.SpinEdit();
            this.sP_GetAccEmployeeInfoBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.emailTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.roleIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.passwordTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.usernameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.panel4 = new System.Windows.Forms.Panel();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.picAvater = new System.Windows.Forms.PictureBox();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.firstNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.lastNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.phoneTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.addressTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.birthdayDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.avatarTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.dateBeginDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.dateEndDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.isWorkingSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.idAccountSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.accountTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.AccountTableAdapter();
            this.sP_GetAccEmployeeInfoTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_GetAccEmployeeInfoTableAdapter();
            label1 = new System.Windows.Forms.Label();
            idLabel = new System.Windows.Forms.Label();
            firstNameLabel = new System.Windows.Forms.Label();
            lastNameLabel = new System.Windows.Forms.Label();
            phoneLabel = new System.Windows.Forms.Label();
            addressLabel = new System.Windows.Forms.Label();
            birthdayLabel = new System.Windows.Forms.Label();
            avatarLabel = new System.Windows.Forms.Label();
            dateBeginLabel = new System.Windows.Forms.Label();
            dateEndLabel = new System.Windows.Forms.Label();
            isWorkingLabel = new System.Windows.Forms.Label();
            idAccountLabel = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            idLabel1 = new System.Windows.Forms.Label();
            emailLabel = new System.Windows.Forms.Label();
            roleIdLabel = new System.Windows.Forms.Label();
            passwordLabel = new System.Windows.Forms.Label();
            usernameLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetAllEmployeeBindingSource)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            this.panel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetAllEmployeeGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.panel1.SuspendLayout();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit1.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetAccEmployeeInfoBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.emailTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.roleIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.passwordTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.usernameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAvater)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.firstNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.lastNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.birthdayDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.birthdayDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.avatarTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBeginDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBeginDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEndDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEndDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.isWorkingSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idAccountSpinEdit.Properties)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label1.Location = new System.Drawing.Point(67, 11);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(240, 36);
            label1.TabIndex = 25;
            label1.Text = "Employee detail";
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(60, 6);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(21, 17);
            idLabel.TabIndex = 0;
            idLabel.Text = "ID";
            // 
            // firstNameLabel
            // 
            firstNameLabel.AutoSize = true;
            firstNameLabel.Location = new System.Drawing.Point(60, 89);
            firstNameLabel.Name = "firstNameLabel";
            firstNameLabel.Size = new System.Drawing.Size(70, 17);
            firstNameLabel.TabIndex = 2;
            firstNameLabel.Text = "Firstname";
            // 
            // lastNameLabel
            // 
            lastNameLabel.AutoSize = true;
            lastNameLabel.Location = new System.Drawing.Point(60, 48);
            lastNameLabel.Name = "lastNameLabel";
            lastNameLabel.Size = new System.Drawing.Size(70, 17);
            lastNameLabel.TabIndex = 4;
            lastNameLabel.Text = "Lastname";
            // 
            // phoneLabel
            // 
            phoneLabel.AutoSize = true;
            phoneLabel.Location = new System.Drawing.Point(60, 131);
            phoneLabel.Name = "phoneLabel";
            phoneLabel.Size = new System.Drawing.Size(49, 17);
            phoneLabel.TabIndex = 6;
            phoneLabel.Text = "Phone";
            // 
            // addressLabel
            // 
            addressLabel.AutoSize = true;
            addressLabel.Location = new System.Drawing.Point(60, 172);
            addressLabel.Name = "addressLabel";
            addressLabel.Size = new System.Drawing.Size(60, 17);
            addressLabel.TabIndex = 8;
            addressLabel.Text = "Address";
            // 
            // birthdayLabel
            // 
            birthdayLabel.AutoSize = true;
            birthdayLabel.Location = new System.Drawing.Point(60, 212);
            birthdayLabel.Name = "birthdayLabel";
            birthdayLabel.Size = new System.Drawing.Size(60, 17);
            birthdayLabel.TabIndex = 10;
            birthdayLabel.Text = "Birthday";
            // 
            // avatarLabel
            // 
            avatarLabel.AutoSize = true;
            avatarLabel.Location = new System.Drawing.Point(60, 251);
            avatarLabel.Name = "avatarLabel";
            avatarLabel.Size = new System.Drawing.Size(49, 17);
            avatarLabel.TabIndex = 12;
            avatarLabel.Text = "Avatar";
            // 
            // dateBeginLabel
            // 
            dateBeginLabel.AutoSize = true;
            dateBeginLabel.Location = new System.Drawing.Point(60, 291);
            dateBeginLabel.Name = "dateBeginLabel";
            dateBeginLabel.Size = new System.Drawing.Size(76, 17);
            dateBeginLabel.TabIndex = 14;
            dateBeginLabel.Text = "Begin date";
            // 
            // dateEndLabel
            // 
            dateEndLabel.AutoSize = true;
            dateEndLabel.Location = new System.Drawing.Point(343, 291);
            dateEndLabel.Name = "dateEndLabel";
            dateEndLabel.Size = new System.Drawing.Size(65, 17);
            dateEndLabel.TabIndex = 16;
            dateEndLabel.Text = "End date";
            // 
            // isWorkingLabel
            // 
            isWorkingLabel.AutoSize = true;
            isWorkingLabel.Location = new System.Drawing.Point(60, 341);
            isWorkingLabel.Name = "isWorkingLabel";
            isWorkingLabel.Size = new System.Drawing.Size(70, 17);
            isWorkingLabel.TabIndex = 18;
            isWorkingLabel.Text = "Is working";
            // 
            // idAccountLabel
            // 
            idAccountLabel.AutoSize = true;
            idAccountLabel.Location = new System.Drawing.Point(343, 337);
            idAccountLabel.Name = "idAccountLabel";
            idAccountLabel.Size = new System.Drawing.Size(76, 17);
            idAccountLabel.TabIndex = 20;
            idAccountLabel.Text = "Account ID";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label2.Location = new System.Drawing.Point(67, 408);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(132, 36);
            label2.TabIndex = 60;
            label2.Text = "Account";
            // 
            // idLabel1
            // 
            idLabel1.AutoSize = true;
            idLabel1.Location = new System.Drawing.Point(70, 471);
            idLabel1.Name = "idLabel1";
            idLabel1.Size = new System.Drawing.Size(23, 17);
            idLabel1.TabIndex = 60;
            idLabel1.Text = "id:";
            // 
            // emailLabel
            // 
            emailLabel.AutoSize = true;
            emailLabel.Location = new System.Drawing.Point(70, 512);
            emailLabel.Name = "emailLabel";
            emailLabel.Size = new System.Drawing.Size(45, 17);
            emailLabel.TabIndex = 62;
            emailLabel.Text = "email:";
            // 
            // roleIdLabel
            // 
            roleIdLabel.AutoSize = true;
            roleIdLabel.Location = new System.Drawing.Point(70, 554);
            roleIdLabel.Name = "roleIdLabel";
            roleIdLabel.Size = new System.Drawing.Size(51, 17);
            roleIdLabel.TabIndex = 64;
            roleIdLabel.Text = "role Id:";
            // 
            // passwordLabel
            // 
            passwordLabel.AutoSize = true;
            passwordLabel.Location = new System.Drawing.Point(70, 594);
            passwordLabel.Name = "passwordLabel";
            passwordLabel.Size = new System.Drawing.Size(72, 17);
            passwordLabel.TabIndex = 66;
            passwordLabel.Text = "password:";
            // 
            // usernameLabel
            // 
            usernameLabel.AutoSize = true;
            usernameLabel.Location = new System.Drawing.Point(70, 634);
            usernameLabel.Name = "usernameLabel";
            usernameLabel.Size = new System.Drawing.Size(75, 17);
            usernameLabel.TabIndex = 68;
            usernameLabel.Text = "username:";
            // 
            // barManager
            // 
            this.barManager.Bars.AddRange(new DevExpress.XtraBars.Bar[] {
            this.bar2});
            this.barManager.DockControls.Add(this.barDockControl1);
            this.barManager.DockControls.Add(this.barDockControl2);
            this.barManager.DockControls.Add(this.barDockControl3);
            this.barManager.DockControls.Add(this.barDockControl5);
            this.barManager.Form = this;
            this.barManager.Items.AddRange(new DevExpress.XtraBars.BarItem[] {
            this.btnAddProvider,
            this.btnDelProvider,
            this.btnSua,
            this.btnReloadProvider,
            this.btnTimKiem,
            this.btnCloseForm});
            this.barManager.MainMenu = this.bar2;
            this.barManager.MaxItemId = 11;
            // 
            // bar2
            // 
            this.bar2.BarName = "Main menu";
            this.bar2.DockCol = 0;
            this.bar2.DockRow = 0;
            this.bar2.DockStyle = DevExpress.XtraBars.BarDockStyle.Top;
            this.bar2.LinksPersistInfo.AddRange(new DevExpress.XtraBars.LinkPersistInfo[] {
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnAddProvider, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnDelProvider, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnReloadProvider, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnCloseForm, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph)});
            this.bar2.OptionsBar.MultiLine = true;
            this.bar2.OptionsBar.UseWholeRow = true;
            this.bar2.Text = "Main menu";
            // 
            // btnAddProvider
            // 
            this.btnAddProvider.Caption = "Add new";
            this.btnAddProvider.Id = 0;
            this.btnAddProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.add;
            this.btnAddProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnAddProvider.ImageOptions.LargeImage")));
            this.btnAddProvider.Name = "btnAddProvider";
            this.btnAddProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnAddProvider_ItemClick);
            // 
            // btnDelProvider
            // 
            this.btnDelProvider.Caption = "Delete";
            this.btnDelProvider.Id = 2;
            this.btnDelProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.delete__1_;
            this.btnDelProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnDelProvider.ImageOptions.LargeImage")));
            this.btnDelProvider.Name = "btnDelProvider";
            this.btnDelProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnDelProvider_ItemClick);
            // 
            // btnReloadProvider
            // 
            this.btnReloadProvider.Caption = "Reload";
            this.btnReloadProvider.Id = 5;
            this.btnReloadProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.exchange;
            this.btnReloadProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnReloadProvider.ImageOptions.LargeImage")));
            this.btnReloadProvider.Name = "btnReloadProvider";
            this.btnReloadProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnReloadProvider_ItemClick);
            // 
            // btnCloseForm
            // 
            this.btnCloseForm.Caption = "Close form";
            this.btnCloseForm.Id = 10;
            this.btnCloseForm.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.close;
            this.btnCloseForm.Name = "btnCloseForm";
            this.btnCloseForm.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnCloseForm_ItemClick);
            // 
            // barDockControl1
            // 
            this.barDockControl1.CausesValidation = false;
            this.barDockControl1.Dock = System.Windows.Forms.DockStyle.Top;
            this.barDockControl1.Location = new System.Drawing.Point(0, 0);
            this.barDockControl1.Manager = this.barManager;
            this.barDockControl1.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl1.Size = new System.Drawing.Size(1932, 30);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 1062);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1932, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 30);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 1032);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1932, 30);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 1032);
            // 
            // btnSua
            // 
            this.btnSua.Caption = "Sửa";
            this.btnSua.Id = 3;
            this.btnSua.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnSua.ImageOptions.Image")));
            this.btnSua.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnSua.ImageOptions.LargeImage")));
            this.btnSua.Name = "btnSua";
            // 
            // btnTimKiem
            // 
            this.btnTimKiem.Caption = "Tìm kiếm";
            this.btnTimKiem.Id = 9;
            this.btnTimKiem.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnTimKiem.ImageOptions.Image")));
            this.btnTimKiem.Name = "btnTimKiem";
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // sP_GetAllEmployeeBindingSource
            // 
            this.sP_GetAllEmployeeBindingSource.DataMember = "SP_GetAllEmployee";
            this.sP_GetAllEmployeeBindingSource.DataSource = this.clothesDataSet;
            // 
            // sP_GetAllEmployeeTableAdapter
            // 
            this.sP_GetAllEmployeeTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.InvoiceTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = null;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.panel3, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.sP_GetAllEmployeeGridControl, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panel1, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panel2, 1, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 30);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 7.714561F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 92.28544F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1932, 1032);
            this.tableLayoutPanel1.TabIndex = 7;
            // 
            // panel3
            // 
            this.panel3.Controls.Add(label1);
            this.panel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel3.Location = new System.Drawing.Point(969, 3);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(960, 73);
            this.panel3.TabIndex = 3;
            // 
            // sP_GetAllEmployeeGridControl
            // 
            this.sP_GetAllEmployeeGridControl.DataSource = this.sP_GetAllEmployeeBindingSource;
            this.sP_GetAllEmployeeGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.sP_GetAllEmployeeGridControl.Location = new System.Drawing.Point(3, 82);
            this.sP_GetAllEmployeeGridControl.MainView = this.gridView1;
            this.sP_GetAllEmployeeGridControl.MenuManager = this.barManager;
            this.sP_GetAllEmployeeGridControl.Name = "sP_GetAllEmployeeGridControl";
            this.sP_GetAllEmployeeGridControl.Size = new System.Drawing.Size(960, 947);
            this.sP_GetAllEmployeeGridControl.TabIndex = 0;
            this.sP_GetAllEmployeeGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            this.sP_GetAllEmployeeGridControl.Click += new System.EventHandler(this.sP_GetAllEmployeeGridControl_Click);
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colid,
            this.collastName,
            this.colfirstName,
            this.colphone,
            this.coladdress,
            this.colbirthday,
            this.colavatar,
            this.coldateBegin,
            this.coldateEnd,
            this.colisWorking,
            this.colidAccount});
            this.gridView1.GridControl = this.sP_GetAllEmployeeGridControl;
            this.gridView1.Name = "gridView1";
            // 
            // colid
            // 
            this.colid.FieldName = "id";
            this.colid.MinWidth = 25;
            this.colid.Name = "colid";
            this.colid.OptionsColumn.AllowEdit = false;
            this.colid.Visible = true;
            this.colid.VisibleIndex = 0;
            this.colid.Width = 94;
            // 
            // collastName
            // 
            this.collastName.FieldName = "lastName";
            this.collastName.MinWidth = 25;
            this.collastName.Name = "collastName";
            this.collastName.OptionsColumn.AllowEdit = false;
            this.collastName.Visible = true;
            this.collastName.VisibleIndex = 2;
            this.collastName.Width = 94;
            // 
            // colfirstName
            // 
            this.colfirstName.FieldName = "firstName";
            this.colfirstName.MinWidth = 25;
            this.colfirstName.Name = "colfirstName";
            this.colfirstName.OptionsColumn.AllowEdit = false;
            this.colfirstName.Visible = true;
            this.colfirstName.VisibleIndex = 1;
            this.colfirstName.Width = 94;
            // 
            // colphone
            // 
            this.colphone.FieldName = "phone";
            this.colphone.MinWidth = 25;
            this.colphone.Name = "colphone";
            this.colphone.OptionsColumn.AllowEdit = false;
            this.colphone.Visible = true;
            this.colphone.VisibleIndex = 3;
            this.colphone.Width = 94;
            // 
            // coladdress
            // 
            this.coladdress.FieldName = "address";
            this.coladdress.MinWidth = 25;
            this.coladdress.Name = "coladdress";
            this.coladdress.OptionsColumn.AllowEdit = false;
            this.coladdress.Visible = true;
            this.coladdress.VisibleIndex = 4;
            this.coladdress.Width = 94;
            // 
            // colbirthday
            // 
            this.colbirthday.FieldName = "birthday";
            this.colbirthday.MinWidth = 25;
            this.colbirthday.Name = "colbirthday";
            this.colbirthday.OptionsColumn.AllowEdit = false;
            this.colbirthday.Visible = true;
            this.colbirthday.VisibleIndex = 5;
            this.colbirthday.Width = 94;
            // 
            // colavatar
            // 
            this.colavatar.FieldName = "avatar";
            this.colavatar.MinWidth = 25;
            this.colavatar.Name = "colavatar";
            this.colavatar.OptionsColumn.AllowEdit = false;
            this.colavatar.Visible = true;
            this.colavatar.VisibleIndex = 6;
            this.colavatar.Width = 94;
            // 
            // coldateBegin
            // 
            this.coldateBegin.FieldName = "dateBegin";
            this.coldateBegin.MinWidth = 25;
            this.coldateBegin.Name = "coldateBegin";
            this.coldateBegin.OptionsColumn.AllowEdit = false;
            this.coldateBegin.Visible = true;
            this.coldateBegin.VisibleIndex = 7;
            this.coldateBegin.Width = 94;
            // 
            // coldateEnd
            // 
            this.coldateEnd.FieldName = "dateEnd";
            this.coldateEnd.MinWidth = 25;
            this.coldateEnd.Name = "coldateEnd";
            this.coldateEnd.OptionsColumn.AllowEdit = false;
            this.coldateEnd.Visible = true;
            this.coldateEnd.VisibleIndex = 8;
            this.coldateEnd.Width = 94;
            // 
            // colisWorking
            // 
            this.colisWorking.FieldName = "isWorking";
            this.colisWorking.MinWidth = 25;
            this.colisWorking.Name = "colisWorking";
            this.colisWorking.OptionsColumn.AllowEdit = false;
            this.colisWorking.Visible = true;
            this.colisWorking.VisibleIndex = 9;
            this.colisWorking.Width = 94;
            // 
            // colidAccount
            // 
            this.colidAccount.FieldName = "idAccount";
            this.colidAccount.MinWidth = 25;
            this.colidAccount.Name = "colidAccount";
            this.colidAccount.OptionsColumn.AllowEdit = false;
            this.colidAccount.Visible = true;
            this.colidAccount.VisibleIndex = 10;
            this.colidAccount.Width = 94;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.btnSearchEmployee);
            this.panel1.Controls.Add(this.edtSearch);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(3, 3);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(960, 73);
            this.panel1.TabIndex = 1;
            // 
            // btnSearchEmployee
            // 
            this.btnSearchEmployee.Location = new System.Drawing.Point(437, 15);
            this.btnSearchEmployee.Name = "btnSearchEmployee";
            this.btnSearchEmployee.Size = new System.Drawing.Size(75, 32);
            this.btnSearchEmployee.TabIndex = 2;
            this.btnSearchEmployee.Text = "Search";
            this.btnSearchEmployee.UseVisualStyleBackColor = true;
            this.btnSearchEmployee.Click += new System.EventHandler(this.btnSearchEmployee_Click);
            // 
            // edtSearch
            // 
            this.edtSearch.Location = new System.Drawing.Point(39, 20);
            this.edtSearch.Name = "edtSearch";
            this.edtSearch.Size = new System.Drawing.Size(374, 22);
            this.edtSearch.TabIndex = 0;
            // 
            // panel2
            // 
            this.panel2.Controls.Add(idLabel1);
            this.panel2.Controls.Add(this.idSpinEdit1);
            this.panel2.Controls.Add(emailLabel);
            this.panel2.Controls.Add(this.emailTextEdit);
            this.panel2.Controls.Add(roleIdLabel);
            this.panel2.Controls.Add(this.roleIdSpinEdit);
            this.panel2.Controls.Add(passwordLabel);
            this.panel2.Controls.Add(this.passwordTextEdit);
            this.panel2.Controls.Add(usernameLabel);
            this.panel2.Controls.Add(this.usernameTextEdit);
            this.panel2.Controls.Add(label2);
            this.panel2.Controls.Add(this.panel4);
            this.panel2.Controls.Add(this.btnCancelAddProvider);
            this.panel2.Controls.Add(this.btnSaveAddProvider);
            this.panel2.Controls.Add(this.picAvater);
            this.panel2.Controls.Add(idLabel);
            this.panel2.Controls.Add(this.idSpinEdit);
            this.panel2.Controls.Add(firstNameLabel);
            this.panel2.Controls.Add(this.firstNameTextEdit);
            this.panel2.Controls.Add(lastNameLabel);
            this.panel2.Controls.Add(this.lastNameTextEdit);
            this.panel2.Controls.Add(phoneLabel);
            this.panel2.Controls.Add(this.phoneTextEdit);
            this.panel2.Controls.Add(addressLabel);
            this.panel2.Controls.Add(this.addressTextEdit);
            this.panel2.Controls.Add(birthdayLabel);
            this.panel2.Controls.Add(this.birthdayDateEdit);
            this.panel2.Controls.Add(avatarLabel);
            this.panel2.Controls.Add(this.avatarTextEdit);
            this.panel2.Controls.Add(dateBeginLabel);
            this.panel2.Controls.Add(this.dateBeginDateEdit);
            this.panel2.Controls.Add(dateEndLabel);
            this.panel2.Controls.Add(this.dateEndDateEdit);
            this.panel2.Controls.Add(isWorkingLabel);
            this.panel2.Controls.Add(this.isWorkingSpinEdit);
            this.panel2.Controls.Add(idAccountLabel);
            this.panel2.Controls.Add(this.idAccountSpinEdit);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel2.Location = new System.Drawing.Point(969, 82);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(960, 947);
            this.panel2.TabIndex = 2;
            // 
            // idSpinEdit1
            // 
            this.idSpinEdit1.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAccEmployeeInfoBindingSource, "id", true));
            this.idSpinEdit1.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit1.Enabled = false;
            this.idSpinEdit1.Location = new System.Drawing.Point(151, 468);
            this.idSpinEdit1.MenuManager = this.barManager;
            this.idSpinEdit1.Name = "idSpinEdit1";
            this.idSpinEdit1.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit1.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit1.TabIndex = 61;
            // 
            // sP_GetAccEmployeeInfoBindingSource
            // 
            this.sP_GetAccEmployeeInfoBindingSource.DataMember = "SP_GetAccEmployeeInfo";
            this.sP_GetAccEmployeeInfoBindingSource.DataSource = this.clothesDataSet;
            // 
            // emailTextEdit
            // 
            this.emailTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAccEmployeeInfoBindingSource, "email", true));
            this.emailTextEdit.Location = new System.Drawing.Point(151, 509);
            this.emailTextEdit.MenuManager = this.barManager;
            this.emailTextEdit.Name = "emailTextEdit";
            this.emailTextEdit.Size = new System.Drawing.Size(274, 22);
            this.emailTextEdit.TabIndex = 63;
            // 
            // roleIdSpinEdit
            // 
            this.roleIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAccEmployeeInfoBindingSource, "roleId", true));
            this.roleIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.roleIdSpinEdit.Location = new System.Drawing.Point(151, 551);
            this.roleIdSpinEdit.MenuManager = this.barManager;
            this.roleIdSpinEdit.Name = "roleIdSpinEdit";
            this.roleIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.roleIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.roleIdSpinEdit.TabIndex = 65;
            // 
            // passwordTextEdit
            // 
            this.passwordTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAccEmployeeInfoBindingSource, "password", true));
            this.passwordTextEdit.Location = new System.Drawing.Point(151, 591);
            this.passwordTextEdit.MenuManager = this.barManager;
            this.passwordTextEdit.Name = "passwordTextEdit";
            this.passwordTextEdit.Size = new System.Drawing.Size(210, 22);
            this.passwordTextEdit.TabIndex = 67;
            // 
            // usernameTextEdit
            // 
            this.usernameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAccEmployeeInfoBindingSource, "username", true));
            this.usernameTextEdit.Location = new System.Drawing.Point(151, 631);
            this.usernameTextEdit.MenuManager = this.barManager;
            this.usernameTextEdit.Name = "usernameTextEdit";
            this.usernameTextEdit.Size = new System.Drawing.Size(125, 22);
            this.usernameTextEdit.TabIndex = 69;
            // 
            // panel4
            // 
            this.panel4.BackColor = System.Drawing.Color.Silver;
            this.panel4.Location = new System.Drawing.Point(63, 377);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(851, 10);
            this.panel4.TabIndex = 59;
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(690, 616);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 58;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            this.btnCancelAddProvider.Click += new System.EventHandler(this.btnCancelAddProvider_Click);
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(472, 616);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 57;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click);
            // 
            // picAvater
            // 
            this.picAvater.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.picAvater.ErrorImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picAvater.InitialImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picAvater.Location = new System.Drawing.Point(656, 32);
            this.picAvater.MaximumSize = new System.Drawing.Size(226, 276);
            this.picAvater.Name = "picAvater";
            this.picAvater.Size = new System.Drawing.Size(226, 276);
            this.picAvater.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picAvater.TabIndex = 22;
            this.picAvater.TabStop = false;
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Enabled = false;
            this.idSpinEdit.Location = new System.Drawing.Point(178, 3);
            this.idSpinEdit.MenuManager = this.barManager;
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 1;
            // 
            // firstNameTextEdit
            // 
            this.firstNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "firstName", true));
            this.firstNameTextEdit.Location = new System.Drawing.Point(178, 86);
            this.firstNameTextEdit.MenuManager = this.barManager;
            this.firstNameTextEdit.Name = "firstNameTextEdit";
            this.firstNameTextEdit.Size = new System.Drawing.Size(183, 22);
            this.firstNameTextEdit.TabIndex = 3;
            // 
            // lastNameTextEdit
            // 
            this.lastNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "lastName", true));
            this.lastNameTextEdit.Location = new System.Drawing.Point(178, 45);
            this.lastNameTextEdit.MenuManager = this.barManager;
            this.lastNameTextEdit.Name = "lastNameTextEdit";
            this.lastNameTextEdit.Size = new System.Drawing.Size(408, 22);
            this.lastNameTextEdit.TabIndex = 5;
            // 
            // phoneTextEdit
            // 
            this.phoneTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "phone", true));
            this.phoneTextEdit.Location = new System.Drawing.Point(178, 128);
            this.phoneTextEdit.MenuManager = this.barManager;
            this.phoneTextEdit.Name = "phoneTextEdit";
            this.phoneTextEdit.Size = new System.Drawing.Size(183, 22);
            this.phoneTextEdit.TabIndex = 7;
            // 
            // addressTextEdit
            // 
            this.addressTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "address", true));
            this.addressTextEdit.Location = new System.Drawing.Point(178, 169);
            this.addressTextEdit.MenuManager = this.barManager;
            this.addressTextEdit.Name = "addressTextEdit";
            this.addressTextEdit.Size = new System.Drawing.Size(408, 22);
            this.addressTextEdit.TabIndex = 9;
            // 
            // birthdayDateEdit
            // 
            this.birthdayDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "birthday", true));
            this.birthdayDateEdit.EditValue = new System.DateTime(2020, 8, 12, 7, 5, 37, 169);
            this.birthdayDateEdit.Location = new System.Drawing.Point(178, 209);
            this.birthdayDateEdit.MenuManager = this.barManager;
            this.birthdayDateEdit.Name = "birthdayDateEdit";
            this.birthdayDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.birthdayDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.birthdayDateEdit.Size = new System.Drawing.Size(125, 22);
            this.birthdayDateEdit.TabIndex = 11;
            // 
            // avatarTextEdit
            // 
            this.avatarTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "avatar", true));
            this.avatarTextEdit.Location = new System.Drawing.Point(178, 248);
            this.avatarTextEdit.MenuManager = this.barManager;
            this.avatarTextEdit.Name = "avatarTextEdit";
            this.avatarTextEdit.Size = new System.Drawing.Size(408, 22);
            this.avatarTextEdit.TabIndex = 13;
            this.avatarTextEdit.EditValueChanged += new System.EventHandler(this.avatarTextEdit_EditValueChanged);
            // 
            // dateBeginDateEdit
            // 
            this.dateBeginDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "dateBegin", true));
            this.dateBeginDateEdit.EditValue = new System.DateTime(2020, 8, 12, 7, 6, 11, 587);
            this.dateBeginDateEdit.Location = new System.Drawing.Point(178, 288);
            this.dateBeginDateEdit.MenuManager = this.barManager;
            this.dateBeginDateEdit.Name = "dateBeginDateEdit";
            this.dateBeginDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateBeginDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateBeginDateEdit.Size = new System.Drawing.Size(125, 22);
            this.dateBeginDateEdit.TabIndex = 15;
            // 
            // dateEndDateEdit
            // 
            this.dateEndDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "dateEnd", true));
            this.dateEndDateEdit.EditValue = new System.DateTime(2020, 8, 12, 7, 6, 20, 873);
            this.dateEndDateEdit.Location = new System.Drawing.Point(461, 288);
            this.dateEndDateEdit.MenuManager = this.barManager;
            this.dateEndDateEdit.Name = "dateEndDateEdit";
            this.dateEndDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateEndDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateEndDateEdit.Size = new System.Drawing.Size(125, 22);
            this.dateEndDateEdit.TabIndex = 17;
            // 
            // isWorkingSpinEdit
            // 
            this.isWorkingSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "isWorking", true));
            this.isWorkingSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.isWorkingSpinEdit.Location = new System.Drawing.Point(178, 338);
            this.isWorkingSpinEdit.MenuManager = this.barManager;
            this.isWorkingSpinEdit.Name = "isWorkingSpinEdit";
            this.isWorkingSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.isWorkingSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.isWorkingSpinEdit.TabIndex = 19;
            // 
            // idAccountSpinEdit
            // 
            this.idAccountSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetAllEmployeeBindingSource, "idAccount", true));
            this.idAccountSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idAccountSpinEdit.Enabled = false;
            this.idAccountSpinEdit.Location = new System.Drawing.Point(461, 334);
            this.idAccountSpinEdit.MenuManager = this.barManager;
            this.idAccountSpinEdit.Name = "idAccountSpinEdit";
            this.idAccountSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idAccountSpinEdit.Properties.DisplayFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.idAccountSpinEdit.Properties.EditFormat.FormatType = DevExpress.Utils.FormatType.Numeric;
            this.idAccountSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idAccountSpinEdit.TabIndex = 21;
            // 
            // accountTableAdapter
            // 
            this.accountTableAdapter.ClearBeforeFill = true;
            // 
            // sP_GetAccEmployeeInfoTableAdapter
            // 
            this.sP_GetAccEmployeeInfoTableAdapter.ClearBeforeFill = true;
            // 
            // EmployeeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1932, 1062);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "EmployeeForm";
            this.Text = "EmployeeForm";
            this.Load += new System.EventHandler(this.EmployeeForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetAllEmployeeBindingSource)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.panel3.ResumeLayout(false);
            this.panel3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetAllEmployeeGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit1.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetAccEmployeeInfoBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.emailTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.roleIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.passwordTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.usernameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picAvater)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.firstNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.lastNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.birthdayDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.birthdayDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.avatarTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBeginDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBeginDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEndDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEndDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.isWorkingSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idAccountSpinEdit.Properties)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraBars.BarManager barManager;
        private DevExpress.XtraBars.Bar bar2;
        private DevExpress.XtraBars.BarButtonItem btnAddProvider;
        private DevExpress.XtraBars.BarButtonItem btnDelProvider;
        private DevExpress.XtraBars.BarButtonItem btnReloadProvider;
        private DevExpress.XtraBars.BarButtonItem btnCloseForm;
        private DevExpress.XtraBars.BarDockControl barDockControl1;
        private DevExpress.XtraBars.BarDockControl barDockControl2;
        private DevExpress.XtraBars.BarDockControl barDockControl3;
        private DevExpress.XtraBars.BarDockControl barDockControl5;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private System.Windows.Forms.BindingSource sP_GetAllEmployeeBindingSource;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.SP_GetAllEmployeeTableAdapter sP_GetAllEmployeeTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private DevExpress.XtraGrid.GridControl sP_GetAllEmployeeGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button btnSearchEmployee;
        private System.Windows.Forms.TextBox edtSearch;
        private System.Windows.Forms.Panel panel3;
        private DevExpress.XtraGrid.Columns.GridColumn colid;
        private DevExpress.XtraGrid.Columns.GridColumn colfirstName;
        private DevExpress.XtraGrid.Columns.GridColumn collastName;
        private DevExpress.XtraGrid.Columns.GridColumn colphone;
        private DevExpress.XtraGrid.Columns.GridColumn coladdress;
        private DevExpress.XtraGrid.Columns.GridColumn colbirthday;
        private DevExpress.XtraGrid.Columns.GridColumn colavatar;
        private DevExpress.XtraGrid.Columns.GridColumn coldateBegin;
        private DevExpress.XtraGrid.Columns.GridColumn coldateEnd;
        private DevExpress.XtraGrid.Columns.GridColumn colisWorking;
        private DevExpress.XtraGrid.Columns.GridColumn colidAccount;
        private System.Windows.Forms.Panel panel2;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.TextEdit firstNameTextEdit;
        private DevExpress.XtraEditors.TextEdit lastNameTextEdit;
        private DevExpress.XtraEditors.TextEdit phoneTextEdit;
        private DevExpress.XtraEditors.TextEdit addressTextEdit;
        private DevExpress.XtraEditors.DateEdit birthdayDateEdit;
        private DevExpress.XtraEditors.TextEdit avatarTextEdit;
        private DevExpress.XtraEditors.DateEdit dateBeginDateEdit;
        private DevExpress.XtraEditors.DateEdit dateEndDateEdit;
        private DevExpress.XtraEditors.SpinEdit isWorkingSpinEdit;
        private DevExpress.XtraEditors.SpinEdit idAccountSpinEdit;
        private System.Windows.Forms.PictureBox picAvater;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private ClothesDataSetTableAdapters.AccountTableAdapter accountTableAdapter;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.BindingSource sP_GetAccEmployeeInfoBindingSource;
        private ClothesDataSetTableAdapters.SP_GetAccEmployeeInfoTableAdapter sP_GetAccEmployeeInfoTableAdapter;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit1;
        private DevExpress.XtraEditors.TextEdit emailTextEdit;
        private DevExpress.XtraEditors.SpinEdit roleIdSpinEdit;
        private DevExpress.XtraEditors.TextEdit passwordTextEdit;
        private DevExpress.XtraEditors.TextEdit usernameTextEdit;
    }
}