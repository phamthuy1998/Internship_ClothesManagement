package com.ptithcm.core.di

import android.content.Context
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ptithcm.core.BuildConfig
import com.ptithcm.core.api.ApiClothesService
import com.ptithcm.core.api.ApiService
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    single {
        createService<ApiService>(get(),BuildConfig.SERVER_URL)
    }

    single {
        createServiceClothes<ApiClothesService>(get())
    }


    single {
        createOkHttpClient(get())
    }


    single { createAmazonS3Client(get()) }

    single { createTransferUtility(get(), get()) }
}

fun createOkHttpClient(context: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor( ChuckInterceptor(context))
//        .addInterceptor(NoInternetInterceptor(CoreApplication.instance))
        .addNetworkInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()
                val builder = request.newBuilder()
//                val token = CoreApplication.instance.profile?.token
//                val currency = CoreApplication.instance.currency
//                builder.header("SnapShop-Currency", currency.code ?: "GBP")
//                    .header("SnapShop-Country", "US")
//                if (token != null) {
//                    builder.header("Authorization", "Token $token")
//                }
                request = builder.build()
                return chain.proceed(request)
            }
        })
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

fun createTransferUtility(context: Context, s3Client: AmazonS3Client): TransferUtility {
    return TransferUtility.builder()
        .context(context)
        .awsConfiguration(AWSMobileClient.getInstance().configuration)
        .s3Client(s3Client)
        .build()
}

fun createAmazonS3Client(context: Context): AmazonS3Client {
    val cognitoCachingCredentialsProvider = CognitoCachingCredentialsProvider(
        context,
        BuildConfig.IDENTITY_POOL_ID,
        Regions.EU_WEST_1
    )
    val s3Client =
        AmazonS3Client(cognitoCachingCredentialsProvider, Region.getRegion(Regions.EU_WEST_1))
    s3Client.setRegion(Region.getRegion(Regions.EU_WEST_1))
    return s3Client
}

inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

inline fun <reified T> createServiceClothes(context: Context): T {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient=  OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor( ChuckInterceptor(context))
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_CLOTHES_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

inline fun <reified T> createService(): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

