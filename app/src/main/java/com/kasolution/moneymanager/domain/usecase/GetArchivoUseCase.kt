package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.domain.model.Archivos
import javax.inject.Inject

class GetArchivoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Archivos> {
        val archivo = repository.getAllUsuariosFromDatabase()

        return if (archivo.isNullOrEmpty()) {
            emptyList()
        } else {
            repository.getAllArchivoFromDataBase()
        }
    }
}