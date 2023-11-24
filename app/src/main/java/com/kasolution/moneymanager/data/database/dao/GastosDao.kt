package com.kasolution.moneymanager.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kasolution.moneymanager.data.database.entities.GastoEntity

@Dao
interface GastosDao {
    @Query("SELECT * FROM Gastos")
    suspend fun getGastos():List<GastoEntity>

//    @Query("SELECT * FROM Usuarios")
//    suspend fun getUser(): List<UsuariosEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarGasto(gasto:List<GastoEntity>)
}