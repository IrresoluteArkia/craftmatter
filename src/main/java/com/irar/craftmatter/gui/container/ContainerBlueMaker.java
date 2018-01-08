package com.irar.craftmatter.gui.container;

import javax.annotation.Nullable;

import com.irar.craftmatter.tileentity.TileBase;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBlueMaker extends ContainerBase{

	public ContainerBlueMaker(InventoryPlayer playerInv, TileBase tileBlueMaker){
		super(playerInv, tileBlueMaker);
		this.addSlotToContainer(new Slot(tileBlueMaker, 1, 62, 17));
        this.addSlotToContainer(new Slot(tileBlueMaker, 2, 62 + 36, 17));
        this.addSlotToContainer(new Slot(tileBlueMaker, 3, 62 + 18, 17 + 36));
	}
}
