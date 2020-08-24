using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManagement.Models
{
    public class  FilterObj
    {
        /* 
         * "filter": {
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
        public List<int> categories { set; get; }
        public List<int> providers { set; get; }
        public Nullable<int> sortBy { set; get; }
    }
}