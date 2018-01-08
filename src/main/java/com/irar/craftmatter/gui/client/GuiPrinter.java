package com.irar.craftmatter.gui.client;

import com.irar.craftmatter.gui.container.ContainerBlueMaker;
import com.irar.craftmatter.gui.container.ContainerPrinter;
import com.irar.craftmatter.tileentity.TileBlueMaker;
import com.irar.craftmatter.tileentity.TilePrinter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiPrinter extends GuiBase {

	public GuiPrinter(InventoryPlayer playerInv, TilePrinter te) {
	    super(playerInv, te, ContainerPrinter.class, "antimatter_printer");
	}
	
}