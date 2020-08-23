using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using DevExpress.XtraReports.UI;

namespace ClothesAdmin
{
    public partial class ProductReport : DevExpress.XtraReports.UI.XtraReport
    {
        public ProductReport(Nullable<Int32> type, Nullable<Int32> top, Nullable<Int32> category, Nullable<Int32> provider, DateTime dateBegin, DateTime dateEnd)
        {
            InitializeComponent();
            clothesDataSet1.EnforceConstraints = false;
            this.sP_StatisticProductTableAdapter1.Fill(this.clothesDataSet1.SP_StatisticProduct,
                type, top, category, provider, dateBegin, dateEnd);
        }

    }
}
