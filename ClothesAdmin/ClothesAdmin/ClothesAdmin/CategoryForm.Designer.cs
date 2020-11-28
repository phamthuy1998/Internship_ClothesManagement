namespace ClothesAdmin
{
    partial class CategoryForm
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
            System.Windows.Forms.Label nameLabel;
            System.Windows.Forms.Label detailLabel;
            System.Windows.Forms.Label imageUrlLabel;
            System.Windows.Forms.Label thumnailLabel;
            System.Windows.Forms.Label sexLabel;
            System.Windows.Forms.Label c;
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label activeLabel;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CategoryForm));
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.categoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.categoryTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.CategoryTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.productTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductTableAdapter();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.nameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.detailRichTextBox = new System.Windows.Forms.RichTextBox();
            this.imageUrlTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.thumnailTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.sexSpinEdit = new DevExpress.XtraEditors.SpinEdit();
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
            this.categoryGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colname = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldetail = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colimageUrl = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colthumnail = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsex = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colactive = new DevExpress.XtraGrid.Columns.GridColumn();
            this.picImageIcon = new System.Windows.Forms.PictureBox();
            this.picThumbnail = new System.Windows.Forms.PictureBox();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.btnSaveImg = new System.Windows.Forms.Button();
            this.btnChangeImg = new System.Windows.Forms.LinkLabel();
            this.button1 = new System.Windows.Forms.Button();
            this.linkLabel1 = new System.Windows.Forms.LinkLabel();
            this.activeSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.cbbActive = new System.Windows.Forms.ComboBox();
            this.cbbGender = new System.Windows.Forms.ComboBox();
            idLabel = new System.Windows.Forms.Label();
            nameLabel = new System.Windows.Forms.Label();
            detailLabel = new System.Windows.Forms.Label();
            imageUrlLabel = new System.Windows.Forms.Label();
            thumnailLabel = new System.Windows.Forms.Label();
            sexLabel = new System.Windows.Forms.Label();
            c = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageUrlTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumnailTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sexSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picImageIcon)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picThumbnail)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).BeginInit();
            this.SuspendLayout();
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(840, 124);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(21, 17);
            idLabel.TabIndex = 1;
            idLabel.Text = "ID";
            // 
            // nameLabel
            // 
            nameLabel.AutoSize = true;
            nameLabel.Location = new System.Drawing.Point(840, 172);
            nameLabel.Name = "nameLabel";
            nameLabel.Size = new System.Drawing.Size(45, 17);
            nameLabel.TabIndex = 3;
            nameLabel.Text = "Name";
            // 
            // detailLabel
            // 
            detailLabel.AutoSize = true;
            detailLabel.Location = new System.Drawing.Point(840, 422);
            detailLabel.Name = "detailLabel";
            detailLabel.Size = new System.Drawing.Size(44, 17);
            detailLabel.TabIndex = 5;
            detailLabel.Text = "Detail";
            // 
            // imageUrlLabel
            // 
            imageUrlLabel.AutoSize = true;
            imageUrlLabel.Location = new System.Drawing.Point(840, 218);
            imageUrlLabel.Name = "imageUrlLabel";
            imageUrlLabel.Size = new System.Drawing.Size(66, 17);
            imageUrlLabel.TabIndex = 7;
            imageUrlLabel.Text = "Icon URL";
            // 
            // thumnailLabel
            // 
            thumnailLabel.AutoSize = true;
            thumnailLabel.Location = new System.Drawing.Point(840, 265);
            thumnailLabel.Name = "thumnailLabel";
            thumnailLabel.Size = new System.Drawing.Size(74, 17);
            thumnailLabel.TabIndex = 9;
            thumnailLabel.Text = "Thumbnail";
            // 
            // sexLabel
            // 
            sexLabel.AutoSize = true;
            sexLabel.Location = new System.Drawing.Point(840, 312);
            sexLabel.Name = "sexLabel";
            sexLabel.Size = new System.Drawing.Size(56, 17);
            sexLabel.TabIndex = 11;
            sexLabel.Text = "Gender";
            // 
            // c
            // 
            c.AutoSize = true;
            c.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            c.Location = new System.Drawing.Point(942, 44);
            c.Name = "c";
            c.Size = new System.Drawing.Size(229, 36);
            c.TabIndex = 24;
            c.Text = "Category detail";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(1425, 124);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(34, 17);
            label2.TabIndex = 51;
            label2.Text = "Icon";
            // 
            // activeLabel
            // 
            activeLabel.AutoSize = true;
            activeLabel.Location = new System.Drawing.Point(840, 364);
            activeLabel.Name = "activeLabel";
            activeLabel.Size = new System.Drawing.Size(46, 17);
            activeLabel.TabIndex = 13;
            activeLabel.Text = "Active";
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // categoryBindingSource
            // 
            this.categoryBindingSource.DataMember = "Category";
            this.categoryBindingSource.DataSource = this.clothesDataSet;
            // 
            // categoryTableAdapter
            // 
            this.categoryTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = this.productTableAdapter;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // productTableAdapter
            // 
            this.productTableAdapter.ClearBeforeFill = true;
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Enabled = false;
            this.idSpinEdit.Location = new System.Drawing.Point(948, 121);
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 2;
            // 
            // nameTextEdit
            // 
            this.nameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource, "name", true));
            this.nameTextEdit.Location = new System.Drawing.Point(948, 169);
            this.nameTextEdit.Name = "nameTextEdit";
            this.nameTextEdit.Size = new System.Drawing.Size(387, 22);
            this.nameTextEdit.TabIndex = 4;
            // 
            // detailRichTextBox
            // 
            this.detailRichTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.categoryBindingSource, "detail", true));
            this.detailRichTextBox.Location = new System.Drawing.Point(948, 419);
            this.detailRichTextBox.Name = "detailRichTextBox";
            this.detailRichTextBox.Size = new System.Drawing.Size(387, 123);
            this.detailRichTextBox.TabIndex = 6;
            this.detailRichTextBox.Text = "";
            // 
            // imageUrlTextEdit
            // 
            this.imageUrlTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource, "imageUrl", true));
            this.imageUrlTextEdit.Location = new System.Drawing.Point(948, 215);
            this.imageUrlTextEdit.Name = "imageUrlTextEdit";
            this.imageUrlTextEdit.Size = new System.Drawing.Size(387, 22);
            this.imageUrlTextEdit.TabIndex = 8;
            this.imageUrlTextEdit.EditValueChanged += new System.EventHandler(this.imageUrlTextEdit_EditValueChanged);
            // 
            // thumnailTextEdit
            // 
            this.thumnailTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource, "thumnail", true));
            this.thumnailTextEdit.Location = new System.Drawing.Point(948, 262);
            this.thumnailTextEdit.Name = "thumnailTextEdit";
            this.thumnailTextEdit.Size = new System.Drawing.Size(387, 22);
            this.thumnailTextEdit.TabIndex = 10;
            this.thumnailTextEdit.EditValueChanged += new System.EventHandler(this.thumnailTextEdit_EditValueChanged);
            // 
            // sexSpinEdit
            // 
            this.sexSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource, "sex", true));
            this.sexSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.sexSpinEdit.Enabled = false;
            this.sexSpinEdit.Location = new System.Drawing.Point(1253, 308);
            this.sexSpinEdit.Name = "sexSpinEdit";
            this.sexSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.sexSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.sexSpinEdit.TabIndex = 12;
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
            this.barDockControl1.Size = new System.Drawing.Size(1940, 30);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 837);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1940, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 30);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 807);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1940, 30);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 807);
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
            // categoryGridControl
            // 
            this.categoryGridControl.DataSource = this.categoryBindingSource;
            this.categoryGridControl.Dock = System.Windows.Forms.DockStyle.Left;
            this.categoryGridControl.Location = new System.Drawing.Point(0, 30);
            this.categoryGridControl.MainView = this.gridView1;
            this.categoryGridControl.Name = "categoryGridControl";
            this.categoryGridControl.Size = new System.Drawing.Size(785, 807);
            this.categoryGridControl.TabIndex = 20;
            this.categoryGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            this.categoryGridControl.Click += new System.EventHandler(this.categoryGridControl_Click);
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colid,
            this.colname,
            this.coldetail,
            this.colimageUrl,
            this.colthumnail,
            this.colsex,
            this.colactive});
            this.gridView1.GridControl = this.categoryGridControl;
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
            this.colid.Width = 87;
            // 
            // colname
            // 
            this.colname.FieldName = "name";
            this.colname.MinWidth = 25;
            this.colname.Name = "colname";
            this.colname.OptionsColumn.AllowEdit = false;
            this.colname.Visible = true;
            this.colname.VisibleIndex = 1;
            this.colname.Width = 242;
            // 
            // coldetail
            // 
            this.coldetail.FieldName = "detail";
            this.coldetail.MinWidth = 25;
            this.coldetail.Name = "coldetail";
            this.coldetail.OptionsColumn.AllowEdit = false;
            this.coldetail.Visible = true;
            this.coldetail.VisibleIndex = 2;
            this.coldetail.Width = 198;
            // 
            // colimageUrl
            // 
            this.colimageUrl.FieldName = "imageUrl";
            this.colimageUrl.MinWidth = 25;
            this.colimageUrl.Name = "colimageUrl";
            this.colimageUrl.OptionsColumn.AllowEdit = false;
            this.colimageUrl.Visible = true;
            this.colimageUrl.VisibleIndex = 3;
            this.colimageUrl.Width = 70;
            // 
            // colthumnail
            // 
            this.colthumnail.FieldName = "thumnail";
            this.colthumnail.MinWidth = 25;
            this.colthumnail.Name = "colthumnail";
            this.colthumnail.OptionsColumn.AllowEdit = false;
            this.colthumnail.Visible = true;
            this.colthumnail.VisibleIndex = 4;
            this.colthumnail.Width = 60;
            // 
            // colsex
            // 
            this.colsex.FieldName = "sex";
            this.colsex.MinWidth = 25;
            this.colsex.Name = "colsex";
            this.colsex.OptionsColumn.AllowEdit = false;
            this.colsex.Visible = true;
            this.colsex.VisibleIndex = 5;
            this.colsex.Width = 44;
            // 
            // colactive
            // 
            this.colactive.FieldName = "active";
            this.colactive.MinWidth = 25;
            this.colactive.Name = "colactive";
            this.colactive.OptionsColumn.AllowEdit = false;
            this.colactive.Visible = true;
            this.colactive.VisibleIndex = 6;
            this.colactive.Width = 54;
            // 
            // picImageIcon
            // 
            this.picImageIcon.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.picImageIcon.ErrorImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picImageIcon.InitialImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picImageIcon.Location = new System.Drawing.Point(1529, 124);
            this.picImageIcon.Name = "picImageIcon";
            this.picImageIcon.Size = new System.Drawing.Size(187, 218);
            this.picImageIcon.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picImageIcon.TabIndex = 53;
            this.picImageIcon.TabStop = false;
            // 
            // picThumbnail
            // 
            this.picThumbnail.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.picThumbnail.ErrorImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picThumbnail.InitialImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picThumbnail.Location = new System.Drawing.Point(1428, 445);
            this.picThumbnail.Name = "picThumbnail";
            this.picThumbnail.Size = new System.Drawing.Size(454, 316);
            this.picThumbnail.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picThumbnail.TabIndex = 54;
            this.picThumbnail.TabStop = false;
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(1183, 695);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 56;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            this.btnCancelAddProvider.Click += new System.EventHandler(this.btnCancelAddProvider_Click);
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(965, 695);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 55;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click);
            // 
            // productBindingSource
            // 
            this.productBindingSource.DataMember = "FK_Product_Category";
            this.productBindingSource.DataSource = this.categoryBindingSource;
            // 
            // btnSaveImg
            // 
            this.btnSaveImg.BackColor = System.Drawing.Color.White;
            this.btnSaveImg.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveImg.ForeColor = System.Drawing.Color.DarkBlue;
            this.btnSaveImg.Location = new System.Drawing.Point(1752, 230);
            this.btnSaveImg.Name = "btnSaveImg";
            this.btnSaveImg.Size = new System.Drawing.Size(147, 52);
            this.btnSaveImg.TabIndex = 64;
            this.btnSaveImg.Text = "Save and get link";
            this.btnSaveImg.UseVisualStyleBackColor = false;
            this.btnSaveImg.Click += new System.EventHandler(this.btnSaveImg_Click);
            // 
            // btnChangeImg
            // 
            this.btnChangeImg.AutoSize = true;
            this.btnChangeImg.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnChangeImg.LinkColor = System.Drawing.SystemColors.MenuHighlight;
            this.btnChangeImg.Location = new System.Drawing.Point(1748, 184);
            this.btnChangeImg.Name = "btnChangeImg";
            this.btnChangeImg.Size = new System.Drawing.Size(151, 25);
            this.btnChangeImg.TabIndex = 63;
            this.btnChangeImg.TabStop = true;
            this.btnChangeImg.Text = "Choose image";
            this.btnChangeImg.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.btnChangeImg_LinkClicked);
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.Color.White;
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.ForeColor = System.Drawing.Color.DarkBlue;
            this.button1.Location = new System.Drawing.Point(1711, 767);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(147, 52);
            this.button1.TabIndex = 66;
            this.button1.Text = "Save and get link";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // linkLabel1
            // 
            this.linkLabel1.AutoSize = true;
            this.linkLabel1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkLabel1.LinkColor = System.Drawing.SystemColors.MenuHighlight;
            this.linkLabel1.Location = new System.Drawing.Point(1524, 778);
            this.linkLabel1.Name = "linkLabel1";
            this.linkLabel1.Size = new System.Drawing.Size(151, 25);
            this.linkLabel1.TabIndex = 65;
            this.linkLabel1.TabStop = true;
            this.linkLabel1.Text = "Choose image";
            this.linkLabel1.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkLabel1_LinkClicked);
            // 
            // activeSpinEdit
            // 
            this.activeSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource, "active", true));
            this.activeSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.activeSpinEdit.Enabled = false;
            this.activeSpinEdit.Location = new System.Drawing.Point(1253, 360);
            this.activeSpinEdit.Name = "activeSpinEdit";
            this.activeSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.activeSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.activeSpinEdit.TabIndex = 14;
            // 
            // cbbActive
            // 
            this.cbbActive.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.categoryBindingSource, "active", true));
            this.cbbActive.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbActive.FormattingEnabled = true;
            this.cbbActive.Location = new System.Drawing.Point(948, 361);
            this.cbbActive.Name = "cbbActive";
            this.cbbActive.Size = new System.Drawing.Size(252, 24);
            this.cbbActive.TabIndex = 72;
            this.cbbActive.SelectedIndexChanged += new System.EventHandler(this.comboBox1_SelectedIndexChanged);
            // 
            // cbbGender
            // 
            this.cbbGender.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbGender.FormattingEnabled = true;
            this.cbbGender.Location = new System.Drawing.Point(948, 307);
            this.cbbGender.Name = "cbbGender";
            this.cbbGender.Size = new System.Drawing.Size(252, 24);
            this.cbbGender.TabIndex = 77;
            this.cbbGender.SelectedIndexChanged += new System.EventHandler(this.cbbGender_SelectedIndexChanged_1);
            // 
            // CategoryForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1940, 837);
            this.Controls.Add(this.cbbGender);
            this.Controls.Add(this.cbbActive);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.linkLabel1);
            this.Controls.Add(this.btnSaveImg);
            this.Controls.Add(this.btnChangeImg);
            this.Controls.Add(this.btnCancelAddProvider);
            this.Controls.Add(this.btnSaveAddProvider);
            this.Controls.Add(this.picImageIcon);
            this.Controls.Add(this.picThumbnail);
            this.Controls.Add(label2);
            this.Controls.Add(c);
            this.Controls.Add(this.categoryGridControl);
            this.Controls.Add(idLabel);
            this.Controls.Add(this.idSpinEdit);
            this.Controls.Add(nameLabel);
            this.Controls.Add(this.nameTextEdit);
            this.Controls.Add(detailLabel);
            this.Controls.Add(this.detailRichTextBox);
            this.Controls.Add(imageUrlLabel);
            this.Controls.Add(this.imageUrlTextEdit);
            this.Controls.Add(thumnailLabel);
            this.Controls.Add(this.thumnailTextEdit);
            this.Controls.Add(sexLabel);
            this.Controls.Add(this.sexSpinEdit);
            this.Controls.Add(activeLabel);
            this.Controls.Add(this.activeSpinEdit);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "CategoryForm";
            this.Text = "CategoryForm1";
            this.Load += new System.EventHandler(this.CategoryForm1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageUrlTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumnailTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sexSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picImageIcon)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picThumbnail)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private ClothesDataSet clothesDataSet;
        private System.Windows.Forms.BindingSource categoryBindingSource;
        private ClothesDataSetTableAdapters.CategoryTableAdapter categoryTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.TextEdit nameTextEdit;
        private System.Windows.Forms.RichTextBox detailRichTextBox;
        private DevExpress.XtraEditors.TextEdit imageUrlTextEdit;
        private DevExpress.XtraEditors.TextEdit thumnailTextEdit;
        private DevExpress.XtraEditors.SpinEdit sexSpinEdit;
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
        private DevExpress.XtraGrid.GridControl categoryGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colid;
        private DevExpress.XtraGrid.Columns.GridColumn colname;
        private DevExpress.XtraGrid.Columns.GridColumn coldetail;
        private DevExpress.XtraGrid.Columns.GridColumn colimageUrl;
        private DevExpress.XtraGrid.Columns.GridColumn colthumnail;
        private DevExpress.XtraGrid.Columns.GridColumn colsex;
        private DevExpress.XtraGrid.Columns.GridColumn colactive;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private System.Windows.Forms.PictureBox picImageIcon;
        private System.Windows.Forms.PictureBox picThumbnail;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private ClothesDataSetTableAdapters.ProductTableAdapter productTableAdapter;
        private System.Windows.Forms.BindingSource productBindingSource;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.LinkLabel linkLabel1;
        private System.Windows.Forms.Button btnSaveImg;
        private System.Windows.Forms.LinkLabel btnChangeImg;
        private System.Windows.Forms.ComboBox cbbActive;
        private DevExpress.XtraEditors.SpinEdit activeSpinEdit;
        private System.Windows.Forms.ComboBox cbbGender;
    }
}