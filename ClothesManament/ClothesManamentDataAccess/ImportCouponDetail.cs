//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ClothesManamentDataAccess
{
    using System;
    using System.Collections.Generic;
    
    public partial class ImportCouponDetail
    {
        public int idCoupon { get; set; }
        public int idProduct { get; set; }
        public Nullable<int> quantity { get; set; }
        public Nullable<double> price { get; set; }
        public int colorId { get; set; }
        public int sizeID { get; set; }
    
        public virtual ImportCoupon ImportCoupon { get; set; }
        public virtual Product Product { get; set; }
        public virtual ProductSizeColor ProductSizeColor { get; set; }
    }
}
