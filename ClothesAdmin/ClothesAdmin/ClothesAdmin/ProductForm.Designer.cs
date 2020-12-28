namespace ClothesAdmin
{
    partial class ProductForm
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
            System.Windows.Forms.Label label6;
            System.Windows.Forms.Label label5;
            System.Windows.Forms.Label lbCategory;
            System.Windows.Forms.Label lbProvider;
            System.Windows.Forms.Label c;
            System.Windows.Forms.Label soldLabel;
            System.Windows.Forms.Label addDateLabel;
            System.Windows.Forms.Label isNewLabel;
            System.Windows.Forms.Label thumnailLabel;
            System.Windows.Forms.Label providerIdLabel;
            System.Windows.Forms.Label activeLabel;
            System.Windows.Forms.Label ratingLabel;
            System.Windows.Forms.Label categoryIDLabel;
            System.Windows.Forms.Label priceLabel;
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label titleLabel;
            System.Windows.Forms.Label idLabel;
            System.Windows.Forms.Label detailLabel;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ProductForm));
            this.defaultLookAndFeel1 = new DevExpress.LookAndFeel.DefaultLookAndFeel(this.components);
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
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.providerBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.categoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.productTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.barManager1 = new DevExpress.XtraBars.BarManager(this.components);
            this.barDockControl4 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl6 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl7 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControlRight = new DevExpress.XtraBars.BarDockControl();
            this.barButtonItem1 = new DevExpress.XtraBars.BarButtonItem();
            this.barButtonItem2 = new DevExpress.XtraBars.BarButtonItem();
            this.barButtonItem3 = new DevExpress.XtraBars.BarButtonItem();
            this.barButtonItem4 = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControl8 = new DevExpress.XtraBars.BarDockControl();
            this.favoriteProductBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.favoriteProductTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.FavoriteProductTableAdapter();
            this.imageBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.imageTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ImageTableAdapter();
            this.importCouponDetailBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.importCouponDetailTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ImportCouponDetailTableAdapter();
            this.productSizeColorBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.productSizeColorTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductSizeColorTableAdapter();
            this.promotionItemBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.promotionItemTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.PromotionItemTableAdapter();
            this.categoryTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.CategoryTableAdapter();
            this.providerTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProviderTableAdapter();
            this.panel1 = new System.Windows.Forms.Panel();
            this.cbProvider = new System.Windows.Forms.CheckBox();
            this.cbCategory = new System.Windows.Forms.CheckBox();
            this.cbbProvider = new System.Windows.Forms.ComboBox();
            this.providerBds = new System.Windows.Forms.BindingSource(this.components);
            this.cbbCategory = new System.Windows.Forms.ComboBox();
            this.categoryBds = new System.Windows.Forms.BindingSource(this.components);
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.productGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coltitle = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldetail = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colprice = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcategoryID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colrating = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colactive = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colproviderId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colthumnail = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colisNew = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coladdDate = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsold = new DevExpress.XtraGrid.Columns.GridColumn();
            this.panel2 = new System.Windows.Forms.Panel();
            this.isNewSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.activeSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.btnSaveImg = new System.Windows.Forms.Button();
            this.btnImageList = new System.Windows.Forms.Button();
            this.btnChangeImg = new System.Windows.Forms.LinkLabel();
            this.providerComboBox = new System.Windows.Forms.ComboBox();
            this.categoryComboBox = new System.Windows.Forms.ComboBox();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSizeColor = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.thumbnailProduct = new System.Windows.Forms.PictureBox();
            this.detailRichTextBox = new System.Windows.Forms.RichTextBox();
            this.addDateDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.idTextBox = new System.Windows.Forms.TextBox();
            this.titleTextBox = new System.Windows.Forms.TextBox();
            this.priceTextBox = new System.Windows.Forms.TextBox();
            this.categoryIDTextBox = new System.Windows.Forms.TextBox();
            this.ratingTextBox = new System.Windows.Forms.TextBox();
            this.providerIdTextBox = new System.Windows.Forms.TextBox();
            this.thumnailTextBox = new System.Windows.Forms.TextBox();
            this.soldTextBox = new System.Windows.Forms.TextBox();
            label6 = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            lbCategory = new System.Windows.Forms.Label();
            lbProvider = new System.Windows.Forms.Label();
            c = new System.Windows.Forms.Label();
            soldLabel = new System.Windows.Forms.Label();
            addDateLabel = new System.Windows.Forms.Label();
            isNewLabel = new System.Windows.Forms.Label();
            thumnailLabel = new System.Windows.Forms.Label();
            providerIdLabel = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            ratingLabel = new System.Windows.Forms.Label();
            categoryIDLabel = new System.Windows.Forms.Label();
            priceLabel = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            titleLabel = new System.Windows.Forms.Label();
            idLabel = new System.Windows.Forms.Label();
            detailLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.favoriteProductBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.promotionItemBindingSource)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.providerBds)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBds)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.productGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.isNewSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumbnailProduct)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties)).BeginInit();
            this.SuspendLayout();
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.Location = new System.Drawing.Point(93, 27);
            label6.Name = "label6";
            label6.Size = new System.Drawing.Size(65, 17);
            label6.TabIndex = 75;
            label6.Text = "Category";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new System.Drawing.Point(756, 27);
            label5.Name = "label5";
            label5.Size = new System.Drawing.Size(61, 17);
            label5.TabIndex = 76;
            label5.Text = "Provider";
            // 
            // lbCategory
            // 
            lbCategory.AutoSize = true;
            lbCategory.Location = new System.Drawing.Point(512, 27);
            lbCategory.Name = "lbCategory";
            lbCategory.Size = new System.Drawing.Size(0, 17);
            lbCategory.TabIndex = 81;
            // 
            // lbProvider
            // 
            lbProvider.AutoSize = true;
            lbProvider.Location = new System.Drawing.Point(1171, 28);
            lbProvider.Name = "lbProvider";
            lbProvider.Size = new System.Drawing.Size(0, 17);
            lbProvider.TabIndex = 82;
            // 
            // c
            // 
            c.AutoSize = true;
            c.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            c.Location = new System.Drawing.Point(47, 21);
            c.Name = "c";
            c.Size = new System.Drawing.Size(212, 36);
            c.TabIndex = 25;
            c.Text = "Product detail";
            // 
            // soldLabel
            // 
            soldLabel.AutoSize = true;
            soldLabel.Location = new System.Drawing.Point(49, 405);
            soldLabel.Name = "soldLabel";
            soldLabel.Size = new System.Drawing.Size(38, 17);
            soldLabel.TabIndex = 47;
            soldLabel.Text = "sold:";
            // 
            // addDateLabel
            // 
            addDateLabel.AutoSize = true;
            addDateLabel.Location = new System.Drawing.Point(49, 366);
            addDateLabel.Name = "addDateLabel";
            addDateLabel.Size = new System.Drawing.Size(70, 17);
            addDateLabel.TabIndex = 45;
            addDateLabel.Text = "add Date:";
            // 
            // isNewLabel
            // 
            isNewLabel.AutoSize = true;
            isNewLabel.Location = new System.Drawing.Point(50, 326);
            isNewLabel.Name = "isNewLabel";
            isNewLabel.Size = new System.Drawing.Size(53, 17);
            isNewLabel.TabIndex = 43;
            isNewLabel.Text = "is New:";
            // 
            // thumnailLabel
            // 
            thumnailLabel.AutoSize = true;
            thumnailLabel.Location = new System.Drawing.Point(50, 287);
            thumnailLabel.Name = "thumnailLabel";
            thumnailLabel.Size = new System.Drawing.Size(74, 17);
            thumnailLabel.TabIndex = 41;
            thumnailLabel.Text = "Thumbnail";
            // 
            // providerIdLabel
            // 
            providerIdLabel.AutoSize = true;
            providerIdLabel.Location = new System.Drawing.Point(50, 251);
            providerIdLabel.Name = "providerIdLabel";
            providerIdLabel.Size = new System.Drawing.Size(61, 17);
            providerIdLabel.TabIndex = 39;
            providerIdLabel.Text = "Provider";
            // 
            // activeLabel
            // 
            activeLabel.AutoSize = true;
            activeLabel.Location = new System.Drawing.Point(349, 365);
            activeLabel.Name = "activeLabel";
            activeLabel.Size = new System.Drawing.Size(49, 17);
            activeLabel.TabIndex = 37;
            activeLabel.Text = "active:";
            // 
            // ratingLabel
            // 
            ratingLabel.AutoSize = true;
            ratingLabel.Location = new System.Drawing.Point(349, 405);
            ratingLabel.Name = "ratingLabel";
            ratingLabel.Size = new System.Drawing.Size(48, 17);
            ratingLabel.TabIndex = 35;
            ratingLabel.Text = "rating:";
            // 
            // categoryIDLabel
            // 
            categoryIDLabel.AutoSize = true;
            categoryIDLabel.Location = new System.Drawing.Point(50, 207);
            categoryIDLabel.Name = "categoryIDLabel";
            categoryIDLabel.Size = new System.Drawing.Size(65, 17);
            categoryIDLabel.TabIndex = 33;
            categoryIDLabel.Text = "Category";
            // 
            // priceLabel
            // 
            priceLabel.AutoSize = true;
            priceLabel.Location = new System.Drawing.Point(50, 168);
            priceLabel.Name = "priceLabel";
            priceLabel.Size = new System.Drawing.Size(40, 17);
            priceLabel.TabIndex = 31;
            priceLabel.Text = "Price";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(372, 168);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(33, 17);
            label1.TabIndex = 31;
            label1.Text = "Vnđ";
            // 
            // titleLabel
            // 
            titleLabel.AutoSize = true;
            titleLabel.Location = new System.Drawing.Point(50, 132);
            titleLabel.Name = "titleLabel";
            titleLabel.Size = new System.Drawing.Size(35, 17);
            titleLabel.TabIndex = 27;
            titleLabel.Text = "Title";
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(50, 90);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(23, 17);
            idLabel.TabIndex = 25;
            idLabel.Text = "id:";
            // 
            // detailLabel
            // 
            detailLabel.AutoSize = true;
            detailLabel.Location = new System.Drawing.Point(52, 448);
            detailLabel.Name = "detailLabel";
            detailLabel.Size = new System.Drawing.Size(46, 17);
            detailLabel.TabIndex = 49;
            detailLabel.Text = "detail:";
            // 
            // defaultLookAndFeel1
            // 
            this.defaultLookAndFeel1.LookAndFeel.SkinName = "Office 2019 Colorful";
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
            this.btnAddProvider.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnAddProvider.ImageOptions.Image")));
            this.btnAddProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnAddProvider.ImageOptions.LargeImage")));
            this.btnAddProvider.Name = "btnAddProvider";
            this.btnAddProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnAddProvider_ItemClick);
            // 
            // btnDelProvider
            // 
            this.btnDelProvider.Caption = "Delete";
            this.btnDelProvider.Id = 2;
            this.btnDelProvider.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnDelProvider.ImageOptions.Image")));
            this.btnDelProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnDelProvider.ImageOptions.LargeImage")));
            this.btnDelProvider.Name = "btnDelProvider";
            this.btnDelProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnDelProvider_ItemClick);
            // 
            // btnReloadProvider
            // 
            this.btnReloadProvider.Caption = "Reload";
            this.btnReloadProvider.Id = 5;
            this.btnReloadProvider.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnReloadProvider.ImageOptions.Image")));
            this.btnReloadProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnReloadProvider.ImageOptions.LargeImage")));
            this.btnReloadProvider.Name = "btnReloadProvider";
            this.btnReloadProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnReloadProvider_ItemClick);
            // 
            // btnCloseForm
            // 
            this.btnCloseForm.Caption = "Close form";
            this.btnCloseForm.Id = 10;
            this.btnCloseForm.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnCloseForm.ImageOptions.Image")));
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
            this.barDockControl1.Size = new System.Drawing.Size(1872, 33);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 902);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1872, 0);
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
            this.barDockControl5.Location = new System.Drawing.Point(1872, 33);
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
            // productBindingSource
            // 
            this.productBindingSource.DataMember = "Product";
            this.productBindingSource.DataSource = this.clothesDataSet;
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // providerBindingSource
            // 
            this.providerBindingSource.DataMember = "Provider";
            this.providerBindingSource.DataSource = this.clothesDataSet;
            // 
            // categoryBindingSource
            // 
            this.categoryBindingSource.DataMember = "Category";
            this.categoryBindingSource.DataSource = this.clothesDataSet;
            // 
            // productTableAdapter
            // 
            this.productTableAdapter.ClearBeforeFill = true;
            // 
            // tableAdapterManager
            // 
            this.tableAdapterManager.AccountTableAdapter = null;
            this.tableAdapterManager.AddressTableAdapter = null;
            this.tableAdapterManager.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager.CategoryTableAdapter = null;
            this.tableAdapterManager.ColorTableAdapter = null;
            this.tableAdapterManager.CustomerTableAdapter = null;
            this.tableAdapterManager.EmployeeTableAdapter = null;
            this.tableAdapterManager.FavoriteProductTableAdapter = null;
            this.tableAdapterManager.ImageTableAdapter = null;
            this.tableAdapterManager.ImportCouponDetailTableAdapter = null;
            this.tableAdapterManager.ImportCouponTableAdapter = null;
            this.tableAdapterManager.InvoiceItemTableAdapter = null;
            this.tableAdapterManager.InvoiceStatusTableAdapter = null;
            this.tableAdapterManager.InvoiceTableAdapter = null;
            this.tableAdapterManager.NotificationDetailTableAdapter = null;
            this.tableAdapterManager.NotificationTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = this.productTableAdapter;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.QuestionTableAdapter = null;
            this.tableAdapterManager.RatingTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.ShopDataTableAdapter = null;
            this.tableAdapterManager.ShopInfoTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.TypeNotiTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // barManager1
            // 
            this.barManager1.DockControls.Add(this.barDockControl4);
            this.barManager1.DockControls.Add(this.barDockControl6);
            this.barManager1.DockControls.Add(this.barDockControl7);
            this.barManager1.DockControls.Add(this.barDockControlRight);
            this.barManager1.Form = this;
            this.barManager1.Items.AddRange(new DevExpress.XtraBars.BarItem[] {
            this.barButtonItem1,
            this.barButtonItem2,
            this.barButtonItem3,
            this.barButtonItem4});
            // 
            // barDockControl4
            // 
            this.barDockControl4.CausesValidation = false;
            this.barDockControl4.Dock = System.Windows.Forms.DockStyle.Top;
            this.barDockControl4.Location = new System.Drawing.Point(0, 0);
            this.barDockControl4.Manager = this.barManager1;
            this.barDockControl4.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl4.Size = new System.Drawing.Size(1872, 0);
            // 
            // barDockControl6
            // 
            this.barDockControl6.CausesValidation = false;
            this.barDockControl6.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl6.Location = new System.Drawing.Point(0, 902);
            this.barDockControl6.Manager = this.barManager1;
            this.barDockControl6.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl6.Size = new System.Drawing.Size(1872, 0);
            // 
            // barDockControl7
            // 
            this.barDockControl7.CausesValidation = false;
            this.barDockControl7.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl7.Location = new System.Drawing.Point(0, 0);
            this.barDockControl7.Manager = this.barManager1;
            this.barDockControl7.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl7.Size = new System.Drawing.Size(0, 902);
            // 
            // barDockControlRight
            // 
            this.barDockControlRight.CausesValidation = false;
            this.barDockControlRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControlRight.Location = new System.Drawing.Point(1872, 0);
            this.barDockControlRight.Manager = this.barManager1;
            this.barDockControlRight.Size = new System.Drawing.Size(0, 902);
            // 
            // barButtonItem1
            // 
            this.barButtonItem1.Caption = "Add new";
            this.barButtonItem1.Id = 0;
            this.barButtonItem1.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barButtonItem1.ImageOptions.Image")));
            this.barButtonItem1.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barButtonItem1.ImageOptions.LargeImage")));
            this.barButtonItem1.Name = "barButtonItem1";
            // 
            // barButtonItem2
            // 
            this.barButtonItem2.Caption = "Delete";
            this.barButtonItem2.Id = 2;
            this.barButtonItem2.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barButtonItem2.ImageOptions.Image")));
            this.barButtonItem2.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barButtonItem2.ImageOptions.LargeImage")));
            this.barButtonItem2.Name = "barButtonItem2";
            // 
            // barButtonItem3
            // 
            this.barButtonItem3.Caption = "Reload";
            this.barButtonItem3.Id = 5;
            this.barButtonItem3.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barButtonItem3.ImageOptions.Image")));
            this.barButtonItem3.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("barButtonItem3.ImageOptions.LargeImage")));
            this.barButtonItem3.Name = "barButtonItem3";
            // 
            // barButtonItem4
            // 
            this.barButtonItem4.Caption = "Close form";
            this.barButtonItem4.Id = 10;
            this.barButtonItem4.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barButtonItem4.ImageOptions.Image")));
            this.barButtonItem4.Name = "barButtonItem4";
            // 
            // barDockControl8
            // 
            this.barDockControl8.CausesValidation = false;
            this.barDockControl8.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl8.Location = new System.Drawing.Point(1872, 33);
            this.barDockControl8.Manager = this.barManager1;
            this.barDockControl8.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl8.Size = new System.Drawing.Size(0, 869);
            // 
            // favoriteProductBindingSource
            // 
            this.favoriteProductBindingSource.DataMember = "FK_FavoriteProduct_Product";
            this.favoriteProductBindingSource.DataSource = this.productBindingSource;
            // 
            // favoriteProductTableAdapter
            // 
            this.favoriteProductTableAdapter.ClearBeforeFill = true;
            // 
            // imageBindingSource
            // 
            this.imageBindingSource.DataMember = "FK_Image_Product";
            this.imageBindingSource.DataSource = this.productBindingSource;
            // 
            // imageTableAdapter
            // 
            this.imageTableAdapter.ClearBeforeFill = true;
            // 
            // importCouponDetailBindingSource
            // 
            this.importCouponDetailBindingSource.DataMember = "FK_ImportCouponDetail_Product";
            this.importCouponDetailBindingSource.DataSource = this.productBindingSource;
            // 
            // importCouponDetailTableAdapter
            // 
            this.importCouponDetailTableAdapter.ClearBeforeFill = true;
            // 
            // productSizeColorBindingSource
            // 
            this.productSizeColorBindingSource.DataMember = "FK_ProductSizeColor_Product1";
            this.productSizeColorBindingSource.DataSource = this.productBindingSource;
            // 
            // productSizeColorTableAdapter
            // 
            this.productSizeColorTableAdapter.ClearBeforeFill = true;
            // 
            // promotionItemBindingSource
            // 
            this.promotionItemBindingSource.DataMember = "FK_PromotionItem_Product";
            this.promotionItemBindingSource.DataSource = this.productBindingSource;
            // 
            // promotionItemTableAdapter
            // 
            this.promotionItemTableAdapter.ClearBeforeFill = true;
            // 
            // categoryTableAdapter
            // 
            this.categoryTableAdapter.ClearBeforeFill = true;
            // 
            // providerTableAdapter
            // 
            this.providerTableAdapter.ClearBeforeFill = true;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(lbProvider);
            this.panel1.Controls.Add(lbCategory);
            this.panel1.Controls.Add(this.cbProvider);
            this.panel1.Controls.Add(this.cbCategory);
            this.panel1.Controls.Add(this.cbbProvider);
            this.panel1.Controls.Add(this.cbbCategory);
            this.panel1.Controls.Add(label6);
            this.panel1.Controls.Add(label5);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 33);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1872, 61);
            this.panel1.TabIndex = 14;
            // 
            // cbProvider
            // 
            this.cbProvider.AutoSize = true;
            this.cbProvider.Location = new System.Drawing.Point(732, 28);
            this.cbProvider.Name = "cbProvider";
            this.cbProvider.Size = new System.Drawing.Size(18, 17);
            this.cbProvider.TabIndex = 80;
            this.cbProvider.UseVisualStyleBackColor = true;
            this.cbProvider.CheckedChanged += new System.EventHandler(this.cbProvider_CheckedChanged);
            // 
            // cbCategory
            // 
            this.cbCategory.AutoSize = true;
            this.cbCategory.Location = new System.Drawing.Point(69, 28);
            this.cbCategory.Name = "cbCategory";
            this.cbCategory.Size = new System.Drawing.Size(18, 17);
            this.cbCategory.TabIndex = 79;
            this.cbCategory.UseVisualStyleBackColor = true;
            this.cbCategory.CheckedChanged += new System.EventHandler(this.cbCategory_CheckedChanged);
            // 
            // cbbProvider
            // 
            this.cbbProvider.DataSource = this.providerBds;
            this.cbbProvider.DisplayMember = "brandName";
            this.cbbProvider.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbProvider.FormattingEnabled = true;
            this.cbbProvider.Location = new System.Drawing.Point(851, 24);
            this.cbbProvider.Name = "cbbProvider";
            this.cbbProvider.Size = new System.Drawing.Size(300, 24);
            this.cbbProvider.TabIndex = 77;
            this.cbbProvider.ValueMember = "id";
            this.cbbProvider.SelectedIndexChanged += new System.EventHandler(this.cbbProvider_SelectedIndexChanged);
            // 
            // providerBds
            // 
            this.providerBds.DataMember = "Provider";
            this.providerBds.DataSource = this.clothesDataSet;
            // 
            // cbbCategory
            // 
            this.cbbCategory.DataSource = this.categoryBds;
            this.cbbCategory.DisplayMember = "name";
            this.cbbCategory.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbCategory.FormattingEnabled = true;
            this.cbbCategory.Location = new System.Drawing.Point(180, 24);
            this.cbbCategory.Name = "cbbCategory";
            this.cbbCategory.Size = new System.Drawing.Size(313, 24);
            this.cbbCategory.TabIndex = 78;
            this.cbbCategory.ValueMember = "id";
            this.cbbCategory.SelectedIndexChanged += new System.EventHandler(this.cbbCategory_SelectedIndexChanged);
            // 
            // categoryBds
            // 
            this.categoryBds.DataMember = "Category";
            this.categoryBds.DataSource = this.clothesDataSet;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 47.49702F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 52.50298F));
            this.tableLayoutPanel1.Controls.Add(this.productGridControl, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panel2, 1, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 94);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1872, 808);
            this.tableLayoutPanel1.TabIndex = 15;
            // 
            // productGridControl
            // 
            this.productGridControl.DataSource = this.productBindingSource;
            this.productGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.productGridControl.Location = new System.Drawing.Point(3, 3);
            this.productGridControl.MainView = this.gridView1;
            this.productGridControl.MenuManager = this.barManager;
            this.productGridControl.Name = "productGridControl";
            this.productGridControl.Size = new System.Drawing.Size(883, 802);
            this.productGridControl.TabIndex = 1;
            this.productGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colid,
            this.coltitle,
            this.coldetail,
            this.colprice,
            this.colcategoryID,
            this.colrating,
            this.colactive,
            this.colproviderId,
            this.colthumnail,
            this.colisNew,
            this.coladdDate,
            this.colsold});
            this.gridView1.CustomizationFormBounds = new System.Drawing.Rectangle(1606, 705, 314, 325);
            this.gridView1.GridControl = this.productGridControl;
            this.gridView1.Name = "gridView1";
            this.gridView1.SortInfo.AddRange(new DevExpress.XtraGrid.Columns.GridColumnSortInfo[] {
            new DevExpress.XtraGrid.Columns.GridColumnSortInfo(this.colsold, DevExpress.Data.ColumnSortOrder.Descending)});
            // 
            // colid
            // 
            this.colid.FieldName = "id";
            this.colid.MinWidth = 25;
            this.colid.Name = "colid";
            this.colid.OptionsColumn.AllowEdit = false;
            this.colid.Visible = true;
            this.colid.VisibleIndex = 0;
            this.colid.Width = 87;
            // 
            // coltitle
            // 
            this.coltitle.FieldName = "title";
            this.coltitle.MinWidth = 25;
            this.coltitle.Name = "coltitle";
            this.coltitle.OptionsColumn.AllowEdit = false;
            this.coltitle.Visible = true;
            this.coltitle.VisibleIndex = 1;
            this.coltitle.Width = 164;
            // 
            // coldetail
            // 
            this.coldetail.FieldName = "detail";
            this.coldetail.MinWidth = 25;
            this.coldetail.Name = "coldetail";
            this.coldetail.OptionsColumn.AllowEdit = false;
            this.coldetail.Visible = true;
            this.coldetail.VisibleIndex = 2;
            this.coldetail.Width = 71;
            // 
            // colprice
            // 
            this.colprice.FieldName = "price";
            this.colprice.MinWidth = 25;
            this.colprice.Name = "colprice";
            this.colprice.OptionsColumn.AllowEdit = false;
            this.colprice.Visible = true;
            this.colprice.VisibleIndex = 3;
            this.colprice.Width = 66;
            // 
            // colcategoryID
            // 
            this.colcategoryID.FieldName = "categoryID";
            this.colcategoryID.MinWidth = 25;
            this.colcategoryID.Name = "colcategoryID";
            this.colcategoryID.OptionsColumn.AllowEdit = false;
            this.colcategoryID.Visible = true;
            this.colcategoryID.VisibleIndex = 4;
            this.colcategoryID.Width = 66;
            // 
            // colrating
            // 
            this.colrating.FieldName = "rating";
            this.colrating.MinWidth = 25;
            this.colrating.Name = "colrating";
            this.colrating.OptionsColumn.AllowEdit = false;
            this.colrating.Visible = true;
            this.colrating.VisibleIndex = 5;
            this.colrating.Width = 66;
            // 
            // colactive
            // 
            this.colactive.FieldName = "active";
            this.colactive.MinWidth = 25;
            this.colactive.Name = "colactive";
            this.colactive.OptionsColumn.AllowEdit = false;
            this.colactive.Visible = true;
            this.colactive.VisibleIndex = 6;
            this.colactive.Width = 66;
            // 
            // colproviderId
            // 
            this.colproviderId.FieldName = "providerId";
            this.colproviderId.MinWidth = 25;
            this.colproviderId.Name = "colproviderId";
            this.colproviderId.OptionsColumn.AllowEdit = false;
            this.colproviderId.Visible = true;
            this.colproviderId.VisibleIndex = 7;
            this.colproviderId.Width = 83;
            // 
            // colthumnail
            // 
            this.colthumnail.FieldName = "thumnail";
            this.colthumnail.MinWidth = 25;
            this.colthumnail.Name = "colthumnail";
            this.colthumnail.OptionsColumn.AllowEdit = false;
            this.colthumnail.Visible = true;
            this.colthumnail.VisibleIndex = 8;
            this.colthumnail.Width = 90;
            // 
            // colisNew
            // 
            this.colisNew.FieldName = "isNew";
            this.colisNew.MinWidth = 25;
            this.colisNew.Name = "colisNew";
            this.colisNew.OptionsColumn.AllowEdit = false;
            this.colisNew.Visible = true;
            this.colisNew.VisibleIndex = 9;
            this.colisNew.Width = 34;
            // 
            // coladdDate
            // 
            this.coladdDate.FieldName = "addDate";
            this.coladdDate.MinWidth = 25;
            this.coladdDate.Name = "coladdDate";
            this.coladdDate.OptionsColumn.AllowEdit = false;
            this.coladdDate.Visible = true;
            this.coladdDate.VisibleIndex = 10;
            this.coladdDate.Width = 60;
            // 
            // colsold
            // 
            this.colsold.FieldName = "sold";
            this.colsold.MinWidth = 25;
            this.colsold.Name = "colsold";
            this.colsold.OptionsColumn.AllowEdit = false;
            this.colsold.Visible = true;
            this.colsold.VisibleIndex = 11;
            this.colsold.Width = 74;
            // 
            // panel2
            // 
            this.panel2.AutoScroll = true;
            this.panel2.Controls.Add(this.isNewSpinEdit);
            this.panel2.Controls.Add(this.activeSpinEdit);
            this.panel2.Controls.Add(this.btnSaveImg);
            this.panel2.Controls.Add(this.btnImageList);
            this.panel2.Controls.Add(this.btnChangeImg);
            this.panel2.Controls.Add(this.providerComboBox);
            this.panel2.Controls.Add(this.categoryComboBox);
            this.panel2.Controls.Add(this.btnCancelAddProvider);
            this.panel2.Controls.Add(this.btnSizeColor);
            this.panel2.Controls.Add(this.btnSaveAddProvider);
            this.panel2.Controls.Add(this.thumbnailProduct);
            this.panel2.Controls.Add(detailLabel);
            this.panel2.Controls.Add(this.detailRichTextBox);
            this.panel2.Controls.Add(this.addDateDateEdit);
            this.panel2.Controls.Add(idLabel);
            this.panel2.Controls.Add(this.idTextBox);
            this.panel2.Controls.Add(titleLabel);
            this.panel2.Controls.Add(this.titleTextBox);
            this.panel2.Controls.Add(label1);
            this.panel2.Controls.Add(priceLabel);
            this.panel2.Controls.Add(this.priceTextBox);
            this.panel2.Controls.Add(categoryIDLabel);
            this.panel2.Controls.Add(this.categoryIDTextBox);
            this.panel2.Controls.Add(ratingLabel);
            this.panel2.Controls.Add(this.ratingTextBox);
            this.panel2.Controls.Add(activeLabel);
            this.panel2.Controls.Add(providerIdLabel);
            this.panel2.Controls.Add(this.providerIdTextBox);
            this.panel2.Controls.Add(thumnailLabel);
            this.panel2.Controls.Add(this.thumnailTextBox);
            this.panel2.Controls.Add(isNewLabel);
            this.panel2.Controls.Add(addDateLabel);
            this.panel2.Controls.Add(soldLabel);
            this.panel2.Controls.Add(this.soldTextBox);
            this.panel2.Controls.Add(c);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel2.Location = new System.Drawing.Point(892, 3);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(977, 802);
            this.panel2.TabIndex = 0;
            // 
            // isNewSpinEdit
            // 
            this.isNewSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.productBindingSource, "isNew", true));
            this.isNewSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.isNewSpinEdit.Location = new System.Drawing.Point(140, 322);
            this.isNewSpinEdit.MenuManager = this.barManager;
            this.isNewSpinEdit.Name = "isNewSpinEdit";
            this.isNewSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.isNewSpinEdit.Size = new System.Drawing.Size(118, 24);
            this.isNewSpinEdit.TabIndex = 64;
            // 
            // activeSpinEdit
            // 
            this.activeSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.productBindingSource, "active", true));
            this.activeSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.activeSpinEdit.Location = new System.Drawing.Point(428, 362);
            this.activeSpinEdit.MenuManager = this.barManager;
            this.activeSpinEdit.Name = "activeSpinEdit";
            this.activeSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.activeSpinEdit.Size = new System.Drawing.Size(105, 24);
            this.activeSpinEdit.TabIndex = 63;
            // 
            // btnSaveImg
            // 
            this.btnSaveImg.BackColor = System.Drawing.Color.White;
            this.btnSaveImg.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveImg.ForeColor = System.Drawing.Color.DarkBlue;
            this.btnSaveImg.Location = new System.Drawing.Point(788, 514);
            this.btnSaveImg.Name = "btnSaveImg";
            this.btnSaveImg.Size = new System.Drawing.Size(147, 52);
            this.btnSaveImg.TabIndex = 62;
            this.btnSaveImg.Text = "Save and get link";
            this.btnSaveImg.UseVisualStyleBackColor = false;
            this.btnSaveImg.Click += new System.EventHandler(this.btnSaveImg_Click_1);
            // 
            // btnImageList
            // 
            this.btnImageList.BackColor = System.Drawing.Color.White;
            this.btnImageList.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnImageList.ForeColor = System.Drawing.Color.DarkGreen;
            this.btnImageList.ImageAlign = System.Drawing.ContentAlignment.BottomCenter;
            this.btnImageList.Location = new System.Drawing.Point(570, 593);
            this.btnImageList.Name = "btnImageList";
            this.btnImageList.Size = new System.Drawing.Size(171, 63);
            this.btnImageList.TabIndex = 61;
            this.btnImageList.Text = "Image list";
            this.btnImageList.UseVisualStyleBackColor = false;
            this.btnImageList.Click += new System.EventHandler(this.btnImageList_Click_1);
            // 
            // btnChangeImg
            // 
            this.btnChangeImg.AutoSize = true;
            this.btnChangeImg.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnChangeImg.LinkColor = System.Drawing.SystemColors.MenuHighlight;
            this.btnChangeImg.Location = new System.Drawing.Point(604, 525);
            this.btnChangeImg.Name = "btnChangeImg";
            this.btnChangeImg.Size = new System.Drawing.Size(151, 25);
            this.btnChangeImg.TabIndex = 60;
            this.btnChangeImg.TabStop = true;
            this.btnChangeImg.Text = "Choose image";
            this.btnChangeImg.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.btnChangeImg_LinkClicked_1);
            // 
            // providerComboBox
            // 
            this.providerComboBox.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.productBindingSource, "providerId", true));
            this.providerComboBox.DataSource = this.providerBindingSource;
            this.providerComboBox.DisplayMember = "brandName";
            this.providerComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.providerComboBox.FormattingEnabled = true;
            this.providerComboBox.Location = new System.Drawing.Point(140, 248);
            this.providerComboBox.Name = "providerComboBox";
            this.providerComboBox.Size = new System.Drawing.Size(258, 24);
            this.providerComboBox.TabIndex = 59;
            this.providerComboBox.ValueMember = "id";
            this.providerComboBox.SelectedIndexChanged += new System.EventHandler(this.providerComboBox_SelectedIndexChanged_1);
            // 
            // categoryComboBox
            // 
            this.categoryComboBox.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.productBindingSource, "categoryID", true));
            this.categoryComboBox.DataSource = this.categoryBindingSource;
            this.categoryComboBox.DisplayMember = "name";
            this.categoryComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.categoryComboBox.FormattingEnabled = true;
            this.categoryComboBox.Location = new System.Drawing.Point(139, 204);
            this.categoryComboBox.Name = "categoryComboBox";
            this.categoryComboBox.Size = new System.Drawing.Size(258, 24);
            this.categoryComboBox.TabIndex = 59;
            this.categoryComboBox.ValueMember = "id";
            this.categoryComboBox.SelectedIndexChanged += new System.EventHandler(this.categoryComboBox_SelectedIndexChanged_1);
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(362, 599);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 58;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            this.btnCancelAddProvider.Click += new System.EventHandler(this.btnCancelAddProvider_Click_1);
            // 
            // btnSizeColor
            // 
            this.btnSizeColor.BackColor = System.Drawing.Color.White;
            this.btnSizeColor.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSizeColor.ForeColor = System.Drawing.Color.DarkViolet;
            this.btnSizeColor.ImageAlign = System.Drawing.ContentAlignment.BottomCenter;
            this.btnSizeColor.Location = new System.Drawing.Point(764, 593);
            this.btnSizeColor.Name = "btnSizeColor";
            this.btnSizeColor.Size = new System.Drawing.Size(171, 63);
            this.btnSizeColor.TabIndex = 57;
            this.btnSizeColor.Text = "Size and color";
            this.btnSizeColor.UseVisualStyleBackColor = false;
            this.btnSizeColor.Click += new System.EventHandler(this.btnSizeColor_Click_1);
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(144, 599);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 57;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click_1);
            // 
            // thumbnailProduct
            // 
            this.thumbnailProduct.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.thumbnailProduct.ErrorImage = ((System.Drawing.Image)(resources.GetObject("thumbnailProduct.ErrorImage")));
            this.thumbnailProduct.Location = new System.Drawing.Point(584, 21);
            this.thumbnailProduct.Name = "thumbnailProduct";
            this.thumbnailProduct.Size = new System.Drawing.Size(351, 469);
            this.thumbnailProduct.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.thumbnailProduct.TabIndex = 51;
            this.thumbnailProduct.TabStop = false;
            // 
            // detailRichTextBox
            // 
            this.detailRichTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "detail", true));
            this.detailRichTextBox.Location = new System.Drawing.Point(139, 445);
            this.detailRichTextBox.Name = "detailRichTextBox";
            this.detailRichTextBox.Size = new System.Drawing.Size(393, 121);
            this.detailRichTextBox.TabIndex = 50;
            this.detailRichTextBox.Text = "";
            // 
            // addDateDateEdit
            // 
            this.addDateDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.productBindingSource, "addDate", true));
            this.addDateDateEdit.EditValue = null;
            this.addDateDateEdit.Enabled = false;
            this.addDateDateEdit.Location = new System.Drawing.Point(139, 362);
            this.addDateDateEdit.MenuManager = this.barManager;
            this.addDateDateEdit.Name = "addDateDateEdit";
            this.addDateDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.addDateDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.addDateDateEdit.Size = new System.Drawing.Size(119, 22);
            this.addDateDateEdit.TabIndex = 49;
            // 
            // idTextBox
            // 
            this.idTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "id", true));
            this.idTextBox.Enabled = false;
            this.idTextBox.Location = new System.Drawing.Point(140, 87);
            this.idTextBox.Name = "idTextBox";
            this.idTextBox.Size = new System.Drawing.Size(119, 22);
            this.idTextBox.TabIndex = 26;
            // 
            // titleTextBox
            // 
            this.titleTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "title", true));
            this.titleTextBox.Location = new System.Drawing.Point(140, 129);
            this.titleTextBox.Name = "titleTextBox";
            this.titleTextBox.Size = new System.Drawing.Size(393, 22);
            this.titleTextBox.TabIndex = 28;
            // 
            // priceTextBox
            // 
            this.priceTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "price", true));
            this.priceTextBox.Location = new System.Drawing.Point(140, 165);
            this.priceTextBox.Name = "priceTextBox";
            this.priceTextBox.Size = new System.Drawing.Size(200, 22);
            this.priceTextBox.TabIndex = 32;
            // 
            // categoryIDTextBox
            // 
            this.categoryIDTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "categoryID", true));
            this.categoryIDTextBox.Enabled = false;
            this.categoryIDTextBox.Location = new System.Drawing.Point(428, 204);
            this.categoryIDTextBox.Name = "categoryIDTextBox";
            this.categoryIDTextBox.Size = new System.Drawing.Size(104, 22);
            this.categoryIDTextBox.TabIndex = 34;
            // 
            // ratingTextBox
            // 
            this.ratingTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "rating", true));
            this.ratingTextBox.Enabled = false;
            this.ratingTextBox.Location = new System.Drawing.Point(428, 402);
            this.ratingTextBox.Name = "ratingTextBox";
            this.ratingTextBox.Size = new System.Drawing.Size(104, 22);
            this.ratingTextBox.TabIndex = 36;
            // 
            // providerIdTextBox
            // 
            this.providerIdTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "providerId", true));
            this.providerIdTextBox.Location = new System.Drawing.Point(428, 248);
            this.providerIdTextBox.Name = "providerIdTextBox";
            this.providerIdTextBox.Size = new System.Drawing.Size(105, 22);
            this.providerIdTextBox.TabIndex = 40;
            // 
            // thumnailTextBox
            // 
            this.thumnailTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "thumnail", true));
            this.thumnailTextBox.Location = new System.Drawing.Point(140, 284);
            this.thumnailTextBox.Name = "thumnailTextBox";
            this.thumnailTextBox.Size = new System.Drawing.Size(392, 22);
            this.thumnailTextBox.TabIndex = 42;
            this.thumnailTextBox.TextChanged += new System.EventHandler(this.thumnailTextBox_TextChanged_1);
            // 
            // soldTextBox
            // 
            this.soldTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productBindingSource, "sold", true));
            this.soldTextBox.Enabled = false;
            this.soldTextBox.Location = new System.Drawing.Point(139, 402);
            this.soldTextBox.Name = "soldTextBox";
            this.soldTextBox.Size = new System.Drawing.Size(120, 22);
            this.soldTextBox.TabIndex = 48;
            // 
            // ProductForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1872, 902);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControl8);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Controls.Add(this.barDockControl7);
            this.Controls.Add(this.barDockControlRight);
            this.Controls.Add(this.barDockControl6);
            this.Controls.Add(this.barDockControl4);
            this.Name = "ProductForm";
            this.Text = "Product";
            this.Load += new System.EventHandler(this.ProductForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.favoriteProductBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.promotionItemBindingSource)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.providerBds)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBds)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.productGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.isNewSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumbnailProduct)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.LookAndFeel.DefaultLookAndFeel defaultLookAndFeel1;
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
        private System.Windows.Forms.BindingSource productBindingSource;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.ProductTableAdapter productTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraBars.BarDockControl barDockControl8;
        private DevExpress.XtraBars.BarManager barManager1;
        private DevExpress.XtraBars.BarButtonItem barButtonItem1;
        private DevExpress.XtraBars.BarButtonItem barButtonItem2;
        private DevExpress.XtraBars.BarButtonItem barButtonItem3;
        private DevExpress.XtraBars.BarButtonItem barButtonItem4;
        private DevExpress.XtraBars.BarDockControl barDockControl4;
        private DevExpress.XtraBars.BarDockControl barDockControl6;
        private DevExpress.XtraBars.BarDockControl barDockControl7;
        private DevExpress.XtraBars.BarDockControl barDockControlRight;
        private System.Windows.Forms.BindingSource favoriteProductBindingSource;
        private ClothesDataSetTableAdapters.FavoriteProductTableAdapter favoriteProductTableAdapter;
        private System.Windows.Forms.BindingSource imageBindingSource;
        private ClothesDataSetTableAdapters.ImageTableAdapter imageTableAdapter;
        private System.Windows.Forms.BindingSource importCouponDetailBindingSource;
        private ClothesDataSetTableAdapters.ImportCouponDetailTableAdapter importCouponDetailTableAdapter;
        private System.Windows.Forms.BindingSource productSizeColorBindingSource;
        private ClothesDataSetTableAdapters.ProductSizeColorTableAdapter productSizeColorTableAdapter;
        private System.Windows.Forms.BindingSource promotionItemBindingSource;
        private ClothesDataSetTableAdapters.PromotionItemTableAdapter promotionItemTableAdapter;
        private System.Windows.Forms.BindingSource categoryBindingSource;
        private ClothesDataSetTableAdapters.CategoryTableAdapter categoryTableAdapter;
        private System.Windows.Forms.BindingSource providerBindingSource;
        private ClothesDataSetTableAdapters.ProviderTableAdapter providerTableAdapter;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private DevExpress.XtraGrid.GridControl productGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.CheckBox cbProvider;
        private System.Windows.Forms.CheckBox cbCategory;
        private System.Windows.Forms.ComboBox cbbProvider;
        private System.Windows.Forms.ComboBox cbbCategory;
        private System.Windows.Forms.BindingSource categoryBds;
        private System.Windows.Forms.BindingSource providerBds;
        private DevExpress.XtraGrid.Columns.GridColumn colid;
        private DevExpress.XtraGrid.Columns.GridColumn coltitle;
        private DevExpress.XtraGrid.Columns.GridColumn coldetail;
        private DevExpress.XtraGrid.Columns.GridColumn colprice;
        private DevExpress.XtraGrid.Columns.GridColumn colcategoryID;
        private DevExpress.XtraGrid.Columns.GridColumn colrating;
        private DevExpress.XtraGrid.Columns.GridColumn colactive;
        private DevExpress.XtraGrid.Columns.GridColumn colproviderId;
        private DevExpress.XtraGrid.Columns.GridColumn colthumnail;
        private DevExpress.XtraGrid.Columns.GridColumn colisNew;
        private DevExpress.XtraGrid.Columns.GridColumn coladdDate;
        private DevExpress.XtraGrid.Columns.GridColumn colsold;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button btnSaveImg;
        private System.Windows.Forms.Button btnImageList;
        private System.Windows.Forms.LinkLabel btnChangeImg;
        private System.Windows.Forms.ComboBox providerComboBox;
        private System.Windows.Forms.ComboBox categoryComboBox;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSizeColor;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private System.Windows.Forms.PictureBox thumbnailProduct;
        private System.Windows.Forms.RichTextBox detailRichTextBox;
        private DevExpress.XtraEditors.DateEdit addDateDateEdit;
        private System.Windows.Forms.TextBox idTextBox;
        private System.Windows.Forms.TextBox titleTextBox;
        private System.Windows.Forms.TextBox priceTextBox;
        private System.Windows.Forms.TextBox categoryIDTextBox;
        private System.Windows.Forms.TextBox ratingTextBox;
        private System.Windows.Forms.TextBox providerIdTextBox;
        private System.Windows.Forms.TextBox thumnailTextBox;
        private System.Windows.Forms.TextBox soldTextBox;
        private DevExpress.XtraEditors.SpinEdit isNewSpinEdit;
        private DevExpress.XtraEditors.SpinEdit activeSpinEdit;
    }
}