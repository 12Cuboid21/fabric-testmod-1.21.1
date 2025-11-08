package com.cuboidlabs.testmod;

import com.cuboidlabs.testmod.entity.ModEntities;
import com.cuboidlabs.testmod.entity.client.LightCycleModel;
import com.cuboidlabs.testmod.entity.client.LightCycleRenderer;
import com.cuboidlabs.testmod.util.ModModelPredicates;
import com.cuboidlabs.testmod.util.RendererManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.util.Identifier;

public class TestModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
        EntityModelLayerRegistry.registerModelLayer(LightCycleModel.LIGHT_CYCLE, LightCycleModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.LIGHT_CYCLE, LightCycleRenderer::new);
    }
}
