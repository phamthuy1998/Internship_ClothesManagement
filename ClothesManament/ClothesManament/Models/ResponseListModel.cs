using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class ResponseListModel<T>
    {
        public string message { set; get; }
        public bool status { set; get; }
        public int code { set; get; }
        public List<T> data { set; get; }
        public Metadata metadata { set; get; }
    }
}