package com.irar.craftmatter.gui.container;

import javax.annotation.Nullable;

import com.irar.craftmatter.tileentity.TileBase;
import com.irar.craftmatter.tileentity.TileBlueMaker;
import com.irar.craftmatter.tileentity.TilePrinter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInverter extends ContainerBase{

	public ContainerInverter(InventoryPlayer playerInv, TileBase tilePrinter){
		super(playerInv, tilePrinter);
		this.addSlotToContainer(new Slot(tilePrinter, 1, 62 + 18, 17 + 18));
	}

}
