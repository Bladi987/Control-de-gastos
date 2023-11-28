package com.kasolution.moneymanager.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kasolution.moneymanager.data.database.entities.ArchivosEntity


@Dao
interface ArchivosDao {
    @Query("SELECT * FROM Archivos")
    suspend fun getArchivos():List<ArchivosEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarArchivos(archivo:List<ArchivosEntity>)
}