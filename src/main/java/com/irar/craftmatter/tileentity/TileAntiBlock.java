package com.irar.craftmatter.tileentity;

import com.irar.craftmatter.block.AntiBlock;
import com.irar.craftmatter.handlers.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.property.IExtendedBlockState;

public class TileAntiBlock extends TileEntity{

	public ItemStack display;
	
	public TileAntiBlock(ItemStack display) {
		this();
		this.display = display;
	}

	public TileAntiBlock() {
		this.display = new ItemStack(BlockHandler.antiBlock);
	}
	
	@Override
	public boolean hasFastRenderer(){
		return true;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if(display != null) {
			tag.setTag("CONTAINED", display.serializeNBT());
		}
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if(tag.hasKey("CONTAINED")) {
			display = new ItemStack((NBTTagCompound) tag.getTag("CONTAINED"));
/*			BlockPos pos = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
			IBlockState block = world.getBlockState(pos);
			IExtendedBlockState es = (IExtendedBlockState) block;
			world.setBlockState(pos, es.withProperty(AntiBlock.IBS, ((ItemBlock) display.getItem()).getBlock().getDefaultState()));*/
		}
	}
}
