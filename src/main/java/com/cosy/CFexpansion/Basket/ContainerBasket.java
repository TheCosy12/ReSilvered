package com.cosy.CFexpansion.Basket; // Upewnij się, że masz ten package!

import com.cosy.CFexpansion.ItemBasket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBasket extends Container {
    public final InventoryBasket inventory;

    public ContainerBasket(EntityPlayer player, InventoryPlayer invPlayer, InventoryBasket backpackInv) {
        this.inventory = backpackInv;

        //3 sloty koszyka
        for (int k = 0; k < 3; ++k) {
            this.addSlotToContainer(new Slot(backpackInv, k, 62 + k * 18, 20) {
                @Override
                public boolean isItemValid(ItemStack stack) {
                    // Uniemożliwia włożenie koszyka do koszyka
                    return !(stack.getItem() instanceof ItemBasket);
                }
            });
        }

        //sloty eq
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(invPlayer, k + j * 9 + 9, 8 + k * 18, 51 + j * 18));
            }
        }

        //hotbar
        for (int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(invPlayer, j, 8 + j * 18, 109));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    //shift click logika
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        //jeśli klika na pelny slot
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //shift click zabezpieczenie
            if (itemstack1.getItem() instanceof ItemBasket) {
                return null;
            }

            //sloty 0, 1, 2 to koszyk.
            if (index < 3) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
            }
            else {
                if (!this.mergeItemStack(itemstack1, 0, 3, false)) {
                    return null;
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}