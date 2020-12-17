using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class RatingParam
    {
        public int accountID { get; set; }
        public Nullable<int> rating { get; set; }
        public string comment { get; set; }
        public Nullable<int> orderId { get; set; }
        public Nullable<int> productID { get; set; }
        public Nullable<int> colorId { get; set; }
        public Nullable<int> sizeId { get; set; }
        public string imageUrl1 { get; set; }
        public string imageUrl2 { get; set; }
        public string imageUrl3 { get; set; }
        public string videoUrl { get; set; }
        public Nullable<int> parentId { get; set; }
    }
}