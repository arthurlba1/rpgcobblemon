package com.rpgcobblemon.world

import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.datafix.DataFixTypes
import net.minecraft.world.level.saveddata.SavedData

class ActorPersistentState : SavedData() {
    val spawnedActors = mutableSetOf<String>()

    override fun save(tag: CompoundTag, provider: HolderLookup.Provider): CompoundTag {
        val list = ListTag()
        spawnedActors.forEach { list.add(StringTag.valueOf(it)) }
        tag.put("SpawnedActors", list)
        return tag
    }

    companion object {
        fun get(world: ServerLevel): ActorPersistentState {
            val storage = world.dataStorage

            val factory = Factory(
                { ActorPersistentState() },
                { nbt: CompoundTag, _: HolderLookup.Provider? ->
                    val state = ActorPersistentState()
                    val list = nbt.getList("SpawnedActors", 8) // 8 = TAG_STRING
                    for (i in 0 until list.size) {
                        state.spawnedActors.add((list[i] as StringTag).asString)
                    }
                    state
                },
                DataFixTypes.SAVED_DATA_MAP_DATA
            )

            return storage.computeIfAbsent(factory, "rpgcobblemon_actor_state")
        }
    }
}