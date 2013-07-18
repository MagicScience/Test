package com.jiffy.block;

import java.util.Random;

import com.jiffy.core.JCMM;
import com.jiffy.entity.TileEntityGoldOven;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GoldOven extends BlockContainer
{

	private Random refineRand;


	@SideOnly(Side.CLIENT)
	private Icon refinerIconTop;
	@SideOnly(Side.CLIENT)
	private Icon refinerIconFront;

	private boolean isActive = false;
	private static boolean keepFurnaceInventory;

	protected GoldOven(int par1, boolean par2Boolean)
	{
		super(par1, Material.rock);
		this.setCreativeTab(JCMM.tab);

		refineRand = new Random();
	}
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);

		par1World.markBlockForUpdate(par2, par3, par4);
	}

	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			byte b0 = 3;

			if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
			{
				b0 = 3;
			}

			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
			{
				b0 = 2;
			}

			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
			{
				b0 = 5;
			}

			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
			{
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EPlayer, int par6, float par7, float par8, float par9){

		TileEntityGoldOven tileentityrefiner = (TileEntityGoldOven)par1World.getBlockTileEntity(par2, par3, par4);

		if (tileentityrefiner != null)
		{
			par5EPlayer.openGui(JCMM.instance, 1, par1World, par2, par3, par4);
		}

		return true;
	}


	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		if(!keepFurnaceInventory){
			TileEntityGoldOven Refiner = (TileEntityGoldOven) par1World.getBlockTileEntity(par2, par3, par4);

			if(Refiner != null){
				for(int i = 0; i < Refiner.getSizeInventory(); i++){
					ItemStack IS = Refiner.getStackInSlot(i);

					if(IS != null){
						float j = this.refineRand.nextFloat() * 0.8F + 0.1F;
						float k = this.refineRand.nextFloat() * 0.8F + 0.1F;
						float l = this.refineRand.nextFloat() * 0.8F + 0.1F;

						while(IS.stackSize > 0){
							int m = this.refineRand.nextInt(21) + 10;

							if(m > IS.stackSize){
								m = IS.stackSize;
							}

							IS.stackSize -= m;

							EntityItem n = new EntityItem(par1World,(double) ((float) par2 + j), (double) ((float) par3 + k), (double) ((float) par4 + l), new ItemStack(IS.itemID, m, IS.getItemDamage()));

							if(IS.hasTagCompound()){
								n.getEntityItem().setTagCompound((NBTTagCompound) IS.getTagCompound().copy());

							}

							float o = 0.05F;

							n.motionX = (double) ((float)this.refineRand.nextGaussian() * o);
							n.motionZ = (double) ((float)this.refineRand.nextGaussian() * o);
							n.motionY = (double) ((float)this.refineRand.nextGaussian() * o);

							par1World.spawnEntityInWorld(n);
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@SideOnly(Side.CLIENT)

	public Icon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.refinerIconTop : (par1 == 0 ? this.refinerIconTop : (par1 != par2 ? this.blockIcon : this.refinerIconFront));
	}

	@SideOnly(Side.CLIENT)


	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("ProjectDestiny:refiner_side");
		this.refinerIconFront = par1IconRegister.registerIcon(this.isActive ? "ProjectDestiny:refiner_front_lit" : "ProjectDestiny:refiner_front");
		this.refinerIconTop = par1IconRegister.registerIcon("ProjectDestiny:refiner_top");
	}

	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileEntityGoldOven();
	}

}