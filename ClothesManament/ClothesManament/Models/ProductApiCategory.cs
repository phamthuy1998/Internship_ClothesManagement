using ClothesManament.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManagement.Models
{
    public class ProductApiCategory
    {
        /*    
        "pageSize": 20,
        "pageNumber": 1,
        "providerID": 2,
         account id để check trạng thái islike của sản phẩm,
         không truyền mặc định tất cả các sản phẩm là chưa like
        "accountId": 1,
        "filter": {
            "categories": [
                1,
                2,
                4
            ],
            "providers": [
                4,
                5,
                4
            ],
             1: Price ASC tăng dần
             2: Price desc giảm dần
             3: newest (sort by date desc)
             4: oldest (sort by date ASC)
             null: default (newest)
            "sortBy": 1
        */
        public Nullable<int> pageSize { set; get; }
        public Nullable<int> pageNumber { set; get; }
        public Nullable<int> categoryID { set; get; }
        public Nullable<int> accountId { set; get; }
        public FilterObj filter  { set; get; }
    }
}