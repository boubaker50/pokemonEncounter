package com.bforbank.pokemon.data.remote.dto.pokemon_details

import kotlinx.serialization.SerialName

data class Species(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)