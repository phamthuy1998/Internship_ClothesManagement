namespace ClothesAdmin
{
    partial class WarehouseFormcs
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(WarehouseFormcs));
            this.barManager = new DevExpress.XtraBars.BarManager(this.components);
            this.bar2 = new DevExpress.XtraBars.Bar();
            this.btnReloadProvider = new DevExpress.XtraBars.BarButtonItem();
            this.btnCloseForm = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControl1 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl2 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl3 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl5 = new DevExpress.XtraBars.BarDockControl();
            this.btnDelProvider = new DevExpress.XtraBars.BarButtonItem();
            this.btnSua = new DevExpress.XtraBars.BarButtonItem();
            this.btnTimKiem = new DevExpress.XtraBars.BarButtonItem();
            this.panel1 = new System.Windows.Forms.Panel();
            this.cbProvider = new System.Windows.Forms.CheckBox();
            this.cbCategory = new System.Windows.Forms.CheckBox();
            this.providerComboBox = new System.Windows.Forms.ComboBox();
            this.providerBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.categoryComboBox = new System.Windows.Forms.ComboBox();
            this.categoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.btnExport = new System.Windows.Forms.Button();
            this.sP_InventoryProductBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sP_InventoryProductTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_InventoryProductTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.sP_InventoryProductGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colproductID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coltitle = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcolorName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsizeName = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colquantity = new DevExpress.XtraGrid.Columns.GridColumn();
            this.categoryTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.CategoryTableAdapter();
            this.providerTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProviderTableAdapter();
            label6 = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_InventoryProductBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_InventoryProductGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.Location = new System.Drawing.Point(75, 41);
            label6.Name = "label6";
            label6.Size = new System.Drawing.Size(65, 17);
            label6.TabIndex = 75;
            label6.Text = "Category";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new System.Drawing.Point(634, 41);
            label5.Name = "label5";
            label5.Size = new System.Drawing.Size(61, 17);
            label5.TabIndex = 76;
            label5.Text = "Provider";
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
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnReloadProvider, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnCloseForm, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph)});
            this.bar2.OptionsBar.MultiLine = true;
            this.bar2.OptionsBar.UseWholeRow = true;
            this.bar2.Text = "Main menu";
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
            this.barDockControl1.Size = new System.Drawing.Size(1689, 33);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 689);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1689, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 33);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 656);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1689, 33);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 656);
            // 
            // btnDelProvider
            // 
            this.btnDelProvider.Caption = "Delete";
            this.btnDelProvider.Id = 2;
            this.btnDelProvider.ImageOptions.Image = global::ClothesAdmin.Properties.Resources.delete__1_;
            this.btnDelProvider.ImageOptions.LargeImage = ((System.Drawing.Image)(resources.GetObject("btnDelProvider.ImageOptions.LargeImage")));
            this.btnDelProvider.Name = "btnDelProvider";
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
            this.panel1.Controls.Add(this.cbProvider);
            this.panel1.Controls.Add(this.cbCategory);
            this.panel1.Controls.Add(this.providerComboBox);
            this.panel1.Controls.Add(this.categoryComboBox);
            this.panel1.Controls.Add(this.btnExport);
            this.panel1.Controls.Add(label6);
            this.panel1.Controls.Add(label5);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 33);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1689, 100);
            this.panel1.TabIndex = 4;
            // 
            // cbProvider
            // 
            this.cbProvider.AutoSize = true;
            this.cbProvider.Location = new System.Drawing.Point(610, 41);
            this.cbProvider.Name = "cbProvider";
            this.cbProvider.Size = new System.Drawing.Size(18, 17);
            this.cbProvider.TabIndex = 83;
            this.cbProvider.UseVisualStyleBackColor = true;
            this.cbProvider.CheckedChanged += new System.EventHandler(this.cbProvider_CheckedChanged);
            // 
            // cbCategory
            // 
            this.cbCategory.AutoSize = true;
            this.cbCategory.Location = new System.Drawing.Point(51, 42);
            this.cbCategory.Name = "cbCategory";
            this.cbCategory.Size = new System.Drawing.Size(18, 17);
            this.cbCategory.TabIndex = 82;
            this.cbCategory.UseVisualStyleBackColor = true;
            this.cbCategory.CheckedChanged += new System.EventHandler(this.cbCategory_CheckedChanged_1);
            // 
            // providerComboBox
            // 
            this.providerComboBox.DataSource = this.providerBindingSource;
            this.providerComboBox.DisplayMember = "brandName";
            this.providerComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.providerComboBox.FormattingEnabled = true;
            this.providerComboBox.Location = new System.Drawing.Point(723, 38);
            this.providerComboBox.Name = "providerComboBox";
            this.providerComboBox.Size = new System.Drawing.Size(300, 24);
            this.providerComboBox.TabIndex = 81;
            this.providerComboBox.ValueMember = "id";
            this.providerComboBox.SelectedIndexChanged += new System.EventHandler(this.providerComboBox_SelectedIndexChanged);
            // 
            // providerBindingSource
            // 
            this.providerBindingSource.DataMember = "Provider";
            this.providerBindingSource.DataSource = this.clothesDataSet;
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // categoryComboBox
            // 
            this.categoryComboBox.DataSource = this.categoryBindingSource;
            this.categoryComboBox.DisplayMember = "name";
            this.categoryComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.categoryComboBox.FormattingEnabled = true;
            this.categoryComboBox.Location = new System.Drawing.Point(161, 41);
            this.categoryComboBox.Name = "categoryComboBox";
            this.categoryComboBox.Size = new System.Drawing.Size(300, 24);
            this.categoryComboBox.TabIndex = 81;
            this.categoryComboBox.ValueMember = "id";
            this.categoryComboBox.SelectedIndexChanged += new System.EventHandler(this.categoryComboBox_SelectedIndexChanged);
            // 
            // categoryBindingSource
            // 
            this.categoryBindingSource.DataMember = "Category";
            this.categoryBindingSource.DataSource = this.clothesDataSet;
            // 
            // btnExport
            // 
            this.btnExport.BackColor = System.Drawing.Color.White;
            this.btnExport.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnExport.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnExport.Location = new System.Drawing.Point(1151, 23);
            this.btnExport.Name = "btnExport";
            this.btnExport.Size = new System.Drawing.Size(189, 52);
            this.btnExport.TabIndex = 81;
            this.btnExport.Text = "Export";
            this.btnExport.UseVisualStyleBackColor = false;
            this.btnExport.Click += new System.EventHandler(this.btnExport_Click);
            // 
            // sP_InventoryProductBindingSource
            // 
            this.sP_InventoryProductBindingSource.DataMember = "SP_InventoryProduct";
            this.sP_InventoryProductBindingSource.DataSource = this.clothesDataSet;
            // 
            // sP_InventoryProductTableAdapter
            // 
            this.sP_InventoryProductTableAdapter.ClearBeforeFill = true;
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
            // sP_InventoryProductGridControl
            // 
            this.sP_InventoryProductGridControl.DataSource = this.sP_InventoryProductBindingSource;
            this.sP_InventoryProductGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.sP_InventoryProductGridControl.Location = new System.Drawing.Point(0, 133);
            this.sP_InventoryProductGridControl.MainView = this.gridView1;
            this.sP_InventoryProductGridControl.MenuManager = this.barManager;
            this.sP_InventoryProductGridControl.Name = "sP_InventoryProductGridControl";
            this.sP_InventoryProductGridControl.Size = new System.Drawing.Size(1689, 556);
            this.sP_InventoryProductGridControl.TabIndex = 7;
            this.sP_InventoryProductGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colproductID,
            this.coltitle,
            this.colcolorName,
            this.colsizeName,
            this.colquantity});
            this.gridView1.GridControl = this.sP_InventoryProductGridControl;
            this.gridView1.Name = "gridView1";
            // 
            // colproductID
            // 
            this.colproductID.FieldName = "productID";
            this.colproductID.MinWidth = 25;
            this.colproductID.Name = "colproductID";
            this.colproductID.OptionsColumn.AllowEdit = false;
            this.colproductID.Visible = true;
            this.colproductID.VisibleIndex = 0;
            this.colproductID.Width = 90;
            // 
            // coltitle
            // 
            this.coltitle.FieldName = "title";
            this.coltitle.MinWidth = 25;
            this.coltitle.Name = "coltitle";
            this.coltitle.OptionsColumn.AllowEdit = false;
            this.coltitle.Visible = true;
            this.coltitle.VisibleIndex = 1;
            this.coltitle.Width = 813;
            // 
            // colcolorName
            // 
            this.colcolorName.FieldName = "colorName";
            this.colcolorName.MinWidth = 25;
            this.colcolorName.Name = "colcolorName";
            this.colcolorName.OptionsColumn.AllowEdit = false;
            this.colcolorName.Visible = true;
            this.colcolorName.VisibleIndex = 2;
            this.colcolorName.Width = 305;
            // 
            // colsizeName
            // 
            this.colsizeName.FieldName = "sizeName";
            this.colsizeName.MinWidth = 25;
            this.colsizeName.Name = "colsizeName";
            this.colsizeName.OptionsColumn.AllowEdit = false;
            this.colsizeName.Visible = true;
            this.colsizeName.VisibleIndex = 3;
            this.colsizeName.Width = 279;
            // 
            // colquantity
            // 
            this.colquantity.FieldName = "quantity";
            this.colquantity.MinWidth = 25;
            this.colquantity.Name = "colquantity";
            this.colquantity.OptionsColumn.AllowEdit = false;
            this.colquantity.Visible = true;
            this.colquantity.VisibleIndex = 4;
            this.colquantity.Width = 172;
            // 
            // categoryTableAdapter
            // 
            this.categoryTableAdapter.ClearBeforeFill = true;
            // 
            // providerTableAdapter
            // 
            this.providerTableAdapter.ClearBeforeFill = true;
            // 
            // WarehouseFormcs
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1689, 689);
            this.Controls.Add(this.sP_InventoryProductGridControl);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "WarehouseFormcs";
            this.Text = "WarehouseFormcs";
            this.Load += new System.EventHandler(this.WarehouseFormcs_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_InventoryProductBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_InventoryProductGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraBars.BarManager barManager;
        private DevExpress.XtraBars.Bar bar2;
        private DevExpress.XtraBars.BarButtonItem btnReloadProvider;
        private DevExpress.XtraBars.BarButtonItem btnCloseForm;
        private DevExpress.XtraBars.BarDockControl barDockControl1;
        private DevExpress.XtraBars.BarDockControl barDockControl2;
        private DevExpress.XtraBars.BarDockControl barDockControl3;
        private DevExpress.XtraBars.BarDockControl barDockControl5;
        private System.Windows.Forms.Panel panel1;
        private DevExpress.XtraBars.BarButtonItem btnDelProvider;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private System.Windows.Forms.ToolStrip fillToolStrip;
        private System.Windows.Forms.ToolStripLabel categoryIdToolStripLabel;
        private System.Windows.Forms.ToolStripTextBox categoryIdToolStripTextBox;
        private System.Windows.Forms.ToolStripLabel providerIDToolStripLabel;
        private System.Windows.Forms.ToolStripTextBox providerIDToolStripTextBox;
        private System.Windows.Forms.ToolStripButton fillToolStripButton;
        private System.Windows.Forms.BindingSource sP_InventoryProductBindingSource;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.SP_InventoryProductTableAdapter sP_InventoryProductTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraGrid.GridControl sP_InventoryProductGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private DevExpress.XtraGrid.Columns.GridColumn colproductID;
        private DevExpress.XtraGrid.Columns.GridColumn coltitle;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorName;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeName;
        private DevExpress.XtraGrid.Columns.GridColumn colquantity;
        private System.Windows.Forms.Button btnExport;
        private System.Windows.Forms.BindingSource categoryBindingSource;
        private ClothesDataSetTableAdapters.CategoryTableAdapter categoryTableAdapter;
        private System.Windows.Forms.ComboBox categoryComboBox;
        private System.Windows.Forms.BindingSource providerBindingSource;
        private ClothesDataSetTableAdapters.ProviderTableAdapter providerTableAdapter;
        private System.Windows.Forms.ComboBox providerComboBox;
        private System.Windows.Forms.CheckBox cbCategory;
        private System.Windows.Forms.CheckBox cbProvider;
    }
}