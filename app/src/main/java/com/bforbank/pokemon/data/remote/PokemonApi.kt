package com.bforbank.pokemon.data.remote

import com.bforbank.pokemon.common.Constants.PAGE_SIZE
import com.bforbank.pokemon.data.remote.dto.pokemon_details.PokemonDetailDto
import com.bforbank.pokemon.data.remote.dto.pokemon_list.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = PAGE_SIZE,
        @Query("offset") offset: Int = 0
    ): PokemonDto
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonDetailDto

}