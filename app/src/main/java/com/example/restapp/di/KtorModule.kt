package com.example.restapp.di

import android.util.Log
import com.example.restapp.data.manager_contracts.DataStoreManager
import com.example.restapp.domain.dto.LoginDTO
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
            host = "192.168.1.105:8000/api"
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

            Log.d("tut", "intercepting")

            if (context.response.status == HttpStatusCode.Unauthorized) {
                val login = dataStoreManager.getProfileLogin()
                val password = dataStoreManager.getProfilePassword()
                if (login != null && password != null) {
                    context.client?.let {
                        val token = it.get<String> {
                            url("")
                            contentType(ContentType.Application.Json)
                            body = LoginDTO(
                                login = login,
                                password = password
                            )
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
                } else {
                    //TODO ебала какая-то
                    throw Exception("у нас почему-то нет логина/пароля")
                }
            }
        }
    }
}