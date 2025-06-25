package com.example.appshop.dao.ventas

import android.content.Context
import androidx.room.Room

object VentasDatabaseProvider {
    private var INSTANCE: VentasDatabase? = null

    fun getDatabase(context: Context): VentasDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                VentasDatabase::class.java,
                "ventas_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
