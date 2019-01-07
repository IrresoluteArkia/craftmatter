package com.irar.craftmatter.gui.client;

import com.irar.craftmatter.gui.container.ContainerBlueMaker;
import com.irar.craftmatter.gui.container.ContainerMatterCrafter;
import com.irar.craftmatter.tileentity.TileBlueMaker;
import com.irar.craftmatter.tileentity.TileMatterCrafter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiMatterCrafter extends GuiBase {

	public GuiMatterCrafter(InventoryPlayer playerInv, TileMatterCrafter te) {
	    super(playerInv, te, ContainerMatterCrafter.class, "matter_crafter");
	    this.textColor = 16777215;
	}   
}