package com.example.test.presentation.entities

import com.example.test.data.local.room.entities.CurrencyDBEntity

class CurrencyUI(
    val secondNominal: String?,
    val date: String?,
    val lastSign: String?
    )

fun CurrencyUI.toDB()= CurrencyDBEntity(value = secondNominal, date = date, lastSign = lastSign)