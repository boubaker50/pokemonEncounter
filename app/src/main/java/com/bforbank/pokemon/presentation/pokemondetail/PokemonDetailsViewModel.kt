package com.bforbank.pokemon.presentation.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bforbank.pokemon.domain.use_case.get_pokemon_by_id.GetPokemonById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonById: GetPokemonById
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonDetailsState())
    val state: StateFlow<PokemonDetailsState> = _state.asStateFlow()

    fun handleEvent(event: PokemonDetailsEvent) {
        when (event) {
            is PokemonDetailsEvent.GetPokemonDetails -> getPokemonDetails(event.id)
        }
    }

    private fun getPokemonDetails(id: Int) {
        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                getPokemonById(id)
            }.onSuccess { pokemonDetails ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        name = pokemonDetails.name,
                        picture = pokemonDetails.picture,
                        order = pokemonDetails.order,
                        height = pokemonDetails.height,
                        weight = pokemonDetails.weight
                    )
                }
            }
                .onFailure {
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}