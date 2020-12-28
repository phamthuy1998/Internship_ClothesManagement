namespace ClothesAdmin
{
    partial class ProductNewForm
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
            System.Windows.Forms.Label lbProvider;
            System.Windows.Forms.Label lbCategory;
            System.Windows.Forms.Label label6;
            System.Windows.Forms.Label label5;
            System.Windows.Forms.Label soldLabel;
            System.Windows.Forms.Label addDateLabel;
            System.Windows.Forms.Label isNewLabel;
            System.Windows.Forms.Label thumnailLabel;
            System.Windows.Forms.Label brandNameLabel;
            System.Windows.Forms.Label providerIdLabel;
            System.Windows.Forms.Label activeLabel;
            System.Windows.Forms.Label ratingLabel;
            System.Windows.Forms.Label nameLabel;
            System.Windows.Forms.Label categoryIDLabel;
            System.Windows.Forms.Label priceLabel;
            System.Windows.Forms.Label detailLabel;
            System.Windows.Forms.Label titleLabel;
            System.Windows.Forms.Label idLabel;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ProductNewForm));
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
            this.cbProvider = new System.Windows.Forms.CheckBox();
            this.cbCategory = new System.Windows.Forms.CheckBox();
            this.cbbProvider = new System.Windows.Forms.ComboBox();
            this.cbbCategory = new System.Windows.Forms.ComboBox();
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.sP_GetProductTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_GetProductTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.panel3 = new System.Windows.Forms.Panel();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.titleTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.detailTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.priceSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.categoryIDSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.nameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.ratingSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.activeSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.providerIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.brandNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.thumnailTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.isNewSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.addDateDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.soldSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.panel2 = new System.Windows.Forms.Panel();
            this.sP_GetProductGridControl = new DevExpress.XtraGrid.GridControl();
            this.sP_GetProductBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.fillToolStrip = new System.Windows.Forms.ToolStrip();
            this.providerIdToolStripLabel = new System.Windows.Forms.ToolStripLabel();
            this.providerIdToolStripTextBox = new System.Windows.Forms.ToolStripTextBox();
            this.categoryIDToolStripLabel = new System.Windows.Forms.ToolStripLabel();
            this.categoryIDToolStripTextBox = new System.Windows.Forms.ToolStripTextBox();
            this.fillToolStripButton = new System.Windows.Forms.ToolStripButton();
            lbProvider = new System.Windows.Forms.Label();
            lbCategory = new System.Windows.Forms.Label();
            label6 = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            soldLabel = new System.Windows.Forms.Label();
            addDateLabel = new System.Windows.Forms.Label();
            isNewLabel = new System.Windows.Forms.Label();
            thumnailLabel = new System.Windows.Forms.Label();
            brandNameLabel = new System.Windows.Forms.Label();
            providerIdLabel = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            ratingLabel = new System.Windows.Forms.Label();
            nameLabel = new System.Windows.Forms.Label();
            categoryIDLabel = new System.Windows.Forms.Label();
            priceLabel = new System.Windows.Forms.Label();
            detailLabel = new System.Windows.Forms.Label();
            titleLabel = new System.Windows.Forms.Label();
            idLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            this.panel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.titleTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.detailTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.priceSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryIDSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.ratingSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.brandNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumnailTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.isNewSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.soldSpinEdit.Properties)).BeginInit();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            this.fillToolStrip.SuspendLayout();
            this.SuspendLayout();
            // 
            // lbProvider
            // 
            lbProvider.AutoSize = true;
            lbProvider.Location = new System.Drawing.Point(1171, 28);
            lbProvider.Name = "lbProvider";
            lbProvider.Size = new System.Drawing.Size(0, 17);
            lbProvider.TabIndex = 82;
            // 
            // lbCategory
            // 
            lbCategory.AutoSize = true;
            lbCategory.Location = new System.Drawing.Point(512, 27);
            lbCategory.Name = "lbCategory";
            lbCategory.Size = new System.Drawing.Size(0, 17);
            lbCategory.TabIndex = 81;
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
            // soldLabel
            // 
            soldLabel.AutoSize = true;
            soldLabel.Location = new System.Drawing.Point(73, 430);
            soldLabel.Name = "soldLabel";
            soldLabel.Size = new System.Drawing.Size(38, 17);
            soldLabel.TabIndex = 26;
            soldLabel.Text = "sold:";
            // 
            // addDateLabel
            // 
            addDateLabel.AutoSize = true;
            addDateLabel.Location = new System.Drawing.Point(73, 402);
            addDateLabel.Name = "addDateLabel";
            addDateLabel.Size = new System.Drawing.Size(70, 17);
            addDateLabel.TabIndex = 24;
            addDateLabel.Text = "add Date:";
            // 
            // isNewLabel
            // 
            isNewLabel.AutoSize = true;
            isNewLabel.Location = new System.Drawing.Point(73, 372);
            isNewLabel.Name = "isNewLabel";
            isNewLabel.Size = new System.Drawing.Size(53, 17);
            isNewLabel.TabIndex = 22;
            isNewLabel.Text = "is New:";
            // 
            // thumnailLabel
            // 
            thumnailLabel.AutoSize = true;
            thumnailLabel.Location = new System.Drawing.Point(73, 344);
            thumnailLabel.Name = "thumnailLabel";
            thumnailLabel.Size = new System.Drawing.Size(65, 17);
            thumnailLabel.TabIndex = 20;
            thumnailLabel.Text = "thumnail:";
            // 
            // brandNameLabel
            // 
            brandNameLabel.AutoSize = true;
            brandNameLabel.Location = new System.Drawing.Point(73, 316);
            brandNameLabel.Name = "brandNameLabel";
            brandNameLabel.Size = new System.Drawing.Size(90, 17);
            brandNameLabel.TabIndex = 18;
            brandNameLabel.Text = "brand Name:";
            // 
            // providerIdLabel
            // 
            providerIdLabel.AutoSize = true;
            providerIdLabel.Location = new System.Drawing.Point(73, 286);
            providerIdLabel.Name = "providerIdLabel";
            providerIdLabel.Size = new System.Drawing.Size(79, 17);
            providerIdLabel.TabIndex = 16;
            providerIdLabel.Text = "provider Id:";
            // 
            // activeLabel
            // 
            activeLabel.AutoSize = true;
            activeLabel.Location = new System.Drawing.Point(73, 256);
            activeLabel.Name = "activeLabel";
            activeLabel.Size = new System.Drawing.Size(49, 17);
            activeLabel.TabIndex = 14;
            activeLabel.Text = "active:";
            // 
            // ratingLabel
            // 
            ratingLabel.AutoSize = true;
            ratingLabel.Location = new System.Drawing.Point(73, 226);
            ratingLabel.Name = "ratingLabel";
            ratingLabel.Size = new System.Drawing.Size(48, 17);
            ratingLabel.TabIndex = 12;
            ratingLabel.Text = "rating:";
            // 
            // nameLabel
            // 
            nameLabel.AutoSize = true;
            nameLabel.Location = new System.Drawing.Point(73, 198);
            nameLabel.Name = "nameLabel";
            nameLabel.Size = new System.Drawing.Size(47, 17);
            nameLabel.TabIndex = 10;
            nameLabel.Text = "name:";
            // 
            // categoryIDLabel
            // 
            categoryIDLabel.AutoSize = true;
            categoryIDLabel.Location = new System.Drawing.Point(73, 168);
            categoryIDLabel.Name = "categoryIDLabel";
            categoryIDLabel.Size = new System.Drawing.Size(84, 17);
            categoryIDLabel.TabIndex = 8;
            categoryIDLabel.Text = "category ID:";
            // 
            // priceLabel
            // 
            priceLabel.AutoSize = true;
            priceLabel.Location = new System.Drawing.Point(73, 138);
            priceLabel.Name = "priceLabel";
            priceLabel.Size = new System.Drawing.Size(43, 17);
            priceLabel.TabIndex = 6;
            priceLabel.Text = "price:";
            // 
            // detailLabel
            // 
            detailLabel.AutoSize = true;
            detailLabel.Location = new System.Drawing.Point(73, 110);
            detailLabel.Name = "detailLabel";
            detailLabel.Size = new System.Drawing.Size(46, 17);
            detailLabel.TabIndex = 4;
            detailLabel.Text = "detail:";
            // 
            // titleLabel
            // 
            titleLabel.AutoSize = true;
            titleLabel.Location = new System.Drawing.Point(73, 82);
            titleLabel.Name = "titleLabel";
            titleLabel.Size = new System.Drawing.Size(34, 17);
            titleLabel.TabIndex = 2;
            titleLabel.Text = "title:";
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(73, 52);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(23, 17);
            idLabel.TabIndex = 0;
            idLabel.Text = "id:";
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
            // 
            // btnCloseForm
            // 
            this.btnCloseForm.Caption = "Close form";
            this.btnCloseForm.Id = 10;
            this.btnCloseForm.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnCloseForm.ImageOptions.Image")));
            this.btnCloseForm.Name = "btnCloseForm";
            // 
            // barDockControl1
            // 
            this.barDockControl1.CausesValidation = false;
            this.barDockControl1.Dock = System.Windows.Forms.DockStyle.Top;
            this.barDockControl1.Location = new System.Drawing.Point(0, 0);
            this.barDockControl1.Manager = this.barManager;
            this.barDockControl1.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl1.Size = new System.Drawing.Size(1284, 33);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 750);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1284, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 33);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 717);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1284, 33);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 717);
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
            this.panel1.Size = new System.Drawing.Size(1284, 61);
            this.panel1.TabIndex = 15;
            // 
            // cbProvider
            // 
            this.cbProvider.AutoSize = true;
            this.cbProvider.Location = new System.Drawing.Point(732, 28);
            this.cbProvider.Name = "cbProvider";
            this.cbProvider.Size = new System.Drawing.Size(18, 17);
            this.cbProvider.TabIndex = 80;
            this.cbProvider.UseVisualStyleBackColor = true;
            // 
            // cbCategory
            // 
            this.cbCategory.AutoSize = true;
            this.cbCategory.Location = new System.Drawing.Point(69, 28);
            this.cbCategory.Name = "cbCategory";
            this.cbCategory.Size = new System.Drawing.Size(18, 17);
            this.cbCategory.TabIndex = 79;
            this.cbCategory.UseVisualStyleBackColor = true;
            // 
            // cbbProvider
            // 
            this.cbbProvider.DisplayMember = "brandName";
            this.cbbProvider.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbProvider.FormattingEnabled = true;
            this.cbbProvider.Location = new System.Drawing.Point(851, 24);
            this.cbbProvider.Name = "cbbProvider";
            this.cbbProvider.Size = new System.Drawing.Size(300, 24);
            this.cbbProvider.TabIndex = 77;
            this.cbbProvider.ValueMember = "id";
            // 
            // cbbCategory
            // 
            this.cbbCategory.DisplayMember = "name";
            this.cbbCategory.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbCategory.FormattingEnabled = true;
            this.cbbCategory.Location = new System.Drawing.Point(180, 24);
            this.cbbCategory.Name = "cbbCategory";
            this.cbbCategory.Size = new System.Drawing.Size(313, 24);
            this.cbbCategory.TabIndex = 78;
            this.cbbCategory.ValueMember = "id";
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // sP_GetProductTableAdapter
            // 
            this.sP_GetProductTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.NotificationDetailTableAdapter = null;
            this.tableAdapterManager.NotificationTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = null;
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
            // panel3
            // 
            this.panel3.Controls.Add(this.btnCancelAddProvider);
            this.panel3.Controls.Add(this.btnSaveAddProvider);
            this.panel3.Controls.Add(idLabel);
            this.panel3.Controls.Add(this.idSpinEdit);
            this.panel3.Controls.Add(titleLabel);
            this.panel3.Controls.Add(this.titleTextEdit);
            this.panel3.Controls.Add(detailLabel);
            this.panel3.Controls.Add(this.detailTextEdit);
            this.panel3.Controls.Add(priceLabel);
            this.panel3.Controls.Add(this.priceSpinEdit);
            this.panel3.Controls.Add(categoryIDLabel);
            this.panel3.Controls.Add(this.categoryIDSpinEdit);
            this.panel3.Controls.Add(nameLabel);
            this.panel3.Controls.Add(this.nameTextEdit);
            this.panel3.Controls.Add(ratingLabel);
            this.panel3.Controls.Add(this.ratingSpinEdit);
            this.panel3.Controls.Add(activeLabel);
            this.panel3.Controls.Add(this.activeSpinEdit);
            this.panel3.Controls.Add(providerIdLabel);
            this.panel3.Controls.Add(this.providerIdSpinEdit);
            this.panel3.Controls.Add(brandNameLabel);
            this.panel3.Controls.Add(this.brandNameTextEdit);
            this.panel3.Controls.Add(thumnailLabel);
            this.panel3.Controls.Add(this.thumnailTextEdit);
            this.panel3.Controls.Add(isNewLabel);
            this.panel3.Controls.Add(this.isNewSpinEdit);
            this.panel3.Controls.Add(addDateLabel);
            this.panel3.Controls.Add(this.addDateDateEdit);
            this.panel3.Controls.Add(soldLabel);
            this.panel3.Controls.Add(this.soldSpinEdit);
            this.panel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel3.Location = new System.Drawing.Point(645, 3);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(636, 650);
            this.panel3.TabIndex = 1;
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(332, 501);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 60;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(114, 501);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 59;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Location = new System.Drawing.Point(169, 49);
            this.idSpinEdit.MenuManager = this.barManager;
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 1;
            // 
            // titleTextEdit
            // 
            this.titleTextEdit.Location = new System.Drawing.Point(169, 79);
            this.titleTextEdit.MenuManager = this.barManager;
            this.titleTextEdit.Name = "titleTextEdit";
            this.titleTextEdit.Size = new System.Drawing.Size(125, 22);
            this.titleTextEdit.TabIndex = 3;
            // 
            // detailTextEdit
            // 
            this.detailTextEdit.Location = new System.Drawing.Point(169, 107);
            this.detailTextEdit.MenuManager = this.barManager;
            this.detailTextEdit.Name = "detailTextEdit";
            this.detailTextEdit.Size = new System.Drawing.Size(125, 22);
            this.detailTextEdit.TabIndex = 5;
            // 
            // priceSpinEdit
            // 
            this.priceSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.priceSpinEdit.Location = new System.Drawing.Point(169, 135);
            this.priceSpinEdit.MenuManager = this.barManager;
            this.priceSpinEdit.Name = "priceSpinEdit";
            this.priceSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.priceSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.priceSpinEdit.TabIndex = 7;
            // 
            // categoryIDSpinEdit
            // 
            this.categoryIDSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.categoryIDSpinEdit.Location = new System.Drawing.Point(169, 165);
            this.categoryIDSpinEdit.MenuManager = this.barManager;
            this.categoryIDSpinEdit.Name = "categoryIDSpinEdit";
            this.categoryIDSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.categoryIDSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.categoryIDSpinEdit.TabIndex = 9;
            // 
            // nameTextEdit
            // 
            this.nameTextEdit.Location = new System.Drawing.Point(169, 195);
            this.nameTextEdit.MenuManager = this.barManager;
            this.nameTextEdit.Name = "nameTextEdit";
            this.nameTextEdit.Size = new System.Drawing.Size(125, 22);
            this.nameTextEdit.TabIndex = 11;
            // 
            // ratingSpinEdit
            // 
            this.ratingSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.ratingSpinEdit.Location = new System.Drawing.Point(169, 223);
            this.ratingSpinEdit.MenuManager = this.barManager;
            this.ratingSpinEdit.Name = "ratingSpinEdit";
            this.ratingSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.ratingSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.ratingSpinEdit.TabIndex = 13;
            // 
            // activeSpinEdit
            // 
            this.activeSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.activeSpinEdit.Location = new System.Drawing.Point(169, 253);
            this.activeSpinEdit.MenuManager = this.barManager;
            this.activeSpinEdit.Name = "activeSpinEdit";
            this.activeSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.activeSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.activeSpinEdit.TabIndex = 15;
            // 
            // providerIdSpinEdit
            // 
            this.providerIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.providerIdSpinEdit.Location = new System.Drawing.Point(169, 283);
            this.providerIdSpinEdit.MenuManager = this.barManager;
            this.providerIdSpinEdit.Name = "providerIdSpinEdit";
            this.providerIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.providerIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.providerIdSpinEdit.TabIndex = 17;
            // 
            // brandNameTextEdit
            // 
            this.brandNameTextEdit.Location = new System.Drawing.Point(169, 313);
            this.brandNameTextEdit.MenuManager = this.barManager;
            this.brandNameTextEdit.Name = "brandNameTextEdit";
            this.brandNameTextEdit.Size = new System.Drawing.Size(125, 22);
            this.brandNameTextEdit.TabIndex = 19;
            // 
            // thumnailTextEdit
            // 
            this.thumnailTextEdit.Location = new System.Drawing.Point(169, 341);
            this.thumnailTextEdit.MenuManager = this.barManager;
            this.thumnailTextEdit.Name = "thumnailTextEdit";
            this.thumnailTextEdit.Size = new System.Drawing.Size(125, 22);
            this.thumnailTextEdit.TabIndex = 21;
            // 
            // isNewSpinEdit
            // 
            this.isNewSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.isNewSpinEdit.Location = new System.Drawing.Point(169, 369);
            this.isNewSpinEdit.MenuManager = this.barManager;
            this.isNewSpinEdit.Name = "isNewSpinEdit";
            this.isNewSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.isNewSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.isNewSpinEdit.TabIndex = 23;
            // 
            // addDateDateEdit
            // 
            this.addDateDateEdit.EditValue = null;
            this.addDateDateEdit.Location = new System.Drawing.Point(169, 399);
            this.addDateDateEdit.MenuManager = this.barManager;
            this.addDateDateEdit.Name = "addDateDateEdit";
            this.addDateDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.addDateDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.addDateDateEdit.Size = new System.Drawing.Size(125, 22);
            this.addDateDateEdit.TabIndex = 25;
            // 
            // soldSpinEdit
            // 
            this.soldSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.soldSpinEdit.Location = new System.Drawing.Point(169, 427);
            this.soldSpinEdit.MenuManager = this.barManager;
            this.soldSpinEdit.Name = "soldSpinEdit";
            this.soldSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.soldSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.soldSpinEdit.TabIndex = 27;
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.sP_GetProductGridControl);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel2.Location = new System.Drawing.Point(3, 3);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(636, 650);
            this.panel2.TabIndex = 0;
            // 
            // sP_GetProductGridControl
            // 
            this.sP_GetProductGridControl.DataSource = this.sP_GetProductBindingSource;
            this.sP_GetProductGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.sP_GetProductGridControl.Location = new System.Drawing.Point(0, 0);
            this.sP_GetProductGridControl.MainView = this.gridView1;
            this.sP_GetProductGridControl.MenuManager = this.barManager;
            this.sP_GetProductGridControl.Name = "sP_GetProductGridControl";
            this.sP_GetProductGridControl.Size = new System.Drawing.Size(636, 650);
            this.sP_GetProductGridControl.TabIndex = 0;
            this.sP_GetProductGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // sP_GetProductBindingSource
            // 
            this.sP_GetProductBindingSource.DataMember = "SP_GetProduct";
            this.sP_GetProductBindingSource.DataSource = this.clothesDataSet;
            // 
            // gridView1
            // 
            this.gridView1.GridControl = this.sP_GetProductGridControl;
            this.gridView1.Name = "gridView1";
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.panel2, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panel3, 1, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 94);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1284, 656);
            this.tableLayoutPanel1.TabIndex = 16;
            // 
            // fillToolStrip
            // 
            this.fillToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.providerIdToolStripLabel,
            this.providerIdToolStripTextBox,
            this.categoryIDToolStripLabel,
            this.categoryIDToolStripTextBox,
            this.fillToolStripButton});
            this.fillToolStrip.Location = new System.Drawing.Point(0, 94);
            this.fillToolStrip.Name = "fillToolStrip";
            this.fillToolStrip.Size = new System.Drawing.Size(1284, 27);
            this.fillToolStrip.TabIndex = 18;
            this.fillToolStrip.Text = "fillToolStrip";
            // 
            // providerIdToolStripLabel
            // 
            this.providerIdToolStripLabel.Name = "providerIdToolStripLabel";
            this.providerIdToolStripLabel.Size = new System.Drawing.Size(81, 24);
            this.providerIdToolStripLabel.Text = "providerId:";
            // 
            // providerIdToolStripTextBox
            // 
            this.providerIdToolStripTextBox.Name = "providerIdToolStripTextBox";
            this.providerIdToolStripTextBox.Size = new System.Drawing.Size(100, 27);
            // 
            // categoryIDToolStripLabel
            // 
            this.categoryIDToolStripLabel.Name = "categoryIDToolStripLabel";
            this.categoryIDToolStripLabel.Size = new System.Drawing.Size(85, 24);
            this.categoryIDToolStripLabel.Text = "categoryID:";
            // 
            // categoryIDToolStripTextBox
            // 
            this.categoryIDToolStripTextBox.Name = "categoryIDToolStripTextBox";
            this.categoryIDToolStripTextBox.Size = new System.Drawing.Size(100, 27);
            // 
            // fillToolStripButton
            // 
            this.fillToolStripButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.fillToolStripButton.Name = "fillToolStripButton";
            this.fillToolStripButton.Size = new System.Drawing.Size(32, 24);
            this.fillToolStripButton.Text = "Fill";
            this.fillToolStripButton.Click += new System.EventHandler(this.fillToolStripButton_Click_1);
            // 
            // ProductNewForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1284, 750);
            this.Controls.Add(this.fillToolStrip);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "ProductNewForm";
            this.Text = "ProductNewForm";
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            this.panel3.ResumeLayout(false);
            this.panel3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.titleTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.detailTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.priceSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryIDSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.ratingSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.brandNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumnailTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.isNewSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addDateDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.soldSpinEdit.Properties)).EndInit();
            this.panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.fillToolStrip.ResumeLayout(false);
            this.fillToolStrip.PerformLayout();
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
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.CheckBox cbProvider;
        private System.Windows.Forms.CheckBox cbCategory;
        private System.Windows.Forms.ComboBox cbbProvider;
        private System.Windows.Forms.ComboBox cbbCategory;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.SP_GetProductTableAdapter sP_GetProductTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private System.Windows.Forms.ToolStrip fillToolStrip;
        private System.Windows.Forms.ToolStripLabel providerIdToolStripLabel;
        private System.Windows.Forms.ToolStripTextBox providerIdToolStripTextBox;
        private System.Windows.Forms.ToolStripLabel categoryIDToolStripLabel;
        private System.Windows.Forms.ToolStripTextBox categoryIDToolStripTextBox;
        private System.Windows.Forms.ToolStripButton fillToolStripButton;
        private System.Windows.Forms.BindingSource sP_GetProductBindingSource;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.TextEdit titleTextEdit;
        private DevExpress.XtraEditors.TextEdit detailTextEdit;
        private DevExpress.XtraEditors.SpinEdit priceSpinEdit;
        private DevExpress.XtraEditors.SpinEdit categoryIDSpinEdit;
        private DevExpress.XtraEditors.TextEdit nameTextEdit;
        private DevExpress.XtraEditors.SpinEdit ratingSpinEdit;
        private DevExpress.XtraEditors.SpinEdit activeSpinEdit;
        private DevExpress.XtraEditors.SpinEdit providerIdSpinEdit;
        private DevExpress.XtraEditors.TextEdit brandNameTextEdit;
        private DevExpress.XtraEditors.TextEdit thumnailTextEdit;
        private DevExpress.XtraEditors.SpinEdit isNewSpinEdit;
        private DevExpress.XtraEditors.DateEdit addDateDateEdit;
        private DevExpress.XtraEditors.SpinEdit soldSpinEdit;
        private DevExpress.XtraGrid.GridControl sP_GetProductGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
    }
}