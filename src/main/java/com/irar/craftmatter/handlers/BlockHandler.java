package com.irar.craftmatter.handlers;

import com.irar.craftmatter.block.AntiPrinter;
import com.irar.craftmatter.block.BlueMaker;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class BlockHandler {
	public static Block blueprintMaker;
	public static ItemBlock ibBlueprintMaker;
	public static Block printer;
	public static ItemBlock ibPrinter;
	public static void init(){
		blueprintMaker = new BlueMaker(Material.ROCK, "blueprint_maker", 7.0F, 7.0F, 0, "pickaxe");
		ibBlueprintMaker = (ItemBlock) new ItemBlock(blueprintMaker);
		printer = new AntiPrinter(Material.ROCK, "antimatter_printer", 7.0F, 7.0F, 0, "pickaxe");
		ibPrinter = (ItemBlock) new ItemBlock(printer);
	}
	
	public static void register(){
		ibBlueprintMaker.setRegistryName(blueprintMaker.getRegistryName());
		
		ForgeRegistries.BLOCKS.register(blueprintMaker);
		ForgeRegistries.ITEMS.register(ibBlueprintMaker);
		
		ibPrinter.setRegistryName(printer.getRegistryName());
		
		ForgeRegistries.BLOCKS.register(printer);
		ForgeRegistries.ITEMS.register(ibPrinter);
		
	}
	
	public static void registerRenders(){
		registerRender(blueprintMaker);
		ItemHandler.registerRender(ibBlueprintMaker);
		registerRender(printer);
		ItemHandler.registerRender(ibPrinter);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
