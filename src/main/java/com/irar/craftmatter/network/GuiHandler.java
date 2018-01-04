package com.irar.craftmatter.network;

import com.irar.craftmatter.gui.client.GuiBlueMaker;
import com.irar.craftmatter.gui.container.ContainerBlueMaker;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	public static final int TILE_ENTITY_ORE_GEN_GUI = 0;
	public static final int CATALYST_TIER_IRIDIUM = 1;
	public static final int CATALYST_TIER_BIRIDIUM = 2;
	public static final int CATALYST_TIER_TRIRIDIUM = 3;
	public static final int CATALYST_TIER_QUADRIDIUM = 4;
	public static final int CATALYST_TIER_QUINTRIDIUM = 5;
	public static final int CATALYST_TIER_SEXTRIDIUM = 6;
	public static final int CATALYST_TIER_SEPTRIDIUM = 7;
	public static final int CATALYST_TIER_OCTRIDIUM = 8;
	public static final int CATALYST_TIER_NONADIUM = 9;
	public static final int CATALYST_TIER_DECADRIUM = 10;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == this.TILE_ENTITY_ORE_GEN_GUI)
			return new ContainerBlueMaker(player.inventory, (TileBlueMaker) world.getTileEntity(new BlockPos(x, y, z)));
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == this.TILE_ENTITY_ORE_GEN_GUI)
			return new GuiBlueMaker(player.inventory, (TileBlueMaker) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

}
