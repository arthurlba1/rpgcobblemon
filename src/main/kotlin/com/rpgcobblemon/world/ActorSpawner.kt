package com.rpgcobblemon.world

import com.rpgcobblemon.data.ActorData
import com.rpgcobblemon.data.ActorDataManager
import com.rpgcobblemon.entity.ModEntities
import com.rpgcobblemon.entity.actor.ActorType
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import java.util.Locale.getDefault

object ActorSpawner {
    fun spawnAllActors(server: MinecraftServer) {
        for (actor in ActorDataManager.actors) {
            val dimensionKey = ResourceKey.create(
                Registries.DIMENSION,
                ResourceLocation.tryParse(actor.world ?: "minecraft:overworld")!!
            )

            val world = server.getLevel(dimensionKey) ?: continue
            val state = ActorPersistentState.get(world)

            if (state.spawnedActors.contains(actor.id)) continue

            val pos = actor.position?.let {
                BlockPos(it.x.toInt(), it.y.toInt(), it.z.toInt())
            } ?: world.sharedSpawnPos

            spawnActorEntity(actor, world, pos)
            state.spawnedActors.add(actor.id)
            state.setDirty()
        }
    }

    private fun spawnActorEntity(actor: ActorData, world: ServerLevel, pos: BlockPos) {
        val entityType = when (actor.type) {
            ActorType.TRAINER.name.lowercase(getDefault()) -> ModEntities.TRAINER_ACTOR_ENTITY
            else -> return
        }

        val entity = entityType.create(world) ?: return
        entity.moveTo(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5, 0f, 0f)
        entity.customName = net.minecraft.network.chat.Component.literal(actor.name)
        entity.isCustomNameVisible = true
        world.addFreshEntity(entity)
    }
}