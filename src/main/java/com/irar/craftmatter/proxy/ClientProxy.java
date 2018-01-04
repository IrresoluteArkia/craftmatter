package com.irar.craftmatter.proxy;

import com.irar.craftmatter.handlers.BlockHandler;
import com.irar.craftmatter.handlers.ItemHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy{
	public void init(FMLInitializationEvent event){
		ItemHandler.registerRenders();
		BlockHandler.registerRenders();
	}
}
