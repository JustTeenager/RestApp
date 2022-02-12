package com.example.restapp

import io.ktor.client.*
import javax.inject.Inject

class ApiRepository @Inject constructor(
    val client: HttpClient
) {
}