package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import com.kasolution.moneymanager.data.database.entities.toDatabase
import com.kasolution.moneymanager.domain.model.Usuario
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(usuario: List<Usuario>): Int {
        return try {
            repository.insertUsuario(usuario.map { it.toDatabase() })
            1
        } catch (e: Exception) {
            -1
        }
    }
}