package com.jiffy.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.jiffy.items.xpBook;
import com.jiffy.lib.Reference;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GUIxpBook extends GuiScreen {

	protected static final ResourceLocation gui = new ResourceLocation(Reference.MODID+":Block/GUIBase.png");
	//protected static final ResourceLocation gui = new ResourceLocation("Block/GUIBase.png");
    public static final ResourceLocation bar = new ResourceLocation("textures/gui/icons.png");


	public GUIxpBook(EntityPlayer player) {

	}

	public final int xSizeOfTexture = 255;
	public final int ySizeOfTexture = 120;
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
        FontRenderer fontrenderer = this.mc.fontRenderer;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(gui);

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		
		this.mc.func_110434_K().func_110577_a(bar);
		int j1 = this.mc.thePlayer.xpBarCap();
		int level = this.mc.thePlayer.experienceLevel;
		int j2;
		
		if (j1 > 0)
        {
            short short1 = 182;
            int i2 = (int)(this.mc.thePlayer.experience * (float)(short1 + 1));
            j2 = posY + 10;
            this.drawTexturedModalRect(posX + 35, posY+20, 0, 64, short1, 5);

            if (i2 > 0)
            {
                this.drawTexturedModalRect(posX + 35, posY+20, 0, 69, i2, 5);
            }
            
            fontrenderer.drawString(level+"", posX + 125, posY+27, Color.black.getAlpha());
        }


		super.drawScreen(x, y, f);

	}

	public void initGui()
	{
		this.buttonList.clear();



		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;


		this.buttonList.add(new GuiButton(0, posX+ 40, posY + 40, 100, 20, "Deposit XP"));
		this.buttonList.add(new GuiButton(1, posX+ 40, posY + 40+20, 100, 20, "Withdraw XP"));

	}
	
	public void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			int bookexp = this.mc.thePlayer.inventory.getCurrentItem().stackTagCompound.getInteger("xp");
			int charexp = this.mc.thePlayer.experienceLevel;
			
			int addbookexp = charexp + bookexp;
			charexp = 0;
			
			if (addbookexp > 50) {
				int leftover = addbookexp - 50;
				charexp += leftover;
				addbookexp = 50;
			}
			this.mc.thePlayer.experienceLevel = charexp;
			this.mc.thePlayer.inventory.getCurrentItem().stackTagCompound.setInteger("xp", addbookexp);
		}
		if (button.id == 1) {
			int bookexp = this.mc.thePlayer.inventory.getCurrentItem().stackTagCompound.getInteger("xp");
			int charexp = this.mc.thePlayer.experienceLevel;
			
			int addcharexp = charexp + bookexp;
			bookexp = 0;
			
			if (addcharexp > 50) {
				int leftover = addcharexp - 50;
				bookexp += leftover;
				charexp = 50;
			}
			this.mc.thePlayer.experienceLevel = addcharexp;
			this.mc.thePlayer.inventory.getCurrentItem().stackTagCompound.setInteger("xp", bookexp);
		}
	}



}
