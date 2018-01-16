package com.irar.craftmatter.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemStackHelper {

	public static boolean areStacksEqual(ItemStack stacka, ItemStack stackb) {
		if(stacka.getMetadata() == OreDictionary.WILDCARD_VALUE || stackb.getMetadata() == OreDictionary.WILDCARD_VALUE) {
			if(stacka.getItem().equals(stackb.getItem())) {
				return true;
			}
		}else {
			if(stacka.isItemEqual(stackb)) {
				return true;
			}
		}
		return false;
	}

}
