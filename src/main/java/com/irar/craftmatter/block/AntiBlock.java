package com.irar.craftmatter.block;


import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.irar.craftmatter.CraftMatter;
import com.irar.craftmatter.handlers.BlockHandler;
import com.irar.craftmatter.handlers.CreativeTabsHandler;
import com.irar.craftmatter.item.ItemAntiBlock;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemAntiItem;
import com.irar.craftmatter.network.GuiHandler;
import com.irar.craftmatter.tileentity.TileAntiBlock;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AntiBlock extends Block implements ITileEntityProvider{
	public static final IUnlistedProperty<IBlockState> IBS = new IUnlistedProperty<IBlockState>() {

		@Override
		public String getName() {return "craftmatter:blockstate_property";}

		@Override
		public boolean isValid(IBlockState value) {return true;}

		@Override
		public Class<IBlockState> getType() {return IBlockState.class;}

		@Override
		public String valueToString(IBlockState value) {return new Gson().toJson(NBTUtil.writeBlockState(new NBTTagCompound(), value));}
		
	};
	public AntiBlock(Material mat, String name, float hardness, float resistance){
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CM);
		setHardness(hardness);
		setResistance(resistance);
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		IExtendedBlockState es = getExtendedState(state, world, pos);
		IBlockState sCopy = es.getValue(IBS);
		boolean rbp = super.removedByPlayer(state, world, pos, player, willHarvest);
		if(rbp && player != null) {
			if(sCopy != null) {
				ItemStack contained = new ItemStack(sCopy.getBlock(), 1, sCopy.getBlock().getMetaFromState(sCopy));
				spawnAsEntity(world, pos, ItemAntiItem.getWithItemStack(contained));
			}else {
				spawnAsEntity(world, pos, new ItemStack(BlockHandler.antiBlock));
			}
		}
		return rbp;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState state = this.getDefaultState();
		ItemStack stack = placer.getHeldItem(hand);
        IExtendedBlockState eState = (IExtendedBlockState) state;
        eState = eState.withProperty(IBS, ((ItemBlock) ItemAntiBlock.getContainedItemStack(stack).getItem()).getBlock().getDefaultState());
        return eState;
	}
	
	public void onBlockPlacedBy(World worldIn,BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		((TileAntiBlock) worldIn.getTileEntity(pos)).display = ItemAntiBlock.getContainedItemStack(stack);
	}


    
    @Override
    public BlockStateContainer createBlockState() {
    	return (new BlockStateContainer.Builder(this)).add(IBS).build();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return true;
    }
    
    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

    private TileAntiBlock getTE(World world, BlockPos pos) {
        return (TileAntiBlock) world.getTileEntity(pos);
    }
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) {
		return new TileAntiBlock();
	}
	
	@Override
	public boolean hasTileEntity(){
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileAntiBlock();
	}
	
	@Override
	public IExtendedBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
	    IExtendedBlockState ext = (IExtendedBlockState) state;
	    TileEntity te = world.getTileEntity(pos);
	    if (te instanceof TileAntiBlock) {
	        ext = ext.withProperty(IBS, ((ItemBlock) ((TileAntiBlock) te).display.getItem()).getBlock().getDefaultState());
	    }
	    return ext;
	}
	
}
