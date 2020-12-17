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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SizeColorForm));
            System.Windows.Forms.Label sizeIdLabel;
            System.Windows.Forms.Label colorIDLabel;
            System.Windows.Forms.Label quantityLabel;
            System.Windows.Forms.Label imageUrlLabel;
            System.Windows.Forms.Label activeLabel;
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label label3;
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
            this.sP_GetProductColorSizeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sP_GetProductColorSizeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_GetProductColorSizeTableAdapter();
            this.panel1 = new System.Windows.Forms.Panel();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.productSizeColorGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colproductID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsizeId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcolorID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colquantity = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colimageUrl = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colactive = new DevExpress.XtraGrid.Columns.GridColumn();
            this.panel2 = new System.Windows.Forms.Panel();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.colorComboBox = new System.Windows.Forms.ComboBox();
            this.colorBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sizeComboBox = new System.Windows.Forms.ComboBox();
            this.sizeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.productComboBox = new System.Windows.Forms.ComboBox();
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.productIDTextBox = new System.Windows.Forms.TextBox();
            this.sizeIdTextBox = new System.Windows.Forms.TextBox();
            this.colorIDTextBox = new System.Windows.Forms.TextBox();
            this.quantityTextBox = new System.Windows.Forms.TextBox();
            this.imageUrlTextBox = new System.Windows.Forms.TextBox();
            this.activeTextBox = new System.Windows.Forms.TextBox();
            this.productTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductTableAdapter();
            this.sizeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SizeTableAdapter();
            this.colorTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ColorTableAdapter();
            this.invoiceItemBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.invoiceItemTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.InvoiceItemTableAdapter();
            this.importCouponDetailBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.importCouponDetailTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ImportCouponDetailTableAdapter();
            productIDLabel = new System.Windows.Forms.Label();
            sizeIdLabel = new System.Windows.Forms.Label();
            colorIDLabel = new System.Windows.Forms.Label();
            quantityLabel = new System.Windows.Forms.Label();
            imageUrlLabel = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductColorSizeBindingSource)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.colorBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // productIDLabel
            // 
            resources.ApplyResources(productIDLabel, "productIDLabel");
            productIDLabel.Name = "productIDLabel";
            // 
            // sizeIdLabel
            // 
            resources.ApplyResources(sizeIdLabel, "sizeIdLabel");
            sizeIdLabel.Name = "sizeIdLabel";
            // 
            // colorIDLabel
            // 
            resources.ApplyResources(colorIDLabel, "colorIDLabel");
            colorIDLabel.Name = "colorIDLabel";
            // 
            // quantityLabel
            // 
            resources.ApplyResources(quantityLabel, "quantityLabel");
            quantityLabel.Name = "quantityLabel";
            // 
            // imageUrlLabel
            // 
            resources.ApplyResources(imageUrlLabel, "imageUrlLabel");
            imageUrlLabel.Name = "imageUrlLabel";
            // 
            // activeLabel
            // 
            resources.ApplyResources(activeLabel, "activeLabel");
            activeLabel.Name = "activeLabel";
            // 
            // label1
            // 
            resources.ApplyResources(label1, "label1");
            label1.Name = "label1";
            // 
            // label2
            // 
            resources.ApplyResources(label2, "label2");
            label2.Name = "label2";
            // 
            // label3
            // 
            resources.ApplyResources(label3, "label3");
            label3.Name = "label3";
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
            resources.ApplyResources(this.bar2, "bar2");
            // 
            // btnAddProvider
            // 
            resources.ApplyResources(this.btnAddProvider, "btnAddProvider");
            this.btnAddProvider.Id = 0;
            this.btnAddProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.add;
            this.btnAddProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnAddProvider.ImageOptions.LargeImage")));
            this.btnAddProvider.Name = "btnAddProvider";
            this.btnAddProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnAddProvider_ItemClick);
            // 
            // btnDelProvider
            // 
            resources.ApplyResources(this.btnDelProvider, "btnDelProvider");
            this.btnDelProvider.Id = 2;
            this.btnDelProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.delete__1_;
            this.btnDelProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnDelProvider.ImageOptions.LargeImage")));
            this.btnDelProvider.Name = "btnDelProvider";
            this.btnDelProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnDelProvider_ItemClick);
            // 
            // btnReloadProvider
            // 
            resources.ApplyResources(this.btnReloadProvider, "btnReloadProvider");
            this.btnReloadProvider.Id = 5;
            this.btnReloadProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.exchange;
            this.btnReloadProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnReloadProvider.ImageOptions.LargeImage")));
            this.btnReloadProvider.Name = "btnReloadProvider";
            this.btnReloadProvider.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnReloadProvider_ItemClick);
            // 
            // btnCloseForm
            // 
            resources.ApplyResources(this.btnCloseForm, "btnCloseForm");
            this.btnCloseForm.Id = 10;
            this.btnCloseForm.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.close;
            this.btnCloseForm.Name = "btnCloseForm";
            this.btnCloseForm.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnCloseForm_ItemClick);
            // 
            // barDockControl1
            // 
            this.barDockControl1.CausesValidation = false;
            resources.ApplyResources(this.barDockControl1, "barDockControl1");
            this.barDockControl1.Manager = this.barManager;
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            resources.ApplyResources(this.barDockControl2, "barDockControl2");
            this.barDockControl2.Manager = this.barManager;
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            resources.ApplyResources(this.barDockControl3, "barDockControl3");
            this.barDockControl3.Manager = this.barManager;
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            resources.ApplyResources(this.barDockControl5, "barDockControl5");
            this.barDockControl5.Manager = this.barManager;
            // 
            // btnSua
            // 
            resources.ApplyResources(this.btnSua, "btnSua");
            this.btnSua.Id = 3;
            this.btnSua.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnSua.ImageOptions.Image")));
            this.btnSua.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnSua.ImageOptions.LargeImage")));
            this.btnSua.Name = "btnSua";
            // 
            // btnTimKiem
            // 
            resources.ApplyResources(this.btnTimKiem, "btnTimKiem");
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
            this.tableAdapterManager.InvoiceStatusTableAdapter = null;
            this.tableAdapterManager.InvoiceTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = this.productSizeColorTableAdapter;
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
            // sP_GetProductColorSizeBindingSource
            // 
            this.sP_GetProductColorSizeBindingSource.DataMember = "SP_GetProductColorSize";
            this.sP_GetProductColorSizeBindingSource.DataSource = this.clothesDataSet;
            // 
            // sP_GetProductColorSizeTableAdapter
            // 
            this.sP_GetProductColorSizeTableAdapter.ClearBeforeFill = true;
            // 
            // panel1
            // 
            resources.ApplyResources(this.panel1, "panel1");
            this.panel1.Name = "panel1";
            // 
            // tableLayoutPanel1
            // 
            resources.ApplyResources(this.tableLayoutPanel1, "tableLayoutPanel1");
            this.tableLayoutPanel1.Controls.Add(this.productSizeColorGridControl, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panel2, 1, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            // 
            // productSizeColorGridControl
            // 
            this.productSizeColorGridControl.DataSource = this.productSizeColorBindingSource;
            resources.ApplyResources(this.productSizeColorGridControl, "productSizeColorGridControl");
            this.productSizeColorGridControl.MainView = this.gridView1;
            this.productSizeColorGridControl.MenuManager = this.barManager;
            this.productSizeColorGridControl.Name = "productSizeColorGridControl";
            this.productSizeColorGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colproductID,
            this.colsizeId,
            this.colcolorID,
            this.colquantity,
            this.colimageUrl,
            this.colactive});
            this.gridView1.GridControl = this.productSizeColorGridControl;
            this.gridView1.Name = "gridView1";
            // 
            // colproductID
            // 
            this.colproductID.FieldName = "productID";
            this.colproductID.MinWidth = 25;
            this.colproductID.Name = "colproductID";
            this.colproductID.OptionsColumn.AllowEdit = false;
            resources.ApplyResources(this.colproductID, "colproductID");
            // 
            // colsizeId
            // 
            this.colsizeId.FieldName = "sizeId";
            this.colsizeId.MinWidth = 25;
            this.colsizeId.Name = "colsizeId";
            this.colsizeId.OptionsColumn.AllowEdit = false;
            resources.ApplyResources(this.colsizeId, "colsizeId");
            // 
            // colcolorID
            // 
            this.colcolorID.FieldName = "colorID";
            this.colcolorID.MinWidth = 25;
            this.colcolorID.Name = "colcolorID";
            this.colcolorID.OptionsColumn.AllowEdit = false;
            resources.ApplyResources(this.colcolorID, "colcolorID");
            // 
            // colquantity
            // 
            this.colquantity.FieldName = "quantity";
            this.colquantity.MinWidth = 25;
            this.colquantity.Name = "colquantity";
            this.colquantity.OptionsColumn.AllowEdit = false;
            resources.ApplyResources(this.colquantity, "colquantity");
            // 
            // colimageUrl
            // 
            this.colimageUrl.FieldName = "imageUrl";
            this.colimageUrl.MinWidth = 25;
            this.colimageUrl.Name = "colimageUrl";
            this.colimageUrl.OptionsColumn.AllowEdit = false;
            resources.ApplyResources(this.colimageUrl, "colimageUrl");
            // 
            // colactive
            // 
            this.colactive.FieldName = "active";
            this.colactive.MinWidth = 25;
            this.colactive.Name = "colactive";
            this.colactive.OptionsColumn.AllowEdit = false;
            resources.ApplyResources(this.colactive, "colactive");
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.btnCancelAddProvider);
            this.panel2.Controls.Add(this.btnSaveAddProvider);
            this.panel2.Controls.Add(this.colorComboBox);
            this.panel2.Controls.Add(this.sizeComboBox);
            this.panel2.Controls.Add(this.productComboBox);
            this.panel2.Controls.Add(label3);
            this.panel2.Controls.Add(label2);
            this.panel2.Controls.Add(label1);
            this.panel2.Controls.Add(productIDLabel);
            this.panel2.Controls.Add(this.productIDTextBox);
            this.panel2.Controls.Add(sizeIdLabel);
            this.panel2.Controls.Add(this.sizeIdTextBox);
            this.panel2.Controls.Add(colorIDLabel);
            this.panel2.Controls.Add(this.colorIDTextBox);
            this.panel2.Controls.Add(quantityLabel);
            this.panel2.Controls.Add(this.quantityTextBox);
            this.panel2.Controls.Add(imageUrlLabel);
            this.panel2.Controls.Add(this.imageUrlTextBox);
            this.panel2.Controls.Add(activeLabel);
            this.panel2.Controls.Add(this.activeTextBox);
            resources.ApplyResources(this.panel2, "panel2");
            this.panel2.Name = "panel2";
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            resources.ApplyResources(this.btnCancelAddProvider, "btnCancelAddProvider");
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            this.btnCancelAddProvider.Click += new System.EventHandler(this.btnCancelAddProvider_Click);
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            resources.ApplyResources(this.btnSaveAddProvider, "btnSaveAddProvider");
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click);
            // 
            // colorComboBox
            // 
            this.colorComboBox.DataSource = this.colorBindingSource;
            this.colorComboBox.DisplayMember = "colorName";
            this.colorComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.colorComboBox.FormattingEnabled = true;
            resources.ApplyResources(this.colorComboBox, "colorComboBox");
            this.colorComboBox.Name = "colorComboBox";
            this.colorComboBox.ValueMember = "id";
            this.colorComboBox.SelectedIndexChanged += new System.EventHandler(this.colorComboBox_SelectedIndexChanged);
            // 
            // colorBindingSource
            // 
            this.colorBindingSource.DataMember = "Color";
            this.colorBindingSource.DataSource = this.clothesDataSet;
            // 
            // sizeComboBox
            // 
            this.sizeComboBox.DataSource = this.sizeBindingSource;
            this.sizeComboBox.DisplayMember = "sizeName";
            this.sizeComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.sizeComboBox.FormattingEnabled = true;
            resources.ApplyResources(this.sizeComboBox, "sizeComboBox");
            this.sizeComboBox.Name = "sizeComboBox";
            this.sizeComboBox.ValueMember = "id";
            this.sizeComboBox.SelectedIndexChanged += new System.EventHandler(this.sizeComboBox_SelectedIndexChanged);
            // 
            // sizeBindingSource
            // 
            this.sizeBindingSource.DataMember = "Size";
            this.sizeBindingSource.DataSource = this.clothesDataSet;
            // 
            // productComboBox
            // 
            this.productComboBox.DataSource = this.productBindingSource;
            this.productComboBox.DisplayMember = "title";
            this.productComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            resources.ApplyResources(this.productComboBox, "productComboBox");
            this.productComboBox.FormattingEnabled = true;
            this.productComboBox.Name = "productComboBox";
            this.productComboBox.ValueMember = "id";
            // 
            // productBindingSource
            // 
            this.productBindingSource.DataMember = "Product";
            this.productBindingSource.DataSource = this.clothesDataSet;
            // 
            // productIDTextBox
            // 
            this.productIDTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productSizeColorBindingSource, "productID", true));
            resources.ApplyResources(this.productIDTextBox, "productIDTextBox");
            this.productIDTextBox.Name = "productIDTextBox";
            // 
            // sizeIdTextBox
            // 
            this.sizeIdTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productSizeColorBindingSource, "sizeId", true));
            resources.ApplyResources(this.sizeIdTextBox, "sizeIdTextBox");
            this.sizeIdTextBox.Name = "sizeIdTextBox";
            // 
            // colorIDTextBox
            // 
            this.colorIDTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productSizeColorBindingSource, "colorID", true));
            resources.ApplyResources(this.colorIDTextBox, "colorIDTextBox");
            this.colorIDTextBox.Name = "colorIDTextBox";
            // 
            // quantityTextBox
            // 
            this.quantityTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productSizeColorBindingSource, "quantity", true));
            resources.ApplyResources(this.quantityTextBox, "quantityTextBox");
            this.quantityTextBox.Name = "quantityTextBox";
            // 
            // imageUrlTextBox
            // 
            this.imageUrlTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productSizeColorBindingSource, "imageUrl", true));
            resources.ApplyResources(this.imageUrlTextBox, "imageUrlTextBox");
            this.imageUrlTextBox.Name = "imageUrlTextBox";
            // 
            // activeTextBox
            // 
            this.activeTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.productSizeColorBindingSource, "active", true));
            resources.ApplyResources(this.activeTextBox, "activeTextBox");
            this.activeTextBox.Name = "activeTextBox";
            // 
            // productTableAdapter
            // 
            this.productTableAdapter.ClearBeforeFill = true;
            // 
            // sizeTableAdapter
            // 
            this.sizeTableAdapter.ClearBeforeFill = true;
            // 
            // colorTableAdapter
            // 
            this.colorTableAdapter.ClearBeforeFill = true;
            // 
            // invoiceItemBindingSource
            // 
            this.invoiceItemBindingSource.DataMember = "FK_InvoiceItem_ProductSizeColor";
            this.invoiceItemBindingSource.DataSource = this.productSizeColorBindingSource;
            // 
            // invoiceItemTableAdapter
            // 
            this.invoiceItemTableAdapter.ClearBeforeFill = true;
            // 
            // importCouponDetailBindingSource
            // 
            this.importCouponDetailBindingSource.DataMember = "FK_ImportCouponDetail_ProductSizeColor";
            this.importCouponDetailBindingSource.DataSource = this.productSizeColorBindingSource;
            // 
            // importCouponDetailTableAdapter
            // 
            this.importCouponDetailTableAdapter.ClearBeforeFill = true;
            // 
            // SizeColorForm
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "SizeColorForm";
            this.Load += new System.EventHandler(this.SizeColorForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProductColorSizeBindingSource)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.colorBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.importCouponDetailBindingSource)).EndInit();
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
        private System.Windows.Forms.BindingSource sP_GetProductColorSizeBindingSource;
        private ClothesDataSetTableAdapters.SP_GetProductColorSizeTableAdapter sP_GetProductColorSizeTableAdapter;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private DevExpress.XtraGrid.GridControl productSizeColorGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colproductID;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeId;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorID;
        private DevExpress.XtraGrid.Columns.GridColumn colquantity;
        private DevExpress.XtraGrid.Columns.GridColumn colimageUrl;
        private DevExpress.XtraGrid.Columns.GridColumn colactive;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.TextBox productIDTextBox;
        private System.Windows.Forms.TextBox sizeIdTextBox;
        private System.Windows.Forms.TextBox colorIDTextBox;
        private System.Windows.Forms.TextBox quantityTextBox;
        private System.Windows.Forms.TextBox imageUrlTextBox;
        private System.Windows.Forms.TextBox activeTextBox;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.BindingSource productBindingSource;
        private ClothesDataSetTableAdapters.ProductTableAdapter productTableAdapter;
        private System.Windows.Forms.ComboBox productComboBox;
        private System.Windows.Forms.BindingSource sizeBindingSource;
        private ClothesDataSetTableAdapters.SizeTableAdapter sizeTableAdapter;
        private System.Windows.Forms.ComboBox sizeComboBox;
        private System.Windows.Forms.BindingSource colorBindingSource;
        private ClothesDataSetTableAdapters.ColorTableAdapter colorTableAdapter;
        private System.Windows.Forms.ComboBox colorComboBox;
        private System.Windows.Forms.BindingSource invoiceItemBindingSource;
        private ClothesDataSetTableAdapters.InvoiceItemTableAdapter invoiceItemTableAdapter;
        private System.Windows.Forms.BindingSource importCouponDetailBindingSource;
        private ClothesDataSetTableAdapters.ImportCouponDetailTableAdapter importCouponDetailTableAdapter;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
    }
}