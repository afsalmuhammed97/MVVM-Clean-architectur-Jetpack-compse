package com.dev.ecomersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.ecomersapp.data.local.dao.ProductDao
import com.dev.ecomersapp.data.local.entity.ProductEntity


@Database (entities = [ProductEntity::class] , version = 1)
abstract  class AppDatabase: RoomDatabase() {


    abstract fun productDao(): ProductDao
}