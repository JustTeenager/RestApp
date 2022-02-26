package com.example.restapp.data.model

data class Cart(
    val id: Int? = null,
    val productList: List<Pair<Int, Product>>,
    val totalPrice: Int,
    val address: String,
    val deliveryState: DeliveryState? = null
) {
    enum class DeliveryState(val code: Int) {
        IN_QUEUE(0),
        COOKING(1),
        ON_THE_WAY(2),
        DELIVERED(3)
    }
}
