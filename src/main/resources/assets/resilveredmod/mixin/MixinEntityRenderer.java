package com.cosy.resilveredmod.mixin;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Shadow
    private int[] lightmapColors;

    @Shadow
    private boolean lightmapUpdateNeeded;

    @Inject(method = "updateLightmap(F)V", at = @At("HEAD"), cancellable = true)
    private void onUpdateLightmap(float partialTicks, CallbackInfo ci) {
        try {
            
            ResourceLocation lightmapLocation = new ResourceLocation("minecraft", "textures/environment/lightmap.png");
            IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(lightmapLocation);

            BufferedImage image = ImageIO.read(resource.getInputStream());
            int width = image.getWidth();
            int height = image.getHeight();

            for (int y = 0; y < 16; ++y) {
                for (int x = 0; x < 16; ++x) {
                    int pixelX = (x * width) / 16;
                    int pixelY = (y * height) / 16;
                    int color = image.getRGB(pixelX, pixelY);

                    int index = y * 16 + x;
                    if (index < lightmapColors.length) {
                        lightmapColors[index] = color;
                    }
                }
            }

            this.lightmapUpdateNeeded = false;
            ci.cancel();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}