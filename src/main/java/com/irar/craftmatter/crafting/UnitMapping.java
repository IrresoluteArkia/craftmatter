package com.irar.craftmatter.crafting;

import java.util.ArrayList;

import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemAntiItem;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.item.ItemGrenade;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class UnitMapping {

	private static ArrayList<String> registryNames = new ArrayList<String>();
	private static ArrayList<Integer> values = new ArrayList<Integer>();
	
	public static void addMapping(Block block, int value) {
		registryNames.add(new ItemStack(block).toString());
		values.add(value);
	}
	
	public static void addMapping(Item item, int value) {
		registryNames.add(new ItemStack(item).toString());
		values.add(value);
	}
	
	public static void addMapping(String ore, int value) {
		NonNullList<ItemStack> ores = OreDictionary.getOres(ore);
		ArrayList<String> toStrings = new ArrayList<String>();
		for(ItemStack stack : ores) {
			ItemStack itemstack = stack.copy();
			itemstack.setCount(1);
			toStrings.add(itemstack.toString());
		}
		
		registryNames.addAll(toStrings);
		values.add(value);
	}
	
	public static int getValueFor(Block block) {
		int value = 1;
		for(int i = 0; i < registryNames.size(); i++) {
			if(registryNames.get(i).equals(new ItemStack(block).toString())) {
				return values.get(i);
			}
		}
		
		return value;
	}
	
	public static int getValueFor(Item item) {
		int value = 1;
		for(int i = 0; i < registryNames.size(); i++) {
			if(registryNames.get(i).equals(new ItemStack(item).toString())) {
				return values.get(i);
			}
		}
		
		return value;
	}
	
	public static int getValueFor(ItemStack stack) {
		int value = 1;
		if(stack.isEmpty()) {
			return 0;
		}
		ItemStack itemstack = new ItemStack(stack.getItem());
		if(stack.getItem() instanceof ItemCraft) {
			value = ItemCraft.getAmount(stack);
		}else if(stack.getItem() instanceof ItemAntiCraft) {
			value = - ItemAntiCraft.getAmount(stack);
		}else if(stack.getItem() instanceof ItemAntiItem){
			value = - getValueFor(ItemAntiItem.getContainedItemStack(stack));
		}else if(stack.getItem() instanceof ItemGrenade){
			value = - ItemGrenade.getAmount(stack);
		}else{
			for(int i = 0; i < registryNames.size(); i++) {
				if(registryNames.get(i).equals(itemstack.toString())) {
					return values.get(i);
				}
			}
		}
		
		return value;
	}

	public static boolean hasValueFor(ItemStack stack) {
		if(stack.isEmpty()) {
			return false;
		}
		ItemStack itemstack = new ItemStack(stack.getItem());
		if(itemstack.getItem() instanceof ItemCraft) {
			return false;
		}else if(itemstack.getItem() instanceof ItemAntiCraft) {
			return false;
		}else {
			for(int i = 0; i < registryNames.size(); i++) {
				if(registryNames.get(i).equals(itemstack.toString())) {
					return true;
				}
			}
		}
		return false;
	}

	
}
