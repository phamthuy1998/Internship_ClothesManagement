using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClothesAdmin
{
    class StatusOrder
    {
        public int id { get; set; }
        public string Text { get; set; }

        public StatusOrder(int id, string text)
        {
            this.id = id;
            this.Text = text;
        }

        public StatusOrder() { }
    }
}
