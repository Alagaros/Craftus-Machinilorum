package com.dalthow.machinilorum.render.block;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.tile.TileEntitySprinkler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

@SideOnly(Side.CLIENT)
public class RenderSprinkler extends TileEntitySpecialRenderer
{
	// Declaration of the model files.
    private IModelCustom engine;
    private IModelCustom top;

    private final ResourceLocation texture = new ResourceLocation(Reference.modId, "textures/models/sprinkler.png");
   
    
    // Constructor.
    public RenderSprinkler()
    {
    	top = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/sprinkler/top.obj"));
    	engine = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/sprinkler/engine.obj"));
    }
    
    
    // Renders the TileEntity at a specific location.
	@Override
	public void renderTileEntityAt(TileEntity tile, double xPos, double yPos, double zPos, float par1)
    {
		if(!(tile instanceof TileEntitySprinkler)) 
		{
			return;
		}
		
		TileEntitySprinkler tileEntitySprinkler = (TileEntitySprinkler) tile;
		
        GL11.glPushMatrix();
       
        GL11.glTranslated(xPos + 0.5, yPos, zPos + 0.5); 
        
        float scale = 0.5F;
              
        GL11.glScalef(scale, scale, scale);
        
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
       
        engine.renderAll();

        switch(tileEntitySprinkler.getBlockMetadata())
        {
        	case 0:	GL11.glTranslated(0, 0, + 2);
            
        	break;
        	
        	case 1:	GL11.glRotated(270, 0, 1, 0);
        			GL11.glTranslated(-0.565, 0, + 2.565);
            break;
            	
        	case 2:	GL11.glRotated(180, 0, 1, 0);
					GL11.glTranslated(0, 0, + 3.125);
            break;
            	
        	case 3:	GL11.glRotated(90, 0, 1, 0);
					GL11.glTranslated(+0.565, 0, + 2.565);
            break;
        }
        
        top.renderAll();
        
        GL11.glPopMatrix();
    }  
}