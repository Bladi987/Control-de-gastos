package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import javax.inject.Inject

class deleteRegistroUseCase @Inject constructor(private val repository: Repository) {
 suspend operator fun invoke(id:Int):Int{
     return try {
         repository.deleteRegistro(id)
      1
     }catch (e:Exception){
      -1
     }
 }
}