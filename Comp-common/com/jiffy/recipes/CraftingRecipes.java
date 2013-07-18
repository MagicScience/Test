package com.jiffy.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.jiffy.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingRecipes {
	
	public static void load() {
		
		GameRegistry.addRecipe(new ItemStack(ModItems.xpBook, 1), new Object[] {
			" W ", " D ", " W ", 'W', Block.planks, 'D', Item.glassBottle
		});
	}

}
