package com.example.appshop.dao.ventas

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VentasDao {
    @Insert
    suspend fun insertarVenta(venta: Venta)

    @Query("SELECT * FROM ventas")
    fun obtenerVentas(): Flow<List<Venta>>
}
