using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using System.Web.Http.ModelBinding;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.Owin.Security;
using Microsoft.Owin.Security.Cookies;
using Microsoft.Owin.Security.OAuth;
using ClothesManament.Models;
using ClothesManament.Providers;
using ClothesManament.Results;
using ClothesManamentDataAccess;
using System.Linq;
using System.Data.Entity.Core.Objects;
using System.Net.Mail;
using ClothesManagement.Models;
using System.IO;
using System.Net;
using System.Text;

namespace ClothesManament.Controllers
{

    public class AccountController : ApiController
    {

        private ClothesEntities entities;
        public AccountController()
        {
            entities = new ClothesEntities();
        }

        [Route("api/logout")]
        [AcceptVerbs("DELETE")]
        [HttpDelete]
        public void signOut()
        {

        }

        [Route("api/changePassword")]
        [AcceptVerbs("PUT")]
        [HttpPut]
        public ResponseObjectModel<SPGetAccountInfoUserID_Result1> changePassword([FromBody] ChangePassParam changePass)
        {
            var result = entities.ChangePassword(changePass.userid, changePass.oldpass, changePass.newpassword).FirstOrDefault();
            if (result.HasValue)
            {
                int resultInt = result.Value;
                if (resultInt == -1)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Mật khẩu cũ không đúng",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else
                {
                    var accountInfo = entities.SPGetAccountInfoUserID(resultInt).FirstOrDefault(); ;
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Đổi mật khẩu thành công",
                        status = true,
                        code = 200,
                        data = accountInfo
                    };
                }
            }

            return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
            {
                message = "Đổi mật khẩu thất bại ",
                status = false,
                code = 200,
                data = null
            };

        }


        [Route("api/changeAccInfo")]
        [AcceptVerbs("PUT")]
        [HttpPut]
        public ResponseObjectModel<SPGetAccountInfoUserID_Result1> changeAccInfo([FromBody] ChangeAccountInfoParam param)
        {
            var result = entities.SP_UpdateAccInfo(param.userid, param.name, param.phone, param.email).FirstOrDefault();
            if (result.HasValue)
            {
                int resultInt = result.Value;
                if (resultInt == -1)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Tài khoản không tồn tại",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else if (resultInt == -2)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Email đã dùng cho tài khoản khác",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else if (resultInt == -3)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Số điện thoại đã dùng cho tài khoản khác",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else
                {
                    var accountInfo = entities.SPGetAccountInfoUserID(resultInt).FirstOrDefault(); ;
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Cập nhật thông tin thành công",
                        status = true,
                        code = 200,
                        data = accountInfo
                    };
                }
            }

            return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
            {
                message = "Tài khoản không tồn tại",
                status = false,
                code = 200,
                data = null
            };

        }

        private string emailFrgotPassword(string userName, string password)

        {
            string body = string.Empty;
            //using streamreader for reading my htmltemplate   
            using (StreamReader reader = new StreamReader(HttpContext.Current.Server.MapPath("/Views/Home/forgot_password.cshtml")))
            {
                body = reader.ReadToEnd();
            }
            body = body.Replace("{UserName}", userName); //replacing the required things  
            body = body.Replace("{password}", password);
            return body;
        }
        // Generate a random number between two numbers    
        public int RandomNumber(int min, int max)
        {
            Random random = new Random();
            return random.Next(min, max);
        }
        // Generate a random password of a given length (optional)  
        public string RandomPassword(int size = 0)
        {
            StringBuilder builder = new StringBuilder();
            builder.Append(RandomNumber(100000, 999999));
            return builder.ToString();
        }
        // Generate a random string with a given size and case.   
        // If second parameter is true, the return string is lowercase  
        public string RandomString(int size, bool lowerCase)
        {
            StringBuilder builder = new StringBuilder();
            Random random = new Random();
            char ch;
            for (int i = 0; i < size; i++)
            {
                ch = Convert.ToChar(Convert.ToInt32(Math.Floor(26 * random.NextDouble() + 65)));
                builder.Append(ch);
            }
            if (lowerCase)
                return builder.ToString().ToLower();
            return builder.ToString();
        }

        [Route("api/forgot-password")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ResponseObjectModel<string>> forgotPassword(String email)
        {
            var result = entities.Accounts.FirstOrDefault(x => x.email == email);
            if (result == null)
            {
                return new ResponseObjectModel<String>()
                {
                    message = "Email không tồn tại trong hệ thống",
                    status = false,
                    code = 200,
                    data = null
                };
            }

            // send mail reset password

            MailMessage msg = new MailMessage();
            msg.From = new MailAddress("congnghephanmemptithcm@gmail.com");
            msg.To.Add(email);
            msg.Subject = "Đổi mật khẩu";
            var password = RandomPassword();
            MemoryCacheHelper.Add("changePassword", password, DateTimeOffset.UtcNow.AddHours(1));
            MemoryCacheHelper.Add("emailChangePassword", email, DateTimeOffset.UtcNow.AddHours(1));
            var name = entities.Accounts.FirstOrDefault(x => x.email == email);
            msg.Body = emailFrgotPassword(name.username, password);
            //msg.Priority = MailPriority.High;
            msg.IsBodyHtml = true;

            using (SmtpClient client = new SmtpClient())
            {
                client.EnableSsl = true;
                client.UseDefaultCredentials = false;
                client.Credentials = new NetworkCredential("congnghephanmemptithcm@gmail.com", "quankhung123");
                client.Host = "smtp.gmail.com";
                client.Port = 587;
                client.DeliveryMethod = SmtpDeliveryMethod.Network;
                //client.Send(msg);
                await client.SendMailAsync(msg);
                await Task.FromResult(0);
            }


            return new ResponseObjectModel<String>()
            {
                message = "Email reset password đã được gửi tới email " + email,
                status = true,
                code = 200,
                data = ""
            };

        }


        [Route("api/updated-password")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public String changePassword()
        {
            var pass = MemoryCacheHelper.GetValue("changePassword") as String;
            var email = MemoryCacheHelper.GetValue("emailChangePassword") as String;

            var result = entities.forgotPasswordReset(email, pass);
            if (result == -1)
            {
                return "email không tồn tại trong hệ thống, vui lòng kiểm tra lại";
            }
            else
            {
                return "Đổi mật khẩu thành công";
            }

        }

        [Route("api/login")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public ResponseObjectModel<SPGetAccountInfoUserID_Result1> login([FromBody] LoginParam loginParam)
        {
            var result = entities.SP_Login(loginParam.email, loginParam.password).FirstOrDefault();
            if (result.HasValue)
            {
                int resultInt = result.Value;
                if (resultInt == -1)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Username không tồn tại",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else if (resultInt == -2)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Mật khẩu không đúng",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else if (resultInt == -3)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Tài khoản chưa được xác thực, vui lòng kiểm tra mail của bạn",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else
                {
                    var accountInfo = entities.SPGetAccountInfoUserID(resultInt).FirstOrDefault(); ;

                    if (accountInfo == null)
                    {
                        return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                        {
                            message = "Đăng nhập thất bại",
                            status = false,
                            code = 200,
                            data = null
                        };
                    }

                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
                    {
                        message = "Đăng nhập thành công",
                        status = true,
                        code = 200,
                        data = accountInfo
                    };
                }
            }

            return new ResponseObjectModel<SPGetAccountInfoUserID_Result1>()
            {
                message = "Đăng nhập thất bại ",
                status = false,
                code = 200,
                data = null
            };

        }

        private string createMailAuthen(string userName)

        {
            string body = string.Empty;
            //using streamreader for reading my htmltemplate   
            using (StreamReader reader = new StreamReader(HttpContext.Current.Server.MapPath("/Views/Home/auth.cshtml")))
            {
                body = reader.ReadToEnd();
            }
            body = body.Replace("{name}", userName); //replacing the required things  
            return body;
        }

        [Route("api/confirm-account")]
        [HttpGet]
        [AcceptVerbs("GET")]
        public String authenAccount()
        {
            var email = MemoryCacheHelper.GetValue("emailRegister") as String;
            if (email == null) return "Xác thực tài khoản thất bại, liên kết đã hết hạn!";
            var result = entities.SP_AuthAccount(email).FirstOrDefault();
            if (result == -1)
            {
                return "Email của bạn chưa được đăng ký trong hệ thống, vui lòng kiểm tra lại!";
            }
            else
            {
                return "Tài khoản của bạn đã được xác thực thành công!";
            }
        }

        [Route("api/signUp")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ResponseObjectModel<SPGetAccountInfoByUsername_Result1>> signUp([FromBody] AccountInfo acc)
        {
            try
            {
                var result = entities.SP_Register(acc.name, acc.email, acc.phone, 3, acc.password, acc.username, null, 1).FirstOrDefault();
                if (result.HasValue)
                {
                    int resultInt = result.Value;
                    if (resultInt == -1)
                    {
                        return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                        {
                            message = "Username đã tồn tại",
                            status = false,
                            code = 200,
                            data = null
                        };
                    }
                    else if (resultInt == -2)
                    {
                        return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                        {
                            message = "Email đã tồn tại",
                            status = false,
                            code = 200,
                            data = null
                        };
                    }
                    else if (resultInt == -3)
                    {
                        return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                        {
                            message = "Số điện thoại đã tồn tại",
                            status = false,
                            code = 200,
                            data = null
                        };
                    }
                    else
                    {
                        //var accountInfo = entities.SPGetAccountInfoByUsername(acc.username).FirstOrDefault();
                        var accountInfo = entities.SPGetAccountInfoByUsername(acc.username).FirstOrDefault();

                        if (accountInfo == null)
                        {
                            return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                            {
                                message = "Lỗi đăng ký tài khoản",
                                status = false,
                                code = 200,
                                data = null
                            };
                        }
                        MailMessage msg = new MailMessage();
                        msg.From = new MailAddress("congnghephanmemptithcm@gmail.com");
                        msg.To.Add(acc.email);
                        msg.Subject = "Xác thực tài khoản";
                        MemoryCacheHelper.Add("emailRegister", acc.email, DateTimeOffset.UtcNow.AddHours(1));

                        msg.Body = createMailAuthen(acc.name);
                        //msg.Priority = MailPriority.High;
                        msg.IsBodyHtml = true;

                        using (SmtpClient client = new SmtpClient())
                        {
                            client.EnableSsl = true;
                            client.UseDefaultCredentials = false;
                            client.Credentials = new NetworkCredential("congnghephanmemptithcm@gmail.com", "quankhung123");
                            client.Host = "smtp.gmail.com";
                            client.Port = 587;
                            client.DeliveryMethod = SmtpDeliveryMethod.Network;
                            //client.Send(msg);
                            await client.SendMailAsync(msg);
                            await Task.FromResult(0);
                        }
                        return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                        {
                            message = "Đăng ký tài khoản thành công, vui lòng kiểm tra mail để xác thực tài khoản!",
                            status = true,
                            code = 200,
                            data = accountInfo
                        };
                    }
                }
                return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                {
                    message = "Lỗi tạo tài khoản!",
                    status = false,
                    code = 200,
                    data = null
                };
            }
            catch (Exception e)
            {
                return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                {
                    message = "Lỗi tạo tài khoản! " + e,
                    status = false,
                    code = 200,
                    data = null
                };
            }
        }

        [Route("api/accountInfo")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public ResponseObjectModel<SPGetAccountInfoByUserId_Result1> getAccountInfo([FromUri]int userId)
        {
            var result = entities.SPGetAccountInfoByUserId(userId).FirstOrDefault();

            if (result == null)
            {
                return new ResponseObjectModel<SPGetAccountInfoByUserId_Result1>()
                {
                    message = "Account not exist.",
                    status = false,
                    code = 404
                };
            }

            return new ResponseObjectModel<SPGetAccountInfoByUserId_Result1>()
            {
                message = "Account info",
                status = true,
                code = 200,
                data = result
            };
        }

        [Route("api/address")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<IHttpActionResult> getAddress([FromUri]int accountId)
        {
            return Ok(await Task.Run(() => entities.SP_GetAllAddress(accountId)));
        }

        [Route("api/del-addess")]
        [AcceptVerbs("DELETE")]
        [HttpDelete]
        public async Task<ResponseObjectModel<int>> delAddress([FromUri]Nullable<int> addressId = null)
        {
            if (addressId == null)
            {
                return new ResponseObjectModel<int>
                {
                    message = "Id địa chỉ không được bỏ trống",
                    status = false,
                    code = 200,
                    data = -1
                };
            }
            var delStatus = (await Task.Run(() => entities.SP_DelAddress(addressId).FirstOrDefault()));
            if (delStatus == 1)
                return new ResponseObjectModel<int>
                {
                    message = "Xóa địa chỉ thành công",
                    status = true,
                    code = 200,
                    data = 1
                };
            else
                return new ResponseObjectModel<int>
                {
                    message = "Xóa địa chỉ thất bại",
                    status = false,
                    code = 200,
                    data = -1
                };
        }

        [Route("api/add-addess")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ResponseObjectModel<int?>> addAddress( AddressEit address)
        {
            var addressId = (await Task.Run(() => entities.SP_AddAddress(address.accountId, address.province, address.district, address.wards, address.street, address.name, address.phone, address.isDefault).FirstOrDefault()));
            if (addressId > 0)
                return new ResponseObjectModel<int?>
                {
                    message = "Thêm địa chỉ thành công",
                    status = true,
                    code = 200,
                    data = addressId
                };
            else
                return new ResponseObjectModel<int?>
                {
                    message = "Thêm địa chỉ thất bại",
                    status = false,
                    code = 200,
                    data = -1
                };
        }

        [Route("api/edit-addess")]
        [AcceptVerbs("PUT")]
        [HttpPut]
        public ResponseObjectModel<int?> editAddress(AddressEit address)

        {
          var result=  entities.SP_EditAddress(address.id, address.province, address.district, address.wards, address.street, address.name, address.phone, address.isDefault, address.accountId);
            return new ResponseObjectModel<int?>
            {
                message = "Cập nhật địa chỉ thành công",
                status = true,
                code = 200,
                data = result
            };

        }
    }
}
