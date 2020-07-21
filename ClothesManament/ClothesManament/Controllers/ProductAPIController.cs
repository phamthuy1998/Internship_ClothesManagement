using ClothesManamentDataAccess;
using System;
using System.Linq;
using System.Web.Http;
using ClothesManament.Models;
using System.Web;
using Newtonsoft.Json;
using System.Threading.Tasks;

namespace ClothesManament.Controllers
{
    [Authorize]
    public class ProductAPIController : ApiController
    {
        ClothesManamentEntities entities;

        public ProductAPIController()
        {
            entities = new ClothesManamentEntities();
        }

        [Route("api/products")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<IHttpActionResult> getProductListPaging(int pageSize, int pageNumber, int categoryID, int accountId)
        {
            if (pageSize <= 0 || pageSize > 100) pageSize = 20;
            if (pageNumber <= 0) pageNumber = 1;
            return Ok(await Task.Run(() => entities.SP_GetProductOfCategory(categoryID, pageNumber, pageSize, accountId)));
        }

        //[Route("api/allProducts")]
        //[AcceptVerbs("GET")]
        //[HttpGet]
        //public IHttpActionResult getAllProduct(int page)
        //{
        //    var result = entities.SP_GetProducts(page);

        //    if (result == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(result);
        //}

        [Route("api/getProductById")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public ResponseObjectModel<Product> getProductInfo([FromUri]int productID)
        {
            //return entities.Products.FirstOrDefault(x => x.id == productID);
            var result = entities.Products.FirstOrDefault(x => x.id == productID);

            if (result == null)
            {
                return new ResponseObjectModel<Product>()
                {
                    message = "Product not found.",
                    status = false,
                    code = 404
                };
            }

            return new ResponseObjectModel<Product>()
            {
                message = "Product info",
                status = true,
                code = 200,
                data = result
            };
        }

        //[Route("api/getProductDetail")]
        //[AcceptVerbs("GET")]
        //[HttpGet]
        //public HttpResponseMessage getProductDetail(int productID)
        //{
        //    var result = entities.Products.FirstOrDefault(x => x.id == productID);
        //    if (result == null)
        //    {
        //        return Request.CreateResponse(HttpStatusCode.NotFound, productID);
        //    }

        //    return Request.CreateResponse(HttpStatusCode.OK, result);
        //}

        //[Route("api/getProductById")]
        //[AcceptVerbs("GET")]
        //[HttpGet]
        //public IHttpActionResult getroductByID(int productID)
        //{
        //    //return entities.Products.FirstOrDefault(x => x.id == productID);
        //    var result = entities.Products.FirstOrDefault(x => x.id == productID);

        //    if (result == null)
        //    {
        //        return new ResultApi(null, Request);
        //    }

        //    return new ResultApi(result, Request);
        //}
    }
}
