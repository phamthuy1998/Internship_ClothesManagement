package com.ptithcm.core.di

import com.ptithcm.core.repository.*
import com.ptithcm.core.repository.impl.*
import org.koin.dsl.module

val repositoryModule = module {
    single<WishListRepository> { WishListRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ShopRepository> { ShopRepositoryImpl(get()) }
    single<ProductFilterRepository> { ProductFilterRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<PaymentRepository> { PaymentRepositoryImpl(get()) }
    single<CarouselDetailRepository> { CarouselDetailRepositoryImpl(get()) }
    single<CurrencyRepository> { CurrencyRepositoryImpl(get()) }
    single<DesignerRepository> { DesignerRepositoryImpl(get()) }
    single<BrandsRepository> { BrandsRepositoryImpl(get()) }
    single<ShoppingCardRepository> { ShoppingCardRepositoryImpl(get(), get()) }
    single<CheckoutRepository> { CheckoutRepositoryImpl(get(), get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<ShareDataRepository> { ShareDataRepositoryImpl(get()) }
    single<UploadRepository> { UploadRepositoryImpl(get()) }
    single<ProvidersRepository> { ProvidersRepositoryImpl(get()) }
}