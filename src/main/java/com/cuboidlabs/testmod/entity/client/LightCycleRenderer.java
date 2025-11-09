package com.cuboidlabs.testmod.entity.client;

import com.cuboidlabs.testmod.TestMod;
import com.cuboidlabs.testmod.entity.custom.LightCycleEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.awt.*;

public class LightCycleRenderer extends MobEntityRenderer<LightCycleEntity, LightCycleModel<LightCycleEntity>> {

    public LightCycleRenderer(EntityRendererFactory.Context context) {
        super(context, new LightCycleModel<>(context.getPart(LightCycleModel.LIGHT_CYCLE)), 0.5f);
    }

    @Override
    public Identifier getTexture(LightCycleEntity entity) {
        return Identifier.of(TestMod.MOD_ID, "textures/entity/light_cycle/light_cycle.png");
    }

    @Override
    public void render(LightCycleEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        float cycleSize = 2f;

        matrixStack.scale(cycleSize, cycleSize, cycleSize);

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
