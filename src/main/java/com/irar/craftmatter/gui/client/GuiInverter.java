package com.irar.craftmatter.gui.client;

import com.irar.craftmatter.gui.container.ContainerBlueMaker;
import com.irar.craftmatter.gui.container.ContainerCondenser;
import com.irar.craftmatter.gui.container.ContainerInverter;
import com.irar.craftmatter.gui.container.ContainerPrinter;
import com.irar.craftmatter.tileentity.TileBlueMaker;
import com.irar.craftmatter.tileentity.TileCondenser;
import com.irar.craftmatter.tileentity.TileInverter;
import com.irar.craftmatter.tileentity.TilePrinter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiInverter extends GuiBase {

	public GuiInverter(InventoryPlayer playerInv, TileInverter te) {
	    super(playerInv, te, ContainerInverter.class, "antimatter_inverter");
	}
	
}