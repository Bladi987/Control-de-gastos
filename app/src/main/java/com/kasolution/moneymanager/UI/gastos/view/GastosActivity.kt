package com.kasolution.moneymanager.UI.gastos.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasolution.moneymanager.UI.gastos.adapter.GastoAdapter
import com.kasolution.moneymanager.UI.gastos.viewmodel.gastosViewModel
import com.kasolution.moneymanager.UI.pojos.itemGastos
import com.kasolution.moneymanager.databinding.ActivityGastosBinding
import com.kasolution.moneymanager.domain.model.Gastos
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class GastosActivity : AppCompatActivity() {
    private val gastosViewModel: gastosViewModel by viewModels()
    private lateinit var binding: ActivityGastosBinding
    private lateinit var preferencesValueConexion: SharedPreferences
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var adapter: GastoAdapter
    private lateinit var lista: ArrayList<Gastos>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGastosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesValueConexion = getSharedPreferences("valuesConexion", Context.MODE_PRIVATE)
        lista = ArrayList()
        recuperarPreferencias()
        Inicializar()
        gastosViewModel.onCreate()

        gastosViewModel.listaGastos.observe(this, Observer { listaGastos->
            lista.addAll(listaGastos)
            adapter.notifyDataSetChanged()
        })
    }

    private fun recuperarPreferencias() {

    }

    private fun Inicializar(){
        lmanager = LinearLayoutManager(this)
        adapter = GastoAdapter(
            listaRecibida = lista,
            OnClickListener = { itemGastos -> onItemDefault(itemGastos) },
            OnClickUpdate = { itemGastos, position -> onItemUpdate(itemGastos, position) },
            OnClickDelete = { id, position -> onDeleteItem(id, position) })
        binding.RVRegistros.layoutManager = lmanager
        binding.RVRegistros.adapter = adapter
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