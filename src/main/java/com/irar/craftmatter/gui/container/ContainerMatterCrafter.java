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

public class ContainerMatterCrafter extends ContainerBase{

	public ContainerMatterCrafter(InventoryPlayer playerInv, TileBase tileBase){
		super(playerInv, tileBase);
		this.addSlotToContainer(new Slot(tileBase, 0, 62, 17));
        this.addSlotToContainer(new Slot(tileBase, 1, 62 + 36, 17));
	}
}
