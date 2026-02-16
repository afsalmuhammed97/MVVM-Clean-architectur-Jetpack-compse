package com.dev.ecomersapp.di

import android.content.Context
import androidx.room.Room
import com.dev.ecomersapp.data.local.AppDatabase
import com.dev.ecomersapp.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun ProvideAppDataBase(@ApplicationContext context: Context): AppDatabase {

        return Room.databaseBuilder(context, AppDatabase::class.java, "fackcart_db").build()

    }


    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao {
        return db.productDao()
    }
}