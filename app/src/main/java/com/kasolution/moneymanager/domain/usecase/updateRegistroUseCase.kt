package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.data.database.entities.ArchivosEntity
import com.kasolution.moneymanager.data.database.entities.toDatabase
import com.kasolution.moneymanager.domain.model.Archivos
import com.kasolution.moneymanager.domain.model.Registros
import javax.inject.Inject

class updateRegistroUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(registro: List<Registros>):Int{
        return try {
            repository.updateRegistro(registro.map { it.toDatabase() })
            1
        }catch (e:Exception){
            -1
        }
    }
}