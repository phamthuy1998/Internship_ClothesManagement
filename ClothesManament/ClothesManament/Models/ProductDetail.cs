using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class ProductDetail
    {
        public int id { get; set; }
        public string title { get; set; }
        public string detail { get; set; }
        public Nullable<double> price { get; set; }
        public Nullable<int> categoryID { get; set; }
        public Nullable<int> sold { get; set; }
        public Nullable<int> rating { get; set; }
        public Nullable<int> active { get; set; }
        public Nullable<int> providerId { get; set; }
        public string thumnail { get; set; }
        public Nullable<int> isNew { get; set; }
        public Nullable<System.DateTime> addDate { get; set; }
        public int isLike { get; set; }

        public SP_GetProviderDetail_Result1 provider { get; set; }

        public List<String> images { set; get; }
        public List<SP_GetColorsOfProduct_Result> colors { set; get; }
        public List<SP_GetSizesOfProduct_Result> sizes { set; get; }
        public List<SP_GetSizesColorsOfProduct_Result> sizesColors { set; get; }

    }
}