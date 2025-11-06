package com.cuboidlabs.testmod;

import com.cuboidlabs.testmod.util.ModModelPredicates;
import com.cuboidlabs.testmod.util.RendererManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Identifier;

public class TestModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
    }
}
