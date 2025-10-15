package com.rpgcobblemon.entity

import com.rpgcobblemon.RPGCobblemon
import com.rpgcobblemon.entity.actor.ActorEntity
import com.rpgcobblemon.entity.actor.types.TrainerActorEntity
import com.rpgcobblemon.util.rpgCobblemonResource
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory

object ModEntities {
    val registry: Registry<EntityType<*>> = BuiltInRegistries.ENTITY_TYPE

    val ACTOR_KEY = rpgCobblemonResource("actor")
    val ACTOR_ENTITY: EntityType<ActorEntity> = Registry.register(
        registry,
        ACTOR_KEY,
        EntityType.Builder.of(::ActorEntity, MobCategory.CREATURE)
            .sized(0.6f, 1.8f)
            .build(ACTOR_KEY.toString())
    )

    val TRAINER_ACTOR_KEY = rpgCobblemonResource("trainer_actor")
    val TRAINER_ACTOR_ENTITY: EntityType<TrainerActorEntity> = Registry.register(
        registry,
        TRAINER_ACTOR_KEY,
        EntityType.Builder.of(::TrainerActorEntity, MobCategory.CREATURE)
            .sized(0.6f, 1.8f)
            .build(TRAINER_ACTOR_KEY.toString())
    )

    fun register() {
        RPGCobblemon.logger.info("Registering entities to ${RPGCobblemon.MOD_ID}}")

        FabricDefaultAttributeRegistry.register(ACTOR_ENTITY, ActorEntity.createAttributes())
        FabricDefaultAttributeRegistry.register(TRAINER_ACTOR_ENTITY, ActorEntity.createAttributes())
    }
}
