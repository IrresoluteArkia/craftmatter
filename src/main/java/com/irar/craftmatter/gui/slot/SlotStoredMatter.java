package com.irar.craftmatter.gui.slot;

import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotStoredMatter extends Slot{

	public SlotStoredMatter(IInventory inventoryIn) {
		super(inventoryIn, 0, 2000, 2000);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(!stack.isEmpty() && (stack.getItem() instanceof ItemCraft || stack.getItem() instanceof ItemAntiCraft)) {
			return super.isItemValid(stack);
		}
		return false;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return false;
	}

}
