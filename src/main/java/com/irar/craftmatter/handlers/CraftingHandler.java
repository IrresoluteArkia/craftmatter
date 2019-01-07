package com.irar.craftmatter.handlers;

import com.irar.craftmatter.config.ConfigBooleans;
import com.irar.craftmatter.crafting.CraftMatterRecipe;
import com.irar.craftmatter.crafting.GrenadeRecipe;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingHandler {

	public static void init() {
		if(ConfigBooleans.CRAFT_IN_TABLE.currentValue) {
			CommonProxy.recipeRegistry.register(new CraftMatterRecipe());
		}
		CommonProxy.recipeRegistry.register(new GrenadeRecipe());
		
		GameRegistry.addShapedRecipe(new ResourceLocation("craftmatter:recipe0"), new ResourceLocation("craftmatter"), new ItemStack(BlockHandler.matterCrafter), new Object[] {
				"S S",
				" C ",
				"S S",
				'S',
				Items.STICK,
				'C',
				Blocks.CRAFTING_TABLE
		});
		GameRegistry.addShapedRecipe(new ResourceLocation("craftmatter:recipe1"), new ResourceLocation("craftmatter"), new ItemStack(BlockHandler.blueprintMaker), new Object[] {
				"LML",
				"MCM",
				"LML",
				'L',
				"dyeBlue",
				'M',
				ItemCraft.getCraftMatterWithUnits(-1),
				'C',
				Blocks.CRAFTING_TABLE
		});
		GameRegistry.addShapedRecipe(new ResourceLocation("craftmatter:recipe2"), new ResourceLocation("craftmatter"), new ItemStack(BlockHandler.condenser), new Object[] {
				"LML",
				"MCM",
				"LML",
				'L',
				Items.GOLD_INGOT,
				'M',
				ItemCraft.getCraftMatterWithUnits(-1),
				'C',
				Blocks.CRAFTING_TABLE
		});
		GameRegistry.addShapedRecipe(new ResourceLocation("craftmatter:recipe3"), new ResourceLocation("craftmatter"), new ItemStack(BlockHandler.printer), new Object[] {
				"LML",
				"MCM",
				"LML",
				'L',
				Items.IRON_INGOT,
				'M',
				ItemAntiCraft.getCraftAntiMatterWithUnits(-1),
				'C',
				Blocks.CRAFTING_TABLE
		});
		GameRegistry.addShapedRecipe(new ResourceLocation("craftmatter:recipe4"), new ResourceLocation("craftmatter"), new ItemStack(BlockHandler.inverter), new Object[] {
				"LML",
				"MCM",
				"LML",
				'L',
				Items.DIAMOND,
				'M',
				ItemAntiCraft.getCraftAntiMatterWithUnits(-1),
				'C',
				Blocks.REDSTONE_TORCH
		});
	}
	
}
