using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using DevExpress.XtraReports.UI;

namespace ClothesAdmin
{
    public partial class InvoiceXReport : DevExpress.XtraReports.UI.XtraReport
    {
        private int orderId;
        public InvoiceXReport(int orderId)
        {
            InitializeComponent();
            this.sP_ReportGetInvoiceDetailTableAdapter1.Connection.ConnectionString = Program.connstr;
            this.sP_ReportGetInvoiceDetailTableAdapter1.Fill(this.clothesDataSet1.SP_ReportGetInvoiceDetail, orderId);
        }

    }
}
