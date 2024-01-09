package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import javax.inject.Inject

class SeleccionarItemUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(){
        repository.selecteditemFromDatabase()
    }
}