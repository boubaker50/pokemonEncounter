package com.bforbank.pokemon.presentation.pokemonlist

import com.bforbank.pokemon.domain.model.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonCount: Int = 0,
    val pokemonList: List<Pokemon> = emptyList(),
) {
    val nextEnabled = pokemonList.size < pokemonCount
}