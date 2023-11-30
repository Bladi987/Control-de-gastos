package com.kasolution.moneymanager.domain.model

import com.kasolution.moneymanager.data.database.entities.ArchivosEntity


data class Archivos(
    val id: Int = 0,
    val Nombre: String,
    val Descripcion: String,
    var selected:Boolean
)

fun ArchivosEntity.toDomain() = Archivos(id, Nombre, Descripcion,selected)