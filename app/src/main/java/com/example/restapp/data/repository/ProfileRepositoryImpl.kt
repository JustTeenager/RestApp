package com.example.restapp.data.repository

import com.example.restapp.data.manager_contracts.ProfileApiManager
import com.example.restapp.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Named

class ProfileRepositoryImpl @Inject constructor(
    @Named("Api") profileApiManager: ProfileApiManager,
    @Named("Mock") mockProfileApiManager: ProfileApiManager
) : ProfileRepository {

    private val isMockUsing = true

    private val manager: ProfileApiManager =
        if (isMockUsing) mockProfileApiManager else profileApiManager

    override fun login(login: String, password: String): Result<String?> {
        TODO("Not yet implemented")
    }

    override fun register() {
        TODO("Not yet implemented")
    }
}