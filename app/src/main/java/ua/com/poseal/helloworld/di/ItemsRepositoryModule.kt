package ua.com.poseal.helloworld.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.com.poseal.helloworld.ItemsRepository
import ua.com.poseal.helloworld.ItemsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface ItemsRepositoryModule {

    @Binds
    fun itemsRepository(impl: ItemsRepositoryImpl) : ItemsRepository

}
