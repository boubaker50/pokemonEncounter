package com.bforbank.pokemon.presentation.pokemonlist

sealed interface PokemonListEvent {
    data class NextClicked(val offset: Int) : PokemonListEvent

}