package com.cuboidlabs.testmod.datagen;

import com.cuboidlabs.testmod.block.ModBlocks;
import com.cuboidlabs.testmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TESI_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TEST_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LIGHT_RAY, Models.GENERATED);
        itemModelGenerator.register(ModItems.PHOTON, Models.GENERATED);
        //itemModelGenerator.register(ModItems.PHOTONOBRUSH, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGICALFUD, Models.GENERATED);
        itemModelGenerator.register(ModItems.POWDERED_PHOTONS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHADER_SPAWNER, Models.GENERATED);
    }
}
