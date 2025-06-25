package com.example.appshop.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appshop.data.remote.RetrofitClient
import com.example.appshop.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getProducts().map { product ->
                    println("PRODUCT NAME: ${product.name}") // Agregado
                   product.copy(image_url = product.image_url.replace("localhost", "10.0.2.2"))

                  //  product.copy(image_url = product.image_url.replace("localhost", "192.168.18.63"))
                }
                _products.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
