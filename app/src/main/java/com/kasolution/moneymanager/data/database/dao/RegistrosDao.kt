package com.kasolution.moneymanager.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kasolution.moneymanager.data.database.entities.RegistrosEntity


@Dao
interface RegistrosDao {
    @Query("SELECT * FROM Registros")
    suspend fun getRegistros():List<RegistrosEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarRegistro(registros:List<RegistrosEntity>)
}