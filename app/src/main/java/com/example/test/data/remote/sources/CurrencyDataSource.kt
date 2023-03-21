package com.example.test.data.remote.sources

import com.example.test.data.remote.entities.CurrencyModel

interface CurrencyDataSource {
    suspend fun getData():CurrencyModel
}