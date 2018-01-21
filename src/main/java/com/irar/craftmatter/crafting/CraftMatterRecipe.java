package com.irar.craftmatter.crafting;

import java.util.function.Consumer;

import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CraftMatterRecipe implements IRecipe{
	private boolean matches;
	private ResourceLocation name = new ResourceLocation("craft_craft_recipe");
	
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
		matches = true;
		if(!inv.isEmpty()) {
			CommonProxy.recipeRegistry.forEach(new Consumer<IRecipe>() {
				@Override
				public void accept(IRecipe recipe) {
					if(!(recipe instanceof CraftMatterRecipe)) {
						if(recipe.matches(inv, worldIn)) {
							matches = false;
						}
					}
				}});
		}
		return matches;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		int value = 0;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack.getItem().hasContainerItem(stack)) {
				ItemStack container = stack.getItem().getContainerItem(stack);
				value += UnitMapping.getValueFor(stack) - UnitMapping.getValueFor(container);
			}else {
				value += UnitMapping.getValueFor(stack);
			}
		}
		
		if(value > 0) {
			return ItemCraft.getCraftMatterWithUnits(value);
		}else if(value < 0) {
			return ItemAntiCraft.getCraftAntiMatterWithUnits(- value);
		}else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ItemHandler.craftMatter);
	}

}
