using ClothesManamentDataAccess;
using System;
using System.Linq;
using System.Web.Http;
using ClothesManament.Models;
using System.Web;
using Newtonsoft.Json;

namespace ClothesManament.Controllers
{
    public class ProductAPIController : ApiController
    {
        ClothesManamentEntities entities = new ClothesManamentEntities();


        ClothesManamentEntities _context;
        public ProductAPIController()
        {
            _context = new ClothesManamentEntities();
        }

        [Route("api/product")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public ResponseListModel<Product> GetCustomer([FromUri]PagingParameterModel pagingparametermodel)
        {
            // Return List of product  
            var source = (from product in _context.Products.
                            OrderBy(a => a.id)
                          select product).AsQueryable();

            // Get's No of Rows Count   
            int count = source.Count();

            // Parameter is passed from Query string if it is null then it default Value will be pageNumber:1  
            int CurrentPage = pagingparametermodel.pageNumber;

            // Parameter is passed from Query string if it is null then it default Value will be pageSize:20  
            int PageSize = pagingparametermodel.pageSize;

            // Display TotalCount to Records to User  
            int TotalCount = count;

            // Calculating Totalpage by Dividing (No of Records / Pagesize)  
            int TotalPages = (int)Math.Ceiling(count / (double)PageSize);

            // Returns List of Customer after applying Paging   
            var items = source.Skip((CurrentPage - 1) * PageSize).Take(PageSize).ToList();

            // if CurrentPage is greater than 1 means it has previousPage  
            var previousPage = CurrentPage > 1 ? "Yes" : "No";

            // if TotalPages is greater than CurrentPage means it has nextPage  
            var nextPage = CurrentPage < TotalPages ? "Yes" : "No";

            // Object which we are going to send in header   
            var paginationMetadata = new
            {
                totalCount = TotalCount,
                pageSize = PageSize,
                currentPage = CurrentPage,
                totalPages = TotalPages,
                previousPage,
                nextPage
            };

            var metadataValue = new Metadata()
            {
                totalCount = TotalCount,
                pageSize = PageSize,
                currentPage = CurrentPage,
                totalPages = TotalPages,
                previousPage= previousPage,
                nextPage= nextPage
            };

            // Setting Header  
            HttpContext.Current.Response.Headers.Add("Paging-Headers", JsonConvert.SerializeObject(paginationMetadata));

            // Returing List of Customers Collections  
            return new ResponseListModel<Product>()
            {
                message = "Product info",
                status = true,
                code = 200,
                data = items,
                metadata= metadataValue
            };
        }
        
        [Route("api/getAllProduct")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public IHttpActionResult getAllProduct(int page)
        {
            var result = entities.SP_GetProducts(page);

            if (result == null)
            {
                return NotFound();
            }

            return Ok(result);
        }

        [Route("api/getProduct")]
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
