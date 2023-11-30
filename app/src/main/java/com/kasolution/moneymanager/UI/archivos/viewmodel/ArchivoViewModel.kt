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
import com.kasolution.moneymanager.domain.usecase.deleteArchivoUseCase
import com.kasolution.moneymanager.domain.usecase.updateArchivoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchivoViewModel @Inject constructor(
    private val getArchivoUserCase: GetArchivoUseCase,
    private val insertArchivoUseCase: InsertArchivoUseCase,
    private val updateArchivoUseCase: updateArchivoUseCase,
    private val deleteArchivoUseCase: deleteArchivoUseCase,
    private val getRegistroUseCase: GetRegistroUseCase,
    private val insertRegistroUseCase: InsertRegistroUseCase
) : ViewModel() {
    val listaArchivo = MutableLiveData<ArrayList<Archivos>>()
    val listaRegistro = MutableLiveData<ArrayList<Registros>>()
    val isLoading = MutableLiveData<Boolean>()

    fun ListarArchivo() {
        var lista = ArrayList<Archivos>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getArchivoUserCase()
            Log.i("datos", result.toString())
            if (result.isNotEmpty()) {
                for (i in result) {
                    lista.add(i)
                }
                listaArchivo.postValue(lista)
                isLoading.postValue(false)
            }
        }
    }

    fun insertArchivo(nombre: String, descripcion: String) {
        val listaArchivo = listOf(Archivos(0, nombre, descripcion, false))
        viewModelScope.launch {
            if (insertArchivoUseCase(listaArchivo) != -1)
                Log.i("datos", "Datos registrados correctamente")
        }
    }

    fun updateArchivo(id: Int, nombre: String, descripcion: String) {
        val listaArchivo = listOf(Archivos(id, nombre, descripcion, false))
        viewModelScope.launch {
            if (updateArchivoUseCase(listaArchivo) != -1) {
                Log.i("datos", "Datos Modificados correctamente")
                listaArchivo
            }
        }
    }

    fun deleteArchivo(id: Int) {
        viewModelScope.launch {
            if (deleteArchivoUseCase(id) != -1) {
                Log.i("datos", "Registro eliminado correctamente")
                listaArchivo
            }
        }
    }

    fun ListarRegistro() {
        var lista = ArrayList<Registros>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRegistroUseCase()
            Log.i("datos", result.toString())
            if (result.isNotEmpty()) {
                for (i in result) {
                    lista.add(i)
                }
                listaRegistro.postValue(lista)
                isLoading.postValue(false)
            }
        }
    }

    fun insertRegistro(nombre: String, estado: String, idArchivo: String) {
        val listaRegistros = listOf(Registros(0, nombre, estado, idArchivo))
        viewModelScope.launch {
            if (insertRegistroUseCase(listaRegistros) != -1)
                Log.i("datos", "Datos registrados correctamente")
        }
    }
}