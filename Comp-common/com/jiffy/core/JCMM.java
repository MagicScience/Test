package com.jiffy.core;

import net.minecraft.client.*;
import net.minecraft.creativetab.*;

import com.jiffy.block.*;
import com.jiffy.creativetab.*;
import com.jiffy.generation.*;
import com.jiffy.items.*;
import com.jiffy.lib.*;
import com.jiffy.recipes.*;
import com.jiffy.gui.*;

import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.*;

@Mod( modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION )
public class JCMM {
	
	@Instance(Reference.MODID)
	public static JCMM instance = new JCMM();
	public static Minecraft mc = Minecraft.getMinecraft();
	
	
	public static CreativeTabs tab = new RandTab("tab");
	
	@EventHandler
	public void preInit( FMLPreInitializationEvent event ) {
		
		ModBlocks.load();
		ModItems.load();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		CraftingRecipes.load();
		LanguageRegistry.instance().addStringLocalization("itemGroup.tab", "en_US", "JCMM tab");
		GameRegistry.registerWorldGenerator(new WorldGeneration());
		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());

	}

	

	
	

}
