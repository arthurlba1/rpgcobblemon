package com.rpgcobblemon.util

import com.rpgcobblemon.RPGCobblemon
import net.minecraft.resources.ResourceLocation

fun rpgCobblemonResource(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(RPGCobblemon.MOD_ID, path)