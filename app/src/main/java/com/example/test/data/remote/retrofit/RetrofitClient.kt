package com.example.test.data.remote.retrofit

import com.example.test.data.remote.apiservices.CurrencyApi
import com.example.test.data.remote.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(LoggingInterceptor().provideLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val provideRetrofit = Retrofit.Builder()
        .baseUrl("https://www.cbr-xml-daily.ru/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCurrencyApiService():CurrencyApi = provideRetrofit.create(CurrencyApi::class.java)
}