package com.irar.craftmatter.gui.client;

import com.irar.craftmatter.gui.container.ContainerBlueMaker;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBlueMaker extends GuiBase {

	public GuiBlueMaker(InventoryPlayer playerInv, TileBlueMaker te) {
	    super(playerInv, te, ContainerBlueMaker.class, "blue_maker");
	}   
}