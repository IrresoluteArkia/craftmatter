package com.irar.craftmatter.gui.container;

import javax.annotation.Nullable;

import com.irar.craftmatter.gui.slot.SlotStoredMatter;
import com.irar.craftmatter.tileentity.Stores;
import com.irar.craftmatter.tileentity.TileBase;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBase extends Container{

	private TileBase te;
	
	public ContainerBase(InventoryPlayer playerInv, TileBase tileBase){
		this.te = tileBase;
		if(!tileBase.storesMatter.equals(Stores.NEITHER)) {
			this.addSlotToContainer(new SlotStoredMatter(tileBase));
		}
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }
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
		int sizeInv = this.te.getSizeInventory();
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (fromSlot < 36)
            {
                if (!this.mergeItemStack(itemstack1, 36, 36 + sizeInv, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 36, true))
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
