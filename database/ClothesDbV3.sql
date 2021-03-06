USE [ClothesManament]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
	[email] [nvarchar](200) NULL,
	[phone] [nvarchar](15) NULL,
	[roleId] [int] NULL,
	[password] [nvarchar](50) NULL,
	[username] [nvarchar](50) NULL,
	[imageUrl] [nvarchar](150) NULL,
	[employeeID] [int] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[detail] [nvarchar](200) NULL,
	[genderID] [int] NOT NULL,
	[imageUrl] [nvarchar](200) NULL,
	[thumnail] [nvarchar](200) NULL,
	[sex] [tinyint] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Color]    Script Date: 2020-07-21 6:30:17 PM ******/
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
/****** Object:  Table [dbo].[Employee]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [int] NOT NULL,
	[firstName] [nvarchar](50) NULL,
	[lastName] [nvarchar](50) NULL,
	[address] [nvarchar](200) NULL,
	[birthday] [date] NULL,
	[isWorking] [int] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FavoriteProduct]    Script Date: 2020-07-21 6:30:17 PM ******/
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
/****** Object:  Table [dbo].[Gender]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Gender](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Gender] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Image]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[imageUrl] [nvarchar](200) NULL,
	[productId] [int] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Image] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ImportCupon]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImportCupon](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [date] NULL,
	[employeeId] [int] NULL,
 CONSTRAINT [PK_ImportCupon] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[updateDate] [date] NULL,
	[buyDate] [date] NULL,
	[name] [nvarchar](50) NULL,
	[phone] [nvarchar](15) NULL,
	[email] [nvarchar](100) NULL,
	[address] [nvarchar](50) NULL,
	[note] [nvarchar](200) NULL,
	[userID] [int] NULL,
	[statusOrderId] [int] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InvoiceItem]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceItem](
	[orderId] [int] NOT NULL,
	[productId] [int] NOT NULL,
	[unitPrice] [float] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_OrderItem] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 2020-07-21 6:30:17 PM ******/
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
	[brandId] [int] NULL,
	[thumnail] [nvarchar](200) NULL,
	[isNew] [int] NULL,
	[addDate] [date] NULL,
	[sold] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProductSizeColor]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductSizeColor](
	[productID] [int] NOT NULL,
	[sizeId] [int] NOT NULL,
	[colorID] [int] NOT NULL,
	[quantity] [int] NULL,
	[imageUrl] [int] NULL,
 CONSTRAINT [PK_ProductSizeColor] PRIMARY KEY CLUSTERED 
(
	[productID] ASC,
	[sizeId] ASC,
	[colorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Promotion]    Script Date: 2020-07-21 6:30:17 PM ******/
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
/****** Object:  Table [dbo].[PromotionItem]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PromotionItem](
	[idProduct] [int] NOT NULL,
	[idPromo] [int] NOT NULL,
	[value] [float] NULL,
 CONSTRAINT [PK_PromotionItem] PRIMARY KEY CLUSTERED 
(
	[idProduct] ASC,
	[idPromo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Provider]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Provider](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[brandName] [nvarchar](50) NULL,
	[infomation] [nvarchar](max) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Brand] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [nvarchar](50) NULL,
	[image] [nvarchar](200) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Size]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[sizeName] [nvarchar](50) NULL,
	[categoryId] [int] NULL,
	[description] [nvarchar](200) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Size] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [name], [email], [phone], [roleId], [password], [username], [imageUrl], [employeeID], [active]) VALUES (1, N'Phạm Thi Thủy', N'phamthithuy5555@gmail.com', N'0373987563', 3, N'Thuy1111', N'phamthuy', N'avatar.png', NULL, 1)
INSERT [dbo].[Account] ([id], [name], [email], [phone], [roleId], [password], [username], [imageUrl], [employeeID], [active]) VALUES (16, N'Thuy pham', N'phamthuy@gmail.com', N'0308479554', 3, N'Thuy1234', N'Thuypham234', N'', NULL, 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (1, N'Áo thun ', N'aaaa', 1, NULL, NULL, NULL, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (2, N'Áo sơ mi ', N'aa', 1, NULL, NULL, NULL, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (3, N'Chân váy', N'hiohooh', 1, NULL, NULL, NULL, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (4, N'Quần jeans ', N'hehe', 1, NULL, NULL, NULL, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (6, N'Quần tây ', N'hihi', 1, NULL, NULL, NULL, 1)
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Color] ON 

INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (1, N'Trắng', N'#fff', 1)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (2, N'Đỏ', N'000', NULL)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (3, N'Đen', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Color] OFF
INSERT [dbo].[FavoriteProduct] ([idProduct], [idAccount]) VALUES (1, 1)
INSERT [dbo].[FavoriteProduct] ([idProduct], [idAccount]) VALUES (2, 1)
SET IDENTITY_INSERT [dbo].[Gender] ON 

INSERT [dbo].[Gender] ([id], [name], [active]) VALUES (1, N'Men', 1)
INSERT [dbo].[Gender] ([id], [name], [active]) VALUES (2, N'Women ', 1)
INSERT [dbo].[Gender] ([id], [name], [active]) VALUES (3, N'Kids', 1)
INSERT [dbo].[Gender] ([id], [name], [active]) VALUES (4, N'Unisex', 1)
SET IDENTITY_INSERT [dbo].[Gender] OFF
SET IDENTITY_INSERT [dbo].[Image] ON 

INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (1, N'cot_no_cham_bi_1_a4d881a1b9594357a33537b54039af3b_master.jpg', 1, 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (2, N'cot_no_cham_bi_1349f3bc927649989b24c7ebe10f634f_master.jpg', 1, 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (3, N'cot_no_cham_bi_2_d6ee7e17438842ccb977334053dc2106_master.jpg', 2, 1)
SET IDENTITY_INSERT [dbo].[Image] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [brandId], [thumnail], [isNew], [addDate], [sold]) VALUES (1, N'Sơ mi cột nơ chấm bi xanh', N'
Miêu tả 	Form suông, tay dài, lai ngang. Cột nơ ở cổ, dễ dàng điều chỉnh
Chất liệu	Cát Hàn, mềm mát, dày dặn, ít nhăn
Kích cỡ sản phẩm	Chiều dài áo 66cm, tay dài 52cm
Số đo người mẫu	1m65 - 50kg, mặc size S', 200000, 2, 5, 1, 1, N'1', NULL, NULL, NULL)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [brandId], [thumnail], [isNew], [addDate], [sold]) VALUES (2, N'Quần suông lưng cao tím', N'
Miêu tả 	Quần ống suông, lưng cao, dây kéo phía trước, có bản lưng (lưng rời), móc ẩn phía trong. Có xếp li và ủi li phía trước, túi xéo 2 bên, không có túi sau
Chất liệu	Tuyết Hàn, mượt, dày vừa phải, gần như không nhăn, co giãn nhẹ, đứng form
Kích cỡ sản phẩm	Dài 103cm bao gồm bản lưng 3cm
Số đo người mẫu	1m65-50kg, mặc size S', 150000, 2, 4, 1, 2, N'2', NULL, NULL, NULL)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [brandId], [thumnail], [isNew], [addDate], [sold]) VALUES (3, N'd', N'a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Promotion] ON 

INSERT [dbo].[Promotion] ([id], [description], [name], [beginDate], [endDate], [value], [imageUrl], [active], [type]) VALUES (2, N'Tưng bừn mua sắm lễ quốc khánh, giảm giá sốc', N'Quốc khánh săn sale', CAST(N'2020-07-10 00:00:00.000' AS DateTime), CAST(N'2020-09-05 00:00:00.000' AS DateTime), 0.3, NULL, 1, NULL)
SET IDENTITY_INSERT [dbo].[Promotion] OFF
SET IDENTITY_INSERT [dbo].[Provider] ON 

INSERT [dbo].[Provider] ([id], [brandName], [infomation], [active]) VALUES (1, N'Thỏ Tây', N'Shop bán hàng nữ', 1)
INSERT [dbo].[Provider] ([id], [brandName], [infomation], [active]) VALUES (2, N'Moxy', N'Quần áo nữ', 1)
SET IDENTITY_INSERT [dbo].[Provider] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (1, N'Admin', NULL)
INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (2, N'Employee', NULL)
INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (3, N'Customer', NULL)
SET IDENTITY_INSERT [dbo].[Role] OFF
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Employee] FOREIGN KEY([employeeID])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Employee]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Role] ([id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_User_Role]
GO
ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [FK_Category_Type] FOREIGN KEY([genderID])
REFERENCES [dbo].[Gender] ([id])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [FK_Category_Type]
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
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Account] FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Account]
GO
ALTER TABLE [dbo].[InvoiceItem]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceItem_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[InvoiceItem] CHECK CONSTRAINT [FK_InvoiceItem_Product]
GO
ALTER TABLE [dbo].[InvoiceItem]  WITH CHECK ADD  CONSTRAINT [FK_OrderItem_Order] FOREIGN KEY([orderId])
REFERENCES [dbo].[Invoice] ([id])
GO
ALTER TABLE [dbo].[InvoiceItem] CHECK CONSTRAINT [FK_OrderItem_Order]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Brand] FOREIGN KEY([brandId])
REFERENCES [dbo].[Provider] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Brand]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[ProductSizeColor]  WITH CHECK ADD  CONSTRAINT [FK_ProductSizeColor_Color] FOREIGN KEY([colorID])
REFERENCES [dbo].[Color] ([id])
GO
ALTER TABLE [dbo].[ProductSizeColor] CHECK CONSTRAINT [FK_ProductSizeColor_Color]
GO
ALTER TABLE [dbo].[ProductSizeColor]  WITH CHECK ADD  CONSTRAINT [FK_ProductSizeColor_Product] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ProductSizeColor] CHECK CONSTRAINT [FK_ProductSizeColor_Product]
GO
ALTER TABLE [dbo].[ProductSizeColor]  WITH CHECK ADD  CONSTRAINT [FK_ProductSizeColor_Size] FOREIGN KEY([sizeId])
REFERENCES [dbo].[Size] ([id])
GO
ALTER TABLE [dbo].[ProductSizeColor] CHECK CONSTRAINT [FK_ProductSizeColor_Size]
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
/****** Object:  StoredProcedure [dbo].[ChangePassword]    Script Date: 2020-07-21 6:30:17 PM ******/
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
/****** Object:  StoredProcedure [dbo].[DelAccount]    Script Date: 2020-07-21 6:30:17 PM ******/
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
/****** Object:  StoredProcedure [dbo].[getCategoryByGender]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getCategoryByGender]
	@GenderID INT
AS
BEGIN
	SELECT id, name, detail, genderID, imageUrl FROM category WHERE genderID = @GenderID AND active = 1
END



GO
/****** Object:  StoredProcedure [dbo].[SP_GetBranch]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetBranch]
	@GenderID INT
AS
BEGIN
	SELECT id, brandName, infomation FROM dbo.Brand WHERE active = 1
END



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductCategory]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductCategory]
	@categoryId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT
AS
begin
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	IF(@AccountID >= 0)
	BEGIN
		select pro.id, pro.title, pro.price, pro.thumnail, CASE WHEN promo.id IS NOT NULL AND f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
		CASE WHEN (GETDATE() >= beginDate AND GETDATE() <= endDate) THEN promo.value ELSE 0 END AS promotion
		from dbo.Product AS pro
		LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
		LEFT OUTER JOIN dbo.Promotion AS promo ON  promo.id = pro.idPromotion
		WHERE pro.categoryID = @categoryId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
    END
	ELSE 
	BEGIN
		select pro.id, pro.title, pro.price, pro.thumnail, 0 AS isLike, 
		CASE WHEN (GETDATE() >= beginDate AND GETDATE() <= endDate) THEN promo.value ELSE 0 END AS promotion
		from dbo.Product AS pro
		LEFT OUTER JOIN dbo.Promotion AS promo ON  promo.id = pro.idPromotion
		WHERE pro.categoryID = @categoryId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
	END
end

GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductInfoByID]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductInfoByID]
	@productID INT
AS

	SELECT pro.id, pro.title, pro.detail, pro.price, pro.categoryID, pro.sold, pro.rating, pro.active, pro.brandId, pro.thumnail, pro.isNew, pro.addDate
		FROM product AS pro
	WHERE pro.id = @productID


GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductOfCategory]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductOfCategory]
	@categoryId INT,
	@currentPage  int,
	@PageSize  INT,
	@AccountID INT
AS
begin
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	IF(@AccountID >= 0)
	BEGIN
		select pro.id, pro.title, pro.price, pro.thumnail, CASE WHEN promo.id IS NOT NULL AND f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
		CASE WHEN (GETDATE() >= beginDate AND GETDATE() <= endDate) THEN promo.value ELSE 0 END AS promotion
		from dbo.Product AS pro
		LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
		LEFT OUTER JOIN dbo.Promotion AS promo ON  promo.id = pro.idPromotion
		WHERE pro.categoryID = @categoryId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
    END
	ELSE 
	BEGIN
		select pro.id, pro.title, pro.price, pro.thumnail, 0 AS isLike, 
		CASE WHEN (GETDATE() >= beginDate AND GETDATE() <= endDate) THEN promo.value ELSE 0 END AS promotion
		from dbo.Product AS pro
		LEFT OUTER JOIN dbo.Promotion AS promo ON  promo.id = pro.idPromotion
		WHERE pro.categoryID = @categoryId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
	END
end

GO
/****** Object:  StoredProcedure [dbo].[SP_Login]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_Login]
	@userName NVARCHAR(50),
	@password NVARCHAR(50)
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM dbo.Account a WHERE Username = @userName OR Email = @userName OR Phone = @userName)
		SELECT -1 AS result --sai userName
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE (Username = @userName OR Email = @userName OR Phone = @userName) AND Password != @password)
		SELECT -2  AS result--đúng userName, sai password
	ELSE		
		SELECT Id AS result, N'đăng nhập thành công' AS message --đăng nhập thành công
		FROM dbo.Account a
		WHERE (Username = @userName OR Email = @userName OR Phone = @userName) AND Password = @password


END 


GO
/****** Object:  StoredProcedure [dbo].[SP_Register]    Script Date: 2020-07-21 6:30:17 PM ******/
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
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE Phone = @phone)
		SELECT -3 --trùng phone
	ELSE
	BEGIN
		INSERT INTO dbo.Account(name, email, phone, roleId, password, username, imageUrl, active)
		 VALUES (@name, @email, @phone, @roleId, @password, @username, @imageUrl, @active )

		SELECT 0 
	END 
END

GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateAccInfo]    Script Date: 2020-07-21 6:30:17 PM ******/
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
	ELSE
		BEGIN
			UPDATE dbo.account 
			SET name = @name, phone = @phone, email = @email
			WHERE Id = @userId

			SELECT 1 --thành công
		END
END



GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateUser]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_UpdateUser]
	@userId INT,
	@name nvarchar(100),
	@email nvarchar(200),
	@phone nvarchar(15),
	@roleId INT,
	@password nvarchar(50),
	@imageUrl nvarchar(150)
AS
BEGIN

	IF EXISTS (SELECT * FROM dbo.Account WHERE Id != @userId AND email = @email)
		SELECT -1 --email đã được sử dụng
	ELSE IF EXISTS (SELECT * FROM dbo.Account WHERE Id != @userId AND phone = @phone)
		SELECT -2 --số điện thoại đã được sử dụng
	
	ELSE
	BEGIN
		Update dbo.Account 
		SET NAME = @name,
		email=  @email,
		phone = @phone, 
		roleId= @roleId,
		PASSWORD =  @password,
		imageUrl=  @imageUrl
		 
		WHERE id = @userId

		SELECT 0 
	END 

END

GO
/****** Object:  StoredProcedure [dbo].[SPGetAccounByUsername]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccounByUsername]
	@username NVARCHAR(50)
AS
BEGIN
	SELECT id, name, email, phone, roleId, password,username,imageUrl, active FROM Account WHERE username = @username
END

GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUserId]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoByUserId]
	@userId int
AS
BEGIN
	SELECT id, name, email, phone, roleId, password,username,imageUrl, active FROM Account WHERE id = @userId
END

GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUsername]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoByUsername]
	@username NVARCHAR(50)
AS
BEGIN
	SELECT id, name, email, phone, roleId, password,username,imageUrl, active FROM Account WHERE username = @username
END

GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoUserID]    Script Date: 2020-07-21 6:30:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoUserID]
	@userId int
AS
BEGIN
	SELECT id, name, email, phone, roleId, password,username,imageUrl, active FROM Account WHERE id = @userId
END

GO
