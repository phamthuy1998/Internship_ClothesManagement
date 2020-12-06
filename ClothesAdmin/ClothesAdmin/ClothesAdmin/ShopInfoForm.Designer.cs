namespace ClothesAdmin
{
    partial class ShopInfoForm
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
            System.Windows.Forms.Label shopNameLabel;
            System.Windows.Forms.Label addressLabel;
            System.Windows.Forms.Label addressDetailLabel;
            System.Windows.Forms.Label phoneNumberLabel;
            System.Windows.Forms.Label versionAppLabel;
            System.Windows.Forms.Label descriptionLabel;
            System.Windows.Forms.Label label1;
            System.Windows.Forms.Label idLabel;
            this.shopNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.shopInfoBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.addressTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.addressDetailTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.phoneNumberTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.versionAppTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.btnCancelImport = new System.Windows.Forms.Button();
            this.btnSaveIport = new System.Windows.Forms.Button();
            this.descriptionRichTextBox = new System.Windows.Forms.RichTextBox();
            this.shopInfoTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ShopInfoTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            shopNameLabel = new System.Windows.Forms.Label();
            addressLabel = new System.Windows.Forms.Label();
            addressDetailLabel = new System.Windows.Forms.Label();
            phoneNumberLabel = new System.Windows.Forms.Label();
            versionAppLabel = new System.Windows.Forms.Label();
            descriptionLabel = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            idLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.shopNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.shopInfoBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressDetailTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneNumberTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.versionAppTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            this.SuspendLayout();
            // 
            // shopNameLabel
            // 
            shopNameLabel.AutoSize = true;
            shopNameLabel.Location = new System.Drawing.Point(187, 141);
            shopNameLabel.Name = "shopNameLabel";
            shopNameLabel.Size = new System.Drawing.Size(80, 17);
            shopNameLabel.TabIndex = 3;
            shopNameLabel.Text = "Shop name";
            // 
            // addressLabel
            // 
            addressLabel.AutoSize = true;
            addressLabel.Location = new System.Drawing.Point(187, 189);
            addressLabel.Name = "addressLabel";
            addressLabel.Size = new System.Drawing.Size(60, 17);
            addressLabel.TabIndex = 5;
            addressLabel.Text = "Address";
            // 
            // addressDetailLabel
            // 
            addressDetailLabel.AutoSize = true;
            addressDetailLabel.Location = new System.Drawing.Point(187, 235);
            addressDetailLabel.Name = "addressDetailLabel";
            addressDetailLabel.Size = new System.Drawing.Size(98, 17);
            addressDetailLabel.TabIndex = 7;
            addressDetailLabel.Text = "Address detail";
            // 
            // phoneNumberLabel
            // 
            phoneNumberLabel.AutoSize = true;
            phoneNumberLabel.Location = new System.Drawing.Point(187, 285);
            phoneNumberLabel.Name = "phoneNumberLabel";
            phoneNumberLabel.Size = new System.Drawing.Size(101, 17);
            phoneNumberLabel.TabIndex = 9;
            phoneNumberLabel.Text = "Phone number";
            // 
            // versionAppLabel
            // 
            versionAppLabel.AutoSize = true;
            versionAppLabel.Location = new System.Drawing.Point(187, 332);
            versionAppLabel.Name = "versionAppLabel";
            versionAppLabel.Size = new System.Drawing.Size(84, 17);
            versionAppLabel.TabIndex = 13;
            versionAppLabel.Text = "Version app";
            // 
            // descriptionLabel
            // 
            descriptionLabel.AutoSize = true;
            descriptionLabel.Location = new System.Drawing.Point(187, 379);
            descriptionLabel.Name = "descriptionLabel";
            descriptionLabel.Size = new System.Drawing.Size(79, 17);
            descriptionLabel.TabIndex = 11;
            descriptionLabel.Text = "Description";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            label1.Location = new System.Drawing.Point(472, 24);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(153, 36);
            label1.TabIndex = 62;
            label1.Text = "Shop Info";
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(187, 101);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(23, 17);
            idLabel.TabIndex = 62;
            idLabel.Text = "id:";
            // 
            // shopNameTextEdit
            // 
            this.shopNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.shopInfoBindingSource, "shopName", true));
            this.shopNameTextEdit.Location = new System.Drawing.Point(333, 138);
            this.shopNameTextEdit.Name = "shopNameTextEdit";
            this.shopNameTextEdit.Size = new System.Drawing.Size(598, 22);
            this.shopNameTextEdit.TabIndex = 4;
            // 
            // shopInfoBindingSource
            // 
            this.shopInfoBindingSource.DataMember = "ShopInfo";
            this.shopInfoBindingSource.DataSource = this.clothesDataSet;
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // addressTextEdit
            // 
            this.addressTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.shopInfoBindingSource, "address", true));
            this.addressTextEdit.Location = new System.Drawing.Point(333, 186);
            this.addressTextEdit.Name = "addressTextEdit";
            this.addressTextEdit.Size = new System.Drawing.Size(598, 22);
            this.addressTextEdit.TabIndex = 6;
            // 
            // addressDetailTextEdit
            // 
            this.addressDetailTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.shopInfoBindingSource, "addressDetail", true));
            this.addressDetailTextEdit.Location = new System.Drawing.Point(333, 232);
            this.addressDetailTextEdit.Name = "addressDetailTextEdit";
            this.addressDetailTextEdit.Size = new System.Drawing.Size(598, 22);
            this.addressDetailTextEdit.TabIndex = 8;
            // 
            // phoneNumberTextEdit
            // 
            this.phoneNumberTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.shopInfoBindingSource, "phoneNumber", true));
            this.phoneNumberTextEdit.Location = new System.Drawing.Point(333, 282);
            this.phoneNumberTextEdit.Name = "phoneNumberTextEdit";
            this.phoneNumberTextEdit.Size = new System.Drawing.Size(203, 22);
            this.phoneNumberTextEdit.TabIndex = 10;
            // 
            // versionAppTextEdit
            // 
            this.versionAppTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.shopInfoBindingSource, "versionApp", true));
            this.versionAppTextEdit.Location = new System.Drawing.Point(333, 329);
            this.versionAppTextEdit.Name = "versionAppTextEdit";
            this.versionAppTextEdit.Size = new System.Drawing.Size(125, 22);
            this.versionAppTextEdit.TabIndex = 14;
            // 
            // btnCancelImport
            // 
            this.btnCancelImport.BackColor = System.Drawing.Color.White;
            this.btnCancelImport.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelImport.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelImport.Location = new System.Drawing.Point(629, 581);
            this.btnCancelImport.Name = "btnCancelImport";
            this.btnCancelImport.Size = new System.Drawing.Size(123, 52);
            this.btnCancelImport.TabIndex = 60;
            this.btnCancelImport.Text = "Cancel";
            this.btnCancelImport.UseVisualStyleBackColor = false;
            this.btnCancelImport.Click += new System.EventHandler(this.btnCancelImport_Click);
            // 
            // btnSaveIport
            // 
            this.btnSaveIport.BackColor = System.Drawing.Color.White;
            this.btnSaveIport.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveIport.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveIport.Location = new System.Drawing.Point(411, 581);
            this.btnSaveIport.Name = "btnSaveIport";
            this.btnSaveIport.Size = new System.Drawing.Size(123, 52);
            this.btnSaveIport.TabIndex = 59;
            this.btnSaveIport.Text = "Save";
            this.btnSaveIport.UseVisualStyleBackColor = false;
            this.btnSaveIport.Click += new System.EventHandler(this.btnSaveIport_Click);
            // 
            // descriptionRichTextBox
            // 
            this.descriptionRichTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.shopInfoBindingSource, "description", true));
            this.descriptionRichTextBox.Location = new System.Drawing.Point(333, 376);
            this.descriptionRichTextBox.Name = "descriptionRichTextBox";
            this.descriptionRichTextBox.Size = new System.Drawing.Size(598, 160);
            this.descriptionRichTextBox.TabIndex = 61;
            this.descriptionRichTextBox.Text = "";
            // 
            // shopInfoTableAdapter
            // 
            this.shopInfoTableAdapter.ClearBeforeFill = true;
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
            this.tableAdapterManager.ProductSizeColorTableAdapter = null;
            this.tableAdapterManager.ProductTableAdapter = null;
            this.tableAdapterManager.PromotionItemTableAdapter = null;
            this.tableAdapterManager.PromotionTableAdapter = null;
            this.tableAdapterManager.ProviderTableAdapter = null;
            this.tableAdapterManager.QuestionTableAdapter = null;
            this.tableAdapterManager.RatingTableAdapter = null;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.ShopInfoTableAdapter = this.shopInfoTableAdapter;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.shopInfoBindingSource, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Location = new System.Drawing.Point(333, 94);
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 63;
            // 
            // ShopInfoForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1149, 704);
            this.Controls.Add(idLabel);
            this.Controls.Add(this.idSpinEdit);
            this.Controls.Add(label1);
            this.Controls.Add(this.descriptionRichTextBox);
            this.Controls.Add(this.btnCancelImport);
            this.Controls.Add(this.btnSaveIport);
            this.Controls.Add(shopNameLabel);
            this.Controls.Add(this.shopNameTextEdit);
            this.Controls.Add(addressLabel);
            this.Controls.Add(this.addressTextEdit);
            this.Controls.Add(addressDetailLabel);
            this.Controls.Add(this.addressDetailTextEdit);
            this.Controls.Add(phoneNumberLabel);
            this.Controls.Add(this.phoneNumberTextEdit);
            this.Controls.Add(descriptionLabel);
            this.Controls.Add(versionAppLabel);
            this.Controls.Add(this.versionAppTextEdit);
            this.Name = "ShopInfoForm";
            this.Text = "Shop Info ";
            this.Load += new System.EventHandler(this.ShopInfoForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.shopNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.shopInfoBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressDetailTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneNumberTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.versionAppTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private ClothesDataSet clothesDataSet;
        private System.Windows.Forms.BindingSource shopInfoBindingSource;
        private ClothesDataSetTableAdapters.ShopInfoTableAdapter shopInfoTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraEditors.TextEdit shopNameTextEdit;
        private DevExpress.XtraEditors.TextEdit addressTextEdit;
        private DevExpress.XtraEditors.TextEdit addressDetailTextEdit;
        private DevExpress.XtraEditors.TextEdit phoneNumberTextEdit;
        private DevExpress.XtraEditors.TextEdit versionAppTextEdit;
        private System.Windows.Forms.Button btnCancelImport;
        private System.Windows.Forms.Button btnSaveIport;
        private System.Windows.Forms.RichTextBox descriptionRichTextBox;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
    }
}