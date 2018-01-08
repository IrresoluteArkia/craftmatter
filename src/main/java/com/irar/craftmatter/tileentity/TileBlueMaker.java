package com.irar.craftmatter.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.crafting.UnitMapping;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemBlueprint;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
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

public class TileBlueMaker extends TileBase{
	
    public TileBlueMaker(){
    	super("Blueprint Maker", 4, true);
    }
    
	@Override
	public void update() {
		super.update();
		ItemStack toMake = inventory.get(1);
		if(!toMake.isEmpty() && UnitMapping.hasValueFor(toMake)) {
			isValid = true;
			matterNeeded = (int) (Math.pow(UnitMapping.getValueFor(toMake), 2.0 / 3.0) * 6);
		}else {
			isValid = false;
			matterNeeded = 0;
		}
		ItemStack matter = inventory.get(2);
		ItemStack result = inventory.get(3);
		if(!matter.isEmpty() && matter.getItem() instanceof ItemCraft) {
			this.amountMatter += matter.getCount();
			if(ItemCraft.getAmount(matter) == 1) {
				inventory.set(2, ItemStack.EMPTY);
			}else {
				ItemCraft.setAmount(matter, ItemCraft.getAmount(matter) - 1);
			}
			this.markDirty();
		}
		if(result.isEmpty() && isValid && matterNeeded < amountMatter) {
			amountMatter -= matterNeeded;
			if(toMake.getCount() > 1) {
				toMake.setCount(toMake.getCount() - 1);
				inventory.set(1, toMake);
			}else {
				inventory.set(1, ItemStack.EMPTY);
			}
			ItemStack toStore = toMake.copy();
			toStore.setCount(1);
			inventory.set(3, ItemBlueprint.getBlueprintWithItemStack(toStore, UnitMapping.getValueFor(toStore)));
			this.markDirty();
		}
	}
    
}
