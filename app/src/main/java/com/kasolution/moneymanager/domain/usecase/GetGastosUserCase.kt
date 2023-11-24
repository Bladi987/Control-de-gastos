package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.domain.model.Gastos
import javax.inject.Inject

class GetGastosUserCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Gastos> {
        val gastos = repository.getAllGastosFromDatabase()
        return if (gastos.isNullOrEmpty()) emptyList() else gastos


    }
}