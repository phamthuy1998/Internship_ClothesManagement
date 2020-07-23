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
    public class ProductAPIController : ApiController
    {
        ClothesManamentEntities entities;

        public ProductAPIController()
        {
            entities = new ClothesManamentEntities();
        }

        [Route("api/allProductsOfCategory")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<IHttpActionResult> getProductListPaging(int pageSize, int pageNumber, int categoryID, int? accountId = null)
        {
            if (pageSize <= 0 || pageSize > 100) pageSize = 20;
            if (pageNumber <= 0) pageNumber = 1;
            return Ok(await Task.Run(() => entities.SP_GetProductOfCategory(categoryID, pageNumber, pageSize, accountId)));
        }

        [Route("api/allProductsOfProvider")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<IHttpActionResult> getProductProviderListPaging(int? pageSize = null, int? pageNumber = null, int? providerId = null, int? accountId = null)
        {
            if (pageSize <= 0 || pageSize > 100 || pageSize == null) pageSize = 20;
            if (pageNumber <= 0 || pageNumber == null) pageNumber = 1;
            if (providerId == null) providerId = 1;
            return Ok(await Task.Run(() => entities.SP_GetProductsOfProvider(providerId, pageNumber, pageSize, accountId)));
        }

        [Route("api/favoriteProducts")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseListModel<SP_GetProductOfCategory_Result>> getPagingFavoriteProduct(int? pageSize = null, int? pageNumber = null, int? accountId = null)
        {
            if (pageSize <= 0 || pageSize > 100 || pageSize == null) pageSize = 20;
            if (pageNumber <= 0 || pageNumber == null) pageNumber = 1;

            if (accountId == null)
            {
                return new ResponseListModel<SP_GetProductOfCategory_Result>()
                {
                    message = "Danh sách sản phẩm yêu thích!",
                    status = true,
                    code = 200,
                    data = null
                };
            }
            else
            {
                var products = await Task.Run(() => entities.SP_GetFavoriteProducts(pageNumber, pageSize, accountId).ToList());
                return new ResponseListModel<SP_GetProductOfCategory_Result>()
                {
                    message = "Danh sách sản phẩm yêu thích!",
                    status = true,
                    code = 200,
                    data = products
                };
            }
        }

        [Route("api/checkFavoriteProduct")]
        [AcceptVerbs("PUT")]
        [HttpPut]
        public ResponseObjectModel<int> checkFavoriteProduct(int productId, int? accountId = null)
        {
            if (accountId == null)
                return new ResponseObjectModel<int>()
                {
                    message = "Vui lòng đăng nhập để thêm sản phẩm vào danh sách sản phẩm yêu thích!",
                    status = false,
                    code = 200,
                    data = -1
                };
            var result = entities.SP_CheckFavoriteProduct(accountId, productId).FirstOrDefault();
            if (result == 1)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Đã thêm sản phẩm vào danh sách sản phẩm yêu thích!",
                    status = true,
                    code = 200,
                    data = 1
                };
            }
            else if (result == 0)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Đã xóa sản phẩm khỏi danh sách sản phẩm yêu thích!",
                    status = true,
                    code = 200,
                    data = 0
                };
            }
            else // (result == -2)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Sản phẩm không tồn tại trong danh sách!",
                    status = false,
                    code = 200,
                    data = -2
                };
            }
        }

        [Route("api/productDetail")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseObjectModel<ProductDetail>> getProductInfo([FromUri]int productID, int? accountId = null)
        {
            //return entities.Products.FirstOrDefault(x => x.id == productID);
            var result = entities.Products.FirstOrDefault(x => x.id == productID);

            if (result == null)
            {
                return new ResponseObjectModel<ProductDetail>()
                {
                    message = "Product not found.",
                    status = false,
                    code = 404
                };
            }

            var productDetail = await Task.Run(() => entities.SP_GetProductDetail(productID, accountId).FirstOrDefault());
            var listImage = await Task.Run(() => entities.SP_ImagesOfProduct(productID).ToList());
            var providerDetail = await Task.Run(() => entities.SP_GetProviderDetail(productDetail.providerId).FirstOrDefault());

            return new ResponseObjectModel<ProductDetail>()
            {
                message = "Product info",
                status = true,
                code = 200,
                data = new ProductDetail()
                {
                    id = productDetail.id,
                    title = productDetail.title,
                    detail = productDetail.detail,
                    price = productDetail.price,
                    categoryID = productDetail.categoryID,
                    sold = productDetail.sold,
                    rating = productDetail.rating,
                    active = productDetail.active,
                    providerId = productDetail.providerId,
                    thumnail = productDetail.thumnail,
                    isNew = productDetail.isNew,
                    addDate = productDetail.addDate,
                    isLike = productDetail.isLike,
                    provider = providerDetail,
                    images = listImage

                }
            };
        }


    }
}
