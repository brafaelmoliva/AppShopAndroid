package com.example.appshop.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) // 👈 Esta línea es lo que faltaba
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
