package com.kasolution.moneymanager.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kasolution.moneymanager.data.database.entities.LiquidacionEntity


@Dao
interface LiquidacionDao {
    @Query("SELECT * FROM Liquidacion")
    suspend fun getLiquidacion():List<LiquidacionEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarLiquidacion(liquidacion:List<LiquidacionEntity>)
}