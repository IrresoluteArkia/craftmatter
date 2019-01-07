package com.irar.craftmatter.model;

import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.EnumFacing;

public class AntiModelCache implements IResourceManagerReloadListener{
	
	public static Map<IBlockState,Map<EnumFacing,ArrayList<BakedQuad>>> cached = Maps.newHashMap();
	public static Map<IBlockState,IBakedModel> items = Maps.newHashMap();
	
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		cached.clear();
		items.clear();
	}

}
