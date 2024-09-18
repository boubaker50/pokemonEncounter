package com.bforbank.pokemon.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val order: Int,
    val picture: String?,
    val height: Int,
    val weight: Int
)