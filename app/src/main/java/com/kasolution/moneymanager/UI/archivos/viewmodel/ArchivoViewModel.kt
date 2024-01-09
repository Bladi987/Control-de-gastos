package com.kasolution.moneymanager.UI.archivos.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasolution.moneymanager.domain.model.Archivos
import com.kasolution.moneymanager.domain.model.Registros
import com.kasolution.moneymanager.domain.usecase.GetArchivoUseCase
import com.kasolution.moneymanager.domain.usecase.GetRegistroUseCase
import com.kasolution.moneymanager.domain.usecase.InsertArchivoUseCase
import com.kasolution.moneymanager.domain.usecase.InsertRegistroUseCase
import com.kasolution.moneymanager.domain.usecase.SeleccionarItemUseCase
import com.kasolution.moneymanager.domain.usecase.deleteArchivoUseCase
import com.kasolution.moneymanager.domain.usecase.deleteRegistroUseCase
import com.kasolution.moneymanager.domain.usecase.updateArchivoUseCase
import com.kasolution.moneymanager.domain.usecase.updateRegistroUseCase
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
    private val insertRegistroUseCase: InsertRegistroUseCase,
    private val updateRegistroUseCase: updateRegistroUseCase,
    private val deleteRegistroUseCase: deleteRegistroUseCase,
    private val SeleccionarItemUseCase: SeleccionarItemUseCase
) : ViewModel() {
    val listaArchivo = MutableLiveData<ArrayList<Archivos>>()
    val listaRegistro = MutableLiveData<ArrayList<Registros>>()
    val isLoading = MutableLiveData<Boolean>()

    fun ListarArchivo() {
        listaArchivo.value = ArrayList()
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
    fun ListarRegistro(idArchivo:Int) {
        var lista = ArrayList<Registros>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRegistroUseCase(idArchivo)
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

    fun insertRegistro(nombre: String, estado: String, idArchivo: String,selected:Boolean) {
        val listaRegistros = listOf(Registros(0, nombre, estado, idArchivo,selected))
        viewModelScope.launch {
            if (insertRegistroUseCase(listaRegistros) != -1)
                Log.i("datos", "Datos registrados correctamente")
        }
    }

    fun updateRegistro(id: Int, nombre: String, estado: String,idArchivo: String,selected:Boolean) {
        val listaRegistro = listOf(Registros(id, nombre, estado, idArchivo,selected))
        viewModelScope.launch {
            if (updateRegistroUseCase(listaRegistro) != -1) {
                Log.i("datos", "Datos Modificados correctamente")
                listaRegistro
            }
        }
    }

    fun deleteRegistro(id: Int) {
        viewModelScope.launch {
            if (deleteRegistroUseCase(id) != -1) {
                Log.i("datos", "Registro eliminado correctamente")
                listaRegistro
            }
        }
    }
    fun unselected(){
        viewModelScope.launch {
            SeleccionarItemUseCase()
        }
    }
}