package com.kasolution.moneymanager.UI.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasolution.moneymanager.domain.usecase.GetUsuarioUserCase
import com.kasolution.moneymanager.domain.model.Usuario
import com.kasolution.moneymanager.domain.usecase.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val getUsuarioUserCase: GetUsuarioUserCase,
    private val insertUserUseCase: InsertUserUseCase
) :
    ViewModel() {

    val usuarioModel = MutableLiveData<Usuario>()
    val isLoading = MutableLiveData<Boolean>()
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getUsuarioUserCase()
            Log.i("datos", result.toString())
            if (!result.isNullOrEmpty()) {
                usuarioModel.postValue(result[0])
                isLoading.postValue(false)
                Log.i("datos", "tiene datos")
            }
        }
    }

    fun saveDataUser(Nombre: String, Usuario: String, Password: String, Tipo: String) {
        val usuario= listOf(Usuario(Nombre,Usuario,Password,Tipo))
        viewModelScope.launch {

            val result = insertUserUseCase(usuario)
            Log.i("datos",result.toString())
        }

    }
}