package com.bforbank.pokemon.domain.use_case.get_pokemon_by_id

import com.bforbank.pokemon.data.remote.dto.pokemon_details.toPokemonDetails
import com.bforbank.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonById @Inject constructor(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(id: Int) = repository.getPokemonById(id).toPokemonDetails()
}