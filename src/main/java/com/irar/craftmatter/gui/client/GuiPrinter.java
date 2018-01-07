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

public class GuiPrinter extends GuiContainer {

	private IInventory playerInv;
	private TilePrinter te;

	public GuiPrinter(InventoryPlayer playerInv, TilePrinter te) {
	    super(new ContainerPrinter(playerInv, te));

	    this.playerInv = playerInv;
	    this.te = te;

	    this.xSize = 176;
	    this.ySize = 166;
	}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("craftmatter:textures/gui/container/tile_blue_maker.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 4, 0);
        this.fontRenderer.drawString("Units Of Antimatter: " + this.te.getAmountMatter(), 8, 34, 0);
        if(this.te.isValidRecipe()) {
        	this.fontRenderer.drawString("Matter Needed: " + this.te.getMatterNeeded(), 8, 44, 0);
        }
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 0);
    }
    
}