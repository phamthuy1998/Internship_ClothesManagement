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
    
    public partial class Question
    {
        public int questionID { get; set; }
        public Nullable<int> accountID { get; set; }
        public string question1 { get; set; }
        public Nullable<System.DateTime> dateComment { get; set; }
        public Nullable<System.DateTime> dateEdit { get; set; }
        public Nullable<int> parentQuestionID { get; set; }
        public Nullable<int> productID { get; set; }
    
        public virtual Account Account { get; set; }
        public virtual Product Product { get; set; }
    }
}
