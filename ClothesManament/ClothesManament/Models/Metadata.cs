using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class Metadata
    {
        public int totalCount { set; get; }
        public int pageSize { set; get; }
        public int currentPage { set; get; }
        public int totalPages { set; get; }
        public string previousPage { set; get; }
        public string nextPage { set; get; }
    }
}