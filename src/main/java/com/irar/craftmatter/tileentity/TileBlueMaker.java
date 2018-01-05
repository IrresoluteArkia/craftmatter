package com.irar.craftmatter.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.crafting.UnitMapping;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemCraft;

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

public class TileBlueMaker extends TileEntity implements ITickable, IInventory{
	
	private Random rand = new Random();
	private int tickNum = 0;
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
    private String customName = "Blueprint Maker";
	private int amountMatter;
	private int matterNeeded = 0;
	private boolean isValid = false;
    
    public TileBlueMaker(){
/*    	if(Minecraft.getMinecraft().world.isRemote) {
    		
    	}*/
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
    
	@Override
	public void update() {
		tickNum++;
/*		if(this.tickNum % 20 == 0) {
			System.out.println(this.amountMatter);
		}*/
		ItemStack toMake = inventory.get(0);
		if(!toMake.isEmpty() && UnitMapping.hasValueFor(toMake)) {
			isValid = true;
			matterNeeded = UnitMapping.getValueFor(toMake);
		}else {
			isValid = false;
			matterNeeded = 0;
		}
		ItemStack matter = inventory.get(1);
		if(!matter.isEmpty() && matter.getItem() instanceof ItemCraft) {
			this.amountMatter += matter.getCount();
			if(ItemCraft.getAmount(matter) == 1) {
				inventory.set(1, ItemStack.EMPTY);
			}else {
				ItemCraft.setAmount(matter, ItemCraft.getAmount(matter) - 1);
			}
			this.markDirty();
		}
	}

	@Override
	public String getName() {
	    return this.hasCustomName() ? this.customName : "container.tile_entity_blue_maker";
	}
	
	@Override
	public boolean hasCustomName() {
	    return this.customName != null && !this.customName.equals("");
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}

	public static int getSizeInventory(int i) {
		return 3;
	}

	@Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

	@Override
	public ItemStack getStackInSlot(int index) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return null;
	    return this.inventory.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventory, index, count);

        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return itemstack;

	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
	    ItemStack stack = this.getStackInSlot(index);
	    this.setInventorySlotContents(index, null);
        this.markDirty();
	    return stack;
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
//	    return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
	        this.setInventorySlotContents(i, null);
	}

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

        ItemStackHelper.loadAllItems(compound, this.inventory);

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
        if (compound.hasKey("AMOUNT_MATTER"))
        {
            this.amountMatter = compound.getInteger("AMOUNT_MATTER");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {

        ItemStackHelper.saveAllItems(compound, this.inventory);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }
        compound.setInteger("AMOUNT_MATTER", this.amountMatter);

        
        return super.writeToNBT(compound);
    }
    
	@Override
	public ITextComponent getDisplayName() {
        return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
	}

	public int getAmountMatter() {
		return this.amountMatter ;
	}

	public int getMatterNeeded() {
		return this.matterNeeded ;
	}
	
	public boolean isValidRecipe() {
		return isValid;
	}

    
}
