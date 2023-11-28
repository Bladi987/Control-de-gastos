package com.kasolution.moneymanager.domain.model

import com.kasolution.moneymanager.data.database.entities.UsuariosEntity


data class Usuario(
    val nombre: String,
    val user: String,
    val password: String,
    val tipo: String
)
fun UsuariosEntity.toDomain()=Usuario(nombre, user, password, tipo)