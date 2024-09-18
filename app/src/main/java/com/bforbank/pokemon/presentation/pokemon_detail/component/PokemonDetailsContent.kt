package com.bforbank.pokemon.presentation.pokemon_detail.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bforbank.pokemon.presentation.pokemon_detail.PokemonDetailsEvent
import com.bforbank.pokemon.presentation.pokemon_detail.PokemonDetailsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsContent(
    id: Int,
    state: PokemonDetailsState,
    handleEvent: (PokemonDetailsEvent) -> Unit = {},
    onBackClicked: () -> Unit = {}
) {
    LaunchedEffect(true) {
        handleEvent(PokemonDetailsEvent.GetPokemonDetails(id))
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back button",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                onBackClicked()
                            },
                        tint = Color.White
                    )
                },
                title = {
                    Text(
                        color = Color.White,
                        text = state.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
    ) { innerPadding ->
        PokemonDetailsComponent(state, innerPadding)
    }
}

@Composable
fun PokemonDetailsComponent(state: PokemonDetailsState, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Log.e("TAG", "PokemonDetailsComponent: ${state.picture}")
        Image(
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            painter = rememberAsyncImagePainter(state.picture),
            contentDescription = null
        )
        Text(
            text = "Details",
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = "Order: ${state.order}")
        Text(text = "Weight: ${state.weight}")
        Text(text = "Height: ${state.height}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    name: String,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.background(Color.White),
        title = {

        },
        navigationIcon = {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back button",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onBackClicked()
                    }
            )
        }
    )
}
