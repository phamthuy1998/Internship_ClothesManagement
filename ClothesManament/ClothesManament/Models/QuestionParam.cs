using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClothesManagement.Models
{
    public class QuestionParam
    {
        public int questionID { get; set; }
        public Nullable<int> accountID { get; set; }
        public string username { get; set; }
        public string question { get; set; }
        public Nullable<System.DateTime> dateComment { get; set; }
        public Nullable<System.DateTime> dateEdit { get; set; }
        public Nullable<int> parentQuestionID { get; set; }
        public Nullable<int> productID { get; set; }
    }
}