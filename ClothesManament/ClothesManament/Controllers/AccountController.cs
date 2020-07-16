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

        private ClothesManamentEntities entities;
        public AccountController()
        {
            entities = new ClothesManamentEntities();
        }

        [Route("api/logout")]
        [AcceptVerbs("DELETE")]
        [HttpDelete]
        public void signOut()
        {
           
        }
        //public ResponseObjectModel<string> signOut()
        //{
        //    return new ResponseObjectModel<string>()
        //    {
        //        message = "Logout success!",
        //        status = true,
        //        code = 200,
        //        data = ""
        //    };
        //}

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
        public ResponseObjectModel<String> forgotPassword([FromBody] String email)
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
                var result = entities.SP_Register(acc.name, acc.email, acc.phone, acc.roleId, acc.password, acc.username, acc.imageUrl, acc.active).FirstOrDefault();
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
        public ResponseObjectModel<ObjectResult<SPGetAccountInfoByUserId_Result>> getAccountInfo([FromUri]int userId)
        {
            var result = entities.SPGetAccountInfoByUserId(userId);

            if (result == null)
            {
                return new ResponseObjectModel<ObjectResult<SPGetAccountInfoByUserId_Result>>()
                {
                    message = "Account not exist.",
                    status = false,
                    code = 404
                };
            }

            return new ResponseObjectModel<ObjectResult<SPGetAccountInfoByUserId_Result>>()
            {
                message = "Account info",
                status = true,
                code = 200,
                data = result
            };
        }



        private const string LocalLoginProvider = "Local";
        private ApplicationUserManager _userManager;



        public AccountController(ApplicationUserManager userManager,
            ISecureDataFormat<AuthenticationTicket> accessTokenFormat)
        {
            UserManager = userManager;
            AccessTokenFormat = accessTokenFormat;
        }

        public ApplicationUserManager UserManager
        {
            get
            {
                return _userManager ?? Request.GetOwinContext().GetUserManager<ApplicationUserManager>();
            }
            private set
            {
                _userManager = value;
            }
        }

        public ISecureDataFormat<AuthenticationTicket> AccessTokenFormat { get; private set; }

        // GET api/Account/UserInfo
        [HostAuthentication(DefaultAuthenticationTypes.ExternalBearer)]
        [Route("UserInfo")]
        public UserInfoViewModel GetUserInfo()
        {
            ExternalLoginData externalLogin = ExternalLoginData.FromIdentity(User.Identity as ClaimsIdentity);

            return new UserInfoViewModel
            {
                Email = User.Identity.GetUserName(),
                HasRegistered = externalLogin == null,
                LoginProvider = externalLogin != null ? externalLogin.LoginProvider : null
            };
        }

        // POST api/Account/Logout
        [Route("Logout")]
        public IHttpActionResult Logout()
        {
            Authentication.SignOut(CookieAuthenticationDefaults.AuthenticationType);
            return Ok();
        }

        // GET api/Account/ManageInfo?returnUrl=%2F&generateState=true
        [Route("ManageInfo")]
        public async Task<ManageInfoViewModel> GetManageInfo(string returnUrl, bool generateState = false)
        {
            IdentityUser user = await UserManager.FindByIdAsync(User.Identity.GetUserId());

            if (user == null)
            {
                return null;
            }

            List<UserLoginInfoViewModel> logins = new List<UserLoginInfoViewModel>();

            foreach (IdentityUserLogin linkedAccount in user.Logins)
            {
                logins.Add(new UserLoginInfoViewModel
                {
                    LoginProvider = linkedAccount.LoginProvider,
                    ProviderKey = linkedAccount.ProviderKey
                });
            }

            if (user.PasswordHash != null)
            {
                logins.Add(new UserLoginInfoViewModel
                {
                    LoginProvider = LocalLoginProvider,
                    ProviderKey = user.UserName,
                });
            }

            return new ManageInfoViewModel
            {
                LocalLoginProvider = LocalLoginProvider,
                Email = user.UserName,
                Logins = logins,
                ExternalLoginProviders = GetExternalLogins(returnUrl, generateState)
            };
        }

        // POST api/Account/ChangePassword
        [Route("ChangePassword")]
        public async Task<IHttpActionResult> ChangePassword(ChangePasswordBindingModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            IdentityResult result = await UserManager.ChangePasswordAsync(User.Identity.GetUserId(), model.OldPassword,
                model.NewPassword);

            if (!result.Succeeded)
            {
                return GetErrorResult(result);
            }

            return Ok();
        }

        // POST api/Account/SetPassword
        [Route("SetPassword")]
        public async Task<IHttpActionResult> SetPassword(SetPasswordBindingModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            IdentityResult result = await UserManager.AddPasswordAsync(User.Identity.GetUserId(), model.NewPassword);

            if (!result.Succeeded)
            {
                return GetErrorResult(result);
            }

            return Ok();
        }

        // POST api/Account/AddExternalLogin
        [Route("AddExternalLogin")]
        public async Task<IHttpActionResult> AddExternalLogin(AddExternalLoginBindingModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            Authentication.SignOut(DefaultAuthenticationTypes.ExternalCookie);

            AuthenticationTicket ticket = AccessTokenFormat.Unprotect(model.ExternalAccessToken);

            if (ticket == null || ticket.Identity == null || (ticket.Properties != null
                && ticket.Properties.ExpiresUtc.HasValue
                && ticket.Properties.ExpiresUtc.Value < DateTimeOffset.UtcNow))
            {
                return BadRequest("External login failure.");
            }

            ExternalLoginData externalData = ExternalLoginData.FromIdentity(ticket.Identity);

            if (externalData == null)
            {
                return BadRequest("The external login is already associated with an account.");
            }

            IdentityResult result = await UserManager.AddLoginAsync(User.Identity.GetUserId(),
                new UserLoginInfo(externalData.LoginProvider, externalData.ProviderKey));

            if (!result.Succeeded)
            {
                return GetErrorResult(result);
            }

            return Ok();
        }

        // POST api/Account/RemoveLogin
        [Route("RemoveLogin")]
        public async Task<IHttpActionResult> RemoveLogin(RemoveLoginBindingModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            IdentityResult result;

            if (model.LoginProvider == LocalLoginProvider)
            {
                result = await UserManager.RemovePasswordAsync(User.Identity.GetUserId());
            }
            else
            {
                result = await UserManager.RemoveLoginAsync(User.Identity.GetUserId(),
                    new UserLoginInfo(model.LoginProvider, model.ProviderKey));
            }

            if (!result.Succeeded)
            {
                return GetErrorResult(result);
            }

            return Ok();
        }

        // GET api/Account/ExternalLogin
        [OverrideAuthentication]
        [HostAuthentication(DefaultAuthenticationTypes.ExternalCookie)]
        [AllowAnonymous]
        [Route("ExternalLogin", Name = "ExternalLogin")]
        public async Task<IHttpActionResult> GetExternalLogin(string provider, string error = null)
        {
            if (error != null)
            {
                return Redirect(Url.Content("~/") + "#error=" + Uri.EscapeDataString(error));
            }

            if (!User.Identity.IsAuthenticated)
            {
                return new ChallengeResult(provider, this);
            }

            ExternalLoginData externalLogin = ExternalLoginData.FromIdentity(User.Identity as ClaimsIdentity);

            if (externalLogin == null)
            {
                return InternalServerError();
            }

            if (externalLogin.LoginProvider != provider)
            {
                Authentication.SignOut(DefaultAuthenticationTypes.ExternalCookie);
                return new ChallengeResult(provider, this);
            }

            ApplicationUser user = await UserManager.FindAsync(new UserLoginInfo(externalLogin.LoginProvider,
                externalLogin.ProviderKey));

            bool hasRegistered = user != null;

            if (hasRegistered)
            {
                Authentication.SignOut(DefaultAuthenticationTypes.ExternalCookie);

                ClaimsIdentity oAuthIdentity = await user.GenerateUserIdentityAsync(UserManager,
                   OAuthDefaults.AuthenticationType);
                ClaimsIdentity cookieIdentity = await user.GenerateUserIdentityAsync(UserManager,
                    CookieAuthenticationDefaults.AuthenticationType);

                AuthenticationProperties properties = ApplicationOAuthProvider.CreateProperties(user.UserName);
                Authentication.SignIn(properties, oAuthIdentity, cookieIdentity);
            }
            else
            {
                IEnumerable<Claim> claims = externalLogin.GetClaims();
                ClaimsIdentity identity = new ClaimsIdentity(claims, OAuthDefaults.AuthenticationType);
                Authentication.SignIn(identity);
            }

            return Ok();
        }

        // GET api/Account/ExternalLogins?returnUrl=%2F&generateState=true
        [AllowAnonymous]
        [Route("ExternalLogins")]
        public IEnumerable<ExternalLoginViewModel> GetExternalLogins(string returnUrl, bool generateState = false)
        {
            IEnumerable<AuthenticationDescription> descriptions = Authentication.GetExternalAuthenticationTypes();
            List<ExternalLoginViewModel> logins = new List<ExternalLoginViewModel>();

            string state;

            if (generateState)
            {
                const int strengthInBits = 256;
                state = RandomOAuthStateGenerator.Generate(strengthInBits);
            }
            else
            {
                state = null;
            }

            foreach (AuthenticationDescription description in descriptions)
            {
                ExternalLoginViewModel login = new ExternalLoginViewModel
                {
                    Name = description.Caption,
                    Url = Url.Route("ExternalLogin", new
                    {
                        provider = description.AuthenticationType,
                        response_type = "token",
                        client_id = Startup.PublicClientId,
                        redirect_uri = new Uri(Request.RequestUri, returnUrl).AbsoluteUri,
                        state = state
                    }),
                    State = state
                };
                logins.Add(login);
            }

            return logins;
        }

        // POST api/Account/Register
        [AllowAnonymous]
        [Route("Register")]
        public async Task<IHttpActionResult> Register(RegisterBindingModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var user = new ApplicationUser() { UserName = model.Email, Email = model.Email };

            IdentityResult result = await UserManager.CreateAsync(user, model.Password);

            if (!result.Succeeded)
            {
                return GetErrorResult(result);
            }

            return Ok();
        }

        // POST api/Account/RegisterExternal
        [OverrideAuthentication]
        [HostAuthentication(DefaultAuthenticationTypes.ExternalBearer)]
        [Route("RegisterExternal")]
        public async Task<IHttpActionResult> RegisterExternal(RegisterExternalBindingModel model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var info = await Authentication.GetExternalLoginInfoAsync();
            if (info == null)
            {
                return InternalServerError();
            }

            var user = new ApplicationUser() { UserName = model.Email, Email = model.Email };

            IdentityResult result = await UserManager.CreateAsync(user);
            if (!result.Succeeded)
            {
                return GetErrorResult(result);
            }

            result = await UserManager.AddLoginAsync(user.Id, info.Login);
            if (!result.Succeeded)
            {
                return GetErrorResult(result);
            }
            return Ok();
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing && _userManager != null)
            {
                _userManager.Dispose();
                _userManager = null;
            }

            base.Dispose(disposing);
        }

        #region Helpers

        private IAuthenticationManager Authentication
        {
            get { return Request.GetOwinContext().Authentication; }
        }

        private IHttpActionResult GetErrorResult(IdentityResult result)
        {
            if (result == null)
            {
                return InternalServerError();
            }

            if (!result.Succeeded)
            {
                if (result.Errors != null)
                {
                    foreach (string error in result.Errors)
                    {
                        ModelState.AddModelError("", error);
                    }
                }

                if (ModelState.IsValid)
                {
                    // No ModelState errors are available to send, so just return an empty BadRequest.
                    return BadRequest();
                }

                return BadRequest(ModelState);
            }

            return null;
        }

        private class ExternalLoginData
        {
            public string LoginProvider { get; set; }
            public string ProviderKey { get; set; }
            public string UserName { get; set; }

            public IList<Claim> GetClaims()
            {
                IList<Claim> claims = new List<Claim>();
                claims.Add(new Claim(ClaimTypes.NameIdentifier, ProviderKey, null, LoginProvider));

                if (UserName != null)
                {
                    claims.Add(new Claim(ClaimTypes.Name, UserName, null, LoginProvider));
                }

                return claims;
            }

            public static ExternalLoginData FromIdentity(ClaimsIdentity identity)
            {
                if (identity == null)
                {
                    return null;
                }

                Claim providerKeyClaim = identity.FindFirst(ClaimTypes.NameIdentifier);

                if (providerKeyClaim == null || String.IsNullOrEmpty(providerKeyClaim.Issuer)
                    || String.IsNullOrEmpty(providerKeyClaim.Value))
                {
                    return null;
                }

                if (providerKeyClaim.Issuer == ClaimsIdentity.DefaultIssuer)
                {
                    return null;
                }

                return new ExternalLoginData
                {
                    LoginProvider = providerKeyClaim.Issuer,
                    ProviderKey = providerKeyClaim.Value,
                    UserName = identity.FindFirstValue(ClaimTypes.Name)
                };
            }
        }

        private static class RandomOAuthStateGenerator
        {
            private static RandomNumberGenerator _random = new RNGCryptoServiceProvider();

            public static string Generate(int strengthInBits)
            {
                const int bitsPerByte = 8;

                if (strengthInBits % bitsPerByte != 0)
                {
                    throw new ArgumentException("strengthInBits must be evenly divisible by 8.", "strengthInBits");
                }

                int strengthInBytes = strengthInBits / bitsPerByte;

                byte[] data = new byte[strengthInBytes];
                _random.GetBytes(data);
                return HttpServerUtility.UrlTokenEncode(data);
            }
        }

        #endregion
    }
}
