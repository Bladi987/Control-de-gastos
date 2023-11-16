package com.kasolution.moneymanager.UI.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.kasolution.moneymanager.UI.home.viewmodel.UsuarioViewModel
import com.kasolution.moneymanager.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val usuarioViewModel: UsuarioViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuarioViewModel.onCreate()
        usuarioViewModel.usuarioModel.observe(this, Observer { currentUsuario ->
            Log.i("Data", currentUsuario.nombre)
            Log.i("Data", currentUsuario.user)
            Log.i("Data", currentUsuario.password)
            Log.i("Data", currentUsuario.tipo)
        })
        usuarioViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
    }
}