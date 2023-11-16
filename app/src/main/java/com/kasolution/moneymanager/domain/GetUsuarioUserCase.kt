package com.kasolution.moneymanager.domain

import android.util.Log
import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.data.database.entities.toDatabase
import com.kasolution.moneymanager.domain.model.Usuario
import javax.inject.Inject

class GetUsuarioUserCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Usuario> {
        val usuario=repository.getAllUsuariosFromDatabase()
        val dato1:Usuario=Usuario("Bladimiro","Bladi","123456","Administrador")
        val lista= listOf<Usuario>(dato1)

        return if (usuario.isNullOrEmpty()){

            Log.e("datos","No existe datos en tu tabla usuario")
            repository.insertUsuario(lista.map { it.toDatabase() })
            emptyList()
        }else{
            repository.getAllUsuariosFromDatabase()
        }

    }
}