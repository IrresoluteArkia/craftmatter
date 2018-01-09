package com.irar.craftmatter.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.crafting.UnitMapping;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemAntiItem;
import com.irar.craftmatter.item.ItemBlueprint;
import com.irar.craftmatter.item.ItemCraft;
import com.irar.craftmatter.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.oredict.OreDictionary;

public class TileInverter extends TileBase{
	
    public TileInverter(){
    	super("Antimatter Inverter", 2, true);
    	displayMatter = false;
    }
    
	@Override
	public void update() {
		super.update();
		ItemStack stack = inventory.get(1);
		if(!stack.isEmpty() && stack.getItem() instanceof ItemAntiItem) {
			int count = stack.getCount();
			ItemStack replacement = ItemAntiItem.getContainedItemStack(stack);
			if(!replacement.isEmpty()) {
				replacement.setCount(count);
				inventory.set(1, replacement);
				this.markDirty();
			}
		}

	}
    
}
