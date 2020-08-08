namespace ClothesAdmin
{
    partial class SizeForm
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
            System.Windows.Forms.Label sizeNameLabel;
            System.Windows.Forms.Label descriptionLabel;
            System.Windows.Forms.Label activeLabel;
            System.Windows.Forms.Label label1;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SizeForm));
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
            this.sizeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sizeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SizeTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.sizeGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsizeName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldescription = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colactive = new DevExpress.XtraGrid.Columns.GridColumn();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.sizeNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.activeSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.descriptionRichTextBox = new System.Windows.Forms.RichTextBox();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.productSizeColorBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.productSizeColorTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductSizeColorTableAdapter();
            idLabel = new System.Windows.Forms.Label();
            sizeNameLabel = new System.Windows.Forms.Label();
            descriptionLabel = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(869, 142);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(21, 17);
            idLabel.TabIndex = 5;
            idLabel.Text = "ID";
            // 
            // sizeNameLabel
            // 
            sizeNameLabel.AutoSize = true;
            sizeNameLabel.Location = new System.Drawing.Point(869, 191);
            sizeNameLabel.Name = "sizeNameLabel";
            sizeNameLabel.Size = new System.Drawing.Size(74, 17);
            sizeNameLabel.TabIndex = 7;
            sizeNameLabel.Text = "Size name";
            // 
            // descriptionLabel
            // 
            descriptionLabel.AutoSize = true;
            descriptionLabel.Location = new System.Drawing.Point(869, 246);
            descriptionLabel.Name = "descriptionLabel";
            descriptionLabel.Size = new System.Drawing.Size(72, 17);
            descriptionLabel.TabIndex = 9;
            descriptionLabel.Text = "Décription";
            // 
            // activeLabel
            // 
            activeLabel.AutoSize = true;
            activeLabel.Location = new System.Drawing.Point(869, 368);
            activeLabel.Name = "activeLabel";
            activeLabel.Size = new System.Drawing.Size(46, 17);
            activeLabel.TabIndex = 11;
            activeLabel.Text = "Active";
            activeLabel.Click += new System.EventHandler(this.activeLabel_Click);
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label1.Location = new System.Drawing.Point(861, 52);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(163, 36);
            label1.TabIndex = 24;
            label1.Text = "Size detail";
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
            this.barDockControl1.Size = new System.Drawing.Size(1437, 33);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 686);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1437, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 33);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 653);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1437, 33);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 653);
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
            // sizeBindingSource
            // 
            this.sizeBindingSource.DataMember = "Size";
            this.sizeBindingSource.DataSource = this.clothesDataSet;
            // 
            // sizeTableAdapter
            // 
            this.sizeTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.InvoiceTableAdapter = null;
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = null;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = this.sizeTableAdapter;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // sizeGridControl
            // 
            this.sizeGridControl.DataSource = this.sizeBindingSource;
            this.sizeGridControl.Dock = System.Windows.Forms.DockStyle.Left;
            this.sizeGridControl.Location = new System.Drawing.Point(0, 33);
            this.sizeGridControl.MainView = this.gridView1;
            this.sizeGridControl.MenuManager = this.barManager;
            this.sizeGridControl.Name = "sizeGridControl";
            this.sizeGridControl.Size = new System.Drawing.Size(814, 653);
            this.sizeGridControl.TabIndex = 5;
            this.sizeGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colid,
            this.colsizeName,
            this.coldescription,
            this.colactive});
            this.gridView1.GridControl = this.sizeGridControl;
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
            // colsizeName
            // 
            this.colsizeName.FieldName = "sizeName";
            this.colsizeName.MinWidth = 25;
            this.colsizeName.Name = "colsizeName";
            this.colsizeName.OptionsColumn.AllowEdit = false;
            this.colsizeName.Visible = true;
            this.colsizeName.VisibleIndex = 1;
            this.colsizeName.Width = 94;
            // 
            // coldescription
            // 
            this.coldescription.FieldName = "description";
            this.coldescription.MinWidth = 25;
            this.coldescription.Name = "coldescription";
            this.coldescription.OptionsColumn.AllowEdit = false;
            this.coldescription.Visible = true;
            this.coldescription.VisibleIndex = 2;
            this.coldescription.Width = 94;
            // 
            // colactive
            // 
            this.colactive.FieldName = "active";
            this.colactive.MinWidth = 25;
            this.colactive.Name = "colactive";
            this.colactive.OptionsColumn.AllowEdit = false;
            this.colactive.Visible = true;
            this.colactive.VisibleIndex = 3;
            this.colactive.Width = 94;
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sizeBindingSource, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Location = new System.Drawing.Point(976, 139);
            this.idSpinEdit.MenuManager = this.barManager;
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 6;
            // 
            // sizeNameTextEdit
            // 
            this.sizeNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sizeBindingSource, "sizeName", true));
            this.sizeNameTextEdit.Location = new System.Drawing.Point(976, 188);
            this.sizeNameTextEdit.MenuManager = this.barManager;
            this.sizeNameTextEdit.Name = "sizeNameTextEdit";
            this.sizeNameTextEdit.Size = new System.Drawing.Size(338, 22);
            this.sizeNameTextEdit.TabIndex = 8;
            // 
            // activeSpinEdit
            // 
            this.activeSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sizeBindingSource, "active", true));
            this.activeSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.activeSpinEdit.Location = new System.Drawing.Point(976, 365);
            this.activeSpinEdit.MenuManager = this.barManager;
            this.activeSpinEdit.Name = "activeSpinEdit";
            this.activeSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.activeSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.activeSpinEdit.TabIndex = 12;
            // 
            // descriptionRichTextBox
            // 
            this.descriptionRichTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.sizeBindingSource, "description", true));
            this.descriptionRichTextBox.Location = new System.Drawing.Point(976, 243);
            this.descriptionRichTextBox.Name = "descriptionRichTextBox";
            this.descriptionRichTextBox.Size = new System.Drawing.Size(338, 96);
            this.descriptionRichTextBox.TabIndex = 25;
            this.descriptionRichTextBox.Text = "";
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(1191, 485);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 39;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            this.btnCancelAddProvider.Click += new System.EventHandler(this.btnCancelAddProvider_Click);
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(982, 485);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 38;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click);
            // 
            // productSizeColorBindingSource
            // 
            this.productSizeColorBindingSource.DataMember = "FK_ProductSizeColor_Size1";
            this.productSizeColorBindingSource.DataSource = this.sizeBindingSource;
            // 
            // productSizeColorTableAdapter
            // 
            this.productSizeColorTableAdapter.ClearBeforeFill = true;
            // 
            // SizeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1437, 686);
            this.Controls.Add(this.btnCancelAddProvider);
            this.Controls.Add(this.btnSaveAddProvider);
            this.Controls.Add(this.descriptionRichTextBox);
            this.Controls.Add(label1);
            this.Controls.Add(idLabel);
            this.Controls.Add(this.idSpinEdit);
            this.Controls.Add(sizeNameLabel);
            this.Controls.Add(this.sizeNameTextEdit);
            this.Controls.Add(descriptionLabel);
            this.Controls.Add(activeLabel);
            this.Controls.Add(this.activeSpinEdit);
            this.Controls.Add(this.sizeGridControl);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "SizeForm";
            this.Text = "SizeForm";
            this.Load += new System.EventHandler(this.SizeForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productSizeColorBindingSource)).EndInit();
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
        private System.Windows.Forms.BindingSource sizeBindingSource;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.SizeTableAdapter sizeTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.TextEdit sizeNameTextEdit;
        private DevExpress.XtraEditors.SpinEdit activeSpinEdit;
        private DevExpress.XtraGrid.GridControl sizeGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colid;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeName;
        private DevExpress.XtraGrid.Columns.GridColumn coldescription;
        private DevExpress.XtraGrid.Columns.GridColumn colactive;
        private System.Windows.Forms.RichTextBox descriptionRichTextBox;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private System.Windows.Forms.BindingSource productSizeColorBindingSource;
        private ClothesDataSetTableAdapters.ProductSizeColorTableAdapter productSizeColorTableAdapter;
    }
}