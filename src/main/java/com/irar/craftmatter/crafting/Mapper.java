package com.irar.craftmatter.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import com.irar.craftmatter.handlers.BlockHandler;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class Mapper {

	private static List<List<Ingredient>> recipeMapIn = new ArrayList<List<Ingredient>>();
	private static List<ItemStack> recipeMapOut = new ArrayList<ItemStack>();
	private static HashMap<ItemStack, Integer> permanentMappings = new HashMap<ItemStack, Integer>();
	
	public static void init() {
		UnitMapping.clear();
		UnitMapping.addMapping(Blocks.LOG, 8);
		UnitMapping.addMapping(Blocks.LOG2, 8);
		UnitMapping.addMapping(Blocks.PLANKS, 2);
		UnitMapping.addMapping(Items.STICK, 1);
		UnitMapping.addMapping(Blocks.ACACIA_FENCE, 3);
		UnitMapping.addMapping(Blocks.ACACIA_FENCE_GATE, 8);
		UnitMapping.addMapping(Blocks.ACACIA_STAIRS, 3);
		UnitMapping.addMapping(Items.IRON_INGOT, 16);
		UnitMapping.addMapping(Items.REDSTONE, 9);
		UnitMapping.addMapping(Blocks.REDSTONE_TORCH, 10);
		UnitMapping.addMapping(Blocks.ACTIVATOR_RAIL, 18);
		UnitMapping.addMapping(Blocks.AIR, 0);
		UnitMapping.addMapping(Blocks.IRON_BLOCK, 144);
		UnitMapping.addMapping(Blocks.ANVIL, 496);
		UnitMapping.addMapping(Blocks.BARRIER, -100);
		UnitMapping.addMapping(Items.STRING, 3);
		UnitMapping.addMapping(Blocks.WOOL, 12);
		UnitMapping.addMapping(Blocks.BED, 39);
		UnitMapping.addMapping(Blocks.BEDROCK, -100);
		UnitMapping.addMapping(Blocks.BIRCH_FENCE, 3);
		UnitMapping.addMapping(Blocks.BIRCH_FENCE_GATE, 8);
		UnitMapping.addMapping(Blocks.BIRCH_STAIRS, 3);
		UnitMapping.addMapping(Items.CLAY_BALL, 1);
		UnitMapping.addMapping(Blocks.CLAY, 4);
		UnitMapping.addMapping(Blocks.HARDENED_CLAY, 8);
		UnitMapping.addMapping(Items.DYE, 1);
		UnitMapping.addMapping(Blocks.STAINED_HARDENED_CLAY, 9);
		UnitMapping.addMapping(Blocks.BLACK_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Items.SHULKER_SHELL, 128);
		UnitMapping.addMapping(Blocks.CHEST, 16);
		UnitMapping.addMapping(Blocks.PURPLE_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.BLACK_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.BLUE_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Items.BONE, 3);
		UnitMapping.addMapping(Blocks.BONE_BLOCK, 9);
		UnitMapping.addMapping(Items.REEDS, 2);
		UnitMapping.addMapping(Items.PAPER, 2);
		UnitMapping.addMapping(Items.LEATHER, 9);
		UnitMapping.addMapping(Items.BOOK, 15);
		UnitMapping.addMapping(Blocks.BOOKSHELF, 57);
		UnitMapping.addMapping(Blocks.COBBLESTONE, 1);
		UnitMapping.addMapping(Items.BLAZE_ROD, 20);
		UnitMapping.addMapping(Blocks.BREWING_STAND, 23);
		UnitMapping.addMapping(Items.BRICK, 2);
		UnitMapping.addMapping(Blocks.BRICK_BLOCK, 8);
		UnitMapping.addMapping(Blocks.BRICK_STAIRS, 12);
		UnitMapping.addMapping(Blocks.BROWN_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.BROWN_MUSHROOM_BLOCK, 10);
		UnitMapping.addMapping(Blocks.BROWN_SHULKER_BOX, 272);
		UnitMapping.addMapping(Items.SKULL, 100);
		UnitMapping.addMapping(Blocks.SOUL_SAND, 10);
		UnitMapping.addMapping(Items.NETHER_STAR, 3400);
		UnitMapping.addMapping(Blocks.SAND, 1);
		UnitMapping.addMapping(Blocks.GLASS, 2);
		UnitMapping.addMapping(Blocks.OBSIDIAN, 2);
		UnitMapping.addMapping(Blocks.BEACON, 3416);
		UnitMapping.addMapping(Blocks.BIRCH_DOOR, 4);
		UnitMapping.addMapping(Blocks.BROWN_MUSHROOM, 10);
		UnitMapping.addMapping(Items.SUGAR, 2);
		UnitMapping.addMapping(Items.MILK_BUCKET, 50);
		UnitMapping.addMapping(Items.WHEAT, 10);
		UnitMapping.addMapping(Items.WHEAT_SEEDS, 1);
		UnitMapping.addMapping(Items.EGG, 5);
		UnitMapping.addMapping(Items.CAKE, 21);
		UnitMapping.addMapping(Blocks.CAKE, 21);
		UnitMapping.addMapping(Blocks.CARPET, 8);
		UnitMapping.addMapping(Items.COAL, 10);
		UnitMapping.addMapping(Blocks.COAL_BLOCK, 90);
		UnitMapping.addMapping(Blocks.COAL_ORE, 20);
		UnitMapping.addMapping(Blocks.COBBLESTONE_WALL, 1);
		UnitMapping.addMapping(Blocks.CONCRETE, 1);
		UnitMapping.addMapping(Blocks.CONCRETE_POWDER, 1);
		UnitMapping.addMapping(Blocks.CRAFTING_TABLE, 8);
		UnitMapping.addMapping(Blocks.CYAN_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.CYAN_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.CACTUS, 2);
		UnitMapping.addMapping(Blocks.CAULDRON, 112);
		UnitMapping.addMapping(Blocks.DARK_OAK_STAIRS, 3);
		UnitMapping.addMapping(Blocks.DARK_OAK_FENCE_GATE, 8);
		UnitMapping.addMapping(Blocks.DARK_OAK_FENCE, 3);
		UnitMapping.addMapping(Blocks.STONE_PRESSURE_PLATE, 4);
		UnitMapping.addMapping(Blocks.DETECTOR_RAIL, 109);
		UnitMapping.addMapping(Items.DIAMOND, 1000);
		UnitMapping.addMapping(Blocks.DIAMOND_BLOCK, 9000);
		UnitMapping.addMapping(Blocks.DIAMOND_ORE, 2000);
		UnitMapping.addMapping(Blocks.DIRT, 1);
		UnitMapping.addMapping(Items.BOW, 12);
		UnitMapping.addMapping(Blocks.DISPENSER, 28);
		UnitMapping.addMapping(Blocks.DRAGON_EGG, 100000);
		UnitMapping.addMapping(Blocks.DROPPER, 16);
		UnitMapping.addMapping(Blocks.DARK_OAK_DOOR, 4);
		UnitMapping.addMapping(Blocks.WOODEN_SLAB, 1);
		UnitMapping.addMapping(Items.QUARTZ, 24);
		UnitMapping.addMapping(Blocks.DAYLIGHT_DETECTOR, 81);
		UnitMapping.addMapping(Blocks.DAYLIGHT_DETECTOR_INVERTED, 81);
		UnitMapping.addMapping(Blocks.DEADBUSH, 1);
		UnitMapping.addMapping(Blocks.DOUBLE_PLANT, 2);
		UnitMapping.addMapping(Items.EMERALD, 1000);
		UnitMapping.addMapping(Blocks.EMERALD_BLOCK, 9000);
		UnitMapping.addMapping(Blocks.EMERALD_ORE, 2000);
		UnitMapping.addMapping(Blocks.ENCHANTING_TABLE, 2023);
		UnitMapping.addMapping(Blocks.END_STONE, 4);
		UnitMapping.addMapping(Blocks.END_BRICKS, 4);
		UnitMapping.addMapping(Items.CHORUS_FRUIT, 16);
		UnitMapping.addMapping(Items.CHORUS_FRUIT_POPPED, 32);
		UnitMapping.addMapping(Blocks.END_ROD, 13);
		UnitMapping.addMapping(Items.ENDER_PEARL, 128);
		UnitMapping.addMapping(Items.BLAZE_POWDER, 10);
		UnitMapping.addMapping(Items.ENDER_EYE, 138);
		UnitMapping.addMapping(Blocks.ENDER_CHEST, 154);
		UnitMapping.addMapping(Blocks.FLOWER_POT, 6);
		UnitMapping.addMapping(Blocks.FROSTED_ICE, 2);
		UnitMapping.addMapping(Blocks.FURNACE, 8);
		UnitMapping.addMapping(Blocks.GLASS_PANE, 1);
		UnitMapping.addMapping(Items.GLOWSTONE_DUST, 4);
		UnitMapping.addMapping(Blocks.GLOWSTONE, 16);
		UnitMapping.addMapping(Items.GOLD_INGOT, 128);
		UnitMapping.addMapping(Blocks.GOLD_BLOCK, 1152);
		UnitMapping.addMapping(Blocks.GOLD_ORE, 256);
		UnitMapping.addMapping(Blocks.GOLDEN_RAIL, 130);
		UnitMapping.addMapping(Blocks.GRAVEL, 1);
		UnitMapping.addMapping(Blocks.GRAY_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.GRAY_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.GREEN_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.GREEN_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.GRASS, 3);
		UnitMapping.addMapping(Blocks.HAY_BLOCK, 90);
		UnitMapping.addMapping(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 32);
		UnitMapping.addMapping(Blocks.HOPPER, 96);
		UnitMapping.addMapping(Blocks.ICE, 1);
		UnitMapping.addMapping(Blocks.IRON_BARS, 6);
		UnitMapping.addMapping(Blocks.IRON_ORE, 32);
		UnitMapping.addMapping(Blocks.IRON_TRAPDOOR, 64);
		UnitMapping.addMapping(Blocks.IRON_DOOR, 48);
		UnitMapping.addMapping(Blocks.JUKEBOX, 1016);
		UnitMapping.addMapping(Blocks.JUNGLE_FENCE, 3);
		UnitMapping.addMapping(Blocks.JUNGLE_FENCE_GATE, 8);
		UnitMapping.addMapping(Blocks.JUNGLE_STAIRS, 3);
		UnitMapping.addMapping(Blocks.JUNGLE_DOOR, 4);
		UnitMapping.addMapping(Blocks.LADDER, 2);
		UnitMapping.addMapping(Blocks.LAPIS_BLOCK, 9);
		UnitMapping.addMapping(Blocks.LAPIS_ORE, 4);
		UnitMapping.addMapping(Blocks.LEVER, 2);
		UnitMapping.addMapping(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.LIGHT_BLUE_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 256);
		UnitMapping.addMapping(Blocks.LIME_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.LIME_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.PUMPKIN, 12);
		UnitMapping.addMapping(Blocks.TORCH, 3);
		UnitMapping.addMapping(Blocks.LIT_PUMPKIN, 15);
		UnitMapping.addMapping(Blocks.LEAVES, 1);
		UnitMapping.addMapping(Blocks.LEAVES2, 1);
		UnitMapping.addMapping(Blocks.MAGENTA_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.MAGENTA_SHULKER_BOX, 272);
		UnitMapping.addMapping(Items.SLIME_BALL, 17);
		UnitMapping.addMapping(Items.MAGMA_CREAM, 27);
		UnitMapping.addMapping(Blocks.MAGMA, 108);
		UnitMapping.addMapping(Items.MELON, 3);
		UnitMapping.addMapping(Blocks.MELON_BLOCK, 27);
		UnitMapping.addMapping(Blocks.MOB_SPAWNER, 321);
		UnitMapping.addMapping(Blocks.MONSTER_EGG, 5);
		UnitMapping.addMapping(Blocks.MOSSY_COBBLESTONE, 4);
		UnitMapping.addMapping(Blocks.MYCELIUM, 4);
		UnitMapping.addMapping(Items.NETHERBRICK, 2);
		UnitMapping.addMapping(Blocks.NETHER_BRICK, 8);
		UnitMapping.addMapping(Blocks.NETHER_BRICK_FENCE, 8);
		UnitMapping.addMapping(Blocks.NETHER_BRICK_STAIRS, 12);
		UnitMapping.addMapping(Items.NETHER_WART, 16);
		UnitMapping.addMapping(Blocks.NETHER_WART_BLOCK, 144);
		UnitMapping.addMapping(Blocks.NOTEBLOCK, 25);
		UnitMapping.addMapping(Blocks.OAK_FENCE, 3);
		UnitMapping.addMapping(Blocks.OAK_FENCE_GATE, 8);
		UnitMapping.addMapping(Blocks.OAK_STAIRS, 3);
		UnitMapping.addMapping(Blocks.OBSERVER, 48);
		UnitMapping.addMapping(Blocks.ORANGE_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.ORANGE_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.OAK_DOOR, 4);
		UnitMapping.addMapping(Blocks.PACKED_ICE, 2);
		UnitMapping.addMapping(Blocks.PINK_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.PINK_SHULKER_BOX, 272);
		UnitMapping.addMapping(Items.PRISMARINE_SHARD, 7);
		UnitMapping.addMapping(Blocks.PRISMARINE, 28);
		UnitMapping.addMapping(Blocks.PURPLE_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.PURPLE_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.PURPUR_BLOCK, 32);
		UnitMapping.addMapping(Blocks.PURPUR_PILLAR, 32);
		UnitMapping.addMapping(Blocks.PURPUR_STAIRS, 48);
//		UnitMapping.addMapping(Blocks.PISTON, 35);
		UnitMapping.addMapping(Blocks.PURPUR_SLAB, 16);
		UnitMapping.addMapping(Blocks.PURPUR_DOUBLE_SLAB, 32);
		UnitMapping.addMapping(Blocks.QUARTZ_BLOCK, 96);
		UnitMapping.addMapping(Blocks.QUARTZ_ORE, 48);
		UnitMapping.addMapping(Blocks.QUARTZ_STAIRS, 144);
		UnitMapping.addMapping(Blocks.RAIL, 6);
		UnitMapping.addMapping(Blocks.RED_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.RED_MUSHROOM_BLOCK, 10);
		UnitMapping.addMapping(Blocks.RED_NETHER_BRICK, 36);
		UnitMapping.addMapping(Blocks.RED_SANDSTONE, 4);
		UnitMapping.addMapping(Blocks.RED_SANDSTONE_STAIRS, 6);
		UnitMapping.addMapping(Blocks.RED_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.REDSTONE_BLOCK, 81);
		UnitMapping.addMapping(Blocks.REDSTONE_LAMP, 52);
		UnitMapping.addMapping(Blocks.REDSTONE_ORE, 27);
		UnitMapping.addMapping(Blocks.RED_FLOWER, 1);
		UnitMapping.addMapping(Blocks.SANDSTONE, 4);
		UnitMapping.addMapping(Blocks.SANDSTONE_STAIRS, 6);
		UnitMapping.addMapping(Blocks.SAPLING, 14);
		UnitMapping.addMapping(Items.PRISMARINE_CRYSTALS, 28);
		UnitMapping.addMapping(Blocks.SEA_LANTERN, 168);
		UnitMapping.addMapping(Blocks.SILVER_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.SILVER_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.SLIME_BLOCK, 153);
		UnitMapping.addMapping(Blocks.SNOW, 4);
		UnitMapping.addMapping(Items.SNOWBALL, 1);
		UnitMapping.addMapping(Blocks.SPONGE, -130);
		UnitMapping.addMapping(Blocks.SPRUCE_FENCE, 3);
		UnitMapping.addMapping(Blocks.SPRUCE_FENCE_GATE, 8);
		UnitMapping.addMapping(Blocks.SPRUCE_STAIRS, 3);
		UnitMapping.addMapping(Blocks.STONE, 2);
		UnitMapping.addMapping(Blocks.STONEBRICK, 2);
		UnitMapping.addMapping(Blocks.STONE_BRICK_STAIRS, 3);
		UnitMapping.addMapping(Blocks.STONE_BUTTON, 2);
		UnitMapping.addMapping(Blocks.STONE_PRESSURE_PLATE, 4);
		UnitMapping.addMapping(Blocks.STONE_STAIRS, 2);
		UnitMapping.addMapping(Blocks.SPRUCE_DOOR, 4);
		UnitMapping.addMapping(Blocks.STAINED_GLASS, 2);
		UnitMapping.addMapping(Blocks.STAINED_GLASS_PANE, 1);
		UnitMapping.addMapping(Blocks.STICKY_PISTON, 52);
		UnitMapping.addMapping(Blocks.STONE_SLAB, 1);
		UnitMapping.addMapping(Blocks.STONE_SLAB2, 1);
		UnitMapping.addMapping(Items.GUNPOWDER, 7);
		UnitMapping.addMapping(Blocks.TNT, 39);
		UnitMapping.addMapping(Blocks.TRAPDOOR, 6);
		UnitMapping.addMapping(Blocks.TRIPWIRE_HOOK, 10);
		UnitMapping.addMapping(Blocks.TRAPPED_CHEST, 26);
		UnitMapping.addMapping(Blocks.TALLGRASS, 1);
		UnitMapping.addMapping(Blocks.VINE, 3);
		UnitMapping.addMapping(Blocks.WATERLILY, 3);
		UnitMapping.addMapping(Blocks.WEB, 29);
		UnitMapping.addMapping(Blocks.WHITE_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.WHITE_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.WOODEN_BUTTON, 2);
		UnitMapping.addMapping(Blocks.WOODEN_PRESSURE_PLATE, 4);
		UnitMapping.addMapping(Blocks.YELLOW_GLAZED_TERRACOTTA, 18);
		UnitMapping.addMapping(Blocks.YELLOW_SHULKER_BOX, 272);
		UnitMapping.addMapping(Blocks.YELLOW_FLOWER, 1);
		UnitMapping.addMapping(Items.ACACIA_BOAT, 10);
		UnitMapping.addMapping(Items.APPLE, 15);
		UnitMapping.addMapping(Items.FEATHER, 6);
		UnitMapping.addMapping(Items.FLINT, 2);
		UnitMapping.addMapping(Items.ARROW, 2);
		UnitMapping.addMapping(Items.ARMOR_STAND, 7);
		UnitMapping.addMapping(Items.POTATO, 10);
		UnitMapping.addMapping(Items.BAKED_POTATO, 20);
		UnitMapping.addMapping(Items.BANNER, 73);
		UnitMapping.addMapping(Items.BED, 39);
		UnitMapping.addMapping(Items.BEEF, 22);
		UnitMapping.addMapping(Items.BEETROOT, 11);
		UnitMapping.addMapping(Items.BEETROOT_SEEDS, 1);
		UnitMapping.addMapping(Items.BOWL, 1);
		UnitMapping.addMapping(Items.BEETROOT_SOUP, 67);
		UnitMapping.addMapping(Items.BIRCH_BOAT, 10);
		UnitMapping.addMapping(Items.BIRCH_DOOR, 4);
		UnitMapping.addMapping(Items.BOAT, 10);
		UnitMapping.addMapping(Items.BREAD, 30);
		UnitMapping.addMapping(Items.BREWING_STAND, 23);
		UnitMapping.addMapping(Items.BUCKET, 48);
		UnitMapping.addMapping(Items.CARROT, 10);
		UnitMapping.addMapping(Items.FISHING_ROD, 9);
		UnitMapping.addMapping(Items.CARROT_ON_A_STICK, 19);
		UnitMapping.addMapping(Items.CAULDRON, 112);
		UnitMapping.addMapping(Items.MINECART, 80);
		UnitMapping.addMapping(Items.CHEST_MINECART, 96);
		UnitMapping.addMapping(Items.CHICKEN, 7);
		UnitMapping.addMapping(Items.CLOCK, 521);
		UnitMapping.addMapping(Items.COMPARATOR, 60);
		UnitMapping.addMapping(Items.COMPASS, 73);
		UnitMapping.addMapping(Items.COOKED_BEEF, 44);
		UnitMapping.addMapping(Items.COOKED_CHICKEN, 14);
		UnitMapping.addMapping(Items.FISH, 7);
		UnitMapping.addMapping(Items.COOKED_FISH, 14);
		UnitMapping.addMapping(Items.MUTTON, 10);
		UnitMapping.addMapping(Items.COOKED_MUTTON, 20);
		UnitMapping.addMapping(Items.PORKCHOP, 21);
		UnitMapping.addMapping(Items.COOKED_PORKCHOP, 42);
		UnitMapping.addMapping(Items.RABBIT, 7);
		UnitMapping.addMapping(Items.COOKED_RABBIT, 14);
		UnitMapping.addMapping(Items.COOKIE, 3);
		UnitMapping.addMapping(Items.CHAINMAIL_BOOTS, 100);
		UnitMapping.addMapping(Items.CHAINMAIL_CHESTPLATE, 100);
		UnitMapping.addMapping(Items.CHAINMAIL_HELMET, 100);
		UnitMapping.addMapping(Items.CHAINMAIL_LEGGINGS, 100);
		UnitMapping.addMapping(Items.DARK_OAK_BOAT, 10);
		UnitMapping.addMapping(Items.DARK_OAK_DOOR, 4);
		UnitMapping.addMapping(Items.DIAMOND_AXE, 3002);
		UnitMapping.addMapping(Items.DIAMOND_HOE, 2002);
		UnitMapping.addMapping(Items.DIAMOND_HORSE_ARMOR, 5500);
		UnitMapping.addMapping(Items.DIAMOND_PICKAXE, 3002);
		UnitMapping.addMapping(Items.DIAMOND_SHOVEL, 1002);
		UnitMapping.addMapping(Items.DIAMOND_SWORD, 2001);
		UnitMapping.addMapping(Items.DRAGON_BREATH, 100);
		UnitMapping.addMapping(Items.DIAMOND_BOOTS, 4000);
		UnitMapping.addMapping(Items.DIAMOND_CHESTPLATE, 8000);
		UnitMapping.addMapping(Items.DIAMOND_HELMET, 5000);
		UnitMapping.addMapping(Items.DIAMOND_LEGGINGS, 7000);
		UnitMapping.addMapping(Items.ELYTRA, 1234);
		UnitMapping.addMapping(Items.ENCHANTED_BOOK, 167);
		UnitMapping.addMapping(Items.GHAST_TEAR, 50);
		UnitMapping.addMapping(Items.END_CRYSTAL, 202);
		UnitMapping.addMapping(Items.EXPERIENCE_BOTTLE, 13);
		UnitMapping.addMapping(Items.SPIDER_EYE, 17);
		UnitMapping.addMapping(Items.FERMENTED_SPIDER_EYE, 29);
		UnitMapping.addMapping(Items.FIRE_CHARGE, 9);
		UnitMapping.addMapping(Items.FIREWORK_CHARGE, 17);
		UnitMapping.addMapping(Items.FIREWORKS, 20);
		UnitMapping.addMapping(Items.FLINT_AND_STEEL, 18);
		UnitMapping.addMapping(Items.FURNACE_MINECART, 88);
		UnitMapping.addMapping(Items.FILLED_MAP, 90);
		UnitMapping.addMapping(Items.MAP, 89);
		UnitMapping.addMapping(Items.GLASS_BOTTLE, 2);
		UnitMapping.addMapping(Items.GOLD_NUGGET, 14);
//		UnitMapping.addMapping(Items.GOLDEN_APPLE, 1034);
		UnitMapping.addMapping(Items.GOLDEN_AXE, 386);
//		UnitMapping.addMapping(Items.GOLDEN_CARROT, 122);
		UnitMapping.addMapping(Items.GOLDEN_HOE, 258);
		UnitMapping.addMapping(Items.GOLDEN_HORSE_ARMOR, 768);
		UnitMapping.addMapping(Items.GOLDEN_PICKAXE, 386);
//		UnitMapping.addMapping(Items.GOLDEN_SHOVEL, 130);
		UnitMapping.addMapping(Items.GOLDEN_SWORD, 257);
		UnitMapping.addMapping(Items.GOLDEN_BOOTS, 256);
		UnitMapping.addMapping(Items.GOLDEN_CHESTPLATE, 1024);
		UnitMapping.addMapping(Items.GOLDEN_HELMET, 640);
		UnitMapping.addMapping(Items.GOLDEN_LEGGINGS, 896);
		UnitMapping.addMapping(Items.HOPPER_MINECART, 176);
		UnitMapping.addMapping(Items.IRON_AXE, 50);
		UnitMapping.addMapping(Items.IRON_DOOR, 48);
		UnitMapping.addMapping(Items.IRON_HOE, 34);
		UnitMapping.addMapping(Items.IRON_HORSE_ARMOR, 96);
		UnitMapping.addMapping(Items.IRON_NUGGET, 2);
		UnitMapping.addMapping(Items.IRON_PICKAXE, 50);
		UnitMapping.addMapping(Items.IRON_SHOVEL, 18);
		UnitMapping.addMapping(Items.IRON_SWORD, 33);
		UnitMapping.addMapping(Items.ITEM_FRAME, 17);
		UnitMapping.addMapping(Items.IRON_BOOTS, 64);
		UnitMapping.addMapping(Items.IRON_CHESTPLATE, 128);
		UnitMapping.addMapping(Items.IRON_HELMET, 80);
		UnitMapping.addMapping(Items.IRON_LEGGINGS, 112);
		UnitMapping.addMapping(Items.JUNGLE_BOAT, 10);
		UnitMapping.addMapping(Items.JUNGLE_DOOR, 4);
		UnitMapping.addMapping(Items.KNOWLEDGE_BOOK, 16);
		UnitMapping.addMapping(Items.LEAD, 29);
		UnitMapping.addMapping(Items.LAVA_BUCKET, 50);
		UnitMapping.addMapping(Items.LEATHER_BOOTS, 36);
		UnitMapping.addMapping(Items.LEATHER_CHESTPLATE, 72);
		UnitMapping.addMapping(Items.LEATHER_HELMET, 45);
		UnitMapping.addMapping(Items.LEATHER_LEGGINGS, 63);
		UnitMapping.addMapping(Items.LINGERING_POTION, 50);
		UnitMapping.addMapping(Items.MELON_SEEDS, 1);
		UnitMapping.addMapping(Items.MUSHROOM_STEW, 21);
		UnitMapping.addMapping(Items.NAME_TAG, 36);
		UnitMapping.addMapping(Items.OAK_DOOR, 4);
		UnitMapping.addMapping(Items.PAINTING, 20);
		UnitMapping.addMapping(Items.POISONOUS_POTATO, 100);
		UnitMapping.addMapping(Items.PUMPKIN_PIE, 19);
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
		UnitMapping.addMapping(Items.REPEATER, 35);
		UnitMapping.addMapping(Items.ROTTEN_FLESH, 5);
		UnitMapping.addMapping(Items.SADDLE, 45);
		UnitMapping.addMapping(Items.SHIELD, 28);
		UnitMapping.addMapping(Items.SIGN, 4);
		UnitMapping.addMapping(Items.SPAWN_EGG, 1000);
		UnitMapping.addMapping(Items.SPECKLED_MELON, 115);
		UnitMapping.addMapping(Items.SPECTRAL_ARROW, 15);
		UnitMapping.addMapping(Items.SPRUCE_BOAT, 10);
		UnitMapping.addMapping(Items.SPRUCE_DOOR, 4);
		UnitMapping.addMapping(Items.STONE_AXE, 5);
		UnitMapping.addMapping(Items.STONE_HOE, 4);
		UnitMapping.addMapping(Items.STONE_PICKAXE, 5);
		UnitMapping.addMapping(Items.STONE_SHOVEL, 3);
		UnitMapping.addMapping(Items.STONE_SWORD, 3);
		UnitMapping.addMapping(Items.SHEARS, 32);
		UnitMapping.addMapping(Items.SPLASH_POTION, 12);
		UnitMapping.addMapping(Items.TIPPED_ARROW, 15);
		UnitMapping.addMapping(Items.TNT_MINECART, 119);
		UnitMapping.addMapping(Items.TOTEM_OF_UNDYING, 10000);
		UnitMapping.addMapping(Items.WATER_BUCKET, 49);
		UnitMapping.addMapping(Items.WOODEN_AXE, 8);
		UnitMapping.addMapping(Items.WOODEN_HOE, 6);
		UnitMapping.addMapping(Items.WOODEN_PICKAXE, 8);
		UnitMapping.addMapping(Items.WOODEN_SHOVEL, 4);
		UnitMapping.addMapping(Items.WOODEN_SWORD, 5);
		UnitMapping.addMapping(Items.WRITABLE_BOOK, 30);
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
		if(!stack.isEmpty() && !UnitMapping.hasValueFor(stack)) {
			int value = 0;
			value = getValueFromOreDict(stack);
			if(value != 0) {
				UnitMapping.addMapping(stack, value);
				System.out.println("Derived value for " + stack.getDisplayName() + ": " + value);
				return;
			}
			
			value = getValueAsRecipeOutput(stack);
			
			if(value != 0) {
				UnitMapping.addMapping(stack, value);
				System.out.println("Derived value for " + stack.getDisplayName() + ": " + value);
				return;
			}
			
		}
	}

	private static int getValueAsRecipeOutput(ItemStack stack) {
		List<List<Ingredient>> ingredientsList = getIngredientsListFor(stack);
		for(List<Ingredient> ingredients : ingredientsList) {
			int totalValue = 0;
			for(Ingredient ingredient : ingredients) {
				ItemStack[] itemstacks = ingredient.getMatchingStacks();
				boolean gotValue = false;
				int value = 0;
				for(ItemStack itemstack : itemstacks) {
					if(!gotValue) {
						if(UnitMapping.hasValueFor(itemstack)) {
							value = UnitMapping.getValueFor(itemstack);
							gotValue = true;
						}else {
							if(!itemstack.isItemEqual(stack)) {
								deriveMapping(itemstack);
							}
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
