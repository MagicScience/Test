package com.jiffy.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class RecipesGoldOven
{
	private static final RecipesGoldOven goldOvenBase = new RecipesGoldOven();

	/** The list of smelting results. */
	private Map goldOvenList = new HashMap();
	private Map goldOvenExperience = new HashMap();

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static final RecipesGoldOven smelting()
	{
		return goldOvenBase;
	}

	private RecipesGoldOven()
	{
		addSmelting(Block.dirt.blockID, new ItemStack(Block.cobblestone, 1, 0), 0.7F);
	}

	/**
	 * Adds a smelting recipe.
	 */
	public void addSmelting(int id, ItemStack itemStack, float experience)
	{
		goldOvenList.put(Integer.valueOf(id), itemStack);
		this.goldOvenExperience.put(Integer.valueOf(itemStack.itemID), Float.valueOf(experience));
	}

	/**
	 * Returns the smelting result of an item.
	 */
	public ItemStack getSmeltingResult(int id)
	{
		return (ItemStack)goldOvenList.get(Integer.valueOf(id));
	}

	public Map getSmeltingList()
	{
		return goldOvenList;
	}
	public float getExperience(int par1)
	{
		return this.goldOvenExperience.containsKey(Integer.valueOf(par1)) ? ((Float)this.goldOvenExperience.get(Integer.valueOf(par1))).floatValue() : 0.0F;
	}
}