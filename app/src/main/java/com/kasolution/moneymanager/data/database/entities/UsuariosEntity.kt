package com.kasolution.moneymanager.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kasolution.moneymanager.domain.model.Usuario

@Entity(tableName = "Usuarios")
data class UsuariosEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "tipo") val tipo: String
)

fun Usuario.toDatabase() =
    UsuariosEntity(nombre = nombre, user = user, password = password, tipo = tipo)