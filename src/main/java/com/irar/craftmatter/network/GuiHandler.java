package com.irar.craftmatter.network;

import com.irar.craftmatter.gui.client.GuiBlueMaker;
import com.irar.craftmatter.gui.client.GuiCondenser;
import com.irar.craftmatter.gui.client.GuiInverter;
import com.irar.craftmatter.gui.client.GuiMatterCrafter;
import com.irar.craftmatter.gui.client.GuiPrinter;
import com.irar.craftmatter.gui.container.ContainerBlueMaker;
import com.irar.craftmatter.gui.container.ContainerCondenser;
import com.irar.craftmatter.gui.container.ContainerInverter;
import com.irar.craftmatter.gui.container.ContainerMatterCrafter;
import com.irar.craftmatter.gui.container.ContainerPrinter;
import com.irar.craftmatter.tileentity.TileBlueMaker;
import com.irar.craftmatter.tileentity.TileCondenser;
import com.irar.craftmatter.tileentity.TileInverter;
import com.irar.craftmatter.tileentity.TileMatterCrafter;
import com.irar.craftmatter.tileentity.TilePrinter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	public static final int TILE_BLUE_MAKER_GUI = 0;
	public static final int TILE_PRINTER_GUI = 1;
	public static final int TILE_CONDENSER_GUI = 2;
	public static final int TILE_INVERTER_GUI = 3;
	public static final int TILE_MATTER_CRAFTER_GUI = 4;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == this.TILE_BLUE_MAKER_GUI)
			return new ContainerBlueMaker(player.inventory, (TileBlueMaker) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_PRINTER_GUI)
			return new ContainerPrinter(player.inventory, (TilePrinter) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_CONDENSER_GUI)
			return new ContainerCondenser(player.inventory, (TileCondenser) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_INVERTER_GUI)
			return new ContainerInverter(player.inventory, (TileInverter) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_MATTER_CRAFTER_GUI)
			return new ContainerMatterCrafter(player.inventory, (TileMatterCrafter) world.getTileEntity(new BlockPos(x, y, z)));
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == this.TILE_BLUE_MAKER_GUI)
			return new GuiBlueMaker(player.inventory, (TileBlueMaker) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_PRINTER_GUI)
			return new GuiPrinter(player.inventory, (TilePrinter) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_CONDENSER_GUI)
			return new GuiCondenser(player.inventory, (TileCondenser) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_INVERTER_GUI)
			return new GuiInverter(player.inventory, (TileInverter) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == this.TILE_MATTER_CRAFTER_GUI)
			return new GuiMatterCrafter(player.inventory, (TileMatterCrafter) world.getTileEntity(new BlockPos(x, y, z)));

		return null;
	}

}
