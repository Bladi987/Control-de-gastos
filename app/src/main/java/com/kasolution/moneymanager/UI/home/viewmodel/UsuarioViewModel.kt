package com.kasolution.moneymanager.UI.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasolution.moneymanager.domain.GetUsuarioUserCase
import com.kasolution.moneymanager.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(private val getUsuarioUserCase: GetUsuarioUserCase) :
    ViewModel() {

    val usuarioModel = MutableLiveData<Usuario>()
    val isLoading = MutableLiveData<Boolean>()
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getUsuarioUserCase()
            Log.i("datos",result.toString())
            if (!result.isNullOrEmpty()) {
                usuarioModel.postValue(result[0])
                isLoading.postValue(false)
                Log.i("datos","tiene datos")
            }
        }
    }
}