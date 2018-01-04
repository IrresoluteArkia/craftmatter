package com.irar.craftmatter.block;


import javax.annotation.Nullable;

import com.irar.craftmatter.CraftMatter;
import com.irar.craftmatter.handlers.CreativeTabsHandler;
import com.irar.craftmatter.network.GuiHandler;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlueMaker extends Block implements ITileEntityProvider{
	public BlueMaker(Material mat, String name, float hardness, float resistance, int harvest, String tool){
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CM);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(tool, harvest);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileBlueMaker();
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new TileBlueMaker();
	}
	
	@Override
	public boolean hasTileEntity(){
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		TileBlueMaker te = (TileBlueMaker) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        ((TileBlueMaker) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
	    }
	}
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
	    return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
	    if (!world.isRemote) {
	        player.openGui(CraftMatter.instance, GuiHandler.TILE_BLUE_MAKER_GUI, world, pos.getX(), pos.getY(), pos.getZ());
	    }
	    return true;
	}
	
}
