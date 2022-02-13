package com.example.restapp.ui

import java.lang.Exception

suspend fun <T> runRequest(method:suspend () -> T): Result<T> {
   return try {
       Result.success(method())
   } catch (e: Exception) {
       e.printStackTrace()
       Result.failure(e)
   }
}