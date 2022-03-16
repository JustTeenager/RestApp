package com.example.restapp.di.navigation

import com.example.restapp.ui.authorization.AuthorizationScreenFactory
import com.example.restapp.ui.create_delivery.DeliveryCreateNavigationFactory
import com.example.restapp.ui.delivery_list.DeliveryListNavigationFactory
import com.example.restapp.ui.product_catalog.ProductsCatalogNavigationFactory
import com.example.restapp.ui.registration.RegistrationScreenFactory
import com.example.restapp.ui.rest_screen.RestaurantScreenFactory
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
    abstract fun bindProductsCatalogFactory(factory: ProductsCatalogNavigationFactory): NavigationScreenFactory

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindDeliveryCreateFactory(factory: DeliveryCreateNavigationFactory): NavigationScreenFactory

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindDeliveryListFactory(factory: DeliveryListNavigationFactory): NavigationScreenFactory

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindAuthorizationFactory(factory: AuthorizationScreenFactory): NavigationScreenFactory

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindRegistrationFactory(factory: RegistrationScreenFactory): NavigationScreenFactory

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindRestaurantFactory(factory: RestaurantScreenFactory): NavigationHostFactory

}