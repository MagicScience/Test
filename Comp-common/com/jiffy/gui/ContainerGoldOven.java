package com.jiffy.gui;


import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.jiffy.entity.TileEntityGoldOven;
import com.jiffy.recipes.RecipesGoldOven;

public class ContainerGoldOven extends Container
{
	private TileEntityGoldOven goldOven;
	private int lastGoldOvenCookTime;
	private int lastGoldOvenBurnTime;
	private int lastGoldOvenItemBurnTime;

	public ContainerGoldOven(InventoryPlayer par1InventoryPlayer, TileEntityGoldOven par2TileEntityGoldOven)
	{
		lastGoldOvenCookTime = 0;
		lastGoldOvenBurnTime = 0;
		lastGoldOvenItemBurnTime = 0;
		goldOven = par2TileEntityGoldOven;
		addSlotToContainer(new Slot(par2TileEntityGoldOven, 0, 90, 56));
		addSlotToContainer(new Slot(par2TileEntityGoldOven, 1, 54, 56));
		for (int i = 0; i < 3; i++)
		{
			for (int k = 0; k < 9; k++)
			{
				addSlotToContainer(new Slot(par1InventoryPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}

		for (int j = 0; j < 9; j++)
		{
			addSlotToContainer(new Slot(par1InventoryPlayer, j, 8 + j * 18, 142));
		}
	}

	/**
	 * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
	 */
	 public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		Iterator var1 = this.crafters.iterator();
		while (var1.hasNext())
		{
			ICrafting var2 = (ICrafting)var1.next();
			if (this.lastGoldOvenCookTime != this.goldOven.furnaceCookTime)
			{
				var2.sendProgressBarUpdate(this, 0, this.goldOven.furnaceCookTime);
			}
			if (this.lastGoldOvenBurnTime != this.goldOven.furnaceBurnTime)
			{
				var2.sendProgressBarUpdate(this, 1, this.goldOven.furnaceBurnTime);
			}
			if (this.lastGoldOvenItemBurnTime != this.goldOven.currentItemBurnTime)
			{
				var2.sendProgressBarUpdate(this, 2, this.goldOven.currentItemBurnTime);
			}
		}
		this.lastGoldOvenCookTime = this.goldOven.furnaceCookTime;
		this.lastGoldOvenBurnTime = this.goldOven.furnaceBurnTime;
		this.lastGoldOvenItemBurnTime = this.goldOven.currentItemBurnTime;
	}

	 public void updateProgressBar(int par1, int par2)
	 {
		 if (par1 == 0)
		 {
			 goldOven.furnaceCookTime = par2;
		 }

		 if (par1 == 1)
		 {
			 goldOven.furnaceBurnTime = par2;
		 }

		 if (par1 == 2)
		 {
			 goldOven.currentItemBurnTime = par2;
		 }
	 }

	 public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	 {
		 return goldOven.isUseableByPlayer(par1EntityPlayer);
	 }

	 /**
	  * Called to transfer a stack from one inventory to the other eg. when shift clicking.
	  */
	 @Override
	 public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber)
	 {
		 ItemStack itemstack = null;
		 Slot slot = (Slot)inventorySlots.get(slotnumber);

		 if (slot != null && slot.getHasStack())
		 {
			 ItemStack itemstack1 = slot.getStack();
			 itemstack = itemstack1.copy();

			 if (slotnumber == 2)
			 {
				 if (!mergeItemStack(itemstack1, 3, 39, true))
				 {
					 return null;
				 }

				 slot.onSlotChange(itemstack1, itemstack);
			 }
			 else if (slotnumber == 1 || slotnumber == 0)
			 {
				 if (!mergeItemStack(itemstack1, 3, 39, false))
				 {
					 return null;
				 }
			 }
			 else if (RecipesGoldOven.smelting().getSmeltingResult(itemstack1.getItem().itemID) != null)
			 {
				 if (!mergeItemStack(itemstack1, 0, 1, false))
				 {
					 return null;
				 }
			 }
			 else if (TileEntityGoldOven.isItemFuel(itemstack1))
			 {
				 if (!mergeItemStack(itemstack1, 1, 2, false))
				 {
					 return null;
				 }
			 }
			 else if (slotnumber >= 3 && slotnumber < 30)
			 {
				 if (!mergeItemStack(itemstack1, 30, 39, false))
				 {
					 return null;
				 }
			 }
			 else if (slotnumber >= 30 && slotnumber < 39 && !mergeItemStack(itemstack1, 3, 30, false))
			 {
				 return null;
			 }

			 if (itemstack1.stackSize == 0)
			 {
				 slot.putStack(null);
			 }
			 else
			 {
				 slot.onSlotChanged();
			 }

			 if (itemstack1.stackSize == itemstack.stackSize)
			 {
				 return null;
			 }

			 slot.onPickupFromSlot(player, itemstack);
		 }

		 return itemstack;
	 }
}