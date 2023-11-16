package com.kasolution.moneymanager.data

import com.kasolution.moneymanager.data.database.dao.UsuariosDao
import com.kasolution.moneymanager.data.database.entities.UsuariosEntity
import com.kasolution.moneymanager.domain.model.Usuario
import com.kasolution.moneymanager.domain.model.toDomain
import javax.inject.Inject

class Repository @Inject constructor(private val UsuarioDao: UsuariosDao) {
    suspend fun getAllUsuariosFromDatabase(): List<Usuario> {
        val response = UsuarioDao.getUser()
        return response.map { it.toDomain() }
    }
    suspend fun insertUsuario(usuario:List<UsuariosEntity>){
        UsuarioDao.insertUser(usuario)
    }
}