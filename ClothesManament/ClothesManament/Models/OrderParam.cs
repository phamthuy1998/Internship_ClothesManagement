using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class OrderParam
    {
        public Nullable<int> accountID { set; get; }
        public string address { set; get; }
        public string phone { set; get; }
        public string name { set; get; }
        public string note { set; get; }
        public Double price { set; get; }
        public string tokenCard { set; get; }
        public List<ProductOrder> products { set; get; }
    }
}