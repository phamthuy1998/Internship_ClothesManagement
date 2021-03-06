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
    
    public partial class ProductSizeColor
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public ProductSizeColor()
        {
            this.InvoiceItems = new HashSet<InvoiceItem>();
        }
    
        public int productID { get; set; }
        public int sizeId { get; set; }
        public int colorID { get; set; }
        public Nullable<int> quantity { get; set; }
        public string imageUrl { get; set; }
        public Nullable<int> active { get; set; }
    
        public virtual Color Color { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<InvoiceItem> InvoiceItems { get; set; }
        public virtual Product Product { get; set; }
        public virtual Size Size { get; set; }
    }
}
