package com.bforbank.pokemon.presentation.pokemondetail

sealed interface PokemonDetailsEvent {
    data class GetPokemonDetails(val id: Int) : PokemonDetailsEvent
}