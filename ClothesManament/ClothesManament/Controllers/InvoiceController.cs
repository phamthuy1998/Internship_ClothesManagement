using ClothesManagement.Models;
using ClothesManament.Models;
using ClothesManamentDataAccess;
using Newtonsoft.Json.Linq;
using Stripe;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net.Mail;
using System.Threading.Tasks;
using System.Web;
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

        [Route("api/allInvoice")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ListResponse<SP_GetAllInvoice_Result1>> getInvoicePaging(Nullable<int> pageSize = 20, Nullable<int> pageNumber = 1, Nullable<int> statusId = null, int? accountId = null)
        {
            if (pageSize <= 0 || pageSize > 100) pageSize = 20;
            if (pageNumber <= 0) pageNumber = 1;

            var listResponse = (await Task.Run(() => entities.SP_GetAllInvoice(statusId, pageNumber, pageSize, accountId).ToList()));
            var count = (await Task.Run(() => entities.SP_GetAllInvoiceCount(statusId, accountId).FirstOrDefault()));
            return new ListResponse<SP_GetAllInvoice_Result1>()
            {
                count = count,
                results = listResponse
            };
        }

        [Route("api/invoice-detail")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseObjectModel<OrderDetail>> getInvoiceDetail(Nullable<int> invoicecId = null)
        {
            var checkExist = (await Task.Run(() => entities.SP_CheckInvoiceExist(invoicecId).FirstOrDefault()));
            if (checkExist == 1)
            {

                var invoiceDeatail = (await Task.Run(() => entities.SP_GetInvoiceDetail(invoicecId).FirstOrDefault()));
                var listProducts = (await Task.Run(() => entities.SP_GetProductInvoice(invoicecId).ToList()));
                if (invoiceDeatail == null)
                {
                    return new ResponseObjectModel<OrderDetail>
                    {
                        message = "Lỗi không thể lấy thông tin chi tiêt đơn hàng",
                        status = false,
                        code = 200,
                        data = null
                    };
                 }else if(listProducts == null)
                {
                    return new ResponseObjectModel<OrderDetail>
                    {
                        message = "Lỗi không thể lấy danh sách sản phẩm!",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                return new ResponseObjectModel<OrderDetail>
                {
                    message = "Invoice detail",
                    status = true,
                    code = 200,
                    data = new OrderDetail()
                    {
                        id = invoiceDeatail.id,
                        updateDate = invoiceDeatail.updateDate,
                        buyDate = invoiceDeatail.buyDate,
                        name = invoiceDeatail.name,
                        phone = invoiceDeatail.phone,
                        address = invoiceDeatail.address,
                        note = invoiceDeatail.note,
                        deliveryDate = invoiceDeatail.deliveryDate,
                        price = invoiceDeatail.price,
                        statusOrderId = invoiceDeatail.statusOrderId,
                        isPaid = invoiceDeatail.isPaid,
                        payment = invoiceDeatail.payment,
                        statusInvoice = invoiceDeatail.statusInvoice,
                        products = listProducts

                    }
                };
            }
            else
            {
                return new ResponseObjectModel<OrderDetail>
                {
                    message = "Invoice id không tồn tại trong hệ thống, vui lòng kiểm tra lại",
                    status = false,
                    code = 200,
                    data = null
                };
            }
        }

        private string createEmailBody(string userName)

        {
            string body = string.Empty;
            //using streamreader for reading my htmltemplate   
            using (StreamReader reader = new StreamReader(HttpContext.Current.Server.MapPath("/Views/Home/send_mail_add_order.cshtml")))
            {
                body = reader.ReadToEnd();
            }
            body = body.Replace("{UserName}", userName); //replacing the required things  
            return body;
        }

        [HttpPost]
        [AcceptVerbs("POST")]
        [Route("api/addInvoice")]
        public async Task<ResponseObjectModel<int>> SendEmail(OrderParam orderParam)
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

            //var email = (await Task.Run(() => entities.SP_GetEmail(orderParam.accountID).FirstOrDefault()));
            //var name = entities.Accounts.FirstOrDefault(x => x.email == email);
            //MailMessage msg = new MailMessage();
            //msg.From = new MailAddress("congnghephanmemptithcm@gmail.com");
            //msg.To.Add(email);
            //msg.Subject = "Xác nhận đơn hàng";
            //MemoryCacheHelper.Add("orderParam", orderParam, DateTimeOffset.UtcNow.AddHours(1));
            //msg.Body = createEmailBody(name.username);
            //msg.Priority = MailPriority.High;
            //msg.IsBodyHtml = true;

            //using (SmtpClient client = new SmtpClient())
            //{
            //    client.EnableSsl = true;
            //    client.UseDefaultCredentials = false;
            //    client.Credentials = new NetworkCredential("congnghephanmemptithcm@gmail.com", "quankhung123");
            //    client.Host = "smtp.gmail.com";
            //    client.Port = 587;
            //    client.DeliveryMethod = SmtpDeliveryMethod.Network;
            //    client.Send(msg);
            //    await client.SendMailAsync(msg);
            //    await Task.FromResult(0);
            //}

            var messageStr = "";// "Đơn hàng đã được gứi tới " + email + ", vui lòng kiểm tra email để xác nhận đơn hàng!";
            var orderId = entities.SP_AddOrder(orderParam.accountID, orderParam.address, orderParam.phone, orderParam.name, orderParam.note).FirstOrDefault();

            ProductOrder product = new ProductOrder();
            for (int i = 0; i < orderParam.products.Count; i++)
            {
                try
                {
                    product = orderParam.products[i];
                    var result = entities.SP_AddOrderItem(orderId, product.productId, product.colorId, product.sizeId, product.quantity);
                }
                catch (Exception e)
                {

                }
            }
            var totalPrice = entities.Sp_GetPriceInvoice(orderId).FirstOrDefault();
            var statusStr = true;
            if (orderParam.tokenCard != null && !orderParam.tokenCard.Trim().Equals(""))
            {
                StripeConfiguration.ApiKey = "sk_test_51HEO9yAUHa3z0jaSDqZ2wNWozw2iEFIMgbPluEpQ6ipK9UplKlbg7laoC2RydTqAZWAw0ddfnAeBM0ATgpUKkztl00vrsHJVWN";

                var options = new ChargeCreateOptions
                {
                    Amount = long.Parse(totalPrice.ToString()),
                    Currency = "vnd",
                    Source = orderParam.tokenCard,
                    Description = "Thanh toan don hang kh id: " + orderParam.accountID,
                };
                var service = new ChargeService();
                Charge charge = service.Create(options);
                if (charge.Paid == false)
                {
                    messageStr = "Đã có lỗi xảy ra khi thanh toán, vui lòng thử lại!";
                    statusStr = false;
                }
                else
                {
                    messageStr = "Thanh toán thành công.";
                    statusStr = true;
                }
            }
            else
            {
                return new ResponseObjectModel<int>()
                {
                    message = "đặt hàng thành công",
                    status = true,
                    code = 200,
                    data = 1
                };
            }

            return new ResponseObjectModel<int>()
            {
                message = messageStr,
                status = statusStr,
                code = 200,
                data = 1
            };
        }

        [Route("api/confirmOrder")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public String addOrder()
        {
            var orderParam = MemoryCacheHelper.GetValue("orderParam") as OrderParam;
            if (orderParam.accountID == null)
            {
                MemoryCacheHelper.Add("statusOrder", -1, DateTimeOffset.UtcNow.AddHours(1));
                return "Đã hết phiên làm việc, vui lòng kiểm tra lại";
            }
            var isAccExist = entities.SP_CheckUserExist(orderParam.accountID).FirstOrDefault();
            if (isAccExist == 0)
            {
                MemoryCacheHelper.Add("statusOrder", -2, DateTimeOffset.UtcNow.AddHours(1));
                return "Tài khoản không tồn tại trong hệ thống, vui lòng kiểm tra lại!";
            }
            else if (orderParam == null || orderParam.address == null || orderParam.address.Trim().Equals("") || orderParam.phone == null
                || orderParam.phone.Trim().Equals("") || orderParam.name == null || orderParam.name.Trim().Equals(""))
            {
                MemoryCacheHelper.Add("statusOrder", -3, DateTimeOffset.UtcNow.AddHours(1));
                return "Vui lòng không bỏ trống thông tin!";
            }
            else
            {
                var orderId = entities.SP_AddOrder(orderParam.accountID, orderParam.address, orderParam.phone, orderParam.name, orderParam.note).FirstOrDefault();
                ProductOrder product = new ProductOrder();
                for (int i = 0; i < orderParam.products.Count; i++)
                {
                    product = orderParam.products[i];
                    entities.SP_AddOrderItem(orderId, product.productId, product.colorId, product.sizeId, product.quantity);
                }

                MemoryCacheHelper.Add("statusOrder", 1, DateTimeOffset.UtcNow.AddHours(1));
                return "Đặt hàng thàng công";
            }
        }

        private string createEmailBody(string userName, string message)
        {
            string body = string.Empty;
            using (StreamReader reader = new StreamReader(HttpContext.Current.Server.MapPath("/Views/Home/htmlTemplate.cshtml")))
            {
                body = reader.ReadToEnd();
            }
            body = body.Replace("{UserName}", userName);
            body = body.Replace("{message}", message);
            return body;
        }


        [HttpGet]
        [AcceptVerbs("GET")]
        [Route("api/getStatusDetail")]
        public String getStatusOrder()
        {
            String res = MemoryCacheHelper.GetValue("orderParam") as String;
            return res;
        }


    }
}
