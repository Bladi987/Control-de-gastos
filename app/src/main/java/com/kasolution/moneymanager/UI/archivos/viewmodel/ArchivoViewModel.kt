package com.kasolution.moneymanager.UI.archivos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasolution.moneymanager.domain.model.Archivos
import com.kasolution.moneymanager.domain.model.Registros
import com.kasolution.moneymanager.domain.usecase.GetArchivoUseCase
import com.kasolution.moneymanager.domain.usecase.GetRegistroUseCase
import com.kasolution.moneymanager.domain.usecase.InsertArchivoUseCase
import com.kasolution.moneymanager.domain.usecase.InsertRegistroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchivoViewModel @Inject constructor(
    private val getArchivoUserCase: GetArchivoUseCase,
    private val insertArchivoUseCase: InsertArchivoUseCase,
    private val getRegistroUseCase: GetRegistroUseCase,
    private val insertRegistroUseCase: InsertRegistroUseCase
):ViewModel() {
    val listaArchivo= MutableLiveData<ArrayList<Archivos>>()
    val listaRegistro=MutableLiveData<ArrayList<Registros>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreateArchivo() {
        var lista= ArrayList<Archivos>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getArchivoUserCase()
            Log.i("datos", result.toString())
            if (result.isNotEmpty()) {
                for (i in result){
                    lista.add(i)
                }
                listaArchivo.postValue(lista)
                isLoading.postValue(false)
            }
        }
    }
    fun onCreateRegistro(){
        var lista= ArrayList<Registros>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRegistroUseCase()
            Log.i("datos", result.toString())
            if (result.isNotEmpty()) {
                for (i in result){
                    lista.add(i)
                }
                listaRegistro.postValue(lista)
                isLoading.postValue(false)
            }
        }
    }
    fun insertArchivo(nombre: String, descripcion: String) {
        val listaArchivo= listOf(Archivos(0,nombre,descripcion))
        viewModelScope.launch {
            if (insertArchivoUseCase(listaArchivo)!=-1)
                Log.i("datos","Datos registrados correctamente")
        }
    }
    fun insertRegistro(nombre: String, estado: String,idArchivo:String) {
        val listaRegistros= listOf(Registros(0,nombre,estado,idArchivo))
        viewModelScope.launch {
            if (insertRegistroUseCase(listaRegistros)!=-1)
                Log.i("datos","Datos registrados correctamente")
        }
    }
}