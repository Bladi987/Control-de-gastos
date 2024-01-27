package com.kasolution.moneymanager.UI.gastos.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.UI.gastos.adapter.GastoAdapter
import com.kasolution.moneymanager.UI.gastos.viewmodel.gastosViewModel
import com.kasolution.moneymanager.UI.pojos.itemGastos
import com.kasolution.moneymanager.databinding.ActivityGastosBinding
import com.kasolution.moneymanager.domain.model.Gastos
import com.kasolution.moneymanager.domain.model.Registros
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class GastosActivity : AppCompatActivity() {
    private val gastosViewModel: gastosViewModel by viewModels()
    private lateinit var binding: ActivityGastosBinding
    private lateinit var preferencesValueConexion: SharedPreferences
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var adapter: GastoAdapter
    private lateinit var adapter1:ArrayAdapter<String>
    private lateinit var lista: ArrayList<Gastos>
    lateinit var listSheets: ArrayList<Registros>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGastosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesValueConexion = getSharedPreferences("valuesConexion", Context.MODE_PRIVATE)
        listSheets= ArrayList()
        lista = ArrayList()
        recuperarPreferencias()
        InicializarGastos()
        InicializarRegistros()
        gastosViewModel.onCreate()
        gastosViewModel.LoadRegister()

        gastosViewModel.listaGastos.observe(this, Observer { listaGastos->
            lista.addAll(listaGastos)
            adapter.notifyDataSetChanged()
        })
        gastosViewModel.listaRegistros.observe(this, Observer { listaRegistros->
            listSheets.addAll(listaRegistros)
            adapter1.notifyDataSetChanged()
            Log.i("BladiDev",listSheets.toString())
        })

        binding.btnAdd.setOnClickListener(){
            val preferences=gastosViewModel.getDataPreferences("valor1")
            Toast.makeText(this,preferences,Toast.LENGTH_SHORT).show()
        }
    }

    private fun recuperarPreferencias() {

    }

    private fun InicializarGastos(){
        lmanager = LinearLayoutManager(this)
        adapter = GastoAdapter(
            listaRecibida = lista,
            OnClickListener = { itemGastos -> onItemDefault(itemGastos) },
            OnClickUpdate = { itemGastos, position -> onItemUpdate(itemGastos, position) },
            OnClickDelete = { id, position -> onDeleteItem(id, position) })
        binding.RVRegistros.layoutManager = lmanager
        binding.RVRegistros.adapter = adapter
    }
    private fun InicializarRegistros(){
        adapter1 = ArrayAdapter(this, R.layout.item_spinner, listSheets.map { it.Nombre })
        adapter1.setDropDownViewResource(R.layout.item_spinner_dropdown)
        binding.spSheets.adapter = adapter1
    }

    private fun onDeleteItem(id: Gastos, position: Int) {
        TODO("Not yet implemented")
    }

    private fun onItemUpdate(itemGastos: Gastos, position: Int) {
        TODO("Not yet implemented")
    }

    private fun onItemDefault(itemGastos: Gastos) {
        TODO("Not yet implemented")
    }


}