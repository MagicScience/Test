package com.jiffy.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.*;

public class ModItems {
	
	
	public static Item Jetpack;
	public static Item xpBook;
	
	
	public static void load() {
		
		Jetpack = new Jetpack(500);
		xpBook = new xpBook(501);
		
		languageRegistry();
	}


	private static void languageRegistry() {
		LanguageRegistry.addName(xpBook, "Experience Book");
	}
}
