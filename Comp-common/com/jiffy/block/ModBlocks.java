package com.jiffy.block;

import com.jiffy.entity.TileEntityGoldOven;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModBlocks {
	
	public static Block sapphireOre;
	public static Block goldoven;
	
 public static void load() {
		
		sapphireOre = new Ore(200);
		goldoven = new GoldOven(201, false);
		languageRegistry();
		gameRegistry();
		harvestLevel();

	}


	private static void harvestLevel() {
		MinecraftForge.setBlockHarvestLevel(sapphireOre, "pickaxe", 2);
	}


	private static void gameRegistry() {
		GameRegistry.registerBlock(sapphireOre, "Sapphire Ore");
		GameRegistry.registerBlock(goldoven, "Gold Oven");
		GameRegistry.registerTileEntity(TileEntityGoldOven.class, "goldOven");
		GameRegistry.addRecipe(new ItemStack(goldoven, 4, 0), new Object[]{"DD", 'D', Block.dirt});
	}


	private static void languageRegistry() {
		LanguageRegistry.addName(sapphireOre, "Sapphire Ore");
		LanguageRegistry.addName(goldoven, "Gold Oven");
	}
}
