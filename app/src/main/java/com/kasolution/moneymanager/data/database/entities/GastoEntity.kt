package com.kasolution.moneymanager.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kasolution.moneymanager.domain.model.Gastos


@Entity(tableName = "Gastos")
data class GastoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "fechaSistema") val fechaSistema: String,
    @ColumnInfo(name = "fechaRegistro") val fechaRegistro: String,
    @ColumnInfo(name = "ciudad") val ciudad: String,
    @ColumnInfo(name = "tipoDoc") val tipoDoc: String,
    @ColumnInfo(name = "nroDoc") val nroDoc: String,
    @ColumnInfo(name = "proveedor") val proveedor: String,
    @ColumnInfo(name = "descripcion") val descripcion: String,
    @ColumnInfo(name = "tipoGasto") val tipoGasto: String,
    @ColumnInfo(name = "sustento") val sustento: String,
    @ColumnInfo(name = "monto") val monto: String,
    @ColumnInfo(name = "idRegistro") val idRegistro: String,

    )

fun Gastos.toDatabase() =
    GastoEntity(
        fechaSistema = fechaSistema,
        fechaRegistro = fechaRegistro,
        ciudad = ciudad,
        tipoDoc = tipoDoc,
        nroDoc = nroDoc,
        proveedor = proveedor,
        descripcion = descripcion,
        tipoGasto = tipoGasto,
        sustento = sustento,
        monto = monto,
        idRegistro = idRegistro
    )