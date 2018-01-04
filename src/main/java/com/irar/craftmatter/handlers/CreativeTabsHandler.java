package com.irar.craftmatter.handlers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsHandler {
	public static CreativeTabs CM = new CreativeTabs("craftmatter"){

		public ItemStack getTabIconItem() {
			return new ItemStack(ItemHandler.craftMatter);
		}
		
	};
}
