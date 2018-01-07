package com.irar.craftmatter.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.irar.craftmatter.crafting.UnitMapping;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.item.ItemAntiCraft;
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

public class TilePrinter extends TileEntity implements ITickable, IInventory{
	
	private Random rand = new Random();
	public int tickNum = 0;
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
    private String customName = "Antimatter Printer";
	public int amountMatter;
	private int matterNeeded = 100;
	private boolean isValid = true;
	public static ArrayList<TilePrinter> tiles = new ArrayList<TilePrinter>();
	private boolean nbtserialized = false;
    
    public TilePrinter(){
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
		if(!tiles.contains(this)) {
			tiles.add(this);
			if(!this.world.isRemote) {
				CommonProxy.updatePacket();
			}
//			this.readFromNBT(this.writeToNBT(new NBTTagCompound()));
		}
/*		if(this.tickNum % 20 == 0) {
			System.out.println(this.amountMatter);
		}*/
		ItemStack result = inventory.get(1);
		ItemStack matter = inventory.get(0);
		if(!matter.isEmpty() && matter.getItem() instanceof ItemCraft) {
			this.amountMatter += matter.getCount();
			if(ItemCraft.getAmount(matter) == 1) {
				inventory.set(1, ItemStack.EMPTY);
			}else {
				ItemCraft.setAmount(matter, ItemCraft.getAmount(matter) - 1);
			}
			this.markDirty();
		}
		if((result.isEmpty() || result.getItem() instanceof ItemAntiCraft) && isValid && matterNeeded < amountMatter) {
			amountMatter -= matterNeeded;
			if(result.getItem() instanceof ItemAntiCraft) {
				inventory.set(1, ItemAntiCraft.getCraftAntiMatterWithUnits(ItemAntiCraft.getAmount(result) + 1));
			}else {
				inventory.set(1, ItemAntiCraft.getCraftAntiMatterWithUnits(1));
			}
			this.markDirty();
		}
	}

	@Override
	public String getName() {
	    return this.hasCustomName() ? this.customName : "container.tile_entity_anti_printer";
	}
	
	@Override
	public boolean hasCustomName() {
	    return this.customName != null && !this.customName.equals("");
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	public static int getSizeInventory(int i) {
		return 2;
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
