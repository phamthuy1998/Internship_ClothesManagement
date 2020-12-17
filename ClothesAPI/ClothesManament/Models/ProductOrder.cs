using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class ProductOrder
    {
        public int productId { set; get; }
        public int colorId { set; get; }
        public int sizeId { set; get; }
        public int quantity { set; get; }


    }
}