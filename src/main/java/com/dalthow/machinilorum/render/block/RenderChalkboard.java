package com.dalthow.machinilorum.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.tile.TileEntityChalkboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios
 * @class RenderChalkboard.java 
 * 
 **/

@SideOnly(Side.CLIENT)
public class RenderChalkboard extends TileEntitySpecialRenderer
{
	// Declaration of the model files.
	
    private IModelCustom model;

    private final ResourceLocation textures = new ResourceLocation(Reference.modId, "textures/models/chalkboard.png");
 
    
    // Constructor that obtains the model.
    
    public RenderChalkboard()
    {
    	model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/chalkboard.obj"));
    }
    
    
    // Renders the TileEntity at a specific location.

	@Override
	public void renderTileEntityAt(TileEntity tile, double xPos, double yPos, double zPos, float par1)
    {
		if(!(tile instanceof TileEntityChalkboard)) return;
		
		TileEntityChalkboard tileChalkboard = (TileEntityChalkboard) tile;
		
        GL11.glPushMatrix();
        
        switch(tileChalkboard.getBlockMetadata())
        {
	        case 0: GL11.glTranslated(xPos + 0.06, yPos + 0.5, zPos + 0.5); 
	        		GL11.glRotatef(90, 0, 0, 1); 
	        		GL11.glRotatef(180, 1, 0, 0); 
	        break;
	        
	        case 1: GL11.glTranslated(xPos + 0.5, yPos + 0.5, zPos + 0.94); 
    				GL11.glRotatef(90, 0, 0, 1); 
    				GL11.glRotatef(90, 1, 0, 0); 
    				GL11.glRotatef(180, 0, 0, 1); 
    		break;
    		
	        case 2: GL11.glTranslated(xPos + 0.94, yPos + 0.5, zPos + 0.5); 
					GL11.glRotatef(90, 0, 0, 1); 
					GL11.glRotatef(180, 1, 0, 0); 
					GL11.glRotatef(180, 0, 0, 1);
			break;
			
	        case 3: GL11.glTranslated(xPos + 0.5, yPos + 0.5, zPos + 0.06); 
					GL11.glRotatef(90, 0, 0, 1); 
					GL11.glRotatef(270, 1, 0, 0); 
					GL11.glRotatef(180, 0, 0, 1);
			break;
        }
        
        float scale = 0.5F;
              
        GL11.glScalef(scale, scale, scale);
        
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(textures);
               
        model.renderAll();

        GL11.glPopMatrix();

        GL11.glPushMatrix();
        
        if(tileChalkboard.startDefined == false)
        {
        	Tessellator tessellator = Tessellator.instance;
	                	
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glColor4d(1, 1, 1, 1);
	        
	        tessellator.startDrawing(2); 
	        
	        switch(tileChalkboard.getBlockMetadata())
	        {
		        case 0: tessellator.addVertex(xPos + 0.06, yPos + tileChalkboard.startPosY, zPos + tileChalkboard.startPosZ);				        
				        tessellator.addVertex(xPos + 0.06, yPos + tileChalkboard.endPosY, zPos + tileChalkboard.endPosZ);
				break;
				
		        case 1: tessellator.addVertex(xPos + tileChalkboard.startPosX, yPos + tileChalkboard.startPosY, zPos +  0.94);
		        		tessellator.addVertex(xPos + tileChalkboard.endPosX, yPos + tileChalkboard.endPosY, zPos +  0.94);
			    break;
				
		        case 2: tessellator.addVertex(xPos + 0.94, yPos + tileChalkboard.startPosY, zPos + tileChalkboard.startPosZ);
		        		tessellator.addVertex(xPos + 0.94, yPos + tileChalkboard.endPosY, zPos + tileChalkboard.endPosZ);
		        break;
		        
		        case 3: tessellator.addVertex(xPos + tileChalkboard.startPosX, yPos + tileChalkboard.startPosY, zPos +  0.06);
		        		tessellator.addVertex(xPos + tileChalkboard.endPosX, yPos + tileChalkboard.endPosY, zPos +  0.06);
		        break;
	        }
	        
	        tessellator.draw();
	        
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glEnable(GL11.GL_LIGHTING);
        }
        
        GL11.glPopMatrix();
    }  
}