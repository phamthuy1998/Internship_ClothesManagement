using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManament.Models
{
    public class AddressEit
    {
        public Nullable<int> id { set; get; }
        public String province { set; get; }
        public String district { set; get; }
        public String wards { set; get; }
        public String street { set; get; }
        public String name { set; get; }
        public String phone { set; get; }
        public Nullable<int> isDefault { set; get; }
        public Nullable<int> accountId { set; get; }
    }
}