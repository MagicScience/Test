package com.jiffy.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import com.jiffy.entity.*;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		switch(ID) {
		case 1: return new ContainerGoldOven(player.inventory, (TileEntityGoldOven)world.getBlockTileEntity(x, y, z));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID) {
		case 0 : return new GUIxpBook(player);
		case 1:
			TileEntityGoldOven gc = (TileEntityGoldOven) world.getBlockTileEntity(x, y, z);
			return new ContainerGoldOven(player.inventory, gc);

		
		}
		
		return null;
	}

}
