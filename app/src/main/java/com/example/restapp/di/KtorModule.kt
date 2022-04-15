package com.example.restapp.di

import android.util.Log
import com.example.restapp.data.manager_contracts.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json as KotlinJson

@Module
@InstallIn(ViewModelComponent::class)
class KtorModule {

    @Provides
    fun provideKtorClient(
        dataStoreManager: DataStoreManager,
    ): HttpClient = HttpClient(Android) {

        defaultRequest {
            host = "192.168.43.2:8000/api"
            url { protocol = URLProtocol.HTTP }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(KotlinJson { ignoreUnknownKeys = true })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("Ktor log", message)
                }

            }
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 10000
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }
    }.apply {
        responsePipeline.intercept(HttpResponsePipeline.Receive) {

            Log.d("tut_interceptor", "intercepting")

            if (context.response.status == HttpStatusCode.Unauthorized) {
                Log.d("tut_interceptor", "intercept_unauthored")
                val refresh = dataStoreManager.getRefreshToken()
                refresh?.let { refreshToken ->
                    context.client?.let {
                        val token = it.post<String> {
                            url("/auth/jwt/refresh/")
                            contentType(ContentType.Application.Json)
                            body = refreshToken
                        }
                        val newRequest = request<HttpResponse> {
                            takeFrom(context.request)
                                .headers.clear()
                            header(HttpHeaders.Authorization, token)
                        }

                        proceedWith(
                            HttpResponseContainer(
                                subject.expectedType,
                                newRequest.receive()
                            )
                        )
                        dataStoreManager.addProfileToken(token)
                    }
                }
            }
        }
    }
}