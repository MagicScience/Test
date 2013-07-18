package com.jiffy.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RandTab extends CreativeTabs{

	public RandTab(String label) {
		super(label);
	}
	
	public ItemStack getIconItemStack() {
        return new ItemStack(Item.eyeOfEnder, 1, 0);
}
	

}