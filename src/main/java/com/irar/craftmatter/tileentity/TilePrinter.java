package com.irar.craftmatter.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.crafting.UnitMapping;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemAntiBlock;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemAntiItem;
import com.irar.craftmatter.item.ItemBlueprint;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.oredict.OreDictionary;

public class TilePrinter extends TileBase{
	
    public TilePrinter(){
    	super("Antimatter Printer", 4, Stores.ANTIMATTER);
    }
    
	@Override
	public void update() {
		super.update();
		ItemStack resultItem = ItemStack.EMPTY;
		ItemStack toMake = inventory.get(1);
		if(!toMake.isEmpty() && toMake.getItem() instanceof ItemBlueprint) {
			if(ItemBlueprint.getAmount(toMake) > 0) {
				isValid = true;
				matterNeeded = ItemBlueprint.getAmount(toMake);
				resultItem = ItemBlueprint.getContainedItemStack(toMake);
			}
		}else {
			isValid = false;
			matterNeeded = 0;
		}
		ItemStack matter = inventory.get(2);
		ItemStack result = inventory.get(3);
		if(!matter.isEmpty() && matter.getItem() instanceof ItemAntiCraft) {
			this.amountMatter += matter.getCount();
			if(ItemAntiCraft.getAmount(matter) == 1) {
				inventory.set(2, ItemStack.EMPTY);
			}else {
				ItemAntiCraft.setAmount(matter, ItemAntiCraft.getAmount(matter) - 1);
			}
			this.markDirty();
		}
		if((result.isEmpty() || (result.getItem() instanceof ItemAntiItem && ItemAntiItem.getContainedItemStack(result).getItem().equals(resultItem.getItem())) && result.getCount() < 64) && isValid && matterNeeded < amountMatter) {
			amountMatter -= matterNeeded;
			if(result.getItem() instanceof ItemAntiItem && ItemAntiItem.getContainedItemStack(result).getItem().equals(resultItem.getItem())) {
				ItemStack toStore = result.copy();
				toStore.setCount(inventory.get(3).getCount() + 1);
				inventory.set(3, toStore);
			}else {
				inventory.set(3, ItemAntiItem.getWithItemStack(resultItem));
			}
			this.markDirty();
		}
		if(result.isEmpty() && matter.getItem() instanceof ItemAntiBlock) {
			if(resultItem.getItem() instanceof ItemBlock) {
				inventory.set(3, ItemAntiBlock.getWithItemStack(resultItem));
			}
		}

	}
    
}
