package com.irar.craftmatter.item;

import java.util.List;

import javax.annotation.Nullable;

import com.irar.craftmatter.Ref;
import com.irar.craftmatter.handlers.CreativeTabsHandler;
import com.irar.craftmatter.handlers.ItemHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCraft extends Item{
	
	public static final String AMOUNT_KEY = Ref.MODID + "_" + "CRAFT_AMOUNT_KEY";
	
	public ItemCraft(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CM);
	}
	
	public static ItemStack getCraftMatterWithUnits(int amount){
		ItemStack itemstack = new ItemStack(ItemHandler.craftMatter, 1);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setInteger(AMOUNT_KEY, amount);
		System.out.println("Adding tier to itemstack");
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(AMOUNT_KEY)){
			int amount = stack.getTagCompound().getInteger(AMOUNT_KEY);
			tooltip.add("Contains " + amount + " Units Of Matter");
		}else{
			tooltip.add("Invalid Units: please get this item through proper methods e.g. practically any crafting recipe!");
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public static int getAmount(ItemStack stack) {
		int amount = -1;
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(AMOUNT_KEY)){
			amount = stack.getTagCompound().getInteger(AMOUNT_KEY);
		}
		return amount;
	}

}
