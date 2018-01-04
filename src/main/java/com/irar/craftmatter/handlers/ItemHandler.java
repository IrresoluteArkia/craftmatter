package com.irar.craftmatter.handlers;


import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemCraft;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class ItemHandler {
	public static ArrayList<Item> allItems = new ArrayList<Item>();
	public static Item craftMatter;
	public static Item craftAntiMatter;
	
	public static void init(){
		craftMatter = new ItemCraft("crafted_matter");
		craftAntiMatter = new ItemAntiCraft("crafted_antimatter");
		
		allItems.add(craftMatter);
		allItems.add(craftAntiMatter);
	}
	
	public static void register(){
		 for(int i = 0; i < allItems.size(); i++){
			 ForgeRegistries.ITEMS.register(allItems.get(i));
		 }
	}
	
	public static void registerRenders(){
		 for(int i = 0; i < allItems.size(); i++){
			 registerRender(allItems.get(i));
		 }
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
