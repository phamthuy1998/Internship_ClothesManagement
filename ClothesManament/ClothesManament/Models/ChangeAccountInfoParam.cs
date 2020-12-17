using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class ChangeAccountInfoParam
    {
        public int userid { set; get; }
        public string name { set; get; }
        public string phone { set; get; }
        public string email { set; get; }
    }
}