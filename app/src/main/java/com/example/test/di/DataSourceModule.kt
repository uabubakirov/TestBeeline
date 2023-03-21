package com.example.test.di

import com.example.test.data.remote.sources.CurrencyDataSource
import com.example.test.data.remote.sources.CurrencyDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun provideCurrencyDataSource(currencyDataSourceImpl: CurrencyDataSourceImpl) : CurrencyDataSource
}