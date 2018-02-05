package com.irar.craftmatter.api;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ICustomValue {

	public int getValueFor(ItemStack stack);
	
	public default boolean matches(Item item) {
		return this.getItemFor().equals(item);
	}
	
	public boolean hasValueFor(ItemStack stack);
	
	public Item getItemFor();
	
}
