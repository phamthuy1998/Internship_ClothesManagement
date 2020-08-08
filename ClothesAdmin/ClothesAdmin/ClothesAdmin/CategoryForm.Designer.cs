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
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label idLabel;
            System.Windows.Forms.Label nameLabel;
            System.Windows.Forms.Label detailLabel;
            System.Windows.Forms.Label imageUrlLabel;
            System.Windows.Forms.Label thumnailLabel;
            System.Windows.Forms.Label sexLabel;
            System.Windows.Forms.Label activeLabel;
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label label3;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CategoryForm));
            this.bar6 = new DevExpress.XtraBars.Bar();
            this.bar1 = new DevExpress.XtraBars.Bar();
            this.categoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.categoryTableAdapterBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.categoryRowBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.categoryDataTableBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.utilitiesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.barManager = new DevExpress.XtraBars.BarManager(this.components);
            this.bar2 = new DevExpress.XtraBars.Bar();
            this.btnAdd = new DevExpress.XtraBars.BarButtonItem();
            this.btnDel = new DevExpress.XtraBars.BarButtonItem();
            this.btnReload = new DevExpress.XtraBars.BarButtonItem();
            this.btnCloseForm = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControl1 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl2 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl3 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl5 = new DevExpress.XtraBars.BarDockControl();
            this.btnSua = new DevExpress.XtraBars.BarButtonItem();
            this.btnTimKiem = new DevExpress.XtraBars.BarButtonItem();
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.categoryBindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            this.categoryTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.CategoryTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.categoryGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colname = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldetail = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colimageUrl = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colthumnail = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsex = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colactive = new DevExpress.XtraGrid.Columns.GridColumn();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.nameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.imageUrlTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.thumnailTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.sexSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.activeSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.detailRichTextBox = new System.Windows.Forms.RichTextBox();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.picThumbnail = new System.Windows.Forms.PictureBox();
            this.picImageIcon = new System.Windows.Forms.PictureBox();
            label1 = new System.Windows.Forms.Label();
            idLabel = new System.Windows.Forms.Label();
            nameLabel = new System.Windows.Forms.Label();
            detailLabel = new System.Windows.Forms.Label();
            imageUrlLabel = new System.Windows.Forms.Label();
            thumnailLabel = new System.Windows.Forms.Label();
            sexLabel = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryTableAdapterBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryRowBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryDataTableBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.utilitiesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageUrlTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumnailTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sexSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picThumbnail)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.picImageIcon)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label1.Location = new System.Drawing.Point(765, 35);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(229, 36);
            label1.TabIndex = 23;
            label1.Text = "Category detail";
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(761, 208);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(21, 17);
            idLabel.TabIndex = 23;
            idLabel.Text = "ID";
            // 
            // nameLabel
            // 
            nameLabel.AutoSize = true;
            nameLabel.Location = new System.Drawing.Point(761, 254);
            nameLabel.Name = "nameLabel";
            nameLabel.Size = new System.Drawing.Size(45, 17);
            nameLabel.TabIndex = 25;
            nameLabel.Text = "Name";
            // 
            // detailLabel
            // 
            detailLabel.AutoSize = true;
            detailLabel.Location = new System.Drawing.Point(761, 505);
            detailLabel.Name = "detailLabel";
            detailLabel.Size = new System.Drawing.Size(44, 17);
            detailLabel.TabIndex = 27;
            detailLabel.Text = "Detail";
            // 
            // imageUrlLabel
            // 
            imageUrlLabel.AutoSize = true;
            imageUrlLabel.Location = new System.Drawing.Point(761, 307);
            imageUrlLabel.Name = "imageUrlLabel";
            imageUrlLabel.Size = new System.Drawing.Size(54, 17);
            imageUrlLabel.TabIndex = 29;
            imageUrlLabel.Text = "icon url";
            // 
            // thumnailLabel
            // 
            thumnailLabel.AutoSize = true;
            thumnailLabel.Location = new System.Drawing.Point(761, 349);
            thumnailLabel.Name = "thumnailLabel";
            thumnailLabel.Size = new System.Drawing.Size(94, 17);
            thumnailLabel.TabIndex = 31;
            thumnailLabel.Text = "Thumbnail url";
            // 
            // sexLabel
            // 
            sexLabel.AutoSize = true;
            sexLabel.Location = new System.Drawing.Point(761, 394);
            sexLabel.Name = "sexLabel";
            sexLabel.Size = new System.Drawing.Size(31, 17);
            sexLabel.TabIndex = 33;
            sexLabel.Text = "Sex";
            // 
            // activeLabel
            // 
            activeLabel.AutoSize = true;
            activeLabel.Location = new System.Drawing.Point(761, 443);
            activeLabel.Name = "activeLabel";
            activeLabel.Size = new System.Drawing.Size(46, 17);
            activeLabel.TabIndex = 35;
            activeLabel.Text = "Active";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(1281, 127);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(34, 17);
            label2.TabIndex = 29;
            label2.Text = "Icon";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(1281, 328);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(74, 17);
            label3.TabIndex = 31;
            label3.Text = "Thumbnail";
            // 
            // bar6
            // 
            this.bar6.BarName = "Main menu";
            this.bar6.DockCol = 0;
            this.bar6.DockRow = 0;
            this.bar6.DockStyle = DevExpress.XtraBars.BarDockStyle.Top;
            this.bar6.OptionsBar.MultiLine = true;
            this.bar6.OptionsBar.UseWholeRow = true;
            this.bar6.Text = "Main menu";
            // 
            // bar1
            // 
            this.bar1.BarName = "Main menu";
            this.bar1.DockCol = 0;
            this.bar1.DockRow = 0;
            this.bar1.DockStyle = DevExpress.XtraBars.BarDockStyle.Top;
            this.bar1.OptionsBar.MultiLine = true;
            this.bar1.OptionsBar.UseWholeRow = true;
            this.bar1.Text = "Main menu";
            // 
            // categoryBindingSource
            // 
            this.categoryBindingSource.DataSource = typeof(ClothesAdmin.Category);
            // 
            // categoryTableAdapterBindingSource
            // 
            this.categoryTableAdapterBindingSource.DataSource = typeof(ClothesAdmin.ClothesDataSetTableAdapters.CategoryTableAdapter);
            // 
            // categoryRowBindingSource
            // 
            this.categoryRowBindingSource.DataSource = typeof(ClothesAdmin.ClothesDataSet.CategoryRow);
            // 
            // categoryDataTableBindingSource
            // 
            this.categoryDataTableBindingSource.DataSource = typeof(ClothesAdmin.ClothesDataSet.CategoryDataTable);
            // 
            // utilitiesBindingSource
            // 
            this.utilitiesBindingSource.DataSource = typeof(SqlServerTypes.Utilities);
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
            this.btnAdd,
            this.btnDel,
            this.btnSua,
            this.btnReload,
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
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnAdd, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnDel, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnReload, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnCloseForm, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph)});
            this.bar2.OptionsBar.MultiLine = true;
            this.bar2.OptionsBar.UseWholeRow = true;
            this.bar2.Text = "Main menu";
            // 
            // btnAdd
            // 
            this.btnAdd.Caption = "Add new";
            this.btnAdd.Id = 0;
            this.btnAdd.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.add;
            this.btnAdd.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnAdd.ImageOptions.LargeImage")));
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.btnAdd_ItemClick);
            // 
            // btnDel
            // 
            this.btnDel.Caption = "Delete";
            this.btnDel.Id = 2;
            this.btnDel.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.delete__1_;
            this.btnDel.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnDel.ImageOptions.LargeImage")));
            this.btnDel.Name = "btnDel";
            // 
            // btnReload
            // 
            this.btnReload.Caption = "Reload";
            this.btnReload.Id = 5;
            this.btnReload.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.exchange;
            this.btnReload.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnReload.ImageOptions.LargeImage")));
            this.btnReload.Name = "btnReload";
            // 
            // btnCloseForm
            // 
            this.btnCloseForm.Caption = "Close form";
            this.btnCloseForm.Id = 10;
            this.btnCloseForm.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.close;
            this.btnCloseForm.Name = "btnCloseForm";
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
            this.barDockControl2.Location = new System.Drawing.Point(0, 1019);
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
            this.barDockControl3.Size = new System.Drawing.Size(0, 986);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1940, 33);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 986);
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
            // categoryBindingSource1
            // 
            this.categoryBindingSource1.DataMember = "Category";
            this.categoryBindingSource1.DataSource = this.clothesDataSet;
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
            // categoryGridControl
            // 
            this.categoryGridControl.DataSource = this.categoryBindingSource1;
            this.categoryGridControl.Dock = System.Windows.Forms.DockStyle.Left;
            this.categoryGridControl.Location = new System.Drawing.Point(0, 33);
            this.categoryGridControl.MainView = this.gridView1;
            this.categoryGridControl.MenuManager = this.barManager;
            this.categoryGridControl.Name = "categoryGridControl";
            this.categoryGridControl.Size = new System.Drawing.Size(711, 986);
            this.categoryGridControl.TabIndex = 4;
            this.categoryGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
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
            this.colid.Width = 94;
            // 
            // colname
            // 
            this.colname.FieldName = "name";
            this.colname.MinWidth = 25;
            this.colname.Name = "colname";
            this.colname.OptionsColumn.AllowEdit = false;
            this.colname.Visible = true;
            this.colname.VisibleIndex = 1;
            this.colname.Width = 94;
            // 
            // coldetail
            // 
            this.coldetail.FieldName = "detail";
            this.coldetail.MinWidth = 25;
            this.coldetail.Name = "coldetail";
            this.coldetail.OptionsColumn.AllowEdit = false;
            this.coldetail.Visible = true;
            this.coldetail.VisibleIndex = 2;
            this.coldetail.Width = 94;
            // 
            // colimageUrl
            // 
            this.colimageUrl.FieldName = "imageUrl";
            this.colimageUrl.MinWidth = 25;
            this.colimageUrl.Name = "colimageUrl";
            this.colimageUrl.OptionsColumn.AllowEdit = false;
            this.colimageUrl.Visible = true;
            this.colimageUrl.VisibleIndex = 3;
            this.colimageUrl.Width = 94;
            // 
            // colthumnail
            // 
            this.colthumnail.FieldName = "thumnail";
            this.colthumnail.MinWidth = 25;
            this.colthumnail.Name = "colthumnail";
            this.colthumnail.OptionsColumn.AllowEdit = false;
            this.colthumnail.Visible = true;
            this.colthumnail.VisibleIndex = 4;
            this.colthumnail.Width = 94;
            // 
            // colsex
            // 
            this.colsex.FieldName = "sex";
            this.colsex.MinWidth = 25;
            this.colsex.Name = "colsex";
            this.colsex.OptionsColumn.AllowEdit = false;
            this.colsex.Visible = true;
            this.colsex.VisibleIndex = 5;
            this.colsex.Width = 94;
            // 
            // colactive
            // 
            this.colactive.FieldName = "active";
            this.colactive.MinWidth = 25;
            this.colactive.Name = "colactive";
            this.colactive.OptionsColumn.AllowEdit = false;
            this.colactive.Visible = true;
            this.colactive.VisibleIndex = 6;
            this.colactive.Width = 94;
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource1, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Enabled = false;
            this.idSpinEdit.Location = new System.Drawing.Point(883, 205);
            this.idSpinEdit.MenuManager = this.barManager;
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 24;
            // 
            // nameTextEdit
            // 
            this.nameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource1, "name", true));
            this.nameTextEdit.Location = new System.Drawing.Point(883, 251);
            this.nameTextEdit.MenuManager = this.barManager;
            this.nameTextEdit.Name = "nameTextEdit";
            this.nameTextEdit.Size = new System.Drawing.Size(333, 22);
            this.nameTextEdit.TabIndex = 26;
            // 
            // imageUrlTextEdit
            // 
            this.imageUrlTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource1, "imageUrl", true));
            this.imageUrlTextEdit.Location = new System.Drawing.Point(883, 304);
            this.imageUrlTextEdit.MenuManager = this.barManager;
            this.imageUrlTextEdit.Name = "imageUrlTextEdit";
            this.imageUrlTextEdit.Size = new System.Drawing.Size(333, 22);
            this.imageUrlTextEdit.TabIndex = 30;
            this.imageUrlTextEdit.EditValueChanged += new System.EventHandler(this.imageUrlTextEdit_EditValueChanged);
            // 
            // thumnailTextEdit
            // 
            this.thumnailTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource1, "thumnail", true));
            this.thumnailTextEdit.Location = new System.Drawing.Point(883, 346);
            this.thumnailTextEdit.MenuManager = this.barManager;
            this.thumnailTextEdit.Name = "thumnailTextEdit";
            this.thumnailTextEdit.Size = new System.Drawing.Size(333, 22);
            this.thumnailTextEdit.TabIndex = 32;
            this.thumnailTextEdit.EditValueChanged += new System.EventHandler(this.thumnailTextEdit_EditValueChanged);
            // 
            // sexSpinEdit
            // 
            this.sexSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource1, "sex", true));
            this.sexSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.sexSpinEdit.Location = new System.Drawing.Point(883, 391);
            this.sexSpinEdit.MenuManager = this.barManager;
            this.sexSpinEdit.Name = "sexSpinEdit";
            this.sexSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.sexSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.sexSpinEdit.TabIndex = 34;
            // 
            // activeSpinEdit
            // 
            this.activeSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.categoryBindingSource1, "active", true));
            this.activeSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.activeSpinEdit.Location = new System.Drawing.Point(883, 440);
            this.activeSpinEdit.MenuManager = this.barManager;
            this.activeSpinEdit.Name = "activeSpinEdit";
            this.activeSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.activeSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.activeSpinEdit.TabIndex = 36;
            // 
            // detailRichTextBox
            // 
            this.detailRichTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.categoryBindingSource1, "detail", true));
            this.detailRichTextBox.Location = new System.Drawing.Point(883, 505);
            this.detailRichTextBox.Name = "detailRichTextBox";
            this.detailRichTextBox.Size = new System.Drawing.Size(333, 166);
            this.detailRichTextBox.TabIndex = 37;
            this.detailRichTextBox.Text = "";
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(1228, 857);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 39;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(1019, 857);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 38;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            // 
            // picThumbnail
            // 
            this.picThumbnail.ErrorImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picThumbnail.InitialImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picThumbnail.Location = new System.Drawing.Point(1284, 371);
            this.picThumbnail.Name = "picThumbnail";
            this.picThumbnail.Size = new System.Drawing.Size(616, 349);
            this.picThumbnail.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picThumbnail.TabIndex = 50;
            this.picThumbnail.TabStop = false;
            // 
            // picImageIcon
            // 
            this.picImageIcon.ErrorImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picImageIcon.InitialImage = global::ClothesAdmin.Properties.Resources.no_image;
            this.picImageIcon.Location = new System.Drawing.Point(1515, 127);
            this.picImageIcon.Name = "picImageIcon";
            this.picImageIcon.Size = new System.Drawing.Size(175, 218);
            this.picImageIcon.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picImageIcon.TabIndex = 50;
            this.picImageIcon.TabStop = false;
            // 
            // CategoryForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1940, 1019);
            this.Controls.Add(this.picImageIcon);
            this.Controls.Add(this.picThumbnail);
            this.Controls.Add(this.btnCancelAddProvider);
            this.Controls.Add(this.btnSaveAddProvider);
            this.Controls.Add(this.detailRichTextBox);
            this.Controls.Add(idLabel);
            this.Controls.Add(this.idSpinEdit);
            this.Controls.Add(nameLabel);
            this.Controls.Add(this.nameTextEdit);
            this.Controls.Add(detailLabel);
            this.Controls.Add(label2);
            this.Controls.Add(imageUrlLabel);
            this.Controls.Add(this.imageUrlTextEdit);
            this.Controls.Add(label3);
            this.Controls.Add(thumnailLabel);
            this.Controls.Add(this.thumnailTextEdit);
            this.Controls.Add(sexLabel);
            this.Controls.Add(this.sexSpinEdit);
            this.Controls.Add(activeLabel);
            this.Controls.Add(this.activeSpinEdit);
            this.Controls.Add(label1);
            this.Controls.Add(this.categoryGridControl);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "CategoryForm";
            this.Text = "Category";
            this.Load += new System.EventHandler(this.CategoryForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryTableAdapterBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryRowBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryDataTableBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.utilitiesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageUrlTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.thumnailTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sexSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picThumbnail)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.picImageIcon)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraBars.Bar bar6;
        private DevExpress.XtraBars.Bar bar1;
        private System.Windows.Forms.BindingSource categoryRowBindingSource;
        private System.Windows.Forms.BindingSource categoryTableAdapterBindingSource;
        private System.Windows.Forms.BindingSource categoryDataTableBindingSource;
        private System.Windows.Forms.BindingSource categoryBindingSource;
        private System.Windows.Forms.BindingSource utilitiesBindingSource;
        private DevExpress.XtraBars.BarManager barManager;
        private DevExpress.XtraBars.Bar bar2;
        private DevExpress.XtraBars.BarButtonItem btnAdd;
        private DevExpress.XtraBars.BarButtonItem btnDel;
        private DevExpress.XtraBars.BarButtonItem btnReload;
        private DevExpress.XtraBars.BarButtonItem btnCloseForm;
        private DevExpress.XtraBars.BarDockControl barDockControl1;
        private DevExpress.XtraBars.BarDockControl barDockControl2;
        private DevExpress.XtraBars.BarDockControl barDockControl3;
        private DevExpress.XtraBars.BarDockControl barDockControl5;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private ClothesDataSet clothesDataSet;
        private System.Windows.Forms.BindingSource categoryBindingSource1;
        private ClothesDataSetTableAdapters.CategoryTableAdapter categoryTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraGrid.GridControl categoryGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colid;
        private DevExpress.XtraGrid.Columns.GridColumn colname;
        private DevExpress.XtraGrid.Columns.GridColumn coldetail;
        private DevExpress.XtraGrid.Columns.GridColumn colimageUrl;
        private DevExpress.XtraGrid.Columns.GridColumn colthumnail;
        private DevExpress.XtraGrid.Columns.GridColumn colsex;
        private DevExpress.XtraGrid.Columns.GridColumn colactive;
        private System.Windows.Forms.RichTextBox detailRichTextBox;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.TextEdit nameTextEdit;
        private DevExpress.XtraEditors.TextEdit imageUrlTextEdit;
        private DevExpress.XtraEditors.TextEdit thumnailTextEdit;
        private DevExpress.XtraEditors.SpinEdit sexSpinEdit;
        private DevExpress.XtraEditors.SpinEdit activeSpinEdit;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private System.Windows.Forms.PictureBox picImageIcon;
        private System.Windows.Forms.PictureBox picThumbnail;
    }
}