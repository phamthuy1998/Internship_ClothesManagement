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
    
    public partial class Product
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Product()
        {
            this.Images = new HashSet<Image>();
            this.ImportCouponDetails = new HashSet<ImportCouponDetail>();
            this.ProductSizeColors = new HashSet<ProductSizeColor>();
            this.Accounts = new HashSet<Account>();
            this.Promotions = new HashSet<Promotion>();
        }
    
        public int id { get; set; }
        public string title { get; set; }
        public string detail { get; set; }
        public Nullable<double> price { get; set; }
        public Nullable<int> categoryID { get; set; }
        public Nullable<int> rating { get; set; }
        public Nullable<int> active { get; set; }
        public Nullable<int> providerId { get; set; }
        public string thumnail { get; set; }
        public Nullable<int> isNew { get; set; }
        public System.DateTime addDate { get; set; }
        public Nullable<int> sold { get; set; }
    
        public virtual Category Category { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Image> Images { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ImportCouponDetail> ImportCouponDetails { get; set; }
        public virtual Provider Provider { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ProductSizeColor> ProductSizeColors { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Account> Accounts { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Promotion> Promotions { get; set; }
    }
}