//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ClothesAdmin
{
    using System;
    using System.Collections.Generic;
    
    public partial class InvoiceItem
    {
        public int orderId { get; set; }
        public int productId { get; set; }
        public int colorId { get; set; }
        public int sizeId { get; set; }
        public Nullable<double> unitPrice { get; set; }
        public Nullable<int> quantity { get; set; }
    
        public virtual Invoice Invoice { get; set; }
        public virtual ProductSizeColor ProductSizeColor { get; set; }
    }
}
