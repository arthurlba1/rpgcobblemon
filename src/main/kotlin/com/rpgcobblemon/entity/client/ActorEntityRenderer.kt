package com.rpgcobblemon.entity.client

import com.rpgcobblemon.entity.actor.ActorEntity
import com.rpgcobblemon.util.rpgCobblemonResource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.client.renderer.entity.HumanoidMobRenderer
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.geom.ModelLayers

class ActorEntityRenderer(context: EntityRendererProvider.Context)
    : HumanoidMobRenderer<ActorEntity, HumanoidModel<ActorEntity>>(
    context,
    HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)),
    0.5f
) {
    override fun getTextureLocation(entity: ActorEntity): ResourceLocation {
        return rpgCobblemonResource("textures/entity/actor/britney.png")
    }
}
