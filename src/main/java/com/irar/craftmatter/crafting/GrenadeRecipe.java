package com.irar.craftmatter.crafting;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.irar.craftmatter.config.ConfigInts;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.item.ItemGrenade;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GrenadeRecipe implements IRecipe{
	private boolean matches;
	private ResourceLocation name = new ResourceLocation("craft_grenade_recipe");
	boolean hasPowder = false;
	boolean hasAnti = false;

	
	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		this.name = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return this.name;
	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return IRecipe.class;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(!inv.getStackInSlot(i).isEmpty()) {
				items.add(inv.getStackInSlot(i));
			}
		}
		if(items.size() == 2) {
			hasPowder = false;
			hasAnti = false;
			items.forEach(new Consumer<ItemStack>() {
				@Override
				public void accept(ItemStack stack) {
					if(stack.getItem().equals(Items.GUNPOWDER)) {
						hasPowder = true;
					}
					if(stack.getItem() instanceof ItemAntiCraft && ItemAntiCraft.getAmount(stack) <= ConfigInts.ANTI_GRENADE_MAX_ANTIMATTER.currentValue) {
						hasAnti = true;
					}
					
				}
			});
			if(hasPowder && hasAnti) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack stack = ItemStack.EMPTY;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(inv.getStackInSlot(i).getItem() instanceof ItemAntiCraft) {
				stack = inv.getStackInSlot(i);
			}
		}
		
		return ItemGrenade.getCraftMatterWithUnits(ItemAntiCraft.getAmount(stack));
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ItemHandler.antiGrenade);
	}

}
