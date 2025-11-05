package com.cuboidlabs.testmod.item;

import com.cuboidlabs.testmod.TestMod;
import com.cuboidlabs.testmod.item.custom.PhotonoBrushItem;
import com.cuboidlabs.testmod.item.custom.ShaderSpawnerItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {
    public static final Item PHOTON = registerItem("photon", new Item(new Item.Settings()){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if(Screen.hasShiftDown()) {
                tooltip.add(Text.translatable("tooltip.testmod.photon.shift_down"));
            } else {
                tooltip.add(Text.translatable("tooltip.testmod.photon.shift_up"));
            }
            super.appendTooltip(stack, context, tooltip, type);
        }
    });
    public static final Item LIGHT_RAY = registerItem("light_ray", new Item(new Item.Settings()));
    public static final Item PHOTONOBRUSH = registerItem("photonobrush", new PhotonoBrushItem(new Item.Settings().maxDamage(16)));
    public static final Item MAGICALFUD = registerItem("magicalfud", new Item(new Item.Settings().food(ModFoodComponents.MAGICALIFUD)));
    public static final Item POWDERED_PHOTONS = registerItem("powdered_photons", new Item( new Item.Settings()));
    public static final Item SHADER_SPAWNER = registerItem("shader_spawner", new ShaderSpawnerItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TestMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TestMod.LOGGER.info("Registering Mod Items for " + TestMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PHOTON);
            entries.add(LIGHT_RAY);
        });
    }
}
