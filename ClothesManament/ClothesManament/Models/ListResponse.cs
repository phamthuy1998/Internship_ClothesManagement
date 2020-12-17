using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class ListResponse<T>
    {
        public Nullable<int> count { set; get; }
        public List<T> results { set; get; }
    }
}