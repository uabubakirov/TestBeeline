package com.example.test.presentation.fragments.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test.data.local.room.entities.toUI
import com.example.test.domain.usecases.curencyusecases.GetLocalCurrencyDataUseCase
import com.example.test.domain.usecases.curencyusecases.GetRemoteCurrencyDataUseCase
import com.example.test.domain.usecases.curencyusecases.UpdateLocalCurrencyDataUseCase
import com.example.test.presentation.fragments.BaseViewModel
import com.example.test.presentation.entities.CurrencyUI
import com.example.test.presentation.entities.ValuteUI
import com.example.test.presentation.utills.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val useCaseLocal: GetLocalCurrencyDataUseCase,
    private val useCaseRemote: GetRemoteCurrencyDataUseCase,
    private val useCaseUpdate: UpdateLocalCurrencyDataUseCase,
) : BaseViewModel() {

    private val _stateCurrencyRemote: MutableLiveData<UIState<ValuteUI>> = MutableLiveData(UIState.Empty())
    val stateCurrencyRemote: LiveData<UIState<ValuteUI>> = _stateCurrencyRemote

    private val _stateCurrencyLocal: MutableLiveData<UIState<CurrencyUI>> = MutableLiveData(UIState.Empty())
    val stateCurrencyLocal: LiveData<UIState<CurrencyUI>> = _stateCurrencyLocal

    init {
        getDataLocal()
    }


    fun update(currencyUI: CurrencyUI){
        viewModelScope.launch {
            useCaseUpdate(currencyUI)
        }
    }
    private fun getDataLocal(){
        useCaseLocal().collectRequest(_stateCurrencyLocal) { it.toUI() }

    }
    fun getDataRemote(){
        useCaseRemote().collectRequest(_stateCurrencyRemote){it}
    }


}