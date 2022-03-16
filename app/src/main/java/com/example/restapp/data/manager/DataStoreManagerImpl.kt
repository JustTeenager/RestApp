package com.example.restapp.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.restapp.data.manager_contracts.DataStoreManager
import com.example.restapp.data.manager_contracts.DataStoreManager.Companion.AUTHORIZATION_PREF_KEY
import com.example.restapp.ui.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : DataStoreManager {

    private val store = context.dataStore

    private val dataStoreKey = stringPreferencesKey(AUTHORIZATION_PREF_KEY)

    override suspend fun addProfileToken(token: String) {
        store.edit {
            it[dataStoreKey] = token
        }
    }

    override suspend fun getProfileToken(): String? =
        store.data.map {
            it[dataStoreKey]
        }.first()

}