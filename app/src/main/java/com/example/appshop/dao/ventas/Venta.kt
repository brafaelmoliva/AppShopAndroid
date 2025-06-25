package com.example.appshop.dao.ventas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ventas")
data class Venta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ciudad: String,
    val totalVentas: Int
)
