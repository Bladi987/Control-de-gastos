package com.kasolution.moneymanager.UI.archivos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kasolution.moneymanager.R
import com.kasolution.moneymanager.UI.archivos.fragment.ArchivoFragment
import com.kasolution.moneymanager.databinding.ActivityArchivoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArchivoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityArchivoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityArchivoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fcontainer, ArchivoFragment())
        fragmentTransaction.commit()
    }
}