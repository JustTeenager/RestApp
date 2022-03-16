package com.example.restapp.domain.repository

interface ProfileRepository {

    suspend fun login(login: String, password: String): Result<String?>

}