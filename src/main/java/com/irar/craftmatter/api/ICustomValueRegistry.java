package com.irar.craftmatter.api;

import net.minecraft.item.ItemStack;

public interface ICustomValueRegistry {

	public void register(ICustomValue value);
	
	public boolean hasValueFor(ItemStack stack);
	
	public int getValueFor(ItemStack stack);
	
}
