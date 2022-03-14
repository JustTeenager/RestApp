package com.example.restapp.data.manager.api.profile

import com.example.restapp.data.manager_contracts.ProfileApiManager
import javax.inject.Inject

class MockProfileApiManagerImpl @Inject constructor() : ProfileApiManager {
    override fun login(login: String, password: String): Result<String?> {
        TODO("Not yet implemented")
    }

    override fun register() {
        TODO("Not yet implemented")
    }
}