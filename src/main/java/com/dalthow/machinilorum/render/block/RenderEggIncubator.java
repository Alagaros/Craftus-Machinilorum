package com.dalthow.machinilorum.render.block;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.tile.TileEntityEggIncubator;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

 /**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

@SideOnly(Side.CLIENT)
public class RenderEggIncubator extends TileEntitySpecialRenderer
{
	// Declaration of the models files.
    private IModelCustom model;

    private final ResourceLocation textures = new ResourceLocation(Reference.modId, "textures/models/egg incubator.png");

    // Constructor.
    public RenderEggIncubator()
    {
    	model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/egg incubator.obj"));
    }

    // Renders the TileEntity at a specific location.
	@Override
	public void renderTileEntityAt(TileEntity tile, double xPos, double yPos, double zPos, float par1)
    {
		if(!(tile instanceof TileEntityEggIncubator)) return;
			
		TileEntityEggIncubator tileEggIncubator = (TileEntityEggIncubator) tile;
		
        GL11.glPushMatrix();
       
        switch(tileEggIncubator.getBlockMetadata())
        {
	        case 0: GL11.glTranslated(xPos + 0.5, yPos, zPos + 0.5); 
	        		
	        break;
	        
	        case 1: GL11.glTranslated(xPos + 0.5, yPos + 1, zPos + 0.5); 
	        		GL11.glRotatef(180, 0, 0, 1);
	        break;
	        		
	        case 2: GL11.glTranslated(xPos + 1, yPos + 0.5, zPos + 0.5); 
					GL11.glRotatef(90, 0, 0, 1);
			break;
	        	
	        case 3: GL11.glTranslated(xPos, yPos + 0.5, zPos + 0.5); 
					GL11.glRotatef(270, 0, 0, 1);
			break;
					
	        case 4: GL11.glTranslated(xPos + 0.5, yPos + 0.5, zPos); 
					GL11.glRotatef(90, 1, 0, 0);
			break;
					
	        case 5: GL11.glTranslated(xPos + 0.5, yPos + 0.5, zPos + 1); 
					GL11.glRotatef(270, 1, 0, 0);
			break;
        }
        
        float scale = 0.5F;
              
        GL11.glScalef(scale, scale, scale);
        
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(textures);
       
        model.renderAll();
        
        GL11.glPopMatrix();
    }  
}