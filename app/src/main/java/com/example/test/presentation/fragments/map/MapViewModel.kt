package com.example.test.presentation.fragments.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.test.data.local.room.entities.toUI
import com.example.test.data.local.sharedpreference.PreferencesHelper
import com.example.test.data.repositories.MapRepositoryImpl
import com.example.test.domain.usecases.curencyusecases.GetLocalCurrencyDataUseCase
import com.example.test.domain.usecases.mapusecases.GetLocalMapDataUseCase
import com.example.test.domain.usecases.mapusecases.UpdateLocalMapDataUseCase
import com.example.test.presentation.entities.MapUIEntity
import com.example.test.presentation.fragments.BaseViewModel
import com.example.test.presentation.utills.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val useCaseGetLocal: GetLocalMapDataUseCase,
    private val useCaseUpdate: UpdateLocalMapDataUseCase

): BaseViewModel() {

    private val _mapState: MutableLiveData<UIState<MapUIEntity>> = MutableLiveData(UIState.Empty())
    val mapState: LiveData<UIState<MapUIEntity>> = _mapState

    fun getData(){
        useCaseGetLocal().collectRequest(_mapState){it.toUI()}
    }
    fun insertData(mapUIEntity: MapUIEntity){
        viewModelScope.launch {
            useCaseUpdate(mapUIEntity)
        }

    }
}