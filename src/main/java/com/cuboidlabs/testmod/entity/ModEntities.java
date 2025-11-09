package com.cuboidlabs.testmod.entity;

import com.cuboidlabs.testmod.TestMod;
import com.cuboidlabs.testmod.entity.custom.LightCycleEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<LightCycleEntity> LIGHT_CYCLE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TestMod.MOD_ID, "light_cycle"),
            EntityType.Builder.create(LightCycleEntity::new, SpawnGroup.MISC)
                    .dimensions(1.2f, 0.7f).build());

    public static void registerModEntities() {
        TestMod.LOGGER.info("Registering Mod Entities: " + TestMod.MOD_ID);
    }
}
