package com.example.restapp.dto

import kotlinx.serialization.SerialName

data class Booking(
    val id: Int,
    @SerialName("booking_time")
    val bookingTime: String,
    @SerialName("client_name")
    val clientName: String
)