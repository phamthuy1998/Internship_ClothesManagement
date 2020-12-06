namespace ClothesAdmin
{
    partial class InvoiceForm
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
            System.Windows.Forms.Label employeeIdLabel;
            System.Windows.Forms.Label idLabel;
            System.Windows.Forms.Label updateDateLabel;
            System.Windows.Forms.Label buyDateLabel;
            System.Windows.Forms.Label nameLabel;
            System.Windows.Forms.Label phoneLabel;
            System.Windows.Forms.Label addressLabel;
            System.Windows.Forms.Label noteLabel;
            System.Windows.Forms.Label label4;
            System.Windows.Forms.Label userIDLabel;
            System.Windows.Forms.Label statusOrderIdLabel;
            System.Windows.Forms.Label deliveryDateLabel;
            System.Windows.Forms.Label activeLabel;
            System.Windows.Forms.Label label3;
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label orderIdLabel;
            System.Windows.Forms.Label productIdLabel;
            System.Windows.Forms.Label colorIdLabel;
            System.Windows.Forms.Label sizeIdLabel;
            System.Windows.Forms.Label unitPriceLabel;
            System.Windows.Forms.Label quantityLabel;
            System.Windows.Forms.Label label5;
            System.Windows.Forms.Label paymentLabel;
            System.Windows.Forms.Label isPaidLabel;
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(InvoiceForm));
            this.barManager = new DevExpress.XtraBars.BarManager(this.components);
            this.bar2 = new DevExpress.XtraBars.Bar();
            this.btnReloadProvider = new DevExpress.XtraBars.BarButtonItem();
            this.btnCloseForm = new DevExpress.XtraBars.BarButtonItem();
            this.barDockControl1 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl2 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl3 = new DevExpress.XtraBars.BarDockControl();
            this.barDockControl5 = new DevExpress.XtraBars.BarDockControl();
            this.btnSua = new DevExpress.XtraBars.BarButtonItem();
            this.btnTimKiem = new DevExpress.XtraBars.BarButtonItem();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.reloadToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.invoiceItemBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.invoiceBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.sizeBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.colorBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.productBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.invoiceTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.InvoiceTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.invoiceItemTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.InvoiceItemTableAdapter();
            this.productTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProductTableAdapter();
            this.colorTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ColorTableAdapter();
            this.sizeTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SizeTableAdapter();
            this.panel1 = new System.Windows.Forms.Panel();
            this.cbbStatusOrder = new System.Windows.Forms.ComboBox();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.invoiceItemGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView2 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colproductId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colcolorId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colsizeId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colunitPrice = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colquantity = new DevExpress.XtraGrid.Columns.GridColumn();
            this.invoiceGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView1 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colupdateDate = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colbuyDate = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colname = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colphone = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coladdress = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colnote = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coluserID = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colstatusOrderId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.coldeliveryDate = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colactive = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colemployeeId = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colpayment = new DevExpress.XtraGrid.Columns.GridColumn();
            this.colisPaid = new DevExpress.XtraGrid.Columns.GridColumn();
            this.panel3 = new System.Windows.Forms.Panel();
            this.btnChangeStatus = new System.Windows.Forms.Button();
            this.tvStatus = new System.Windows.Forms.TextBox();
            this.isPaidTextBox = new System.Windows.Forms.TextBox();
            this.paymentTextBox = new System.Windows.Forms.TextBox();
            this.employeeIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.statusOrderCombobox = new System.Windows.Forms.ComboBox();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.updateDateDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.buyDateDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.nameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.phoneTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.addressTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.noteTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.userIDSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.statusOrderIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.deliveryDateDateEdit = new DevExpress.XtraEditors.DateEdit();
            this.activeSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.panel2 = new System.Windows.Forms.Panel();
            this.btnExport = new System.Windows.Forms.Button();
            this.sizeComboBox = new System.Windows.Forms.ComboBox();
            this.colorComboBox = new System.Windows.Forms.ComboBox();
            this.productComboBox = new System.Windows.Forms.ComboBox();
            this.orderIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.productIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.colorIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.sizeIdSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.unitPriceSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.quantitySpinEdit = new DevExpress.XtraEditors.SpinEdit();
            employeeIdLabel = new System.Windows.Forms.Label();
            idLabel = new System.Windows.Forms.Label();
            updateDateLabel = new System.Windows.Forms.Label();
            buyDateLabel = new System.Windows.Forms.Label();
            nameLabel = new System.Windows.Forms.Label();
            phoneLabel = new System.Windows.Forms.Label();
            addressLabel = new System.Windows.Forms.Label();
            noteLabel = new System.Windows.Forms.Label();
            label4 = new System.Windows.Forms.Label();
            userIDLabel = new System.Windows.Forms.Label();
            statusOrderIdLabel = new System.Windows.Forms.Label();
            deliveryDateLabel = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            orderIdLabel = new System.Windows.Forms.Label();
            productIdLabel = new System.Windows.Forms.Label();
            colorIdLabel = new System.Windows.Forms.Label();
            sizeIdLabel = new System.Windows.Forms.Label();
            unitPriceLabel = new System.Windows.Forms.Label();
            quantityLabel = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            paymentLabel = new System.Windows.Forms.Label();
            isPaidLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).BeginInit();
            this.contextMenuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).BeginInit();
            this.panel1.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.panel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.employeeIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.updateDateDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.updateDateDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.buyDateDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.buyDateDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.noteTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.userIDSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.statusOrderIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.deliveryDateDateEdit.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.deliveryDateDateEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).BeginInit();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.orderIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.productIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeIdSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.unitPriceSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.quantitySpinEdit.Properties)).BeginInit();
            this.SuspendLayout();
            // 
            // employeeIdLabel
            // 
            employeeIdLabel.AutoSize = true;
            employeeIdLabel.Location = new System.Drawing.Point(651, 120);
            employeeIdLabel.Name = "employeeIdLabel";
            employeeIdLabel.Size = new System.Drawing.Size(88, 17);
            employeeIdLabel.TabIndex = 59;
            employeeIdLabel.Text = "employee Id:";
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(47, 24);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(23, 17);
            idLabel.TabIndex = 0;
            idLabel.Text = "id:";
            // 
            // updateDateLabel
            // 
            updateDateLabel.AutoSize = true;
            updateDateLabel.Location = new System.Drawing.Point(347, 63);
            updateDateLabel.Name = "updateDateLabel";
            updateDateLabel.Size = new System.Drawing.Size(90, 17);
            updateDateLabel.TabIndex = 2;
            updateDateLabel.Text = "update Date:";
            // 
            // buyDateLabel
            // 
            buyDateLabel.AutoSize = true;
            buyDateLabel.Location = new System.Drawing.Point(47, 63);
            buyDateLabel.Name = "buyDateLabel";
            buyDateLabel.Size = new System.Drawing.Size(69, 17);
            buyDateLabel.TabIndex = 4;
            buyDateLabel.Text = "buy Date:";
            // 
            // nameLabel
            // 
            nameLabel.AutoSize = true;
            nameLabel.Location = new System.Drawing.Point(47, 103);
            nameLabel.Name = "nameLabel";
            nameLabel.Size = new System.Drawing.Size(47, 17);
            nameLabel.TabIndex = 6;
            nameLabel.Text = "name:";
            // 
            // phoneLabel
            // 
            phoneLabel.AutoSize = true;
            phoneLabel.Location = new System.Drawing.Point(47, 138);
            phoneLabel.Name = "phoneLabel";
            phoneLabel.Size = new System.Drawing.Size(52, 17);
            phoneLabel.TabIndex = 8;
            phoneLabel.Text = "phone:";
            // 
            // addressLabel
            // 
            addressLabel.AutoSize = true;
            addressLabel.Location = new System.Drawing.Point(47, 172);
            addressLabel.Name = "addressLabel";
            addressLabel.Size = new System.Drawing.Size(63, 17);
            addressLabel.TabIndex = 10;
            addressLabel.Text = "address:";
            // 
            // noteLabel
            // 
            noteLabel.AutoSize = true;
            noteLabel.Location = new System.Drawing.Point(47, 207);
            noteLabel.Name = "noteLabel";
            noteLabel.Size = new System.Drawing.Size(40, 17);
            noteLabel.TabIndex = 12;
            noteLabel.Text = "note:";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new System.Drawing.Point(47, 276);
            label4.Name = "label4";
            label4.Size = new System.Drawing.Size(90, 17);
            label4.TabIndex = 14;
            label4.Text = "Status order ";
            // 
            // userIDLabel
            // 
            userIDLabel.AutoSize = true;
            userIDLabel.Location = new System.Drawing.Point(47, 242);
            userIDLabel.Name = "userIDLabel";
            userIDLabel.Size = new System.Drawing.Size(57, 17);
            userIDLabel.TabIndex = 14;
            userIDLabel.Text = "user ID:";
            // 
            // statusOrderIdLabel
            // 
            statusOrderIdLabel.AutoSize = true;
            statusOrderIdLabel.Location = new System.Drawing.Point(633, 269);
            statusOrderIdLabel.Name = "statusOrderIdLabel";
            statusOrderIdLabel.Size = new System.Drawing.Size(106, 17);
            statusOrderIdLabel.TabIndex = 16;
            statusOrderIdLabel.Text = "status Order Id:";
            // 
            // deliveryDateLabel
            // 
            deliveryDateLabel.AutoSize = true;
            deliveryDateLabel.Location = new System.Drawing.Point(633, 63);
            deliveryDateLabel.Name = "deliveryDateLabel";
            deliveryDateLabel.Size = new System.Drawing.Size(95, 17);
            deliveryDateLabel.TabIndex = 18;
            deliveryDateLabel.Text = "delivery Date:";
            // 
            // activeLabel
            // 
            activeLabel.AutoSize = true;
            activeLabel.Location = new System.Drawing.Point(47, 309);
            activeLabel.Name = "activeLabel";
            activeLabel.Size = new System.Drawing.Size(49, 17);
            activeLabel.TabIndex = 20;
            activeLabel.Text = "active:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(42, 182);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(35, 17);
            label3.TabIndex = 59;
            label3.Text = "Size";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Enabled = false;
            label2.Location = new System.Drawing.Point(42, 142);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(41, 17);
            label2.TabIndex = 59;
            label2.Text = "Color";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(42, 100);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(57, 17);
            label1.TabIndex = 59;
            label1.Text = "Product";
            // 
            // orderIdLabel
            // 
            orderIdLabel.AutoSize = true;
            orderIdLabel.Location = new System.Drawing.Point(42, 56);
            orderIdLabel.Name = "orderIdLabel";
            orderIdLabel.Size = new System.Drawing.Size(61, 17);
            orderIdLabel.TabIndex = 0;
            orderIdLabel.Text = "order Id:";
            // 
            // productIdLabel
            // 
            productIdLabel.AutoSize = true;
            productIdLabel.Location = new System.Drawing.Point(464, 95);
            productIdLabel.Name = "productIdLabel";
            productIdLabel.Size = new System.Drawing.Size(75, 17);
            productIdLabel.TabIndex = 2;
            productIdLabel.Text = "product Id:";
            // 
            // colorIdLabel
            // 
            colorIdLabel.AutoSize = true;
            colorIdLabel.Location = new System.Drawing.Point(464, 136);
            colorIdLabel.Name = "colorIdLabel";
            colorIdLabel.Size = new System.Drawing.Size(58, 17);
            colorIdLabel.TabIndex = 4;
            colorIdLabel.Text = "color Id:";
            // 
            // sizeIdLabel
            // 
            sizeIdLabel.AutoSize = true;
            sizeIdLabel.Location = new System.Drawing.Point(464, 182);
            sizeIdLabel.Name = "sizeIdLabel";
            sizeIdLabel.Size = new System.Drawing.Size(52, 17);
            sizeIdLabel.TabIndex = 6;
            sizeIdLabel.Text = "size Id:";
            // 
            // unitPriceLabel
            // 
            unitPriceLabel.AutoSize = true;
            unitPriceLabel.Location = new System.Drawing.Point(42, 230);
            unitPriceLabel.Name = "unitPriceLabel";
            unitPriceLabel.Size = new System.Drawing.Size(71, 17);
            unitPriceLabel.TabIndex = 8;
            unitPriceLabel.Text = "unit Price:";
            // 
            // quantityLabel
            // 
            quantityLabel.AutoSize = true;
            quantityLabel.Location = new System.Drawing.Point(42, 274);
            quantityLabel.Name = "quantityLabel";
            quantityLabel.Size = new System.Drawing.Size(62, 17);
            quantityLabel.TabIndex = 10;
            quantityLabel.Text = "quantity:";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new System.Drawing.Point(61, 20);
            label5.Name = "label5";
            label5.Size = new System.Drawing.Size(90, 17);
            label5.TabIndex = 14;
            label5.Text = "Status order ";
            // 
            // paymentLabel
            // 
            paymentLabel.AutoSize = true;
            paymentLabel.Location = new System.Drawing.Point(662, 172);
            paymentLabel.Name = "paymentLabel";
            paymentLabel.Size = new System.Drawing.Size(66, 17);
            paymentLabel.TabIndex = 60;
            paymentLabel.Text = "payment:";
            // 
            // isPaidLabel
            // 
            isPaidLabel.AutoSize = true;
            isPaidLabel.Location = new System.Drawing.Point(674, 210);
            isPaidLabel.Name = "isPaidLabel";
            isPaidLabel.Size = new System.Drawing.Size(54, 17);
            isPaidLabel.TabIndex = 61;
            isPaidLabel.Text = "is Paid:";
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
            this.barDockControl1.Size = new System.Drawing.Size(1940, 30);
            // 
            // barDockControl2
            // 
            this.barDockControl2.CausesValidation = false;
            this.barDockControl2.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.barDockControl2.Location = new System.Drawing.Point(0, 872);
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
            this.barDockControl3.Size = new System.Drawing.Size(0, 842);
            // 
            // barDockControl5
            // 
            this.barDockControl5.CausesValidation = false;
            this.barDockControl5.Dock = System.Windows.Forms.DockStyle.Right;
            this.barDockControl5.Location = new System.Drawing.Point(1940, 30);
            this.barDockControl5.Manager = this.barManager;
            this.barDockControl5.Margin = new System.Windows.Forms.Padding(2);
            this.barDockControl5.Size = new System.Drawing.Size(0, 842);
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
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.reloadToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(130, 30);
            // 
            // reloadToolStripMenuItem
            // 
            this.reloadToolStripMenuItem.Image = global::ClothesAdmin.Properties.Resources.exchange;
            this.reloadToolStripMenuItem.Name = "reloadToolStripMenuItem";
            this.reloadToolStripMenuItem.Size = new System.Drawing.Size(129, 26);
            this.reloadToolStripMenuItem.Text = "Reload";
            this.reloadToolStripMenuItem.Click += new System.EventHandler(this.reloadToolStripMenuItem_Click);
            // 
            // invoiceItemBindingSource
            // 
            this.invoiceItemBindingSource.DataMember = "FK_InvoiceItem_Invoice";
            this.invoiceItemBindingSource.DataSource = this.invoiceBindingSource;
            // 
            // invoiceBindingSource
            // 
            this.invoiceBindingSource.DataMember = "Invoice";
            this.invoiceBindingSource.DataSource = this.clothesDataSet;
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
            // colorBindingSource
            // 
            this.colorBindingSource.DataMember = "Color";
            this.colorBindingSource.DataSource = this.clothesDataSet;
            // 
            // productBindingSource
            // 
            this.productBindingSource.DataMember = "Product";
            this.productBindingSource.DataSource = this.clothesDataSet;
            // 
            // invoiceTableAdapter
            // 
            this.invoiceTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.InvoiceItemTableAdapter = this.invoiceItemTableAdapter;
            this.tableAdapterManager.InvoiceStatusTableAdapter = null;
            this.tableAdapterManager.InvoiceTableAdapter = this.invoiceTableAdapter;
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
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // invoiceItemTableAdapter
            // 
            this.invoiceItemTableAdapter.ClearBeforeFill = true;
            // 
            // productTableAdapter
            // 
            this.productTableAdapter.ClearBeforeFill = true;
            // 
            // colorTableAdapter
            // 
            this.colorTableAdapter.ClearBeforeFill = true;
            // 
            // sizeTableAdapter
            // 
            this.sizeTableAdapter.ClearBeforeFill = true;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.cbbStatusOrder);
            this.panel1.Controls.Add(label5);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 30);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1940, 57);
            this.panel1.TabIndex = 9;
            // 
            // cbbStatusOrder
            // 
            this.cbbStatusOrder.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbStatusOrder.FormattingEnabled = true;
            this.cbbStatusOrder.Location = new System.Drawing.Point(172, 17);
            this.cbbStatusOrder.Name = "cbbStatusOrder";
            this.cbbStatusOrder.Size = new System.Drawing.Size(426, 24);
            this.cbbStatusOrder.TabIndex = 59;
            this.cbbStatusOrder.SelectedIndexChanged += new System.EventHandler(this.cbbStatusOrder_SelectedIndexChanged);
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 64.3299F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35.6701F));
            this.tableLayoutPanel1.Controls.Add(this.invoiceItemGridControl, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.invoiceGridControl, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panel3, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panel2, 1, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 87);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 40.50956F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 59.49044F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1940, 785);
            this.tableLayoutPanel1.TabIndex = 10;
            // 
            // invoiceItemGridControl
            // 
            this.invoiceItemGridControl.ContextMenuStrip = this.contextMenuStrip1;
            this.invoiceItemGridControl.DataSource = this.invoiceItemBindingSource;
            this.invoiceItemGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.invoiceItemGridControl.Location = new System.Drawing.Point(1251, 3);
            this.invoiceItemGridControl.MainView = this.gridView2;
            this.invoiceItemGridControl.MenuManager = this.barManager;
            this.invoiceItemGridControl.Name = "invoiceItemGridControl";
            this.invoiceItemGridControl.Size = new System.Drawing.Size(686, 312);
            this.invoiceItemGridControl.TabIndex = 3;
            this.invoiceItemGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView2});
            // 
            // gridView2
            // 
            this.gridView2.Appearance.GroupPanel.Font = new System.Drawing.Font("Microsoft Sans Serif", 16.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.gridView2.Appearance.GroupPanel.Options.UseFont = true;
            this.gridView2.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colproductId,
            this.colcolorId,
            this.colsizeId,
            this.colunitPrice,
            this.colquantity});
            this.gridView2.GridControl = this.invoiceItemGridControl;
            this.gridView2.GroupPanelText = "Invoice detail";
            this.gridView2.Name = "gridView2";
            // 
            // colproductId
            // 
            this.colproductId.FieldName = "productId";
            this.colproductId.MinWidth = 25;
            this.colproductId.Name = "colproductId";
            this.colproductId.OptionsColumn.AllowEdit = false;
            this.colproductId.Visible = true;
            this.colproductId.VisibleIndex = 0;
            this.colproductId.Width = 87;
            // 
            // colcolorId
            // 
            this.colcolorId.FieldName = "colorId";
            this.colcolorId.MinWidth = 25;
            this.colcolorId.Name = "colcolorId";
            this.colcolorId.OptionsColumn.AllowEdit = false;
            this.colcolorId.Visible = true;
            this.colcolorId.VisibleIndex = 1;
            this.colcolorId.Width = 74;
            // 
            // colsizeId
            // 
            this.colsizeId.FieldName = "sizeId";
            this.colsizeId.MinWidth = 25;
            this.colsizeId.Name = "colsizeId";
            this.colsizeId.OptionsColumn.AllowEdit = false;
            this.colsizeId.Visible = true;
            this.colsizeId.VisibleIndex = 2;
            this.colsizeId.Width = 86;
            // 
            // colunitPrice
            // 
            this.colunitPrice.FieldName = "unitPrice";
            this.colunitPrice.MinWidth = 25;
            this.colunitPrice.Name = "colunitPrice";
            this.colunitPrice.OptionsColumn.AllowEdit = false;
            this.colunitPrice.Visible = true;
            this.colunitPrice.VisibleIndex = 3;
            this.colunitPrice.Width = 234;
            // 
            // colquantity
            // 
            this.colquantity.FieldName = "quantity";
            this.colquantity.MinWidth = 25;
            this.colquantity.Name = "colquantity";
            this.colquantity.OptionsColumn.AllowEdit = false;
            this.colquantity.Visible = true;
            this.colquantity.VisibleIndex = 4;
            this.colquantity.Width = 186;
            // 
            // invoiceGridControl
            // 
            this.invoiceGridControl.DataSource = this.invoiceBindingSource;
            this.invoiceGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.invoiceGridControl.Location = new System.Drawing.Point(3, 3);
            this.invoiceGridControl.MainView = this.gridView1;
            this.invoiceGridControl.MenuManager = this.barManager;
            this.invoiceGridControl.Name = "invoiceGridControl";
            this.invoiceGridControl.Size = new System.Drawing.Size(1242, 312);
            this.invoiceGridControl.TabIndex = 3;
            this.invoiceGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
            this.gridView1.Appearance.GroupPanel.Font = new System.Drawing.Font("Microsoft Sans Serif", 16.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.gridView1.Appearance.GroupPanel.Options.UseFont = true;
            this.gridView1.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colid,
            this.colupdateDate,
            this.colbuyDate,
            this.colname,
            this.colphone,
            this.coladdress,
            this.colnote,
            this.coluserID,
            this.colstatusOrderId,
            this.coldeliveryDate,
            this.colactive,
            this.colemployeeId,
            this.colpayment,
            this.colisPaid});
            this.gridView1.GridControl = this.invoiceGridControl;
            this.gridView1.GroupPanelText = "Invoice";
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
            // colupdateDate
            // 
            this.colupdateDate.FieldName = "updateDate";
            this.colupdateDate.MinWidth = 25;
            this.colupdateDate.Name = "colupdateDate";
            this.colupdateDate.OptionsColumn.AllowEdit = false;
            this.colupdateDate.Visible = true;
            this.colupdateDate.VisibleIndex = 1;
            this.colupdateDate.Width = 94;
            // 
            // colbuyDate
            // 
            this.colbuyDate.FieldName = "buyDate";
            this.colbuyDate.MinWidth = 25;
            this.colbuyDate.Name = "colbuyDate";
            this.colbuyDate.OptionsColumn.AllowEdit = false;
            this.colbuyDate.Visible = true;
            this.colbuyDate.VisibleIndex = 2;
            this.colbuyDate.Width = 94;
            // 
            // colname
            // 
            this.colname.FieldName = "name";
            this.colname.MinWidth = 25;
            this.colname.Name = "colname";
            this.colname.OptionsColumn.AllowEdit = false;
            this.colname.Visible = true;
            this.colname.VisibleIndex = 3;
            this.colname.Width = 94;
            // 
            // colphone
            // 
            this.colphone.FieldName = "phone";
            this.colphone.MinWidth = 25;
            this.colphone.Name = "colphone";
            this.colphone.OptionsColumn.AllowEdit = false;
            this.colphone.Visible = true;
            this.colphone.VisibleIndex = 4;
            this.colphone.Width = 94;
            // 
            // coladdress
            // 
            this.coladdress.FieldName = "address";
            this.coladdress.MinWidth = 25;
            this.coladdress.Name = "coladdress";
            this.coladdress.OptionsColumn.AllowEdit = false;
            this.coladdress.Visible = true;
            this.coladdress.VisibleIndex = 5;
            this.coladdress.Width = 94;
            // 
            // colnote
            // 
            this.colnote.FieldName = "note";
            this.colnote.MinWidth = 25;
            this.colnote.Name = "colnote";
            this.colnote.OptionsColumn.AllowEdit = false;
            this.colnote.Visible = true;
            this.colnote.VisibleIndex = 6;
            this.colnote.Width = 94;
            // 
            // coluserID
            // 
            this.coluserID.FieldName = "userID";
            this.coluserID.MinWidth = 25;
            this.coluserID.Name = "coluserID";
            this.coluserID.OptionsColumn.AllowEdit = false;
            this.coluserID.Visible = true;
            this.coluserID.VisibleIndex = 7;
            this.coluserID.Width = 94;
            // 
            // colstatusOrderId
            // 
            this.colstatusOrderId.FieldName = "statusOrderId";
            this.colstatusOrderId.MinWidth = 25;
            this.colstatusOrderId.Name = "colstatusOrderId";
            this.colstatusOrderId.OptionsColumn.AllowEdit = false;
            this.colstatusOrderId.Visible = true;
            this.colstatusOrderId.VisibleIndex = 8;
            this.colstatusOrderId.Width = 94;
            // 
            // coldeliveryDate
            // 
            this.coldeliveryDate.FieldName = "deliveryDate";
            this.coldeliveryDate.MinWidth = 25;
            this.coldeliveryDate.Name = "coldeliveryDate";
            this.coldeliveryDate.OptionsColumn.AllowEdit = false;
            this.coldeliveryDate.Visible = true;
            this.coldeliveryDate.VisibleIndex = 9;
            this.coldeliveryDate.Width = 94;
            // 
            // colactive
            // 
            this.colactive.FieldName = "active";
            this.colactive.MinWidth = 25;
            this.colactive.Name = "colactive";
            this.colactive.OptionsColumn.AllowEdit = false;
            this.colactive.Visible = true;
            this.colactive.VisibleIndex = 10;
            this.colactive.Width = 94;
            // 
            // colemployeeId
            // 
            this.colemployeeId.FieldName = "employeeId";
            this.colemployeeId.MinWidth = 25;
            this.colemployeeId.Name = "colemployeeId";
            this.colemployeeId.OptionsColumn.AllowEdit = false;
            this.colemployeeId.Visible = true;
            this.colemployeeId.VisibleIndex = 11;
            this.colemployeeId.Width = 94;
            // 
            // colpayment
            // 
            this.colpayment.FieldName = "payment";
            this.colpayment.MinWidth = 25;
            this.colpayment.Name = "colpayment";
            this.colpayment.OptionsColumn.AllowEdit = false;
            this.colpayment.Visible = true;
            this.colpayment.VisibleIndex = 12;
            this.colpayment.Width = 94;
            // 
            // colisPaid
            // 
            this.colisPaid.FieldName = "isPaid";
            this.colisPaid.MinWidth = 25;
            this.colisPaid.Name = "colisPaid";
            this.colisPaid.OptionsColumn.AllowEdit = false;
            this.colisPaid.Visible = true;
            this.colisPaid.VisibleIndex = 13;
            this.colisPaid.Width = 94;
            // 
            // panel3
            // 
            this.panel3.AutoScroll = true;
            this.panel3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel3.Controls.Add(this.btnChangeStatus);
            this.panel3.Controls.Add(this.tvStatus);
            this.panel3.Controls.Add(isPaidLabel);
            this.panel3.Controls.Add(this.isPaidTextBox);
            this.panel3.Controls.Add(paymentLabel);
            this.panel3.Controls.Add(this.paymentTextBox);
            this.panel3.Controls.Add(employeeIdLabel);
            this.panel3.Controls.Add(this.employeeIdSpinEdit);
            this.panel3.Controls.Add(this.statusOrderCombobox);
            this.panel3.Controls.Add(this.btnCancelAddProvider);
            this.panel3.Controls.Add(this.btnSaveAddProvider);
            this.panel3.Controls.Add(idLabel);
            this.panel3.Controls.Add(this.idSpinEdit);
            this.panel3.Controls.Add(updateDateLabel);
            this.panel3.Controls.Add(this.updateDateDateEdit);
            this.panel3.Controls.Add(buyDateLabel);
            this.panel3.Controls.Add(this.buyDateDateEdit);
            this.panel3.Controls.Add(nameLabel);
            this.panel3.Controls.Add(this.nameTextEdit);
            this.panel3.Controls.Add(phoneLabel);
            this.panel3.Controls.Add(this.phoneTextEdit);
            this.panel3.Controls.Add(addressLabel);
            this.panel3.Controls.Add(this.addressTextEdit);
            this.panel3.Controls.Add(noteLabel);
            this.panel3.Controls.Add(this.noteTextEdit);
            this.panel3.Controls.Add(label4);
            this.panel3.Controls.Add(userIDLabel);
            this.panel3.Controls.Add(this.userIDSpinEdit);
            this.panel3.Controls.Add(statusOrderIdLabel);
            this.panel3.Controls.Add(this.statusOrderIdSpinEdit);
            this.panel3.Controls.Add(deliveryDateLabel);
            this.panel3.Controls.Add(this.deliveryDateDateEdit);
            this.panel3.Controls.Add(activeLabel);
            this.panel3.Controls.Add(this.activeSpinEdit);
            this.panel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel3.Location = new System.Drawing.Point(3, 321);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(1242, 461);
            this.panel3.TabIndex = 2;
            // 
            // btnChangeStatus
            // 
            this.btnChangeStatus.BackColor = System.Drawing.Color.White;
            this.btnChangeStatus.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnChangeStatus.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnChangeStatus.Location = new System.Drawing.Point(948, 258);
            this.btnChangeStatus.Name = "btnChangeStatus";
            this.btnChangeStatus.Size = new System.Drawing.Size(200, 52);
            this.btnChangeStatus.TabIndex = 64;
            this.btnChangeStatus.Text = "Change status";
            this.btnChangeStatus.UseVisualStyleBackColor = false;
            this.btnChangeStatus.Click += new System.EventHandler(this.btnChangeStatus_Click);
            // 
            // tvStatus
            // 
            this.tvStatus.Enabled = false;
            this.tvStatus.Location = new System.Drawing.Point(745, 210);
            this.tvStatus.Name = "tvStatus";
            this.tvStatus.Size = new System.Drawing.Size(326, 22);
            this.tvStatus.TabIndex = 63;
            // 
            // isPaidTextBox
            // 
            this.isPaidTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.invoiceBindingSource, "isPaid", true));
            this.isPaidTextBox.Enabled = false;
            this.isPaidTextBox.Location = new System.Drawing.Point(1110, 210);
            this.isPaidTextBox.Name = "isPaidTextBox";
            this.isPaidTextBox.Size = new System.Drawing.Size(100, 22);
            this.isPaidTextBox.TabIndex = 62;
            this.isPaidTextBox.TextChanged += new System.EventHandler(this.isPaidTextBox_TextChanged);
            // 
            // paymentTextBox
            // 
            this.paymentTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.invoiceBindingSource, "payment", true));
            this.paymentTextBox.Enabled = false;
            this.paymentTextBox.Location = new System.Drawing.Point(745, 167);
            this.paymentTextBox.Name = "paymentTextBox";
            this.paymentTextBox.Size = new System.Drawing.Size(379, 22);
            this.paymentTextBox.TabIndex = 61;
            // 
            // employeeIdSpinEdit
            // 
            this.employeeIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "employeeId", true));
            this.employeeIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.employeeIdSpinEdit.Enabled = false;
            this.employeeIdSpinEdit.Location = new System.Drawing.Point(745, 116);
            this.employeeIdSpinEdit.MenuManager = this.barManager;
            this.employeeIdSpinEdit.Name = "employeeIdSpinEdit";
            this.employeeIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.employeeIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.employeeIdSpinEdit.TabIndex = 60;
            // 
            // statusOrderCombobox
            // 
            this.statusOrderCombobox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.statusOrderCombobox.Enabled = false;
            this.statusOrderCombobox.FormattingEnabled = true;
            this.statusOrderCombobox.Location = new System.Drawing.Point(158, 273);
            this.statusOrderCombobox.Name = "statusOrderCombobox";
            this.statusOrderCombobox.Size = new System.Drawing.Size(426, 24);
            this.statusOrderCombobox.TabIndex = 59;
            this.statusOrderCombobox.SelectedIndexChanged += new System.EventHandler(this.statusOrderCombobox_SelectedIndexChanged);
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(605, 348);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 58;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            this.btnCancelAddProvider.Click += new System.EventHandler(this.btnCancelAddProvider_Click_1);
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(387, 348);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 57;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            this.btnSaveAddProvider.Click += new System.EventHandler(this.btnSaveAddProvider_Click_1);
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Enabled = false;
            this.idSpinEdit.Location = new System.Drawing.Point(159, 21);
            this.idSpinEdit.MenuManager = this.barManager;
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 1;
            // 
            // updateDateDateEdit
            // 
            this.updateDateDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "updateDate", true));
            this.updateDateDateEdit.EditValue = null;
            this.updateDateDateEdit.Enabled = false;
            this.updateDateDateEdit.Location = new System.Drawing.Point(459, 60);
            this.updateDateDateEdit.MenuManager = this.barManager;
            this.updateDateDateEdit.Name = "updateDateDateEdit";
            this.updateDateDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.updateDateDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.updateDateDateEdit.Size = new System.Drawing.Size(125, 22);
            this.updateDateDateEdit.TabIndex = 3;
            // 
            // buyDateDateEdit
            // 
            this.buyDateDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "buyDate", true));
            this.buyDateDateEdit.EditValue = null;
            this.buyDateDateEdit.Enabled = false;
            this.buyDateDateEdit.Location = new System.Drawing.Point(159, 60);
            this.buyDateDateEdit.MenuManager = this.barManager;
            this.buyDateDateEdit.Name = "buyDateDateEdit";
            this.buyDateDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.buyDateDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.buyDateDateEdit.Size = new System.Drawing.Size(125, 22);
            this.buyDateDateEdit.TabIndex = 5;
            // 
            // nameTextEdit
            // 
            this.nameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "name", true));
            this.nameTextEdit.Enabled = false;
            this.nameTextEdit.Location = new System.Drawing.Point(159, 100);
            this.nameTextEdit.MenuManager = this.barManager;
            this.nameTextEdit.Name = "nameTextEdit";
            this.nameTextEdit.Size = new System.Drawing.Size(312, 22);
            this.nameTextEdit.TabIndex = 7;
            // 
            // phoneTextEdit
            // 
            this.phoneTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "phone", true));
            this.phoneTextEdit.Enabled = false;
            this.phoneTextEdit.Location = new System.Drawing.Point(159, 135);
            this.phoneTextEdit.MenuManager = this.barManager;
            this.phoneTextEdit.Name = "phoneTextEdit";
            this.phoneTextEdit.Size = new System.Drawing.Size(217, 22);
            this.phoneTextEdit.TabIndex = 9;
            // 
            // addressTextEdit
            // 
            this.addressTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "address", true));
            this.addressTextEdit.Enabled = false;
            this.addressTextEdit.Location = new System.Drawing.Point(159, 169);
            this.addressTextEdit.MenuManager = this.barManager;
            this.addressTextEdit.Name = "addressTextEdit";
            this.addressTextEdit.Size = new System.Drawing.Size(425, 22);
            this.addressTextEdit.TabIndex = 11;
            // 
            // noteTextEdit
            // 
            this.noteTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "note", true));
            this.noteTextEdit.Enabled = false;
            this.noteTextEdit.Location = new System.Drawing.Point(159, 204);
            this.noteTextEdit.MenuManager = this.barManager;
            this.noteTextEdit.Name = "noteTextEdit";
            this.noteTextEdit.Size = new System.Drawing.Size(425, 22);
            this.noteTextEdit.TabIndex = 13;
            // 
            // userIDSpinEdit
            // 
            this.userIDSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "userID", true));
            this.userIDSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.userIDSpinEdit.Enabled = false;
            this.userIDSpinEdit.Location = new System.Drawing.Point(159, 239);
            this.userIDSpinEdit.MenuManager = this.barManager;
            this.userIDSpinEdit.Name = "userIDSpinEdit";
            this.userIDSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.userIDSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.userIDSpinEdit.TabIndex = 15;
            // 
            // statusOrderIdSpinEdit
            // 
            this.statusOrderIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "statusOrderId", true));
            this.statusOrderIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.statusOrderIdSpinEdit.Enabled = false;
            this.statusOrderIdSpinEdit.Location = new System.Drawing.Point(745, 266);
            this.statusOrderIdSpinEdit.MenuManager = this.barManager;
            this.statusOrderIdSpinEdit.Name = "statusOrderIdSpinEdit";
            this.statusOrderIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.statusOrderIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.statusOrderIdSpinEdit.TabIndex = 17;
            this.statusOrderIdSpinEdit.EditValueChanged += new System.EventHandler(this.statusOrderIdSpinEdit_EditValueChanged);
            // 
            // deliveryDateDateEdit
            // 
            this.deliveryDateDateEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "deliveryDate", true));
            this.deliveryDateDateEdit.EditValue = new System.DateTime(2020, 8, 13, 12, 47, 20, 118);
            this.deliveryDateDateEdit.Location = new System.Drawing.Point(745, 60);
            this.deliveryDateDateEdit.MenuManager = this.barManager;
            this.deliveryDateDateEdit.Name = "deliveryDateDateEdit";
            this.deliveryDateDateEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.deliveryDateDateEdit.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.deliveryDateDateEdit.Size = new System.Drawing.Size(125, 22);
            this.deliveryDateDateEdit.TabIndex = 19;
            // 
            // activeSpinEdit
            // 
            this.activeSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceBindingSource, "active", true));
            this.activeSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.activeSpinEdit.Enabled = false;
            this.activeSpinEdit.Location = new System.Drawing.Point(159, 312);
            this.activeSpinEdit.MenuManager = this.barManager;
            this.activeSpinEdit.Name = "activeSpinEdit";
            this.activeSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.activeSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.activeSpinEdit.TabIndex = 21;
            // 
            // panel2
            // 
            this.panel2.AutoScroll = true;
            this.panel2.ContextMenuStrip = this.contextMenuStrip1;
            this.panel2.Controls.Add(this.btnExport);
            this.panel2.Controls.Add(this.sizeComboBox);
            this.panel2.Controls.Add(this.colorComboBox);
            this.panel2.Controls.Add(label3);
            this.panel2.Controls.Add(label2);
            this.panel2.Controls.Add(label1);
            this.panel2.Controls.Add(this.productComboBox);
            this.panel2.Controls.Add(orderIdLabel);
            this.panel2.Controls.Add(this.orderIdSpinEdit);
            this.panel2.Controls.Add(productIdLabel);
            this.panel2.Controls.Add(this.productIdSpinEdit);
            this.panel2.Controls.Add(colorIdLabel);
            this.panel2.Controls.Add(this.colorIdSpinEdit);
            this.panel2.Controls.Add(sizeIdLabel);
            this.panel2.Controls.Add(this.sizeIdSpinEdit);
            this.panel2.Controls.Add(unitPriceLabel);
            this.panel2.Controls.Add(this.unitPriceSpinEdit);
            this.panel2.Controls.Add(quantityLabel);
            this.panel2.Controls.Add(this.quantitySpinEdit);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel2.Location = new System.Drawing.Point(1251, 321);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(686, 461);
            this.panel2.TabIndex = 3;
            // 
            // btnExport
            // 
            this.btnExport.BackColor = System.Drawing.Color.White;
            this.btnExport.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnExport.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnExport.Location = new System.Drawing.Point(300, 349);
            this.btnExport.Name = "btnExport";
            this.btnExport.Size = new System.Drawing.Size(123, 52);
            this.btnExport.TabIndex = 61;
            this.btnExport.Text = "Export";
            this.btnExport.UseVisualStyleBackColor = false;
            this.btnExport.Click += new System.EventHandler(this.btnExport_Click);
            // 
            // sizeComboBox
            // 
            this.sizeComboBox.DataSource = this.sizeBindingSource;
            this.sizeComboBox.DisplayMember = "sizeName";
            this.sizeComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.sizeComboBox.Enabled = false;
            this.sizeComboBox.FormattingEnabled = true;
            this.sizeComboBox.Location = new System.Drawing.Point(123, 182);
            this.sizeComboBox.Name = "sizeComboBox";
            this.sizeComboBox.Size = new System.Drawing.Size(300, 24);
            this.sizeComboBox.TabIndex = 59;
            this.sizeComboBox.ValueMember = "id";
            this.sizeComboBox.SelectedIndexChanged += new System.EventHandler(this.sizeComboBox_SelectedIndexChanged);
            // 
            // colorComboBox
            // 
            this.colorComboBox.DataSource = this.colorBindingSource;
            this.colorComboBox.DisplayMember = "colorName";
            this.colorComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.colorComboBox.Enabled = false;
            this.colorComboBox.FormattingEnabled = true;
            this.colorComboBox.Location = new System.Drawing.Point(123, 139);
            this.colorComboBox.Name = "colorComboBox";
            this.colorComboBox.Size = new System.Drawing.Size(300, 24);
            this.colorComboBox.TabIndex = 59;
            this.colorComboBox.ValueMember = "id";
            this.colorComboBox.SelectedIndexChanged += new System.EventHandler(this.colorComboBox_SelectedIndexChanged);
            // 
            // productComboBox
            // 
            this.productComboBox.DataSource = this.productBindingSource;
            this.productComboBox.DisplayMember = "title";
            this.productComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.productComboBox.Enabled = false;
            this.productComboBox.FormattingEnabled = true;
            this.productComboBox.Location = new System.Drawing.Point(123, 97);
            this.productComboBox.Name = "productComboBox";
            this.productComboBox.Size = new System.Drawing.Size(300, 24);
            this.productComboBox.TabIndex = 58;
            this.productComboBox.ValueMember = "id";
            this.productComboBox.SelectedIndexChanged += new System.EventHandler(this.productComboBox_SelectedIndexChanged);
            // 
            // orderIdSpinEdit
            // 
            this.orderIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceItemBindingSource, "orderId", true));
            this.orderIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.orderIdSpinEdit.Enabled = false;
            this.orderIdSpinEdit.Location = new System.Drawing.Point(123, 53);
            this.orderIdSpinEdit.MenuManager = this.barManager;
            this.orderIdSpinEdit.Name = "orderIdSpinEdit";
            this.orderIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.orderIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.orderIdSpinEdit.TabIndex = 1;
            // 
            // productIdSpinEdit
            // 
            this.productIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceItemBindingSource, "productId", true));
            this.productIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.productIdSpinEdit.Enabled = false;
            this.productIdSpinEdit.Location = new System.Drawing.Point(545, 92);
            this.productIdSpinEdit.MenuManager = this.barManager;
            this.productIdSpinEdit.Name = "productIdSpinEdit";
            this.productIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.productIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.productIdSpinEdit.TabIndex = 3;
            this.productIdSpinEdit.EditValueChanged += new System.EventHandler(this.productIdSpinEdit_EditValueChanged);
            // 
            // colorIdSpinEdit
            // 
            this.colorIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceItemBindingSource, "colorId", true));
            this.colorIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.colorIdSpinEdit.Enabled = false;
            this.colorIdSpinEdit.Location = new System.Drawing.Point(545, 134);
            this.colorIdSpinEdit.MenuManager = this.barManager;
            this.colorIdSpinEdit.Name = "colorIdSpinEdit";
            this.colorIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.colorIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.colorIdSpinEdit.TabIndex = 5;
            this.colorIdSpinEdit.EditValueChanged += new System.EventHandler(this.colorIdSpinEdit_EditValueChanged);
            // 
            // sizeIdSpinEdit
            // 
            this.sizeIdSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceItemBindingSource, "sizeId", true));
            this.sizeIdSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.sizeIdSpinEdit.Enabled = false;
            this.sizeIdSpinEdit.Location = new System.Drawing.Point(545, 179);
            this.sizeIdSpinEdit.MenuManager = this.barManager;
            this.sizeIdSpinEdit.Name = "sizeIdSpinEdit";
            this.sizeIdSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.sizeIdSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.sizeIdSpinEdit.TabIndex = 7;
            this.sizeIdSpinEdit.EditValueChanged += new System.EventHandler(this.sizeIdSpinEdit_EditValueChanged);
            // 
            // unitPriceSpinEdit
            // 
            this.unitPriceSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceItemBindingSource, "unitPrice", true));
            this.unitPriceSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.unitPriceSpinEdit.Enabled = false;
            this.unitPriceSpinEdit.Location = new System.Drawing.Point(123, 227);
            this.unitPriceSpinEdit.MenuManager = this.barManager;
            this.unitPriceSpinEdit.Name = "unitPriceSpinEdit";
            this.unitPriceSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.unitPriceSpinEdit.Size = new System.Drawing.Size(177, 24);
            this.unitPriceSpinEdit.TabIndex = 9;
            // 
            // quantitySpinEdit
            // 
            this.quantitySpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.invoiceItemBindingSource, "quantity", true));
            this.quantitySpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.quantitySpinEdit.Enabled = false;
            this.quantitySpinEdit.Location = new System.Drawing.Point(123, 271);
            this.quantitySpinEdit.MenuManager = this.barManager;
            this.quantitySpinEdit.Name = "quantitySpinEdit";
            this.quantitySpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.quantitySpinEdit.Size = new System.Drawing.Size(125, 24);
            this.quantitySpinEdit.TabIndex = 11;
            // 
            // InvoiceForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1940, 872);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.barDockControl3);
            this.Controls.Add(this.barDockControl5);
            this.Controls.Add(this.barDockControl2);
            this.Controls.Add(this.barDockControl1);
            this.Name = "InvoiceForm";
            this.Text = "Invoice";
            this.Load += new System.EventHandler(this.InvoiceForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.barManager)).EndInit();
            this.contextMenuStrip1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productBindingSource)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.tableLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.panel3.ResumeLayout(false);
            this.panel3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.employeeIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.updateDateDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.updateDateDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.buyDateDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.buyDateDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.noteTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.userIDSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.statusOrderIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.deliveryDateDateEdit.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.deliveryDateDateEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).EndInit();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.orderIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.productIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.colorIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.sizeIdSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.unitPriceSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.quantitySpinEdit.Properties)).EndInit();
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
        private DevExpress.XtraBars.BarButtonItem btnSua;
        private DevExpress.XtraBars.BarButtonItem btnTimKiem;
        private System.Windows.Forms.BindingSource invoiceBindingSource;
        private ClothesDataSet clothesDataSet;
        private ClothesDataSetTableAdapters.InvoiceTableAdapter invoiceTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private ClothesDataSetTableAdapters.InvoiceItemTableAdapter invoiceItemTableAdapter;
        private System.Windows.Forms.BindingSource invoiceItemBindingSource;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem reloadToolStripMenuItem;
        private System.Windows.Forms.BindingSource productBindingSource;
        private ClothesDataSetTableAdapters.ProductTableAdapter productTableAdapter;
        private System.Windows.Forms.BindingSource colorBindingSource;
        private ClothesDataSetTableAdapters.ColorTableAdapter colorTableAdapter;
        private System.Windows.Forms.BindingSource sizeBindingSource;
        private ClothesDataSetTableAdapters.SizeTableAdapter sizeTableAdapter;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private DevExpress.XtraGrid.GridControl invoiceItemGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView2;
        private DevExpress.XtraGrid.Columns.GridColumn colproductId;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorId;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeId;
        private DevExpress.XtraGrid.Columns.GridColumn colunitPrice;
        private DevExpress.XtraGrid.Columns.GridColumn colquantity;
        private DevExpress.XtraGrid.GridControl invoiceGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
        private System.Windows.Forms.Panel panel3;
        private DevExpress.XtraEditors.SpinEdit employeeIdSpinEdit;
        private System.Windows.Forms.ComboBox statusOrderCombobox;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.DateEdit updateDateDateEdit;
        private DevExpress.XtraEditors.DateEdit buyDateDateEdit;
        private DevExpress.XtraEditors.TextEdit nameTextEdit;
        private DevExpress.XtraEditors.TextEdit phoneTextEdit;
        private DevExpress.XtraEditors.TextEdit addressTextEdit;
        private DevExpress.XtraEditors.TextEdit noteTextEdit;
        private DevExpress.XtraEditors.SpinEdit userIDSpinEdit;
        private DevExpress.XtraEditors.SpinEdit statusOrderIdSpinEdit;
        private DevExpress.XtraEditors.DateEdit deliveryDateDateEdit;
        private DevExpress.XtraEditors.SpinEdit activeSpinEdit;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.ComboBox sizeComboBox;
        private System.Windows.Forms.ComboBox colorComboBox;
        private System.Windows.Forms.ComboBox productComboBox;
        private DevExpress.XtraEditors.SpinEdit orderIdSpinEdit;
        private DevExpress.XtraEditors.SpinEdit productIdSpinEdit;
        private DevExpress.XtraEditors.SpinEdit colorIdSpinEdit;
        private DevExpress.XtraEditors.SpinEdit sizeIdSpinEdit;
        private DevExpress.XtraEditors.SpinEdit unitPriceSpinEdit;
        private DevExpress.XtraEditors.SpinEdit quantitySpinEdit;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.ComboBox cbbStatusOrder;
        private System.Windows.Forms.Button btnExport;
        private System.Windows.Forms.TextBox isPaidTextBox;
        private System.Windows.Forms.TextBox paymentTextBox;
        private System.Windows.Forms.TextBox tvStatus;
        private DevExpress.XtraGrid.Columns.GridColumn colid;
        private DevExpress.XtraGrid.Columns.GridColumn colupdateDate;
        private DevExpress.XtraGrid.Columns.GridColumn colbuyDate;
        private DevExpress.XtraGrid.Columns.GridColumn colname;
        private DevExpress.XtraGrid.Columns.GridColumn colphone;
        private DevExpress.XtraGrid.Columns.GridColumn coladdress;
        private DevExpress.XtraGrid.Columns.GridColumn colnote;
        private DevExpress.XtraGrid.Columns.GridColumn coluserID;
        private DevExpress.XtraGrid.Columns.GridColumn colstatusOrderId;
        private DevExpress.XtraGrid.Columns.GridColumn coldeliveryDate;
        private DevExpress.XtraGrid.Columns.GridColumn colactive;
        private DevExpress.XtraGrid.Columns.GridColumn colemployeeId;
        private DevExpress.XtraGrid.Columns.GridColumn colpayment;
        private DevExpress.XtraGrid.Columns.GridColumn colisPaid;
        private System.Windows.Forms.Button btnChangeStatus;
    }
}