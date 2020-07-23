using ClothesManament.Models;
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
    public class CategoryApiController : ApiController
    {

        private ClothesManamentEntities entities;
        public CategoryApiController()
        {
            entities = new ClothesManamentEntities();
        }

        // GET api/Account/ManageInfo?returnUrl=%2F&generateState=true
        [Route("api/categories")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<IHttpActionResult> getAllCategories(int genderID)
        {
            return Ok(await Task.Run(() => entities.getCategoryGender(genderID)));
        }

    }
}
