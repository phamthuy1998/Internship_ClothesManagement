namespace ClothesAdmin
{
    partial class ProfitForm
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
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label label3;
            System.Windows.Forms.Label label4;
            System.Windows.Forms.Label totalInvoiceLabel;
            System.Windows.Forms.Label label7;
            System.Windows.Forms.Label label8;
            System.Windows.Forms.Label label9;
            System.Windows.Forms.Label label10;
            System.Windows.Forms.Label label11;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ProfitForm));
            this.barManager = new DevExpress.XtraBars.BarManager(this.components);
            this.bar1 = new DevExpress.XtraBars.Bar();
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
            this.cbbYear = new System.Windows.Forms.ComboBox();
            this.cbbMonth = new System.Windows.Forms.ComboBox();
            this.profitSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.sP_ProfitDataMonthYearBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.totalPriceSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.totalProductSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.totalInvoiceSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.radMonthYear = new System.Windows.Forms.RadioButton();
            this.rbDate = new System.Windows.Forms.RadioButton();
            this.dateEnd = new DevExpress.XtraEditors.DateEdit();
            this.dateBegin = new DevExpress.XtraEditors.DateEdit();
            this.cbProvider = new System.Windows.Forms.CheckBox();
            this.cbCategory = new System.Windows.Forms.CheckBox();
            this.providerComboBox = new System.Windows.Forms.ComboBox();
            this.providerBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.categoryComboBox = new System.Windows.Forms.ComboBox();
            this.categoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.btnExport = new System.Windows.Forms.Button();
            this.sP_GetProfitBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.sP_GetProfitTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_GetProfitTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.sP_ProfitDataMonthYearTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_ProfitDataMonthYearTableAdapter();
            this.categoryTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.CategoryTableAdapter();
            this.providerTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProviderTableAdapter();
            label6 = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            label4 = new System.Windows.Forms.Label();
            totalInvoiceLabel = new System.Windows.Forms.Label();
            label7 = new System.Windows.Forms.Label();
            label8 = new System.Windows.Forms.Label();
            label9 = new System.Windows.Forms.Label();
            label10 = new System.Windows.Forms.Label();
            label11 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.profitSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_ProfitDataMonthYearBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.totalPriceSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.totalProductSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.totalInvoiceSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProfitBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.Location = new System.Drawing.Point(275, 224);
            label6.Name = "label6";
            label6.Size = new System.Drawing.Size(65, 17);
            label6.TabIndex = 75;
            label6.Text = "Category";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new System.Drawing.Point(781, 228);
            label5.Name = "label5";
            label5.Size = new System.Drawing.Size(61, 17);
            label5.TabIndex = 76;
            label5.Text = "Provider";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(766, 116);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(66, 17);
            label2.TabIndex = 86;
            label2.Text = "Date end";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(380, 116);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(77, 17);
            label1.TabIndex = 87;
            label1.Text = "Date begin";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(380, 167);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(47, 17);
            label3.TabIndex = 87;
            label3.Text = "Month";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new System.Drawing.Point(766, 167);
            label4.Name = "label4";
            label4.Size = new System.Drawing.Size(38, 17);
            label4.TabIndex = 87;
            label4.Text = "Year";
            // 
            // totalInvoiceLabel
            // 
            totalInvoiceLabel.AutoSize = true;
            totalInvoiceLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            totalInvoiceLabel.ForeColor = System.Drawing.Color.Navy;
            totalInvoiceLabel.Location = new System.Drawing.Point(64, 320);
            totalInvoiceLabel.Name = "totalInvoiceLabel";
            totalInvoiceLabel.Size = new System.Drawing.Size(109, 29);
            totalInvoiceLabel.TabIndex = 92;
            totalInvoiceLabel.Text = "Invoices";
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label7.ForeColor = System.Drawing.Color.Navy;
            label7.Location = new System.Drawing.Point(836, 320);
            label7.Name = "label7";
            label7.Size = new System.Drawing.Size(139, 29);
            label7.TabIndex = 92;
            label7.Text = "Total price";
            // 
            // label8
            // 
            label8.AutoSize = true;
            label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label8.ForeColor = System.Drawing.Color.Navy;
            label8.Location = new System.Drawing.Point(425, 320);
            label8.Name = "label8";
            label8.Size = new System.Drawing.Size(116, 29);
            label8.TabIndex = 92;
            label8.Text = "Products";
            // 
            // label9
            // 
            label9.AutoSize = true;
            label9.Font = new System.Drawing.Font("Microsoft Sans Serif", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label9.ForeColor = System.Drawing.Color.Navy;
            label9.Location = new System.Drawing.Point(1324, 320);
            label9.Name = "label9";
            label9.Size = new System.Drawing.Size(75, 29);
            label9.TabIndex = 92;
            label9.Text = "Profit";
            // 
            // label10
            // 
            label10.AutoSize = true;
            label10.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label10.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            label10.Location = new System.Drawing.Point(1242, 327);
            label10.Name = "label10";
            label10.Size = new System.Drawing.Size(38, 20);
            label10.TabIndex = 92;
            label10.Text = "vnd";
            // 
            // label11
            // 
            label11.AutoSize = true;
            label11.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label11.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            label11.Location = new System.Drawing.Point(1652, 330);
            label11.Name = "label11";
            label11.Size = new System.Drawing.Size(38, 20);
            label11.TabIndex = 92;
            label11.Text = "vnd";
            // 
            // barManager
            // 
            this.barManager.Bars.AddRange(new DevExpress.XtraBars.Bar[] {
            this.bar1});
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
            this.barManager.MainMenu = this.bar1;
            this.barManager.MaxItemId = 11;
            // 
            // bar1
            // 
            this.bar1.BarName = "Main menu";
            this.bar1.DockCol = 0;
            this.bar1.DockRow = 0;
            this.bar1.DockStyle = DevExpress.XtraBars.BarDockStyle.Top;
            this.bar1.LinksPersistInfo.AddRange(new DevExpress.XtraBars.LinkPersistInfo[] {
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnReloadProvider, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph),
            new DevExpress.XtraBars.LinkPersistInfo(DevExpress.XtraBars.BarLinkUserDefines.PaintStyle, this.btnCloseForm, DevExpress.XtraBars.BarItemPaintStyle.CaptionGlyph)});
            this.bar1.OptionsBar.MultiLine = true;
            this.bar1.OptionsBar.UseWholeRow = true;
            this.bar1.Text = "Main menu";
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
            this.barDockControl1.Size = new System.Drawing.Size(1792, 30);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 950);
            this.barDockControl2.Manager = this.barManager;
            this.barDockControl2.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl2.Size = new System.Drawing.Size(1792, 0);
            // 
            // barDockControl3
            // 
            this.barDockControl3.CausesValidation = false;
            this.barDockControl3.Dock = System.Windows.Forms.DockStyle.Left;
            this.barDockControl3.Location = new System.Drawing.Point(0, 30);
            this.barDockControl3.Manager = this.barManager;
            this.barDockControl3.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl3.Size = new System.Drawing.Size(0, 920);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1792, 30);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 920);
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
            this.panel1.Controls.Add(this.cbbYear);
            this.panel1.Controls.Add(this.cbbMonth);
            this.panel1.Controls.Add(this.profitSpinEdit);
            this.panel1.Controls.Add(this.totalPriceSpinEdit);
            this.panel1.Controls.Add(this.totalProductSpinEdit);
            this.panel1.Controls.Add(this.totalInvoiceSpinEdit);
            this.panel1.Controls.Add(label8);
            this.panel1.Controls.Add(label9);
            this.panel1.Controls.Add(label11);
            this.panel1.Controls.Add(label10);
            this.panel1.Controls.Add(label7);
            this.panel1.Controls.Add(totalInvoiceLabel);
            this.panel1.Controls.Add(this.radMonthYear);
            this.panel1.Controls.Add(this.rbDate);
            this.panel1.Controls.Add(label2);
            this.panel1.Controls.Add(label4);
            this.panel1.Controls.Add(label3);
            this.panel1.Controls.Add(label1);
            this.panel1.Controls.Add(this.dateEnd);
            this.panel1.Controls.Add(this.dateBegin);
            this.panel1.Controls.Add(this.cbProvider);
            this.panel1.Controls.Add(this.cbCategory);
            this.panel1.Controls.Add(this.providerComboBox);
            this.panel1.Controls.Add(this.categoryComboBox);
            this.panel1.Controls.Add(this.btnExport);
            this.panel1.Controls.Add(label6);
            this.panel1.Controls.Add(label5);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(0, 30);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1792, 920);
            this.panel1.TabIndex = 6;
            // 
            // cbbYear
            // 
            this.cbbYear.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbYear.FormattingEnabled = true;
            this.cbbYear.Location = new System.Drawing.Point(870, 167);
            this.cbbYear.Name = "cbbYear";
            this.cbbYear.Size = new System.Drawing.Size(141, 24);
            this.cbbYear.TabIndex = 104;
            this.cbbYear.SelectedIndexChanged += new System.EventHandler(this.cbbYear_SelectedIndexChanged);
            // 
            // cbbMonth
            // 
            this.cbbMonth.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbMonth.FormattingEnabled = true;
            this.cbbMonth.Location = new System.Drawing.Point(482, 167);
            this.cbbMonth.Name = "cbbMonth";
            this.cbbMonth.Size = new System.Drawing.Size(141, 24);
            this.cbbMonth.TabIndex = 104;
            this.cbbMonth.SelectedIndexChanged += new System.EventHandler(this.cbbMonth_SelectedIndexChanged);
            // 
            // profitSpinEdit
            // 
            this.profitSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_ProfitDataMonthYearBindingSource, "profit", true));
            this.profitSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.profitSpinEdit.Enabled = false;
            this.profitSpinEdit.Location = new System.Drawing.Point(1421, 326);
            this.profitSpinEdit.MenuManager = this.barManager;
            this.profitSpinEdit.Name = "profitSpinEdit";
            this.profitSpinEdit.Properties.Appearance.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.profitSpinEdit.Properties.Appearance.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            this.profitSpinEdit.Properties.Appearance.Options.UseFont = true;
            this.profitSpinEdit.Properties.Appearance.Options.UseForeColor = true;
            this.profitSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.profitSpinEdit.Size = new System.Drawing.Size(214, 26);
            this.profitSpinEdit.TabIndex = 103;
            // 
            // sP_ProfitDataMonthYearBindingSource
            // 
            this.sP_ProfitDataMonthYearBindingSource.DataMember = "SP_ProfitDataMonthYear";
            this.sP_ProfitDataMonthYearBindingSource.DataSource = this.clothesDataSet;
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // totalPriceSpinEdit
            // 
            this.totalPriceSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_ProfitDataMonthYearBindingSource, "totalPrice", true));
            this.totalPriceSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.totalPriceSpinEdit.Enabled = false;
            this.totalPriceSpinEdit.Location = new System.Drawing.Point(997, 326);
            this.totalPriceSpinEdit.MenuManager = this.barManager;
            this.totalPriceSpinEdit.Name = "totalPriceSpinEdit";
            this.totalPriceSpinEdit.Properties.Appearance.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalPriceSpinEdit.Properties.Appearance.Options.UseFont = true;
            this.totalPriceSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.totalPriceSpinEdit.Size = new System.Drawing.Size(227, 26);
            this.totalPriceSpinEdit.TabIndex = 102;
            // 
            // totalProductSpinEdit
            // 
            this.totalProductSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_ProfitDataMonthYearBindingSource, "totalProduct", true));
            this.totalProductSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.totalProductSpinEdit.Enabled = false;
            this.totalProductSpinEdit.Location = new System.Drawing.Point(551, 326);
            this.totalProductSpinEdit.MenuManager = this.barManager;
            this.totalProductSpinEdit.Name = "totalProductSpinEdit";
            this.totalProductSpinEdit.Properties.Appearance.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalProductSpinEdit.Properties.Appearance.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            this.totalProductSpinEdit.Properties.Appearance.Options.UseFont = true;
            this.totalProductSpinEdit.Properties.Appearance.Options.UseForeColor = true;
            this.totalProductSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.totalProductSpinEdit.Size = new System.Drawing.Size(125, 26);
            this.totalProductSpinEdit.TabIndex = 101;
            // 
            // totalInvoiceSpinEdit
            // 
            this.totalInvoiceSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.sP_ProfitDataMonthYearBindingSource, "totalInvoice", true));
            this.totalInvoiceSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.totalInvoiceSpinEdit.Enabled = false;
            this.totalInvoiceSpinEdit.Location = new System.Drawing.Point(179, 326);
            this.totalInvoiceSpinEdit.MenuManager = this.barManager;
            this.totalInvoiceSpinEdit.Name = "totalInvoiceSpinEdit";
            this.totalInvoiceSpinEdit.Properties.Appearance.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.totalInvoiceSpinEdit.Properties.Appearance.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            this.totalInvoiceSpinEdit.Properties.Appearance.Options.UseFont = true;
            this.totalInvoiceSpinEdit.Properties.Appearance.Options.UseForeColor = true;
            this.totalInvoiceSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.totalInvoiceSpinEdit.Size = new System.Drawing.Size(125, 26);
            this.totalInvoiceSpinEdit.TabIndex = 100;
            // 
            // radMonthYear
            // 
            this.radMonthYear.AutoSize = true;
            this.radMonthYear.Location = new System.Drawing.Point(256, 165);
            this.radMonthYear.Name = "radMonthYear";
            this.radMonthYear.Size = new System.Drawing.Size(102, 21);
            this.radMonthYear.TabIndex = 89;
            this.radMonthYear.Text = "Month/Year";
            this.radMonthYear.UseVisualStyleBackColor = true;
            // 
            // rbDate
            // 
            this.rbDate.AutoSize = true;
            this.rbDate.Checked = true;
            this.rbDate.Location = new System.Drawing.Point(256, 114);
            this.rbDate.Name = "rbDate";
            this.rbDate.Size = new System.Drawing.Size(59, 21);
            this.rbDate.TabIndex = 88;
            this.rbDate.TabStop = true;
            this.rbDate.Text = "Date";
            this.rbDate.UseVisualStyleBackColor = true;
            this.rbDate.CheckedChanged += new System.EventHandler(this.rbDate_CheckedChanged);
            // 
            // dateEnd
            // 
            this.dateEnd.EditValue = new System.DateTime(2020, 9, 1, 18, 41, 35, 175);
            this.dateEnd.Location = new System.Drawing.Point(870, 113);
            this.dateEnd.Name = "dateEnd";
            this.dateEnd.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateEnd.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateEnd.Properties.MinValue = new System.DateTime(2020, 9, 1, 0, 0, 0, 0);
            this.dateEnd.Size = new System.Drawing.Size(141, 22);
            this.dateEnd.TabIndex = 84;
            this.dateEnd.EditValueChanged += new System.EventHandler(this.dateEnd_EditValueChanged);
            // 
            // dateBegin
            // 
            this.dateBegin.EditValue = new System.DateTime(2020, 9, 1, 0, 0, 0, 0);
            this.dateBegin.Location = new System.Drawing.Point(482, 113);
            this.dateBegin.Name = "dateBegin";
            this.dateBegin.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateBegin.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateBegin.Properties.MinValue = new System.DateTime(2020, 9, 1, 0, 0, 0, 0);
            this.dateBegin.Size = new System.Drawing.Size(141, 22);
            this.dateBegin.TabIndex = 85;
            this.dateBegin.EditValueChanged += new System.EventHandler(this.dateBegin_EditValueChanged);
            // 
            // cbProvider
            // 
            this.cbProvider.AutoSize = true;
            this.cbProvider.Location = new System.Drawing.Point(757, 228);
            this.cbProvider.Name = "cbProvider";
            this.cbProvider.Size = new System.Drawing.Size(18, 17);
            this.cbProvider.TabIndex = 83;
            this.cbProvider.UseVisualStyleBackColor = true;
            this.cbProvider.CheckedChanged += new System.EventHandler(this.cbProvider_CheckedChanged);
            // 
            // cbCategory
            // 
            this.cbCategory.AutoSize = true;
            this.cbCategory.Location = new System.Drawing.Point(251, 225);
            this.cbCategory.Name = "cbCategory";
            this.cbCategory.Size = new System.Drawing.Size(18, 17);
            this.cbCategory.TabIndex = 82;
            this.cbCategory.UseVisualStyleBackColor = true;
            this.cbCategory.CheckedChanged += new System.EventHandler(this.cbCategory_CheckedChanged);
            // 
            // providerComboBox
            // 
            this.providerComboBox.DataSource = this.providerBindingSource;
            this.providerComboBox.DisplayMember = "brandName";
            this.providerComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.providerComboBox.FormattingEnabled = true;
            this.providerComboBox.Location = new System.Drawing.Point(870, 225);
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
            // categoryComboBox
            // 
            this.categoryComboBox.DataSource = this.categoryBindingSource;
            this.categoryComboBox.DisplayMember = "name";
            this.categoryComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.categoryComboBox.FormattingEnabled = true;
            this.categoryComboBox.Location = new System.Drawing.Point(361, 224);
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
            this.btnExport.Location = new System.Drawing.Point(1366, 149);
            this.btnExport.Name = "btnExport";
            this.btnExport.Size = new System.Drawing.Size(189, 52);
            this.btnExport.TabIndex = 81;
            this.btnExport.Text = "Export";
            this.btnExport.UseVisualStyleBackColor = false;
            this.btnExport.Click += new System.EventHandler(this.btnExport_Click);
            // 
            // sP_GetProfitBindingSource
            // 
            this.sP_GetProfitBindingSource.DataMember = "SP_GetProfit";
            this.sP_GetProfitBindingSource.DataSource = this.clothesDataSet;
            // 
            // sP_GetProfitTableAdapter
            // 
            this.sP_GetProfitTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.ShopInfoTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.TypeNotiTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // sP_ProfitDataMonthYearTableAdapter
            // 
            this.sP_ProfitDataMonthYearTableAdapter.ClearBeforeFill = true;
            // 
            // categoryTableAdapter
            // 
            this.categoryTableAdapter.ClearBeforeFill = true;
            // 
            // providerTableAdapter
            // 
            this.providerTableAdapter.ClearBeforeFill = true;
            // 
            // ProfitForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1792, 950);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "ProfitForm";
            this.Text = "               Profit               ";
            this.Load += new System.EventHandler(this.ProfitForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.profitSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_ProfitDataMonthYearBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.totalPriceSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.totalProductSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.totalInvoiceSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.categoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetProfitBindingSource)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private DevExpress.XtraBars.BarManager barManager;
        private DevExpress.XtraBars.Bar bar1;
        private DevExpress.XtraBars.BarButtonItem btnReloadProvider;
        private DevExpress.XtraBars.BarButtonItem btnCloseForm;
        private DevExpress.XtraBars.BarDockControl barDockControl1;
        private DevExpress.XtraBars.BarDockControl barDockControl2;
        private DevExpress.XtraBars.BarDockControl barDockControl3;
        private DevExpress.XtraBars.BarDockControl barDockControl5;
        private DevExpress.XtraBars.BarButtonItem btnDelProvider;
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.CheckBox cbProvider;
        private System.Windows.Forms.CheckBox cbCategory;
        private System.Windows.Forms.ComboBox providerComboBox;
        private System.Windows.Forms.ComboBox categoryComboBox;
        private System.Windows.Forms.Button btnExport;
        private System.Windows.Forms.RadioButton radMonthYear;
        private System.Windows.Forms.RadioButton rbDate;
        private DevExpress.XtraEditors.DateEdit dateEnd;
        private DevExpress.XtraEditors.DateEdit dateBegin;
        private System.Windows.Forms.BindingSource sP_GetProfitBindingSource;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.SP_GetProfitTableAdapter sP_GetProfitTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private System.Windows.Forms.BindingSource sP_ProfitDataMonthYearBindingSource;
        private ClothesDataSetTableAdapters.SP_ProfitDataMonthYearTableAdapter sP_ProfitDataMonthYearTableAdapter;
        private System.Windows.Forms.BindingSource categoryBindingSource;
        private ClothesDataSetTableAdapters.CategoryTableAdapter categoryTableAdapter;
        private System.Windows.Forms.BindingSource providerBindingSource;
        private ClothesDataSetTableAdapters.ProviderTableAdapter providerTableAdapter;
        private DevExpress.XtraEditors.SpinEdit profitSpinEdit;
        private DevExpress.XtraEditors.SpinEdit totalPriceSpinEdit;
        private DevExpress.XtraEditors.SpinEdit totalProductSpinEdit;
        private DevExpress.XtraEditors.SpinEdit totalInvoiceSpinEdit;
        private System.Windows.Forms.ComboBox cbbYear;
        private System.Windows.Forms.ComboBox cbbMonth;
    }
}