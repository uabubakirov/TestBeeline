package com.example.test.di

import com.example.test.data.repositories.CurrencyRepositoryImpl
import com.example.test.data.repositories.MapRepositoryImpl
import com.example.test.domain.repositories.CurrencyRepository
import com.example.test.domain.repositories.MapRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getCurrencyRepository(currencyRepository: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    fun getMapRepository(mapRepository: MapRepositoryImpl):MapRepository
}