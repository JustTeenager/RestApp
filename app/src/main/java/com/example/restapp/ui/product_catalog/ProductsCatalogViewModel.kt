package com.example.restapp.ui.product_catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapp.data.model.Product
import com.example.restapp.domain.dto.ProductDTO
import com.example.restapp.domain.repository.LoadProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsCatalogViewModel @Inject constructor(
    private val loadProductsRepository: LoadProductsRepository
) : ViewModel() {

    val productsLoadState = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    val productsList = MutableStateFlow<List<Product>>(Product.shimmeredProductsList)

    init {
        viewModelScope.launch {
            obtainEvent(Event.OnLoadingStarted)
        }
    }

    fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingStarted -> {
                startLoading()
            }
            is Event.OnLoadingFailed -> {
                failLoading()
            }
            is Event.OnLoadingSucceed -> {
                succeedLoading(event.data)
            }
        }
    }

    private fun startLoading() = viewModelScope.launch {
        productsLoadState.emit(LoadingState.LOAD_IN_PROGRESS)

        loadProductsRepository.loadProducts()
            .onSuccess { obtainEvent(Event.OnLoadingSucceed(it)) }
            .onFailure { obtainEvent(Event.OnLoadingFailed) }
    }

    private fun failLoading() = viewModelScope.launch {
        productsLoadState.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedLoading(list: List<Product>) = viewModelScope.launch {
        productsList.emit(list)

        productsLoadState.emit(LoadingState.LOAD_SUCCEED)
    }

    sealed class Event {
        object OnLoadingStarted : Event()
        object OnLoadingFailed : Event()
        class OnLoadingSucceed(val data: List<Product>) : Event()
    }

    enum class LoadingState {
        LOAD_IN_PROGRESS,
        LOAD_FAILED,
        LOAD_SUCCEED
    }
}