package com.jiffy.block;

import java.util.Random;

import com.jiffy.core.*;
import com.jiffy.lib.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class Ore extends Block {
	
	public Ore(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(JCMM.tab);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
		setUnlocalizedNames();
		
	}
	
	public void setUnlocalizedNames() {
		if (this.blockID == 200) 
			this.setUnlocalizedName("sapphireOre");
		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		if (this.blockID == 200)
			this.blockIcon = ir.registerIcon(Reference.MODID + ":sapphireOre" );
	}
	
	public int quantityDropped(Random par1Random)
    {
        return 1;
    }
	
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }
	
	
	
	
}
