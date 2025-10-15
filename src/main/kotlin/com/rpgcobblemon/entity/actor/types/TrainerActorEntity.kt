package com.rpgcobblemon.entity.actor.types


import com.rpgcobblemon.entity.actor.ActorEntity
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.level.Level
import net.minecraft.world.entity.player.Player

class TrainerActorEntity(
    type: EntityType<out PathfinderMob>,
    world: Level
) : ActorEntity(type, world) {

    override fun handleInteraction(player: Player) {
        player.sendSystemMessage(
            Component.literal("Me chamo ${this.actorName} e sou um treinador! Vamos batalhar!")
        )
    }
}