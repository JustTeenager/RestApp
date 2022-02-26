package com.example.restapp.di

import android.util.Log
import com.example.restapp.data.manager.ApiManagerImpl
import com.example.restapp.data.manager.MockApiManagerImpl
import com.example.restapp.data.manager_contracts.ApiManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Named
import kotlinx.serialization.json.Json as KotlinJson

@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {

    @Provides
    fun provideKtorClient(): HttpClient = HttpClient(Android) {

        defaultRequest {
            host = "192.168.162.2:8000/api"
            url { protocol = URLProtocol.HTTP }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(KotlinJson { ignoreUnknownKeys = true })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("Ktor body is", message)
                }

            }
            level = LogLevel.BODY
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiBinding {

    @Binds
    @Named("Mock")
    abstract fun bindMockApiManager(manager: MockApiManagerImpl): ApiManager

    @Binds
    @Named("Api")
    abstract fun bindApiManager(manager: ApiManagerImpl): ApiManager

}