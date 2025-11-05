package com.cuboidlabs.testmod.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RendererManager {
    private static float totalTickDelta = 0f;
    private static boolean registered = false;
    public static void renderTriangles() {
        if (registered == true) {
            System.out.println("renderTriangles was already called once.");
            return;
        }

        registered = true;

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            Tessellator tessellator = Tessellator.getInstance();
            MatrixStack matrices = drawContext.getMatrices();

            totalTickDelta += tickDelta.getTickDelta(true);
            System.out.println("TickDelta: " + tickDelta.getTickDelta(true) + ", Total: " + totalTickDelta);

            matrices.push();
            Matrix4f transformationMatrix = matrices.peek().getPositionMatrix();

            //rotation
            float rotAmount = (float) (totalTickDelta / 50F % 360);
            matrices.multiply(RotationAxis.POSITIVE_Z.rotation(rotAmount));

            matrices.translate(-20f, -40f, 0f);
            //rotation end

            float scaleAmount = MathHelper.sin(totalTickDelta / 10F) / 2F + 1.5F;
            System.out.println(scaleAmount);

            matrices.scale(scaleAmount, scaleAmount, 1F);

            BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);

            buffer.vertex(transformationMatrix, 20, 20, 5).color(1, 255, 14,255);
            buffer.vertex(transformationMatrix, 5, 40, 5).color(1, 255, 196,255);
            buffer.vertex(transformationMatrix, 35, 40, 5).color(1, 255, 196,255);
            buffer.vertex(transformationMatrix, 20, 60, 5).color(1, 255, 14,255);

            RenderSystem.setShader(GameRenderer::getPositionColorProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            BufferRenderer.drawWithGlobalProgram(buffer.end());

            matrices.pop();
        });
    }
}
