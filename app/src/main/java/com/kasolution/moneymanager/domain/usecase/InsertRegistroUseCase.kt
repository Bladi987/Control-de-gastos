package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.data.database.entities.toDatabase
import com.kasolution.moneymanager.domain.model.Registros
import javax.inject.Inject

class InsertRegistroUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(registros: List<Registros>): Int {
        return try {
            repository.insertRegistro(registros.map { it.toDatabase() })
            1
        } catch (e: Exception) {
            -1
        }
    }
}