package com.irar.craftmatter.tileentity.tesr;

import org.apache.commons.lang3.tuple.Pair;

import com.irar.craftmatter.tileentity.TileAntiBlock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;

public class AntiBlockTESR extends FastTESR<TileAntiBlock>{
    protected static BlockRendererDispatcher blockRenderer;

	@Override
	public void renderTileEntityFast(TileAntiBlock tab, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        if(blockRenderer == null) blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
        BlockPos pos = tab.getPos();
        IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(tab.getWorld(), pos);
        IBlockState state = ((ItemBlock) tab.display.getItem()).getBlock().getDefaultState();
        IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(state);
//        buffer.setTranslation(x, y, z);
        buffer.addVertexData(new int[] {0, 0, 0, 1, 1, 1});
        blockRenderer.getBlockModelRenderer().renderModel(world, model, state, pos, buffer, true);
        buffer.endVertex();
	}

}
