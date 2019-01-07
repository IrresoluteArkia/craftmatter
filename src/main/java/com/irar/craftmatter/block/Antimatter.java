package com.irar.craftmatter.block;

import java.util.Random;

import com.irar.craftmatter.handlers.CreativeTabsHandler;
import com.irar.craftmatter.item.ItemAntiBlock;
import com.irar.craftmatter.item.ItemAntiCraft;
import com.irar.craftmatter.item.ItemAntiItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;

public class Antimatter extends Block{
    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    protected static final AxisAlignedBB ANTIMATTER_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
	public Antimatter(Material mat, String name, float hardness, float resistance){
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CM);
		setHardness(hardness);
		setResistance(resistance);
		setTickRandomly(true);
		setDefaultState(blockState.getBaseState().withProperty(DECAYABLE, true));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState state = this.getDefaultState();
		ItemStack stack = placer.getHeldItem(hand);
        IExtendedBlockState eState = (IExtendedBlockState) state.withProperty(DECAYABLE, false);
        return eState;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if((Boolean) state.getValue(DECAYABLE)) {
			super.updateTick(worldIn, pos, state, rand);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}

	@Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		if(state.getValue(DECAYABLE).booleanValue()) {
			if(entityIn instanceof EntityItem) {
				EntityItem entityItem = (EntityItem) entityIn;
				if(entityItem.getItem().getItem() instanceof ItemBlock) {
					ItemBlock ib = (ItemBlock) entityItem.getItem().getItem();
					if(ib.getBlock() instanceof AntiBlock) {
						return;
					}
				}
				if(entityItem.getItem().getItem() instanceof ItemAntiCraft || entityItem.getItem().getItem() instanceof ItemAntiItem) {
					return;
				}
			}
			boolean success = entityIn.attackEntityFrom(DamageSource.WITHER, 5.0F);
			if(success) {
				worldIn.setBlockToAir(pos);
			}
		}
    }
	
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
		if((Boolean) blockState.getValue(DECAYABLE)) {
			return ANTIMATTER_COLLISION_AABB;
		}
		return super.getCollisionBoundingBox(blockState, worldIn, pos);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
    	if(state.getValue(DECAYABLE).booleanValue()) {
    		return 0;
    	}
    	return 1;
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	if(meta == 0) {
    		return this.getDefaultState().withProperty(DECAYABLE, true);
    	}
		return this.getDefaultState().withProperty(DECAYABLE, false);
    }
	
    @Override
    public BlockStateContainer createBlockState() {
    	return (new BlockStateContainer.Builder(this)).add(DECAYABLE).build();
    }

	
}
