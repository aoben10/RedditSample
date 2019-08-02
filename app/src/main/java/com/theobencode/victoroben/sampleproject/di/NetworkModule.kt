package com.theobencode.victoroben.sampleproject.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.theobencode.victoroben.sampleproject.BuildConfig
import com.theobencode.victoroben.sampleproject.data.RedditAPIService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

const val BASE_URL = " https://www.reddit.com"

val networkModule = module {
    single { createOkHttpClient(get()) }
    single { createWebService<RedditAPIService>(get(), BASE_URL) }
    single { createLoggingInterceptor() }
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create()
}

fun createOkHttpClient(interceptor: Interceptor): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(interceptor).build()

fun createLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}