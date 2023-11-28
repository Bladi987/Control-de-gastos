package com.kasolution.moneymanager.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kasolution.moneymanager.domain.model.Destinos

@Entity(tableName = "Destinos")
data class DestinosEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "Destino") val Destino: String
)

fun Destinos.ToDatabase() = DestinosEntity(id = id, Destino = Destino)