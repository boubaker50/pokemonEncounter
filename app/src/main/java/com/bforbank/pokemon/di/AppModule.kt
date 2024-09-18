package com.bforbank.pokemon.di


import com.bforbank.pokemon.common.Constants
import com.bforbank.pokemon.data.remote.PokemonApi
import com.bforbank.pokemon.data.repository.PokemonRepositoryImp
import com.bforbank.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImp(api)
    }
}