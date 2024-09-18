package com.bforbank.pokemon.presentation.pokemon_list

sealed interface PokemonListEvent {
    data class NextClicked(val offset: Int) : PokemonListEvent

}