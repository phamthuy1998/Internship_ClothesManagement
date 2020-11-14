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
    public class QuestionController : ApiController
    {
        ClothesEntities entities;
        public QuestionController()
        {
            entities = new ClothesEntities();
        }

        [Route("api/getQuestions")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ListResponse<QuestionResponse>> getQuestions(Nullable<int> pageSize = 20, Nullable<int> pageNumber = 1, int? productId = null)
        {
            if (pageSize <= 0 || pageSize > 100) pageSize = 20;
            if (pageNumber <= 0) pageNumber = 1;

            var listResponse = (await Task.Run(() => entities.SP_GetQuestion(pageNumber, pageSize, productId).ToList()));
            var count = (await Task.Run(() => entities.SP_GetQuestionCount(productId).FirstOrDefault()));
            List<QuestionResponse> listComments = new List<QuestionResponse>();
            for (int i = 0; i < listResponse.Count; i++)
            {
                var subQuestions = (await Task.Run(() => entities.SP_GetSubQuestion(listResponse[i].questionID).ToList()));
                listComments.Add(new QuestionResponse()
                {
                    questionID = listResponse[i].questionID,
                    accountID = listResponse[i].accountID,
                    roleId = listResponse[i].roleId,
                    username = listResponse[i].username,
                    question = listResponse[i].question,
                    dateComment = listResponse[i].dateComment,
                    dateEdit = listResponse[i].dateEdit,
                    parentQuestionID = listResponse[i].parentQuestionID,
                    subQuestions = subQuestions
                });
            }
            return new ListResponse<QuestionResponse>()
            {
                count = count,
                results = listComments
            };
        }

        [Route("api/getQuestionsCount")]
        [AcceptVerbs("GET")]
        [HttpGet]
        public async Task<ResponseObjectModel<int>> getQuestionsCount( int? productId = null)
        {
            var count = (await Task.Run(() => entities.SP_GetQuestionCount(productId).FirstOrDefault()));
            return new ResponseObjectModel<int>()
            {
                message = "Lấy số câu hỏi thành công",
                status = true,
                code = 200,
                data =int.Parse (count.ToString())
            };
        }


        [Route("api/addQuestion")]
        [AcceptVerbs("POST")]
        [HttpPost]
        public async Task<ResponseObjectModel<int>> addQuestion(QuestionParam questionParam)
        {
            var reponse = (await Task.Run(() => entities.SP_AddQuestion(
               questionParam.accountID,
               questionParam.question,
               questionParam.parentQuestionID,
               questionParam.productID)));
            if (reponse > 0)
            {
                return new ResponseObjectModel<int>()
                {
                    message = "Gửi câu hỏi thành công",
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
        [Route("api/updateQuestion")]
        [AcceptVerbs("PUT")]
        [HttpPut]
        public async Task<ResponseObjectModel<int>> updateQuestion(QuestionParam questionParam)
        {
            var reponse = (await Task.Run(() => entities.SP_UpdateQuestion(
                questionParam.questionID,
                 questionParam.question
                )));
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

        [Route("api/delQuestion")]
        [AcceptVerbs("DELETE")]
        [HttpDelete]
        public async Task<ResponseObjectModel<int>> delQuestion(int questionID, int isSubQuestion)
        {
            var reponse = (await Task.Run(() => entities.SP_DelQuestion(questionID, isSubQuestion)));
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