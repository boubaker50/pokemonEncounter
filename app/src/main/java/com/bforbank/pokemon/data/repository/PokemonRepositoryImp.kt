package com.bforbank.pokemon.data.repository

import android.util.Log
import com.bforbank.pokemon.data.remote.PokemonApi
import com.bforbank.pokemon.data.remote.dto.pokemon_details.PokemonDetailDto
import com.bforbank.pokemon.data.remote.dto.pokemon_list.PokemonDto
import com.bforbank.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImp @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonDto {
        return api.getPokemonList(limit, offset)
    }

    override suspend fun getPokemonById(id: Int): PokemonDetailDto {
        val x = api.getPokemonById(id)
        Log.e("TAG", "getPokemonById: $x")
        return x
    }
}
