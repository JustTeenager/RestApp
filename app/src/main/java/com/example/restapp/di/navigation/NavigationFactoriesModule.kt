package com.example.restapp.di.navigation

import com.example.restapp.ui.delivery_create.DeliveryCreateNavigationFactory
import com.example.restapp.ui.delivery_list.DeliveryListNavigationFactory
import com.example.restapp.ui.product_catalog.ProductsCatalogNavigationFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationFactoriesModule {

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindProductsCatalogFactory(factory: ProductsCatalogNavigationFactory): NavigationFactory

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindDeliveryCreateFactory(factory: DeliveryCreateNavigationFactory): NavigationFactory

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindDeliveryListFactory(factory: DeliveryListNavigationFactory): NavigationFactory
}