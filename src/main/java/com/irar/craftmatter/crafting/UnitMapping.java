package com.irar.craftmatter.crafting;

import java.util.ArrayList;

import com.irar.craftmatter.item.ItemCraft;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UnitMapping {

	private static ArrayList<ResourceLocation> registryNames = new ArrayList<ResourceLocation>();
	private static ArrayList<Integer> values = new ArrayList<Integer>();
	
	public static void addMapping(Block block, int value) {
		ResourceLocation name = block.getRegistryName();
		registryNames.add(name);
		values.add(value);
	}
	
	public static void addMapping(Item item, int value) {
		ResourceLocation name = item.getRegistryName();
		registryNames.add(name);
		values.add(value);
	}
	
	public static int getValueFor(Block block) {
		int value = 1;
		ResourceLocation name = block.getRegistryName();
		
		for(int i = 0; i < registryNames.size(); i++) {
			if(registryNames.get(i).equals(name)) {
				return values.get(i);
			}
		}
		
		return value;
	}
	
	public static int getValueFor(Item item) {
		int value = 1;
		ResourceLocation name = item.getRegistryName();
		
		for(int i = 0; i < registryNames.size(); i++) {
			if(registryNames.get(i).equals(name)) {
				return values.get(i);
			}
		}
		
		return value;
	}
	
	public static int getValueFor(ItemStack itemstack) {
		int value = 1;
		if(itemstack.isEmpty()) {
			return 0;
		}
		ResourceLocation name = itemstack.getItem().getRegistryName();
		
		if(itemstack.getItem() instanceof ItemCraft) {
			value = ItemCraft.getAmount(itemstack);
		}else {
			for(int i = 0; i < registryNames.size(); i++) {
				if(registryNames.get(i).equals(name)) {
					return values.get(i);
				}
			}
		}
		
		return value;
	}

	
}
