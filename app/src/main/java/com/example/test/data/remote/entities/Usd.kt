package com.example.test.data.remote.entities

import com.google.gson.annotations.SerializedName

data class Usd(
    @SerializedName("ID")
    val ID: String?,
    @SerializedName("NumCode")
    val numCode: String?,
    @SerializedName("CharCode")
    val charCode: String?,
    @SerializedName("Nominal")
    val nominal: Int?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("Value")
    val value: Double?
)


