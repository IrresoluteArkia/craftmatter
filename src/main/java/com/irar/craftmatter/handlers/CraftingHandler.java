package com.irar.craftmatter.handlers;

import com.irar.craftmatter.crafting.CraftMatterRecipe;
import com.irar.craftmatter.proxy.CommonProxy;

public class CraftingHandler {

	public static void init() {
		CommonProxy.recipeRegistry.register(new CraftMatterRecipe());
	}
	
}
