package com.irar.craftmatter.model;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import com.irar.craftmatter.block.AntiBlock;
import com.irar.craftmatter.item.ItemAntiBlock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;

public class AntiBakedModel implements IBakedModel {
	
//	public final IBlockState state;

	public AntiBakedModel() {
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState someState, EnumFacing side, long rand) {
		if (!(someState instanceof IExtendedBlockState))
			return placeHolder(someState, side, rand);

		IBakedModel wanted = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(Blocks.SNOW.getDefaultState());
		IExtendedBlockState state = (IExtendedBlockState) someState;
		IBlockState copy = state.getValue(AntiBlock.IBS);

		Minecraft mc = Minecraft.getMinecraft();
		BlockRendererDispatcher blockRendererDispatcher = mc.getBlockRendererDispatcher();
		BlockModelShapes blockModelShapes = blockRendererDispatcher.getBlockModelShapes();
		IBakedModel copiedBlockModel = blockModelShapes.getModelForState(copy);
		wanted = copiedBlockModel;
		return wanted.getQuads(copy, side, rand);
	}
	
	public List<BakedQuad> placeHolder(IBlockState someState, EnumFacing side, long rand) {
		return Minecraft.getMinecraft().getBlockRendererDispatcher()
				.getModelForState(Blocks.COBBLESTONE.getDefaultState()).getQuads(someState, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return true;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("craftmatter:blocks/anti_block");
	}

	@Override
	public ItemOverrideList getOverrides() {
		return itemHandler;
	}

	private final ItemOverrideList itemHandler = new ItemOverrideList(ImmutableList.of()) {
		@Nonnull
		@Override
		public IBakedModel handleItemState(@Nonnull IBakedModel model, ItemStack stack, World world,
				EntityLivingBase entity) {

			if (!stack.hasTagCompound())
				return Minecraft.getMinecraft().getBlockRendererDispatcher()
						.getModelForState(Blocks.COBBLESTONE.getDefaultState());

			IBlockState state = ((ItemBlock) ItemAntiBlock.getContainedItemStack(stack).getItem()).getBlock().getDefaultState();
			
			return Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			
		}
	};

}
