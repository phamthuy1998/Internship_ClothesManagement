namespace ClothesAdmin
{
    partial class ImportCuponForm
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
            System.Windows.Forms.Label idLabel;
            System.Windows.Forms.Label employeeIdLabel;
            System.Windows.Forms.Label idCouponLabel;
            System.Windows.Forms.Label idProductLabel;
            System.Windows.Forms.Label quantityLabel;
            System.Windows.Forms.Label priceLabel;
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label dateLabel;
            System.Windows.Forms.Label label3;
            System.Windows.Forms.Label sizeIDLabel;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ImportCuponForm));
            System.Windows.Forms.Label label4;
            System.Windows.Forms.Label label5;
            System.Windows.Forms.Label colorIdLabel;
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
            this.panel1 = new System.Windows.Forms.Panel();
            this.employeeComboBox1 = new System.Windows.Forms.ComboBox();
            this.employeeBindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.importCouponGridControl = new DevExpress.XtraGrid.GridControl();
            this.importCouponBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldate = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colemployeeId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.importCouponDetailGridControl = new DevExpress.XtraGrid.GridControl();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.addProductPromoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.deleteProductPromoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.reloadToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.importCouponDetailBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridView2 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colidCoupon = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colidProduct = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcolorId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsizeID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colquantity = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colprice = new DevExpress.XtraGrid.Columns.GridColumn();
            this.panel4 = new System.Windows.Forms.Panel();
            this.dateDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.employeeComboBox = new System.Windows.Forms.ComboBox();
            this.employeeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.btnCancelImport = new System.Windows.Forms.Button();
            this.btnSaveIport = new System.Windows.Forms.Button();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.employeeIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.panel5 = new System.Windows.Forms.Panel();
            this.sizeComboBox = new System.Windows.Forms.ComboBox();
            this.sizeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sizeIDTextBox = new System.Windows.Forms.TextBox();
            this.productComboBox = new System.Windows.Forms.ComboBox();
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.btnCancelAddItem = new System.Windows.Forms.Button();
            this.btnSaveAddItem = new System.Windows.Forms.Button();
            this.idCouponSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.idProductSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.quantitySpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.priceSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.importCouponTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ImportCouponTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.employeeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.EmployeeTableAdapter();
            this.importCouponDetailTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ImportCouponDetailTableAdapter();
            this.productTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductTableAdapter();
            this.sizeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SizeTableAdapter();
            this.colorBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.colorTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ColorTableAdapter();
            this.colorComboBox = new System.Windows.Forms.ComboBox();
            this.colorIdTextBox = new System.Windows.Forms.TextBox();
            idLabel = new System.Windows.Forms.Label();
            employeeIdLabel = new System.Windows.Forms.Label();
            idCouponLabel = new System.Windows.Forms.Label();
            idProductLabel = new System.Windows.Forms.Label();
            quantityLabel = new System.Windows.Forms.Label();
            priceLabel = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            dateLabel = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            sizeIDLabel = new System.Windows.Forms.Label();
            label4 = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            colorIdLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.employeeBindingSource1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailGridControl)).BeginInit();
            this.contextMenuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).BeginInit();
            this.panel4.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dateDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeeIdSpinEdit.Properties)).BeginInit();
            this.panel5.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idCouponSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idProductSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.quantitySpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.priceSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(67, 44);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(23, 17);
            idLabel.TabIndex = 0;
            idLabel.Text = "id:";
            // 
            // employeeIdLabel
            // 
            employeeIdLabel.AutoSize = true;
            employeeIdLabel.Location = new System.Drawing.Point(67, 97);
            employeeIdLabel.Name = "employeeIdLabel";
            employeeIdLabel.Size = new System.Drawing.Size(88, 17);
            employeeIdLabel.TabIndex = 4;
            employeeIdLabel.Text = "employee Id:";
            // 
            // idCouponLabel
            // 
            idCouponLabel.AutoSize = true;
            idCouponLabel.Location = new System.Drawing.Point(84, 25);
            idCouponLabel.Name = "idCouponLabel";
            idCouponLabel.Size = new System.Drawing.Size(74, 17);
            idCouponLabel.TabIndex = 0;
            idCouponLabel.Text = "Coupon ID";
            // 
            // idProductLabel
            // 
            idProductLabel.AutoSize = true;
            idProductLabel.Location = new System.Drawing.Point(625, 71);
            idProductLabel.Name = "idProductLabel";
            idProductLabel.Size = new System.Drawing.Size(74, 17);
            idProductLabel.TabIndex = 2;
            idProductLabel.Text = "Product ID";
            // 
            // quantityLabel
            // 
            quantityLabel.AutoSize = true;
            quantityLabel.Location = new System.Drawing.Point(82, 215);
            quantityLabel.Name = "quantityLabel";
            quantityLabel.Size = new System.Drawing.Size(61, 17);
            quantityLabel.TabIndex = 4;
            quantityLabel.Text = "Quantity";
            // 
            // priceLabel
            // 
            priceLabel.AutoSize = true;
            priceLabel.Location = new System.Drawing.Point(582, 215);
            priceLabel.Name = "priceLabel";
            priceLabel.Size = new System.Drawing.Size(40, 17);
            priceLabel.TabIndex = 6;
            priceLabel.Text = "Price";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(82, 70);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(57, 17);
            label1.TabIndex = 4;
            label1.Text = "Product";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(405, 97);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(116, 17);
            label2.TabIndex = 2;
            label2.Text = "Empoyee\'s name";
            // 
            // dateLabel
            // 
            dateLabel.AutoSize = true;
            dateLabel.Location = new System.Drawing.Point(698, 45);
            dateLabel.Name = "dateLabel";
            dateLabel.Size = new System.Drawing.Size(40, 17);
            dateLabel.TabIndex = 58;
            dateLabel.Text = "date:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(20, 23);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(116, 17);
            label3.TabIndex = 2;
            label3.Text = "Empoyee\'s name";
            // 
            // sizeIDLabel
            // 
            sizeIDLabel.AutoSize = true;
            sizeIDLabel.Location = new System.Drawing.Point(625, 116);
            sizeIDLabel.Name = "sizeIDLabel";
            sizeIDLabel.Size = new System.Drawing.Size(52, 17);
            sizeIDLabel.TabIndex = 58;
            sizeIDLabel.Text = "Size ID";
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
            this.barDockControl1.Size = new System.Drawing.Size(1940, 33);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 902);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1940, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 33);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 869);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1940, 33);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 869);
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
            // panel1
            // 
            this.panel1.Controls.Add(this.employeeComboBox1);
            this.panel1.Controls.Add(label3);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 33);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1940, 68);
            this.panel1.TabIndex = 4;
            // 
            // employeeComboBox1
            // 
            this.employeeComboBox1.DataSource = this.employeeBindingSource1;
            this.employeeComboBox1.DisplayMember = "firstName";
            this.employeeComboBox1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.employeeComboBox1.FormattingEnabled = true;
            this.employeeComboBox1.Location = new System.Drawing.Point(162, 24);
            this.employeeComboBox1.Name = "employeeComboBox1";
            this.employeeComboBox1.Size = new System.Drawing.Size(300, 24);
            this.employeeComboBox1.TabIndex = 2;
            this.employeeComboBox1.ValueMember = "id";
            this.employeeComboBox1.SelectedIndexChanged += new System.EventHandler(this.employeeComboBox1_SelectedIndexChanged);
            // 
            // employeeBindingSource1
            // 
            this.employeeBindingSource1.DataMember = "Employee";
            this.employeeBindingSource1.DataSource = this.clothesDataSet;
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.Single;
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.importCouponGridControl, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.importCouponDetailGridControl, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.panel4, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panel5, 1, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 101);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1940, 801);
            this.tableLayoutPanel1.TabIndex = 5;
            // 
            // importCouponGridControl
            // 
            this.importCouponGridControl.DataSource = this.importCouponBindingSource;
            this.importCouponGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.importCouponGridControl.Location = new System.Drawing.Point(4, 4);
            this.importCouponGridControl.MainView = this.gridView1;
            this.importCouponGridControl.MenuManager = this.barManager;
            this.importCouponGridControl.Name = "importCouponGridControl";
            this.importCouponGridControl.Size = new System.Drawing.Size(962, 393);
            this.importCouponGridControl.TabIndex = 3;
            this.importCouponGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // importCouponBindingSource
            // 
            this.importCouponBindingSource.DataMember = "ImportCoupon";
            this.importCouponBindingSource.DataSource = this.clothesDataSet;
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colid,
            this.coldate,
            this.colemployeeId});
            this.gridView1.GridControl = this.importCouponGridControl;
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
            // coldate
            // 
            this.coldate.FieldName = "date";
            this.coldate.MinWidth = 25;
            this.coldate.Name = "coldate";
            this.coldate.OptionsColumn.AllowEdit = false;
            this.coldate.Visible = true;
            this.coldate.VisibleIndex = 1;
            this.coldate.Width = 94;
            // 
            // colemployeeId
            // 
            this.colemployeeId.FieldName = "employeeId";
            this.colemployeeId.MinWidth = 25;
            this.colemployeeId.Name = "colemployeeId";
            this.colemployeeId.OptionsColumn.AllowEdit = false;
            this.colemployeeId.Visible = true;
            this.colemployeeId.VisibleIndex = 2;
            this.colemployeeId.Width = 94;
            // 
            // importCouponDetailGridControl
            // 
            this.importCouponDetailGridControl.ContextMenuStrip = this.contextMenuStrip1;
            this.importCouponDetailGridControl.DataSource = this.importCouponDetailBindingSource;
            this.importCouponDetailGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.importCouponDetailGridControl.Location = new System.Drawing.Point(973, 4);
            this.importCouponDetailGridControl.MainView = this.gridView2;
            this.importCouponDetailGridControl.MenuManager = this.barManager;
            this.importCouponDetailGridControl.Name = "importCouponDetailGridControl";
            this.importCouponDetailGridControl.Size = new System.Drawing.Size(963, 393);
            this.importCouponDetailGridControl.TabIndex = 3;
            this.importCouponDetailGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView2});
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.addProductPromoToolStripMenuItem,
            this.deleteProductPromoToolStripMenuItem,
            this.reloadToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(215, 82);
            // 
            // addProductPromoToolStripMenuItem
            // 
            this.addProductPromoToolStripMenuItem.Image = global::ClothesAdmin.Properties.Resources.add;
            this.addProductPromoToolStripMenuItem.Name = "addProductPromoToolStripMenuItem";
            this.addProductPromoToolStripMenuItem.Size = new System.Drawing.Size(214, 26);
            this.addProductPromoToolStripMenuItem.Text = "Add item coupon";
            this.addProductPromoToolStripMenuItem.Click += new System.EventHandler(this.addProductPromoToolStripMenuItem_Click);
            // 
            // deleteProductPromoToolStripMenuItem
            // 
            this.deleteProductPromoToolStripMenuItem.Image = global::ClothesAdmin.Properties.Resources.delete;
            this.deleteProductPromoToolStripMenuItem.Name = "deleteProductPromoToolStripMenuItem";
            this.deleteProductPromoToolStripMenuItem.Size = new System.Drawing.Size(214, 26);
            this.deleteProductPromoToolStripMenuItem.Text = "Delete item coupon";
            this.deleteProductPromoToolStripMenuItem.Click += new System.EventHandler(this.deleteProductPromoToolStripMenuItem_Click);
            // 
            // reloadToolStripMenuItem
            // 
            this.reloadToolStripMenuItem.Image = global::ClothesAdmin.Properties.Resources.exchange;
            this.reloadToolStripMenuItem.Name = "reloadToolStripMenuItem";
            this.reloadToolStripMenuItem.Size = new System.Drawing.Size(214, 26);
            this.reloadToolStripMenuItem.Text = "Reload ";
            this.reloadToolStripMenuItem.Click += new System.EventHandler(this.reloadToolStripMenuItem_Click);
            // 
            // importCouponDetailBindingSource
            // 
            this.importCouponDetailBindingSource.DataMember = "FK_ImportCouponDetail_ImportCoupon";
            this.importCouponDetailBindingSource.DataSource = this.importCouponBindingSource;
            // 
            // gridView2
            // 
            this.gridView2.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colidCoupon,
            this.colidProduct,
            this.colcolorId,
            this.colsizeID,
            this.colquantity,
            this.colprice});
            this.gridView2.GridControl = this.importCouponDetailGridControl;
            this.gridView2.Name = "gridView2";
            // 
            // colidCoupon
            // 
            this.colidCoupon.FieldName = "idCoupon";
            this.colidCoupon.MinWidth = 25;
            this.colidCoupon.Name = "colidCoupon";
            this.colidCoupon.OptionsColumn.AllowEdit = false;
            this.colidCoupon.Visible = true;
            this.colidCoupon.VisibleIndex = 0;
            this.colidCoupon.Width = 94;
            // 
            // colidProduct
            // 
            this.colidProduct.FieldName = "idProduct";
            this.colidProduct.MinWidth = 25;
            this.colidProduct.Name = "colidProduct";
            this.colidProduct.OptionsColumn.AllowEdit = false;
            this.colidProduct.Visible = true;
            this.colidProduct.VisibleIndex = 1;
            this.colidProduct.Width = 94;
            // 
            // colcolorId
            // 
            this.colcolorId.FieldName = "colorId";
            this.colcolorId.MinWidth = 25;
            this.colcolorId.Name = "colcolorId";
            this.colcolorId.OptionsColumn.AllowEdit = false;
            this.colcolorId.Visible = true;
            this.colcolorId.VisibleIndex = 4;
            this.colcolorId.Width = 94;
            // 
            // colsizeID
            // 
            this.colsizeID.FieldName = "sizeID";
            this.colsizeID.MinWidth = 25;
            this.colsizeID.Name = "colsizeID";
            this.colsizeID.OptionsColumn.AllowEdit = false;
            this.colsizeID.Visible = true;
            this.colsizeID.VisibleIndex = 5;
            this.colsizeID.Width = 94;
            // 
            // colquantity
            // 
            this.colquantity.FieldName = "quantity";
            this.colquantity.MinWidth = 25;
            this.colquantity.Name = "colquantity";
            this.colquantity.OptionsColumn.AllowEdit = false;
            this.colquantity.Visible = true;
            this.colquantity.VisibleIndex = 2;
            this.colquantity.Width = 94;
            // 
            // colprice
            // 
            this.colprice.FieldName = "price";
            this.colprice.MinWidth = 25;
            this.colprice.Name = "colprice";
            this.colprice.OptionsColumn.AllowEdit = false;
            this.colprice.Visible = true;
            this.colprice.VisibleIndex = 3;
            this.colprice.Width = 94;
            // 
            // panel4
            // 
            this.panel4.Controls.Add(dateLabel);
            this.panel4.Controls.Add(this.dateDateEdit);
            this.panel4.Controls.Add(this.employeeComboBox);
            this.panel4.Controls.Add(this.btnCancelImport);
            this.panel4.Controls.Add(idLabel);
            this.panel4.Controls.Add(this.btnSaveIport);
            this.panel4.Controls.Add(this.idSpinEdit);
            this.panel4.Controls.Add(label2);
            this.panel4.Controls.Add(employeeIdLabel);
            this.panel4.Controls.Add(this.employeeIdSpinEdit);
            this.panel4.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel4.Location = new System.Drawing.Point(4, 404);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(962, 393);
            this.panel4.TabIndex = 2;
            // 
            // dateDateEdit
            // 
            this.dateDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.importCouponBindingSource, "date", true));
            this.dateDateEdit.EditValue = null;
            this.dateDateEdit.Location = new System.Drawing.Point(744, 42);
            this.dateDateEdit.MenuManager = this.barManager;
            this.dateDateEdit.Name = "dateDateEdit";
            this.dateDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateDateEdit.Size = new System.Drawing.Size(125, 22);
            this.dateDateEdit.TabIndex = 59;
            // 
            // employeeComboBox
            // 
            this.employeeComboBox.DataSource = this.employeeBindingSource;
            this.employeeComboBox.DisplayMember = "firstName";
            this.employeeComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.employeeComboBox.Enabled = false;
            this.employeeComboBox.FormattingEnabled = true;
            this.employeeComboBox.Location = new System.Drawing.Point(537, 94);
            this.employeeComboBox.Name = "employeeComboBox";
            this.employeeComboBox.Size = new System.Drawing.Size(332, 24);
            this.employeeComboBox.TabIndex = 58;
            this.employeeComboBox.ValueMember = "id";
            this.employeeComboBox.SelectedIndexChanged += new System.EventHandler(this.employeeComboBox_SelectedIndexChanged);
            // 
            // employeeBindingSource
            // 
            this.employeeBindingSource.DataMember = "Employee";
            this.employeeBindingSource.DataSource = this.clothesDataSet;
            // 
            // btnCancelImport
            // 
            this.btnCancelImport.BackColor = System.Drawing.Color.White;
            this.btnCancelImport.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelImport.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelImport.Location = new System.Drawing.Point(489, 166);
            this.btnCancelImport.Name = "btnCancelImport";
            this.btnCancelImport.Size = new System.Drawing.Size(123, 52);
            this.btnCancelImport.TabIndex = 58;
            this.btnCancelImport.Text = "Cancel";
            this.btnCancelImport.UseVisualStyleBackColor = false;
            this.btnCancelImport.Click += new System.EventHandler(this.btnCancelImport_Click);
            // 
            // btnSaveIport
            // 
            this.btnSaveIport.BackColor = System.Drawing.Color.White;
            this.btnSaveIport.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveIport.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveIport.Location = new System.Drawing.Point(271, 166);
            this.btnSaveIport.Name = "btnSaveIport";
            this.btnSaveIport.Size = new System.Drawing.Size(123, 52);
            this.btnSaveIport.TabIndex = 57;
            this.btnSaveIport.Text = "Save";
            this.btnSaveIport.UseVisualStyleBackColor = false;
            this.btnSaveIport.Click += new System.EventHandler(this.btnSaveIport_Click);
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.importCouponBindingSource, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Enabled = false;
            this.idSpinEdit.Location = new System.Drawing.Point(173, 40);
            this.idSpinEdit.MenuManager = this.barManager;
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 1;
            // 
            // employeeIdSpinEdit
            // 
            this.employeeIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.importCouponBindingSource, "employeeId", true));
            this.employeeIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.employeeIdSpinEdit.Enabled = false;
            this.employeeIdSpinEdit.Location = new System.Drawing.Point(173, 93);
            this.employeeIdSpinEdit.MenuManager = this.barManager;
            this.employeeIdSpinEdit.Name = "employeeIdSpinEdit";
            this.employeeIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.employeeIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.employeeIdSpinEdit.TabIndex = 5;
            this.employeeIdSpinEdit.EditValueChanged += new System.EventHandler(this.employeeIdSpinEdit_EditValueChanged);
            // 
            // panel5
            // 
            this.panel5.ContextMenuStrip = this.contextMenuStrip1;
            this.panel5.Controls.Add(colorIdLabel);
            this.panel5.Controls.Add(this.colorIdTextBox);
            this.panel5.Controls.Add(this.colorComboBox);
            this.panel5.Controls.Add(this.sizeComboBox);
            this.panel5.Controls.Add(sizeIDLabel);
            this.panel5.Controls.Add(this.sizeIDTextBox);
            this.panel5.Controls.Add(this.productComboBox);
            this.panel5.Controls.Add(this.btnCancelAddItem);
            this.panel5.Controls.Add(this.btnSaveAddItem);
            this.panel5.Controls.Add(idCouponLabel);
            this.panel5.Controls.Add(this.idCouponSpinEdit);
            this.panel5.Controls.Add(idProductLabel);
            this.panel5.Controls.Add(this.idProductSpinEdit);
            this.panel5.Controls.Add(label5);
            this.panel5.Controls.Add(label4);
            this.panel5.Controls.Add(label1);
            this.panel5.Controls.Add(quantityLabel);
            this.panel5.Controls.Add(this.quantitySpinEdit);
            this.panel5.Controls.Add(priceLabel);
            this.panel5.Controls.Add(this.priceSpinEdit);
            this.panel5.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel5.Location = new System.Drawing.Point(973, 404);
            this.panel5.Name = "panel5";
            this.panel5.Size = new System.Drawing.Size(963, 393);
            this.panel5.TabIndex = 3;
            // 
            // sizeComboBox
            // 
            this.sizeComboBox.DataSource = this.sizeBindingSource;
            this.sizeComboBox.DisplayMember = "sizeName";
            this.sizeComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.sizeComboBox.FormattingEnabled = true;
            this.sizeComboBox.Location = new System.Drawing.Point(188, 113);
            this.sizeComboBox.Name = "sizeComboBox";
            this.sizeComboBox.Size = new System.Drawing.Size(376, 24);
            this.sizeComboBox.TabIndex = 59;
            this.sizeComboBox.ValueMember = "id";
            this.sizeComboBox.SelectedIndexChanged += new System.EventHandler(this.sizeComboBox_SelectedIndexChanged);
            // 
            // sizeBindingSource
            // 
            this.sizeBindingSource.DataMember = "Size";
            this.sizeBindingSource.DataSource = this.clothesDataSet;
            // 
            // sizeIDTextBox
            // 
            this.sizeIDTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.importCouponDetailBindingSource, "sizeID", true));
            this.sizeIDTextBox.Enabled = false;
            this.sizeIDTextBox.Location = new System.Drawing.Point(729, 113);
            this.sizeIDTextBox.Name = "sizeIDTextBox";
            this.sizeIDTextBox.Size = new System.Drawing.Size(125, 22);
            this.sizeIDTextBox.TabIndex = 59;
            this.sizeIDTextBox.TextChanged += new System.EventHandler(this.sizeIDTextBox_TextChanged);
            // 
            // productComboBox
            // 
            this.productComboBox.DataSource = this.productBindingSource;
            this.productComboBox.DisplayMember = "title";
            this.productComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.productComboBox.FormattingEnabled = true;
            this.productComboBox.Location = new System.Drawing.Point(186, 67);
            this.productComboBox.Name = "productComboBox";
            this.productComboBox.Size = new System.Drawing.Size(378, 24);
            this.productComboBox.TabIndex = 58;
            this.productComboBox.ValueMember = "id";
            this.productComboBox.SelectedIndexChanged += new System.EventHandler(this.productComboBox_SelectedIndexChanged);
            // 
            // productBindingSource
            // 
            this.productBindingSource.DataMember = "Product";
            this.productBindingSource.DataSource = this.clothesDataSet;
            // 
            // btnCancelAddItem
            // 
            this.btnCancelAddItem.BackColor = System.Drawing.Color.White;
            this.btnCancelAddItem.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddItem.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddItem.Location = new System.Drawing.Point(443, 274);
            this.btnCancelAddItem.Name = "btnCancelAddItem";
            this.btnCancelAddItem.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddItem.TabIndex = 58;
            this.btnCancelAddItem.Text = "Cancel";
            this.btnCancelAddItem.UseVisualStyleBackColor = false;
            this.btnCancelAddItem.Click += new System.EventHandler(this.btnCancelAddItem_Click);
            // 
            // btnSaveAddItem
            // 
            this.btnSaveAddItem.BackColor = System.Drawing.Color.White;
            this.btnSaveAddItem.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddItem.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddItem.Location = new System.Drawing.Point(217, 274);
            this.btnSaveAddItem.Name = "btnSaveAddItem";
            this.btnSaveAddItem.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddItem.TabIndex = 57;
            this.btnSaveAddItem.Text = "Save";
            this.btnSaveAddItem.UseVisualStyleBackColor = false;
            this.btnSaveAddItem.Click += new System.EventHandler(this.btnSaveAddItem_Click);
            // 
            // idCouponSpinEdit
            // 
            this.idCouponSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.importCouponDetailBindingSource, "idCoupon", true));
            this.idCouponSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idCouponSpinEdit.Enabled = false;
            this.idCouponSpinEdit.Location = new System.Drawing.Point(188, 21);
            this.idCouponSpinEdit.MenuManager = this.barManager;
            this.idCouponSpinEdit.Name = "idCouponSpinEdit";
            this.idCouponSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idCouponSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idCouponSpinEdit.TabIndex = 1;
            // 
            // idProductSpinEdit
            // 
            this.idProductSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.importCouponDetailBindingSource, "idProduct", true));
            this.idProductSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idProductSpinEdit.Enabled = false;
            this.idProductSpinEdit.Location = new System.Drawing.Point(729, 67);
            this.idProductSpinEdit.MenuManager = this.barManager;
            this.idProductSpinEdit.Name = "idProductSpinEdit";
            this.idProductSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idProductSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idProductSpinEdit.TabIndex = 3;
            this.idProductSpinEdit.EditValueChanged += new System.EventHandler(this.idProductSpinEdit_EditValueChanged);
            // 
            // quantitySpinEdit
            // 
            this.quantitySpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.importCouponDetailBindingSource, "quantity", true));
            this.quantitySpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.quantitySpinEdit.Location = new System.Drawing.Point(186, 211);
            this.quantitySpinEdit.MenuManager = this.barManager;
            this.quantitySpinEdit.Name = "quantitySpinEdit";
            this.quantitySpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.quantitySpinEdit.Size = new System.Drawing.Size(125, 24);
            this.quantitySpinEdit.TabIndex = 5;
            // 
            // priceSpinEdit
            // 
            this.priceSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.importCouponDetailBindingSource, "price", true));
            this.priceSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.priceSpinEdit.Location = new System.Drawing.Point(658, 211);
            this.priceSpinEdit.MenuManager = this.barManager;
            this.priceSpinEdit.Name = "priceSpinEdit";
            this.priceSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.priceSpinEdit.Size = new System.Drawing.Size(196, 24);
            this.priceSpinEdit.TabIndex = 7;
            // 
            // importCouponTableAdapter
            // 
            this.importCouponTableAdapter.ClearBeforeFill = true;
            // 
            // tableAdapterManager
            // 
            this.tableAdapterManager.AccountTableAdapter = null;
            this.tableAdapterManager.AddressTableAdapter = null;
            this.tableAdapterManager.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager.CategoryTableAdapter = null;
            this.tableAdapterManager.ColorTableAdapter = null;
            this.tableAdapterManager.CustomerTableAdapter = null;
            this.tableAdapterManager.EmployeeTableAdapter = this.employeeTableAdapter;
            this.tableAdapterManager.FavoriteProductTableAdapter = null;
            this.tableAdapterManager.ImageTableAdapter = null;
            this.tableAdapterManager.ImportCouponDetailTableAdapter = this.importCouponDetailTableAdapter;
            this.tableAdapterManager.ImportCouponTableAdapter = this.importCouponTableAdapter;
            this.tableAdapterManager.InvoiceItemTableAdapter = null;
            this.tableAdapterManager.InvoiceTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = this.productTableAdapter;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // employeeTableAdapter
            // 
            this.employeeTableAdapter.ClearBeforeFill = true;
            // 
            // importCouponDetailTableAdapter
            // 
            this.importCouponDetailTableAdapter.ClearBeforeFill = true;
            // 
            // productTableAdapter
            // 
            this.productTableAdapter.ClearBeforeFill = true;
            // 
            // sizeTableAdapter
            // 
            this.sizeTableAdapter.ClearBeforeFill = true;
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new System.Drawing.Point(82, 116);
            label4.Name = "label4";
            label4.Size = new System.Drawing.Size(35, 17);
            label4.TabIndex = 4;
            label4.Text = "Size";
            // 
            // colorBindingSource
            // 
            this.colorBindingSource.DataMember = "Color";
            this.colorBindingSource.DataSource = this.clothesDataSet;
            // 
            // colorTableAdapter
            // 
            this.colorTableAdapter.ClearBeforeFill = true;
            // 
            // colorComboBox
            // 
            this.colorComboBox.DataSource = this.colorBindingSource;
            this.colorComboBox.DisplayMember = "colorName";
            this.colorComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.colorComboBox.FormattingEnabled = true;
            this.colorComboBox.Location = new System.Drawing.Point(190, 162);
            this.colorComboBox.Name = "colorComboBox";
            this.colorComboBox.Size = new System.Drawing.Size(374, 24);
            this.colorComboBox.TabIndex = 59;
            this.colorComboBox.ValueMember = "id";
            this.colorComboBox.SelectedIndexChanged += new System.EventHandler(this.colorComboBox_SelectedIndexChanged);
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new System.Drawing.Point(84, 165);
            label5.Name = "label5";
            label5.Size = new System.Drawing.Size(41, 17);
            label5.TabIndex = 4;
            label5.Text = "Color";
            // 
            // colorIdLabel
            // 
            colorIdLabel.AutoSize = true;
            colorIdLabel.Location = new System.Drawing.Point(625, 165);
            colorIdLabel.Name = "colorIdLabel";
            colorIdLabel.Size = new System.Drawing.Size(58, 17);
            colorIdLabel.TabIndex = 59;
            colorIdLabel.Text = "Color ID";
            // 
            // colorIdTextBox
            // 
            this.colorIdTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.importCouponDetailBindingSource, "colorId", true));
            this.colorIdTextBox.Enabled = false;
            this.colorIdTextBox.Location = new System.Drawing.Point(729, 162);
            this.colorIdTextBox.Name = "colorIdTextBox";
            this.colorIdTextBox.Size = new System.Drawing.Size(125, 22);
            this.colorIdTextBox.TabIndex = 60;
            this.colorIdTextBox.TextChanged += new System.EventHandler(this.colorIdTextBox_TextChanged);
            // 
            // ImportCuponForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1940, 902);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "ImportCuponForm";
            this.Text = "ImportCuponForm";
            this.Load += new System.EventHandler(this.ImportCuponForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.employeeBindingSource1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.importCouponGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailGridControl)).EndInit();
            this.contextMenuStrip1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).EndInit();
            this.panel4.ResumeLayout(false);
            this.panel4.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dateDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeeIdSpinEdit.Properties)).EndInit();
            this.panel5.ResumeLayout(false);
            this.panel5.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idCouponSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idProductSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.quantitySpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.priceSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorBindingSource)).EndInit();
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
        private System.Windows.Forms.BindingSource importCouponBindingSource;
        private ClothesDataSet clothesDataSet;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.Panel panel5;
        private System.Windows.Forms.Panel panel1;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private ClothesDataSetTableAdapters.ImportCouponTableAdapter importCouponTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private ClothesDataSetTableAdapters.ImportCouponDetailTableAdapter importCouponDetailTableAdapter;
        private System.Windows.Forms.BindingSource importCouponDetailBindingSource;
        private DevExpress.XtraGrid.GridControl importCouponDetailGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView2;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.SpinEdit employeeIdSpinEdit;
        private DevExpress.XtraEditors.SpinEdit idCouponSpinEdit;
        private DevExpress.XtraEditors.SpinEdit idProductSpinEdit;
        private DevExpress.XtraEditors.SpinEdit quantitySpinEdit;
        private DevExpress.XtraEditors.SpinEdit priceSpinEdit;
        private System.Windows.Forms.Button btnCancelImport;
        private System.Windows.Forms.Button btnSaveIport;
        private System.Windows.Forms.Button btnCancelAddItem;
        private System.Windows.Forms.Button btnSaveAddItem;
        private ClothesDataSetTableAdapters.ProductTableAdapter productTableAdapter;
        private System.Windows.Forms.BindingSource productBindingSource;
        private System.Windows.Forms.ComboBox productComboBox;
        private ClothesDataSetTableAdapters.EmployeeTableAdapter employeeTableAdapter;
        private System.Windows.Forms.BindingSource employeeBindingSource;
        private System.Windows.Forms.ComboBox employeeComboBox;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem addProductPromoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem deleteProductPromoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem reloadToolStripMenuItem;
        private DevExpress.XtraGrid.GridControl importCouponGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colid;
        private DevExpress.XtraGrid.Columns.GridColumn coldate;
        private DevExpress.XtraGrid.Columns.GridColumn colemployeeId;
        private DevExpress.XtraEditors.DateEdit dateDateEdit;
        private System.Windows.Forms.ComboBox employeeComboBox1;
        private System.Windows.Forms.BindingSource employeeBindingSource1;
        private DevExpress.XtraGrid.Columns.GridColumn colidCoupon;
        private DevExpress.XtraGrid.Columns.GridColumn colidProduct;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorId;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeID;
        private DevExpress.XtraGrid.Columns.GridColumn colquantity;
        private DevExpress.XtraGrid.Columns.GridColumn colprice;
        private System.Windows.Forms.TextBox sizeIDTextBox;
        private System.Windows.Forms.BindingSource sizeBindingSource;
        private ClothesDataSetTableAdapters.SizeTableAdapter sizeTableAdapter;
        private System.Windows.Forms.ComboBox sizeComboBox;
        private System.Windows.Forms.BindingSource colorBindingSource;
        private ClothesDataSetTableAdapters.ColorTableAdapter colorTableAdapter;
        private System.Windows.Forms.TextBox colorIdTextBox;
        private System.Windows.Forms.ComboBox colorComboBox;
    }
}