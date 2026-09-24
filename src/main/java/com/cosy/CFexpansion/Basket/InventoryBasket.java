package com.cosy.CFexpansion.Basket;

import com.cosy.CFexpansion.ItemBasket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryBasket implements IInventory {
    private String name = "Basket";
    private final int INV_SIZE = 3; //Pojemność (sloty)
    private ItemStack[] inventory = new ItemStack[INV_SIZE];
    private ItemStack invItem; //przedmiot

    public InventoryBasket(ItemStack stack) {
        this.invItem = stack;
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        readFromNBT(stack.getTagCompound());
    }

    @Override
    public int getSizeInventory() { return INV_SIZE; }

    @Override
    public ItemStack getStackInSlot(int slot) { return inventory[slot]; }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize > amount) {
                stack = stack.splitStack(amount);
                markDirty();
            } else {
                setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) { setInventorySlotContents(slot, null); }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    @Override
    public String getInventoryName() { return name; }
    @Override
    public boolean hasCustomInventoryName() { return true; }
    @Override
    public int getInventoryStackLimit() { return 64; }

    @Override
    public void markDirty() {
        //to zapisuje zmienione itemy w środku
        for (int i = 0; i < getSizeInventory(); ++i) {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0) {
                inventory[i] = null;
            }
        }
        writeToNBT(invItem.getTagCompound());
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) { return true; }
    @Override
    public void openInventory() {}
    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        //zabezpieczenie przed wlozeniem plecaka do plecaka
        return !(stack.getItem() instanceof ItemBasket);
    }

    public void readFromNBT(NBTTagCompound compound) {
        NBTTagList items = compound.getTagList("ItemInventory", 10);
        for (int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            int slot = item.getByte("Slot") & 255;
            if (slot >= 0 && slot < getSizeInventory()) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < getSizeInventory(); ++i) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(item);
                items.appendTag(item);
            }
        }
        compound.setTag("ItemInventory", items);
    }
}