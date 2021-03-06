USE [ClothesManament]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NULL,
	[email] [nvarchar](200) NULL,
	[phone] [nvarchar](15) NULL,
	[gender] [int] NULL,
	[roleId] [int] NULL,
	[password] [nvarchar](50) NULL,
	[username] [nvarchar](50) NULL,
	[imageUrl] [nvarchar](150) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Brand]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brand](
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
/****** Object:  Table [dbo].[Cart]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NULL,
	[quantity] [int] NULL,
	[userID] [int] NULL,
 CONSTRAINT [PK_Cart] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[detail] [nvarchar](200) NULL,
	[typeId] [int] NOT NULL,
	[imageUrl] [nvarchar](200) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Color]    Script Date: 7/12/2020 6:37:06 AM ******/
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
/****** Object:  Table [dbo].[Image]    Script Date: 7/12/2020 6:37:06 AM ******/
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
/****** Object:  Table [dbo].[ItemPromotion]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ItemPromotion](
	[idProduct] [int] NOT NULL,
	[idPromotion] [int] NOT NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_ItemPromotion] PRIMARY KEY CLUSTERED 
(
	[idProduct] ASC,
	[idPromotion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Order]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[buyDate] [date] NULL,
	[name] [nvarchar](50) NULL,
	[phone] [nvarchar](15) NULL,
	[email] [nvarchar](100) NULL,
	[address] [nvarchar](50) NULL,
	[note] [nvarchar](200) NULL,
	[userID] [int] NULL,
	[statusOrderId] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[OrderItem]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderItem](
	[orderId] [int] NOT NULL,
	[productId] [int] NOT NULL,
	[unitPrice] [float] NULL,
	[quantity] [int] NULL,
	[sale] [nchar](10) NULL,
 CONSTRAINT [PK_OrderItem] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 7/12/2020 6:37:06 AM ******/
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
	[sold] [int] NULL,
	[rating] [int] NULL,
	[active] [int] NULL,
	[brandId] [int] NULL,
	[thumnail] [nvarchar](200) NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ProductSizeColor]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductSizeColor](
	[productID] [int] NOT NULL,
	[sizeId] [int] NOT NULL,
	[colorID] [int] NOT NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_ProductSizeColor] PRIMARY KEY CLUSTERED 
(
	[productID] ASC,
	[sizeId] ASC,
	[colorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Promotion]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Promotion](
	[id] [int] NOT NULL,
	[description] [nvarchar](200) NULL,
	[name] [nvarchar](200) NULL,
	[beginDate] [nvarchar](50) NULL,
	[endDate] [nvarchar](50) NULL,
	[typePromotionId] [int] NULL,
	[value] [float] NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Promotion] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 7/12/2020 6:37:06 AM ******/
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
/****** Object:  Table [dbo].[Size]    Script Date: 7/12/2020 6:37:06 AM ******/
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
/****** Object:  Table [dbo].[Type]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Gender] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TypePromotion]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TypePromotion](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NULL,
	[code] [nvarchar](50) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_TypePromotion] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [name], [email], [phone], [gender], [roleId], [password], [username], [imageUrl], [active]) VALUES (1, N'Phạm Thủy', N'phamthithuy4444@gmail.com', N'0373987563', 1, 3, N'123123', N'phamthuy', N'avatar.png', 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
SET IDENTITY_INSERT [dbo].[Brand] ON 

INSERT [dbo].[Brand] ([id], [brandName], [infomation], [active]) VALUES (1, N'Thỏ Tây', N'Shop bán hàng nữ', 1)
INSERT [dbo].[Brand] ([id], [brandName], [infomation], [active]) VALUES (2, N'Moxy', N'Quần áo nữ', 1)
SET IDENTITY_INSERT [dbo].[Brand] OFF
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name], [detail], [typeId], [imageUrl], [active]) VALUES (1, N'Áo thun nữ', N'aaaa', 1, NULL, NULL)
INSERT [dbo].[Category] ([id], [name], [detail], [typeId], [imageUrl], [active]) VALUES (2, N'Áo sơ mi nữ', N'aa', 1, NULL, NULL)
INSERT [dbo].[Category] ([id], [name], [detail], [typeId], [imageUrl], [active]) VALUES (3, N'Váy nữ', N'hiohooh', 1, NULL, NULL)
INSERT [dbo].[Category] ([id], [name], [detail], [typeId], [imageUrl], [active]) VALUES (4, N'Quần jeans nữ', N'hehe', 1, NULL, NULL)
INSERT [dbo].[Category] ([id], [name], [detail], [typeId], [imageUrl], [active]) VALUES (5, N'Chân váy nữ', N'hihi', 1, NULL, NULL)
INSERT [dbo].[Category] ([id], [name], [detail], [typeId], [imageUrl], [active]) VALUES (6, N'Quần tây nữ', NULL, 1, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Color] ON 

INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (1, N'Trắng', N'fff', 1)
SET IDENTITY_INSERT [dbo].[Color] OFF
SET IDENTITY_INSERT [dbo].[Image] ON 

INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (1, N'cot_no_cham_bi_1_a4d881a1b9594357a33537b54039af3b_master.jpg', 1, 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (2, N'cot_no_cham_bi_1349f3bc927649989b24c7ebe10f634f_master.jpg', 1, 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (3, N'cot_no_cham_bi_2_d6ee7e17438842ccb977334053dc2106_master.jpg', 2, 1)
SET IDENTITY_INSERT [dbo].[Image] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [sold], [rating], [active], [brandId], [thumnail]) VALUES (1, N'Sơ mi cột nơ chấm bi xanh', N'
Miêu tả 	Form suông, tay dài, lai ngang. Cột nơ ở cổ, dễ dàng điều chỉnh
Chất liệu	Cát Hàn, mềm mát, dày dặn, ít nhăn
Kích cỡ sản phẩm	Chiều dài áo 66cm, tay dài 52cm
Số đo người mẫu	1m65 - 50kg, mặc size S', 200000, 2, 1, 5, 1, 1, N'cot_no_cham_bi_1_a4d881a1b9594357a33537b54039af3b_master.jpg')
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [sold], [rating], [active], [brandId], [thumnail]) VALUES (2, N'Quần suông lưng cao tím', N'
Miêu tả 	Quần ống suông, lưng cao, dây kéo phía trước, có bản lưng (lưng rời), móc ẩn phía trong. Có xếp li và ủi li phía trước, túi xéo 2 bên, không có túi sau
Chất liệu	Tuyết Hàn, mượt, dày vừa phải, gần như không nhăn, co giãn nhẹ, đứng form
Kích cỡ sản phẩm	Dài 103cm bao gồm bản lưng 3cm
Số đo người mẫu	1m65-50kg, mặc size S', 150000, 2, 2, 4, 1, 2, N'cot_no_cham_bi_1_a4d881a1b9594357a33537b54039af3b_master.jpg')
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [sold], [rating], [active], [brandId], [thumnail]) VALUES (3, N'd', N'a', NULL, NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (1, N'Admin', NULL)
INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (2, N'Employee', NULL)
INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (3, N'Customer', NULL)
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Type] ON 

INSERT [dbo].[Type] ([id], [name], [active]) VALUES (1, N'Male', 1)
INSERT [dbo].[Type] ([id], [name], [active]) VALUES (2, N'Female', 1)
INSERT [dbo].[Type] ([id], [name], [active]) VALUES (3, N'Kids', 1)
SET IDENTITY_INSERT [dbo].[Type] OFF
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Role] ([id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_User_Role]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Product]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_User] FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_User]
GO
ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [FK_Category_Type] FOREIGN KEY([typeId])
REFERENCES [dbo].[Type] ([id])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [FK_Category_Type]
GO
ALTER TABLE [dbo].[Image]  WITH CHECK ADD  CONSTRAINT [FK_Image_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Image] CHECK CONSTRAINT [FK_Image_Product]
GO
ALTER TABLE [dbo].[ItemPromotion]  WITH CHECK ADD  CONSTRAINT [FK_ItemPromotion_Product] FOREIGN KEY([idProduct])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ItemPromotion] CHECK CONSTRAINT [FK_ItemPromotion_Product]
GO
ALTER TABLE [dbo].[ItemPromotion]  WITH CHECK ADD  CONSTRAINT [FK_ItemPromotion_Promotion] FOREIGN KEY([idPromotion])
REFERENCES [dbo].[Promotion] ([id])
GO
ALTER TABLE [dbo].[ItemPromotion] CHECK CONSTRAINT [FK_ItemPromotion_Promotion]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_User] FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_User]
GO
ALTER TABLE [dbo].[OrderItem]  WITH CHECK ADD  CONSTRAINT [FK_OrderItem_Order] FOREIGN KEY([orderId])
REFERENCES [dbo].[Order] ([id])
GO
ALTER TABLE [dbo].[OrderItem] CHECK CONSTRAINT [FK_OrderItem_Order]
GO
ALTER TABLE [dbo].[OrderItem]  WITH CHECK ADD  CONSTRAINT [FK_OrderItem_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[OrderItem] CHECK CONSTRAINT [FK_OrderItem_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Brand] FOREIGN KEY([brandId])
REFERENCES [dbo].[Brand] ([id])
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
ALTER TABLE [dbo].[Promotion]  WITH CHECK ADD  CONSTRAINT [FK_Promotion_TypePromotion] FOREIGN KEY([typePromotionId])
REFERENCES [dbo].[TypePromotion] ([id])
GO
ALTER TABLE [dbo].[Promotion] CHECK CONSTRAINT [FK_Promotion_TypePromotion]
GO
/****** Object:  StoredProcedure [dbo].[ChangePassword]    Script Date: 7/12/2020 6:37:06 AM ******/
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
/****** Object:  StoredProcedure [dbo].[DelAccount]    Script Date: 7/12/2020 6:37:06 AM ******/
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
/****** Object:  StoredProcedure [dbo].[getCategoryByType]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getCategoryByType]
	@TypeID INT
AS
BEGIN
	SELECT * FROM category WHERE typeId = @TypeID
END


GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductCategory]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create PROCEDURE [dbo].[SP_GetProductCategory]
	@categoryId INT
AS

 SELECT id, title, price, thumnail from product where product.categoryID = @categoryId

GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductInfoByID]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductInfoByID]
	@productID INT
AS

	SELECT pro.* 
	FROM product AS pro
	INNER JOIN Image ON pro.id = image.productID
	WHERE pro.id = @productID

GO
/****** Object:  StoredProcedure [dbo].[SP_Login]    Script Date: 7/12/2020 6:37:06 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_Register]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_Register]
	@name nvarchar(100),
	@email nvarchar(200),
	@phone nvarchar(15),
	@gender INT,
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
		INSERT INTO dbo.Account(name, email, phone, gender, roleId, password, username, imageUrl, active)
		 VALUES (@name, @email, @phone, @gender, @roleId, @password, @username, @imageUrl, @active )

		SELECT 0 
	END 
END
GO
/****** Object:  StoredProcedure [dbo].[SP_UpdateUser]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_UpdateUser]
	@userId INT,
	@name nvarchar(100),
	@email nvarchar(200),
	@phone nvarchar(15),
	@gender INT,
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
		gender= @gender,
		roleId= @roleId,
		PASSWORD =  @password,
		imageUrl=  @imageUrl
		 
		WHERE id = @userId

		SELECT 0 
	END 

END
GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUserId]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoByUserId]
	@userId int
AS
BEGIN
	SELECT * FROM Account WHERE id = @userId
END
GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUsername]    Script Date: 7/12/2020 6:37:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccountInfoByUsername]
	@username NVARCHAR(50)
AS
BEGIN
	SELECT * FROM Account WHERE username = @username
END
GO
