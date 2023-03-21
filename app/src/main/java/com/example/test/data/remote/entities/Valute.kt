package com.example.test.data.remote.entities

import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("USD")
    val usd: Usd?
)
