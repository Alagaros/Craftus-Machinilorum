package com.dalthow.machinilorum.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.container.ContainerStoneCutter;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class GuiStoneCutter extends GuiContainer 
{
	// Declaration of the elements used in the gui.
	private final ResourceLocation background = new ResourceLocation(Reference.modId + ":" + "textures/gui/stone cutter.png");

	
	// Constructor that adds data to the gui.
	public GuiStoneCutter(InventoryPlayer inventoryPlayer, World world, int xPos, int yPos, int zPos)
	{
		super(new ContainerStoneCutter(inventoryPlayer, world, xPos, yPos, zPos));

		xSize = 256;
		ySize = 184;
	}

	
	// Gets called when the gui gets closed.
	public void onGuiClosed() 
	{
		super.onGuiClosed();
	}

	// Everything in front of the slots.
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRendererObj.drawString(I18n.format("container.stoneCutter", new Object[0]), xSize / 2 - 24, 7, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), xSize / 2 - 10, ySize - 96 + 2, 4210752);
		
		buttonList.clear();
		
		buttonList.add(new GuiButton(0, width / 2 + 51, height / 2 - 79, 71, 20, I18n.format("Close", new Object[0])));	
	}
	
	// Everything behind the slots.
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(background);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	// Executes something after a button press.
	protected void actionPerformed(GuiButton guiButton)
    {
		if (guiButton.id == 0)
        {
        	mc.thePlayer.closeScreen();
        }
    }
}