package com.kasolution.moneymanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasolution.moneymanager.data.database.dao.UsuariosDao
import com.kasolution.moneymanager.data.database.entities.UsuariosEntity

@Database(entities = [UsuariosEntity::class], version = 1)
abstract class BDGastos:RoomDatabase() {
    abstract fun getUsuarioDao():UsuariosDao
}