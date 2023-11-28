package com.kasolution.moneymanager.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kasolution.moneymanager.domain.model.Registros

@Entity(tableName = "Registros")
data class RegistrosEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "Nombre") val Nombre: String,
    @ColumnInfo(name = "Estado") val Estado: String,
    @ColumnInfo(name = "idArchivo") val idArchivo: String
)

fun Registros.toDataBase() =
    RegistrosEntity(id = id, Nombre = Nombre, Estado = Estado, idArchivo = idArchivo)