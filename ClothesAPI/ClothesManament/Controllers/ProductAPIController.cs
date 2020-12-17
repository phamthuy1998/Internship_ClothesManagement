using ClothesManamentDataAccess;
using System;
using System.Linq;
using System.Web.Http;
using ClothesManament.Models;
using System.Web;
using Newtonsoft.Json;
using System.Threading.Tasks;
using System.Collections.Generic;
using ClothesManagement.Models;

namespace ClothesManament.Controllers
{
    public class ProductAPIController : ApiController
    {
        ClothesEntities entities;

        public ProductAPIController()
        {
            entities = new ClothesEntities();
        }


        [Route("api/get-all-product-cart")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public List<Models.ProductDetail> getListProductCart(List<int> idProducts)
        {
            List<Models.ProductDetail> listProduct = new List<Models.ProductDetail>();
            for (int i = 0; i < idProducts.Count; i++)
            {
                var product = getProductDetail(idProducts[i]);
                if (product != null)
                    listProduct.Add(product);
            }
            return listProduct;
        }

        public Models.ProductDetail getProductDetail(int productID)
        {
            var productDetail = entities.SP_GetProductInfoDetail(productID, null).FirstOrDefault();

            var listImage = entities.SP_ImagesOfProduct(productID).ToList();
            var providerDetail = entities.SP_GetProviderDetail(productDetail.providerId).FirstOrDefault();
            var colors = entities.SP_GetColorsOfProduct(productID).ToList();
            var sizes = entities.SP_GetSizesOfProduct(productID).ToList();
            var colorsSizes = entities.SP_GetSizesColorsOfProduct(productID).ToList();

            return new Models.ProductDetail()
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
            };
        }

        [Route("api/allProductsOfCategory")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ListResponse<SP_GetProductOfCategory_Result1>> getProductListPaging(ProductApiCategory productParam)
        {
            if (productParam == null)
            {
                return new ListResponse<SP_GetProductOfCategory_Result1>()
                {
                    count = 0,
                    results = null
                };
            }
            if (productParam?.pageSize <= 0 || productParam?.pageSize > 100) productParam.pageSize = 20;
            if (productParam?.pageNumber <= 0) productParam.pageNumber = 1;
            var listResponse = (await Task.Run(() => entities.SP_GetProductOfCategoryFilter(
                productParam.categoryID,
                productParam.pageNumber,
                productParam.pageSize,
                productParam.accountId,
                productParam.filter.sortBy).ToList()
              ));
            var count = (await Task.Run(() => entities.SP_GetProductOfCategoryCount(productParam.categoryID).FirstOrDefault()));
            return new ListResponse<SP_GetProductOfCategory_Result1>()
            {
                count = count,
                results = listResponse
            };
        }

        [Route("api/allProductsOfProvider")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ListResponse<SP_GetProductOfCategory_Result1>> getProductProviderListPaging(ProductApi productParam)
        {
            if (productParam == null)
            {
                return new ListResponse<SP_GetProductOfCategory_Result1>()
                {
                    count = 0,
                    results = null
                };
            }
            if (productParam?.pageSize <= 0 || productParam?.pageSize > 100) productParam.pageSize = 20;
            if (productParam?.pageNumber <= 0) productParam.pageNumber = 1;

            var listResponse = (await Task.Run(() => entities.SP_GetProductsOfProviderFilter(
                productParam.providerID,
                productParam.pageNumber,
                productParam.pageSize,
                productParam.accountId,
                productParam.filter.sortBy).ToList()));
            var count = (await Task.Run(() => entities.SP_GetProductsOfProviderCount(productParam.providerID).FirstOrDefault()));
            return new ListResponse<SP_GetProductOfCategory_Result1>()
            {
                count = count,
                results = listResponse
            };
        }

        [Route("api/products-search")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ListResponse<SP_GetProductOfCategory_Result1>> getProductSearchFilter(ProductApiSearch searchFilter)
        {
            if (searchFilter == null)
            {
                return new ListResponse<SP_GetProductOfCategory_Result1>()
                {
                    count = 0,
                    results = null
                };
            }
            if (searchFilter?.pageSize <= 0 || searchFilter?.pageSize > 100) searchFilter.pageSize = 20;
            if (searchFilter?.pageNumber <= 0) searchFilter.pageNumber = 1;
            if (searchFilter.keySearch != null) searchFilter.keySearch = "%" + searchFilter.keySearch + "%";
            var listResponse = (await Task.Run(() => entities.SP_SearchFilter(
              searchFilter.keySearch,
              searchFilter.pageNumber,
              searchFilter.pageSize,
              searchFilter.accountId,
              searchFilter.filter?.sortBy).ToList()));
            var counter = (await Task.Run(() => entities.SP_SearchFilterCounter(searchFilter.keySearch).FirstOrDefault()));
            if (counter == null||counter == -1) counter = 0;
            return new ListResponse<SP_GetProductOfCategory_Result1>()
            {
                count = counter,
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
                    message = "Lỗi lấy thông tin sản phẩm",
                    status = false,
                    code = 200,
                    data = null
                };
            }
            var listImage = await Task.Run(() => entities.SP_ImagesOfProduct(productID).ToList());
            var providerDetail = await Task.Run(() => entities.SP_GetProviderDetail(productDetail.providerId).FirstOrDefault());
            var colors = await Task.Run(() => entities.SP_GetColorsOfProduct(productID).ToList());
            var sizes = await Task.Run(() => entities.SP_GetSizesOfProduct(productID).ToList());
            var colorsSizes = await Task.Run(() => entities.SP_GetSizesColorsOfProduct(productID).ToList());

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
