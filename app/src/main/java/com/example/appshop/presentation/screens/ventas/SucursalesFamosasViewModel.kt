package com.example.appshop.presentation.screens.ventas


import android.app.Application
import androidx.lifecycle.*
import com.example.appshop.dao.ventas.Venta
import com.example.appshop.dao.ventas.VentasDatabaseProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SucursalesFamosasViewModel(application: Application) : AndroidViewModel(application) {


    // Obtengo la instancia del DAO de Room

    private val dao = VentasDatabaseProvider.getDatabase(application).ventasDao()

    private val _ventas = MutableStateFlow<List<Venta>>(emptyList())
    val ventas: StateFlow<List<Venta>> = _ventas

    init {
        viewModelScope.launch {
            dao.obtenerVentas().collect {
                _ventas.value = it
            }
        }
    }

    fun insertarVenta(venta: Venta) {
        viewModelScope.launch {
            dao.insertarVenta(venta)
        }
    }

    companion object {
        fun Factory(application: Application): ViewModelProvider.Factory =
            object : ViewModelProvider.AndroidViewModelFactory(application) {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return SucursalesFamosasViewModel(application) as T
                }
            }
    }
}
