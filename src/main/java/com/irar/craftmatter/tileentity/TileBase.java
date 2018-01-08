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

public class TileBase extends TileEntity implements ITickable, IInventory{
	
	private Random rand = new Random();
	protected int tickNum = 0;
    protected NonNullList<ItemStack> inventory/* = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY)*/;
    protected String customName = "";
	public int amountMatter = 0;
	private int prevMatter = 0;
	public boolean storesMatter = true;
	protected int matterNeeded = 0;
	protected boolean isValid = false;
	public static ArrayList<TileBase> tiles = new ArrayList<TileBase>();
	public boolean matterSet = false;
    
    public TileBase(){
    	this.inventory = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    }

    public TileBase(String name, int numSlots, boolean storesMatter){
    	this.customName = name;
    	this.storesMatter = storesMatter;
    	this.inventory = NonNullList.<ItemStack>withSize(numSlots, ItemStack.EMPTY);
    }

    private boolean hasMatterChanged() {
    	if(!(amountMatter == prevMatter)) {
    		prevMatter = amountMatter;
    		return true;
    	}
    	return false;
    }
    
    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
    
	@Override
	public void update() {
		this.tickNum++;
		if(!matterSet) {
			int matter = this.getAmountMatterFromItemStack();
			if(this.amountMatter == 0 && matter > 0) {
				this.amountMatter = matter;
				matterSet = true;
			}
		}
		/*if(!matterLoaded) {
	        this.amountMatter = this.getAmountMatterFromItemStack();
	        matterLoaded = true;
		}else {
			this.writeMatterToItemStack();
		}*/
 	}

	@Override
	public String getName() {
	    return this.hasCustomName() ? this.customName : "container.tile_entity_" + this.customName.toLowerCase().replace(" ", "_");
	}
	
	@Override
	public boolean hasCustomName() {
	    return this.customName != null && !this.customName.equals("");
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.size();
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
        this.amountMatter = this.getAmountMatterFromItemStack();
/*        if(world.isRemote) {
        	CommonProxy.updateData(pos.getX(), pos.getY(), pos.getZ(), amountMatter);
        }*/
    }

    private int getAmountMatterFromItemStack() {
    	ItemStack stack = inventory.get(0);
    	if(stack.getItem() instanceof ItemCraft) {
    		return ItemCraft.getAmount(stack);
    	}else if(stack.getItem() instanceof ItemAntiCraft) {
    		return ItemAntiCraft.getAmount(stack);
    	}
		return 0;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {

		super.writeToNBT(compound);
		this.writeMatterToItemStack();
        ItemStackHelper.saveAllItems(compound, this.inventory);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }
        
/*        if(world.isRemote) {
        	CommonProxy.updateData(pos.getX(), pos.getY(), pos.getZ(), amountMatter);
        }*/
        return compound;
    }
    
	public void writeMatterToItemStack() {
		if(this.storesMatter) {
			this.inventory.set(0, ItemCraft.getCraftMatterWithUnits(this.amountMatter));
		}else{
			this.inventory.set(0, ItemAntiCraft.getCraftAntiMatterWithUnits(this.amountMatter));
		}
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
		return this.isValid;
	}

    
}
