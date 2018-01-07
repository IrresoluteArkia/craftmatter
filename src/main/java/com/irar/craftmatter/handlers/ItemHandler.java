package com.irar.craftmatter.handlers;


import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemAntiItem;
import com.irar.craftmatter.item.ItemBlueprint;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.item.ItemGrenade;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class ItemHandler {
	public static ArrayList<Item> allItems = new ArrayList<Item>();
	public static Item craftMatter;
	public static Item craftAntiMatter;
	public static Item antiGrenade;
	public static Item blueprint;
	public static Item antiItem;
	
	public static void init(){
		craftMatter = new ItemCraft("crafted_matter");
		craftAntiMatter = new ItemAntiCraft("crafted_antimatter");
		antiGrenade = new ItemGrenade("antimatter_grenade");
		blueprint = new ItemBlueprint("blueprint");
		antiItem = new ItemAntiItem("anti_item");
		
		allItems.add(craftMatter);
		allItems.add(craftAntiMatter);
		allItems.add(antiGrenade);
		allItems.add(blueprint);
//		allItems.add(antiItem);
	}
	
	public static void register(){
		 for(int i = 0; i < allItems.size(); i++){
			 ForgeRegistries.ITEMS.register(allItems.get(i));
		 }
		 ForgeRegistries.ITEMS.register(antiItem);
	}
	
	public static <T extends Item & ItemMeshDefinition> void registerRenders(){
		 for(int i = 0; i < allItems.size(); i++){
			 registerRender(allItems.get(i));
		 }
		 registerCustomRender((T) antiItem);
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static <T extends Item & ItemMeshDefinition> void registerCustomRender(T item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, item);
	}
}
