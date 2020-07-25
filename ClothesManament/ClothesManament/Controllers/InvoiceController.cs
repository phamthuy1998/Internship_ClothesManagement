using ClothesManament.Models;
using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace ClothesManagement.Controllers
{
    public class InvoiceController : ApiController
    {
        ClothesEntities entities;

        public InvoiceController()
        {
            entities = new ClothesEntities();
        }


        [Route("api/addnvoice")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public ResponseObjectModel<int> addOrder(OrderParam orderParam)
        {
            if (orderParam.accountID == null)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "UserId không được bỏ trống, vui lòng đăng nhập lại để sử dụng tính năng này!",
                    status = false,
                    code = 200,
                    data = -1
                };
            }
            var isAccExist = entities.SP_CheckUserExist(orderParam.accountID).FirstOrDefault();
            if (isAccExist == 0)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Tài khoản không tồn tại trong hệ thống, vui lòng kiểm tra lại!",
                    status = false,
                    code = 200,
                    data = -1
                };
            }
            else if (orderParam == null || orderParam.address == null || orderParam.address.Trim().Equals("") || orderParam.phone == null
                || orderParam.phone.Trim().Equals("") || orderParam.name == null || orderParam.name.Trim().Equals(""))
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Vui lòng không bỏ trống thông tin!",
                    status = false,
                    code = 200,
                    data = -1
                };
            }
            else
            {
                Nullable< int> orderId = entities.SP_AddOrder(orderParam.accountID, orderParam.address, orderParam.phone, orderParam.name, orderParam.note).FirstOrDefault();
                ProductOrder product = new ProductOrder();
                for (int i = 0; i < orderParam.products.Count; i++)
                {
                    product = orderParam.products[i];
                    entities.SP_AddOrderItem(product.id, product.quantity, orderId);
                }
                return new ResponseObjectModel<int>()
                {
                    message = "Danh sách sản phẩm yêu thích!",
                    status = true,
                    code = 200,
                    data = 0
                };
            }
        }
    }
}
