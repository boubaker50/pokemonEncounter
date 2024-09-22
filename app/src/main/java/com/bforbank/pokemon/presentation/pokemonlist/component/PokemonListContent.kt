package com.bforbank.pokemon.presentation.pokemonlist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bforbank.pokemon.domain.model.Pokemon
import com.bforbank.pokemon.presentation.pokemonlist.PokemonListEvent
import com.bforbank.pokemon.presentation.pokemonlist.PokemonListState

@Composable
fun PokemonListContent(
    onClick: (Int) -> Unit = {},
    state: PokemonListState = PokemonListState(),
    handleEvent: (PokemonListEvent) -> Unit = {}
) {
    Scaffold(
        topBar = { PokemonListTopBar() },
        bottomBar = {
            PokemonListBottomBar(
                size = state.pokemonList.size,
                count = state.pokemonCount,
                nextEnabled = state.nextEnabled,
                nextClicked = {
                    handleEvent(PokemonListEvent.NextClicked(state.pokemonList.size))
                }
            )
        }
    ) { innerPadding ->
        PokemonList(
            onClick = onClick,
            list = state.pokemonList,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun PokemonListTopBar() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            painter =
            rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/media/master/logo/pokeapi_256.png"),
            contentDescription = null
        )
        Text(
            text = "Pokemon List",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun PokemonList(list: List<Pokemon>, modifier: Modifier, onClick: (Int) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(list.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                for (item in rowItems) {
                    PokemonListItem(
                        onClick = onClick,
                        pokemon = item,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}


@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    modifier: Modifier,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray)
            .padding(8.dp)
            .clickable {
                pokemon.id?.let { onClick(it) }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = pokemon.id.toString()
        )
        Text(text = pokemon.name)
    }
}

@Composable
fun PokemonListBottomBar(
    size: Int,
    count: Int,
    nextEnabled: Boolean,
    nextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 12.dp,
                end = 12.dp,
                bottom = 12.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("$size / $count Pokemon loaded")
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { nextClicked() },
            enabled = nextEnabled
        ) {
            Text("Next")
        }
    }
}

@Preview
@Composable
private fun PokemonListContentPreview() {
    PokemonListContent()
}