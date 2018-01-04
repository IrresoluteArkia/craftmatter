package com.irar.craftmatter.handlers;

import com.irar.craftmatter.block.DimBlock;

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
	public static Block DimMarker;
	public static ItemBlock ibDimMarker;
	public static void init(){
		DimMarker = new DimBlock(Material.LEAVES, "dim_marker", 0F, 0F);
		ibDimMarker = (ItemBlock) new ItemBlock(DimMarker);
	}
	
	public static void register(){
		ibDimMarker.setRegistryName(DimMarker.getRegistryName());
		
		ForgeRegistries.BLOCKS.register(DimMarker);
		ForgeRegistries.ITEMS.register(ibDimMarker);
		
	}
	
	public static void registerRenders(){
		registerRender(DimMarker);
		ItemHandler.registerRender(ibDimMarker);
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
