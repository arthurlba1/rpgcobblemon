package com.rpgcobblemon

import com.rpgcobblemon.entity.ModEntities
import com.rpgcobblemon.entity.client.ActorEntityRenderer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

class RPGCobblemonClient : ClientModInitializer {
    override fun onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.ACTOR_ENTITY) { ctx ->
            ActorEntityRenderer(ctx)
        }

        EntityRendererRegistry.register(ModEntities.TRAINER_ACTOR_ENTITY) { ctx ->
            ActorEntityRenderer(ctx)
        }
    }
}
