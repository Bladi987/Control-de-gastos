package com.kasolution.moneymanager.domain.usecase

import com.kasolution.moneymanager.data.Repository
import javax.inject.Inject

class deleteArchivoUseCase @Inject constructor(private val repository: Repository) {
 suspend operator fun invoke(id:Int):Int{
     return try {
         repository.deleteArchivo(id)
      1
     }catch (e:Exception){
      -1
     }
 }
}