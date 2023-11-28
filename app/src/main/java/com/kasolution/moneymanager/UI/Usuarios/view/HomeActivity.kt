package com.kasolution.moneymanager.UI.Usuarios.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasolution.moneymanager.UI.Usuarios.adapter.userAdapter
import com.kasolution.moneymanager.UI.Usuarios.viewmodel.UsuarioViewModel
import com.kasolution.moneymanager.databinding.ActivityUsuariosBinding

import com.kasolution.moneymanager.domain.model.Usuario
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val usuarioViewModel: UsuarioViewModel by viewModels()
    private lateinit var binding: ActivityUsuariosBinding
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var adapter: userAdapter
    private lateinit var lista: ArrayList<Usuario>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lista = ArrayList()

        initilizar()
        binding.btnClic.setOnClickListener() {

            Toast.makeText(this, lista[0].toString(), Toast.LENGTH_LONG).show()
        }

        usuarioViewModel.onCreate()

        usuarioViewModel.usuarioModel.observe(this, Observer { currentUsuario ->
            Log.i("Data", currentUsuario.nombre)
            Log.i("Data", currentUsuario.user)
            Log.i("Data", currentUsuario.password)
            Log.i("Data", currentUsuario.tipo)
//            Toast.makeText(this,"${currentUsuario.nombre}",Toast.LENGTH_LONG).show()
        })
        usuarioViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
        binding.btnGuardar.setOnClickListener() {
            usuarioViewModel.saveDataUser(
                binding.tvnombre.text.toString(),
                binding.tvuser.text.toString(),
                binding.tvpassword.text.toString(),
                binding.tvtipo.text.toString()
            )
        }
        usuarioViewModel.listaUsuario.observe(this, Observer { listausuario ->
            lista.addAll(listausuario)
            adapter.notifyDataSetChanged()


            Log.i("datos", "lista ${listausuario.toString()}")
        })


    }

    private fun initilizar() {
        lmanager = LinearLayoutManager(this)
        adapter = userAdapter(
            listaRecibida = lista,
            OnClickListener = { itemUsuario -> onItemDefault(itemUsuario) },
            OnClickUpdate = { itemUsuario, position -> onItemUpdate(itemUsuario, position) },
            OnClickDelete = { id, position -> onDeleteItem(id, position) })
        binding.rvListaUsuario.layoutManager = lmanager
        binding.rvListaUsuario.adapter = adapter
    }

    private fun onDeleteItem(id: Int, position: Int) {

    }

    private fun onItemUpdate(itemUsuario: Usuario, position: Int) {

    }

    private fun onItemDefault(itemUsuario: Usuario) {

    }

}