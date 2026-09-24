package com.cosy.CFexpansion;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.List;

public class RemoveRecipes {

    public static void removeBedRecipe() {

        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

        for (int i = recipes.size() - 1; i >= 0; i--) {

            IRecipe recipe = recipes.get(i);
            ItemStack output = recipe.getRecipeOutput();

            if (output != null && output.getItem() == Items.bed) {
                recipes.remove(i);
            }
        }
    }
}