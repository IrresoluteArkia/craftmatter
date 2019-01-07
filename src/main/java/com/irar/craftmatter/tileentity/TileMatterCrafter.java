package com.irar.craftmatter.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.crafting.UnitMapping;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemAntiCraft;
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

public class TileMatterCrafter extends TileBase{
	
    public TileMatterCrafter(){
    	super("Matter Crafter", 2, Stores.NEITHER);
    	this.displayMatter = false;
    }
    
	@Override
	public void update() {
		super.update();
		ItemStack toCraft = inventory.get(0);
		ItemStack matter = inventory.get(1);
		if(!toCraft.isEmpty() && (UnitMapping.hasValueFor(toCraft) || toCraft.getItem() instanceof ItemCraft || toCraft.getItem() instanceof ItemAntiCraft)) {
			int value = UnitMapping.getValueFor(toCraft) * toCraft.getCount();
			if(matter.isEmpty()) {
				if(value > 0) {
					inventory.set(1, ItemCraft.getCraftMatterWithUnits(value));
				}else if(value < 0) {
					inventory.set(1, ItemAntiCraft.getCraftAntiMatterWithUnits(-value));
				}else {
					inventory.set(1, ItemStack.EMPTY);
				}
				inventory.set(0, ItemStack.EMPTY);
			}else if(matter.getItem() instanceof ItemCraft) {
				value += ItemCraft.getAmount(matter);
				if(value > 0) {
					inventory.set(1, ItemCraft.getCraftMatterWithUnits(value));
				}else if(value < 0) {
					inventory.set(1, ItemAntiCraft.getCraftAntiMatterWithUnits(-value));
				}else {
					inventory.set(1, ItemStack.EMPTY);
				}
				inventory.set(0, ItemStack.EMPTY);
			}else if(matter.getItem() instanceof ItemAntiCraft) {
				value -= ItemAntiCraft.getAmount(matter);
				if(value > 0) {
					inventory.set(1, ItemCraft.getCraftMatterWithUnits(value));
				}else if(value < 0) {
					inventory.set(1, ItemAntiCraft.getCraftAntiMatterWithUnits(-value));
				}else {
					inventory.set(1, ItemStack.EMPTY);
				}
				inventory.set(0, ItemStack.EMPTY);
			}
			this.markDirty();
		}
	}
    
}
