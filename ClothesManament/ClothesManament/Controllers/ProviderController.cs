using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace ClothesManagement.Controllers
{
    public class ProviderController : ApiController
    {

        ClothesEntities entities;

        public ProviderController()
        {
            entities = new ClothesEntities();
        }

        [Route("api/providers")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<IHttpActionResult> getProviders()
        {
            return Ok(await Task.Run(() => entities.SP_GetProviders()));
        }

        [Route("api/providerDetail")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<IHttpActionResult> getProviderDetail(int providerId)
        {
            return Ok(await Task.Run(() => entities.SP_GetProviderDetail(providerId).FirstOrDefault()));
        }
    }
}
