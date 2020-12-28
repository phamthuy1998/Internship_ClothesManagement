namespace ClothesAdmin
{
    partial class ShopDataForm
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
            System.Windows.Forms.Label profitLabel;
            System.Windows.Forms.Label idLabel;
            System.Windows.Forms.Label label1;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ShopDataForm));
            this.profitSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.shopDataBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.idLabel1 = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.shopDataTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ShopDataTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.btnCloseForm = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControlRight = new DevExpress.XtraBars.BarDockControl();
            this.addressTableAdapter1 = new ClothesAdmin.ClothesDataSetTableAdapters.AddressTableAdapter();
            this.barManager = new DevExpress.XtraBars.BarManager(this.components);
            this.bar2 = new DevExpress.XtraBars.Bar();
            this.btnReloadProvider = new DevExpress.XtraBars.BarButtonItem();
            this.barButtonItem1 = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControl1 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl2 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl3 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl5 = new DevExpress.XtraBars.BarDockControl();
            this.btnSua = new DevExpress.XtraBars.BarButtonItem();
            this.btnTimKiem = new DevExpress.XtraBars.BarButtonItem();
            profitLabel = new System.Windows.Forms.Label();
            idLabel = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.profitSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.shopDataBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            this.SuspendLayout();
            // 
            // profitLabel
            // 
            profitLabel.AutoSize = true;
            profitLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 19.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            profitLabel.ForeColor = System.Drawing.Color.Navy;
            profitLabel.Location = new System.Drawing.Point(98, 23);
            profitLabel.Name = "profitLabel";
            profitLabel.Size = new System.Drawing.Size(439, 38);
            profitLabel.TabIndex = 3;
            profitLabel.Text = "Set up profit on per product";
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(308, 202);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(23, 17);
            idLabel.TabIndex = 4;
            idLabel.Text = "id:";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 19.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label1.ForeColor = System.Drawing.Color.Navy;
            label1.Location = new System.Drawing.Point(456, 116);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(47, 38);
            label1.TabIndex = 3;
            label1.Text = "%";
            // 
            // profitSpinEdit
            // 
            this.profitSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.shopDataBindingSource, "profit", true));
            this.profitSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.profitSpinEdit.Location = new System.Drawing.Point(241, 113);
            this.profitSpinEdit.Name = "profitSpinEdit";
            this.profitSpinEdit.Properties.Appearance.Font = new System.Drawing.Font("Microsoft Sans Serif", 19.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.profitSpinEdit.Properties.Appearance.ForeColor = System.Drawing.Color.Navy;
            this.profitSpinEdit.Properties.Appearance.Options.UseFont = true;
            this.profitSpinEdit.Properties.Appearance.Options.UseForeColor = true;
            this.profitSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.profitSpinEdit.Size = new System.Drawing.Size(191, 44);
            this.profitSpinEdit.TabIndex = 4;
            // 
            // shopDataBindingSource
            // 
            this.shopDataBindingSource.DataMember = "ShopData";
            this.shopDataBindingSource.DataSource = this.clothesDataSet;
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // idLabel1
            // 
            this.idLabel1.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.shopDataBindingSource, "id", true));
            this.idLabel1.Location = new System.Drawing.Point(387, 196);
            this.idLabel1.Name = "idLabel1";
            this.idLabel1.Size = new System.Drawing.Size(100, 23);
            this.idLabel1.TabIndex = 5;
            this.idLabel1.Text = "label1";
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.btnSaveAddProvider);
            this.panel1.Controls.Add(label1);
            this.panel1.Controls.Add(profitLabel);
            this.panel1.Controls.Add(this.profitSpinEdit);
            this.panel1.Location = new System.Drawing.Point(266, 79);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(838, 430);
            this.panel1.TabIndex = 6;
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(210, 252);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(250, 52);
            this.btnSaveAddProvider.TabIndex = 58;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click);
            // 
            // shopDataTableAdapter
            // 
            this.shopDataTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.ProductTableAdapter = null;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.QuestionTableAdapter = null;
            this.tableAdapterManager.RatingTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.ShopDataTableAdapter = this.shopDataTableAdapter;
            this.tableAdapterManager.ShopInfoTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.TypeNotiTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // btnCloseForm
            // 
            this.btnCloseForm.Caption = "Close form";
            this.btnCloseForm.Id = 10;
            this.btnCloseForm.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("btnCloseForm.ImageOptions.Image")));
            this.btnCloseForm.Name = "btnCloseForm";
            // 
            // barDockControlRight
            // 
            this.barDockControlRight.CausesValidation = false;
            this.barDockControlRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControlRight.Location = new System.Drawing.Point(1174, 33);
            this.barDockControlRight.Manager = null;
            this.barDockControlRight.Size = new System.Drawing.Size(0, 760);
            // 
            // addressTableAdapter1
            // 
            this.addressTableAdapter1.ClearBeforeFill = true;
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
            this.btnSua,
            this.btnReloadProvider,
            this.btnTimKiem,
            this.barButtonItem1});
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
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.barButtonItem1, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph)});
            this.bar2.OptionsBar.MultiLine = true;
            this.bar2.OptionsBar.UseWholeRow = true;
            this.bar2.Text = "Main menu";
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
            // barButtonItem1
            // 
            this.barButtonItem1.Caption = "Close form";
            this.barButtonItem1.Id = 10;
            this.barButtonItem1.ImageOptions.Image = ((System.Drawing.Image)(resources.GetObject("barButtonItem1.ImageOptions.Image")));
            this.barButtonItem1.Name = "barButtonItem1";
            this.barButtonItem1.ItemClick += new DevExpress.XtraBars.ItemClickEventHandler(this.barButtonItem1_ItemClick);
            // 
            // barDockControl1
            // 
            this.barDockControl1.CausesValidation = false;
            this.barDockControl1.Dock = System.Windows.Forms.DockStyle.Top;
            this.barDockControl1.Location = new System.Drawing.Point(0, 0);
            this.barDockControl1.Manager = this.barManager;
            this.barDockControl1.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl1.Size = new System.Drawing.Size(1174, 33);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 793);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1174, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 33);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 760);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1174, 33);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 760);
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
            // ShopDataForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1174, 793);
            this.Controls.Add(this.panel1);
            this.Controls.Add(idLabel);
            this.Controls.Add(this.idLabel1);
            this.Controls.Add(this.barDockControlRight);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "ShopDataForm";
            this.Text = "Shop Data";
            this.Load += new System.EventHandler(this.ShopDataForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.profitSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.shopDataBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private ClothesDataSet clothesDataSet;
        private System.Windows.Forms.BindingSource shopDataBindingSource;
        private ClothesDataSetTableAdapters.ShopDataTableAdapter shopDataTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraEditors.SpinEdit profitSpinEdit;
        private System.Windows.Forms.Label idLabel1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private DevExpress.XtraBars.BarButtonItem btnCloseForm;
        private DevExpress.XtraBars.BarDockControl barDockControlRight;
        private ClothesDataSetTableAdapters.AddressTableAdapter addressTableAdapter1;
        private DevExpress.XtraBars.BarManager barManager;
        private DevExpress.XtraBars.Bar bar2;
        private DevExpress.XtraBars.BarButtonItem btnReloadProvider;
        private DevExpress.XtraBars.BarButtonItem barButtonItem1;
        private DevExpress.XtraBars.BarDockControl barDockControl1;
        private DevExpress.XtraBars.BarDockControl barDockControl2;
        private DevExpress.XtraBars.BarDockControl barDockControl3;
        private DevExpress.XtraBars.BarDockControl barDockControl5;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
    }
}