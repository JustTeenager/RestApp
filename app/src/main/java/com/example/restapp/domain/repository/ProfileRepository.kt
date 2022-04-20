package com.example.restapp.domain.repository

import com.example.restapp.domain.dto.TokenDTO

interface ProfileRepository {

    suspend fun login(login: String, password: String): Result<TokenDTO?>

}