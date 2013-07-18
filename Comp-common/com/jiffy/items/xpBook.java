package com.jiffy.items;

import com.jiffy.core.JCMM;
import com.jiffy.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class xpBook extends Item {
	
	public int xp;

	public xpBook(int par1) {
		super(par1);
		xp = 0;
		this.setCreativeTab(JCMM.tab);
		this.setUnlocalizedName("xpBook");
		new NBTTagCompound();
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.itemIcon = ir.registerIcon(Reference.MODID + ":xpBook" );
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		
		if (itemstack.getTagCompound() == null) {
			itemstack.stackTagCompound = new NBTTagCompound();
		}
		
		player.openGui(JCMM.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		
        return itemstack;
    }
	
	

}
