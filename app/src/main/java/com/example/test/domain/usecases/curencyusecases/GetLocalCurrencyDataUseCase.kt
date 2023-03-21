package com.example.test.domain.usecases.curencyusecases

import com.example.test.domain.repositories.CurrencyRepository
import javax.inject.Inject

class GetLocalCurrencyDataUseCase @Inject constructor(
    private val repository: CurrencyRepository
){
    operator fun invoke() = repository.getLocalData()
}