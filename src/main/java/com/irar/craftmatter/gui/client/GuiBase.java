package com.irar.craftmatter.gui.client;

import java.lang.reflect.InvocationTargetException;

import com.irar.craftmatter.gui.container.ContainerBase;
import com.irar.craftmatter.gui.container.ContainerBlueMaker;
import com.irar.craftmatter.tileentity.TileBase;
import com.irar.craftmatter.tileentity.TileBlueMaker;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBase extends GuiContainer {

	protected IInventory playerInv;
	protected TileBase te;
	private String nameWithUnderscore;
//	private Class<? extends ContainerBase> containerClass = ContainerBase.class;

	public GuiBase(InventoryPlayer playerInv, TileBase te, Class<? extends ContainerBase> containerClass, String nameWithUnderscore) {
	    super(instantiateContainer(containerClass, playerInv, te));

	    this.playerInv = playerInv;
	    this.te = te;
	    this.nameWithUnderscore = nameWithUnderscore;

	    this.xSize = 176;
	    this.ySize = 166;
	}

    private static ContainerBase instantiateContainer(Class<? extends ContainerBase> containerClass, InventoryPlayer playerInv2, TileBase te2) {
    	try {
			return containerClass.getDeclaredConstructor(InventoryPlayer.class, TileBase.class).newInstance(playerInv2, te2);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("craftmatter:textures/gui/container/tile_" + this.nameWithUnderscore + ".png"));
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
        if(this.te.displayMatter) {
	        this.fontRenderer.drawString("Units Of " + matter() + ": " + this.te.getAmountMatter(), 8, 34, 0);
	        if(this.te.isValidRecipe()) {
	        	this.fontRenderer.drawString(matter() + " Needed: " + this.te.getMatterNeeded(), 8, 44, 0);
	        }
        }
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 0);
    }
    
    private String matter() {
    	if(this.te.storesMatter) {
    		return "Matter";
    	}else {
    		return "Antimatter";
    	}
    }
    
}