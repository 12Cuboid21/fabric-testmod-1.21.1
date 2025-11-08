package com.cuboidlabs.testmod.entity.custom;

import com.cuboidlabs.testmod.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.lang.reflect.Type;

public class LightCycleEntity extends VehicleEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private float cycleHealth = 20f;
    private float cycleSpeed = 0.0f;

    public LightCycleEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    public void damageCycle(float amount) {
        cycleHealth -= amount;
        if (cycleHealth <= 0) {
            this.kill();
            this.dropItem(ModItems.LIGHT_CYCLE_SPAWNER);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            this.setupAnimationStates();
        }

        if (this.getFirstPassenger() instanceof PlayerEntity player) {
            // Simple forward acceleration
            speed += 0.02f;
            speed = Math.min(speed, 1.0f); // max speed

            this.setVelocity(this.getRotationVector().multiply(speed));
            this.move(MovementType.SELF, this.getVelocity());
        } else {
            speed *= 0.9f; // slow down when empty
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 100;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public Item asItem() {
        return ModItems.LIGHT_CYCLE_SPAWNER;
    }
}
