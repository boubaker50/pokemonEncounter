package com.bforbank.pokemon.data.remote.dto.pokemon_details

import com.bforbank.pokemon.domain.model.PokemonDetails

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val order: Int,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
)

fun PokemonDetailDto.toPokemonDetails(): PokemonDetails {
    return PokemonDetails(
        id = id,
        name = name,
        order = order,
        picture = sprites.other.home.front_default,
        height = height,
        weight = weight
    )
}