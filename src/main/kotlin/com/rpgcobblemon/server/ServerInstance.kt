package com.rpgcobblemon.server

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.MinecraftServer

object ServerInstance {
    private var server: MinecraftServer? = null

    init {
        ServerLifecycleEvents.SERVER_STARTED.register { srv ->
            server = srv
        }
        ServerLifecycleEvents.SERVER_STOPPED.register {
            server = null
        }
    }

    fun get(): MinecraftServer? = server
}
