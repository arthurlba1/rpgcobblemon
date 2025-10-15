package com.rpgcobblemon.data

import kotlinx.serialization.Serializable

@Serializable
data class ActorData(
    val id: String,
    val name: String,
    val world: String? = "minecraft:overworld",
    val position: ActorPosition? = null,
    val skin: String? = null,
    val level: Int = 1,
    val type: String,
    val role: String,
    val team: List<PokemonData>? = null
)

@Serializable
data class ActorPosition(val x: Double, val y: Double, val z: Double)

@Serializable
data class PokemonData(
    val species: String,
    val level: Int,
    val moves: List<String>? = null
)
