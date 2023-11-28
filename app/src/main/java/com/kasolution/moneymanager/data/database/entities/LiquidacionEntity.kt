package com.kasolution.moneymanager.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kasolution.moneymanager.domain.model.Liquidacion

@Entity(tableName = "Liquidacion")
data class LiquidacionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "fechaRegistro") val fechaRegistro: String,
    @ColumnInfo(name = "fechaActualizacion") val fechaActualizacion: String,
    @ColumnInfo(name = "archivo") val archivo: String,
    @ColumnInfo(name = "registro") val registro: String,
    @ColumnInfo(name = "estado") val estado: String,
    @ColumnInfo(name = "monto") val monto: String,
    @ColumnInfo(name = "comentarios") val comentarios: String
)

fun Liquidacion.ToDataBase() = LiquidacionEntity(
    id = id,
    fechaRegistro = fechaRegistro,
    fechaActualizacion = fechaActualizacion,
    archivo = archivo,
    registro = registro,
    estado = estado,
    monto = monto,
    comentarios = comentarios
)