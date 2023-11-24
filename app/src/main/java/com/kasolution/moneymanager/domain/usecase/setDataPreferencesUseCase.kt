package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import javax.inject.Inject

class setDataPreferencesUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(key:String,value:String){
        repository.saveData(key,value)
    }
}