package com.bforbank.pokemon.data.remote.dto.pokemon_list

import com.bforbank.pokemon.common.Constants.BASE_URL
import com.bforbank.pokemon.domain.model.Pokemon
import com.bforbank.pokemon.domain.model.PokemonList
import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<Result>
)

fun PokemonDto.toPokemonList(): PokemonList {
    return PokemonList(
        pokemonCount = count,
        pokemonList = results.map { result ->
            Pokemon(
                id = result.url.extractPokemonIdOrNull(),
                name = result.name,
            )
        }
    )
}

fun String.extractPokemonIdOrNull(): Int? {
    return this
        .takeIf { it.contains(BASE_URL + "pokemon/") }
        ?.substringAfter(BASE_URL + "pokemon/")
        ?.substringBefore("/")
        ?.toIntOrNull()
}