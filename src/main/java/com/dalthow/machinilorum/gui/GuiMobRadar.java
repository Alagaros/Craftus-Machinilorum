package com.dalthow.machinilorum.gui;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.container.ContainerMobRadar;
import com.dalthow.machinilorum.packet.mobradar.PacketMobRadarId;
import com.dalthow.machinilorum.packet.mobradar.PacketMobRadarRadius;
import com.dalthow.machinilorum.packet.mobradar.PacketMobRadarSignal;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

/**
* Craftus Machinilorum
*
* 
* @author Dalthow Game Studios 
* @class GuiMobRadar.java
* 
**/

public class GuiMobRadar extends GuiContainer
{
	// Declaration of the TileEntity.
	
	private TileEntityMobRadar tile;
	
	
	// Declaration of the elements used in the gui.
	
	private GuiButton highSignal;
	private GuiButton lowSignal;
	private GuiButton close;
	private GuiButton next;
	private GuiButton previous;
	
	private GuiTextField radius;

	private final ResourceLocation background = new ResourceLocation(Reference.modId + ":" + "textures/gui/mob radar.png");
	
	
	// Declaring some other variables.
	
	private int localMobId;
	
	private boolean errorNoNummer = false;
	
	
	// Constructor that adds data to the gui.
	
	public GuiMobRadar(TileEntityMobRadar tile) 
	{
		super(new ContainerMobRadar(tile));

		this.tile = tile;
		
		xSize = 256;
		ySize = 133;
	}
	
	
	// Everything in front of the slots.
	
	@Override	
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRendererObj.drawString(I18n.format("container.mobRadar", new Object[0]), xSize / 2 - 78, 6, 4210752);
		
		buttonList.clear();
		
		highSignal = new GuiButton(3, guiLeft + 134, guiTop + 107, 30, 20, "High");
		lowSignal = new GuiButton(4, guiLeft + 168, guiTop + 107, 30, 20, "Low");
				
		next = new GuiButton(1, guiLeft + 73, guiTop + 18, 50, 20, "Next");
		previous = new GuiButton(2, guiLeft + 8, guiTop + 18, 60, 20, "Previous");
		
		close = new GuiButton(5, width / 2 - 120, height / 2 + 10, 30, 20, I18n.format("Set", new Object[0]));
		
		fontRendererObj.drawString("Radius:", guiLeft / width + 93, guiTop / height + 62, 4210752);
		
		if(errorNoNummer)
		{
			fontRendererObj.drawString("Please insert", guiLeft / width + 14, guiTop / height + 106, 4210752);
			fontRendererObj.drawString("a number.", guiLeft / width + 14, guiTop / height + 116, 4210752);
		}
		
		if(tile.signal == 0)
		{
			lowSignal.enabled = false;
		}
		
		if(tile.signal == 1)
		{
			highSignal.enabled = false;
		}
		
		if(localMobId == 0)
		{
			previous.enabled = false;
		}
		
		if(localMobId == 9)
		{
			next.enabled = false;
		}
		
		buttonList.add(new GuiButton(0, width / 2 + 51, height / 2 - 53, 71, 20, I18n.format("Close", new Object[0])));	
	    buttonList.add(next);
	    buttonList.add(previous);
	    buttonList.add(highSignal);
	    buttonList.add(lowSignal);
	    buttonList.add(close);	
	    
	    localMobId = tile.mobId;
	    
		radius.drawTextBox();
	}
	
	
	// Everything behind the slots.

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		switch(tile.mobId)
		{
			case 0: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 0, 133, 32, 32);
			
			break;
			
			case 1: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 32, 133, 32, 32);
			
			break;
			
			case 2: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 64, 133, 32, 32);
			
			break;
			
			case 3: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 96, 133, 32, 32);
			
			break;
			
			case 4: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 0, 165, 32, 32);
						
			break;
						
			case 5: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 32, 165, 32, 32);
			
			break;
			
			case 6: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 64, 165, 32, 32);
			
			break;
			
			case 7: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 96, 165, 32, 32);
			
			break;
			
			case 8: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 128, 165, 32, 32);
			
			break;
			
			case 9: drawTexturedModalRect(guiLeft + 129, guiTop + 19, 160, 165, 32, 32);
			
			break;
		}
		
		switch(tile.signal)
		{
			case 0: drawTexturedModalRect(guiLeft + 185, guiTop + 71, 8, 197, 8, 22);
				
			break;
			
			case 1: drawTexturedModalRect(guiLeft + 185, guiTop + 71, 0, 197, 8, 22);
			
			break;
		}
		
		if(errorNoNummer == true)
		{
			drawTexturedModalRect(guiLeft + 8, guiTop + 104, 127, 108, 78, 26);
		}
	}
	
	
	// The first method that gets called once when the gui opens.

	public void initGui()
	{
		super.initGui();
		
		radius = new GuiTextField(fontRendererObj, guiLeft / width + 132, guiTop / height + 58, 26, 16);
		
		radius.setMaxStringLength(3);
		radius.setText(Integer.toString(tile.radius));

		radius.setFocused(true);
	}
	
	
	// Gets called when the player presses a key.
	
	protected void keyTyped(char par1, int par2)
	{
		radius.textboxKeyTyped(par1, par2);
	}
	
	
	// Executes something after a button press.
	
	protected void actionPerformed(GuiButton guiButton)
    {
		if(guiButton.id == 0)
        {
        	mc.thePlayer.closeScreen();
        }
		
		if(guiButton.id == 1)
        {
			if(tile.mobId < 9)
			{
				localMobId++;
				
				Main.packetPipeline.sendToServer(new PacketMobRadarId(tile.xCoord, tile.yCoord, tile.zCoord, localMobId));
			}
        }
		
		if(guiButton.id == 2)
        {
			if(tile.mobId > 0)
			{
				localMobId--;
				
				Main.packetPipeline.sendToServer(new PacketMobRadarId(tile.xCoord, tile.yCoord, tile.zCoord, localMobId));
			}
        }
		
		if(guiButton.id == 3)
		{
			Main.packetPipeline.sendToServer(new PacketMobRadarSignal(tile.xCoord, tile.yCoord, tile.zCoord, 1));
		}
		
		if(guiButton.id == 4)
		{
			Main.packetPipeline.sendToServer(new PacketMobRadarSignal(tile.xCoord, tile.yCoord, tile.zCoord, 0));
		}
		
		if(guiButton.id == 5)
		{
			int localRadius;
			
			try
			{
				localRadius = Integer.parseInt(radius.getText());
				
				Main.packetPipeline.sendToServer(new PacketMobRadarRadius(tile.xCoord, tile.yCoord, tile.zCoord, localRadius));
			}
			
			catch(Exception error)
			{
				errorNoNummer = true;
			}
		}	
    }
}