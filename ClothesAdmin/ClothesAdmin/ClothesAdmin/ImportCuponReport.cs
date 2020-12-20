using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using DevExpress.XtraReports.UI;

namespace ClothesAdmin
{
    public partial class ImportCuponReport : XtraReport
    {
        public ImportCuponReport(Nullable<Int32> importcuponID)
        {
            InitializeComponent();
            try
            {
                clothesDataSet2.EnforceConstraints = false;
                this.sP_ReportImportCuponTableAdapter2.Fill(this.clothesDataSet2.SP_ReportImportCupon, importcuponID);

            }
            catch (Exception ex)
            {
            }
        }

    }
}
