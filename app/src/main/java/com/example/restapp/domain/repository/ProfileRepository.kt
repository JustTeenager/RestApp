package com.example.restapp.domain.repository

interface ProfileRepository {

    fun login(login: String, password: String): Result<String?>

    fun register()

}