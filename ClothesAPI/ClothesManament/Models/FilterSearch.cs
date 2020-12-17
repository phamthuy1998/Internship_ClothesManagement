using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class FilterSearch
    {
        // -- 1: search only, 
        //    2: search and filter, 
        //    null: get all product by default 
        public Nullable<int> typeSearchFilter { get; set; }
        public String keySearch { get; set; }

        // 1: category, 2: provider 
        // --> must be not null
        public Nullable<int> typeSearch { get; set; }

        // id of provider or category (return list product in category or in provider)
        // --> must be not null
        public Nullable<int> idTypeSearch { get; set; }

        // Type of filter
        // 1: Price ASC tăng dần
        // 2: Price desc giảm dần
        // 3: newest (sort by date desc)
        // 4: oldest (sort by date ASC)
        // null: default 
        public Nullable<int> typeFilter { get; set; }


        public Nullable<int> pageSize { get; set; }
        public Nullable<int> pageNumber { get; set; }
        public Nullable<int> accountId { get; set; }
    }
}