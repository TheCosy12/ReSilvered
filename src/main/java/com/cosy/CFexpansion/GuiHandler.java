package com.cosy.CFexpansion;

import com.cosy.CFexpansion.Basket.ContainerBasket;
import com.cosy.CFexpansion.Basket.InventoryBasket;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_BASKET = 1;

    //dla serwera
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_BASKET) {
            return new ContainerBasket(player, player.inventory, new InventoryBasket(player.getHeldItem()));
        }
        return null;
    }

    //dla klienta
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_BASKET) {
            return new com.cosy.CFexpansion.Basket.GuiBasket(new com.cosy.CFexpansion.Basket.ContainerBasket(player, player.inventory, new com.cosy.CFexpansion.Basket.InventoryBasket(player.getHeldItem())));
        }
        return null;
    }
}