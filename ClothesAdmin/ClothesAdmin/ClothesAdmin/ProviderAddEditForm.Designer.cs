namespace ClothesAdmin
{
    partial class ProviderAddEditForm
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
            System.Windows.Forms.Label imageUrlLabel;
            System.Windows.Forms.Label brandNameLabel;
            System.Windows.Forms.Label infomationLabel;
            System.Windows.Forms.Label phoneLabel;
            System.Windows.Forms.Label addressLabel;
            System.Windows.Forms.Label activeLabel;
            this.clothesDataSet = new ClothesAdmin.ClothesDataSet();
            this.providerBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.providerTableAdapter = new ClothesAdmin.ClothesDataSetTableAdapters.ProviderTableAdapter();
            this.tableAdapterManager = new ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager();
            this.idSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.imageUrlTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.brandNameTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.phoneTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.addressTextEdit = new DevExpress.XtraEditors.TextEdit();
            this.activeSpinEdit = new DevExpress.XtraEditors.SpinEdit();
            this.infomationRichTextBox = new System.Windows.Forms.RichTextBox();
            this.btnSaveAddProvider = new System.Windows.Forms.Button();
            this.btnCancelAddProvider = new System.Windows.Forms.Button();
            this.pictureEdit1 = new DevExpress.XtraEditors.PictureEdit();
            this.btnChoosePictureProvider = new System.Windows.Forms.LinkLabel();
            idLabel = new System.Windows.Forms.Label();
            imageUrlLabel = new System.Windows.Forms.Label();
            brandNameLabel = new System.Windows.Forms.Label();
            infomationLabel = new System.Windows.Forms.Label();
            phoneLabel = new System.Windows.Forms.Label();
            addressLabel = new System.Windows.Forms.Label();
            activeLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageUrlTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.brandNameTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureEdit1.Properties)).BeginInit();
            this.SuspendLayout();
            // 
            // clothesDataSet
            // 
            this.clothesDataSet.DataSetName = "ClothesDataSet";
            this.clothesDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
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
            this.tableAdapterManager.ProviderTableAdapter = this.providerTableAdapter;
            this.tableAdapterManager.RoleTableAdapter = null;
            this.tableAdapterManager.SizeTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = ClothesAdmin.ClothesDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // idLabel
            // 
            idLabel.AutoSize = true;
            idLabel.Location = new System.Drawing.Point(90, 80);
            idLabel.Name = "idLabel";
            idLabel.Size = new System.Drawing.Size(23, 17);
            idLabel.TabIndex = 1;
            idLabel.Text = "id:";
            // 
            // idSpinEdit
            // 
            this.idSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.providerBindingSource, "id", true));
            this.idSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.idSpinEdit.Location = new System.Drawing.Point(186, 77);
            this.idSpinEdit.Name = "idSpinEdit";
            this.idSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.idSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.idSpinEdit.TabIndex = 2;
            // 
            // imageUrlLabel
            // 
            imageUrlLabel.AutoSize = true;
            imageUrlLabel.Location = new System.Drawing.Point(90, 128);
            imageUrlLabel.Name = "imageUrlLabel";
            imageUrlLabel.Size = new System.Drawing.Size(72, 17);
            imageUrlLabel.TabIndex = 3;
            imageUrlLabel.Text = "image Url:";
            // 
            // imageUrlTextEdit
            // 
            this.imageUrlTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.providerBindingSource, "imageUrl", true));
            this.imageUrlTextEdit.Location = new System.Drawing.Point(186, 125);
            this.imageUrlTextEdit.Name = "imageUrlTextEdit";
            this.imageUrlTextEdit.Size = new System.Drawing.Size(438, 22);
            this.imageUrlTextEdit.TabIndex = 4;
            // 
            // brandNameLabel
            // 
            brandNameLabel.AutoSize = true;
            brandNameLabel.Location = new System.Drawing.Point(90, 169);
            brandNameLabel.Name = "brandNameLabel";
            brandNameLabel.Size = new System.Drawing.Size(90, 17);
            brandNameLabel.TabIndex = 5;
            brandNameLabel.Text = "brand Name:";
            // 
            // brandNameTextEdit
            // 
            this.brandNameTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.providerBindingSource, "brandName", true));
            this.brandNameTextEdit.Location = new System.Drawing.Point(186, 166);
            this.brandNameTextEdit.Name = "brandNameTextEdit";
            this.brandNameTextEdit.Size = new System.Drawing.Size(172, 22);
            this.brandNameTextEdit.TabIndex = 6;
            // 
            // infomationLabel
            // 
            infomationLabel.AutoSize = true;
            infomationLabel.Location = new System.Drawing.Point(90, 353);
            infomationLabel.Name = "infomationLabel";
            infomationLabel.Size = new System.Drawing.Size(77, 17);
            infomationLabel.TabIndex = 7;
            infomationLabel.Text = "infomation:";
            // 
            // phoneLabel
            // 
            phoneLabel.AutoSize = true;
            phoneLabel.Location = new System.Drawing.Point(90, 224);
            phoneLabel.Name = "phoneLabel";
            phoneLabel.Size = new System.Drawing.Size(52, 17);
            phoneLabel.TabIndex = 9;
            phoneLabel.Text = "phone:";
            // 
            // phoneTextEdit
            // 
            this.phoneTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.providerBindingSource, "phone", true));
            this.phoneTextEdit.Location = new System.Drawing.Point(186, 221);
            this.phoneTextEdit.Name = "phoneTextEdit";
            this.phoneTextEdit.Size = new System.Drawing.Size(172, 22);
            this.phoneTextEdit.TabIndex = 10;
            // 
            // addressLabel
            // 
            addressLabel.AutoSize = true;
            addressLabel.Location = new System.Drawing.Point(90, 265);
            addressLabel.Name = "addressLabel";
            addressLabel.Size = new System.Drawing.Size(63, 17);
            addressLabel.TabIndex = 11;
            addressLabel.Text = "address:";
            // 
            // addressTextEdit
            // 
            this.addressTextEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.providerBindingSource, "address", true));
            this.addressTextEdit.Location = new System.Drawing.Point(186, 262);
            this.addressTextEdit.Name = "addressTextEdit";
            this.addressTextEdit.Size = new System.Drawing.Size(438, 22);
            this.addressTextEdit.TabIndex = 12;
            // 
            // activeLabel
            // 
            activeLabel.AutoSize = true;
            activeLabel.Location = new System.Drawing.Point(90, 311);
            activeLabel.Name = "activeLabel";
            activeLabel.Size = new System.Drawing.Size(49, 17);
            activeLabel.TabIndex = 13;
            activeLabel.Text = "active:";
            // 
            // activeSpinEdit
            // 
            this.activeSpinEdit.DataBindings.Add(new System.Windows.Forms.Binding("EditValue", this.providerBindingSource, "active", true));
            this.activeSpinEdit.EditValue = new decimal(new int[] {
            0,
            0,
            0,
            0});
            this.activeSpinEdit.Location = new System.Drawing.Point(186, 308);
            this.activeSpinEdit.Name = "activeSpinEdit";
            this.activeSpinEdit.Properties.Buttons.AddRange(new DevExpress.XtraEditors.Controls.EditorButton[] {
            new DevExpress.XtraEditors.Controls.EditorButton(DevExpress.XtraEditors.Controls.ButtonPredefines.Combo)});
            this.activeSpinEdit.Size = new System.Drawing.Size(125, 24);
            this.activeSpinEdit.TabIndex = 14;
            // 
            // infomationRichTextBox
            // 
            this.infomationRichTextBox.DataBindings.Add(new System.Windows.Forms.Binding("Text", this.providerBindingSource, "infomation", true));
            this.infomationRichTextBox.Location = new System.Drawing.Point(186, 353);
            this.infomationRichTextBox.Name = "infomationRichTextBox";
            this.infomationRichTextBox.Size = new System.Drawing.Size(438, 126);
            this.infomationRichTextBox.TabIndex = 15;
            this.infomationRichTextBox.Text = "";
            // 
            // btnSaveAddProvider
            // 
            this.btnSaveAddProvider.BackColor = System.Drawing.Color.White;
            this.btnSaveAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSaveAddProvider.ForeColor = System.Drawing.Color.ForestGreen;
            this.btnSaveAddProvider.Location = new System.Drawing.Point(291, 532);
            this.btnSaveAddProvider.Name = "btnSaveAddProvider";
            this.btnSaveAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnSaveAddProvider.TabIndex = 16;
            this.btnSaveAddProvider.Text = "Save";
            this.btnSaveAddProvider.UseVisualStyleBackColor = false;
            // 
            // btnCancelAddProvider
            // 
            this.btnCancelAddProvider.BackColor = System.Drawing.Color.White;
            this.btnCancelAddProvider.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelAddProvider.ForeColor = System.Drawing.Color.Crimson;
            this.btnCancelAddProvider.Location = new System.Drawing.Point(500, 532);
            this.btnCancelAddProvider.Name = "btnCancelAddProvider";
            this.btnCancelAddProvider.Size = new System.Drawing.Size(123, 52);
            this.btnCancelAddProvider.TabIndex = 16;
            this.btnCancelAddProvider.Text = "Cancel";
            this.btnCancelAddProvider.UseVisualStyleBackColor = false;
            this.btnCancelAddProvider.Click += new System.EventHandler(this.btnCancelAddProvider_Click);
            // 
            // pictureEdit1
            // 
            this.pictureEdit1.Location = new System.Drawing.Point(696, 128);
            this.pictureEdit1.Name = "pictureEdit1";
            this.pictureEdit1.Properties.ShowCameraMenuItem = DevExpress.XtraEditors.Controls.CameraMenuItemVisibility.Auto;
            this.pictureEdit1.Size = new System.Drawing.Size(332, 351);
            this.pictureEdit1.TabIndex = 18;
            // 
            // btnChoosePictureProvider
            // 
            this.btnChoosePictureProvider.AutoSize = true;
            this.btnChoosePictureProvider.LinkColor = System.Drawing.Color.SteelBlue;
            this.btnChoosePictureProvider.Location = new System.Drawing.Point(825, 506);
            this.btnChoosePictureProvider.Name = "btnChoosePictureProvider";
            this.btnChoosePictureProvider.Size = new System.Drawing.Size(80, 17);
            this.btnChoosePictureProvider.TabIndex = 19;
            this.btnChoosePictureProvider.TabStop = true;
            this.btnChoosePictureProvider.Text = "Add picture";
            // 
            // ProviderAddEditForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1142, 613);
            this.Controls.Add(this.btnChoosePictureProvider);
            this.Controls.Add(this.pictureEdit1);
            this.Controls.Add(this.btnCancelAddProvider);
            this.Controls.Add(this.btnSaveAddProvider);
            this.Controls.Add(this.infomationRichTextBox);
            this.Controls.Add(idLabel);
            this.Controls.Add(this.idSpinEdit);
            this.Controls.Add(imageUrlLabel);
            this.Controls.Add(this.imageUrlTextEdit);
            this.Controls.Add(brandNameLabel);
            this.Controls.Add(this.brandNameTextEdit);
            this.Controls.Add(infomationLabel);
            this.Controls.Add(phoneLabel);
            this.Controls.Add(this.phoneTextEdit);
            this.Controls.Add(addressLabel);
            this.Controls.Add(this.addressTextEdit);
            this.Controls.Add(activeLabel);
            this.Controls.Add(this.activeSpinEdit);
            this.Name = "ProviderAddEditForm";
            this.Text = "ProviderAddEditForm";
            this.Load += new System.EventHandler(this.ProviderAddEditForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.clothesDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.providerBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.idSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.imageUrlTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.brandNameTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.phoneTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.addressTextEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.activeSpinEdit.Properties)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureEdit1.Properties)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private ClothesDataSet clothesDataSet;
        private System.Windows.Forms.BindingSource providerBindingSource;
        private ClothesDataSetTableAdapters.ProviderTableAdapter providerTableAdapter;
        private ClothesDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DevExpress.XtraEditors.SpinEdit idSpinEdit;
        private DevExpress.XtraEditors.TextEdit imageUrlTextEdit;
        private DevExpress.XtraEditors.TextEdit brandNameTextEdit;
        private DevExpress.XtraEditors.TextEdit phoneTextEdit;
        private DevExpress.XtraEditors.TextEdit addressTextEdit;
        private DevExpress.XtraEditors.SpinEdit activeSpinEdit;
        private System.Windows.Forms.RichTextBox infomationRichTextBox;
        private System.Windows.Forms.Button btnSaveAddProvider;
        private System.Windows.Forms.Button btnCancelAddProvider;
        private DevExpress.XtraEditors.PictureEdit pictureEdit1;
        private System.Windows.Forms.LinkLabel btnChoosePictureProvider;
    }
}