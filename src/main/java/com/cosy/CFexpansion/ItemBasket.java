package com.cosy.CFexpansion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBasket extends Item {
    public ItemBasket() {
        super();
        this.setMaxStackSize(1); //Nie stackuje sie
        this.setUnlocalizedName("basket");
        this.setTextureName("CFexpansion:basket");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            //Otwiera GUI
            player.openGui(CFexpansion.instance, GuiHandler.GUI_BASKET, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
        return stack;
    }
}