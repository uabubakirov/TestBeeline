package com.example.test.di

import com.example.test.data.remote.retrofit.RetrofitClient
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManagerType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    val retrofit = RetrofitClient()

    @Singleton
    @Provides
    fun provideCurrencyApiService() = retrofit.provideCurrencyApiService()



}