package com.bforbank.pokemon.presentation.pokemon_detail

sealed interface PokemonDetailsEvent {
    data class GetPokemonDetails(val id: Int) : PokemonDetailsEvent
}