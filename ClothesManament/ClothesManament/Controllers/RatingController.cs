using ClothesManagement.Models;
using ClothesManament.Models;
using ClothesManamentDataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace ClothesManagement.Controllers
{
    public class RatingController : ApiController
    {
        ClothesEntities entities;
        public RatingController()
        {
            entities = new ClothesEntities();
        }

        [Route("api/getRating")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ListResponse<CommentsObj>> getRating(Nullable<int> pageSize = 20, Nullable<int> pageNumber = 1, int? productId = null)
        {
            if (pageSize <= 0 || pageSize > 100) pageSize = 20;
            if (pageNumber <= 0) pageNumber = 1;

            var listResponse = (await Task.Run(() => entities.SP_GetRatings(pageNumber, pageSize, productId).ToList()));
            var count = (await Task.Run(() => entities.SP_GetRatingsCount(productId).FirstOrDefault()));
            List<CommentsObj> listComments = new List<CommentsObj>();
            for (int i = 0; i < listResponse.Count; i++)
            {
                var subComment = (await Task.Run(() => entities.SP_GetSubRating(listResponse[i].ratingID).ToList()));
                listComments.Add(new CommentsObj()
                {
                    ratingID = listResponse[i].ratingID,
                    accountID = listResponse[i].accountID,
                    username = listResponse[i].username,
                    rating = listResponse[i].rating,
                    comment = listResponse[i].comment,
                    dateRating = listResponse[i].dateRating,
                    dateEdit = listResponse[i].dateEdit,
                    productID = listResponse[i].productID,
                    sizeName = listResponse[i].sizeName,
                    colorName = listResponse[i].colorName,
                    imageUrl1 = listResponse[i].imageUrl1,
                    imageUrl2 = listResponse[i].imageUrl2,
                    imageUrl3 = listResponse[i].imageUrl3,
                    videoUrl = listResponse[i].videoUrl,
                    subComments = subComment
                });
            }
            return new ListResponse<CommentsObj>()
            {
                count = count,
                results = listComments
            };
        }


        [Route("api/getRatingProduct")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseObjectModel<RatingResult>> getRatingProduct(int? productId = null)
        {
            var rating = (await Task.Run(() => entities.SP_GetRatingProduct(productId).FirstOrDefault()));
            var count = (await Task.Run(() => entities.SP_GetRatingsCount(productId).FirstOrDefault()));

            return new ResponseObjectModel<RatingResult>()
            {
                message = "Lấy số đánh giá thành công",
                status = true,
                code = 200,
                data = new RatingResult()
                {
                    rating = rating,
                    ratingCount = count
                }
            };
        }


        [Route("api/getRatingDetail")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseObjectModel<SP_GetRatingDetail_Result>> getRatingDetail(int? ratingId = null)
        {
            var rating = (await Task.Run(() => entities.SP_GetRatingDetail(ratingId).FirstOrDefault()));

            return new ResponseObjectModel<SP_GetRatingDetail_Result>()
            {
                message = "Lấy số đánh giá thành công",
                status = true,
                code = 200,
                data = rating
            };
        }

        [Route("api/checkRatingProduct")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseObjectModel<int>> checkRatingProduct(int? productId = null, int? accId = null, int? colorId = null, int? sizeId = null, int? orderId = null)
        {
            var check = (await Task.Run(() => entities.SP_CheckRating(productId, accId, colorId, sizeId, orderId).FirstOrDefault()));
            return new ResponseObjectModel<int>()
            {
                message = "Lấy số đánh giá thành công",
                status = true,
                code = 200,
                data =int.Parse( check.ToString())
            };
        }


        [Route("api/addRating")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ResponseObjectModel<int>> addRating(RatingParam ratingObj)
        {
            var reponse = (await Task.Run(() => entities.SP_AddRating(
                ratingObj.accountID,
                ratingObj.rating,
                ratingObj.comment,
                ratingObj.productID,
                ratingObj.orderId,
               ratingObj.colorId,
               ratingObj.sizeId,
                ratingObj.imageUrl1,
                ratingObj.imageUrl2,
                ratingObj.imageUrl3,
                ratingObj.videoUrl,
                ratingObj.parentId)));
            if (reponse > 0)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Gửi đánh giá thành công",
                    status = true,
                    code = 200,
                    data = reponse
                };
            }
            else
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Thêm không thành công",
                    status = false,
                    code = 200,
                    data = -1
                };
            }
        }

        [Route("api/updateRating")]
        [AcceptVerbs("PUT")]
        [HttpPut]
        public async Task<ResponseObjectModel<int>> updateRating(SP_GetRatings_Result5 ratingObj)
        {
            var reponse = (await Task.Run(() => entities.SP_UpdateRating(
                ratingObj.ratingID,
                ratingObj.rating,
                ratingObj.comment,
                ratingObj.imageUrl1,
                ratingObj.imageUrl2,
                ratingObj.imageUrl3,
                ratingObj.videoUrl,
                ratingObj.parentId)));
            if (reponse > 0)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Cập nhật đánh giá thành công",
                    status = true,
                    code = 200,
                    data = reponse
                };
            }
            else
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Cập nhật không thành công",
                    status = false,
                    code = 200,
                    data = -1
                };
            }
        }

        [Route("api/delRating")]
        [AcceptVerbs("DELETE")]
        [HttpDelete]
        public async Task<ResponseObjectModel<int>> delRating(int ratingID)
        {
            var reponse = (await Task.Run(() => entities.SP_DelRating(ratingID)));
            //if (object.Equals(reponse, 0))
            //{
            //    return new ResponseObjectModel<int>()
            //    {
            //        message = "Xóa đánh giá thành công",
            //        status = true,
            //        code = 200,
            //        data = 0
            //    };
            //}
            //else
            //{
            return new ResponseObjectModel<int>()
            {
                message = "Xóa đánh giá không thành công",
                status = false,
                code = 200,
                data = -1
            };
            //}
        }
    }


}