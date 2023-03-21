package com.example.test.presentation.entities

import com.example.test.data.local.room.entities.CurrencyDBEntity

data class ValuteUI(
    val secondNominal: String?,
    val date: String?,
    )
fun ValuteUI.toLocal(lastSign: String) = CurrencyUI(secondNominal,date,lastSign)

