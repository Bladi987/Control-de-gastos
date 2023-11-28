package com.kasolution.moneymanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasolution.moneymanager.data.database.dao.ArchivosDao
import com.kasolution.moneymanager.data.database.dao.DestinosDao
import com.kasolution.moneymanager.data.database.dao.GastosDao
import com.kasolution.moneymanager.data.database.dao.LiquidacionDao
import com.kasolution.moneymanager.data.database.dao.RegistrosDao
import com.kasolution.moneymanager.data.database.dao.UsuariosDao
import com.kasolution.moneymanager.data.database.entities.ArchivosEntity
import com.kasolution.moneymanager.data.database.entities.DestinosEntity
import com.kasolution.moneymanager.data.database.entities.GastoEntity
import com.kasolution.moneymanager.data.database.entities.LiquidacionEntity
import com.kasolution.moneymanager.data.database.entities.RegistrosEntity
import com.kasolution.moneymanager.data.database.entities.UsuariosEntity

@Database(
    entities = [UsuariosEntity::class, GastoEntity::class, ArchivosEntity::class, RegistrosEntity::class, LiquidacionEntity::class, DestinosEntity::class],
    version = 1
)
abstract class BDGastos : RoomDatabase() {
    abstract fun getUsuarioDao(): UsuariosDao
    abstract fun getGastosDao(): GastosDao
    abstract fun getArchivosDao(): ArchivosDao
    abstract fun getRegistrosDao(): RegistrosDao
    abstract fun getLiquidacionDao(): LiquidacionDao
    abstract fun getDestinosDao(): DestinosDao

}