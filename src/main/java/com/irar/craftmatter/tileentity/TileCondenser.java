package com.irar.craftmatter.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.crafting.UnitMapping;
import com.irar.craftmatter.handlers.ItemHandler;
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

public class TileCondenser extends TileBase{
	
    public TileCondenser(){
    	super("Antimatter Condenser", 3, true);
    	isValid = true;
    	matterNeeded = 100;
    }
    
	@Override
	public void update() {
		super.update();
		ItemStack result = inventory.get(2);
		ItemStack matter = inventory.get(1);
		if(!matter.isEmpty() && matter.getItem() instanceof ItemCraft) {
			this.amountMatter += matter.getCount();
			if(ItemCraft.getAmount(matter) == 1) {
				inventory.set(1, ItemStack.EMPTY);
			}else {
				ItemCraft.setAmount(matter, ItemCraft.getAmount(matter) - 1);
			}
			this.markDirty();
		}
		if((result.isEmpty() || result.getItem() instanceof ItemAntiCraft) && isValid && matterNeeded < amountMatter) {
			amountMatter -= matterNeeded;
			if(result.getItem() instanceof ItemAntiCraft) {
				inventory.set(2, ItemAntiCraft.getCraftAntiMatterWithUnits(ItemAntiCraft.getAmount(result) + 1));
			}else {
				inventory.set(2, ItemAntiCraft.getCraftAntiMatterWithUnits(1));
			}
			this.markDirty();
		}

	}
    
}
