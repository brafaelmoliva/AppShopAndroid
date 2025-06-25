package com.example.appshop.domain.model

data class Local(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val latitud: Double,
    val longitud: Double,
    val foto: String
)
