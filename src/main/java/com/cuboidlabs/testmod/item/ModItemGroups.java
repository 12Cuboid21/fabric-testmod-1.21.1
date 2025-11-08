package com.cuboidlabs.testmod.item;

import com.cuboidlabs.testmod.TestMod;
import com.cuboidlabs.testmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TEST_MOD_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TestMod.MOD_ID, "test_mod_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.PHOTON))
                    .displayName(Text.translatable("itemgroup.testmod.test_mod_items"))
                    .entries((displayContext, entries) -> {
                      entries.add(ModItems.PHOTON);
                      entries.add(ModItems.LIGHT_RAY);
                      entries.add(ModItems.PHOTONOBRUSH);
                      entries.add(ModItems.MAGICALFUD);
                      entries.add(ModItems.SHADER_SPAWNER);
                      entries.add(ModItems.LIGHT_CYCLE_SPAWNER);
                    }).build());

    public static final ItemGroup TEST_MOD_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TestMod.MOD_ID, "test_mod_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.TEST_BLOCK))
                    .displayName(Text.translatable("itemgroup.testmod.test_mod_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.TEST_BLOCK);
                        entries.add(ModBlocks.TESI_BLOCK);
                        entries.add(ModBlocks.MAGIC_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        TestMod.LOGGER.info("Registering Mod Item Groups for " + TestMod.MOD_ID);
    }
}
