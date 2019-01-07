package com.irar.craftmatter.handlers;

import java.util.ArrayList;
import java.util.List;

import com.irar.craftmatter.block.AntiBlock;
import com.irar.craftmatter.block.AntiCondenser;
import com.irar.craftmatter.block.AntiInverter;
import com.irar.craftmatter.block.AntiPrinter;
import com.irar.craftmatter.block.BlueMaker;
import com.irar.craftmatter.block.MatterCrafter;
import com.irar.craftmatter.item.ItemAntiBlock;

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
	public static Block printer;
	public static Block condenser;
	public static Block inverter;
	public static Block antiBlock;
	public static Block matterCrafter;
	public static ItemBlock ibAntiBlock;
	public static List<Block> allBlocks = new ArrayList<>();
	public static void init(){
		blueprintMaker = new BlueMaker(Material.ROCK, "blueprint_maker", 7.0F, 7.0F, 0, "pickaxe");
		printer = new AntiPrinter(Material.ROCK, "antimatter_printer", 7.0F, 7.0F, 0, "pickaxe");
		condenser = new AntiCondenser(Material.ROCK, "antimatter_condenser", 7.0F, 7.0F, 0, "pickaxe");
		inverter = new AntiInverter(Material.ROCK, "antimatter_inverter", 7.0F, 7.0F, 0, "pickaxe");
		antiBlock = new AntiBlock(Material.CLAY, "anti_block", 0.1F, 0.1F);
		matterCrafter = new MatterCrafter(Material.ROCK, "matter_crafter", 7.0F, 7.0F, 0, "pickaxe");
		
		allBlocks.add(blueprintMaker);
		allBlocks.add(printer);
		allBlocks.add(condenser);
		allBlocks.add(inverter);
		allBlocks.add(matterCrafter);
		
		for(Block block : allBlocks) {
			ItemBlock ib = new ItemBlock(block);
			ib.setRegistryName(block.getRegistryName());
			ItemHandler.allItems.add(ib);
		}
		allBlocks.add(antiBlock);
		ibAntiBlock = new ItemAntiBlock(antiBlock);
		ibAntiBlock.setRegistryName(antiBlock.getRegistryName());
		ItemHandler.allItems.add(ibAntiBlock);
	}
	
	public static void register(){
		for(Block block : allBlocks) {
			ForgeRegistries.BLOCKS.register(block);
		}
	}
	
	public static void registerRenders(){
		for(Block block : allBlocks) {
			registerRender(block);
		}
	}
	
	public static void registerRender(Block block){
		Item item = Item.getItemFromBlock(block);
		if(item instanceof ItemAntiBlock) {
			
		}
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	
	}
}
