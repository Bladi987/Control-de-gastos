package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import javax.inject.Inject

class getDataPreferencesUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(key:String):String{
        return repository.getData(key,"")
    }
}