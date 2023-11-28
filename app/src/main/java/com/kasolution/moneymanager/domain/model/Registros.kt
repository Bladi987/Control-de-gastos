package com.kasolution.moneymanager.domain.model

import androidx.room.ColumnInfo
import com.kasolution.moneymanager.data.database.entities.RegistrosEntity

data class Registros(
    val id: Int = 0,
    val Nombre: String,
    val Estado: String,
    val idArchivo: String
)
fun RegistrosEntity.toDomain()=Registros(id,Nombre,Estado,idArchivo)