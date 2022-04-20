package com.example.restapp.data.manager.api.profile

import com.example.restapp.data.manager_contracts.ProfileApiManager
import com.example.restapp.domain.dto.TokenDTO
import javax.inject.Inject

class MockProfileApiManagerImpl @Inject constructor() : ProfileApiManager {
    override suspend fun login(login: String, password: String): TokenDTO {
        return TokenDTO(
            login,
            password
        )
        //throw Exception("")
    }

    override suspend fun register() {
        TODO("Not yet implemented")
    }
}