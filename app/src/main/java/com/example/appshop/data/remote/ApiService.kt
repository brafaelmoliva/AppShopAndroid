package com.example.appshop.data.remote

import com.example.appshop.domain.model.Local
import com.example.appshop.domain.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products.php")
    suspend fun getProducts(): List<Product>

    @GET("locales.php")
    suspend fun getLocales(): List<Local>
}
