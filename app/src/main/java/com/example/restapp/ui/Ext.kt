package com.example.restapp.ui

suspend fun <T> runRequest(method: suspend () -> T): Result<T> {
    return try {
        Result.success(method())
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}

//TODO Стринг ресурс
fun Int.toRoubles(): String {
    return "$this ₽"
}