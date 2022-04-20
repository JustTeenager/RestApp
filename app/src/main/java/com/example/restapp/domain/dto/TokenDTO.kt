package com.example.restapp.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenDTO(
    val refresh: String,
    val access: String
)