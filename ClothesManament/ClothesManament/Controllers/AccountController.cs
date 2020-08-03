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

        [Route("api/signUp")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public ResponseObjectModel<SPGetAccountInfoByUsername_Result1> signUp([FromBody] AccountInfo acc)
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

                        return new ResponseObjectModel<SPGetAccountInfoByUsername_Result1>()
                        {
                            message = "Đăng ký tài khoản thành công",
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
        public async Task<ResponseObjectModel<int?>> addAddress(
            Nullable<int> userId = null,
            String province = null,
            String district = null,
            String wards = null,
            String street = null,
            String name = null,
            String phone = null)
        {
            var addressId = (await Task.Run(() => entities.SP_AddAddress(userId, province, district, wards, street, name, phone).FirstOrDefault()));
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
        public async Task<ResponseObjectModel<int?>> editAddress(
           Nullable<int> addressId = null,
           String province = null,
           String district = null,
           String wards = null,
           String street = null,
           String name = null,
           String phone = null)
        {
            var result = (await Task.Run(() => entities.SP_EditAddress(addressId, province, district, wards, street, name, phone)));
            return new ResponseObjectModel<int?>
            {
                message = "Cập nhật địa chỉ thành công",
                status = true,
                code = 200,
                data = addressId
            };

        }
    }
}
