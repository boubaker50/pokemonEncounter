package com.bforbank.pokemon.presentation

import kotlinx.serialization.Serializable

@Serializable
data object PokemonListRoute

@Serializable
data class PokemonDetailsRoute(val id: Int)