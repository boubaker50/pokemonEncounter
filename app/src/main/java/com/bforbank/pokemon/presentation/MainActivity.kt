package com.bforbank.pokemon.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bforbank.pokemon.presentation.pokemon_detail.component.PokemonDetailsScreen
import com.bforbank.pokemon.presentation.pokemon_list.component.PokemonListScreen
import com.bforbank.pokemon.presentation.ui.theme.PokemonBforBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            PokemonBforBTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = PokemonListRoute
                    ) {
                        composable<PokemonListRoute> {
                            PokemonListScreen(navController = navController)
                        }
                        composable<PokemonDetailsRoute> {
                            val arguments = it.toRoute<PokemonDetailsRoute>()
                            PokemonDetailsScreen(
                                navController = navController,
                                id = arguments.id
                            )
                        }
                    }
                }
            }
        }
    }
}