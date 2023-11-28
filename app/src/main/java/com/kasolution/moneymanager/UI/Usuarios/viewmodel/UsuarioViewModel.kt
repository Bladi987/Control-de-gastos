package com.kasolution.moneymanager.UI.Usuarios.viewmodel

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
    val listaUsuario=MutableLiveData<ArrayList<Usuario>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        var lista= ArrayList<Usuario>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getUsuarioUserCase()
            Log.i("datos", result.toString())
            if (result.isNotEmpty()) {
                for (i in result){
                    lista.add(i)
                }
                usuarioModel.postValue(result[0])
                listaUsuario.postValue(lista)
                isLoading.postValue(false)
//                Log.i("datos","tamano de lista ${lista.size}")
            }
        }
    }

    fun saveDataUser(nombre: String, usuario: String, password: String, tipo: String) {
        val listausuario= listOf(Usuario(nombre,usuario,password,tipo))
        viewModelScope.launch {
            if (insertUserUseCase(listausuario)!=-1)
            Log.i("datos","Datos registrados correctamente")
        }
    }
}