package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.data.database.entities.toDatabase
import com.kasolution.moneymanager.domain.model.Gastos
import javax.inject.Inject

class InsertGastoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(gasto:List<Gastos>):Int{
        return try {
            repository.insertGasto(gasto.map { it.toDatabase() })
            1
        } catch (e: Exception) {
            -1
        }
    }
}