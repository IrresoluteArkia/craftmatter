package com.irar.craftmatter.item;

import com.irar.craftmatter.handlers.CreativeTabsHandler;
import com.irar.craftmatter.handlers.ItemHandler;

import java.util.List;

import javax.annotation.Nullable;

import com.irar.craftmatter.Ref;
import com.irar.craftmatter.config.ConfigBooleans;
import com.irar.craftmatter.entity.projectile.EntityGrenade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemGrenade extends Item implements IDontShowMatter{
	public static final String AMOUNT_KEY = Ref.MODID + "_" + "CRAFT_AMOUNT_KEY";

	public ItemGrenade(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CM);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
    	if(ConfigBooleans.ANTI_GRENADE_ENABLED.currentValue) {
	        ItemStack itemstack = playerIn.getHeldItem(handIn);
	        
	        int amount = ItemGrenade.getAmount(itemstack);
	
	        if (!playerIn.capabilities.isCreativeMode){
	            itemstack.shrink(1);
	        }
	
	        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	
	        if (!worldIn.isRemote){
	            EntityGrenade entitygrenade = new EntityGrenade(worldIn, playerIn, amount);
	            entitygrenade.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
	            worldIn.spawnEntity(entitygrenade);
	        }
	
	        playerIn.addStat(StatList.getObjectUseStats(this));
	        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    	}
    	return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    
	public static ItemStack getCraftMatterWithUnits(int amount){
		ItemStack itemstack = new ItemStack(ItemHandler.antiGrenade, 1);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setInteger(AMOUNT_KEY, amount);
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey(AMOUNT_KEY)){
			int amount = stack.getTagCompound().getInteger(AMOUNT_KEY);
			tooltip.add("Contains " + amount + " Units Of Antimatter");
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