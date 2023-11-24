package com.kasolution.moneymanager.UI.gastos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.databinding.ActivityGastosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GastosActivity : AppCompatActivity() {
    private lateinit var binding:ActivityGastosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGastosBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}