package com.example.restapp.ui

import android.content.Context
import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.restapp.data.mapper.FromDtoToProductMapper
import com.example.restapp.data.mapper.FromProductToDtoMapper
import com.example.restapp.data.model.Cart
import com.example.restapp.data.model.Product
import com.example.restapp.di.navigation.NavigationFactory
import com.example.restapp.domain.dto.CartDTO
import com.example.restapp.domain.dto.ProductDTO

suspend fun <T> runRequest(method: suspend () -> T): Result<T> {
    return try {
        Log.d("tut", method.javaClass.name)
        Result.success(method())
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}

fun Int.toProductType(): Product.ProductType {
    Product.ProductType.values().forEach {
        if (this == it.code)
            return it
    }
    throw Exception("Wrong Product type code")
}

fun Modifier.withGradient(brush: Brush, isBackground: Boolean = false) = drawWithContent {
    if (isBackground) {
        drawRect(brush)
        drawContent()
    } else {
        drawContent()
        drawRect(brush)
    }
}

fun List<ProductDTO>.toProductList(mapper: FromDtoToProductMapper): List<Product> =
    this.map { mapper(it) }

fun Cart.toDTOCart(mapper: FromProductToDtoMapper): CartDTO = CartDTO(
    id,
    productList.map { it.first to mapper(it.second) },
    totalPrice,
    address,
    deliveryState?.code
)

fun Set<NavigationFactory>.filter(vararg filters: NavigationFactory.NavigationFactoryType): List<NavigationFactory> {
    val rezList = mutableListOf<NavigationFactory>()
    filters.forEach { type ->
        rezList.addAll(this.filter { it.factoryType.contains(type) })
    }
    return rezList.toList()
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Settings")
