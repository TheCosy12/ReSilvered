package com.cosy.CFexpansion.proxy;

import com.cosy.CFexpansion.Variants.VariantZombie;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.entity.monster.EntityZombie;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        //podpiecie klasy variantzombie
        RenderingRegistry.registerEntityRenderingHandler(EntityZombie.class, new VariantZombie());
    }

}