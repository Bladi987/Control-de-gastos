package com.kasolution.moneymanager.domain.model

import com.kasolution.moneymanager.data.database.entities.LiquidacionEntity


data class Liquidacion(
    val id: Int = 0,
    val fechaRegistro: String,
    val fechaActualizacion: String,
    val archivo: String,
    val registro: String,
    val estado: String,
    val monto: String,
    val comentarios: String

)

fun LiquidacionEntity.toDomain() = Liquidacion(
    id,
    fechaRegistro,
    fechaActualizacion,
    archivo,
    registro,
    estado,
    monto,
    comentarios
)