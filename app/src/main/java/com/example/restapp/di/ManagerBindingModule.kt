package com.example.restapp.di

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
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
abstract class ManagerBinding {

    @Binds
    @Named("Mock")
    abstract fun bindMockProductApiManager(manager: MockProductApiManagerImpl): ProductApiManager

    @Binds
    @Named("Api")
    abstract fun bindProductApiManager(manager: ProductApiManagerImpl): ProductApiManager

    @Binds
    @Named("Mock")
    abstract fun bindMockProfileApiManager(manager: MockProfileApiManagerImpl): ProfileApiManager

    @Binds
    @Named("Api")
    abstract fun bindProfileApiManager(manager: ProfileApiManagerImpl): ProfileApiManager

    @Binds
    abstract fun bindStorageManager(manager: StorageManagerImpl): StorageManager


    @Binds
    abstract fun bindDataStoreManager(manager: DataStoreManagerImpl): DataStoreManager
}