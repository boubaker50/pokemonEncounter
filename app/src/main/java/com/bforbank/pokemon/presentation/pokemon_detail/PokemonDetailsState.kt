package com.bforbank.pokemon.presentation.pokemon_detail

import com.bforbank.pokemon.common.Constants.EMPTY_STRING

data class PokemonDetailsState(
    val isLoading: Boolean = false,
    val name: String = EMPTY_STRING,
    val picture: Any? = null,
    val order: Int = 0,
    val height: Int = 0,
    val weight: Int = 0
)