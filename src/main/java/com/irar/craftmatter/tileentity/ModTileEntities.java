package com.irar.craftmatter.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModTileEntities {
	
	public static void init(){
		GameRegistry.registerTileEntity(TileBlueMaker.class, "blue_maker_tile_entity");
		GameRegistry.registerTileEntity(TilePrinter.class, "anti_printer_tile_entity");
	}
}
