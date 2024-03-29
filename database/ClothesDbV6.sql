USE [ClothesManament]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[Address]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[accountId] [int] NULL,
	[address] [nvarchar](200) NULL,
	[name] [nvarchar](50) NULL,
	[phone] [nvarchar](16) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Address] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[Color]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[Employee]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[FavoriteProduct]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[Image]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[Invoice]    Script Date: 2020-07-26 10:55:41 AM ******/
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
	[active] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InvoiceItem]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceItem](
	[orderId] [int] NOT NULL,
	[productId] [int] NOT NULL,
	[unitPrice] [float] NULL,
	[quantity] [int] NULL,
	[sale] [float] NULL,
 CONSTRAINT [PK_OrderItem] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[ProductSizeColor]    Script Date: 2020-07-26 10:55:41 AM ******/
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
 CONSTRAINT [PK_ProductSizeColor] PRIMARY KEY CLUSTERED 
(
	[productID] ASC,
	[sizeId] ASC,
	[colorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Promotion]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[PromotionItem]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[Provider]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Provider](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[imageUrl] [nvarchar](200) NULL,
	[brandName] [nvarchar](50) NULL,
	[infomation] [nvarchar](max) NULL,
	[active] [int] NULL,
 CONSTRAINT [PK_Brand] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  Table [dbo].[Size]    Script Date: 2020-07-26 10:55:41 AM ******/
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

INSERT [dbo].[Account] ([id], [name], [email], [phone], [roleId], [password], [username], [imageUrl], [employeeID], [active]) VALUES (1, N'Thuy Pham', N'phamthuy@gmail.com', N'012345678', 3, N'Thuy123', N'phamthuy', N'avatar.png', NULL, 1)
INSERT [dbo].[Account] ([id], [name], [email], [phone], [roleId], [password], [username], [imageUrl], [employeeID], [active]) VALUES (16, N'Thuy pham', N'phamthuy2@gmail.com', N'0308479554', 3, N'Thuy1234', N'Thuypham234', N'', NULL, 1)
INSERT [dbo].[Account] ([id], [name], [email], [phone], [roleId], [password], [username], [imageUrl], [employeeID], [active]) VALUES (17, N'Jimmy Le', N'l.vantien286@gmail.com', N'0637477812', 3, N'emyeuanh', N'jimmy', NULL, NULL, 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
SET IDENTITY_INSERT [dbo].[Address] ON 

INSERT [dbo].[Address] ([id], [accountId], [address], [name], [phone], [active]) VALUES (1, 1, N'97, Man Thiện, Hiệp Phú, quận 9, TP.Hồ Chí Minh', N'Phạm Thủy', N'0125634521', 1)
INSERT [dbo].[Address] ([id], [accountId], [address], [name], [phone], [active]) VALUES (2, 1, N'23/2 đường 20, tòa nhà T&M buliding, phường 3, quận Gò Vấp, TP. Hồ Chí Minh', N'Phương Thanh', N'0354676857', 1)
SET IDENTITY_INSERT [dbo].[Address] OFF
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (1, N'Áo thun ', N'aaaa', 1, NULL, NULL, 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (2, N'Áo sơ mi ', N'aa', 1, NULL, NULL, 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (3, N'Chân váy', N'hiohooh', 1, NULL, NULL, 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (4, N'Quần jeans ', N'hehe', 1, NULL, NULL, 2, 1)
INSERT [dbo].[Category] ([id], [name], [detail], [genderID], [imageUrl], [thumnail], [sex], [active]) VALUES (6, N'Quần tây ', N'hihi', 1, NULL, NULL, 1, 1)
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Color] ON 

INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (1, N'Trắng', N'#fff', 1)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (2, N'Đỏ', N'000', NULL)
INSERT [dbo].[Color] ([id], [colorName], [colorHex], [active]) VALUES (3, N'Đen', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Color] OFF
INSERT [dbo].[FavoriteProduct] ([idProduct], [idAccount]) VALUES (1, 1)
INSERT [dbo].[FavoriteProduct] ([idProduct], [idAccount]) VALUES (2, 1)
SET IDENTITY_INSERT [dbo].[Image] ON 

INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (1, N'cot_no_cham_bi_1_a4d881a1b9594357a33537b54039af3b_master.jpg', 1, 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (2, N'cot_no_cham_bi_1349f3bc927649989b24c7ebe10f634f_master.jpg', 1, 1)
INSERT [dbo].[Image] ([id], [imageUrl], [productId], [active]) VALUES (3, N'cot_no_cham_bi_2_d6ee7e17438842ccb977334053dc2106_master.jpg', 2, 1)
SET IDENTITY_INSERT [dbo].[Image] OFF
SET IDENTITY_INSERT [dbo].[Invoice] ON 

INSERT [dbo].[Invoice] ([id], [updateDate], [buyDate], [name], [phone], [address], [note], [userID], [statusOrderId], [active]) VALUES (12, NULL, CAST(N'2020-07-26' AS Date), N'Jimmy khùng', N'0123456789', N'97, Man Thiện, phường Hiệp Phú, quận 9, TP.Hồ Chí Minh', N'Hohoooho', 1, 1, 1)
SET IDENTITY_INSERT [dbo].[Invoice] OFF
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [unitPrice], [quantity], [sale]) VALUES (12, 1, 140000, 3, 0.3)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [unitPrice], [quantity], [sale]) VALUES (12, 3, 150000, 2, 0)
INSERT [dbo].[InvoiceItem] ([orderId], [productId], [unitPrice], [quantity], [sale]) VALUES (12, 4, 130000, 1, 0)
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (1, N'Sơ mi cột nơ chấm bi xanh', N'
Miêu tả 	Form suông, tay dài, lai ngang. Cột nơ ở cổ, dễ dàng điều chỉnh
Chất liệu	Cát Hàn, mềm mát, dày dặn, ít nhăn
Kích cỡ sản phẩm	Chiều dài áo 66cm, tay dài 52cm
Số đo người mẫu	1m65 - 50kg, mặc size S', 200000, 2, 5, 1, 1, N'1', 1, CAST(N'2020-02-02' AS Date), 10)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (2, N'Quần suông lưng cao tím', N'
Miêu tả 	Quần ống suông, lưng cao, dây kéo phía trước, có bản lưng (lưng rời), móc ẩn phía trong. Có xếp li và ủi li phía trước, túi xéo 2 bên, không có túi sau
Chất liệu	Tuyết Hàn, mượt, dày vừa phải, gần như không nhăn, co giãn nhẹ, đứng form
Kích cỡ sản phẩm	Dài 103cm bao gồm bản lưng 3cm
Số đo người mẫu	1m65-50kg, mặc size S', 150000, 2, 4, 1, 2, N's', 0, CAST(N'2020-02-02' AS Date), 2)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (3, N'd', N'a', 150000, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2020-02-02' AS Date), 3)
INSERT [dbo].[Product] ([id], [title], [detail], [price], [categoryID], [rating], [active], [providerId], [thumnail], [isNew], [addDate], [sold]) VALUES (4, N'a', N'a', 130000, 2, 5, 1, 2, N'1', 1, CAST(N'2020-07-24' AS Date), 20)
SET IDENTITY_INSERT [dbo].[Product] OFF
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (1, 1, 1, 10, N'2', 1)
INSERT [dbo].[ProductSizeColor] ([productID], [sizeId], [colorID], [quantity], [imageUrl], [active]) VALUES (1, 2, 1, 6, N'1', 1)
SET IDENTITY_INSERT [dbo].[Promotion] ON 

INSERT [dbo].[Promotion] ([id], [description], [name], [beginDate], [endDate], [value], [imageUrl], [active], [type]) VALUES (2, N'Tưng bừn mua sắm lễ quốc khánh, giảm giá sốc', N'Quốc khánh săn sale', CAST(N'2020-07-10 00:00:00.000' AS DateTime), CAST(N'2020-09-05 00:00:00.000' AS DateTime), 0.3, NULL, 1, N'percent')
INSERT [dbo].[Promotion] ([id], [description], [name], [beginDate], [endDate], [value], [imageUrl], [active], [type]) VALUES (3, N'Khuyến mãi khủng, chào mừng ngày quốc khánh', N'Sale tưng bừng', CAST(N'2020-01-01 00:00:00.000' AS DateTime), CAST(N'2020-09-20 00:00:00.000' AS DateTime), 0.1, NULL, 1, N'percent')
SET IDENTITY_INSERT [dbo].[Promotion] OFF
INSERT [dbo].[PromotionItem] ([idProduct], [idPromo]) VALUES (1, 2)
SET IDENTITY_INSERT [dbo].[Provider] ON 

INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [active]) VALUES (1, N'1', N'Thỏ Tây', N'Shop bán hàng nữ', 1)
INSERT [dbo].[Provider] ([id], [imageUrl], [brandName], [infomation], [active]) VALUES (2, N'1', N'Moxy', N'Quần áo nữ', 1)
SET IDENTITY_INSERT [dbo].[Provider] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (1, N'Admin', NULL)
INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (2, N'Employee', NULL)
INSERT [dbo].[Role] ([id], [roleName], [image]) VALUES (3, N'Customer', NULL)
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Size] ON 

INSERT [dbo].[Size] ([id], [sizeName], [description], [active]) VALUES (1, N'L', N'aaa', 1)
INSERT [dbo].[Size] ([id], [sizeName], [description], [active]) VALUES (2, N'M', N'bbb', 1)
INSERT [dbo].[Size] ([id], [sizeName], [description], [active]) VALUES (3, N'S', N'uuu', 1)
SET IDENTITY_INSERT [dbo].[Size] OFF
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
ALTER TABLE [dbo].[Address]  WITH CHECK ADD  CONSTRAINT [FK_Address_Account] FOREIGN KEY([accountId])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Address] CHECK CONSTRAINT [FK_Address_Account]
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
/****** Object:  StoredProcedure [dbo].[ChangePassword]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[DelAccount]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[getCategoryByGender]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getCategoryByGender]
	@GenderID INT
AS
BEGIN
	SELECT id, name, detail, genderID, imageUrl, thumnail FROM category WHERE sex = @GenderID AND active = 1
END



GO
/****** Object:  StoredProcedure [dbo].[getCategoryGender]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getCategoryGender]
	@GenderID INT
AS
BEGIN
	SELECT id, name, detail, genderID, imageUrl, thumnail FROM category WHERE sex = @GenderID AND active = 1
END



GO
/****** Object:  StoredProcedure [dbo].[SP_AddOrder]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddOrder]
	@userId INT,
	@address NVARCHAR(500),
	@phone NVARCHAR(20),
	@name NVARCHAR(100),
	@note NVARCHAR(500)
AS
BEGIN
DECLARE @invoiceID int
	INSERT dbo.Invoice(buyDate ,name ,phone , address ,note ,userID , statusOrderId , active )
	VALUES  (GETDATE() , -- buyDate - date
	          @name , -- name - nvarchar(50)
	          @phone , -- phone - nvarchar(15)
	          @address , -- address - nvarchar(50)
	          @note , -- note - nvarchar(200)
	          @userId , -- userID - int
	          1 , -- statusOrderId - int
	          1  -- active - int
	        )
	SET @invoiceID =  @@IDENTITY
	SELECT @invoiceID
END

GO
/****** Object:  StoredProcedure [dbo].[SP_AddOrderItem]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_AddOrderItem]
	@productId INT,
	@quantity INT,
	@invoiceId INT 
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
	          unitPrice ,
	          quantity,
			  sale
	        )
	VALUES  ( @invoiceId , -- orderId - int
	          @productId , -- productId - int
			  (SELECT (price - (price * @promotion))
			   FROM dbo.Product 
			   WHERE id = @productID) , -- unitPrice - float
	          @quantity , -- quantity - int
			  @promotion
	        )
END

GO
/****** Object:  StoredProcedure [dbo].[SP_CheckFavoriteProduct]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_CheckUserExist]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_GetAllAddress]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetAllAddress]
	@userId INT
AS
BEGIN
	SELECT id, accountId, address, name, phone  FROM dbo.Address WHERE accountId = @userId AND active =1
END

GO
/****** Object:  StoredProcedure [dbo].[SP_GetColorsOfProduct]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_GetFavoriteProducts]    Script Date: 2020-07-26 10:55:41 AM ******/
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
		LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
    END
	ELSE 
	BEGIN
		select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
		CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion,promo.type AS typePromotion
		from dbo.Product AS pro
		JOIN dbo.FavoriteProduct ON FavoriteProduct.idProduct = pro.id
		LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
	END
end

GO
/****** Object:  StoredProcedure [dbo].[SP_GetFavoriteProductsCount]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_GetProductInfoDetail]    Script Date: 2020-07-26 10:55:41 AM ******/
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
	JOIN dbo.Promotion ON  PromotionItem.idPromo = Promotion.id

	SELECT pro.id, pro.title, pro.detail, pro.price, pro.categoryID, pro.sold, pro.rating, pro.active, pro.providerId, pro.thumnail, pro.isNew, pro.addDate, 
	CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike,
	CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
		FROM product AS pro
		JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
		JOIN #Promo AS promo ON  promo.idProduct = pro.id
	WHERE pro.id = @productID AND pro.active =1


GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductOfCategory]    Script Date: 2020-07-26 10:55:41 AM ******/
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
BEGIN
	SET FMTONLY OFF
	declare @offsetcount as int
	set @offsetcount=(@currentPage-1) * @PageSize

	SELECT dbo.PromotionItem.idProduct, dbo.PromotionItem.idPromo, dbo.Promotion.value, dbo.Promotion.type, dbo.Promotion.beginDate, dbo.Promotion.endDate INTO #Promo 
	FROM dbo.PromotionItem 
	JOIN dbo.Promotion ON  PromotionItem.idPromo =Promotion.id

	IF(@AccountID >= 0)
	BEGIN
		select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, CASE WHEN f.idProduct IS NOT NULL THEN 1 ELSE 0 END AS isLike, 
		CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
		from dbo.Product AS pro
		LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
		LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.categoryID = @categoryId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
    END
	ELSE 
	BEGIN
		select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
		CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
		from dbo.Product AS pro
		LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.categoryID = @categoryId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
	END
end

GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductOfCategoryCount]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_GetProductsOfProvider]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProductsOfProvider]
	@providerId INT,
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
		LEFT OUTER JOIN FavoriteProduct AS f ON f.idProduct = pro.id AND f.idAccount = @AccountID
		LEFT OUTER JOIN #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.providerId = @providerId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
    END
	ELSE 
	BEGIN
		select pro.id, pro.title,pro.detail, pro.rating, pro.sold, pro.price, pro.thumnail, 0 AS isLike, 
		CASE WHEN (GETDATE() >= promo.beginDate AND GETDATE() <= promo.endDate) THEN promo.value ELSE 0 END AS promotion, promo.type AS typePromotion
		from dbo.Product AS pro
		LEFT OUTER JOIN  #Promo AS promo ON  promo.idProduct = pro.id
		WHERE pro.providerId = @providerId AND pro.active =1 
		ORDER by id offset @offsetcount rows fetch Next @PageSize rows ONLY
	END
end

GO
/****** Object:  StoredProcedure [dbo].[SP_GetProductsOfProviderCount]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_GetProviderDetail]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_GetProviderDetail]
	@providerId INT
AS
BEGIN
	SELECT id, brandName,infomation, imageUrl FROM dbo.Provider WHERE id = @providerId 
END



GO
/****** Object:  StoredProcedure [dbo].[SP_GetProviders]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_GetSizesColorsOfProduct]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_GetSizesOfProduct]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_ImagesOfProduct]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_ImagesOfProduct]
	@productID INT
AS
	SELECT imageUrl FROM dbo.Image WHERE productId = @productID


GO
/****** Object:  StoredProcedure [dbo].[SP_Login]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_Register]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_UpdateAccInfo]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SP_UpdateUser]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SPGetAccounByUsername]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SPGetAccount]    Script Date: 2020-07-26 10:55:41 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPGetAccount]
	@userId int
AS
BEGIN
	SELECT id, name, email, phone, roleId, password,username,imageUrl, active FROM Account WHERE id = @userId
END

GO
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUserId]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoByUsername]    Script Date: 2020-07-26 10:55:41 AM ******/
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
/****** Object:  StoredProcedure [dbo].[SPGetAccountInfoUserID]    Script Date: 2020-07-26 10:55:41 AM ******/
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
