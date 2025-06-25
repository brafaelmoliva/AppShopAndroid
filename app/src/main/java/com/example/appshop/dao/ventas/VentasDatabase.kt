package com.example.appshop.dao.ventas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Venta::class], version = 1, exportSchema = false)
abstract class VentasDatabase : RoomDatabase() {
    abstract fun ventasDao(): VentasDao
}
