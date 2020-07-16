using System.Web.Http;
using WebActivatorEx;
using ClothesManagement;
using Swashbuckle.Application;

[assembly: PreApplicationStartMethod(typeof(SwaggerConfig), "Register")]

namespace ClothesManagement
{
    public class SwaggerConfig
    {
        public static void Register()
        {
            var thisAssembly = typeof(SwaggerConfig).Assembly;

            GlobalConfiguration.Configuration
                                .EnableSwagger(c => c.SingleApiVersion("v1", "Clothes Management Web Api"))
                                .EnableSwaggerUi();
        }
    }
}
