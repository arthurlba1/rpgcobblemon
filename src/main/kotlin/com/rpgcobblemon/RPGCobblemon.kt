package com.rpgcobblemon

import com.rpgcobblemon.data.ActorDataLoader
import com.rpgcobblemon.entity.ModEntities
import com.rpgcobblemon.world.ActorSpawner
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.server.packs.PackType

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object RPGCobblemon : ModInitializer {
    const val MOD_ID = "rpgcobblemon"
    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(ActorDataLoader)

        ModEntities.register()

        ServerLifecycleEvents.SERVER_STARTED.register { server ->
            ActorSpawner.spawnAllActors(server)
        }
    }
}
