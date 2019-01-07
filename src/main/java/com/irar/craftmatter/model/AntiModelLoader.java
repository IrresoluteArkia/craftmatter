package com.irar.craftmatter.model;

import com.irar.craftmatter.Ref;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class AntiModelLoader implements ICustomModelLoader{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return modelLocation.getResourceDomain().equals(Ref.MODID) && modelLocation.getResourcePath().contains("anti_block");
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return new AntiModel();
	}

}
