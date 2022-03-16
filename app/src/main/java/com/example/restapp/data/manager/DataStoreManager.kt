package com.example.restapp.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.restapp.ui.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    @ApplicationContext context: Context
) {

    private val store = context.dataStore

    private val dataStoreKey = stringPreferencesKey(AUTHORIZATION_PREF_KEY)

    companion object {
        private const val AUTHORIZATION_PREF_KEY = "authorization_pref_key"
    }

    suspend fun addProfileToken(token: String) {
        store.edit {
            it[dataStoreKey] = token
        }
    }

    suspend fun getProfileToken(): String? =
        store.data.map {
            it[dataStoreKey]
        }.first()

}