package com.kasolution.moneymanager.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kasolution.moneymanager.data.database.entities.ArchivosEntity
import com.kasolution.moneymanager.data.database.entities.RegistrosEntity


@Dao
interface RegistrosDao {
    @Query("SELECT * FROM Registros WHERE idArchivo = :archivoId")
    suspend fun getRegistros(archivoId: Int): List<RegistrosEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarRegistro(registros: List<RegistrosEntity>)

    @Update
    suspend fun actualizarRegistro(registro: List<RegistrosEntity>)

    @Query("DELETE FROM Registros WHERE id = :idRegistro")
    suspend fun eleminarRegistro(idRegistro: Int)

    @Query("UPDATE Registros SET selected=:itemSelect")
    suspend fun itemSelected(itemSelect: Boolean = false)
}