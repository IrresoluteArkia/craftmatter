package com.irar.craftmatter.proxy;

import com.irar.craftmatter.handlers.BlockHandler;
import com.irar.craftmatter.handlers.ItemHandler;
import com.irar.craftmatter.model.AntiModelCache;
import com.irar.craftmatter.model.AntiModelLoader;
import com.irar.craftmatter.tileentity.TileAntiBlock;
import com.irar.craftmatter.tileentity.tesr.AntiBlockTESR;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new AntiModelCache());
		ModelLoaderRegistry.registerLoader(new AntiModelLoader());
	}
	
	public void init(FMLInitializationEvent event){
		super.init(event);
		ItemHandler.registerRenders();
		BlockHandler.registerRenders();
//		ClientRegistry.bindTileEntitySpecialRenderer(TileAntiBlock.class, new AntiBlockTESR());
	}
}
