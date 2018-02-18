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

public class ItemBlueprint extends Item implements IDontShowMatter{
	
//	public static final String ITEM_KEY = Ref.MODID + "_" + "CRAFT_ITEM_KEY";
	public static final String AMOUNT_KEY = Ref.MODID + "_" + "CRAFT_AMOUNT_KEY";
//	public static final String META_KEY = Ref.MODID + "_" + "CRAFT_META_KEY";
	public static final String STACK_KEY = Ref.MODID + "_" + "CRAFT_STACK_NBT_KEY";
	
	public ItemBlueprint(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CM);
	}
	
	public static ItemStack getBlueprintWithItemStack(ItemStack stack, int amountToPrint){
		ItemStack itemstack = new ItemStack(ItemHandler.blueprint, 1);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setInteger(AMOUNT_KEY, amountToPrint);
		itemstack.getTagCompound().setTag(STACK_KEY, stack.serializeNBT());
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(STACK_KEY)){
			NBTTagCompound stackData = (NBTTagCompound) stack.getTagCompound().getTag(STACK_KEY);
			ItemStack itemstack = new ItemStack(stackData);
			tooltip.add("Contains Blueprint For: " + itemstack.getItem().getItemStackDisplayName(itemstack));
			if(stack.getTagCompound().hasKey(AMOUNT_KEY)) {
				tooltip.add("Requires " + stack.getTagCompound().getInteger(AMOUNT_KEY) + " Units Of Antimatter To Print");
			}else {
				tooltip.add("Invalid Antimatter Amount: This Can NOT Be Printed");
			}
		}else{
			tooltip.add("Invalid Itemstack: please get this item through proper methods!");
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public static ItemStack getContainedItemStack(ItemStack itemstack) {
		ItemStack stack = ItemStack.EMPTY;
		if(itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey(STACK_KEY)){
			stack = new ItemStack((NBTTagCompound) itemstack.getTagCompound().getTag(STACK_KEY));
		}
		return stack;
	}

	public static int getAmount(ItemStack stack) {
		int amount = -1;
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(AMOUNT_KEY)){
			amount = stack.getTagCompound().getInteger(AMOUNT_KEY);
		}
		return amount;
	}

}
