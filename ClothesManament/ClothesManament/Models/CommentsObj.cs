using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManagement.Models
{
    public class CommentsObj
    {
        public int ratingID { get; set; }
        public Nullable<int> accountID { get; set; }
        public string username { get; set; }
        public Nullable<int> rating { get; set; }
        public string comment { get; set; }
        public Nullable<System.DateTime> dateRating { get; set; }
        public Nullable<System.DateTime> dateEdit { get; set; }
        public Nullable<int> productID { get; set; }
        public string sizeName { get; set; }
        public string colorName { get; set; }
        public string imageUrl1 { get; set; }
        public string imageUrl2 { get; set; }
        public string imageUrl3 { get; set; }
        public string videoUrl { get; set; }
        public Nullable<int> parentId { get; set; }

        public List<SP_GetSubRating_Result2> subComments = null;
    }
}