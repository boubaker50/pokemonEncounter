package com.bforbank.pokemon.data.remote.dto.pokemon_list

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    val picture: String
)