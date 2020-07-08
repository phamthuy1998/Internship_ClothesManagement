using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class ResponseObjectModel<T>
    {
        public string message { set; get; }
        public bool status { set; get; }
        public int code { set; get; }
        public T data { set; get; }
    }
}