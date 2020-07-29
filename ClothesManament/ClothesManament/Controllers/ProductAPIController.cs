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
        ClothesEntities entities;

        public ProductAPIController()
        {
            entities = new ClothesEntities();
        }

        [Route("api/allProductsOfCategory")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ListResponse<SP_GetProductOfCategory_Result1>> getProductListPaging(int pageSize, int pageNumber, int categoryID, int? accountId = null)
        {
            if (pageSize <= 0 || pageSize > 100) pageSize = 20;
            if (pageNumber <= 0) pageNumber = 1;
            var listResponse = (await Task.Run(() => entities.SP_GetProductOfCategory(categoryID, pageNumber, pageSize, accountId).ToList()));
            var count = (await Task.Run(() => entities.SP_GetProductOfCategoryCount(categoryID).FirstOrDefault()));
            return new ListResponse<SP_GetProductOfCategory_Result1>() {
                count = count,
                results = listResponse
            };
        }

        [Route("api/allProductsOfProvider")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ListResponse<SP_GetProductsOfProvider_Result1>> getProductProviderListPaging(int? pageSize = null, int? pageNumber = null, int? providerId = null, int? accountId = null)
        {
            if (pageSize <= 0 || pageSize > 100 || pageSize == null) pageSize = 20;
            if (pageNumber <= 0 || pageNumber == null) pageNumber = 1;
            if (providerId == null) providerId = 1;

            var listResponse = (await Task.Run(() => entities.SP_GetProductsOfProvider(providerId, pageNumber, pageSize, accountId).ToList()));
            var count = (await Task.Run(() => entities.SP_GetProductsOfProviderCount(providerId).FirstOrDefault()));
            return new ListResponse<SP_GetProductsOfProvider_Result1>()
            {
                count = count,
                results = listResponse
            };
        }

        [Route("api/favoriteProducts")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseListModel<SP_GetFavoriteProducts_Result1>> getPagingFavoriteProduct(int? pageSize = null, int? pageNumber = null, int? accountId = null)
        {
            if (pageSize <= 0 || pageSize > 100 || pageSize == null) pageSize = 20;
            if (pageNumber <= 0 || pageNumber == null) pageNumber = 1;

            if (accountId == null)
            {
                return new ResponseListModel<SP_GetFavoriteProducts_Result1>()
                {
                    message = "Vui lòng đăng nhập để sử dụng tính năng này!",
                    status = false,
                    code = 200,
                    data = null
                };
            }
            else
            {
                var products = await Task.Run(() => entities.SP_GetFavoriteProducts(pageNumber, pageSize, accountId).ToList());
                return new ResponseListModel<SP_GetFavoriteProducts_Result1>()
                {
                    message = "Danh sách sản phẩm yêu thích",
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
        public async Task<ResponseObjectModel<Models.ProductDetail>> getProductInfo([FromUri]int productID, Nullable<int> accountId = null)
        {
            var productDetail = entities.SP_GetProductInfoDetail(productID, accountId).FirstOrDefault();
            if (productDetail == null)
            {
                return new ResponseObjectModel<Models.ProductDetail>()
                {
                    message = "Product info",
                    status = false,
                    code = 200,
                    data = null
                };
            }
            var listImage = await Task.Run(() => entities.SP_ImagesOfProduct(productID).ToList());
            var providerDetail = await Task.Run(() => entities.SP_GetProviderDetail(productDetail.providerId).FirstOrDefault());
            var colors = await Task.Run(() => entities.SP_GetColorsOfProduct(productDetail.providerId).ToList());
            var sizes = await Task.Run(() => entities.SP_GetSizesOfProduct(productDetail.providerId).ToList());
            var colorsSizes = await Task.Run(() => entities.SP_GetSizesColorsOfProduct(productDetail.providerId).ToList());

            return new ResponseObjectModel<Models.ProductDetail>()
            {
                message = "Product info",
                status = true,
                code = 200,
                data = new Models.ProductDetail()
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
                    valuePromotion = productDetail.promotion,
                    typePromotion = productDetail.typePromotion,
                    provider = providerDetail,
                    images = listImage,
                    colors = colors,
                    sizes = sizes,
                    sizesColors = colorsSizes
                }
            };
        }


    }
}
