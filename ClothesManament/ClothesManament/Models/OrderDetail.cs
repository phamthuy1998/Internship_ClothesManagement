using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class OrderDetail
    {
        public int id { get; set; }
        public Nullable<System.DateTime> updateDate { get; set; }
        public Nullable<System.DateTime> buyDate { get; set; }
        public string name { get; set; }
        public string phone { get; set; }
        public string address { get; set; }
        public string note { get; set; }
        public Nullable<System.DateTime> deliveryDate { get; set; }
        public Nullable<double> price { get; set; }
        public Nullable<int> statusOrderId { get; set; }
        public Nullable<int> isPaid { get; set; }
        public string payment { get; set; }
        public string statusInvoice { get; set; }
        public List<SP_GetProductInvoice_Result3> products { get; set; }
    }
}