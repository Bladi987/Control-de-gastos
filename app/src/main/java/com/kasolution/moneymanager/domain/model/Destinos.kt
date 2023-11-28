package com.kasolution.moneymanager.domain.model

import com.kasolution.moneymanager.data.database.entities.DestinosEntity

data class Destinos(
    val id: Int = 0,
    val Destino: String
)

fun DestinosEntity.ToDomain() = Destinos(id, Destino)
