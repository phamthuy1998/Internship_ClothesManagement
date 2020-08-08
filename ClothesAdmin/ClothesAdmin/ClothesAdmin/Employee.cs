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
    
    public partial class Employee
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Employee()
        {
            this.ImportCoupons = new HashSet<ImportCoupon>();
        }
    
        public int id { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public string phone { get; set; }
        public string address { get; set; }
        public string avatar { get; set; }
        public Nullable<System.DateTime> dateEnd { get; set; }
        public Nullable<System.DateTime> dateBegin { get; set; }
        public Nullable<System.DateTime> birthday { get; set; }
        public Nullable<int> isWorking { get; set; }
        public Nullable<int> idAccount { get; set; }
    
        public virtual Account Account { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ImportCoupon> ImportCoupons { get; set; }
    }
}