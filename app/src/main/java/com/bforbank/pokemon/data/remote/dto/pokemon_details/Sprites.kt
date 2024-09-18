package com.bforbank.pokemon.data.remote.dto.pokemon_details

data class Sprites(
    val back_default: String,
    val other: Other,
)

data class Other(
    val home: PictureUrl,
)

data class PictureUrl(
    val front_default: String,
)