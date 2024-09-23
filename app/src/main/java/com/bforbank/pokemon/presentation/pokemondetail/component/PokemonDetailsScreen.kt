package com.bforbank.pokemon.presentation.pokemondetail.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bforbank.pokemon.presentation.common.ModalLoading
import com.bforbank.pokemon.presentation.pokemondetail.PokemonDetailsViewModel

@Composable
fun PokemonDetailsScreen(
    navController: NavController,
    id: Int
) {
    val viewModel: PokemonDetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    PokemonDetailsContent(
        id = id,
        state = state,
        handleEvent = viewModel::handleEvent,
        onBackClicked = {
            navController.popBackStack()
        }
    )
    if (state.isLoading) {
        ModalLoading()
    }
}