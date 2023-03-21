package com.example.test.presentation.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.remote.utils.Resource
import com.example.test.presentation.utills.UIState

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    protected fun <T, S> Flow<Resource<T>>.collectRequest(
        state: MutableLiveData<UIState<S>>,
        mappedData: (T) -> S,
    ) {
        viewModelScope.launch{
            this@collectRequest.collect {
                when (it) {
                    is Resource.Loading -> {
                        state.value = UIState.Loading()
                    }
                    is Resource.Error -> it.message?.let { error ->
                        state.value = UIState.Error(error)
                    }
                    is Resource.Success -> it.data?.let { data ->
                        state.value = UIState.Success(mappedData(data))
                    }
                    is Resource.Empty -> {
                        state.value = UIState.Empty()
                    }
                }
            }
        }
    }
}