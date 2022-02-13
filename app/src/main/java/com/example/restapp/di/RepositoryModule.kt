package com.example.restapp.di

import com.example.restapp.data.repository.BuyProductRepositoryImpl
import com.example.restapp.data.repository.LoadProductsRepositoryImpl
import com.example.restapp.domain.repository.BuyProductRepository
import com.example.restapp.domain.repository.LoadProductsRepository
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
}