package com.example.test.data.remote.entities

import com.example.test.presentation.entities.ValuteUI
import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("Date")
    val date: String?,
    @SerializedName("Valute")
    val valute: Valute?,
)

fun CurrencyModel.toUI(): ValuteUI {
    return ValuteUI(
        valute?.usd?.value.toString(),
        date?.take(10)
    )
}
