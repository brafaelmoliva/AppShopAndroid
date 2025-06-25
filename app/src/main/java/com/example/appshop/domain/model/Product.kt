package com.example.appshop.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val category: String,
    val image_url: String
)
