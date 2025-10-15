package com.rpgcobblemon.entity.actor

import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level


open class ActorEntity(
    type: EntityType<out PathfinderMob>,
    level: Level
) : PathfinderMob(type, level) {
    var actorType: ActorType = ActorType.GENERIC
    var actorName: String = "Unknown"
    var actorRole: String = "none"

    companion object {
        fun createAttributes(): AttributeSupplier.Builder {
            return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
        }
    }

    override fun registerGoals() {
        this.goalSelector.addGoal(1, FloatGoal(this))
        this.goalSelector.addGoal(2, LookAtPlayerGoal(this, Player::class.java, 8.0f))
        this.goalSelector.addGoal(3, RandomStrollGoal(this, 0.7))
    }

    override fun mobInteract(player: Player, interactionHand: InteractionHand) : InteractionResult {
        if (!level().isClientSide) {
            handleInteraction(player)
        }

        return InteractionResult.SUCCESS
    }

    open fun handleInteraction(player: Player) {
        player.sendSystemMessage(Component.literal("Olá, eu sou ${this.actorName}"))
    }

    override fun tick() {
        super.tick()
    }
}
