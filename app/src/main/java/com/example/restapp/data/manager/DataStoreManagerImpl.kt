package com.example.restapp.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.restapp.data.manager_contracts.DataStoreManager
import com.example.restapp.data.manager_contracts.DataStoreManager.Companion.LOGIN_PREF_KEY
import com.example.restapp.data.manager_contracts.DataStoreManager.Companion.PASSWORD_PREF_KEY
import com.example.restapp.data.manager_contracts.DataStoreManager.Companion.TOKEN_PREF_KEY
import com.example.restapp.ui.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : DataStoreManager {

    private val store = context.dataStore

    private val dataStoreTokenKey = stringPreferencesKey(TOKEN_PREF_KEY)
    private val dataStoreLoginKey = stringPreferencesKey(LOGIN_PREF_KEY)
    private val dataStorePasswordKey = stringPreferencesKey(PASSWORD_PREF_KEY)

    override suspend fun addProfileToken(token: String) {
        store.edit {
            it[dataStoreTokenKey] = token
        }
    }

    override suspend fun getProfileToken(): String? =
        store.data.map {
            it[dataStoreTokenKey]
        }.firstOrNull()

    override suspend fun addRefreshToken(token: String) {
    }

    override suspend fun getRefreshToken(): String? {
        TODO("Not yet implemented")
    }

}