package com.cosy.CFexpansion.Variants;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class VariantZombie extends RenderZombie {

    //3 zombie variants and location with names
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
            new ResourceLocation("cfexpansionmod:textures/entity/zombie_1.png"),
            new ResourceLocation("cfexpansionmod:textures/entity/zombie_2.png"),
            new ResourceLocation("cfexpansionmod:textures/entity/zombie_3.png")
    };

    @Override
    protected ResourceLocation getEntityTexture(EntityZombie entity) {
        //set random texture
        long uuid = entity.getUniqueID().getLeastSignificantBits();

        int index = Math.abs((int) (uuid % TEXTURES.length));

        return TEXTURES[index];
    }
}