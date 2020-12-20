using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using DevExpress.XtraReports.UI;

namespace ClothesAdmin
{
    public partial class InventoryReport : DevExpress.XtraReports.UI.XtraReport
    {
        public InventoryReport(Nullable<int> categoryId, Nullable<int> providerId)
        {
            InitializeComponent();
            clothesDataSet1.EnforceConstraints = false;
            this.sP_InventoryProductNewTableAdapter1.Fill(this.clothesDataSet1.SP_InventoryProductNew,
                categoryId, providerId);
        }

    }
}
