package com.rpgcobblemon.data

import com.google.gson.Gson
import com.rpgcobblemon.util.rpgCobblemonResource
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import org.slf4j.LoggerFactory

object ActorDataLoader : SimpleSynchronousResourceReloadListener {
    private val logger = LoggerFactory.getLogger("ActorDataLoader")
    private val gson = Gson()

    override fun getFabricId(): ResourceLocation = rpgCobblemonResource("actor_data_loader")

    override fun onResourceManagerReload(resourceManager: ResourceManager) {
        val loadedActors = mutableListOf<ActorData>()
        val basePath = "actors"

        for (resource in resourceManager.listResources(basePath) { it.path.endsWith(".json") }) {
            resourceManager.getResource(resource.key).ifPresent { res ->
                res.openAsReader().use { reader ->
                    val data = gson.fromJson(reader, Array<ActorData>::class.java)
                    loadedActors.addAll(data)
                }
            }
        }

        ActorDataManager.actors.clear()
        ActorDataManager.actors.addAll(loadedActors)

        logger.info("Loaded ${loadedActors.size} actor(s) from JSON")
    }
}