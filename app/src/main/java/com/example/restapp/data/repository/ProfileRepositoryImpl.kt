package com.example.restapp.data.repository

import com.example.restapp.data.manager_contracts.DataStoreManager
import com.example.restapp.data.manager_contracts.ProfileApiManager
import com.example.restapp.domain.dto.TokenDTO
import com.example.restapp.domain.repository.ProfileRepository
import com.example.restapp.ui.runRequest
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileApiManager: ProfileApiManager,
    private val dataStoreManager: DataStoreManager,
) : ProfileRepository {

    override suspend fun login(login: String, password: String): Result<TokenDTO?> {
        return runRequest { profileApiManager.login(login, password) }
            .onSuccess {
                dataStoreManager.addProfileToken(it.access)
                dataStoreManager.addRefreshToken(it.refresh)
            }
    }
}