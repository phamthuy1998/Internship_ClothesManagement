USE [master]
GO
/****** Object:  Database [ClothesManament]    Script Date: 2020-11-18 8:49:43 AM ******/
CREATE DATABASE [ClothesManament]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ClothesManament', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\ClothesManament.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'ClothesManament_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\ClothesManament_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [ClothesManament] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ClothesManament].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ClothesManament] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ClothesManament] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ClothesManament] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ClothesManament] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ClothesManament] SET ARITHABORT OFF 
GO
ALTER DATABASE [ClothesManament] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ClothesManament] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ClothesManament] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ClothesManament] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ClothesManament] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ClothesManament] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ClothesManament] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ClothesManament] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ClothesManament] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ClothesManament] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ClothesManament] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ClothesManament] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ClothesManament] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ClothesManament] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ClothesManament] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ClothesManament] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ClothesManament] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ClothesManament] SET RECOVERY FULL 
GO
ALTER DATABASE [ClothesManament] SET  MULTI_USER 
GO
ALTER DATABASE [ClothesManament] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ClothesManament] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ClothesManament] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ClothesManament] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [ClothesManament] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'ClothesManament', N'ON'
GO
USE [ClothesManament]
GO
/****** Object:  UserDefinedTableType [dbo].[CategoryIds]    Script Date: 2020-11-18 8:49:43 AM ******/
CREATE TYPE [dbo].[CategoryIds] AS TABLE(
	[ID] [int] NULL
)
GO
/****** Object:  UserDefinedTableType [dbo].[IDList]    Script Date: 2020-11-18 8:49:43 AM ******/
CREATE TYPE [dbo].[IDList] AS TABLE(
	[ID] [int] NULL
)
GO
/****** Object:  UserDefinedTableType [dbo].[ProviderIds]    Script Date: 2020-11-18 8:49:43 AM ******/
CREATE TYPE [dbo].[ProviderIds] AS TABLE(
	[ID] [int] NULL
)
GO
/****** Object:  UserDefinedFunction [dbo].[ValidatePromotionCampaignDates]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[ValidatePromotionCampaignDates]
(
	@promotionId int,
    @promotionStart datetime,
    @promotionEnd datetime
)
returns bit
as
begin
    declare @Valid bit = 1;

    if exists(    select *
                  from   dbo.Promotion p 
                  where  p.id = @promotionId
                  and    @promotionStart <= p.endDate and P.beginDate <= @promotionEnd )
       set @Valid = 0;

    return @Valid;
end

GO
/****** Object:  Table [dbo].[Account]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](200) NULL,
	[roleId] [int] NULL,
	[password] [nvarchar](50) NULL,
	[username] [nvarchar](50) NULL,
	[isAccuracy] [int] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Address]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[accountId] [int] NULL,
	[province] [nvarchar](200) NULL,
	[district] [nvarchar](200) NULL,
	[wards] [nvarchar](200) NULL,
	[street] [nvarchar](200) NULL,
	[name] [nvarchar](50) NULL,
	[phone] [nvarchar](16) NULL,
	[isDefault] [int] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Address] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[detail] [nvarchar](200) NULL,
	[imageUrl] [nvarchar](max) NULL,
	[thumnail] [nvarchar](max) NULL,
	[sex] [tinyint] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Color]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Color](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[colorName] [nvarchar](50) NULL,
	[colorHex] [nvarchar](50) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Color] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Customer]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
	[phone] [nvarchar](50) NULL,
	[dateCreate] [date] NULL,
	[imageUrl] [nchar](10) NULL,
	[idAccount] [int] NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Employee]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[firstName] [nvarchar](50) NULL,
	[lastName] [nvarchar](50) NULL,
	[phone] [nvarchar](20) NULL,
	[address] [nvarchar](200) NULL,
	[avatar] [nvarchar](500) NULL,
	[dateEnd] [date] NULL,
	[dateBegin] [date] NULL CONSTRAINT [DF_Employee_dateBegin]  DEFAULT (getdate()),
	[birthday] [date] NULL,
	[isWorking] [int] NULL,
	[idAccount] [int] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FavoriteProduct]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FavoriteProduct](
	[idProduct] [int] NOT NULL,
	[idAccount] [int] NOT NULL,
 CONSTRAINT [PK_FavoriteProduct] PRIMARY KEY CLUSTERED 
(
	[idProduct] ASC,
	[idAccount] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Image]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[imageUrl] [nvarchar](200) NULL,
	[productId] [int] NULL,
 CONSTRAINT [PK_Image] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ImportCoupon]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImportCoupon](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] NULL CONSTRAINT [DF_ImportCoupon_date]  DEFAULT (getdate()),
	[employeeId] [int] NULL,
 CONSTRAINT [PK_ImportCoupon_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ImportCouponDetail]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImportCouponDetail](
	[idCoupon] [int] NOT NULL,
	[idProduct] [int] NOT NULL,
	[colorId] [int] NOT NULL,
	[sizeID] [int] NOT NULL,
	[quantity] [int] NULL,
	[price] [float] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[updateDate] [date] NULL,
	[buyDate] [date] NULL,
	[name] [nvarchar](100) NULL,
	[phone] [nvarchar](20) NULL,
	[address] [nvarchar](200) NULL,
	[note] [nvarchar](200) NULL,
	[userID] [int] NULL,
	[statusOrderId] [int] NULL,
	[employeeId] [int] NULL,
	[payment] [nvarchar](50) NULL,
	[isPaid] [int] NULL,
	[deliveryDate] [date] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InvoiceItem]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceItem](
	[orderId] [int] NOT NULL,
	[productId] [int] NOT NULL,
	[colorId] [int] NOT NULL,
	[sizeId] [int] NOT NULL,
	[unitPrice] [float] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_InvoiceItem] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC,
	[productId] ASC,
	[colorId] ASC,
	[sizeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InvoiceStatus]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceStatus](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_InvoiceStatus] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](200) NULL,
	[detail] [nvarchar](500) NULL,
	[price] [float] NULL,
	[categoryID] [int] NULL,
	[rating] [int] NULL,
	[active] [int] NULL,
	[providerId] [int] NULL,
	[thumnail] [nvarchar](200) NULL,
	[isNew] [int] NULL,
	[addDate] [date] NOT NULL CONSTRAINT [DF_Product_addDate]  DEFAULT (getdate()),
	[sold] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProductSizeColor]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductSizeColor](
	[productID] [int] NOT NULL,
	[sizeId] [int] NOT NULL,
	[colorID] [int] NOT NULL,
	[quantity] [int] NULL,
	[imageUrl] [nvarchar](200) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_ProductSizeColor_1] PRIMARY KEY CLUSTERED 
(
	[productID] ASC,
	[sizeId] ASC,
	[colorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Promotion]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Promotion](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](200) NULL,
	[name] [nvarchar](200) NULL,
	[beginDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[value] [float] NULL,
	[imageUrl] [nvarchar](200) NULL,
	[active] [int] NULL,
	[type] [nvarchar](50) NULL,
 CONSTRAINT [PK_Promotion] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PromotionItem]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PromotionItem](
	[idProduct] [int] NOT NULL,
	[idPromo] [int] NOT NULL,
 CONSTRAINT [PK_PromotionItem] PRIMARY KEY CLUSTERED 
(
	[idProduct] ASC,
	[idPromo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Provider]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Provider](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[imageUrl] [nvarchar](max) NULL,
	[brandName] [nvarchar](50) NULL,
	[infomation] [nvarchar](max) NULL,
	[phone] [nvarchar](20) NULL,
	[address] [nvarchar](200) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Brand] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Question]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[questionID] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NULL,
	[question] [nvarchar](1000) NULL,
	[dateComment] [date] NULL CONSTRAINT [DF_Comment_dateComment]  DEFAULT (getdate()),
	[dateEdit] [date] NULL,
	[parentQuestionID] [int] NULL,
	[productID] [int] NULL,
 CONSTRAINT [PK_Comment] PRIMARY KEY CLUSTERED 
(
	[questionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Rating]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rating](
	[ratingID] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [int] NOT NULL,
	[colorId] [int] NOT NULL,
	[sizeId] [int] NOT NULL,
	[productID] [int] NOT NULL,
	[accountID] [int] NOT NULL,
	[rating] [int] NULL,
	[comment] [nvarchar](1000) NULL,
	[dateRating] [datetime] NULL CONSTRAINT [DF_Rating_dateRating]  DEFAULT (getdate()),
	[dateEdit] [datetime] NULL,
	[imageUrl1] [nvarchar](200) NULL,
	[imageUrl2] [nvarchar](200) NULL,
	[imageUrl3] [nvarchar](200) NULL,
	[videoUrl] [nvarchar](200) NULL,
	[parentId] [int] NULL,
 CONSTRAINT [PK_Rating] PRIMARY KEY CLUSTERED 
(
	[ratingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleId] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [nvarchar](50) NULL,
	[description] [nvarchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Size]    Script Date: 2020-11-18 8:49:43 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[sizeName] [nvarchar](50) NULL,
	[description] [nvarchar](200) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Size] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (1, N'phamthithuy4444@gmail.com', 3, N'thuythuy', N'phamthuy', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (16, N'phamthuy2@gmail.com', 3, N'Thuy1234', N'Thuypham234', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (17, N'l.vantien286@gmail.com', 3, N'thuy123', N'thuypham123', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (21, N'thuyhhee@gmail.com', 3, N'thuy123', N'thuypham', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (26, N'nguyenngoctram@gmail.com', 2, N'thuy', N'ngoctram', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (28, N'phungngocngo@gmail.com', 2, N'phungngocngo@gmail.com', N'ngocngo', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (30, N'anna1998@gmail.com', 1, N'thuythuy', N'anna1998', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (31, N'ngocnhi@gmail.com', 2, N'ngocnhi', N'ngocnhi', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (32, N'vongocnhi@gmail.com', 2, N'ngocnhi', N'ngocnhi123', 1, 1)
INSERT [dbo].[Account] ([id], [email], [roleId], [password], [username], [isAccuracy], [active]) VALUES (33, N'aaaa', 1, N'aaaa', N'aaaa', 1, 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
SET IDENTITY_INSERT [dbo].[Address] ON 

INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (3, 1, N'Hồ Chí Minh 1', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm 1', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (4, 1, N'Hồ Chí Minh 1', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm 1', N'03676578485', 1, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (5, 1, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (7, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97, Man thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (8, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (9, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (10, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (1006, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (1007, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (1008, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (1009, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 0, 1)
INSERT [dbo].[Address] ([id], [accountId], [province], [district], [wards], [street], [name], [phone], [isDefault], [active]) VALUES (1010, 17, N'Hồ Chí Minh', N'quận 9', N'phường hiệp phú', N'97 Man Thiện', N'Thủy phạm', N'03676578485', 1, 1)
SET IDENTITY_INSERT [dbo].[Address] OFF
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (1, N'Áo thun ', N'aaaa', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fburgundy-t-shirt-kenzo-clothing-f-inseason-shirts-2019_105_1000x.jpg?alt=media&token=344bf320-0dbc-4a33-a90e-da1818288965', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FIMG_2520_xybMkyH.jpg?alt=media&token=6bcb96d1-339f-4316-a974-ef6ced86af35', 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (2, N'Áo sơ mi ', N'aa', N'https://file.yes24.vn/Upload/ProductImage/blueexchange/1867077_L.png', N'https://image.freepik.com/free-photo/close-up-colorful-t-shirts-hangers-apparel-background_51195-3843.jpg', 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (3, N'Chân váy', N'hiohooh', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fpink-skirt-ultrachic-clothing-f-inseason-skirts-2019_798_1000x.jpg?alt=media&token=a7d33314-fdb2-4534-9529-7dfe34d67c16', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FIMG_2517_LowW95s.jpg?alt=media&token=1c2325fc-bc8d-420f-b91d-cb152ed15d84', 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (4, N'Quần jeans ', N'hehe', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fjeans.png?alt=media&token=30d03a02-972d-4c47-a512-99dd802467db', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fjeans_bg.jpg?alt=media&token=867a570a-1066-4548-8272-83f292868dfb', 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (6, N'Quần tây ', N'hihi', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fpant_man.jpg?alt=media&token=99629bef-7b3e-4679-b46c-98bd6731539e', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fpant_bg.jpg?alt=media&token=ebb5502e-04d7-41c7-8677-3172a23cd137', 1, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (7, N'Đồ bơi', NULL, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FBEACH__SWIMWEAR_-_women.jpg?alt=media&token=41a91541-a351-45c5-a378-bb6b92bc0762', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FScreenshot_2019-02-07_at_20.08.44.png?alt=media&token=4296a6f9-5dad-4907-910f-105cf5ddef69', 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (8, N'Áo thun', NULL, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FIMG_2418.png?alt=media&token=c6d0e223-d809-406c-bb20-ee0a0d0a9abc', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FIMG_2537_y3TcMBv.jpg?alt=media&token=4e7cc015-6def-4dc2-bb6d-4c2dea469b32', 1, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (9, N'Váy', NULL, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fblack-dress-off-white-clothing-dresses-f-inseason-2019_476_1000x_TIQ2ph7.jpg?alt=media&token=2bb536a5-1595-4955-90f0-becbfdd1431e', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FIMG_2546_ft71GYk.jpg?alt=media&token=efebb016-9819-4ebd-a9de-5969f703d8fb', 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (10, N'Đồ ngủ', NULL, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fgilda_and_pearl__fBcTwQz.jpg?alt=media&token=8e634590-d6dc-4115-ba99-792a2673ae30', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FNIght_and_Loungewear_banner_.png?alt=media&token=41858b48-ce21-48e1-9767-e974f81d9acc', 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (11, N'Áo khoác', NULL, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FOUTERWEAR_-_unisex.jpg?alt=media&token=376d6e0b-5f50-4d5b-975d-0bc1fc92657b', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2FScreenshot_2019-02-07_at_19.38.14_Dh9sdwN.png?alt=media&token=9be6bf8a-29c8-43bc-8970-e8ddc6321d83', 3, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [imageUrl], [thumnail], [sex], [active]) VALUES (14, N'Áo sơ mi nam', N'ababab', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fcategory_icon_%C3%81o%20s%C6%A1%20mi%20nam.jpg?alt=media&token=032cc033-0773-447c-9bc9-7ad32c02def3', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/category%2Fcategory_thumbnail_%C3%81o%20s%C6%A1%20mi%20nam.jpg?alt=media&token=955f6bbf-27b1-47bb-884d-2aeff6fcf801', 1, 1)
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Color] ON 

INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (1, N'Trắng', N'#FFFFFF', 1)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (2, N'Đỏ', N'#FF0000', 1)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (3, N'Đen', N'#000000', 1)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (4, N'Hồng', N'#FF0080', 1)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (8, N'Be', N'#F1CC5C', 1)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (9, N'Ca rô', N'#400000', 1)
SET IDENTITY_INSERT [dbo].[Color] OFF
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [name], [phone], [dateCreate], [imageUrl], [idAccount]) VALUES (1, N'Thuy Pham', N'012345671', NULL, NULL, 1)
INSERT [dbo].[Customer] ([id], [name], [phone], [dateCreate], [imageUrl], [idAccount]) VALUES (2, N'Thủy Phạm', N'0476576153', NULL, NULL, 16)
INSERT [dbo].[Customer] ([id], [name], [phone], [dateCreate], [imageUrl], [idAccount]) VALUES (4, N'Pham Thi Thuy', N'0637477812', NULL, NULL, 21)
INSERT [dbo].[Customer] ([id], [name], [phone], [dateCreate], [imageUrl], [idAccount]) VALUES (5, N'Pham Thi Thuy', N'0373547621', NULL, NULL, 26)
SET IDENTITY_INSERT [dbo].[Customer] OFF
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([id], [firstName], [lastName], [phone], [address], [avatar], [dateEnd], [dateBegin], [birthday], [isWorking], [idAccount], [active]) VALUES (1, N'Trâm', N'Nguyễn Ngọc Thùy', N'012767346237', N'12, Man Thiện, phường Hiệp Phú q9, Hồ Chí Minh', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/account%2Faccount_4.jpg?alt=media&token=58b96ad0-73f9-4e5c-99e4-43a8d39114fa', CAST(N'2020-08-11' AS Date), CAST(N'2019-10-09' AS Date), CAST(N'2001-02-14' AS Date), 1, 26, 1)
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [phone], [address], [avatar], [dateEnd], [dateBegin], [birthday], [isWorking], [idAccount], [active]) VALUES (2, N'Ngô', N'Phụng  Ngọc Minh', N'0656137847', N'12, Man Thiện, phường Hiệp Phú q9, Hồ Chí Minh', N'https://www.chuphinhsanpham.vn/wp-content/uploads/2018/09/chup-anh-cv-dep-2.jpg', NULL, CAST(N'2020-08-10' AS Date), CAST(N'1998-01-01' AS Date), 1, 28, 1)
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [phone], [address], [avatar], [dateEnd], [dateBegin], [birthday], [isWorking], [idAccount], [active]) VALUES (4, N'Thuy', N'Phạm Thị', N'0939482337', N'97, Man THiện ', N'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTx3Zmwl93kSrhaxf7so-usNdWGpNc64aUanA&usqp=CAU', NULL, CAST(N'2020-08-12' AS Date), NULL, NULL, 30, 1)
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [phone], [address], [avatar], [dateEnd], [dateBegin], [birthday], [isWorking], [idAccount], [active]) VALUES (6, N'Nhi', N'Võ Ngọc', N'0176756652', N'2, Lã Xuân Oai, quận 9, Hồ Chí Minh', N'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQgYUunWYQniRrS3P4NBxZ-Flo91jB5x1PwlA&usqp=CAU', NULL, NULL, CAST(N'1900-01-01' AS Date), 1, 32, 1)
SET IDENTITY_INSERT [dbo].[Employee] OFF
INSERT [dbo].[FavoriteProduct] ([idProduct], [idAccount]) VALUES (3, 1)
SET IDENTITY_INSERT [dbo].[Image] ON 

INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (3, N'https://gumac.vn/image/t72020/img_2119-1070720201700575483.jpg', 2)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (4, N'https://gumac.vn/image/t72020/img_2113-1070720201700575483.jpg', 2)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (5, N'https://gumac.vn/image/t72020/img_2123-1070720201700575483.jpg', 2)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (9, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2F04_thun1.jpg?alt=media&token=4d90c398-cec1-41a6-8c1d-b4a641d8a807', 3)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (10, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2F04_thun2.jpg?alt=media&token=86864360-3d64-47cf-97e0-7780e205aec1', 3)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (11, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2F04_thun3.jpg?alt=media&token=93c04fec-93b2-4550-ab84-e217c5b97596', 3)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (12, N'https://gumac.vn/image/t82020/ao-thun-in-o-vuong-822300720201621422021.jpg', 4)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (13, N'https://gumac.vn/image/t82020/ao-thun-in-o-vuong-822-2300720201621422021.jpg', 4)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (14, N'https://gumac.vn/image/t82020/ao-thun-in-o-vuong-822-3300720201621422021.jpg', 4)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (15, N'https://gumac.vn/image/t82020/ao-guong-mat-dhcpnv-804-2040820201109073173.jpg', 5)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (16, N'https://gumac.vn/image/t82020/ao-guong-mat-dhcpnv-804040820201109073173.jpg', 5)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (17, N'https://gumac.vn/image/t82020/ao-guong-mat-dhcpnv-804-3040820201109073173.jpg', 5)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (18, N'https://gumac.vn/image/t82020/ao-thun-in-plant-lady-830300720201509363638.jpg', 6)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (19, N'https://gumac.vn/image/t82020/ao-thun-in-plant-lady-830-2300720201509363794.jpg', 6)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (20, N'https://gumac.vn/image/t82020/ao-thun-in-plant-lady-830-3300720201509363794.jpg', 6)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (21, N'https://gumac.vn/image/t82020/ao-thun-in-bong-hoa-828300720201423023184.jpg', 7)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (22, N'https://gumac.vn/image/t82020/ao-thun-in-bong-hoa-828-2300720201423023340.jpg', 7)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (23, N'https://gumac.vn/image/t82020/ao-thun-in-bong-hoa-828-3300720201423023340.jpg', 7)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (24, N'https://gumac.vn/image/t72020/img_2658-1-copy070720201659062668.jpg', 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (25, N'https://gumac.vn/image/t72020/img_2262-1070720201700245326.jpg', 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (26, N'https://gumac.vn/image/t72020/img_2665-1-copy070720201659062668.jpg', 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (27, N'https://gumac.vn/image/thang620basic/630-643m110620200925068623.jpg', 8)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (28, N'https://gumac.vn/image/thang620basic/630-643110620200925068467.jpg', 8)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (29, N'https://gumac.vn/image/thang620basic/630-643sau110620200925068623.jpg', 8)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (30, N'https://gumac.vn/image/t72020/img_2330-1070720201700045325.jpg', 9)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (31, N'https://gumac.vn/image/t72020/img_2335-1070720201700045325.jpg', 9)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (32, N'https://gumac.vn/image/t72020/img_2341-1070720201700045325.jpg', 9)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (33, N'https://gumac.vn/image/t72020/img_2349-1070720201700045481.jpg', 9)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (34, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2Fproduct_-1_1.jpg?alt=media&token=0964b25a-edb4-4ffb-a97c-44e3144d078d', 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId]) VALUES (35, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2Fproduct_-1_2.jpg?alt=media&token=652b2b4b-929c-483d-be27-16ac1ba64c04', 2)
SET IDENTITY_INSERT [dbo].[Image] OFF
SET IDENTITY_INSERT [dbo].[ImportCoupon] ON 

INSERT [dbo].[ImportCoupon] ([id], [date], [employeeId]) VALUES (4, CAST(N'2020-08-15' AS Date), 1)
INSERT [dbo].[ImportCoupon] ([id], [date], [employeeId]) VALUES (5, CAST(N'2020-08-11' AS Date), 4)
INSERT [dbo].[ImportCoupon] ([id], [date], [employeeId]) VALUES (8, CAST(N'2020-08-31' AS Date), 4)
SET IDENTITY_INSERT [dbo].[ImportCoupon] OFF
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (4, 2, 2, 2, 22, 452225)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (4, 2, 1, 2, 15, 200000)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (5, 9, 1, 2, 12, NULL)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (5, 9, 2, 2, 1, 21323)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (4, 2, 8, 2, 12, NULL)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (4, 2, 9, 2, 2, NULL)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (8, 2, 2, 1, 12, 20000)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (8, 1, 1, 1, 20, 12)
INSERT [dbo].[ImportCouponDetail] ([idCoupon], [idProduct], [colorId], [sizeID], [quantity], [price]) VALUES (5, 1, 1, 1, 8, 210000)
SET IDENTITY_INSERT [dbo].[Invoice] ON 

INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (34, NULL, CAST(N'2020-08-22' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 2, NULL, N'Thanh toán online', 1, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (35, NULL, CAST(N'2020-08-22' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 1, NULL, N'thanh toán khi nhận hàng', 0, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (36, NULL, CAST(N'2020-08-25' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 1, NULL, N'thanh toán khi nhận hàng', 0, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (37, NULL, CAST(N'2020-08-25' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 1, NULL, N'Thanh toán online', 1, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (38, NULL, CAST(N'2020-08-25' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 1, NULL, N'Thanh toán khi nhận hàng', 0, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (39, NULL, CAST(N'2020-08-25' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 1, NULL, N'Thanh toán online', 1, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (40, NULL, CAST(N'2020-08-27' AS Date), N'Thủy phạm', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh', N'', 1, 1, NULL, N'Thanh toán online', 1, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (41, NULL, CAST(N'2020-08-27' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 1, NULL, N'Thanh toán khi nhận hàng', 0, NULL, 1)
INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [employeeId], [payment], [isPaid], [deliveryDate], [active]) VALUES (42, NULL, CAST(N'2020-08-29' AS Date), N'Thủy phạm 1', N'03676578485', N'97 Man Thiện, phường hiệp phú, quận 9, Hồ Chí Minh 1', N'', 1, 1, NULL, N'Thanh toán khi nhận hàng', 0, NULL, 1)
SET IDENTITY_INSERT [dbo].[Invoice] OFF
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (34, 7, 2, 2, 99000, 2)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (34, 7, 3, 2, 99000, 2)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (35, 6, 2, 2, 99000, 3)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (36, 3, 1, 1, 150000, 1)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (39, 1, 1, 1, 149100, 1)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (40, 1, 1, 1, 149100, 11)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (41, 1, 1, 1, 149100, 2)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId], [unitPrice], [quantity]) VALUES (42, 1, 1, 1, 149100, 4)
SET IDENTITY_INSERT [dbo].[InvoiceStatus] ON 

INSERT [dbo].[InvoiceStatus] ([id], [statusName]) VALUES (1, N'Đã tiếp nhận')
INSERT [dbo].[InvoiceStatus] ([id], [statusName]) VALUES (2, N'Đang giao')
INSERT [dbo].[InvoiceStatus] ([id], [statusName]) VALUES (3, N'Đã giao')
INSERT [dbo].[InvoiceStatus] ([id], [statusName]) VALUES (4, N'Đã hủy')
INSERT [dbo].[InvoiceStatus] ([id], [statusName]) VALUES (5, N'Đã thanh toán online')
SET IDENTITY_INSERT [dbo].[InvoiceStatus] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (1, N'ÁO SƠ MI CỔ LẬT', N'THÔNG TIN SẢN PHẨM
Áo sơ mi cổ lật

Mã Sản Phẩm: AA772
- Màu Sắc Sản Phẩm: BE, ĐEN
- Giá Bán Sản Phẩm: 285.000đ
- Số Đo Vòng Ngực: 92CM
- Số Đo Lai: 91CM
- Chiều Dài Áo: 60CM
- Số Đo Ngang Vai: 34CM
- Số Đo Dài Tay: 57CM
- Số Đo Cửa Tay: 20CM
- Thông Tin Sản Phẩm Size: XS
- Chất Liệu Vải: XÔ BÓNG
- Số Size:  XS - S - M - L - XL
- Áo Có 1 Lớp', 213000, 2, 5, 1, 5, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2Fthumbnail_1.jpg?alt=media&token=7700cd22-abd1-4dbe-90f3-94454c41fcae', 1, CAST(N'2020-02-02' AS Date), 28)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (2, N'ÁO SƠ MI PHỐI MẮT CÁO', N'THÔNG TIN SẢN PHẨM
Áo sơ mi phối mắt cáo

Mã Sản Phẩm: AA714
- Màu Sắc Sản Phẩm: TRẮNG
- Giá Bán Sản Phẩm: 285.000đ
- Số Đo Vòng Ngực: 86CM
- Số Đo Lai: 92CM
- Chiều Dài Áo: 60CM
- Số Đo Ngang Vai: 35CM
- Số Đo Dài Tay: 41CM
- Số Đo Cửa Tay: 24CM
- Thông Tin Sản Phẩm Size: XS
- Chất Liệu Vải: XÔ BÓNG
- Số Size:  XS - S - M - L - XL
- Áo Có 1 Lớp', 150000, 2, 4, 1, 5, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2Fthumbnail_2.jpg?alt=media&token=88035651-fe04-456d-961b-4a4f438bbadd', 0, CAST(N'2020-02-02' AS Date), 2)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (3, N'ÁO THUN VIỀN BE ĐHCPNV', N'THÔNG TIN SẢN PHẨM
Áo thun viền be ĐHCPNV

Mã Sản Phẩm: ATA881
- Màu Sắc: TRẮNG
- Giá Bán: 185,000đ
- Số Đo Ngực: 102CM
- Số Đo Ngang Lai: 102CM
- Số Đo Vai: 56CM
- Số Đo Dài Tay: 18CM
- Số Đo Cửa Tay: 38CM
- Số Đo Dài Áo: 64CM
- Thông Tin Size: S
- Số Size: S, M, L
- Chất Liệu Vải: Thun TC https://www.google.com/ tel 017551652347', 150000, 1, 4, 1, 5, N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/product%2F04_thun3.jpg?alt=media&token=93c04fec-93b2-4550-ab84-e217c5b97596', 0, CAST(N'2020-02-02' AS Date), 4)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (4, N'ÁO THUN IN Ô VUÔNG', N'THÔNG TIN SẢN PHẨM
Áo gương mặt DHCPNV

Mã Sản Phẩm: ATA804
- Màu Sắc: GẠCH
- Giá Bán: 185,000đ
- Số Đo Ngực: 96CM
- Số Đo Ngang Lai: 96CM
- Số Đo Vai: 37.5CM
- Chiều Dài Tay: 18CM
- Số Đo Cửa Tay: 34CM
- Chiều Dài Áo: 62CM
- Thông Tin Size: M
- Số Size: S, M, L
- Chất Liệu Vải: Thun TC', 130000, 1, 5, 1, 5, N'https://gumac.vn/image/t82020/ao-thun-in-o-vuong-822300720201621422021.jpg?width=450', 1, CAST(N'2020-07-24' AS Date), 20)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (5, N'ÁO GƯƠNG MẶT DHCPNV', N'THÔNG TIN SẢN PHẨM
Áo gương mặt DHCPNV

Mã Sản Phẩm: ATA804
- Màu Sắc: GẠCH
- Giá Bán: 185,000đ
- Số Đo Ngực: 96CM
- Số Đo Ngang Lai: 96CM
- Số Đo Vai: 37.5CM
- Chiều Dài Tay: 18CM
- Số Đo Cửa Tay: 34CM
- Chiều Dài Áo: 62CM
- Thông Tin Size: M
- Số Size: S, M, L
- Chất Liệu Vải: Thun TC', 90000, 1, 5, 1, 5, N'https://gumac.vn/image/t82020/ao-guong-mat-dhcpnv-804-2040820201109073173.jpg', 1, CAST(N'2020-08-21' AS Date), 0)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (6, N'ÁO THUN IN PLANT LADY', N'THÔNG TIN SẢN PHẨM
Áo thun in plant lady

Mã Sản Phẩm: ATA830
- Màu Sắc: TRẮNG
- Giá Bán: 185,000đ
- Số Đo Ngực: 96CM
- Số Đo Ngang Lai: 96CM
- Số Đo Vai: 37CM
- Số Đo Dài Tay: 18CM
- Số Đo Cửa Tay: 28CM
- Chiều Dài Áo: 60CM
- Thông Tin Size: S
- Số Size: S, M, L
- Chất Liệu Vải: Thun Dẻo', 99000, 1, 4, 1, 5, N'https://gumac.vn/image/t82020/ao-thun-in-plant-lady-830300720201509363638.jpg?width=450', 0, CAST(N'2020-08-21' AS Date), 1)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (7, N'ÁO THUN IN BÔNG HOA', N'THÔNG TIN SẢN PHẨM
Áo thun in bông hoa

Mã Sản Phẩm: ATA828
- Màu Sắc: XÁM
- Giá Bán: 185,000đ
- Số Đo Ngực: 90CM
- Số Đo Ngang Lai: 92CM
- Số Đo Vai: 36CM
- Chiều Dài Tay: 17CM
- Số Đo Cửa Tay: 29CM
- Chiều Dài Áo: 57.5CM
- Thông Tin Size: S
- Số Size: S, M, L
- Chất Liệu Vải: Thun Dẻo', 99000, 1, 4, 1, 5, N'https://gumac.vn/image/t82020/ao-thun-in-bong-hoa-828300720201423023184.jpg?width=450', 0, CAST(N'2020-08-21' AS Date), 3)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (8, N'ÁO SƠ MI MẮT CÁO', N'THÔNG TIN SẢN PHẨM
Áo sơ mi mắt cáo

Mã Sản Phẩm: AA630
- Màu Sắc Sản Phẩm: ĐEN
- Giá Bán Sản Phẩm: 275.000đ
- Số Đo Vòng Ngực: 93CM
- Số Đo Vòng Hông: 97CM
- Chiều Dài Áo: 63CM
- Số Đo Ngang Vai: 38CM
- Số Đo Cửa Tay: 18CM
- Chiều Dài Tay: 61CM
- Thông Tin Sản Phẩm Size: XS
- Chất Liệu Vải: LỤA CHÉO
- Số Size: XS - S - M - L - XL
- Áo Có 1 Lớp', 165000, 2, 5, 1, 5, N'https://gumac.vn/image/thang620basic/630-643m110620200925068623.jpg?width=450', 1, CAST(N'2020-08-21' AS Date), 4)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (9, N'CHÂN VÁY XÒE ĐAI RỜI', N'THÔNG TIN SẢN PHẨM
Chân váy xòe đai rời

Mã Sản Phẩm: VA768
- Màu Sắc Sản Phẩm: CARO
- Giá Bán Sản Phẩm: 285.000đ
- Số Đo Vòng Eo: 62CM
- Số Đo Vòng Hông: 70CM
- Chiều Dài Váy: 63CM
- Thông Tin Sản Phẩm Size: XS
- Chất Liệu Vải: BỐ COTTON
- Số Size: XS - S - M - L - XL
- Có Quần Lớp Trong
- Có Túi', 220000, 3, NULL, 1, 5, N'https://gumac.vn/image/t72020/img_2330-1070720201700045325.jpg?width=450', 1, CAST(N'2020-08-21' AS Date), 33)
SET IDENTITY_INSERT [dbo].[Product] OFF
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (1, 1, 1, 24, N'2', 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (1, 1, 8, 12, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (1, 2, 1, 2, N'1', 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (1, 2, 2, 46, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (2, 1, 1, 58, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (2, 1, 2, 12, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (2, 2, 1, 37, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (2, 2, 8, 12, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (2, 2, 9, 2, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (2, 3, 1, 2, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (2, 4, 1, 2, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (3, 1, 1, 50, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (3, 2, 1, 38, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (3, 3, 1, 12, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (3, 4, 1, 0, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (4, 2, 2, 12, N'aa', 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (4, 3, 2, 3, N'3', 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (5, 1, 1, 30, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (5, 2, 1, 17, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (5, 3, 1, 23, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (5, 4, 1, 54, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (6, 2, 2, 34, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (6, 2, 3, 24, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (6, 2, 4, 23, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (7, 2, 2, 21, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (7, 2, 3, 22, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (8, 1, 2, 2, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (8, 2, 2, 2, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (8, 3, 2, 1, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (8, 4, 2, 1, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (9, 1, 1, 40, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (9, 2, 1, 17, NULL, 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (9, 2, 2, 1, NULL, 1)
SET IDENTITY_INSERT [dbo].[Promotion] ON 

INSERT [dbo].[Promotion] ([id], [description], [name], [beginDate], [endDate], [value], [imageUrl], [active], [type]) VALUES (2, N'Tưng bừn mua sắm lễ quốc khánh, giảm giá sốc', N'Quốc khánh săn sale', CAST(N'2020-10-10 00:00:00.000' AS DateTime), CAST(N'2020-11-05 00:00:00.000' AS DateTime), 0.3, NULL, 1, N'percent')
INSERT [dbo].[Promotion] ([id], [description], [name], [beginDate], [endDate], [value], [imageUrl], [active], [type]) VALUES (3, N'Khuyến mãi khủng, chào mừng ngày quốc khánh', N'Sale tưng bừng', CAST(N'2020-01-01 00:00:00.000' AS DateTime), CAST(N'2020-07-20 00:00:00.000' AS DateTime), 0.1, NULL, 1, N'percent')
INSERT [dbo].[Promotion] ([id], [description], [name], [beginDate], [endDate], [value], [imageUrl], [active], [type]) VALUES (4, N'Tưng bừng mua sắm mùa covid, ở nhà an toàn', N'Khuyến mãi mùa covid', CAST(N'2020-08-09 00:00:00.000' AS DateTime), CAST(N'2020-08-28 00:00:00.000' AS DateTime), 0.2, N'aaaa', 1, N'percent')
SET IDENTITY_INSERT [dbo].[Promotion] OFF
INSERT [dbo].[PromotionItem] ([idProduct], [idPromo]) VALUES (1, 2)
INSERT [dbo].[PromotionItem] ([idProduct], [idPromo]) VALUES (2, 2)
INSERT [dbo].[PromotionItem] ([idProduct], [idPromo]) VALUES (2, 4)
SET IDENTITY_INSERT [dbo].[Provider] ON 

INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (1, N'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTg98qdNu00SqKoVbVQmqDP4fSAxetTiErXLA&usqp=CAU', N'Thỏ Tây', N'Shop bán hàng nữ', N'076265657762', N'160 đường 20 Gò Vấp, tp.Hồ Chí Minh', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (2, N'https://moxycoaching.com/wp-content/uploads/2019/06/moxyLogo.png', N'Moxy', N'Quần áo nữ', N'03762378181', N'97, Man Thiện, phường Hiệp Phú, quận 9, Tp.Hồ Chí Minh', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (4, N'https://tapchilamdep.com/wp-content/uploads/2019/11/viettien-logo.jpg', N'Việt Tiến', N'Quần áo nam công sở', N'03874872171', N'12, Lê Văn Việt, Hiệp Phú quận 9', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (5, N'https://vietmarket.vn/wp-content/uploads/2018/10/logo-07-300x224.png', N'GUMAC', N'Quần áo nữ', N'023747374612', N'22, Lê Văn Việt, Phường Hiệp Phú, quận 9', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (7, N'https://admarket.vn/blog/uploads/images/adidas-logo-1.jpg', N'Adidas', N'Quần áo thời trang', N'028394823487', N'22, Lê Văn Việt, Phường Hiệp Phú, quận 9', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (8, N'https://shopee.vn/blog/wp-content/uploads/2018/09/Juno.jpg', N'Juno', N'Quần áo nữ', N'0139893848', N'12, Lê Chí Thọ', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (9, N'https://idme-marketplace.s3.amazonaws.com/nWvKA2m4pfviu91koP8DWHZV', N'Just man', N'Đồ nam', N'90293498477', N'11, Lê Văn Việt', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (10, N'https://thietkelogo.com/wp-content/uploads/2017/09/logo-nike.png', N'Nike', N'Thời trang nam nữ', N'0989818374', N'99, Lê Trang Long,...', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (11, N'https://logos-world.net/wp-content/uploads/2020/04/Amazon-Logo.png', N'Amazon', N'Quần áo phụ kiện', N'029839283', N'123, abc, a', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [phone], [address], [active]) VALUES (12, N'https://www.thuongdo.com/sites/default/files/field/image/alibaba.jpg', N'Alibaba', N'Quần áo nam', N'03898478817', N'88, Lê Thị', 1)
SET IDENTITY_INSERT [dbo].[Provider] OFF
SET IDENTITY_INSERT [dbo].[Question] ON 

INSERT [dbo].[Question] ([questionID], [accountID], [question], [dateComment], [dateEdit], [parentQuestionID], [productID]) VALUES (40, 16, N'xin chao', CAST(N'2020-10-08' AS Date), NULL, NULL, 3)
INSERT [dbo].[Question] ([questionID], [accountID], [question], [dateComment], [dateEdit], [parentQuestionID], [productID]) VALUES (48, 1, N'chinh sua', CAST(N'2020-10-24' AS Date), NULL, NULL, 3)
INSERT [dbo].[Question] ([questionID], [accountID], [question], [dateComment], [dateEdit], [parentQuestionID], [productID]) VALUES (49, 1, N'@ phamthuy chao xin', CAST(N'2020-10-24' AS Date), NULL, 48, 3)
SET IDENTITY_INSERT [dbo].[Question] OFF
SET IDENTITY_INSERT [dbo].[Rating] ON 

INSERT [dbo].[Rating] ([ratingID], [orderId], [colorId], [sizeId], [productID], [accountID], [rating], [comment], [dateRating], [dateEdit], [imageUrl1], [imageUrl2], [imageUrl3], [videoUrl], [parentId]) VALUES (12, 42, 1, 1, 1, 1, 5, N'hehehe', CAST(N'2020-11-12 11:44:07.840' AS DateTime), CAST(N'2020-11-12 14:56:01.280' AS DateTime), N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/rating%2F56265647-eb0e-40b5-887d-cd7222baa44a?alt=media&token=a7d1d923-5c4a-456f-a6e3-c916fd9cb182', N'https://firebasestorage.googleapis.com/v0/b/ptshop-8b8b3.appspot.com/o/rating%2F979499c2-ca23-4b70-a810-cc5d26590797?alt=media&token=05c42efb-c825-4792-8fe0-5da41ffb8b3e', NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Rating] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([roleId], [roleName], [description]) VALUES (1, N'Admin', NULL)
INSERT [dbo].[Role] ([roleId], [roleName], [description]) VALUES (2, N'Employee', NULL)
INSERT [dbo].[Role] ([roleId], [roleName], [description]) VALUES (3, N'Customer', NULL)
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Size] ON 

INSERT [dbo].[Size] ([id], [sizeName], [description], [active]) VALUES (1, N'XS', N'hahshgj', 1)
INSERT [dbo].[Size] ([id], [sizeName], [description], [active]) VALUES (2, N'M', N'bbb', 1)
INSERT [dbo].[Size] ([id], [sizeName], [description], [active]) VALUES (3, N'S', N'uuu', 1)
INSERT [dbo].[Size] ([id], [sizeName], [description], [active]) VALUES (4, N'XL', N'aaaa', 1)
SET IDENTITY_INSERT [dbo].[Size] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UX_AccountEmail]    Script Date: 2020-11-18 8:49:44 AM ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UX_AccountEmail] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UX_Username]    Script Date: 2020-11-18 8:49:44 AM ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UX_Username] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_Color]    Script Date: 2020-11-18 8:49:44 AM ******/
ALTER TABLE [dbo].[Color] ADD  CONSTRAINT [IX_Color] UNIQUE NONCLUSTERED 
(
	[colorName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_CustomerPhone]    Script Date: 2020-11-18 8:49:44 AM ******/
ALTER TABLE [dbo].[Customer] ADD  CONSTRAINT [IX_CustomerPhone] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_EmployeePhone]    Script Date: 2020-11-18 8:49:44 AM ******/
ALTER TABLE [dbo].[Employee] ADD  CONSTRAINT [IX_EmployeePhone] UNIQUE NONCLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_Rating]    Script Date: 2020-11-18 8:49:44 AM ******/
ALTER TABLE [dbo].[Rating] ADD  CONSTRAINT [IX_Rating] UNIQUE NONCLUSTERED 
(
	[productID] ASC,
	[accountID] ASC,
	[colorId] ASC,
	[orderId] ASC,
	[sizeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_Size]    Script Date: 2020-11-18 8:49:44 AM ******/
ALTER TABLE [dbo].[Size] ADD  CONSTRAINT [IX_Size] UNIQUE NONCLUSTERED 
(
	[sizeName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Role] ([roleId])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Address]  WITH CHECK ADD  CONSTRAINT [FK_Address_Account1] FOREIGN KEY([accountId])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Address] CHECK CONSTRAINT [FK_Address_Account1]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_Account] FOREIGN KEY([idAccount])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FK_Customer_Account]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Account1] FOREIGN KEY([idAccount])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Account1]
GO
ALTER TABLE [dbo].[FavoriteProduct]  WITH CHECK ADD  CONSTRAINT [FK_FavoriteProduct_Account] FOREIGN KEY([idAccount])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[FavoriteProduct] CHECK CONSTRAINT [FK_FavoriteProduct_Account]
GO
ALTER TABLE [dbo].[FavoriteProduct]  WITH CHECK ADD  CONSTRAINT [FK_FavoriteProduct_Product] FOREIGN KEY([idProduct])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[FavoriteProduct] CHECK CONSTRAINT [FK_FavoriteProduct_Product]
GO
ALTER TABLE [dbo].[Image]  WITH CHECK ADD  CONSTRAINT [FK_Image_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Image] CHECK CONSTRAINT [FK_Image_Product]
GO
ALTER TABLE [dbo].[ImportCoupon]  WITH CHECK ADD  CONSTRAINT [FK_ImportCoupon_Employee] FOREIGN KEY([employeeId])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[ImportCoupon] CHECK CONSTRAINT [FK_ImportCoupon_Employee]
GO
ALTER TABLE [dbo].[ImportCouponDetail]  WITH CHECK ADD  CONSTRAINT [FK_ImportCouponDetail_ImportCoupon] FOREIGN KEY([idCoupon])
REFERENCES [dbo].[ImportCoupon] ([id])
GO
ALTER TABLE [dbo].[ImportCouponDetail] CHECK CONSTRAINT [FK_ImportCouponDetail_ImportCoupon]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Account] FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Account]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Employee] FOREIGN KEY([employeeId])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Employee]
GO
ALTER TABLE [dbo].[InvoiceItem]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceItem_Invoice] FOREIGN KEY([orderId])
REFERENCES [dbo].[Invoice] ([id])
GO
ALTER TABLE [dbo].[InvoiceItem] CHECK CONSTRAINT [FK_InvoiceItem_Invoice]
GO
ALTER TABLE [dbo].[InvoiceItem]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceItem_ProductSizeColor] FOREIGN KEY([productId], [sizeId], [colorId])
REFERENCES [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID])
GO
ALTER TABLE [dbo].[InvoiceItem] CHECK CONSTRAINT [FK_InvoiceItem_ProductSizeColor]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Brand] FOREIGN KEY([providerId])
REFERENCES [dbo].[Provider] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Brand]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[ProductSizeColor]  WITH CHECK ADD  CONSTRAINT [FK_ProductSizeColor_Color1] FOREIGN KEY([colorID])
REFERENCES [dbo].[Color] ([id])
GO
ALTER TABLE [dbo].[ProductSizeColor] CHECK CONSTRAINT [FK_ProductSizeColor_Color1]
GO
ALTER TABLE [dbo].[ProductSizeColor]  WITH CHECK ADD  CONSTRAINT [FK_ProductSizeColor_Product1] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ProductSizeColor] CHECK CONSTRAINT [FK_ProductSizeColor_Product1]
GO
ALTER TABLE [dbo].[ProductSizeColor]  WITH CHECK ADD  CONSTRAINT [FK_ProductSizeColor_Size1] FOREIGN KEY([sizeId])
REFERENCES [dbo].[Size] ([id])
GO
ALTER TABLE [dbo].[ProductSizeColor] CHECK CONSTRAINT [FK_ProductSizeColor_Size1]
GO
ALTER TABLE [dbo].[PromotionItem]  WITH CHECK ADD  CONSTRAINT [FK_PromotionItem_Product] FOREIGN KEY([idProduct])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[PromotionItem] CHECK CONSTRAINT [FK_PromotionItem_Product]
GO
ALTER TABLE [dbo].[PromotionItem]  WITH CHECK ADD  CONSTRAINT [FK_PromotionItem_Promotion] FOREIGN KEY([idPromo])
REFERENCES [dbo].[Promotion] ([id])
GO
ALTER TABLE [dbo].[PromotionItem] CHECK CONSTRAINT [FK_PromotionItem_Promotion]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Account] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Comment_Account]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Product] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Comment_Product]
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD  CONSTRAINT [FK_Rating_Account] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Rating] CHECK CONSTRAINT [FK_Rating_Account]
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD  CONSTRAINT [FK_Rating_InvoiceItem] FOREIGN KEY([orderId], [productID], [colorId], [sizeId])
REFERENCES [dbo].[InvoiceItem] ([orderId], [productId], [colorId], [sizeId])
GO
ALTER TABLE [dbo].[Rating] CHECK CONSTRAINT [FK_Rating_InvoiceItem]
GO
ALTER TABLE [dbo].[Promotion]  WITH NOCHECK ADD  CONSTRAINT [CK_PromotionCampaign_ValidateDates] CHECK  (([dbo].[ValidatePromotionCampaignDates]([id],[beginDate],[endDate])=(1)))
GO
ALTER TABLE [dbo].[Promotion] CHECK CONSTRAINT [CK_PromotionCampaign_ValidateDates]
GO
/****** Object:  StoredProcedure [dbo].[ChangePassword]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ChangePassword]
	@userId INT,
	@oldPass VARCHAR(36),
	@newPass VARCHAR(36)
AS
BEGIN
	IF EXISTS (SELECT * FROM dbo.Account WHERE Id = @userId AND Password != @oldPass)
		SELECT -1--mật khẩu cũ không đúng
	ELSE
		BEGIN
			UPDATE dbo.account 
			SET Password = @newPass
			WHERE Id = @userId

			SELECT 1 --thành công
		END
END





GO
/****** Object:  StoredProcedure [dbo].[DelAccount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[DelAccount]
	@userId INT
AS
BEGIN

	IF NOT EXISTS (SELECT * FROM dbo.Account WHERE Id != @userId)
		SELECT -1 --account không tồn tại
	ELSE
	BEGIN
		UPDATE account SET ACTIVE = 0 WHERE id = @userId
		SELECT 0  -- Xóa tài khoản thành công
	END 

END



GO
/****** Object:  StoredProcedure [dbo].[forgotPasswordReset]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[forgotPasswordReset]
	@email NVARCHAR(50),
	@newPass VARCHAR(36)
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM dbo.Account WHERE email = @email)
		SELECT -1--email khong ton tai trong he thong
	ELSE
    BEGIN
		UPDATE dbo.account 
		SET Password = @newPass
		WHERE email = @email
	END
	SELECT 1
END





GO
/****** Object:  StoredProcedure [dbo].[getCategoryByGender]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getCategoryByGender]
	@GenderID INT
AS
BEGIN
	SELECT id, name, detail, imageUrl, thumnail FROM category WHERE sex = @GenderID AND active = 1
END





GO
/****** Object:  StoredProcedure [dbo].[getCategoryGender]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getCategoryGender]
	@GenderID INT
AS
BEGIN
	SELECT id, name, detail, imageUrl, thumnail FROM category WHERE sex = @GenderID AND active = 1
END





GO
/****** Object:  StoredProcedure [dbo].[SP_AddAccount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddAccount]
	@firstName NVARCHAR(50),
	@lastName NVARCHAR(50),
	@phone NVARCHAR(50),
	@address NVARCHAR(50),
	@dateBegin NVARCHAR(50),
	@birthday NVARCHAR(50),
	@isWorking INT,
	@email NVARCHAR(50),
	@roleId int,
	@password NVARCHAR(50),
	@username NVARCHAR(50)
AS
BEGIN
	DECLARE @accountId int
	IF EXISTS (SELECT * FROM Account WHERE email = @email)
		SELECT -1
	ELSE IF EXISTS (SELECT * FROM dbo.Employee WHERE phone = @phone)
		SELECT -2
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE username = @username)
		SELECT -3
	ELSE
	BEGIN
		INSERT dbo.Account
		        ( email ,
		          roleId ,
		          password ,
		          username ,
		          active
		        )
		VALUES  ( @email , -- email - nvarchar(200)
		          @roleId , -- roleId - int
		          @password, -- password - nvarchar(50)
		          @username , -- username - nvarchar(50)
		          1  -- active - int
		        )
		SET @accountId = SCOPE_IDENTITY() 

		INSERT dbo.Employee
		        ( firstName ,
		          lastName ,
		          phone ,
		          address ,
		          dateEnd ,
		          dateBegin ,
		          birthday ,
		          isWorking ,
		          idAccount
		        )
		VALUES  (@firstName , -- firstName - nvarchar(50)
		         @lastName , -- lastName - nvarchar(50)
		         @phone , -- phone - nvarchar(20)
		         @address , -- address - nvarchar(200)
		         null , -- dateEnd - date
		         @dateBegin , -- dateBegin - date
		         @birthday , -- birthday - date
		          1 , -- isWorking - int
		          @accountId  -- idAccount - int
		        )
	END

END





GO
/****** Object:  StoredProcedure [dbo].[SP_AddAddress]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddAddress]
	@userId INT,
	@province NVARCHAR(200),
	@district NVARCHAR(200),
	@wards NVARCHAR(200),
	@street NVARCHAR(200),
	@name NVARCHAR(50),
	@phone NVARCHAR(20),
	@isDefault int
AS
BEGIN
	DECLARE @idAddress INT
    INSERT dbo.Address
            ( accountId ,
              province ,
              district ,
              wards ,
              street ,
              name ,
              phone ,
              active, 
			  isDefault
            )
    VALUES  ( @userId , -- accountId - int
              @province , -- province - nvarchar(200)
              @district , -- district - nvarchar(200)
              @wards , -- wards - nvarchar(200)
              @street , -- street - nvarchar(200)
              @name , -- name - nvarchar(50)
              @phone , -- phone - nvarchar(16)
              1,  -- active - int,
			  @isDefault
            )

	SET @idAddress = SCOPE_IDENTITY() 
	IF(@isDefault = 1)
	BEGIN
		UPDATE dbo.Address SET isDefault = 0 WHERE  id != @idAddress AND accountId = @userId
	END
	SELECT @idAddress
END



GO
/****** Object:  StoredProcedure [dbo].[SP_AddEmployee]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddEmployee]
	@firstname NVARCHAR(100),
	@lastname NVARCHAR(100),
	@phone NVARCHAR(100),
	@address NVARCHAR(200),
	@avatar NVARCHAR(MAX),
	@birthday NVARCHAR(10),
	@beginDate NVARCHAR(10),
	@endDate NVARCHAR(10),
	@isWorking INT,

	@email NVARCHAR(50),
	@roleId INT,
	@password NVARCHAR(100),
	@username NVARCHAR(100)
AS
BEGIN
	IF EXISTS (SELECT * FROM dbo.Employee WHERE phone= @phone )
		SELECT -1, 'Số điện thoại đã được dùng cho tài khoản khác' AS message -- trung so dien thoai
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE email= @email)
		SELECT -3 , 'Email đã được dùng cho tài khoản khác' AS message--trung email
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE username = @username )
		SELECT -4 ,  'Username đã được dùng cho tài khoản khác' AS message--trung username
	ELSE
    BEGIN
		IF(@birthday ='null')set @birthday=NULL
		IF(@beginDate ='null') SET @beginDate=NULL
		IF(@endDate ='null') SET @endDate = NULL

		DECLARE @accountId INT 
		INSERT dbo.Account(email,[roleId],[password],[username],[isAccuracy],[active])
		VALUES(@email,@roleId,@password,@username,1,1)
		SET @accountId = SCOPE_IDENTITY()

		INSERT Employee(firstName, lastName, phone, address, avatar, dateEnd, dateBegin, birthday, isWorking, idAccount, active)
		VALUES(  @firstname,@lastname, @phone,@address,@avatar,(SELECT CONVERT(DATE, @endDate, 120)),(SELECT CONVERT(DATE, @beginDate, 120)),
								(SELECT CONVERT(DATE, @birthday, 120)), @isWorking, @accountId, 1)
		SELECT 1, 'Thêm tài khoản thành công' AS message
    END
end


GO
/****** Object:  StoredProcedure [dbo].[SP_AddOrder]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddOrder]
	@userId INT,
	@address NVARCHAR(500),
	@phone NVARCHAR(20),
	@name NVARCHAR(100),
	@note NVARCHAR(500),
	@payment NVARCHAR(200),
	@isPaid INT		
AS
BEGIN
DECLARE @invoiceID int
	INSERT dbo.Invoice(buyDate ,name ,phone , address ,note ,userID , statusOrderId , active, payment, isPaid )
	VALUES  (GETDATE() , -- buyDate - date
	          @name , -- name - nvarchar(50)
	          @phone , -- phone - nvarchar(15)
	          @address , -- address - nvarchar(50)
	          @note , -- note - nvarchar(200)
	          @userId , -- userID - int
	          1 , -- statusOrderId - int
	          1 , -- active - int
			  @payment,
			  @isPaid
	        )
	SET @invoiceID =  @@IDENTITY
	SELECT @invoiceID
END



GO
/****** Object:  StoredProcedure [dbo].[SP_AddOrderItem]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddOrderItem]
	@invoiceId INT,
	@productId INT,
	@colorId INT,
	@sizeId INT,
	@quantity INT 
AS
BEGIN

	DECLARE @promotion FLOAT = 0
	SET @promotion = (SELECT value 
					  FROM dbo.PromotionItem 
					  JOIN dbo.Promotion ON Promotion.id = PromotionItem.idPromo
					  WHERE idProduct = @productId)

	IF(@promotion IS NULL)SET  @promotion = 0
	INSERT dbo.InvoiceItem
	        ( orderId ,
	          productId ,
	          colorId ,
	          sizeId ,
	          unitPrice ,
	          quantity
	        )
	VALUES  ( @invoiceId , -- orderId - int
	          @productId , -- productId - int
	          @colorId , -- colorId - int
	          @sizeId , -- sizeId - int
	           (SELECT (price - (price * @promotion))
			   FROM dbo.Product 
			   WHERE id = @productID)  , -- unitPrice - float
	          @quantity  -- quantity - int
	        )
END



GO
/****** Object:  StoredProcedure [dbo].[SP_AddQuestion]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddQuestion]
	@accountID INT,
	@question NVARCHAR(1000),
	@parentQuestionID int,
	@productID INT
AS
BEGIN
	INSERT INTO 
	Question (accountID, question, parentQuestionID, productID)
	VALUES   (@accountID, @question, @parentQuestionID, @productID)

	SELECT SCOPE_IDENTITY() 
end



GO
/****** Object:  StoredProcedure [dbo].[SP_AddRating]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddRating]
	@accountId INT,
	@rating INT,
	@comment NVARCHAR(1000),
	@productID INT,
	@orderId INT,
	@colorId int,
	@sizeId int,
	@imageUrl1 NVARCHAR(200),
	@imageUrl2 NVARCHAR(200),
	@imageUrl3 NVARCHAR(200),
	@videoUrl NVARCHAR(200),
	@parentId int
AS
BEGIN
	INSERT INTO 
	Rating(accountID, rating, comment, productID, orderId,sizeId, colorId, imageUrl1, imageUrl2,imageUrl3, videoUrl, parentId) 
	VALUES(@accountId,@rating,@comment,@productID,@orderId, @sizeId, @colorId, @imageUrl1,@imageUrl2, @imageUrl3,@videoUrl,  @parentId)
	SELECT SCOPE_IDENTITY() 
end



GO
/****** Object:  StoredProcedure [dbo].[SP_AdminLogin]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AdminLogin]
	@userName NVARCHAR(50),
	@password NVARCHAR(50)
AS
BEGIN
	DECLARE @isAccuracy INT
	SET @isAccuracy = (SELECT isAccuracy FROM dbo.Account WHERE Username = @userName OR Email = @userName)
	IF NOT EXISTS (SELECT * FROM dbo.Account a WHERE (Username = @userName OR Email = @userName) )
		SELECT -1 AS result --sai userName
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE (Username = @userName OR Email = @userName) AND Password !=  @password AND active = 1 AND roleId <> 3)
		SELECT -2  AS result--đúng userName, sai password
	ELSE IF (@isAccuracy <> 1) 
		SELECT -3  AS result--tài khoản chưa được xác thực
	ELSE
	BEGIN		-- DDanwg nhap thanh cong
		IF EXISTS (SELECT e.phone FROM Account a JOIN Employee e ON a.id = e.idAccount  WHERE (Username = @userName OR Email = @userName ))
		BEGIN
			SELECT Account.Id AS result, Account.roleId,Role.roleName, email, username, Employee.firstName+ ' '+Employee.lastName AS name, dbo.Employee.avatar, Employee.id 
			FROM dbo.Account 
			JOIN dbo.Employee ON Employee.idAccount = Account.id
			JOIN role ON Role.roleId = Account.roleId
			WHERE (Username = @userName OR Email = @userName )
        END
		ELSE 
		BEGIN
			SELECT -1 AS result
		END
	END
	
END 



GO
/****** Object:  StoredProcedure [dbo].[SP_AuthAccount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AuthAccount]
	@email NVARCHAR(50)
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM dbo.Account a WHERE a.email = @email)
		SELECT -1 AS result --  email không tồn tại trong hệ thống
	ELSE 
	BEGIN
		UPDATE dbo.Account SET isAccuracy = 1 WHERE email = @email
		SELECT 1
	END
	
END 




GO
/****** Object:  StoredProcedure [dbo].[SP_CheckFavoriteProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_CheckFavoriteProduct]
	@accountId INT,
	@productId INT
AS
BEGIN
	-- Nếu sản phẩm đã tồn tại trong danh sách sản phẩn yêu thích  --> xóa sản phẩm khỏi danh sách
	IF EXISTS (SELECT * FROM dbo.FavoriteProduct WHERE idAccount = @accountId AND idProduct = @productId)
	BEGIN
		DELETE dbo.FavoriteProduct WHERE idAccount = @accountId AND idProduct = @productId
		SELECT 0 -- dislike product
	END

	ELSE IF NOT EXISTS (SELECT * FROM dbo.Product WHERE id= @productId)
	BEGIN
		SELECT -2 -- Sản phẩm không tồn tại trong danh sách
	END

	ELSE -- Chưa tồn tại thì thêm sản phẩm vào danh sách sản phẩm yêu thích
	BEGIN
		INSERT dbo.FavoriteProduct( idProduct, idAccount )
		VALUES  ( @productId, @accountId )
		SELECT 1 -- like product
	END
END





GO
/****** Object:  StoredProcedure [dbo].[SP_CheckInvoiceExist]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_CheckInvoiceExist]
	@inovoiceId INT
AS
BEGIN
	IF EXISTS (SELECT * FROM dbo.Invoice WHERE id = @inovoiceId)
		SELECT 1
	ELSE 
		SELECT 0
end



GO
/****** Object:  StoredProcedure [dbo].[SP_CheckRating]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_CheckRating]
	@accId int,
	@productID int, 
	@colorId int,
	@sizeId int, 
	@orderId int
AS
BEGIN
	IF EXISTS (SELECT * FROM Rating where accountID = @accId and productID = @productID and colorId = @colorId AND sizeId = @sizeId and orderId = @orderId)
		SELECT ratingID as 'result' FROM Rating where accountID = @accId and productID = @productID and colorId = @colorId AND sizeId = @sizeId  and orderId = @orderId-- đã đánh giá cho sản phẩm
	else if EXISTS(SELECT * from InvoiceItem 
					join Invoice on id = orderId
				    where productId =  @productID and userID = @accId )
		SELECT '0'  as 'result' -- được đánh giá
	ELSE		
		SELECT '-2'  as 'result' -- lôi gì đó
END 




GO
/****** Object:  StoredProcedure [dbo].[SP_CheckUserExist]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE
 PROCEDURE [dbo].[SP_CheckUserExist]
	@userID int
AS
BEGIN
	IF EXISTS (SELECT * FROM dbo.Account where id = @userID AND active =1)
		SELECT 1 -- tai khoan ton tai trong he thong
	ELSE		
		SELECT 0
END 




GO
/****** Object:  StoredProcedure [dbo].[SP_DelAddress]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_DelAddress]
	@addressId INT
AS
BEGIN
	DELETE dbo.Address WHERE id = @addressId
	IF EXISTS (SELECT *FROM dbo.Address WHERE id = @addressId)
		SELECT -1
	ELSE
        SELECT 1
END



GO
/****** Object:  StoredProcedure [dbo].[SP_DelEmployee]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_DelEmployee]
	@employeeId INT
AS
BEGIN
	DECLARE @accountId INT
    SET @accountId = (SELECT idAccount FROM dbo.Employee WHERE id = @employeeId)
	IF EXISTS (SELECT * FROM dbo.ImportCoupon WHERE employeeId = @employeeId)
	BEGIN
		UPDATE dbo.Account SET active = 0 WHERE id = @accountId
		UPDATE dbo.Employee SET active = 0 WHERE id = @employeeId
	END
	ELSE
	BEGIN
		DELETE dbo.Employee WHERE id = @employeeId
		DELETE dbo.Account WHERE id = @accountId
	end
end


GO
/****** Object:  StoredProcedure [dbo].[SP_DelQuestion]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_DelQuestion]
	@questionId INT,
	@isSubQuestion INT
AS
BEGIN
	IF(@isSubQuestion=0)
	BEGIN
		DELETE Question WHERE questionID = @questionId
	END
	ELSE
	BEGIN
		DELETE Question WHERE parentQuestionID = @questionId
		DELETE Question WHERE questionID = @questionId
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_DelRating]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_DelRating]
	@ratingId INT
AS
BEGIN
	DELETE Rating WHERE ratingID = @ratingID
	DELETE Rating WHERE parentId = @ratingID
	IF EXISTS (SELECT * FROM Rating r WHERE r.ratingID = @ratingID)
		SELECT 1
	ELSE SELECT 0
end



GO
/****** Object:  StoredProcedure [dbo].[SP_EditAddress]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_EditAddress]
	@addressId INT,
	@province NVARCHAR(200),
	@district NVARCHAR(200),
	@wards NVARCHAR(200),
	@street NVARCHAR(200),
	@name NVARCHAR(50),
	@phone NVARCHAR(20), 
	@isDefault INT,
	@accountId int
AS
BEGIN
	IF(@isDefault =0)
		UPDATE dbo.Address SET 
			province = @province,
			district = @district,
			wards = @wards,
			street= @street,
			name = @name,
			phone = @phone,
			active =1,
			isDefault =0
			WHERE id = @addressId
	ELSE
    BEGIN
		UPDATE dbo.Address SET 
			province = @province,
			district = @district,
			wards = @wards,
			street= @street,
			name = @name,
			phone = @phone,
			active =1,
			isDefault =1
			WHERE id = @addressId

		UPDATE dbo.Address SET isDefault = 0 WHERE  id != @addressId AND accountId = @accountId
	END

END



GO
/****** Object:  StoredProcedure [dbo].[SP_GetAccEmployeeInfo]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetAccEmployeeInfo]
	@accId int
AS
BEGIN
	SELECT id, email, roleId, password, username
	FROM dbo.Account
	WHERE id = @accId
end


GO
/****** Object:  StoredProcedure [dbo].[SP_GetAllAcc]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetAllAcc]
	@accId int
AS
BEGIN
	SELECT *
	FROM dbo.Account
	WHERE id <> @accId AND roleId <> 3
end


GO
/****** Object:  StoredProcedure [dbo].[SP_GetAllAddress]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetAllAddress]
	@userId INT
AS
BEGIN
	SELECT id, accountId, province, district, wards, street, name, phone, isDefault 
	FROM dbo.Address 
	WHERE accountId = @userId AND active =1
	ORDER BY isDefault DESC
END



GO
/****** Object:  StoredProcedure [dbo].[SP_GetAllEmployee]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetAllEmployee]
	@accId INT,
	@strSearch NVARCHAR(50)
AS
BEGIN
	IF(@strSearch IS NULL)
	BEGIN
		SELECT id, lastName, firstName, phone, address,birthday, avatar, dateBegin,dateEnd, isWorking, idAccount
		FROM dbo.Employee
		WHERE idAccount <> @accId AND active =1 
	END
	ELSE
    BEGIN
		SET @strSearch ='%'+@strSearch+'%'
		SELECT id, firstName, lastName, phone, address,birthday, avatar, dateBegin,dateEnd, isWorking, idAccount
		FROM dbo.Employee
		WHERE idAccount <> @accId AND( LOWER(lastName) LIKE LOWER(@strSearch) 
									OR LOWER(firstName) LIKE LOWER(@strSearch)
									OR LOWER(lastName+' '+firstName) LIKE LOWER(@strSearch) )
									AND active =1 
	END
end


GO
/****** Object:  StoredProcedure [dbo].[SP_GetAllInvoice]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetAllInvoice]
	@statusId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT
AS
BEGIN
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize
	IF(@statusId>0)
	BEGIN
		select id, updateDate, buyDate, name, phone, address, deliveryDate, (SELECT SUM(unitPrice) FROM dbo.InvoiceItem WHERE orderId = id) AS price, statusOrderId, isPaid, payment,
		CASE WHEN statusOrderId = 1 THEN N'Đã tiếp nhận' 
			 WHEN statusOrderId = 2 then N'Đang giao' 
			 WHEN statusOrderId = 3 then N'Đã giao' 
			 WHEN statusOrderId = 2 then N'Đã hủy' 
			 ELSE N'Đã hủy'
			 END AS statusInvoice 
		FROM dbo.Invoice
		LEFT OUTER JOIN dbo.InvoiceItem ON InvoiceItem.orderId = Invoice.id
		WHERE  userID = @AccountID AND statusOrderId = @statusId
		GROUP BY id, updateDate,buyDate, name, phone, ADDRESS, deliveryDate, isPaid, payment,statusOrderId
		ORDER by buyDate DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
	
	END
    ELSE 
	BEGIN
		select id, updateDate, buyDate, name, phone, address, deliveryDate, (SELECT SUM(unitPrice) FROM dbo.InvoiceItem WHERE orderId = id) AS price, statusOrderId, isPaid, payment,
		CASE WHEN statusOrderId = 1 THEN N'Đã tiếp nhận' 
			 WHEN statusOrderId = 2 then N'Đang giao' 
			 WHEN statusOrderId = 3 then N'Đã giao' 
			 WHEN statusOrderId = 2 then N'Đã hủy' 
			 ELSE N'Đã hủy'
			 END AS statusInvoice 
		FROM dbo.Invoice
		LEFT OUTER JOIN dbo.InvoiceItem ON InvoiceItem.orderId = Invoice.id
		WHERE  userID = @AccountID
		GROUP BY id, updateDate,buyDate, name, phone, ADDRESS, deliveryDate, isPaid, payment, statusOrderId
		ORDER by buyDate DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetAllInvoiceCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetAllInvoiceCount]
	@statusId INT,
	@AccountID INT
AS
BEGIN
	IF(@statusId>0)
	BEGIN
		select COUNT(id)
		FROM dbo.Invoice
		WHERE  userID = @AccountID AND statusOrderId = @statusId
	END
    ELSE 
	BEGIN
		select COUNT(id)
		FROM dbo.Invoice
		WHERE  userID = @AccountID
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetColorsOfProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetColorsOfProduct]
	@productId int
AS
BEGIN
	SELECT  id, colorName, colorHex
	FROM dbo.ProductSizeColor 
	JOIN dbo.Color ON Color.id = ProductSizeColor.colorID
	WHERE productID =@productId AND Color.active = 1 AND ProductSizeColor.active =1
	GROUP BY id, colorName, colorHex

END





GO
/****** Object:  StoredProcedure [dbo].[SP_GetEmail]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetEmail]
	@userId INT
AS
BEGIN
	SELECT email FROM dbo.Account WHERE id = @userId
END





GO
/****** Object:  StoredProcedure [dbo].[SP_GetFavoriteProducts]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetFavoriteProducts]
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT
AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value,type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id

	IF(@AccountID >= 0)
	BEGIN
		select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
		CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion,promo.type AS typePromotion
		from dbo.Product AS pro
		JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
		LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
		WHERE pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
    END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetFavoriteProductsCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetFavoriteProductsCount]
	@accountId INT
AS
begin
	select COUNT(pro.id)
	from dbo.Product AS pro
	JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
	WHERE  pro.active =1 
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetInvoice]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetInvoice]
	@statusOrderId INT,
	@beginDate NVARCHAR(20),
	@endDate NVARCHAR(20)
AS
BEGIN

	IF(@statusOrderId =0)
	BEGIN
		SELECT COUNT(i.id) AS totalInvoice ,SUM (ii.unitPrice*ii.quantity) AS totalPrice, SUM(ii.quantity)AS totalProduct
		FROM   Invoice i
		JOIN InvoiceItem ii ON i.id = ii.orderId
		WHERE buyDate BETWEEN (SELECT CONVERT(DATE, @beginDate, 120)) AND (SELECT CONVERT(DATE, @endDate, 120)) 

	END
	ELSE
	BEGIN
		SELECT COUNT(i.id) AS totalInvoice ,SUM (ii.unitPrice*ii.quantity) AS totalPrice, SUM(ii.quantity)AS totalProduct
		FROM   Invoice i
		JOIN InvoiceItem ii ON i.id = ii.orderId
		WHERE (statusOrderId = @statusOrderId ) AND  buyDate BETWEEN (SELECT CONVERT(DATE, @beginDate, 120)) AND (SELECT CONVERT(DATE, @endDate, 120)) 
	END
END



GO
/****** Object:  StoredProcedure [dbo].[SP_GetInvoiceDetail]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetInvoiceDetail]
	@inovoiceId INT
AS
BEGIN
	select id, updateDate, buyDate, name, phone, address,note,  deliveryDate, (SELECT SUM(unitPrice) FROM dbo.InvoiceItem WHERE orderId = id) AS price, statusOrderId,isPaid, payment,
	CASE WHEN statusOrderId = 1 THEN N'Đã tiếp nhận' 
			WHEN statusOrderId = 2 then N'Đang giao' 
			WHEN statusOrderId = 3 then N'Đã giao' 
			WHEN statusOrderId = 2 then N'Đã hủy' 
			ELSE N'Đã hủy'
			END AS statusInvoice 
	FROM dbo.Invoice
	JOIN dbo.InvoiceItem ON InvoiceItem.orderId = Invoice.id
	WHERE  id = @inovoiceId
	GROUP BY id, updateDate,buyDate, name, phone, ADDRESS, deliveryDate, statusOrderId,note,isPaid, payment
end



GO
/****** Object:  StoredProcedure [dbo].[Sp_GetPriceInvoice]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Sp_GetPriceInvoice]
	@orderID INT
AS
BEGIN
	SELECT SUM(ii.unitPrice) 
	FROM InvoiceItem ii
	WHERE ii.orderId = @orderID


END



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductColorSize]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductColorSize]
	@categoryId INT,
	@providerId INT 
AS
BEGIN
	SELECT productID, dbo.Product.title, sizeId, dbo.Size.sizeName, colorID, dbo.Color.colorName, quantity, ProductSizeColor.active, colorHex, dbo.Category.name, dbo.Provider.brandName
	FROM dbo.ProductSizeColor
	JOIN dbo.Product ON Product.id = ProductSizeColor.productID
	JOIN dbo.Color ON Color.id = ProductSizeColor.colorID
	JOIN dbo.Size ON Size.id = ProductSizeColor.sizeId
	JOIN dbo.Category ON Category.id = Product.categoryID
	JOIN dbo.Provider ON Provider.id = Product.providerId
	WHERE dbo.Product.categoryID = @categoryId AND dbo.Product.providerId = @providerId
end


GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductInfoDetail]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductInfoDetail]
	@productID INT,
	@AccountID int
AS
	SET FMTONLY OFF
	SELECT idProduct, idPromo,value,type, beginDate, endDate INTO #Promo 
	FROM dbo.PromotionItem 
	LEFT OUTER JOIN dbo.Promotion ON  PromotionItem.idPromo = Promotion.id

	IF(@accountId IS NULL)
	BEGIN
		SELECT pro.id, pro.title, pro.detail, pro.price, pro.categoryID, pro.sold, pro.rating, pro.active, pro.providerId, pro.thumnail, pro.isNew, pro.addDate, 
		0 AS isLike,
		CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			FROM product AS pro
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.id = @productID AND pro.active =1
	END
	ELSE
	BEGIN
		SELECT pro.id, pro.title, pro.detail, pro.price, pro.categoryID, pro.sold, pro.rating, pro.active, pro.providerId, pro.thumnail, pro.isNew, pro.addDate, 
		CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike,
		CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			FROM product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.id = @productID AND pro.active =1

	END
	



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductInvoice]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductInvoice]
	@inovoiceId INT
AS
BEGIN
	select @inovoiceId as 'invoiceId', product.id, title, s.sizeName, s.id as 'sizeId', c.colorName, c.id as 'colorId', c.colorHex, detail, dbo.InvoiceItem.unitPrice, InvoiceItem.quantity, thumnail
	FROM dbo.Product
	JOIN dbo.InvoiceItem ON InvoiceItem.productId = Product.id 
	JOIN Size s ON InvoiceItem.sizeId = s.id
	JOIN Color c ON c.id = InvoiceItem.colorId
	WHERE dbo.InvoiceItem.orderId = @inovoiceId
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductOfCategory]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductOfCategory]
	@categoryId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT,
	@ListProviders AS dbo.ProviderIds READONLY,
	@ListCategories AS dbo.CategoryIds READONLY,
	@sortBy int

AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value, dbo.Promotion.type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id

	IF(@AccountID >= 0)
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
	ELSE 
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
end


GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductOfCategoryCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductOfCategoryCount]
	@categoryId INT
AS
begin
	select COUNT(pro.id)
	from dbo.Product AS pro
	WHERE pro.categoryID = @categoryId AND pro.active =1 
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductOfCategoryFilter]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductOfCategoryFilter]
	@categoryId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT,
	@ListProviders AS dbo.ProviderIds READONLY,
	@ListCategories AS dbo.CategoryIds READONLY,
	@sortBy int

AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value, dbo.Promotion.type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id

	IF(@AccountID >= 0)
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
	ELSE 
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.categoryID = @categoryId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
end


GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductSearchFilter]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductSearchFilter]
	@typeId INT,
	@keySearch NVARCHAR(200),
	@typeFilter INT,
	@categoryId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT
AS
BEGIN
	
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value, dbo.Promotion.type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id
	IF(@typeId = 1) -- category
	BEGIN
		IF(@keySearch IS NOT NULL)
		BEGIN
			IF(@typeFilter is NULL)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter =1)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter =2)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 3)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END

			ELSE IF(@typeFilter = 4)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
		END
		ELSE
		BEGIN
			IF(@typeFilter = 1)-- filter tang dan
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 
					ORDER BY pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1
					ORDER by pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 2)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 
					ORDER BY pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1 
					ORDER by pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 3)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 
					ORDER BY pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1 
					ORDER by pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END

			ELSE IF(@typeFilter = 4)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active =1 
					ORDER BY pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.categoryID = @categoryId AND pro.active = 1
					ORDER by pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
		END
    END -- provider 
	ELSE
	BEGIN
		IF(@keySearch IS not NULL)
		BEGIN
			IF(@typeFilter is NULL)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 1)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 2)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 3) -- newest by date
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END

			ELSE IF(@typeFilter = 4)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER BY pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
					ORDER by pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
		END
		ELSE
		BEGIN
			IF(@typeFilter = 1)-- filter tang dan
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 
					ORDER BY pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1
					ORDER by pro.price ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 2)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 
					ORDER BY pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 
					ORDER by pro.price DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
			ELSE IF(@typeFilter = 3) -- newest by date
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1
					ORDER BY pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 
					ORDER by pro.addDate DESC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END

			ELSE IF(@typeFilter = 4)
			BEGIN
				IF(@AccountID >= 0)
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
					LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active =1 
					ORDER BY pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
				ELSE 
				BEGIN
					select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
					CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
					from dbo.Product AS pro
					LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
					WHERE pro.providerId = @categoryId AND pro.active = 1 
					ORDER by pro.addDate ASC, id offset @offsetcount rows fetch Next @PageSize rows ONLY
				END
			END
		END
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductSearchFilterCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductSearchFilterCount]
	@typeId INT,
	@keySearch NVARCHAR(200),
	@categoryId INT
AS
BEGIN
	IF(@typeId = 1)
	BEGIN
		IF(@keySearch IS NOT NULL)
		BEGIN
		
			SELECT COUNT(pro.id)
			FROM dbo.Product AS pro
			WHERE pro.categoryID = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
		END
		ELSE
		BEGIN
			SELECT COUNT(pro.id) 
			FROM dbo.Product AS pro
			WHERE pro.categoryID = @categoryId AND pro.active = 1 
		END
	END
	ELSE
    BEGIN
		IF(@keySearch IS NOT NULL)
		BEGIN
		
			SELECT COUNT(pro.id)
			FROM dbo.Product AS pro
			WHERE pro.providerId = @categoryId AND pro.active =1 AND LOWER(pro.title) LIKE LOWER(@keySearch)
		END
		ELSE
		BEGIN
			SELECT COUNT(pro.id) 
			FROM dbo.Product AS pro
			WHERE pro.providerId = @categoryId AND pro.active = 1 
		END
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductsOfProvider]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductsOfProvider]
	@providerId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT,
	@ListProviders AS dbo.ProviderIds READONLY,
	@ListCategories AS dbo.CategoryIds READONLY,
	@sortBy int
AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value, dbo.Promotion.type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id

	IF(@AccountID >= 0)
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
	ELSE 
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductsOfProviderCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductsOfProviderCount]
	@provderId INT
AS
begin
	select COUNT(pro.id)
	from dbo.Product AS pro
	WHERE pro.providerId = @provderId AND pro.active =1 
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductsOfProviderFilter]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductsOfProviderFilter]
	@providerId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT,
	@ListProviders AS dbo.ProviderIds READONLY,
	@ListCategories AS dbo.CategoryIds READONLY,
	@sortBy int
AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value, dbo.Promotion.type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id

	IF(@AccountID >= 0)
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
			LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
	ELSE 
	BEGIN
		IF(@sortBy IS null)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 1)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 2)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 3)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
		ELSE IF(@sortBy = 4)
		BEGIN
			select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
			CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
			from dbo.Product AS pro
			LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
			WHERE pro.providerId = @providerId AND pro.active =1 
			ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
		END 
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProviderDetail]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProviderDetail]
	@providerId INT
AS
BEGIN
	SELECT id, brandName,infomation,  phone, address, imageUrl FROM dbo.Provider WHERE id = @providerId 
END





GO
/****** Object:  StoredProcedure [dbo].[SP_GetProviders]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProviders]
AS
BEGIN
	SELECT id, brandName, imageUrl FROM dbo.Provider WHERE active = 1
END





GO
/****** Object:  StoredProcedure [dbo].[SP_GetQuestion]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetQuestion]
	@currentPage  int,
	@PageSize  INT,
	@productId int
AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize
	SELECT q.questionID, q.accountID, a.username,a.roleId, q.question, q.dateComment, q.dateEdit, q.parentQuestionID
	FROM Question q
	INNER JOIN Account a ON q.accountID = a.id 
	WHERE q.productID =@productId AND q.parentQuestionID is NULL
	ORDER BY q.dateComment DESC, q.dateEdit DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetQuestionCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetQuestionCount]
	@productId int
AS
BEGIN
	
	SELECT COUNT(q.questionID)
	FROM Question q
	WHERE q.productID =@productId AND q.parentQuestionID is NULL
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetRatingDetail]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetRatingDetail]
	@ratingId int
AS
BEGIN
	SELECT r.ratingID, r.accountID,a.username, r.rating, r.comment, r.dateRating, r.dateEdit, r.productID, c.colorName, s.sizeName, r.imageUrl1, r.imageUrl2, r.imageUrl3, r.videoUrl, r.parentId
	FROM Rating r
	INNER JOIN Account a ON r.accountID = a.id
	 JOIN Color c on r.colorId = c.id
	 JOIN Size s on r.sizeId = s.id
	WHERE r.ratingID = @ratingId
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetRatingProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetRatingProduct]
	@productId int
AS
BEGIN
	SELECT  AVG( Cast(r.rating as FLOAT)) 
	FROM Rating r
	WHERE r.productId = @productId AND r.parentId is NULL AND r.rating IS NOT NULL

end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetRatings]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetRatings]
	@currentPage  int,
	@PageSize  INT,
	@productId int
AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize
	SELECT r.ratingID, r.accountID,a.username, r.rating, r.comment, r.dateRating, r.dateEdit, r.productID, c.colorName, s.sizeName, r.imageUrl1, r.imageUrl2, r.imageUrl3, r.videoUrl, r.parentId
	FROM Rating r
	INNER JOIN Account a ON r.accountID = a.id
	 JOIN Color c on r.colorId = c.id
	 JOIN Size s on r.sizeId = s.id
	WHERE r.productId =@productId AND r.parentId is NULL AND r.rating IS NOT NULL
	ORDER BY r.dateRating DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetRatingsCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetRatingsCount]
	@productId int
AS
BEGIN
	SELECT COUNT(r.ratingID)
	FROM Rating r
	WHERE r.productId = @productId AND r.parentId is NULL AND r.rating IS NOT NULL
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetSizesColorsOfProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetSizesColorsOfProduct]
	@productId int
AS
BEGIN
	SELECT  ProductSizeColor.productID, ProductSizeColor.sizeId, sizeName, ProductSizeColor.colorID, dbo.Color.colorName, ProductSizeColor.quantity
	FROM dbo.ProductSizeColor 
	JOIN dbo.Size ON Size.id = ProductSizeColor.sizeId
	JOIN dbo.Color ON Color.id = ProductSizeColor.colorID
	WHERE productID = @productId AND ProductSizeColor.active =1
END





GO
/****** Object:  StoredProcedure [dbo].[SP_GetSizesOfProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetSizesOfProduct]
	@productId int
AS
BEGIN
	SELECT  id, sizeName, description
	FROM dbo.ProductSizeColor 
	JOIN dbo.Size ON Size.id = ProductSizeColor.sizeId
	WHERE productID = @productId AND dbo.Size.active = 1 AND ProductSizeColor.active =1
	GROUP BY  id, sizeName, description
END





GO
/****** Object:  StoredProcedure [dbo].[SP_GetSubQuestion]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetSubQuestion]
	@questionID int
AS
BEGIN
	SELECT q.questionID, q.accountID, a.username,a.roleId, q.question, q.dateComment, q.dateEdit, q.parentQuestionID
	FROM Question q
	INNER JOIN Account a ON q.accountID = a.id
	WHERE q.parentQuestionID = @questionID 
end



GO
/****** Object:  StoredProcedure [dbo].[SP_GetSubRating]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetSubRating]
	@ratingId int
AS
BEGIN
	SELECT r.ratingID, r.accountID,a.username, r.rating, r.comment, r.dateRating, r.dateEdit, r.productID, r.imageUrl1, r.imageUrl2, r.imageUrl3, r.videoUrl, r.parentId
	FROM Rating r
	INNER JOIN Account a ON r.accountID = a.id
	WHERE r.parentId = @ratingId 
end



GO
/****** Object:  StoredProcedure [dbo].[SP_ImagesOfProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ImagesOfProduct]
	@productID INT
AS
	SELECT imageUrl FROM dbo.Image WHERE productId = @productID




GO
/****** Object:  StoredProcedure [dbo].[SP_InventoryProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_InventoryProduct]
	@categoryId INT,
	@providerID INT 
AS
BEGIN
	IF(@categoryId>0 AND  @providerId>0)
	BEGIN
		SELECT psc.productID, p.title, c.colorName, s.sizeName,  psc.quantity
		FROM ProductSizeColor psc
		JOIN Product p ON psc.productID = p.id
		JOIN Color c ON psc.colorID = c.id
		JOIN Size s ON psc.sizeId = s.id
		WHERE p.categoryID = @categoryId AND  p.providerId = @providerID
		ORDER BY psc.productID
	END
	ELSE IF ((@categoryId>0 AND  @providerId<=0) OR (@categoryId IS NOT NULL AND  @providerId IS NULL))
	BEGIN
		SELECT psc.productID, p.title, c.colorName, s.sizeName,  psc.quantity
		FROM ProductSizeColor psc
		JOIN Product p ON psc.productID = p.id
		JOIN Color c ON psc.colorID = c.id
		JOIN Size s ON psc.sizeId = s.id
		WHERE p.categoryID = @categoryId 
		ORDER BY psc.productID
	END
	ELSE IF ((@categoryId<=0 AND  @providerId>0) OR (@categoryId IS  NULL AND  @providerId IS NOT NULL))
	BEGIN
		SELECT psc.productID, p.title, c.colorName, s.sizeName,  psc.quantity
		FROM ProductSizeColor psc
		JOIN Product p ON psc.productID = p.id
		JOIN Color c ON psc.colorID = c.id
		JOIN Size s ON psc.sizeId = s.id
		WHERE p.providerId = @providerID
		ORDER BY psc.productID
	END
	ELSE IF ((@categoryId<=0 AND  @providerId<=0) OR (@categoryId IS  NULL AND  @providerId IS  NULL))
	BEGIN
		SELECT psc.productID, p.title, c.colorName, s.sizeName,  psc.quantity
		FROM ProductSizeColor psc
		JOIN Product p ON psc.productID = p.id
		JOIN Color c ON psc.colorID = c.id
		JOIN Size s ON psc.sizeId = s.id
		ORDER BY psc.productID
	end
END





GO
/****** Object:  StoredProcedure [dbo].[SP_Login]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_Login]
	@userName NVARCHAR(50),
	@password NVARCHAR(50)
AS
BEGIN
	DECLARE @isAccuracy INT
	SET @isAccuracy = (SELECT isAccuracy FROM dbo.Account WHERE Username = @userName OR Email = @userName)
	IF NOT EXISTS (SELECT * FROM dbo.Account a WHERE Username = @userName OR Email = @userName)
		SELECT -1 AS result --sai userName
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE (Username = @userName OR Email = @userName) AND Password != @password AND active = 1 AND roleId = 3)
		SELECT -2  AS result--đúng userName, sai password
	ELSE IF (@isAccuracy <> 1) 
		SELECT -3  AS result--tài khoản chưa được xác thực
	ELSE		
		SELECT Id AS result --đăng nhập thành công
		FROM dbo.Account 
		WHERE (Username = @userName OR Email = @userName )
END 




GO
/****** Object:  StoredProcedure [dbo].[SP_Register]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_Register]
	@name nvarchar(100),
	@email nvarchar(200),
	@phone nvarchar(15),
	@roleId INT,
	@password nvarchar(50),
	@username nvarchar(50),
	@imageUrl nvarchar(150),
	@active INT 
AS
BEGIN

	IF EXISTS (SELECT * FROM dbo.Account WHERE Username = @userName)
		SELECT -1 AS result --trùng userName
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE Email = @email)
		SELECT -2  --trùng email
	ELSE IF EXISTS (SELECT * FROM dbo.Customer WHERE Phone = @phone)
		SELECT -3 --trùng phone
	ELSE
	BEGIN
	DECLARE @accountId int
		INSERT INTO dbo.Account(email, roleId, password, username,isAccuracy, active)
		 VALUES (@email,@roleId, @password, @username,0, @active )

		 SET @accountId = SCOPE_IDENTITY()
		 INSERT INTO customer(name,phone, imageUrl, idAccount)
				VALUES		(@name, @phone, NULL,@accountId)
		SELECT @accountId 
	END 
END



GO
/****** Object:  StoredProcedure [dbo].[SP_ReportGetInvoiceDetail]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ReportGetInvoiceDetail]
	@invoiceId int
AS
BEGIN
	SELECT Row_number() over(order by ii.productId)as STT, ii.productId AS ProductId, P.title AS ProductName, c.colorName AS Color, s.sizeName AS Size, ii.quantity AS Quantity, format(ii.unitPrice, 'N0') AS Price
	FROM InvoiceItem ii 
	JOIN Product p ON ii.productId = p.id
	JOIN Color c ON ii.colorId = c.id
	JOIN Size s ON s.id = ii.sizeId
	WHERE ii.orderId = @invoiceId
END



GO
/****** Object:  StoredProcedure [dbo].[SP_SearchFilter]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_SearchFilter]
	@keySearch NVARCHAR(200),
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT,
	@ListProviders AS dbo.ProviderIds READONLY,
	@ListCategories AS dbo.CategoryIds READONLY,
	@sortBy int
AS
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value, dbo.Promotion.type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id

	IF(@AccountID >= 0)
	BEGIN
		IF(@keySearch IS NOT NULL)
		BEGIN
			IF(@sortBy IS null)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND  pro.active =1 
				ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 1)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 2)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 3)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 4)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
		END
		ELSE -- key search is empty
		BEGIN
			IF(@sortBy IS null)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE pro.active =1 
				ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 1)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE  pro.active =1 
				ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 2)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE  pro.active =1 
				ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 3)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE pro.active =1 
				ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 4)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
				LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE pro.active =1 
				ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
		END
	END
	ELSE -- khong co account --> is like = false
	BEGIN
		IF(@keySearch IS NOT NULL)
		BEGIN
			IF(@sortBy IS null)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 1)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 2)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 3)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 4)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND pro.active =1 
				ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
		END
		------------
		ELSE -- keysearch null
		BEGIN
			IF(@sortBy IS null)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE  pro.active =1 
				ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 1)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE pro.active =1 
				ORDER by ( pro.price) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 2)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE  pro.active =1 
				ORDER by ( pro.price) DESC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 3)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE pro.active =1 
				ORDER by ( pro.addDate) desc offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
			ELSE IF(@sortBy = 4)
			BEGIN
				select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
				CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
				from dbo.Product AS pro
				LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id AND GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate
				WHERE pro.active =1 
				ORDER by ( pro.addDate) ASC offset @offsetcount rows fetch Next @PageSize rows ONLY
			END 
		END 
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_SearchFilterCount]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_SearchFilterCount]
	@keySearch NVARCHAR(200)--,
	--@ListProviders AS dbo.ProviderIds READONLY,
	--@ListCategories AS dbo.CategoryIds READONLY
AS
BEGIN
	
	IF(@keySearch IS NOT NULL)
	BEGIN
		SELECT count (id)
		from dbo.Product AS pro
		WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND  pro.active =1 
	END
	ELSE -- key search is empty
	BEGIN
		SELECT count (id)
		from dbo.Product AS pro
		WHERE pro.active =1 
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_SearchFilterCounter]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_SearchFilterCounter]
	@keySearch NVARCHAR(200)--,
	--@ListProviders AS dbo.ProviderIds READONLY,
	--@ListCategories AS dbo.CategoryIds READONLY
AS
BEGIN
	
	IF(@keySearch IS NOT NULL)
	BEGIN
		SELECT count (id)
		from dbo.Product AS pro
		WHERE LOWER(pro.title) LIKE LOWER(@keySearch) AND  pro.active =1 
	END
	ELSE -- key search is empty
	BEGIN
		SELECT count (id)
		from dbo.Product AS pro
		WHERE pro.active =1 
	END
end



GO
/****** Object:  StoredProcedure [dbo].[SP_StatisticProduct]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_StatisticProduct]
	@type INT,
	@top INT,
	@categoryId int,
	@providerId INT,
	@dateBegin DATE,
	@dateEnd DATE
AS
BEGIN

	IF(@top IS NULL) SET @top = 50

	-- Bán chạy nhât trong khoảng thời gian - bắt buộc nhập ngày bắt đầu và ngày kết thúc
	IF(@type = 1)
	BEGIN
		SELECT TOP(@top) ii.productId, c1.colorName AS Color,s.sizeName AS Size , title, price, c.name AS Category, P.brandName AS Provider , addDate,  sum (ii.quantity) AS sold
		FROM InvoiceItem ii
		JOIN Product p1 ON ii.productId = p1.id
		JOIN Invoice i ON ii.orderId = i.id
		JOIN Category c ON p1.categoryID = c.id
		JOIN Provider p ON p1.providerId = p.id
		JOIN Color c1 ON ii.colorId = c1.id
		JOIN Size s ON ii.sizeId = s.id
		WHERE i.buyDate BETWEEN (SELECT CONVERT(DATE, @dateBegin, 120)) AND (SELECT CONVERT(DATE, @dateEnd, 120))
			  AND (@categoryId IS NULL OR p1.categoryID= @categoryId) AND (@providerId IS NULL OR p1.providerId= @providerId)
		GROUP BY ii.productId, c1.colorName ,s.sizeName , title, price, c.name , P.brandName, addDate
		ORDER BY sold DESC
		
	END
	-- sản phẩm bán được nhiều nhất
	ELSE IF(@type = 2)
	BEGIN
		SELECT TOP(@top) pr.id, title, price, c.name AS Category, P.brandName AS Provider , addDate, sold
		FROM dbo.Product pr
		JOIN Category c ON pr.categoryID = c.id
		JOIN Provider p ON pr.providerId = p.id
		WHERE pr.isNew = 1 AND pr.active = 1 AND (@categoryId IS NULL OR pr.categoryID= @categoryId) AND (@providerId IS NULL OR pr.providerId= @providerId)
		ORDER BY pr.sold  DESC
	END

	-- sản phẩm mới nhất
	ELSE IF(@type = 3)
	BEGIN
		SELECT TOP(@top) pr.id, title, price, c.name AS Category, P.brandName AS Provider , addDate, sold
		FROM dbo.Product pr
		JOIN Category c ON pr.categoryID = c.id
		JOIN Provider p ON pr.providerId = p.id
		WHERE pr.active = 1 AND (@categoryId IS NULL OR pr.categoryID= @categoryId) AND (@providerId IS NULL OR pr.providerId= @providerId)
		ORDER BY CONVERT(DateTime, pr.addDate,101)  DESC
	END
END



GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateAccInfo]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_UpdateAccInfo]
	@userId INT,
	@name NVARCHAR(50),
	@phone NVARCHAR(50),
	@email NVARCHAR(50)
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM dbo.Account WHERE Id = @userId )
		SELECT -1--tai khoan khong ton tai
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE Id != @userId AND email = @email)
		SELECT -2 -- trung email với tài khoản khác
	ELSE IF EXISTS (SELECT * FROM dbo.Customer WHERE Id != @userId AND phone = @phone)
		SELECT -3 -- trung phone với tài khoản khác
	ELSE
		BEGIN
			UPDATE dbo.account SET email = @email WHERE Id = @userId
			UPDATE customer SET name = @name, phone = @phone WHERE idAccount = @userId
			SELECT 1 --thành công
		END
END





GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateEmployee]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_UpdateEmployee]
	@employeeId INT,
	@firstname NVARCHAR(100),
	@lastname NVARCHAR(100),
	@phone NVARCHAR(100),
	@address NVARCHAR(200),
	@avatar NVARCHAR(MAX),
	@birthday NVARCHAR(10),
	@beginDate NVARCHAR(10),
	@endDate NVARCHAR(10),
	@isWorking INT,
	@email NVARCHAR(50),
	@roleId INT,
	@password NVARCHAR(100),
	@username NVARCHAR(100),
	@accountId int
AS
BEGIN
	IF EXISTS (SELECT * FROM dbo.Employee WHERE phone= @phone AND id <>@employeeId)
		SELECT -1, N'Số điện thoại đã được dùng cho tài khoản khác' AS message -- trung so dien thoai
	ELSE IF EXISTS  (SELECT * FROM dbo.Employee WHERE idAccount= @accountId AND id <>@employeeId)
		SELECT -2 , N'account id đã được dùng cho tài khoản khác' AS message-- trung tai khoan id
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE email= @email AND id <>@accountId)
		SELECT -3 , N'Email đã được dùng cho tài khoản khác' AS message--trung email
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE username = @username AND id <>@accountId)
		SELECT -4 ,  N'Username đã được dùng cho tài khoản khác' AS message--trung username
	ELSE
    BEGIN
		IF(@birthday ='null')set @birthday=NULL
		IF(@beginDate ='null') SET @beginDate=NULL
		IF(@endDate ='null') SET @endDate = null
    	UPDATE dbo.Employee SET firstName = @firstname,
								lastName = @lastname,
								phone = @phone,
								address = @address,
								birthday = (SELECT CONVERT(DATE, @birthday, 120)),
								dateBegin = (SELECT CONVERT(DATE, @beginDate, 120)),
								dateEnd = (SELECT CONVERT(DATE, @endDate, 120)),
								avatar = @avatar,
								isWorking = @isWorking
				WHERE id = @employeeId
		UPDATE dbo.Account SET email = @email,
							   roleId = @roleId,
							   password = @password,
							   username = @username
				WHERE id = @accountId
		SELECT 1, N'Cập nhật thông tin thành công' AS message
    END
end


GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateQuestion]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_UpdateQuestion]
	@questionID INT,
	@question NVARCHAR(1000)
AS
BEGIN
	UPDATE Question SET question = @question WHERE questionID= @questionID
end


GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateRating]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_UpdateRating]
	@ratingId INT,
	@rating INT,
	@comment NVARCHAR(1000),
	@imageUrl1 NVARCHAR(200),
	@imageUrl2 NVARCHAR(200),
	@imageUrl3 NVARCHAR(200),
	@videoUrl NVARCHAR(200),
	@parentId int
AS
BEGIN
	UPDATE Rating SET rating = @rating,
					  comment = @comment,
					  imageUrl1 = @imageUrl1,
					  imageUrl2 = @imageUrl2,
					  imageUrl3 = @imageUrl3,
					  videoUrl= @videoUrl,
					  dateEdit = GETDATE()
			WHERE ratingID = @ratingId
end



GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUserId]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoByUserId]
	@userId int
AS
BEGIN
	SELECT  dbo.Account.id, name, email, phone, roleId, password,username,imageUrl, active 
	FROM Account 
	JOIN Customer ON Customer.idAccount =  dbo.Account.id
	WHERE dbo.Account.id = @userId
END



GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUsername]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoByUsername]
	@username NVARCHAR(50)
AS
BEGIN
	SELECT  dbo.Account.id, name, email, phone, roleId, password,username,imageUrl, active 
	FROM Account 
	JOIN Customer ON Customer.idAccount =  dbo.Account.id
	WHERE dbo.Account.username = @username
END



GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoUserID]    Script Date: 2020-11-18 8:49:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoUserID]
	@userId int
AS
BEGIN
	SELECT  dbo.Account.id, name, email, phone, roleId, password,username,imageUrl, active
	FROM Account 
	JOIN Customer ON Customer.idAccount =  dbo.Account.id
	WHERE dbo.Account.id = @userId
END



GO
USE [master]
GO
ALTER DATABASE [ClothesManament] SET  READ_WRITE 
GO
