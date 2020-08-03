﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ClothesManamentDataAccess
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    using System.Data.Entity.Core.Objects;
    using System.Linq;
    
    public partial class ClothesEntities : DbContext
    {
        public ClothesEntities()
            : base("name=ClothesEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<Account> Accounts { get; set; }
        public virtual DbSet<Address> Addresses { get; set; }
        public virtual DbSet<Category> Categories { get; set; }
        public virtual DbSet<Color> Colors { get; set; }
        public virtual DbSet<Customer> Customers { get; set; }
        public virtual DbSet<Employee> Employees { get; set; }
        public virtual DbSet<Image> Images { get; set; }
        public virtual DbSet<ImportCoupon> ImportCoupons { get; set; }
        public virtual DbSet<ImportCouponDetail> ImportCouponDetails { get; set; }
        public virtual DbSet<Invoice> Invoices { get; set; }
        public virtual DbSet<InvoiceItem> InvoiceItems { get; set; }
        public virtual DbSet<Product> Products { get; set; }
        public virtual DbSet<ProductSizeColor> ProductSizeColors { get; set; }
        public virtual DbSet<Promotion> Promotions { get; set; }
        public virtual DbSet<Provider> Providers { get; set; }
        public virtual DbSet<Size> Sizes { get; set; }
        public virtual DbSet<sysdiagram> sysdiagrams { get; set; }
    
        public virtual ObjectResult<Nullable<int>> ChangePassword(Nullable<int> userId, string oldPass, string newPass)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            var oldPassParameter = oldPass != null ?
                new ObjectParameter("oldPass", oldPass) :
                new ObjectParameter("oldPass", typeof(string));
    
            var newPassParameter = newPass != null ?
                new ObjectParameter("newPass", newPass) :
                new ObjectParameter("newPass", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("ChangePassword", userIdParameter, oldPassParameter, newPassParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> DelAccount(Nullable<int> userId)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("DelAccount", userIdParameter);
        }
    
        public virtual ObjectResult<getCategoryByGender_Result1> getCategoryByGender(Nullable<int> genderID)
        {
            var genderIDParameter = genderID.HasValue ?
                new ObjectParameter("GenderID", genderID) :
                new ObjectParameter("GenderID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<getCategoryByGender_Result1>("getCategoryByGender", genderIDParameter);
        }
    
        public virtual ObjectResult<getCategoryGender_Result1> getCategoryGender(Nullable<int> genderID)
        {
            var genderIDParameter = genderID.HasValue ?
                new ObjectParameter("GenderID", genderID) :
                new ObjectParameter("GenderID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<getCategoryGender_Result1>("getCategoryGender", genderIDParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_AddOrder(Nullable<int> userId, string address, string phone, string name, string note)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            var addressParameter = address != null ?
                new ObjectParameter("address", address) :
                new ObjectParameter("address", typeof(string));
    
            var phoneParameter = phone != null ?
                new ObjectParameter("phone", phone) :
                new ObjectParameter("phone", typeof(string));
    
            var nameParameter = name != null ?
                new ObjectParameter("name", name) :
                new ObjectParameter("name", typeof(string));
    
            var noteParameter = note != null ?
                new ObjectParameter("note", note) :
                new ObjectParameter("note", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_AddOrder", userIdParameter, addressParameter, phoneParameter, nameParameter, noteParameter);
        }
    
        public virtual int SP_AddOrderItem(Nullable<int> invoiceId, Nullable<int> productId, Nullable<int> colorId, Nullable<int> sizeId, Nullable<int> quantity)
        {
            var invoiceIdParameter = invoiceId.HasValue ?
                new ObjectParameter("invoiceId", invoiceId) :
                new ObjectParameter("invoiceId", typeof(int));
    
            var productIdParameter = productId.HasValue ?
                new ObjectParameter("productId", productId) :
                new ObjectParameter("productId", typeof(int));
    
            var colorIdParameter = colorId.HasValue ?
                new ObjectParameter("colorId", colorId) :
                new ObjectParameter("colorId", typeof(int));
    
            var sizeIdParameter = sizeId.HasValue ?
                new ObjectParameter("sizeId", sizeId) :
                new ObjectParameter("sizeId", typeof(int));
    
            var quantityParameter = quantity.HasValue ?
                new ObjectParameter("quantity", quantity) :
                new ObjectParameter("quantity", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("SP_AddOrderItem", invoiceIdParameter, productIdParameter, colorIdParameter, sizeIdParameter, quantityParameter);
        }
    
        public virtual int sp_alterdiagram(string diagramname, Nullable<int> owner_id, Nullable<int> version, byte[] definition)
        {
            var diagramnameParameter = diagramname != null ?
                new ObjectParameter("diagramname", diagramname) :
                new ObjectParameter("diagramname", typeof(string));
    
            var owner_idParameter = owner_id.HasValue ?
                new ObjectParameter("owner_id", owner_id) :
                new ObjectParameter("owner_id", typeof(int));
    
            var versionParameter = version.HasValue ?
                new ObjectParameter("version", version) :
                new ObjectParameter("version", typeof(int));
    
            var definitionParameter = definition != null ?
                new ObjectParameter("definition", definition) :
                new ObjectParameter("definition", typeof(byte[]));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("sp_alterdiagram", diagramnameParameter, owner_idParameter, versionParameter, definitionParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_CheckFavoriteProduct(Nullable<int> accountId, Nullable<int> productId)
        {
            var accountIdParameter = accountId.HasValue ?
                new ObjectParameter("accountId", accountId) :
                new ObjectParameter("accountId", typeof(int));
    
            var productIdParameter = productId.HasValue ?
                new ObjectParameter("productId", productId) :
                new ObjectParameter("productId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_CheckFavoriteProduct", accountIdParameter, productIdParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_CheckUserExist(Nullable<int> userID)
        {
            var userIDParameter = userID.HasValue ?
                new ObjectParameter("userID", userID) :
                new ObjectParameter("userID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_CheckUserExist", userIDParameter);
        }
    
        public virtual int sp_creatediagram(string diagramname, Nullable<int> owner_id, Nullable<int> version, byte[] definition)
        {
            var diagramnameParameter = diagramname != null ?
                new ObjectParameter("diagramname", diagramname) :
                new ObjectParameter("diagramname", typeof(string));
    
            var owner_idParameter = owner_id.HasValue ?
                new ObjectParameter("owner_id", owner_id) :
                new ObjectParameter("owner_id", typeof(int));
    
            var versionParameter = version.HasValue ?
                new ObjectParameter("version", version) :
                new ObjectParameter("version", typeof(int));
    
            var definitionParameter = definition != null ?
                new ObjectParameter("definition", definition) :
                new ObjectParameter("definition", typeof(byte[]));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("sp_creatediagram", diagramnameParameter, owner_idParameter, versionParameter, definitionParameter);
        }
    
        public virtual int sp_dropdiagram(string diagramname, Nullable<int> owner_id)
        {
            var diagramnameParameter = diagramname != null ?
                new ObjectParameter("diagramname", diagramname) :
                new ObjectParameter("diagramname", typeof(string));
    
            var owner_idParameter = owner_id.HasValue ?
                new ObjectParameter("owner_id", owner_id) :
                new ObjectParameter("owner_id", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("sp_dropdiagram", diagramnameParameter, owner_idParameter);
        }
    
        public virtual ObjectResult<SP_GetAllAddress_Result1> SP_GetAllAddress(Nullable<int> userId)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetAllAddress_Result1>("SP_GetAllAddress", userIdParameter);
        }
    
        public virtual ObjectResult<SP_GetColorsOfProduct_Result> SP_GetColorsOfProduct(Nullable<int> productId)
        {
            var productIdParameter = productId.HasValue ?
                new ObjectParameter("productId", productId) :
                new ObjectParameter("productId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetColorsOfProduct_Result>("SP_GetColorsOfProduct", productIdParameter);
        }
    
        public virtual ObjectResult<SP_GetFavoriteProducts_Result1> SP_GetFavoriteProducts(Nullable<int> currentPage, Nullable<int> pageSize, Nullable<int> accountID)
        {
            var currentPageParameter = currentPage.HasValue ?
                new ObjectParameter("currentPage", currentPage) :
                new ObjectParameter("currentPage", typeof(int));
    
            var pageSizeParameter = pageSize.HasValue ?
                new ObjectParameter("PageSize", pageSize) :
                new ObjectParameter("PageSize", typeof(int));
    
            var accountIDParameter = accountID.HasValue ?
                new ObjectParameter("AccountID", accountID) :
                new ObjectParameter("AccountID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetFavoriteProducts_Result1>("SP_GetFavoriteProducts", currentPageParameter, pageSizeParameter, accountIDParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_GetFavoriteProductsCount(Nullable<int> accountId)
        {
            var accountIdParameter = accountId.HasValue ?
                new ObjectParameter("accountId", accountId) :
                new ObjectParameter("accountId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_GetFavoriteProductsCount", accountIdParameter);
        }
    
        public virtual ObjectResult<ProductDetail> SP_GetProductInfo(Nullable<int> productID, Nullable<int> accountID)
        {
            var productIDParameter = productID.HasValue ?
                new ObjectParameter("productID", productID) :
                new ObjectParameter("productID", typeof(int));
    
            var accountIDParameter = accountID.HasValue ?
                new ObjectParameter("AccountID", accountID) :
                new ObjectParameter("AccountID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<ProductDetail>("SP_GetProductInfo", productIDParameter, accountIDParameter);
        }
    
        public virtual ObjectResult<SP_GetProductInfoDetail_Result1> SP_GetProductInfoDetail(Nullable<int> productID, Nullable<int> accountID)
        {
            var productIDParameter = productID.HasValue ?
                new ObjectParameter("productID", productID) :
                new ObjectParameter("productID", typeof(int));
    
            var accountIDParameter = accountID.HasValue ?
                new ObjectParameter("AccountID", accountID) :
                new ObjectParameter("AccountID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetProductInfoDetail_Result1>("SP_GetProductInfoDetail", productIDParameter, accountIDParameter);
        }
    
        public virtual ObjectResult<SP_GetProductOfCategory_Result1> SP_GetProductOfCategory(Nullable<int> categoryId, Nullable<int> currentPage, Nullable<int> pageSize, Nullable<int> accountID)
        {
            var categoryIdParameter = categoryId.HasValue ?
                new ObjectParameter("categoryId", categoryId) :
                new ObjectParameter("categoryId", typeof(int));
    
            var currentPageParameter = currentPage.HasValue ?
                new ObjectParameter("currentPage", currentPage) :
                new ObjectParameter("currentPage", typeof(int));
    
            var pageSizeParameter = pageSize.HasValue ?
                new ObjectParameter("PageSize", pageSize) :
                new ObjectParameter("PageSize", typeof(int));
    
            var accountIDParameter = accountID.HasValue ?
                new ObjectParameter("AccountID", accountID) :
                new ObjectParameter("AccountID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetProductOfCategory_Result1>("SP_GetProductOfCategory", categoryIdParameter, currentPageParameter, pageSizeParameter, accountIDParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_GetProductOfCategoryCount(Nullable<int> categoryId)
        {
            var categoryIdParameter = categoryId.HasValue ?
                new ObjectParameter("categoryId", categoryId) :
                new ObjectParameter("categoryId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_GetProductOfCategoryCount", categoryIdParameter);
        }
    
        public virtual ObjectResult<SP_GetProductsOfProvider_Result1> SP_GetProductsOfProvider(Nullable<int> providerId, Nullable<int> currentPage, Nullable<int> pageSize, Nullable<int> accountID)
        {
            var providerIdParameter = providerId.HasValue ?
                new ObjectParameter("providerId", providerId) :
                new ObjectParameter("providerId", typeof(int));
    
            var currentPageParameter = currentPage.HasValue ?
                new ObjectParameter("currentPage", currentPage) :
                new ObjectParameter("currentPage", typeof(int));
    
            var pageSizeParameter = pageSize.HasValue ?
                new ObjectParameter("PageSize", pageSize) :
                new ObjectParameter("PageSize", typeof(int));
    
            var accountIDParameter = accountID.HasValue ?
                new ObjectParameter("AccountID", accountID) :
                new ObjectParameter("AccountID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetProductsOfProvider_Result1>("SP_GetProductsOfProvider", providerIdParameter, currentPageParameter, pageSizeParameter, accountIDParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_GetProductsOfProviderCount(Nullable<int> provderId)
        {
            var provderIdParameter = provderId.HasValue ?
                new ObjectParameter("provderId", provderId) :
                new ObjectParameter("provderId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_GetProductsOfProviderCount", provderIdParameter);
        }
    
        public virtual ObjectResult<SP_GetProviderDetail_Result1> SP_GetProviderDetail(Nullable<int> providerId)
        {
            var providerIdParameter = providerId.HasValue ?
                new ObjectParameter("providerId", providerId) :
                new ObjectParameter("providerId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetProviderDetail_Result1>("SP_GetProviderDetail", providerIdParameter);
        }
    
        public virtual ObjectResult<SP_GetProviders_Result> SP_GetProviders()
        {
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetProviders_Result>("SP_GetProviders");
        }
    
        public virtual ObjectResult<SP_GetSizesColorsOfProduct_Result> SP_GetSizesColorsOfProduct(Nullable<int> productId)
        {
            var productIdParameter = productId.HasValue ?
                new ObjectParameter("productId", productId) :
                new ObjectParameter("productId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetSizesColorsOfProduct_Result>("SP_GetSizesColorsOfProduct", productIdParameter);
        }
    
        public virtual ObjectResult<SP_GetSizesOfProduct_Result> SP_GetSizesOfProduct(Nullable<int> productId)
        {
            var productIdParameter = productId.HasValue ?
                new ObjectParameter("productId", productId) :
                new ObjectParameter("productId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetSizesOfProduct_Result>("SP_GetSizesOfProduct", productIdParameter);
        }
    
        public virtual ObjectResult<sp_helpdiagramdefinition_Result> sp_helpdiagramdefinition(string diagramname, Nullable<int> owner_id)
        {
            var diagramnameParameter = diagramname != null ?
                new ObjectParameter("diagramname", diagramname) :
                new ObjectParameter("diagramname", typeof(string));
    
            var owner_idParameter = owner_id.HasValue ?
                new ObjectParameter("owner_id", owner_id) :
                new ObjectParameter("owner_id", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<sp_helpdiagramdefinition_Result>("sp_helpdiagramdefinition", diagramnameParameter, owner_idParameter);
        }
    
        public virtual ObjectResult<sp_helpdiagrams_Result> sp_helpdiagrams(string diagramname, Nullable<int> owner_id)
        {
            var diagramnameParameter = diagramname != null ?
                new ObjectParameter("diagramname", diagramname) :
                new ObjectParameter("diagramname", typeof(string));
    
            var owner_idParameter = owner_id.HasValue ?
                new ObjectParameter("owner_id", owner_id) :
                new ObjectParameter("owner_id", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<sp_helpdiagrams_Result>("sp_helpdiagrams", diagramnameParameter, owner_idParameter);
        }
    
        public virtual ObjectResult<string> SP_ImagesOfProduct(Nullable<int> productID)
        {
            var productIDParameter = productID.HasValue ?
                new ObjectParameter("productID", productID) :
                new ObjectParameter("productID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<string>("SP_ImagesOfProduct", productIDParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_Login(string userName, string password)
        {
            var userNameParameter = userName != null ?
                new ObjectParameter("userName", userName) :
                new ObjectParameter("userName", typeof(string));
    
            var passwordParameter = password != null ?
                new ObjectParameter("password", password) :
                new ObjectParameter("password", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_Login", userNameParameter, passwordParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_Register(string name, string email, string phone, Nullable<int> roleId, string password, string username, string imageUrl, Nullable<int> active)
        {
            var nameParameter = name != null ?
                new ObjectParameter("name", name) :
                new ObjectParameter("name", typeof(string));
    
            var emailParameter = email != null ?
                new ObjectParameter("email", email) :
                new ObjectParameter("email", typeof(string));
    
            var phoneParameter = phone != null ?
                new ObjectParameter("phone", phone) :
                new ObjectParameter("phone", typeof(string));
    
            var roleIdParameter = roleId.HasValue ?
                new ObjectParameter("roleId", roleId) :
                new ObjectParameter("roleId", typeof(int));
    
            var passwordParameter = password != null ?
                new ObjectParameter("password", password) :
                new ObjectParameter("password", typeof(string));
    
            var usernameParameter = username != null ?
                new ObjectParameter("username", username) :
                new ObjectParameter("username", typeof(string));
    
            var imageUrlParameter = imageUrl != null ?
                new ObjectParameter("imageUrl", imageUrl) :
                new ObjectParameter("imageUrl", typeof(string));
    
            var activeParameter = active.HasValue ?
                new ObjectParameter("active", active) :
                new ObjectParameter("active", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_Register", nameParameter, emailParameter, phoneParameter, roleIdParameter, passwordParameter, usernameParameter, imageUrlParameter, activeParameter);
        }
    
        public virtual int sp_renamediagram(string diagramname, Nullable<int> owner_id, string new_diagramname)
        {
            var diagramnameParameter = diagramname != null ?
                new ObjectParameter("diagramname", diagramname) :
                new ObjectParameter("diagramname", typeof(string));
    
            var owner_idParameter = owner_id.HasValue ?
                new ObjectParameter("owner_id", owner_id) :
                new ObjectParameter("owner_id", typeof(int));
    
            var new_diagramnameParameter = new_diagramname != null ?
                new ObjectParameter("new_diagramname", new_diagramname) :
                new ObjectParameter("new_diagramname", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("sp_renamediagram", diagramnameParameter, owner_idParameter, new_diagramnameParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_UpdateAccInfo(Nullable<int> userId, string name, string phone, string email)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            var nameParameter = name != null ?
                new ObjectParameter("name", name) :
                new ObjectParameter("name", typeof(string));
    
            var phoneParameter = phone != null ?
                new ObjectParameter("phone", phone) :
                new ObjectParameter("phone", typeof(string));
    
            var emailParameter = email != null ?
                new ObjectParameter("email", email) :
                new ObjectParameter("email", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_UpdateAccInfo", userIdParameter, nameParameter, phoneParameter, emailParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_UpdateUser(Nullable<int> userId, string name, string email, string phone, Nullable<int> roleId, string password, string imageUrl)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            var nameParameter = name != null ?
                new ObjectParameter("name", name) :
                new ObjectParameter("name", typeof(string));
    
            var emailParameter = email != null ?
                new ObjectParameter("email", email) :
                new ObjectParameter("email", typeof(string));
    
            var phoneParameter = phone != null ?
                new ObjectParameter("phone", phone) :
                new ObjectParameter("phone", typeof(string));
    
            var roleIdParameter = roleId.HasValue ?
                new ObjectParameter("roleId", roleId) :
                new ObjectParameter("roleId", typeof(int));
    
            var passwordParameter = password != null ?
                new ObjectParameter("password", password) :
                new ObjectParameter("password", typeof(string));
    
            var imageUrlParameter = imageUrl != null ?
                new ObjectParameter("imageUrl", imageUrl) :
                new ObjectParameter("imageUrl", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_UpdateUser", userIdParameter, nameParameter, emailParameter, phoneParameter, roleIdParameter, passwordParameter, imageUrlParameter);
        }
    
        public virtual int sp_upgraddiagrams()
        {
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("sp_upgraddiagrams");
        }
    
        public virtual ObjectResult<SPGetAccounByUsername_Result> SPGetAccounByUsername(string username)
        {
            var usernameParameter = username != null ?
                new ObjectParameter("username", username) :
                new ObjectParameter("username", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SPGetAccounByUsername_Result>("SPGetAccounByUsername", usernameParameter);
        }
    
        public virtual ObjectResult<SPGetAccount_Result> SPGetAccount(Nullable<int> userId)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SPGetAccount_Result>("SPGetAccount", userIdParameter);
        }
    
        public virtual ObjectResult<SPGetAccountInfoByUserId_Result1> SPGetAccountInfoByUserId(Nullable<int> userId)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SPGetAccountInfoByUserId_Result1>("SPGetAccountInfoByUserId", userIdParameter);
        }
    
        public virtual ObjectResult<SPGetAccountInfoByUsername_Result1> SPGetAccountInfoByUsername(string username)
        {
            var usernameParameter = username != null ?
                new ObjectParameter("username", username) :
                new ObjectParameter("username", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SPGetAccountInfoByUsername_Result1>("SPGetAccountInfoByUsername", usernameParameter);
        }
    
        public virtual ObjectResult<SPGetAccountInfoUserID_Result1> SPGetAccountInfoUserID(Nullable<int> userId)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SPGetAccountInfoUserID_Result1>("SPGetAccountInfoUserID", userIdParameter);
        }
    
        public virtual ObjectResult<string> SP_GetEmail(Nullable<int> userId)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<string>("SP_GetEmail", userIdParameter);
        }
    
        public virtual ObjectResult<SP_GetAllInvoice_Result> SP_GetAllInvoice(Nullable<int> statusId, Nullable<int> currentPage, Nullable<int> pageSize, Nullable<int> accountID)
        {
            var statusIdParameter = statusId.HasValue ?
                new ObjectParameter("statusId", statusId) :
                new ObjectParameter("statusId", typeof(int));
    
            var currentPageParameter = currentPage.HasValue ?
                new ObjectParameter("currentPage", currentPage) :
                new ObjectParameter("currentPage", typeof(int));
    
            var pageSizeParameter = pageSize.HasValue ?
                new ObjectParameter("PageSize", pageSize) :
                new ObjectParameter("PageSize", typeof(int));
    
            var accountIDParameter = accountID.HasValue ?
                new ObjectParameter("AccountID", accountID) :
                new ObjectParameter("AccountID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetAllInvoice_Result>("SP_GetAllInvoice", statusIdParameter, currentPageParameter, pageSizeParameter, accountIDParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_GetAllInvoiceCount(Nullable<int> statusId, Nullable<int> accountID)
        {
            var statusIdParameter = statusId.HasValue ?
                new ObjectParameter("statusId", statusId) :
                new ObjectParameter("statusId", typeof(int));
    
            var accountIDParameter = accountID.HasValue ?
                new ObjectParameter("AccountID", accountID) :
                new ObjectParameter("AccountID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_GetAllInvoiceCount", statusIdParameter, accountIDParameter);
        }
    
        public virtual ObjectResult<SP_GetInvoiceDetail_Result> SP_GetInvoiceDetail(Nullable<int> inovoiceId)
        {
            var inovoiceIdParameter = inovoiceId.HasValue ?
                new ObjectParameter("inovoiceId", inovoiceId) :
                new ObjectParameter("inovoiceId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetInvoiceDetail_Result>("SP_GetInvoiceDetail", inovoiceIdParameter);
        }
    
        public virtual ObjectResult<SP_GetProductInvoice_Result> SP_GetProductInvoice(Nullable<int> inovoiceId)
        {
            var inovoiceIdParameter = inovoiceId.HasValue ?
                new ObjectParameter("inovoiceId", inovoiceId) :
                new ObjectParameter("inovoiceId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<SP_GetProductInvoice_Result>("SP_GetProductInvoice", inovoiceIdParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_CheckInvoiceExist(Nullable<int> inovoiceId)
        {
            var inovoiceIdParameter = inovoiceId.HasValue ?
                new ObjectParameter("inovoiceId", inovoiceId) :
                new ObjectParameter("inovoiceId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_CheckInvoiceExist", inovoiceIdParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_AddAddress(Nullable<int> userId, string province, string district, string wards, string street, string name, string phone)
        {
            var userIdParameter = userId.HasValue ?
                new ObjectParameter("userId", userId) :
                new ObjectParameter("userId", typeof(int));
    
            var provinceParameter = province != null ?
                new ObjectParameter("province", province) :
                new ObjectParameter("province", typeof(string));
    
            var districtParameter = district != null ?
                new ObjectParameter("district", district) :
                new ObjectParameter("district", typeof(string));
    
            var wardsParameter = wards != null ?
                new ObjectParameter("wards", wards) :
                new ObjectParameter("wards", typeof(string));
    
            var streetParameter = street != null ?
                new ObjectParameter("street", street) :
                new ObjectParameter("street", typeof(string));
    
            var nameParameter = name != null ?
                new ObjectParameter("name", name) :
                new ObjectParameter("name", typeof(string));
    
            var phoneParameter = phone != null ?
                new ObjectParameter("phone", phone) :
                new ObjectParameter("phone", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_AddAddress", userIdParameter, provinceParameter, districtParameter, wardsParameter, streetParameter, nameParameter, phoneParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_DelAddress(Nullable<int> addressId)
        {
            var addressIdParameter = addressId.HasValue ?
                new ObjectParameter("addressId", addressId) :
                new ObjectParameter("addressId", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_DelAddress", addressIdParameter);
        }
    
        public virtual ObjectResult<Nullable<int>> SP_AddAccount(string firstName, string lastName, string phone, string address, string dateBegin, string birthday, Nullable<int> isWorking, string email, Nullable<int> roleId, string password, string username)
        {
            var firstNameParameter = firstName != null ?
                new ObjectParameter("firstName", firstName) :
                new ObjectParameter("firstName", typeof(string));
    
            var lastNameParameter = lastName != null ?
                new ObjectParameter("lastName", lastName) :
                new ObjectParameter("lastName", typeof(string));
    
            var phoneParameter = phone != null ?
                new ObjectParameter("phone", phone) :
                new ObjectParameter("phone", typeof(string));
    
            var addressParameter = address != null ?
                new ObjectParameter("address", address) :
                new ObjectParameter("address", typeof(string));
    
            var dateBeginParameter = dateBegin != null ?
                new ObjectParameter("dateBegin", dateBegin) :
                new ObjectParameter("dateBegin", typeof(string));
    
            var birthdayParameter = birthday != null ?
                new ObjectParameter("birthday", birthday) :
                new ObjectParameter("birthday", typeof(string));
    
            var isWorkingParameter = isWorking.HasValue ?
                new ObjectParameter("isWorking", isWorking) :
                new ObjectParameter("isWorking", typeof(int));
    
            var emailParameter = email != null ?
                new ObjectParameter("email", email) :
                new ObjectParameter("email", typeof(string));
    
            var roleIdParameter = roleId.HasValue ?
                new ObjectParameter("roleId", roleId) :
                new ObjectParameter("roleId", typeof(int));
    
            var passwordParameter = password != null ?
                new ObjectParameter("password", password) :
                new ObjectParameter("password", typeof(string));
    
            var usernameParameter = username != null ?
                new ObjectParameter("username", username) :
                new ObjectParameter("username", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<Nullable<int>>("SP_AddAccount", firstNameParameter, lastNameParameter, phoneParameter, addressParameter, dateBeginParameter, birthdayParameter, isWorkingParameter, emailParameter, roleIdParameter, passwordParameter, usernameParameter);
        }
    
        public virtual int SP_EditAddress(Nullable<int> addressId, string province, string district, string wards, string street, string name, string phone)
        {
            var addressIdParameter = addressId.HasValue ?
                new ObjectParameter("addressId", addressId) :
                new ObjectParameter("addressId", typeof(int));
    
            var provinceParameter = province != null ?
                new ObjectParameter("province", province) :
                new ObjectParameter("province", typeof(string));
    
            var districtParameter = district != null ?
                new ObjectParameter("district", district) :
                new ObjectParameter("district", typeof(string));
    
            var wardsParameter = wards != null ?
                new ObjectParameter("wards", wards) :
                new ObjectParameter("wards", typeof(string));
    
            var streetParameter = street != null ?
                new ObjectParameter("street", street) :
                new ObjectParameter("street", typeof(string));
    
            var nameParameter = name != null ?
                new ObjectParameter("name", name) :
                new ObjectParameter("name", typeof(string));
    
            var phoneParameter = phone != null ?
                new ObjectParameter("phone", phone) :
                new ObjectParameter("phone", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("SP_EditAddress", addressIdParameter, provinceParameter, districtParameter, wardsParameter, streetParameter, nameParameter, phoneParameter);
        }
    }
}
