package com.cuboidlabs.testmod.entity.client;

import com.cuboidlabs.testmod.TestMod;
import com.cuboidlabs.testmod.entity.custom.LightCycleEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LightCycleRenderer extends EntityRenderer<LightCycleEntity> {
    private final LightCycleModel<LightCycleEntity> model;

    public LightCycleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new LightCycleModel<>(ctx.getPart(LightCycleModel.LIGHT_CYCLE));
    }

    @Override
    public Identifier getTexture(LightCycleEntity entity) {
        return Identifier.of(TestMod.MOD_ID, "textures/entity/light_cycle/light_cycle.png");
    }

    @Override
    public void render(LightCycleEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.scale(1f, 1f, 1f);
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
