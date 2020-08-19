package com.ptithcm.ptshop

import com.ptithcm.ptshop.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { WishListViewModel(get()) }
    viewModel { AuthenticateViewModel(get())}
    viewModel { UserViewModel(get())}
    viewModel { ShopViewModel(get())}
    viewModel { ProductFilterViewModel(get())}
    viewModel { HomeViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
    viewModel { CarouselDetailViewModel(get()) }
    viewModel { DesignerViewModel(get()) }
    viewModel { RefineViewModel() }
    viewModel { BrandsViewModel(get()) }
    viewModel { ShoppingViewModel(get()) }
    viewModel { CheckoutViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ShareDataViewModel(get(), get()) }
    viewModel { AddProductViewModel(get()) }
    viewModel { UploadViewModel(get()) }
    viewModel { ListenerViewModel() }
    viewModel { ProvidersViewModel(get()) }
}