package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.data.database.entities.toDatabase
import com.kasolution.moneymanager.domain.model.Archivos
import javax.inject.Inject

class InsertArchivoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(Archivo: List<Archivos>): Int {
        return try {
            repository.insertArchivo(Archivo.map { it.toDatabase() })
            1
        } catch (e: Exception) {
            -1
        }
    }
}