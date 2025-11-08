package com.cuboidlabs.testmod.item.custom;

import com.cuboidlabs.testmod.entity.ModEntities;
import com.cuboidlabs.testmod.entity.custom.LightCycleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LightCycleSpawnerItem extends Item {
    public LightCycleSpawnerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            Vec3d look = user.getRotationVector();
            Vec3d spawnPos = user.getPos().add(look.multiply(1.5)).add(0, 0.1, 0);

            LightCycleEntity bike = new LightCycleEntity(ModEntities.LIGHT_CYCLE, world);

            bike.refreshPositionAndAngles(
                    spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(),
                    user.getYaw(), 0
            );

            world.spawnEntity(bike);
            stack.decrement(1);
        }
        return TypedActionResult.success(stack);
    }
}
