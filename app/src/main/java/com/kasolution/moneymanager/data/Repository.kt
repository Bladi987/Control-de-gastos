package com.kasolution.moneymanager.data

import android.content.SharedPreferences
import com.kasolution.moneymanager.data.database.dao.ArchivosDao
import com.kasolution.moneymanager.data.database.dao.GastosDao
import com.kasolution.moneymanager.data.database.dao.RegistrosDao
import com.kasolution.moneymanager.data.database.dao.UsuariosDao
import com.kasolution.moneymanager.data.database.entities.ArchivosEntity
import com.kasolution.moneymanager.data.database.entities.GastoEntity
import com.kasolution.moneymanager.data.database.entities.RegistrosEntity
import com.kasolution.moneymanager.data.database.entities.UsuariosEntity
import com.kasolution.moneymanager.domain.model.Archivos
import com.kasolution.moneymanager.domain.model.Gastos
import com.kasolution.moneymanager.domain.model.Registros
import com.kasolution.moneymanager.domain.model.Usuario
import com.kasolution.moneymanager.domain.model.toDomain
import javax.inject.Inject

class Repository @Inject constructor(
    private val UsuarioDao: UsuariosDao,
    private val GastosDao: GastosDao,
    private val ArchivoDao: ArchivosDao,
    private val RegistroDao: RegistrosDao,
    private val sharedPreferences: SharedPreferences
) {
    //TODO esta zona de utilidades
    suspend fun selecteditemFromDatabase() {
        ArchivoDao.itemSelected()
        RegistroDao.itemSelected()
    }

    //TODO este zona es de gestion de datos de room
    //implementacion de usuarios
    suspend fun getAllUsuariosFromDatabase(): List<Usuario> {
        val response = UsuarioDao.getUser()
        return response.map { it.toDomain() }
    }

    suspend fun insertUsuario(usuario: List<UsuariosEntity>) {
        UsuarioDao.insertUser(usuario)
    }

    //implementacion de gastos
    suspend fun getAllGastosFromDatabase(): List<Gastos> {
        val response = GastosDao.getGastos()
        return response.map { it.toDomain() }
    }

    suspend fun insertGasto(Gasto: List<GastoEntity>) {
        GastosDao.insertarGasto(Gasto)
    }

    //implementacion de Archivos
    suspend fun getAllArchivoFromDataBase(): List<Archivos> {
        val response = ArchivoDao.getArchivos()
        return response.map { it.toDomain() }
    }

    suspend fun insertArchivo(archivo: List<ArchivosEntity>) {
        ArchivoDao.insertarArchivos(archivo)
    }

    suspend fun updateArchivo(archivo: List<ArchivosEntity>) {
        ArchivoDao.actualizarArchivo(archivo)
    }

    suspend fun deleteArchivo(idArchivo: Int) {
        ArchivoDao.eleminarArchivo(idArchivo)
    }

    //implementacion de Registros
    suspend fun getAllRegistrosFromDataBase(archivoId: Int): List<Registros> {
        val response = RegistroDao.getRegistros(archivoId)
        return response.map { it.toDomain() }
    }

    suspend fun insertRegistro(registros: List<RegistrosEntity>) {
        RegistroDao.insertarRegistro(registros)
    }

    suspend fun updateRegistro(registro: List<RegistrosEntity>) {
        RegistroDao.actualizarRegistro(registro)
    }

    suspend fun deleteRegistro(idRegistro: Int) {
        RegistroDao.eleminarRegistro(idRegistro)
    }

    //TODO esta zona es de gestion de datos de sharedPreference

    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}