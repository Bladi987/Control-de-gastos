package com.kasolution.moneymanager.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kasolution.moneymanager.data.database.entities.DestinosEntity


@Dao
interface DestinosDao {
    @Query("SELECT * FROM Destinos")
    suspend fun getDestinos():List<DestinosEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarDestinos(Destinos:List<DestinosEntity>)
}