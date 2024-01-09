package com.kasolution.moneymanager.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kasolution.moneymanager.data.database.entities.ArchivosEntity


@Dao
interface ArchivosDao {
    @Query("SELECT * FROM Archivos")
    suspend fun getArchivos(): List<ArchivosEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarArchivos(archivo: List<ArchivosEntity>)

    @Update
    suspend fun actualizarArchivo(archivo: List<ArchivosEntity>)

    @Query("DELETE FROM Archivos WHERE id = :idArchivo")
    suspend fun eleminarArchivo(idArchivo: Int)

    @Query("UPDATE ARCHIVOS SET selected=:itemSelect")
    suspend fun itemSelected(itemSelect: Boolean = false)

}