package com.example.restapp.data.manager.api.profile

import com.example.restapp.data.manager_contracts.ProfileApiManager
import javax.inject.Inject

class MockProfileApiManagerImpl @Inject constructor() : ProfileApiManager {
    override suspend fun login(login: String, password: String): String {
        //return "$login $password"
        throw Exception("")
    }

    override suspend fun register() {
        TODO("Not yet implemented")
    }
}