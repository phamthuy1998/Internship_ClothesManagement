using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using DevExpress.XtraReports.UI;

namespace ClothesAdmin
{
    public partial class WarehouseReport : DevExpress.XtraReports.UI.XtraReport
    {
        public WarehouseReport(Nullable<int> categoryId, Nullable<int> provicerId)
        {
            InitializeComponent();
            clothesDataSet1.EnforceConstraints = false;
            this.sP_InventoryProductTableAdapter1.Fill(this.clothesDataSet1.SP_InventoryProduct,
                categoryId, provicerId);
        }

    }
}
