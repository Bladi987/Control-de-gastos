package com.kasolution.moneymanager.di

import android.content.Context
import androidx.room.Room
import com.kasolution.moneymanager.data.database.BDGastos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun providerRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BDGastos::class.java, "BDGastos.sqlite").build()

    @Singleton
    @Provides
    fun providerUsuarioDao(db:BDGastos)=db.getUsuarioDao()

    @Singleton
    @Provides
    fun providerGastosDao(db:BDGastos)=db.getGastosDao()
}