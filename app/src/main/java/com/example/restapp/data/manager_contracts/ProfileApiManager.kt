package com.example.restapp.data.manager_contracts

interface ProfileApiManager {
    fun login(login: String, password: String): Result<String?>

    fun register()
}