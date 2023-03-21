package com.example.test.data.remote.apiservices

import com.example.test.data.remote.entities.CurrencyModel
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getData():CurrencyModel
}