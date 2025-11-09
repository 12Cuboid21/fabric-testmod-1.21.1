package com.cuboidlabs.testmod;

import com.cuboidlabs.testmod.block.ModBlocks;
import com.cuboidlabs.testmod.component.ModDataComponentTypes;
import com.cuboidlabs.testmod.entity.ModEntities;
import com.cuboidlabs.testmod.entity.custom.LightCycleEntity;
import com.cuboidlabs.testmod.item.ModItemGroups;
import com.cuboidlabs.testmod.item.ModItems;
import com.cuboidlabs.testmod.util.ModModelPredicates;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModDataComponentTypes.registerDataComponentTypes();
        ModEntities.registerModEntities();

		FuelRegistry.INSTANCE.add(ModItems.POWDERED_PHOTONS, 5000);

		FabricDefaultAttributeRegistry.register(ModEntities.LIGHT_CYCLE, LightCycleEntity.createAttributes());
	}
}