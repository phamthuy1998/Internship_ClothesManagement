namespace ClothesAdmin
{
    partial class SizeColorForm
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
            System.Windows.Forms.Label productIDLabel;
            System.Windows.Forms.Label titleLabel;
            System.Windows.Forms.Label sizeIdLabel;
            System.Windows.Forms.Label sizeNameLabel;
            System.Windows.Forms.Label colorIDLabel;
            System.Windows.Forms.Label colorNameLabel;
            System.Windows.Forms.Label colorHexLabel;
            System.Windows.Forms.Label nameLabel;
            System.Windows.Forms.Label brandNameLabel;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SizeColorForm));
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
            this.productSizeColorBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.productSizeColorTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductSizeColorTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.categoryTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.CategoryTableAdapter();
            this.categoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.providerBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.providerTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProviderTableAdapter();
            this.label2 = new System.Windows.Forms.Label();
            this.sP_GetProductColorSizeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sP_GetProductColorSizeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_GetProductColorSizeTableAdapter();
            this.cbbProvider = new System.Windows.Forms.ComboBox();
            this.cbbCategory = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.panelControl1 = new DevExpress.XtraEditors.PanelControl();
            this.panelControl2 = new DevExpress.XtraEditors.PanelControl();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.productIDSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.titleTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.sizeIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.sizeNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.colorIDSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.colorNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.colorHexTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.nameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.brandNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.panelControl3 = new DevExpress.XtraEditors.PanelControl();
            this.panelControl4 = new DevExpress.XtraEditors.PanelControl();
            this.sP_GetProductColorSizeGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colproductID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coltitle = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsizeId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsizeName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcolorID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcolorName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcolorHex = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colname = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colbrandName = new DevExpress.XtraGrid.Columns.GridColumn();
            productIDLabel = new System.Windows.Forms.Label();
            titleLabel = new System.Windows.Forms.Label();
            sizeIdLabel = new System.Windows.Forms.Label();
            sizeNameLabel = new System.Windows.Forms.Label();
            colorIDLabel = new System.Windows.Forms.Label();
            colorNameLabel = new System.Windows.Forms.Label();
            colorHexLabel = new System.Windows.Forms.Label();
            nameLabel = new System.Windows.Forms.Label();
            brandNameLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductColorSizeBindingSource)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).BeginInit();
            this.panelControl1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl2)).BeginInit();
            this.panelControl2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.productIDSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.titleTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorIDSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorHexTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.brandNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl4)).BeginInit();
            this.panelControl4.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductColorSizeGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // productIDLabel
            // 
            productIDLabel.AutoSize = true;
            productIDLabel.Location = new System.Drawing.Point(100, 84);
            productIDLabel.Name = "productIDLabel";
            productIDLabel.Size = new System.Drawing.Size(77, 17);
            productIDLabel.TabIndex = 0;
            productIDLabel.Text = "product ID:";
            // 
            // titleLabel
            // 
            titleLabel.AutoSize = true;
            titleLabel.Location = new System.Drawing.Point(100, 112);
            titleLabel.Name = "titleLabel";
            titleLabel.Size = new System.Drawing.Size(34, 17);
            titleLabel.TabIndex = 2;
            titleLabel.Text = "title:";
            // 
            // sizeIdLabel
            // 
            sizeIdLabel.AutoSize = true;
            sizeIdLabel.Location = new System.Drawing.Point(100, 140);
            sizeIdLabel.Name = "sizeIdLabel";
            sizeIdLabel.Size = new System.Drawing.Size(52, 17);
            sizeIdLabel.TabIndex = 4;
            sizeIdLabel.Text = "size Id:";
            // 
            // sizeNameLabel
            // 
            sizeNameLabel.AutoSize = true;
            sizeNameLabel.Location = new System.Drawing.Point(100, 168);
            sizeNameLabel.Name = "sizeNameLabel";
            sizeNameLabel.Size = new System.Drawing.Size(78, 17);
            sizeNameLabel.TabIndex = 6;
            sizeNameLabel.Text = "size Name:";
            // 
            // colorIDLabel
            // 
            colorIDLabel.AutoSize = true;
            colorIDLabel.Location = new System.Drawing.Point(100, 196);
            colorIDLabel.Name = "colorIDLabel";
            colorIDLabel.Size = new System.Drawing.Size(60, 17);
            colorIDLabel.TabIndex = 8;
            colorIDLabel.Text = "color ID:";
            // 
            // colorNameLabel
            // 
            colorNameLabel.AutoSize = true;
            colorNameLabel.Location = new System.Drawing.Point(100, 224);
            colorNameLabel.Name = "colorNameLabel";
            colorNameLabel.Size = new System.Drawing.Size(84, 17);
            colorNameLabel.TabIndex = 10;
            colorNameLabel.Text = "color Name:";
            // 
            // colorHexLabel
            // 
            colorHexLabel.AutoSize = true;
            colorHexLabel.Location = new System.Drawing.Point(100, 252);
            colorHexLabel.Name = "colorHexLabel";
            colorHexLabel.Size = new System.Drawing.Size(71, 17);
            colorHexLabel.TabIndex = 12;
            colorHexLabel.Text = "color Hex:";
            // 
            // nameLabel
            // 
            nameLabel.AutoSize = true;
            nameLabel.Location = new System.Drawing.Point(100, 280);
            nameLabel.Name = "nameLabel";
            nameLabel.Size = new System.Drawing.Size(47, 17);
            nameLabel.TabIndex = 14;
            nameLabel.Text = "name:";
            // 
            // brandNameLabel
            // 
            brandNameLabel.AutoSize = true;
            brandNameLabel.Location = new System.Drawing.Point(100, 308);
            brandNameLabel.Name = "brandNameLabel";
            brandNameLabel.Size = new System.Drawing.Size(90, 17);
            brandNameLabel.TabIndex = 16;
            brandNameLabel.Text = "brand Name:";
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
            // 
            // btnReloadProvider
            // 
            this.btnReloadProvider.Caption = "Reload";
            this.btnReloadProvider.Id = 5;
            this.btnReloadProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.exchange;
            this.btnReloadProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnReloadProvider.ImageOptions.LargeImage")));
            this.btnReloadProvider.Name = "btnReloadProvider";
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
            this.barDockControl2.Location = new System.Drawing.Point(0, 606);
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
            this.barDockControl3.Size = new System.Drawing.Size(0, 576);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1932, 30);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 576);
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
            // productSizeColorBindingSource
            // 
            this.productSizeColorBindingSource.DataMember = "ProductSizeColor";
            this.productSizeColorBindingSource.DataSource = this.clothesDataSet;
            // 
            // productSizeColorTableAdapter
            // 
            this.productSizeColorTableAdapter.ClearBeforeFill = true;
            // 
            // tableAdapterManager
            // 
            this.tableAdapterManager.AccountTableAdapter = null;
            this.tableAdapterManager.AddressTableAdapter = null;
            this.tableAdapterManager.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager.CategoryTableAdapter = this.categoryTableAdapter;
            this.tableAdapterManager.ColorTableAdapter = null;
            this.tableAdapterManager.CustomerTableAdapter = null;
            this.tableAdapterManager.EmployeeTableAdapter = null;
            this.tableAdapterManager.FavoriteProductTableAdapter = null;
            this.tableAdapterManager.ImageTableAdapter = null;
            this.tableAdapterManager.ImportCouponDetailTableAdapter = null;
            this.tableAdapterManager.ImportCouponTableAdapter = null;
            this.tableAdapterManager.InvoiceItemTableAdapter = null;
            this.tableAdapterManager.InvoiceTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = this.productSizeColorTableAdapter;
            this.tableAdapterManager.ProductTableAdapter = null;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // categoryTableAdapter
            // 
            this.categoryTableAdapter.ClearBeforeFill = true;
            // 
            // categoryBindingSource
            // 
            this.categoryBindingSource.DataMember = "Category";
            this.categoryBindingSource.DataSource = this.clothesDataSet;
            // 
            // providerBindingSource
            // 
            this.providerBindingSource.DataMember = "Provider";
            this.providerBindingSource.DataSource = this.clothesDataSet;
            // 
            // providerTableAdapter
            // 
            this.providerTableAdapter.ClearBeforeFill = true;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(238, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(61, 17);
            this.label2.TabIndex = 9;
            this.label2.Text = "Provider";
            // 
            // sP_GetProductColorSizeBindingSource
            // 
            this.sP_GetProductColorSizeBindingSource.DataMember = "SP_GetProductColorSize";
            this.sP_GetProductColorSizeBindingSource.DataSource = this.clothesDataSet;
            // 
            // sP_GetProductColorSizeTableAdapter
            // 
            this.sP_GetProductColorSizeTableAdapter.ClearBeforeFill = true;
            // 
            // cbbProvider
            // 
            this.cbbProvider.DataSource = this.providerBindingSource;
            this.cbbProvider.DisplayMember = "brandName";
            this.cbbProvider.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbProvider.FormattingEnabled = true;
            this.cbbProvider.Location = new System.Drawing.Point(112, 82);
            this.cbbProvider.Name = "cbbProvider";
            this.cbbProvider.Size = new System.Drawing.Size(300, 24);
            this.cbbProvider.TabIndex = 8;
            this.cbbProvider.ValueMember = "id";
            this.cbbProvider.SelectedIndexChanged += new System.EventHandler(this.cbbProvider_SelectedIndexChanged);
            // 
            // cbbCategory
            // 
            this.cbbCategory.DataSource = this.categoryBindingSource;
            this.cbbCategory.DisplayMember = "name";
            this.cbbCategory.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbCategory.FormattingEnabled = true;
            this.cbbCategory.Location = new System.Drawing.Point(112, 35);
            this.cbbCategory.Name = "cbbCategory";
            this.cbbCategory.Size = new System.Drawing.Size(300, 24);
            this.cbbCategory.TabIndex = 4;
            this.cbbCategory.ValueMember = "id";
            this.cbbCategory.SelectedIndexChanged += new System.EventHandler(this.cbbCategory_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(28, 35);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(65, 17);
            this.label1.TabIndex = 9;
            this.label1.Text = "Category";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(32, 85);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(61, 17);
            this.label3.TabIndex = 9;
            this.label3.Text = "Provider";
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 62.03585F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 37.96415F));
            this.tableLayoutPanel1.Controls.Add(this.panelControl1, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panelControl2, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.panelControl3, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.panelControl4, 0, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 30);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 23.78472F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 76.21528F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1932, 576);
            this.tableLayoutPanel1.TabIndex = 19;
            // 
            // panelControl1
            // 
            this.panelControl1.Controls.Add(this.cbbProvider);
            this.panelControl1.Controls.Add(this.label1);
            this.panelControl1.Controls.Add(this.cbbCategory);
            this.panelControl1.Controls.Add(this.label3);
            this.panelControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelControl1.Location = new System.Drawing.Point(3, 3);
            this.panelControl1.Name = "panelControl1";
            this.panelControl1.Size = new System.Drawing.Size(1192, 130);
            this.panelControl1.TabIndex = 0;
            // 
            // panelControl2
            // 
            this.panelControl2.Controls.Add(this.btnCancelAddProvider);
            this.panelControl2.Controls.Add(this.btnSaveAddProvider);
            this.panelControl2.Controls.Add(productIDLabel);
            this.panelControl2.Controls.Add(this.productIDSpinEdit);
            this.panelControl2.Controls.Add(titleLabel);
            this.panelControl2.Controls.Add(this.titleTextEdit);
            this.panelControl2.Controls.Add(sizeIdLabel);
            this.panelControl2.Controls.Add(this.sizeIdSpinEdit);
            this.panelControl2.Controls.Add(sizeNameLabel);
            this.panelControl2.Controls.Add(this.sizeNameTextEdit);
            this.panelControl2.Controls.Add(colorIDLabel);
            this.panelControl2.Controls.Add(this.colorIDSpinEdit);
            this.panelControl2.Controls.Add(colorNameLabel);
            this.panelControl2.Controls.Add(this.colorNameTextEdit);
            this.panelControl2.Controls.Add(colorHexLabel);
            this.panelControl2.Controls.Add(this.colorHexTextEdit);
            this.panelControl2.Controls.Add(nameLabel);
            this.panelControl2.Controls.Add(this.nameTextEdit);
            this.panelControl2.Controls.Add(brandNameLabel);
            this.panelControl2.Controls.Add(this.brandNameTextEdit);
            this.panelControl2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelControl2.Location = new System.Drawing.Point(1201, 139);
            this.panelControl2.Name = "panelControl2";
            this.panelControl2.Size = new System.Drawing.Size(728, 434);
            this.panelControl2.TabIndex = 1;
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(573, 217);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 41;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(364, 217);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 40;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click);
            // 
            // productIDSpinEdit
            // 
            this.productIDSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "productID", true));
            this.productIDSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.productIDSpinEdit.Location = new System.Drawing.Point(196, 81);
            this.productIDSpinEdit.MenuManager = this.barManager;
            this.productIDSpinEdit.Name = "productIDSpinEdit";
            this.productIDSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.productIDSpinEdit.Size = new System.Drawing.Size(125, 22);
            this.productIDSpinEdit.TabIndex = 1;
            // 
            // titleTextEdit
            // 
            this.titleTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "title", true));
            this.titleTextEdit.Location = new System.Drawing.Point(196, 109);
            this.titleTextEdit.MenuManager = this.barManager;
            this.titleTextEdit.Name = "titleTextEdit";
            this.titleTextEdit.Size = new System.Drawing.Size(125, 22);
            this.titleTextEdit.TabIndex = 3;
            // 
            // sizeIdSpinEdit
            // 
            this.sizeIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "sizeId", true));
            this.sizeIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.sizeIdSpinEdit.Location = new System.Drawing.Point(196, 137);
            this.sizeIdSpinEdit.MenuManager = this.barManager;
            this.sizeIdSpinEdit.Name = "sizeIdSpinEdit";
            this.sizeIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.sizeIdSpinEdit.Size = new System.Drawing.Size(125, 22);
            this.sizeIdSpinEdit.TabIndex = 5;
            // 
            // sizeNameTextEdit
            // 
            this.sizeNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "sizeName", true));
            this.sizeNameTextEdit.Location = new System.Drawing.Point(196, 165);
            this.sizeNameTextEdit.MenuManager = this.barManager;
            this.sizeNameTextEdit.Name = "sizeNameTextEdit";
            this.sizeNameTextEdit.Size = new System.Drawing.Size(125, 22);
            this.sizeNameTextEdit.TabIndex = 7;
            // 
            // colorIDSpinEdit
            // 
            this.colorIDSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "colorID", true));
            this.colorIDSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.colorIDSpinEdit.Location = new System.Drawing.Point(196, 193);
            this.colorIDSpinEdit.MenuManager = this.barManager;
            this.colorIDSpinEdit.Name = "colorIDSpinEdit";
            this.colorIDSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.colorIDSpinEdit.Size = new System.Drawing.Size(125, 22);
            this.colorIDSpinEdit.TabIndex = 9;
            // 
            // colorNameTextEdit
            // 
            this.colorNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "colorName", true));
            this.colorNameTextEdit.Location = new System.Drawing.Point(196, 221);
            this.colorNameTextEdit.MenuManager = this.barManager;
            this.colorNameTextEdit.Name = "colorNameTextEdit";
            this.colorNameTextEdit.Size = new System.Drawing.Size(125, 22);
            this.colorNameTextEdit.TabIndex = 11;
            // 
            // colorHexTextEdit
            // 
            this.colorHexTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "colorHex", true));
            this.colorHexTextEdit.Location = new System.Drawing.Point(196, 249);
            this.colorHexTextEdit.MenuManager = this.barManager;
            this.colorHexTextEdit.Name = "colorHexTextEdit";
            this.colorHexTextEdit.Size = new System.Drawing.Size(125, 22);
            this.colorHexTextEdit.TabIndex = 13;
            // 
            // nameTextEdit
            // 
            this.nameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "name", true));
            this.nameTextEdit.Location = new System.Drawing.Point(196, 277);
            this.nameTextEdit.MenuManager = this.barManager;
            this.nameTextEdit.Name = "nameTextEdit";
            this.nameTextEdit.Size = new System.Drawing.Size(125, 22);
            this.nameTextEdit.TabIndex = 15;
            // 
            // brandNameTextEdit
            // 
            this.brandNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_GetProductColorSizeBindingSource, "brandName", true));
            this.brandNameTextEdit.Location = new System.Drawing.Point(196, 305);
            this.brandNameTextEdit.MenuManager = this.barManager;
            this.brandNameTextEdit.Name = "brandNameTextEdit";
            this.brandNameTextEdit.Size = new System.Drawing.Size(125, 22);
            this.brandNameTextEdit.TabIndex = 17;
            // 
            // panelControl3
            // 
            this.panelControl3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelControl3.Location = new System.Drawing.Point(1201, 3);
            this.panelControl3.Name = "panelControl3";
            this.panelControl3.Size = new System.Drawing.Size(728, 130);
            this.panelControl3.TabIndex = 2;
            // 
            // panelControl4
            // 
            this.panelControl4.Controls.Add(this.sP_GetProductColorSizeGridControl);
            this.panelControl4.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelControl4.Location = new System.Drawing.Point(3, 139);
            this.panelControl4.Name = "panelControl4";
            this.panelControl4.Size = new System.Drawing.Size(1192, 434);
            this.panelControl4.TabIndex = 3;
            // 
            // sP_GetProductColorSizeGridControl
            // 
            this.sP_GetProductColorSizeGridControl.DataSource = this.sP_GetProductColorSizeBindingSource;
            this.sP_GetProductColorSizeGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.sP_GetProductColorSizeGridControl.Location = new System.Drawing.Point(2, 2);
            this.sP_GetProductColorSizeGridControl.MainView = this.gridView1;
            this.sP_GetProductColorSizeGridControl.MenuManager = this.barManager;
            this.sP_GetProductColorSizeGridControl.Name = "sP_GetProductColorSizeGridControl";
            this.sP_GetProductColorSizeGridControl.Size = new System.Drawing.Size(1188, 430);
            this.sP_GetProductColorSizeGridControl.TabIndex = 0;
            this.sP_GetProductColorSizeGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colproductID,
            this.coltitle,
            this.colsizeId,
            this.colsizeName,
            this.colcolorID,
            this.colcolorName,
            this.colcolorHex,
            this.colname,
            this.colbrandName});
            this.gridView1.GridControl = this.sP_GetProductColorSizeGridControl;
            this.gridView1.Name = "gridView1";
            // 
            // colproductID
            // 
            this.colproductID.FieldName = "productID";
            this.colproductID.MinWidth = 25;
            this.colproductID.Name = "colproductID";
            this.colproductID.Visible = true;
            this.colproductID.VisibleIndex = 0;
            this.colproductID.Width = 25;
            // 
            // coltitle
            // 
            this.coltitle.FieldName = "title";
            this.coltitle.MinWidth = 25;
            this.coltitle.Name = "coltitle";
            this.coltitle.Visible = true;
            this.coltitle.VisibleIndex = 1;
            this.coltitle.Width = 170;
            // 
            // colsizeId
            // 
            this.colsizeId.FieldName = "sizeId";
            this.colsizeId.MinWidth = 25;
            this.colsizeId.Name = "colsizeId";
            this.colsizeId.Visible = true;
            this.colsizeId.VisibleIndex = 2;
            this.colsizeId.Width = 30;
            // 
            // colsizeName
            // 
            this.colsizeName.FieldName = "sizeName";
            this.colsizeName.MinWidth = 25;
            this.colsizeName.Name = "colsizeName";
            this.colsizeName.Visible = true;
            this.colsizeName.VisibleIndex = 3;
            this.colsizeName.Width = 48;
            // 
            // colcolorID
            // 
            this.colcolorID.FieldName = "colorID";
            this.colcolorID.MinWidth = 25;
            this.colcolorID.Name = "colcolorID";
            this.colcolorID.Visible = true;
            this.colcolorID.VisibleIndex = 4;
            this.colcolorID.Width = 34;
            // 
            // colcolorName
            // 
            this.colcolorName.FieldName = "colorName";
            this.colcolorName.MinWidth = 25;
            this.colcolorName.Name = "colcolorName";
            this.colcolorName.Visible = true;
            this.colcolorName.VisibleIndex = 5;
            this.colcolorName.Width = 111;
            // 
            // colcolorHex
            // 
            this.colcolorHex.FieldName = "colorHex";
            this.colcolorHex.MinWidth = 25;
            this.colcolorHex.Name = "colcolorHex";
            this.colcolorHex.Visible = true;
            this.colcolorHex.VisibleIndex = 6;
            this.colcolorHex.Width = 97;
            // 
            // colname
            // 
            this.colname.FieldName = "name";
            this.colname.MinWidth = 25;
            this.colname.Name = "colname";
            this.colname.OptionsColumn.AllowEdit = false;
            this.colname.Visible = true;
            this.colname.VisibleIndex = 7;
            this.colname.Width = 120;
            // 
            // colbrandName
            // 
            this.colbrandName.FieldName = "brandName";
            this.colbrandName.MinWidth = 25;
            this.colbrandName.Name = "colbrandName";
            this.colbrandName.OptionsColumn.AllowEdit = false;
            this.colbrandName.Visible = true;
            this.colbrandName.VisibleIndex = 8;
            this.colbrandName.Width = 150;
            // 
            // SizeColorForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1932, 606);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "SizeColorForm";
            this.Text = "SizeColorForm";
            this.Load += new System.EventHandler(this.SizeColorForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductColorSizeBindingSource)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.panelControl1)).EndInit();
            this.panelControl1.ResumeLayout(false);
            this.panelControl1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl2)).EndInit();
            this.panelControl2.ResumeLayout(false);
            this.panelControl2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.productIDSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.titleTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorIDSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorHexTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.brandNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.panelControl4)).EndInit();
            this.panelControl4.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductColorSizeGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
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
        private System.Windows.Forms.BindingSource productSizeColorBindingSource;
        private ClothesDataSet clothesDataSet;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private ClothesDataSetTableAdapters.ProductSizeColorTableAdapter productSizeColorTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private ClothesDataSetTableAdapters.CategoryTableAdapter categoryTableAdapter;
        private System.Windows.Forms.BindingSource categoryBindingSource;
        private System.Windows.Forms.BindingSource providerBindingSource;
        private ClothesDataSetTableAdapters.ProviderTableAdapter providerTableAdapter;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.BindingSource sP_GetProductColorSizeBindingSource;
        private ClothesDataSetTableAdapters.SP_GetProductColorSizeTableAdapter sP_GetProductColorSizeTableAdapter;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private DevExpress.XtraEditors.PanelControl panelControl1;
        private System.Windows.Forms.ComboBox cbbProvider;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox cbbCategory;
        private System.Windows.Forms.Label label3;
        private DevExpress.XtraEditors.PanelControl panelControl2;
        private DevExpress.XtraEditors.PanelControl panelControl3;
        private DevExpress.XtraEditors.PanelControl panelControl4;
        private DevExpress.XtraGrid.GridControl sP_GetProductColorSizeGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colproductID;
        private DevExpress.XtraGrid.Columns.GridColumn coltitle;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeId;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeName;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorID;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorName;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorHex;
        private DevExpress.XtraGrid.Columns.GridColumn colname;
        private DevExpress.XtraGrid.Columns.GridColumn colbrandName;
        private DevExpress.XtraEditors.SpinEdit productIDSpinEdit;
        private DevExpress.XtraEditors.TextEdit titleTextEdit;
        private DevExpress.XtraEditors.SpinEdit sizeIdSpinEdit;
        private DevExpress.XtraEditors.TextEdit sizeNameTextEdit;
        private DevExpress.XtraEditors.SpinEdit colorIDSpinEdit;
        private DevExpress.XtraEditors.TextEdit colorNameTextEdit;
        private DevExpress.XtraEditors.TextEdit colorHexTextEdit;
        private DevExpress.XtraEditors.TextEdit nameTextEdit;
        private DevExpress.XtraEditors.TextEdit brandNameTextEdit;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
    }
}