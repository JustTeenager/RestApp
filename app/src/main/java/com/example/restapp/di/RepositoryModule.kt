package com.example.restapp.di

import com.example.restapp.data.repository.BuyCartRepositoryImpl
import com.example.restapp.data.repository.BuyProductRepositoryImpl
import com.example.restapp.data.repository.LoadProductsRepositoryImpl
import com.example.restapp.data.repository.ProfileRepositoryImpl
import com.example.restapp.domain.repository.BuyCartRepository
import com.example.restapp.domain.repository.BuyProductRepository
import com.example.restapp.domain.repository.LoadProductsRepository
import com.example.restapp.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoadProductRepository(impl: LoadProductsRepositoryImpl): LoadProductsRepository

    @Binds
    abstract fun bindBuyProductRepository(impl: BuyProductRepositoryImpl): BuyProductRepository

    @Binds
    abstract fun bindBuyCartRepository(impl: BuyCartRepositoryImpl): BuyCartRepository

    @Binds
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository
}