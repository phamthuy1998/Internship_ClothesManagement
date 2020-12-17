using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class ChangePassParam
    {
        public int userid { set; get; }
        public string oldpass { set; get; }
        public string newpassword { set; get; }
    }
}