package com.bforbank.pokemon.domain.use_case.get_pokemons

import com.bforbank.pokemon.common.Constants.PAGE_SIZE
import com.bforbank.pokemon.data.remote.dto.pokemon_list.toPokemonList
import com.bforbank.pokemon.domain.model.PokemonList
import com.bforbank.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(offset: Int): PokemonList =
        repository.getPokemonList(limit = PAGE_SIZE, offset = offset).toPokemonList()
}