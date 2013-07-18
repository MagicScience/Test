package com.jiffy.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.jiffy.entity.TileEntityGoldOven;
import com.jiffy.lib.Reference;
public class GuiGoldOven extends GuiContainer
{
	private TileEntityGoldOven goldInventory;
	//protected static final ResourceLocation gui = new ResourceLocation(Reference.MODID+":Block/GUIBase.png");
	protected static final ResourceLocation gui = new ResourceLocation("Block/FurnaceGui.png");


	public GuiGoldOven(InventoryPlayer inventory, TileEntityGoldOven gold)
	{
		super(new ContainerGoldOven(inventory, gold));
		goldInventory = gold;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everythin in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(gui);




		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

		if (goldInventory.isBurning())
		{
			int burn = goldInventory.getBurnTimeRemainingScaled(14);
			drawTexturedModalRect(j + 73, k+59, 176, 16, burn, 10);
		}

		int update = goldInventory.getCookProgressScaled(16);
		drawTexturedModalRect(j+ 89, k+55, 191, 15,-update , -update);
	}
}