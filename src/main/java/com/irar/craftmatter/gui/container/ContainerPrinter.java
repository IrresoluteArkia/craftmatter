package com.irar.craftmatter.gui.container;

import javax.annotation.Nullable;

import com.irar.craftmatter.tileentity.TileBlueMaker;
import com.irar.craftmatter.tileentity.TilePrinter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPrinter extends Container{

	private TilePrinter te;
	
	public ContainerPrinter(InventoryPlayer playerInv, TilePrinter tilePrinter){
		this.te = tilePrinter;
		 // Tile Entity, Slot 0-8, Slot IDs 0-8
/*	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 3; ++x) {
	            this.addSlotToContainer(new Slot(tileBlueMaker, x + y * 3, 62 + x * 18, 17 + y * 18));
	        }
	    }
*/
		this.addSlotToContainer(new Slot(tilePrinter, 0, 62 + 18, 17));
        this.addSlotToContainer(new Slot(tilePrinter, 1, 62 + 18, 17 + 36));

	    // Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	    }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}

	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (fromSlot < 2)
            {
                if (!this.mergeItemStack(itemstack1, 2, 39, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 2, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
	}
	
}
