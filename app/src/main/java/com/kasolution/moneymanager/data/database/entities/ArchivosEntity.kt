package com.kasolution.moneymanager.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kasolution.moneymanager.domain.model.Archivos


@Entity(tableName = "Archivos")
data class ArchivosEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "Nombre") val Nombre: String,
    @ColumnInfo(name = "Descripcion") val Descripcion: String,
    @ColumnInfo(name = "selected") val selected: Boolean
)

fun Archivos.toDatabase() = ArchivosEntity(
    id = id,
    Nombre = Nombre,
    Descripcion = Descripcion,
    selected = selected
)
