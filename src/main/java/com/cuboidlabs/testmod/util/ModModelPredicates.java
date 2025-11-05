package com.cuboidlabs.testmod.util;

import com.cuboidlabs.testmod.TestMod;
import com.cuboidlabs.testmod.component.ModDataComponentTypes;
import com.cuboidlabs.testmod.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.PHOTONOBRUSH, Identifier.of(TestMod.MOD_ID, "chargelevel"),
                (stack, world, entity, seed) -> {
                    Integer charge = stack.get(ModDataComponentTypes.CHARGE_LEVEL);
                    System.out.println("Photonobrush charge predicate: " + charge);
                    if (charge == null) charge = 0;
                    return charge / 2.0f;
        });
    }
}
