package com.irar.craftmatter.crafting;

import java.util.ArrayList;

import com.irar.craftmatter.handlers.PluginHandler;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemAntiItem;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.item.ItemGrenade;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class UnitMapping {

	private static ArrayList<ItemStack> registryNames = new ArrayList<ItemStack>();
	private static ArrayList<Integer> values = new ArrayList<Integer>();
	
	public static void addMapping(Block block, int value) {
		registryNames.add(new ItemStack(block));
		values.add(value);
	}
	
	public static void addMapping(Item item, int value) {
		registryNames.add(new ItemStack(item));
		values.add(value);
	}
	
	public static void addMapping(ItemStack stack, int value) {
		if(!stack.isEmpty()) {
			ItemStack itemstack = stack.copy();
			itemstack.setCount(1);
			
			registryNames.add(itemstack);
			values.add(value);
		}
	}
	
	public static void addAllMappings(Block block, int value) {
		addAllMappings(new ItemStack(block), value);
	}
	
	public static void addAllMappings(Item item, int value) {
		addAllMappings(new ItemStack(item), value);
	}
	
	public static void addAllMappings(ItemStack stack, int value) {
		if(stack.getItem().getHasSubtypes()) {
			NonNullList<ItemStack> items = NonNullList.<ItemStack>create();
			CreativeTabs[] tabs = CreativeTabs.CREATIVE_TAB_ARRAY;
			for(CreativeTabs tab : tabs) {
				stack.getItem().getSubItems(tab, items);
			}
			for(ItemStack itemstack : items) {
				addMapping(itemstack, value);
			}
		}else {
			addMapping(stack, value);
		}
	}

	public static void addMapping(String ore, int value) {
		NonNullList<ItemStack> ores = OreDictionary.getOres(ore);
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		for(ItemStack stack : ores) {
			ItemStack itemstack = stack.copy();
			itemstack.setCount(1);
			stacks.add(itemstack);
		}
		
		for(ItemStack stack : stacks) {
			registryNames.add(stack);
			values.add(value);
		}
	}
	
	public static int getValueFor(Block block) {
		int value = 1;
		for(int i = 0; i < registryNames.size(); i++) {
			if(registryNames.get(i).isItemEqual(new ItemStack(block))) {
				return values.get(i);
			}
		}
		
		return value;
	}
	
	public static int getValueFor(Item item) {
		int value = 1;
		for(int i = 0; i < registryNames.size(); i++) {
			if(registryNames.get(i).isItemEqual(new ItemStack(item))) {
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
		if(PluginHandler.customValueRegistry.hasValueFor(stack)) {
			return PluginHandler.customValueRegistry.getValueFor(stack);
		}else if(stack.getItem() instanceof ItemCraft) {
			value = ItemCraft.getAmount(stack);
		}else if(stack.getItem() instanceof ItemAntiCraft) {
			value = - ItemAntiCraft.getAmount(stack);
		}else if(stack.getItem() instanceof ItemAntiItem){
			value = - getValueFor(ItemAntiItem.getContainedItemStack(stack));
		}else if(stack.getItem() instanceof ItemGrenade){
			value = - ItemGrenade.getAmount(stack);
		}else{
			for(int i = 0; i < registryNames.size(); i++) {
				boolean isWildcard = stack.getMetadata() == OreDictionary.WILDCARD_VALUE || registryNames.get(i).getMetadata() == OreDictionary.WILDCARD_VALUE;
				if(isWildcard) {
					if(registryNames.get(i).getItem().equals(stack.getItem())) {
						return values.get(i);
					}
				}else {
					if(registryNames.get(i).isItemEqual(stack)) {
						return values.get(i);
					}
				}
			}
		}
		
		return value;
	}

	public static boolean hasValueFor(ItemStack stack) {
		if(stack.isEmpty()) {
			return false;
		}
		
		if(PluginHandler.customValueRegistry.hasValueFor(stack)) {
			return true;
		}else if(stack.getItem() instanceof ItemCraft) {
			return false;
		}else if(stack.getItem() instanceof ItemAntiCraft) {
			return false;
		}else {
			for(int i = 0; i < registryNames.size(); i++) {
				boolean isWildcard = stack.getMetadata() == OreDictionary.WILDCARD_VALUE || registryNames.get(i).getMetadata() == OreDictionary.WILDCARD_VALUE;
				if(isWildcard) {
					if(registryNames.get(i).getItem().equals(stack.getItem())) {
						return true;
					}
				}else {
					if(registryNames.get(i).isItemEqual(stack)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void clear() {
		registryNames.clear();
		values.clear();
	}

	
}
