package com.bforbank.pokemon.presentation.pokemon_list.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bforbank.pokemon.presentation.PokemonDetailsRoute
import com.bforbank.pokemon.presentation.common.ModalLoading
import com.bforbank.pokemon.presentation.pokemon_list.PokemonListViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    PokemonListContent(
        onClick = { id ->
            navController.navigate(PokemonDetailsRoute(id))
        },
        state = state,
        handleEvent = viewModel::handleEvent
    )
    if (state.isLoading) {
        ModalLoading()
    }
}