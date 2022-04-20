package com.example.restapp.di

import com.example.restapp.BuildConfig
import com.example.restapp.data.manager.DataStoreManagerImpl
import com.example.restapp.data.manager.StorageManagerImpl
import com.example.restapp.data.manager.api.product.MockProductApiManagerImpl
import com.example.restapp.data.manager.api.product.ProductApiManagerImpl
import com.example.restapp.data.manager.api.profile.MockProfileApiManagerImpl
import com.example.restapp.data.manager.api.profile.ProfileApiManagerImpl
import com.example.restapp.data.manager_contracts.DataStoreManager
import com.example.restapp.data.manager_contracts.ProductApiManager
import com.example.restapp.data.manager_contracts.ProfileApiManager
import com.example.restapp.data.manager_contracts.StorageManager
import com.example.restapp.data.mapper.FromDtoToProductMapper
import com.example.restapp.data.mapper.FromProductToDtoMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.*

@Module
@InstallIn(ViewModelComponent::class)
abstract class ManagerBinding {

    @Binds
    abstract fun bindStorageManager(manager: StorageManagerImpl): StorageManager

    @Binds
    abstract fun bindDataStoreManager(manager: DataStoreManagerImpl): DataStoreManager
}

@Module
@InstallIn(ViewModelComponent::class)
class ManagerProviding {

    @Provides
    fun provideProductApiManager(
        dtoToProductMapper: FromDtoToProductMapper,
        productToDtoMapper: FromProductToDtoMapper,
        client: HttpClient,
        dataStoreManager: DataStoreManager
    ): ProductApiManager {
        return if (BuildConfig.IS_MOCK_USING) MockProductApiManagerImpl(
            dtoToProductMapper, productToDtoMapper
        ) else ProductApiManagerImpl(
            client, dataStoreManager, dtoToProductMapper, productToDtoMapper
        )
    }

    @Provides
    fun provideProfileApiManager(
        client: HttpClient
    ): ProfileApiManager {
        return if (BuildConfig.IS_MOCK_USING) MockProfileApiManagerImpl()
        else ProfileApiManagerImpl(client)
    }
}