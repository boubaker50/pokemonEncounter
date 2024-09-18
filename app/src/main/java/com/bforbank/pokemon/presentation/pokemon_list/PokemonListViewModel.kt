package com.bforbank.pokemon.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bforbank.pokemon.domain.use_case.get_pokemons.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListUseCase: GetPokemonList
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state.asStateFlow()

    init {
        getPokemonList(0)
    }

    fun handleEvent(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.NextClicked -> getPokemonList(event.offset)
        }
    }

    fun getPokemonList(
        offset: Int
    ) {
        viewModelScope.launch {
            runCatching {
                _state.update { it.copy(isLoading = true) }
                pokemonListUseCase(offset)
            }.onSuccess { pokemonList ->
                _state.update {
                    it.copy(
                        pokemonList = it.pokemonList + pokemonList.pokemonList,
                        isLoading = false,
                        pokemonCount = pokemonList.pokemonCount
                    )
                }
            }.onFailure {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}