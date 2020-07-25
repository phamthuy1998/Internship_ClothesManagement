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
        public ResponseObjectModel<SPGetAccountInfoUserID_Result> changePassword([FromBody] ChangePassParam changePass)
        {
            var result = entities.ChangePassword(changePass.userid, changePass.oldpass, changePass.newpassword).FirstOrDefault();
            if (result.HasValue)
            {
                int resultInt = result.Value;
                if (resultInt == -1)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
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
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
                    {
                        message = "Đổi mật khẩu thành công",
                        status = true,
                        code = 200,
                        data = accountInfo
                    };
                }
            }

            return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
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
        public ResponseObjectModel<SPGetAccountInfoUserID_Result> changeAccInfo([FromBody] ChangeAccountInfoParam param)
        {
            var result = entities.SP_UpdateAccInfo(param.userid, param.name, param.phone, param.email).FirstOrDefault();
            if (result.HasValue)
            {
                int resultInt = result.Value;
                if (resultInt == -1)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
                    {
                        message = "Tài khoản không tồn tại",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else
                {
                    var accountInfo = entities.SPGetAccountInfoUserID(resultInt).FirstOrDefault(); ;
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
                    {
                        message = "Cập nhật thông tin thành công",
                        status = true,
                        code = 200,
                        data = accountInfo
                    };
                }
            }

            return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
            {
                message = "Tài khoản không tồn tại",
                status = false,
                code = 200,
                data = null
            };

        }


        [Route("api/forgotPassword")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public ResponseObjectModel<String> forgotPassword(String email)
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


            return new ResponseObjectModel<String>()
            {
                message = "Thay đổi mật khẩu thất bại",
                status = false,
                code = 200,
                data = ""
            };

        }

        [Route("api/login")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public ResponseObjectModel<SPGetAccountInfoUserID_Result> login([FromBody] LoginParam loginParam)
        {
            var result = entities.SP_Login(loginParam.email, loginParam.password).FirstOrDefault();
            if (result.HasValue)
            {
                int resultInt = result.Value;
                if (resultInt == -1)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
                    {
                        message = "Username không tồn tại",
                        status = false,
                        code = 200,
                        data = null
                    };
                }
                else if (resultInt == -2)
                {
                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
                    {
                        message = "Mật khẩu không đúng",
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
                        return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
                        {
                            message = "Đăng nhập thất bại",
                            status = false,
                            code = 200,
                            data = null
                        };
                    }

                    return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
                    {
                        message = "Đăng nhập thành công",
                        status = true,
                        code = 200,
                        data = accountInfo
                    };
                }
            }

            return new ResponseObjectModel<SPGetAccountInfoUserID_Result>()
            {
                message = "Đăng nhập thất bại ",
                status = false,
                code = 200,
                data = null
            };

        }

        [Route("api/signUp")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public ResponseObjectModel<SPGetAccounByUsername_Result> signUp([FromBody] Account acc)
        {
            try
            {
                var result = entities.SP_Register(acc.name, acc.email, acc.phone, 3, acc.password, acc.username, null, 1).FirstOrDefault();
                if (result.HasValue)
                {
                    int resultInt = result.Value;
                    if (resultInt == -1)
                    {
                        return new ResponseObjectModel<SPGetAccounByUsername_Result>()
                        {
                            message = "Username đã tồn tại",
                            status = false,
                            code = 200,
                            data = null
                        };
                    }
                    else if (resultInt == -2)
                    {
                        return new ResponseObjectModel<SPGetAccounByUsername_Result>()
                        {
                            message = "Email đã tồn tại",
                            status = false,
                            code = 200,
                            data = null
                        };
                    }
                    else if (resultInt == -3)
                    {
                        return new ResponseObjectModel<SPGetAccounByUsername_Result>()
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
                        var accountInfo = entities.SPGetAccounByUsername(acc.username).FirstOrDefault();

                        if (accountInfo == null)
                        {
                            return new ResponseObjectModel<SPGetAccounByUsername_Result>()
                            {
                                message = "Lỗi đăng ký tài khoản",
                                status = false,
                                code = 200,
                                data = null
                            };
                        }

                        return new ResponseObjectModel<SPGetAccounByUsername_Result>()
                        {
                            message = "Đăng ký tài khoản thành công",
                            status = true,
                            code = 200,
                            data = accountInfo
                        };
                    }
                }
                return new ResponseObjectModel<SPGetAccounByUsername_Result>()
                {
                    message = "Lỗi tạo tài khoản!",
                    status = false,
                    code = 200,
                    data = null
                };
            }
            catch (Exception e)
            {
                return new ResponseObjectModel<SPGetAccounByUsername_Result>()
                {
                    message = "Lỗi tạo tài khoản! " + e,
                    status = false,
                    code = 200,
                    data = null
                };
            }
        }

        private Account getAccount(String username)
        {

            return entities.Accounts.FirstOrDefault(x => x.username == username);
        }

        [Route("api/accountInfo")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public ResponseObjectModel<SPGetAccount_Result> getAccountInfo([FromUri]int userId)
        {
            var result = entities.SPGetAccount(userId).FirstOrDefault();

            if (result == null)
            {
                return new ResponseObjectModel<SPGetAccount_Result>()
                {
                    message = "Account not exist.",
                    status = false,
                    code = 404
                };
            }

            return new ResponseObjectModel<SPGetAccount_Result>()
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
    }
}
