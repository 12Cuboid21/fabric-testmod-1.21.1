package com.cuboidlabs.testmod.entity.client;

import com.cuboidlabs.testmod.TestMod;
import com.cuboidlabs.testmod.entity.custom.LightCycleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class LightCycleModel<T extends LightCycleEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer LIGHT_CYCLE = new EntityModelLayer(Identifier.of(TestMod.MOD_ID, "light_cycle"), "main");

    private final ModelPart root;
    private final ModelPart bikeBody;
    private final ModelPart wheelNew1;
    private final ModelPart wheelNew2;
    public LightCycleModel(ModelPart root) {
        this.root = root.getChild("root");
        this.bikeBody = this.root.getChild("bikeBody");
        this.wheelNew1 = this.root.getChild("wheelNew1");
        this.wheelNew2 = this.root.getChild("wheelNew2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.6667F, 0.0F));

        ModelPartData bikeBody = root.addChild("bikeBody", ModelPartBuilder.create().uv(0, 1).cuboid(-3.0F, -5.0F, -9.0F, 6.0F, 3.0F, 19.0F, new Dilation(0.0F))
                .uv(20, 50).cuboid(-1.0F, -5.0F, -10.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 1).cuboid(-1.0F, -6.0F, 8.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(1, 24).cuboid(-3.0F, -7.0F, -8.0F, 6.0F, 2.0F, 16.0F, new Dilation(0.0F))
                .uv(13, 32).cuboid(-2.0F, -8.0F, -3.0F, 4.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 3).cuboid(-4.0F, -6.0F, -2.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 3).cuboid(3.0F, -6.0F, -2.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 27).cuboid(-2.0F, -2.0F, -6.0F, 4.0F, 1.0F, 13.0F, new Dilation(0.0F))
                .uv(20, 0).cuboid(-1.0F, -6.0F, -9.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 0).cuboid(-2.0F, -9.0F, -6.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 0).cuboid(-2.0F, -8.0F, -7.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(18, 0).cuboid(-2.0F, -9.0F, -5.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(17, 0).cuboid(-2.0F, -10.0F, -5.0F, 4.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 36).cuboid(-2.0F, -10.0F, -5.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(17, 0).cuboid(-1.0F, -11.0F, -3.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.3333F, 0.0F));

        ModelPartData wheelNew1 = root.addChild("wheelNew1", ModelPartBuilder.create().uv(0, 54).cuboid(-2.0F, -2.5F, -2.5F, 4.0F, 5.0F, 5.0F, new Dilation(0.0F))
                .uv(58, 0).cuboid(-1.0F, -1.5F, 2.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(58, 0).cuboid(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(31, 0).cuboid(-1.0F, -3.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(31, 0).cuboid(-1.0F, 2.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.1667F, 9.5F));

        ModelPartData wheelNew2 = root.addChild("wheelNew2", ModelPartBuilder.create().uv(0, 54).cuboid(-2.0F, -2.5F, -2.5F, 4.0F, 5.0F, 5.0F, new Dilation(0.0F))
                .uv(58, 0).cuboid(-1.0F, -1.5F, 2.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(58, 0).cuboid(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(31, 0).cuboid(-1.0F, -3.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(31, 0).cuboid(-1.0F, 2.5F, -1.5F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.1667F, -9.5F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(LightCycleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setWheelAngles(netHeadYaw);

        this.animateMovement(LightCycleAnimations.LIGHT_CYCLE_DRIVEFORWARD, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, LightCycleAnimations.LIGHT_CYCLE_IDLE, ageInTicks, 1f);
    }

    private void setWheelAngles(float wheelYaw) {
        wheelYaw = MathHelper.clamp(wheelYaw, -30.0f, 30.0f);
        this.wheelNew1.yaw = wheelYaw * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}
