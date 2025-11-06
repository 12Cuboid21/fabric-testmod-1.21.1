package com.cuboidlabs.testmod.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
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
            matrices.translate(-20f, -40f, 0f);
            Matrix4f transformationMatrix = matrices.peek().getPositionMatrix();

            //rotation
            float rotAmount = (float) (totalTickDelta / 50F % 360);
            matrices.multiply(RotationAxis.POSITIVE_Z.rotation(rotAmount));

            //matrices.translate(-20f, -40f, 0f);
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

    public static void drawContextTest() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            int rectPosX = 10;
            int rectPosY = 10;
            int rectWidth = 100;
            int rectHeight = 50;

            //drawing the rect
            drawContext.fill(rectPosX, rectPosY, rectPosX + rectWidth, rectPosY + rectHeight, 0xFF52C613);

            //drawing the border
            drawContext.drawBorder(rectPosX, rectPosY, rectWidth, rectHeight, 0xFF13C6BD);

            //draw vertical line
            drawContext.drawVerticalLine(rectPosX + rectWidth / 2, rectPosY, rectPosY + rectHeight, 0xFFC61394);
        });
    }

    public static void scissorManagerTest() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            int scissorRegionX = 200;
            int scissorRegionY = 20;
            int scissorRegionWidth = 50;

            int scissorRegionHeight = 100;

            drawContext.enableScissor(scissorRegionX, scissorRegionY, scissorRegionX + scissorRegionWidth, scissorRegionY + scissorRegionHeight);

            drawContext.fillGradient(scissorRegionX, scissorRegionY, scissorRegionX + scissorRegionWidth, scissorRegionY + scissorRegionHeight, 0xFF13C6BD, 0xFF52C613);

            drawContext.disableScissor();
        });
    }

    public static void textureRenderingTest() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            Identifier texture = Identifier.of("minecraft", "textures/block/deepslate.png");

            drawContext.drawTexture(texture, 300, 20, 0, 0,16, 16, 16, 16);
        });
    }

    public static void textRenderingTest() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, "Hello, World!", 400, 20, 0xFFC61394, true);
        });
    }
}
