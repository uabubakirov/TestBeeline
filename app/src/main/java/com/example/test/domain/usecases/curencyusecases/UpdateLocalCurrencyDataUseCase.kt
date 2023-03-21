package com.example.test.domain.usecases.curencyusecases

import com.example.test.domain.repositories.CurrencyRepository
import com.example.test.presentation.entities.CurrencyUI
import javax.inject.Inject

class UpdateLocalCurrencyDataUseCase @Inject constructor(
    private val repository: CurrencyRepository
){
    suspend operator fun invoke(currencyUI: CurrencyUI) = repository.updateLocalData(currencyUI)
}