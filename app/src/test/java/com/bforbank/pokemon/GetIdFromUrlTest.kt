package com.bforbank.pokemon

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bforbank.pokemon.data.remote.dto.pokemon_list.extractPokemonIdOrNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetIdFromUrlTest {

    @Test
    fun `test extractPokemonIdOrNull with valid URL`() {
        val url = "https://pokeapi.co/api/v2/pokemon/25"
        val result = url.extractPokemonIdOrNull()
        assertEquals(25, result)
    }

    @Test
    fun `test extractPokemonIdOrNull with invalid URL`() {
        val url = "https://pokeapi.co/api/v2/pikachu/25"
        val result = url.extractPokemonIdOrNull()
        assertNull(result)
    }

    @Test
    fun `test extractPokemonIdOrNull with partial URL`() {
        val url = "https://pokeapi.co/api/v2/pokemon/"
        val result = url.extractPokemonIdOrNull()
        assertNull(result)
    }

    @Test
    fun `test extractPokemonIdOrNull with empty string`() {
        val url = ""
        val result = url.extractPokemonIdOrNull()
        assertNull(result)
    }

    @Test
    fun `test extractPokemonIdOrNull with unrelated string`() {
        val url = "This is not a pokemon URL"
        val result = url.extractPokemonIdOrNull()
        assertNull(result)
    }
}