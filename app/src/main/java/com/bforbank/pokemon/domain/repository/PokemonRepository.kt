package com.bforbank.pokemon.domain.repository

import com.bforbank.pokemon.data.remote.dto.pokemon_details.PokemonDetailDto
import com.bforbank.pokemon.data.remote.dto.pokemon_list.PokemonDto


interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): PokemonDto
    suspend fun getPokemonById(id: Int): PokemonDetailDto

}