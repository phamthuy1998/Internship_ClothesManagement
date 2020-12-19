namespace ClothesAdmin
{
    partial class InvoiceStatisticFrom
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
            System.Windows.Forms.Label label5;
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label label2;
            System.Windows.Forms.Label totalProductLabel;
            System.Windows.Forms.Label label3;
            this.totalPriceLabel = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.cbbStatusOrder = new System.Windows.Forms.ComboBox();
            this.dateEnd = new DevExpress.XtraEditors.DateEdit();
            this.dateBegin = new DevExpress.XtraEditors.DateEdit();
            this.invoiceItemBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.invoiceBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.panel2 = new System.Windows.Forms.Panel();
            this.totalInvoiceTextBox = new System.Windows.Forms.TextBox();
            this.sP_GetInvoiceBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.totalPriceTextBox = new System.Windows.Forms.TextBox();
            this.totalProductTextBox = new System.Windows.Forms.TextBox();
            this.invoiceTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.InvoiceTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.invoiceItemTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.InvoiceItemTableAdapter();
            this.sP_GetInvoiceTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.SP_GetInvoiceTableAdapter();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.invoiceItemGridControl = new DevExpress.XtraGrid.GridControl();
            this.gridView2 = new DevExpress.XtraGrid.Views.Grid.GridView();
            this.colorderId = new DevExpress.XtraGrid.Columns.GridColumn();
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
            this.label4 = new System.Windows.Forms.Label();
            label5 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            totalProductLabel = new System.Windows.Forms.Label();
            label3 = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties.CalendarTimeProperties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            this.panel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetInvoiceBindingSource)).BeginInit();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceGridControl)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new System.Drawing.Point(883, 38);
            label5.Name = "label5";
            label5.Size = new System.Drawing.Size(90, 17);
            label5.TabIndex = 63;
            label5.Text = "Status order ";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(87, 38);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(77, 17);
            label1.TabIndex = 63;
            label1.Text = "Date begin";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(492, 38);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(66, 17);
            label2.TabIndex = 63;
            label2.Text = "Date end";
            // 
            // totalPriceLabel
            // 
            this.totalPriceLabel.AutoSize = true;
            this.totalPriceLabel.Location = new System.Drawing.Point(491, 23);
            this.totalPriceLabel.Name = "totalPriceLabel";
            this.totalPriceLabel.Size = new System.Drawing.Size(75, 17);
            this.totalPriceLabel.TabIndex = 2;
            this.totalPriceLabel.Text = "total Price:";
            // 
            // totalProductLabel
            // 
            totalProductLabel.AutoSize = true;
            totalProductLabel.Location = new System.Drawing.Point(886, 23);
            totalProductLabel.Name = "totalProductLabel";
            totalProductLabel.Size = new System.Drawing.Size(92, 17);
            totalProductLabel.TabIndex = 4;
            totalProductLabel.Text = "total Product:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(818, 23);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(31, 17);
            label3.TabIndex = 2;
            label3.Text = "vnd";
            // 
            // panel1
            // 
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.cbbStatusOrder);
            this.panel1.Controls.Add(label2);
            this.panel1.Controls.Add(label1);
            this.panel1.Controls.Add(label5);
            this.panel1.Controls.Add(this.dateEnd);
            this.panel1.Controls.Add(this.dateBegin);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1914, 85);
            this.panel1.TabIndex = 62;
            // 
            // cbbStatusOrder
            // 
            this.cbbStatusOrder.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbbStatusOrder.FormattingEnabled = true;
            this.cbbStatusOrder.Location = new System.Drawing.Point(994, 35);
            this.cbbStatusOrder.Name = "cbbStatusOrder";
            this.cbbStatusOrder.Size = new System.Drawing.Size(426, 24);
            this.cbbStatusOrder.TabIndex = 64;
            this.cbbStatusOrder.SelectedIndexChanged += new System.EventHandler(this.cbbStatusOrder_SelectedIndexChanged);
            // 
            // dateEnd
            // 
            this.dateEnd.EditValue = null;
            this.dateEnd.Location = new System.Drawing.Point(594, 35);
            this.dateEnd.Name = "dateEnd";
            this.dateEnd.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateEnd.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateEnd.Size = new System.Drawing.Size(218, 22);
            this.dateEnd.TabIndex = 62;
            this.dateEnd.EditValueChanged += new System.EventHandler(this.dateEnd_EditValueChanged);
            // 
            // dateBegin
            // 
            this.dateBegin.EditValue = null;
            this.dateBegin.Location = new System.Drawing.Point(189, 35);
            this.dateBegin.Name = "dateBegin";
            this.dateBegin.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateBegin.Properties.CalendarTimeProperties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.dateBegin.Size = new System.Drawing.Size(218, 22);
            this.dateBegin.TabIndex = 62;
            this.dateBegin.EditValueChanged += new System.EventHandler(this.dateBegin_EditValueChanged);
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
            // panel2
            // 
            this.panel2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel2.Controls.Add(this.label4);
            this.panel2.Controls.Add(this.totalInvoiceTextBox);
            this.panel2.Controls.Add(label3);
            this.panel2.Controls.Add(this.totalPriceLabel);
            this.panel2.Controls.Add(this.totalPriceTextBox);
            this.panel2.Controls.Add(totalProductLabel);
            this.panel2.Controls.Add(this.totalProductTextBox);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel2.Location = new System.Drawing.Point(0, 85);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(1914, 69);
            this.panel2.TabIndex = 64;
            // 
            // totalInvoiceTextBox
            // 
            this.totalInvoiceTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.sP_GetInvoiceBindingSource, "totalInvoice", true));
            this.totalInvoiceTextBox.Enabled = false;
            this.totalInvoiceTextBox.Location = new System.Drawing.Point(189, 14);
            this.totalInvoiceTextBox.Name = "totalInvoiceTextBox";
            this.totalInvoiceTextBox.Size = new System.Drawing.Size(218, 22);
            this.totalInvoiceTextBox.TabIndex = 1;
            // 
            // sP_GetInvoiceBindingSource
            // 
            this.sP_GetInvoiceBindingSource.DataMember = "SP_GetInvoice";
            this.sP_GetInvoiceBindingSource.DataSource = this.clothesDataSet;
            // 
            // totalPriceTextBox
            // 
            this.totalPriceTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.sP_GetInvoiceBindingSource, "totalPrice", true));
            this.totalPriceTextBox.Enabled = false;
            this.totalPriceTextBox.Location = new System.Drawing.Point(589, 20);
            this.totalPriceTextBox.Name = "totalPriceTextBox";
            this.totalPriceTextBox.Size = new System.Drawing.Size(223, 22);
            this.totalPriceTextBox.TabIndex = 3;
            // 
            // totalProductTextBox
            // 
            this.totalProductTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.sP_GetInvoiceBindingSource, "totalProduct", true));
            this.totalProductTextBox.Enabled = false;
            this.totalProductTextBox.Location = new System.Drawing.Point(984, 20);
            this.totalProductTextBox.Name = "totalProductTextBox";
            this.totalProductTextBox.Size = new System.Drawing.Size(100, 22);
            this.totalProductTextBox.TabIndex = 5;
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
            this.tableAdapterManager.InvoiceItemTableAdapter = null;
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
            // sP_GetInvoiceTableAdapter
            // 
            this.sP_GetInvoiceTableAdapter.ClearBeforeFill = true;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 49.94775F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50.05225F));
            this.tableLayoutPanel1.Controls.Add(this.invoiceItemGridControl, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.invoiceGridControl, 0, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 154);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1914, 449);
            this.tableLayoutPanel1.TabIndex = 65;
            // 
            // invoiceItemGridControl
            // 
            this.invoiceItemGridControl.DataSource = this.invoiceItemBindingSource;
            this.invoiceItemGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.invoiceItemGridControl.Location = new System.Drawing.Point(958, 3);
            this.invoiceItemGridControl.MainView = this.gridView2;
            this.invoiceItemGridControl.Name = "invoiceItemGridControl";
            this.invoiceItemGridControl.Size = new System.Drawing.Size(953, 443);
            this.invoiceItemGridControl.TabIndex = 1;
            this.invoiceItemGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView2});
            // 
            // gridView2
            // 
            this.gridView2.Columns.AddRange(new DevExpress.XtraGrid.Columns.GridColumn[] {
            this.colorderId,
            this.colproductId,
            this.colcolorId,
            this.colsizeId,
            this.colunitPrice,
            this.colquantity});
            this.gridView2.GridControl = this.invoiceItemGridControl;
            this.gridView2.Name = "gridView2";
            // 
            // colorderId
            // 
            this.colorderId.FieldName = "orderId";
            this.colorderId.MinWidth = 25;
            this.colorderId.Name = "colorderId";
            this.colorderId.OptionsColumn.AllowEdit = false;
            this.colorderId.Visible = true;
            this.colorderId.VisibleIndex = 0;
            this.colorderId.Width = 94;
            // 
            // colproductId
            // 
            this.colproductId.FieldName = "productId";
            this.colproductId.MinWidth = 25;
            this.colproductId.Name = "colproductId";
            this.colproductId.OptionsColumn.AllowEdit = false;
            this.colproductId.Visible = true;
            this.colproductId.VisibleIndex = 1;
            this.colproductId.Width = 94;
            // 
            // colcolorId
            // 
            this.colcolorId.FieldName = "colorId";
            this.colcolorId.MinWidth = 25;
            this.colcolorId.Name = "colcolorId";
            this.colcolorId.OptionsColumn.AllowEdit = false;
            this.colcolorId.Visible = true;
            this.colcolorId.VisibleIndex = 2;
            this.colcolorId.Width = 94;
            // 
            // colsizeId
            // 
            this.colsizeId.FieldName = "sizeId";
            this.colsizeId.MinWidth = 25;
            this.colsizeId.Name = "colsizeId";
            this.colsizeId.OptionsColumn.AllowEdit = false;
            this.colsizeId.Visible = true;
            this.colsizeId.VisibleIndex = 3;
            this.colsizeId.Width = 94;
            // 
            // colunitPrice
            // 
            this.colunitPrice.FieldName = "unitPrice";
            this.colunitPrice.MinWidth = 25;
            this.colunitPrice.Name = "colunitPrice";
            this.colunitPrice.OptionsColumn.AllowEdit = false;
            this.colunitPrice.Visible = true;
            this.colunitPrice.VisibleIndex = 4;
            this.colunitPrice.Width = 94;
            // 
            // colquantity
            // 
            this.colquantity.FieldName = "quantity";
            this.colquantity.MinWidth = 25;
            this.colquantity.Name = "colquantity";
            this.colquantity.OptionsColumn.AllowEdit = false;
            this.colquantity.Visible = true;
            this.colquantity.VisibleIndex = 5;
            this.colquantity.Width = 94;
            // 
            // invoiceGridControl
            // 
            this.invoiceGridControl.DataSource = this.invoiceBindingSource;
            this.invoiceGridControl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.invoiceGridControl.Location = new System.Drawing.Point(3, 3);
            this.invoiceGridControl.MainView = this.gridView1;
            this.invoiceGridControl.Name = "invoiceGridControl";
            this.invoiceGridControl.Size = new System.Drawing.Size(949, 443);
            this.invoiceGridControl.TabIndex = 0;
            this.invoiceGridControl.ViewCollection.AddRange(new DevExpress.XtraGrid.Views.Base.BaseView[] {
            this.gridView1});
            // 
            // gridView1
            // 
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
            this.colemployeeId});
            this.gridView1.GridControl = this.invoiceGridControl;
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
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.ForeColor = System.Drawing.Color.Navy;
            this.label4.Location = new System.Drawing.Point(70, 14);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(94, 25);
            this.label4.TabIndex = 6;
            this.label4.Text = "Total bill";
            // 
            // InvoiceStatisticFrom
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.ClientSize = new System.Drawing.Size(1914, 603);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.panel1);
            this.Name = "InvoiceStatisticFrom";
            this.Text = "Invoice Statistic";
            this.Load += new System.EventHandler(this.InvoiceStatisticFrom_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateEnd.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties.CalendarTimeProperties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dateBegin.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sP_GetInvoiceBindingSource)).EndInit();
            this.tableLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.invoiceItemGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.invoiceGridControl)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.gridView1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.ComboBox cbbStatusOrder;
        private DevExpress.XtraEditors.DateEdit dateBegin;
        private System.Windows.Forms.Panel panel2;
        private ClothesDataSet clothesDataSet;
        private System.Windows.Forms.BindingSource invoiceBindingSource;
        private ClothesDataSetTableAdapters.InvoiceTableAdapter invoiceTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private System.Windows.Forms.BindingSource invoiceItemBindingSource;
        private ClothesDataSetTableAdapters.InvoiceItemTableAdapter invoiceItemTableAdapter;
        private DevExpress.XtraEditors.DateEdit dateEnd;
        private System.Windows.Forms.BindingSource sP_GetInvoiceBindingSource;
        private ClothesDataSetTableAdapters.SP_GetInvoiceTableAdapter sP_GetInvoiceTableAdapter;
        private System.Windows.Forms.TextBox totalInvoiceTextBox;
        private System.Windows.Forms.TextBox totalPriceTextBox;
        private System.Windows.Forms.TextBox totalProductTextBox;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private DevExpress.XtraGrid.GridControl invoiceItemGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView2;
        private DevExpress.XtraGrid.Columns.GridColumn colorderId;
        private DevExpress.XtraGrid.Columns.GridColumn colproductId;
        private DevExpress.XtraGrid.Columns.GridColumn colcolorId;
        private DevExpress.XtraGrid.Columns.GridColumn colsizeId;
        private DevExpress.XtraGrid.Columns.GridColumn colunitPrice;
        private DevExpress.XtraGrid.Columns.GridColumn colquantity;
        private DevExpress.XtraGrid.GridControl invoiceGridControl;
        private DevExpress.XtraGrid.Views.Grid.GridView gridView1;
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
        private System.Windows.Forms.Label totalPriceLabel;
        private System.Windows.Forms.Label label4;
    }
}