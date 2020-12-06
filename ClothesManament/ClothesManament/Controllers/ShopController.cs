using ClothesManament.Models;
using ClothesManamentDataAccess;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;
using System.Web.Http;

namespace ClothesManagement.Controllers
{
    public class ShopController :ApiController
    {
        ClothesEntities entities;

        public ShopController()
        {
            entities = new ClothesEntities();
        }

        [System.Web.Http.Route("api/getShopInfo")]
        [System.Web.Http.AcceptVerbs("GET")]
        [System.Web.Http.HttpGet]
        public async Task<ResponseObjectModel<SP_GetShopInfo_Result>> getShopInfo()
        {
            var shopInfo = await Task.Run(() => entities.SP_GetShopInfo().First());
            return new ResponseObjectModel<SP_GetShopInfo_Result>()
            {
                message = "lay hong tin chi tiet shop thanh cong",
                status = true,
                code = 200,
                data = shopInfo
            };
        }

    }
}