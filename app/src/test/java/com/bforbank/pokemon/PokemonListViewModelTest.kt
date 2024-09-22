package com.bforbank.pokemon

import com.bforbank.pokemon.domain.model.Pokemon
import com.bforbank.pokemon.domain.model.PokemonList
import com.bforbank.pokemon.domain.use_case.get_pokemons.GetPokemonList
import com.bforbank.pokemon.presentation.pokemonlist.PokemonListEvent
import com.bforbank.pokemon.presentation.pokemonlist.PokemonListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonListViewModelTest {

    private val mockPokemonListUseCase = mockk<GetPokemonList>()
    private lateinit var viewModel: PokemonListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify initial load of Pokemon list`() = runTest {
        // Given
        val expectedPokemonList = listOf(
            Pokemon(0, "Pikachu"),
            Pokemon(1, "Charmander")
        )
        val mockResponse = PokemonList(
            pokemonList = expectedPokemonList,
            pokemonCount = expectedPokemonList.size
        )
        coEvery { mockPokemonListUseCase(any()) } returns mockResponse
        viewModel = PokemonListViewModel(mockPokemonListUseCase)

        // When
        // Advance the coroutine clock to let the async operations finish
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        assertEquals(expectedPokemonList, state.pokemonList)
        assertEquals(expectedPokemonList.size, state.pokemonCount)
        assertEquals(false, state.isLoading)
    }

    @Test
    fun `verify loader is visible when isLoading is true`() = runTest {
        // Given
        val initialPokemonList = listOf(
            Pokemon(0, "Pikachu")
        )
        val initialResponse = PokemonList(
            pokemonList = initialPokemonList,
            pokemonCount = initialPokemonList.size
        )
        coEvery { mockPokemonListUseCase(0) } coAnswers {
            delay(2000)
            initialResponse
        }
        viewModel = PokemonListViewModel(mockPokemonListUseCase)
        // When
        advanceTimeBy(100)
        // Then first step to see the loader
        assertEquals(true, viewModel.state.value.isLoading)

        // Then second step to hide the loader
        advanceUntilIdle()
        assertEquals(false, viewModel.state.value.isLoading)
        // I know it's better if i seperate to get every senario in a seperate Unit Test
    }


    @Test
    fun `verify Pokemon list grows when NextClicked event is triggered multiple times`() = runTest {
        // Given
        val initialPokemonList = listOf(Pokemon(0, "Pikachu"))
        val additionalPokemonList = listOf(
            Pokemon(0, "Charmander"),
            Pokemon(1, "Bulbasaur")
        )
        val initialResponse = PokemonList(
            pokemonList = initialPokemonList,
            pokemonCount = initialPokemonList.size
        )
        val additionalResponse = PokemonList(
            pokemonList = additionalPokemonList,
            pokemonCount = initialPokemonList.size + additionalPokemonList.size
        )

        coEvery { mockPokemonListUseCase(0) } returns initialResponse
        coEvery { mockPokemonListUseCase(10) } returns additionalResponse

        // Initialize the view model
        viewModel = PokemonListViewModel(mockPokemonListUseCase)

        // When triggering NextClicked
        viewModel.handleEvent(PokemonListEvent.NextClicked(10))
        advanceUntilIdle()

        // Then
        val state = viewModel.state.value
        assertEquals(initialPokemonList + additionalPokemonList, state.pokemonList)
        assertEquals(initialPokemonList.size + additionalPokemonList.size, state.pokemonCount)
        assertEquals(false, state.isLoading)
    }

    @After
    fun tearDown() {
        // Reset the dispatcher to its original state
        Dispatchers.resetMain()
    }
}
