package com.kasolution.moneymanager.UI.gastos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasolution.moneymanager.domain.model.Gastos
import com.kasolution.moneymanager.domain.usecase.GetGastosUserCase
import com.kasolution.moneymanager.domain.usecase.InsertGastoUseCase
import com.kasolution.moneymanager.domain.usecase.getDataPreferencesUseCase
import com.kasolution.moneymanager.domain.usecase.setDataPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class gastosViewModel @Inject constructor(
    private val getGastosUserCase: GetGastosUserCase,
    private val insertGastoUseCase: InsertGastoUseCase,
    private val setDataPreferencesUseCase: setDataPreferencesUseCase,
    private val getDataPreferencesUseCase: getDataPreferencesUseCase
) : ViewModel() {
    val listaGastos = MutableLiveData<ArrayList<Gastos>>()
    val isLoading = MutableLiveData<Boolean>()
    fun onCreate() {
        var lista = ArrayList<Gastos>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getGastosUserCase()
            Log.i("datos", result.toString())
            if (result.isNotEmpty()) {
                for (i in result) {
                    lista.add(i)
                }
                listaGastos.postValue(lista)
                isLoading.postValue(false)
            }
        }
    }

    fun saveDataGastos(
        fechaSistema: String,
        fechaRegistro: String,
        ciudad: String,
        tipoDoc: String,
        nroDoc: String,
        proveedor: String,
        descripcion: String,
        tipoGasto: String,
        sustento: String,
        monto: String,
        idRegistro: String
    ) {
        val listaGasto = listOf(
            Gastos(
                0,
                fechaSistema,
                fechaRegistro,
                ciudad,
                tipoDoc,
                nroDoc,
                proveedor,
                descripcion,
                tipoGasto,
                sustento,
                monto,
                idRegistro
            )
        )
        viewModelScope.launch {
            if (insertGastoUseCase(listaGasto) != -1)
                Log.i("datos", "Datos registrados correctamente")
        }
    }

    fun getDataPreferences(key: String) = getDataPreferencesUseCase(key)

    fun setDataPreferences(key: String, value: String) = setDataPreferencesUseCase(key, value)

}