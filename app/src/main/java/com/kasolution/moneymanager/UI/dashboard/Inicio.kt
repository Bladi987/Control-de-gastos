package com.kasolution.moneymanager.UI.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.UI.dashboard.fragment.FragmentMenu
import com.kasolution.moneymanager.databinding.ActivityInicioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Inicio : AppCompatActivity() {
    private lateinit var binding:ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = Bundle()
        bundle.putString("key","Menu")
        val fragment = FragmentMenu()
        fragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fcontainer, fragment)
        fragmentTransaction.commit()
    }
}