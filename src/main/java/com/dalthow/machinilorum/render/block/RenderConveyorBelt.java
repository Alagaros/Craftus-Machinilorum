/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class RenderConveyorBelt.java
 * 
 **/

package com.dalthow.machinilorum.render.block;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.tile.TileEntityConveyorBelt;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConveyorBelt extends TileEntitySpecialRenderer
{
	// Declaration
	
    private IModelCustom model;

    private final ResourceLocation activeTexture = new ResourceLocation(Reference.modId, "textures/models/conveyor belt active.png");
    private final ResourceLocation offTexture = new ResourceLocation(Reference.modId, "textures/models/conveyor belt off.png");
    
    
    // Constructor
    
    public RenderConveyorBelt()
    {
    	model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/conveyor belt.obj"));
    }
    
    
    // Renders the tile at the entity

	@Override
	public void renderTileEntityAt(TileEntity tile, double xPos, double yPos, double zPos, float par1)
    {
        GL11.glPushMatrix();
       
        if(!(tile instanceof TileEntityConveyorBelt)) return;
		
		TileEntityConveyorBelt tileConveyorBelt = (TileEntityConveyorBelt) tile;
        
        switch(tileConveyorBelt.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord))
        {
	        case 0: GL11.glTranslated(xPos + 0.5, yPos - 0.025, zPos + 0.5); 
	        		
	        break;
	        
	        case 1: GL11.glTranslated(xPos + 0.5, yPos + 0.275, zPos + 0.5); 
	        		GL11.glRotatef(180, 1, 0, 0);
	        break;
	        		
	        case 2: GL11.glTranslated(xPos + 0.5, yPos - 0.025, zPos + 0.5); 
					GL11.glRotatef(90, 0, 1, 0);
			break;
	        	
	        case 3: GL11.glTranslated(xPos + 0.5, yPos - 0.025, zPos + 0.5); 
					GL11.glRotatef(270, 0, 1, 0);
			break;
        }
             
        float scale = 0.5F;
             
        GL11.glScalef(scale, scale, scale);
        
        if(tileConveyorBelt.isActive)
        {
        	FMLClientHandler.instance().getClient().getTextureManager().bindTexture(activeTexture);
        }
        
        else
        {
        	FMLClientHandler.instance().getClient().getTextureManager().bindTexture(offTexture);	
        }
        
        model.renderAll();
        
        GL11.glPopMatrix();
    }  
}
