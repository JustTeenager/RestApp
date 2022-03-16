package com.example.restapp.data.repository

import com.example.restapp.data.manager_contracts.DataStoreManager
import com.example.restapp.data.manager_contracts.ProfileApiManager
import com.example.restapp.domain.repository.ProfileRepository
import com.example.restapp.ui.runRequest
import javax.inject.Inject
import javax.inject.Named

class ProfileRepositoryImpl @Inject constructor(
    @Named("Api") profileApiManager: ProfileApiManager,
    @Named("Mock") mockProfileApiManager: ProfileApiManager,
    private val dataStoreManager: DataStoreManager
) : ProfileRepository {

    private val isMockUsing = true

    private val manager: ProfileApiManager =
        if (isMockUsing) mockProfileApiManager else profileApiManager

    override suspend fun login(login: String, password: String): Result<String?> {
        return runRequest { manager.login(login, password) }
            .onSuccess { dataStoreManager.addProfileToken(it) }
    }
}