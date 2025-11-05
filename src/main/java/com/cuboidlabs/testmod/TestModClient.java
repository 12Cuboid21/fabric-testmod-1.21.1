package com.cuboidlabs.testmod;

import com.cuboidlabs.testmod.util.ModModelPredicates;
import com.cuboidlabs.testmod.util.RendererManager;
import net.fabricmc.api.ClientModInitializer;

public class TestModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
        RendererManager.renderTriangles();
    }
}
