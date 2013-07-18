package com.jiffy.items;

import com.jiffy.core.JCMM;
import com.jiffy.lib.*;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class Jetpack extends Item {

	public Jetpack(int par1) {
		super(par1);
		this.setCreativeTab(JCMM.tab);


	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
	    float power = 1.0F;
	    float dropPercentage = 0.2F;
	    
	    
		return itemstack;
	}






















	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.itemIcon = ir.registerIcon(Reference.MODID+"jetpack" );
	}



}
