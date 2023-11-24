package com.kasolution.moneymanager.domain.model

import androidx.room.ColumnInfo
import com.kasolution.moneymanager.data.database.entities.GastoEntity

data class Gastos(
    val id: Int,
    val fechaSistema: String,
    val fechaRegistro: String,
    val ciudad: String,
    val tipoDoc: String,
    val nroDoc: String,
    val proveedor: String,
    val descripcion: String,
    val tipoGasto: String,
    val sustento: String,
    val monto: String,
    val idRegistro: String
)

fun GastoEntity.toDomain() = Gastos(
    id,
    fechaSistema,
    fechaRegistro,
    ciudad,
    tipoDoc,
    nroDoc,
    proveedor,
    descripcion,
    tipoGasto,
    sustento,
    monto,
    idRegistro
)