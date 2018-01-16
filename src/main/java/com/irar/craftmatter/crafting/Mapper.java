package com.irar.craftmatter.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import com.irar.craftmatter.handlers.BlockHandler;
import com.irar.craftmatter.proxy.CommonProxy;
import com.irar.craftmatter.util.ItemStackHelper;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class Mapper {

	private static List<List<Ingredient>> recipeMapIn = new ArrayList<List<Ingredient>>();
	private static List<ItemStack> recipeMapOut = new ArrayList<ItemStack>();
	private static List<ItemStack> alreadyChecked = new ArrayList<ItemStack>();
	private static HashMap<ItemStack, Integer> permanentMappings = new HashMap<ItemStack, Integer>();
	private static List<ItemStack> input = new ArrayList<ItemStack>();
	private static boolean containsValue;
	
	public static void init() {
		UnitMapping.clear();
		alreadyChecked.clear();
		recipeMapIn.clear();
		recipeMapOut.clear();
		UnitMapping.addAllMappings(Blocks.LOG, 8);
		UnitMapping.addAllMappings(Blocks.LOG2, 8);
		UnitMapping.addMapping(Items.IRON_INGOT, 16);
		UnitMapping.addMapping(Items.REDSTONE, 9);
		UnitMapping.addMapping(Blocks.AIR, 0);
		UnitMapping.addMapping(Blocks.BARRIER, -100);
		UnitMapping.addMapping(Items.STRING, 3);
		UnitMapping.addMapping(Blocks.BEDROCK, -100);
		UnitMapping.addMapping(Items.CLAY_BALL, 1);
//		UnitMapping.addMapping(Blocks.HARDENED_CLAY, 8);
		UnitMapping.addAllMappings(Items.DYE, 1);
		UnitMapping.addAllMappings(Items.SHULKER_SHELL, 128);
		UnitMapping.addMapping(Items.BONE, 3);
		UnitMapping.addMapping(Blocks.BONE_BLOCK, 9);
		UnitMapping.addMapping(Items.REEDS, 2);
		UnitMapping.addMapping(Items.LEATHER, 9);
		UnitMapping.addMapping(Blocks.COBBLESTONE, 1);
		UnitMapping.addMapping(Items.BLAZE_ROD, 20);
//		UnitMapping.addMapping(Items.BRICK, 2);
		UnitMapping.addMapping(Blocks.BROWN_MUSHROOM_BLOCK, 10);
		UnitMapping.addMapping(Items.SKULL, 100);
		UnitMapping.addMapping(Blocks.SOUL_SAND, 10);
		UnitMapping.addMapping(Items.NETHER_STAR, 3400);
		UnitMapping.addAllMappings(Blocks.SAND, 1);
//		UnitMapping.addAllMappings(Blocks.GLASS, 2);
		UnitMapping.addMapping(Blocks.OBSIDIAN, 2);
		UnitMapping.addMapping(Blocks.BROWN_MUSHROOM, 10);
		UnitMapping.addMapping(Items.SUGAR, 2);
		UnitMapping.addMapping(Items.MILK_BUCKET, 50);
		UnitMapping.addMapping(Items.WHEAT, 10);
		UnitMapping.addMapping(Items.WHEAT_SEEDS, 1);
		UnitMapping.addMapping(Items.EGG, 5);
		UnitMapping.addMapping(Items.COAL, 10);
		UnitMapping.addMapping(Blocks.COAL_ORE, 20);
		UnitMapping.addMapping(Blocks.CONCRETE, 1);
		UnitMapping.addMapping(Blocks.CACTUS, 2);
		UnitMapping.addMapping(Items.DIAMOND, 1000);
		UnitMapping.addMapping(Blocks.DIAMOND_ORE, 2000);
		UnitMapping.addMapping(Blocks.DIRT, 1);
		UnitMapping.addMapping(Blocks.DRAGON_EGG, 100000);
		UnitMapping.addMapping(Items.QUARTZ, 24);
		UnitMapping.addMapping(Blocks.DEADBUSH, 1);
		UnitMapping.addMapping(Blocks.DOUBLE_PLANT, 2);
		UnitMapping.addMapping(Items.EMERALD, 1000);
		UnitMapping.addMapping(Blocks.EMERALD_ORE, 2000);
		UnitMapping.addMapping(Blocks.END_STONE, 4);
		UnitMapping.addMapping(Items.CHORUS_FRUIT, 16);
		UnitMapping.addMapping(Items.CHORUS_FRUIT_POPPED, 32);
		UnitMapping.addMapping(Items.ENDER_PEARL, 128);
		UnitMapping.addMapping(Blocks.FROSTED_ICE, 2);
		UnitMapping.addMapping(Items.GLOWSTONE_DUST, 4);
		UnitMapping.addMapping(Items.GOLD_INGOT, 128);
		UnitMapping.addMapping(Blocks.GOLD_ORE, 256);
		UnitMapping.addMapping(Blocks.GRAVEL, 1);
		UnitMapping.addMapping(Blocks.GRASS, 3);
		UnitMapping.addMapping(Blocks.ICE, 1);
		UnitMapping.addMapping(Blocks.IRON_ORE, 32);
		UnitMapping.addMapping(Blocks.LAPIS_ORE, 4);
		UnitMapping.addMapping(Blocks.PUMPKIN, 12);
		UnitMapping.addAllMappings(Blocks.LEAVES, 1);
		UnitMapping.addAllMappings(Blocks.LEAVES2, 1);
		UnitMapping.addMapping(Items.SLIME_BALL, 17);
		UnitMapping.addMapping(Items.MELON, 3);
		UnitMapping.addMapping(Blocks.MOB_SPAWNER, 321);
		UnitMapping.addAllMappings(Blocks.MONSTER_EGG, 5);
		UnitMapping.addMapping(Blocks.MOSSY_COBBLESTONE, 4);
		UnitMapping.addMapping(Blocks.MYCELIUM, 4);
		UnitMapping.addMapping(Items.NETHERBRICK, 2);
		UnitMapping.addMapping(Items.NETHER_WART, 16);
		UnitMapping.addMapping(Blocks.PACKED_ICE, 2);
		UnitMapping.addMapping(Items.PRISMARINE_SHARD, 7);
		UnitMapping.addMapping(Blocks.QUARTZ_ORE, 48);
		UnitMapping.addMapping(Blocks.RED_MUSHROOM_BLOCK, 10);
		UnitMapping.addMapping(Blocks.REDSTONE_ORE, 27);
		UnitMapping.addAllMappings(Blocks.RED_FLOWER, 1);
		UnitMapping.addMapping(Blocks.SAPLING, 14);
		UnitMapping.addMapping(Items.PRISMARINE_CRYSTALS, 28);
		UnitMapping.addMapping(Items.SNOWBALL, 1);
		UnitMapping.addMapping(Blocks.SPONGE, -130);
		UnitMapping.addAllMappings(Blocks.STONE, 2);
		UnitMapping.addMapping(Items.GUNPOWDER, 7);
		UnitMapping.addMapping(Blocks.TALLGRASS, 1);
		UnitMapping.addMapping(Blocks.VINE, 3);
		UnitMapping.addMapping(Blocks.WATERLILY, 3);
		UnitMapping.addMapping(Blocks.WEB, 29);
		UnitMapping.addAllMappings(Blocks.YELLOW_FLOWER, 1);
		UnitMapping.addMapping(Items.APPLE, 15);
		UnitMapping.addMapping(Items.FEATHER, 6);
		UnitMapping.addMapping(Items.FLINT, 2);
		UnitMapping.addMapping(Items.POTATO, 10);
//		UnitMapping.addMapping(Items.BAKED_POTATO, 20);
		UnitMapping.addMapping(Items.BEEF, 22);
		UnitMapping.addMapping(Items.BEETROOT, 11);
		UnitMapping.addMapping(Items.BEETROOT_SEEDS, 1);
		UnitMapping.addMapping(Items.CARROT, 10);
		UnitMapping.addMapping(Items.CHICKEN, 7);
//		UnitMapping.addMapping(Items.COOKED_BEEF, 44);
//		UnitMapping.addMapping(Items.COOKED_CHICKEN, 14);
		UnitMapping.addMapping(Items.FISH, 7);
//		UnitMapping.addMapping(Items.COOKED_FISH, 14);
		UnitMapping.addMapping(Items.MUTTON, 10);
//		UnitMapping.addMapping(Items.COOKED_MUTTON, 20);
		UnitMapping.addMapping(Items.PORKCHOP, 21);
//		UnitMapping.addMapping(Items.COOKED_PORKCHOP, 42);
		UnitMapping.addMapping(Items.RABBIT, 7);
//		UnitMapping.addMapping(Items.COOKED_RABBIT, 14);
		UnitMapping.addMapping(Items.CHAINMAIL_BOOTS, 100);
		UnitMapping.addMapping(Items.CHAINMAIL_CHESTPLATE, 100);
		UnitMapping.addMapping(Items.CHAINMAIL_HELMET, 100);
		UnitMapping.addMapping(Items.CHAINMAIL_LEGGINGS, 100);
		UnitMapping.addMapping(Items.DRAGON_BREATH, 100);
		UnitMapping.addMapping(Items.ELYTRA, 1234);
		UnitMapping.addAllMappings(Items.ENCHANTED_BOOK, 167);
		UnitMapping.addMapping(Items.GHAST_TEAR, 50);
		UnitMapping.addMapping(Items.END_CRYSTAL, 202);
		UnitMapping.addMapping(Items.EXPERIENCE_BOTTLE, 13);
		UnitMapping.addMapping(Items.SPIDER_EYE, 17);
		UnitMapping.addMapping(Items.FIREWORK_CHARGE, 17);
		UnitMapping.addMapping(Items.FIREWORKS, 20);
		UnitMapping.addMapping(Items.FILLED_MAP, 90);
		UnitMapping.addMapping(Items.GOLDEN_HORSE_ARMOR, 768);
		UnitMapping.addMapping(Items.IRON_HORSE_ARMOR, 96);
		UnitMapping.addMapping(Items.KNOWLEDGE_BOOK, 16);
		UnitMapping.addMapping(Items.LAVA_BUCKET, 50);
		UnitMapping.addMapping(Items.LINGERING_POTION, 50);
		UnitMapping.addMapping(Items.MELON_SEEDS, 1);
		UnitMapping.addMapping(Items.NAME_TAG, 36);
		UnitMapping.addMapping(Items.POISONOUS_POTATO, 100);
		UnitMapping.addMapping(Items.PUMPKIN_SEEDS, 1);
		UnitMapping.addMapping(Items.POTIONITEM, 10);
		UnitMapping.addMapping(Items.RABBIT_FOOT, 13);
		UnitMapping.addMapping(Items.RABBIT_HIDE, 9);
		UnitMapping.addMapping(Items.RABBIT_STEW, 55);
		UnitMapping.addMapping(Items.RECORD_11, 1000);
		UnitMapping.addMapping(Items.RECORD_13, 1000);
		UnitMapping.addMapping(Items.RECORD_BLOCKS, 1000);
		UnitMapping.addMapping(Items.RECORD_CAT, 1000);
		UnitMapping.addMapping(Items.RECORD_CHIRP, 1000);
		UnitMapping.addMapping(Items.RECORD_FAR, 1000);
		UnitMapping.addMapping(Items.RECORD_MALL, 1000);
		UnitMapping.addMapping(Items.RECORD_MELLOHI, 1000);
		UnitMapping.addMapping(Items.RECORD_STAL, 1000);
		UnitMapping.addMapping(Items.RECORD_STRAD, 1000);
		UnitMapping.addMapping(Items.RECORD_WAIT, 1000);
		UnitMapping.addMapping(Items.RECORD_WARD, 1000);
		UnitMapping.addMapping(Items.ROTTEN_FLESH, 5);
		UnitMapping.addMapping(Items.SADDLE, 45);
		UnitMapping.addMapping(Items.SPAWN_EGG, 1000);
		UnitMapping.addMapping(Items.SPLASH_POTION, 12);
		UnitMapping.addMapping(Items.TIPPED_ARROW, 15);
		UnitMapping.addMapping(Items.TOTEM_OF_UNDYING, 10000);
		UnitMapping.addMapping(Items.WATER_BUCKET, 49);
		UnitMapping.addMapping(Items.WRITTEN_BOOK, 50);
		UnitMapping.addMapping(BlockHandler.blueprintMaker, 10);
		UnitMapping.addMapping(BlockHandler.condenser, 1000);
		UnitMapping.addMapping(BlockHandler.inverter, 5000);
		UnitMapping.addMapping(BlockHandler.printer, 1000);
		
		permanentMappings.forEach(new BiConsumer<ItemStack, Integer>(){
			@Override
			public void accept(ItemStack stack, Integer value) {
				UnitMapping.addMapping(stack, value);
			}
		});
		
		deriveMappings(CommonProxy.recipeRegistry);
	}

	private static void deriveMappings(IForgeRegistry<IRecipe> recipes) {
		IForgeRegistry<Block> blocks = ForgeRegistries.BLOCKS;
		IForgeRegistry<Item> items = ForgeRegistries.ITEMS;
		populateRecipeMap(recipes);
		for(Block block : blocks) {
			deriveMapping(block, recipes);
		}
		for(Item item : items) {
			deriveMapping(item, recipes);
		}
	}

	private static void populateRecipeMap(IForgeRegistry<IRecipe> recipes) {
		for(IRecipe irecipe : recipes) {
			if(irecipe instanceof ShapedRecipes) {
				ShapedRecipes recipe = (ShapedRecipes) irecipe;
				ItemStack result = recipe.getRecipeOutput();
				List<Ingredient> ingredients = recipe.getIngredients();
				recipeMapIn.add(ingredients);
				recipeMapOut.add(result);
			}
			if(irecipe instanceof ShapelessRecipes) {
				ShapelessRecipes recipe = (ShapelessRecipes) irecipe;
				ItemStack result = recipe.getRecipeOutput();
				List<Ingredient> ingredients = recipe.getIngredients();
				recipeMapIn.add(ingredients);
				recipeMapOut.add(result);
			}
			if(irecipe instanceof ShapedOreRecipe) {
				ShapedOreRecipe recipe = (ShapedOreRecipe) irecipe;
				ItemStack result = recipe.getRecipeOutput();
				List<Ingredient> ingredients = recipe.getIngredients();
				recipeMapIn.add(ingredients);
				recipeMapOut.add(result);
			}
			if(irecipe instanceof ShapelessOreRecipe) {
				ShapelessOreRecipe recipe = (ShapelessOreRecipe) irecipe;
				ItemStack result = recipe.getRecipeOutput();
				List<Ingredient> ingredients = recipe.getIngredients();
				recipeMapIn.add(ingredients);
				recipeMapOut.add(result);
			}
		}
	}

	private static void deriveMapping(Item item, IForgeRegistry<IRecipe> recipes) {
		if(item.getHasSubtypes()) {
			NonNullList<ItemStack> items = NonNullList.<ItemStack>create();
			CreativeTabs[] tabs = CreativeTabs.CREATIVE_TAB_ARRAY;
			for(CreativeTabs tab : tabs) {
				item.getSubItems(tab, items);
			}
			for(ItemStack stack : items) {
				deriveMapping(stack, recipes);
			}
		}
		deriveMapping(new ItemStack(item, 1, 0), recipes);
	}

	private static void deriveMapping(ItemStack stack, IForgeRegistry<IRecipe> recipes) {
		deriveMapping(stack);
	}

	private static void deriveMapping(ItemStack stack) {
		if(!contains(alreadyChecked, stack)) {
			alreadyChecked.add(stack);
			if(!stack.isEmpty() && !UnitMapping.hasValueFor(stack)) {
				int value = 0;
				value = getValueFromOreDict(stack);
				if(value != 0) {
					UnitMapping.addMapping(stack, value);
//					System.out.println("Derived value for " + stack.getDisplayName() + ": " + value);
					return;
				}
				
				value = getValueAsRecipeOutput(stack);
				
				if(value != 0) {
					UnitMapping.addMapping(stack, value);
//					System.out.println("Derived value for " + stack.getDisplayName() + ": " + value);
					return;
				}
				
				value = getValueAsSmeltingOutput(stack);
				
				if(value != 0) {
					UnitMapping.addMapping(stack, value);
//					System.out.println("Derived value for " + stack.getDisplayName() + ": " + value);
					return;
				}
				
			}
		}
	}

	private static int getValueAsSmeltingOutput(ItemStack stack) {
		if(stack.getItem().getRegistryName().getResourcePath().equals("iron") && stack.getItem().getRegistryName().getResourceDomain().equals("iron")) {
			System.out.println("");
		}
		input.clear();
		containsValue = false;
		FurnaceRecipes.instance().getSmeltingList().forEach(new BiConsumer<ItemStack, ItemStack>(){
			@Override
			public void accept(ItemStack instack, ItemStack outstack) {
				if(ItemStackHelper.areStacksEqual(stack, outstack)) {
					containsValue = true;
					input.add(instack);
				}
			}
		});
		if(containsValue) {
			for(int i = 0; i < input.size(); i++) {
				ItemStack in = input.get(i);
				int value = 0;
				if(UnitMapping.hasValueFor(in)) {
					return UnitMapping.getValueFor(in) * 2;
				}else {
					deriveMapping(in);
				}
				if(UnitMapping.hasValueFor(in)) {
					return UnitMapping.getValueFor(in) * 2;
				}
			}
		}
		return 0;
	}

	private static boolean contains(List<ItemStack> alreadyChecked, ItemStack stack) {
		for(ItemStack itemstack : alreadyChecked) {
			if(itemstack.isItemEqual(stack)) {
				return true;
			}
		}
		return false;
	}

	private static int getValueAsRecipeOutput(ItemStack stack) {
		List<List<Ingredient>> ingredientsList = getIngredientsListFor(stack);
		for(List<Ingredient> ingredients : ingredientsList) {
			int totalValue = 0;
			for(Ingredient ingredient : ingredients) {
				if(!ingredient.equals(Ingredient.EMPTY) && ingredient.getMatchingStacks().length > 0) {
					ItemStack[] itemstacks = ingredient.getMatchingStacks();
					boolean gotValue = false;
					int value = 0;
					for(ItemStack itemstack : itemstacks) {
						if(!gotValue) {
							if(UnitMapping.hasValueFor(itemstack)) {
								value = UnitMapping.getValueFor(itemstack);
								gotValue = true;
							}else {
								deriveMapping(itemstack);
							}
							if(UnitMapping.hasValueFor(itemstack)) {
								value = UnitMapping.getValueFor(itemstack);
								gotValue = true;
							}
						}
					}
					if(gotValue) {
						totalValue += value;
					}else {
						totalValue = 0;
						break;
					}
				}
			}
			if(totalValue != 0) {
				return totalValue / (recipeMapOut.get(recipeMapIn.indexOf(ingredients)).getCount());
			}
		}
		return 0;
	}

	private static List<List<Ingredient>> getIngredientsListFor(ItemStack stack) {
		List<List<Ingredient>> ingredientsList = new ArrayList<List<Ingredient>>();
		for(int i = 0; i < recipeMapOut.size(); i++) {
			if(recipeMapOut.get(i).isItemEqual(stack)) {
				ingredientsList.add(recipeMapIn.get(i));
			}
		}
		return ingredientsList;
	}

	private static int getValueFromOreDict(ItemStack stack) {
		if(OreDictionary.getOreIDs(stack).length > 0) {
			int[] ids = OreDictionary.getOreIDs(stack);
			ArrayList<String> names = new ArrayList<String>();
			for(int id : ids) {
				names.add(OreDictionary.getOreName(id));
			}
			for(String name : names) {
				List<ItemStack> stacks = OreDictionary.getOres(name);
				for(ItemStack testStack : stacks) {
					if(UnitMapping.hasValueFor(testStack)) {
						return UnitMapping.getValueFor(stack);
					}
				}
			}
		}
		return 0;
	}

	private static void deriveMapping(Block block, IForgeRegistry<IRecipe> recipes) {
		Item item = (new ItemStack(block)).getItem();
		deriveMapping(item, recipes);
	}

	public static void addPermanentMapping(ItemStack stack, int value) {
		permanentMappings.put(stack, value);
	}
	
}
