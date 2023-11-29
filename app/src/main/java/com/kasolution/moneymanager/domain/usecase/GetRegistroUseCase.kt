package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.domain.model.Registros
import javax.inject.Inject

class GetRegistroUseCase @Inject constructor(private val  repository: Repository) {
    suspend operator fun invoke(): List<Registros> {
        val registros = repository.getAllRegistrosFromDataBase()

        return if (registros.isNullOrEmpty()) {
            emptyList()
        } else {
            repository.getAllRegistrosFromDataBase()
        }
    }
}