package com.dalthow.machinilorum.render.block;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

@SideOnly(Side.CLIENT)
public class RenderMobRadar extends TileEntitySpecialRenderer
{
	// Declaration of the models files.
    private IModelCustom top;
    private IModelCustom bottom;

    private final ResourceLocation texture = new ResourceLocation(Reference.modId, "textures/models/mob radar.png");

    // Constructor.
    public RenderMobRadar()
    {
    	top = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/mobradar/top.obj"));
    	bottom = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/mobradar/bottom.obj"));
    }

    // Renders the TileEntity at a specific location.
	@Override
	public void renderTileEntityAt(TileEntity tile, double xPos, double yPos, double zPos, float par1)
    {
		if(!(tile instanceof TileEntityMobRadar)) return;
		
		TileEntityMobRadar tileEntityMobRadar = (TileEntityMobRadar) tile;
		
		GL11.glPushMatrix();
       
        GL11.glTranslated(xPos + 0.5, yPos, zPos + 0.5);
             
        float scale = 0.5F;
             
        GL11.glScalef(scale, scale, scale);
        
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
        
        bottom.renderAll();
        
        GL11.glRotated(tileEntityMobRadar.rotationAngle, 0, 1, 0);
           
        top.renderAll();
        
        GL11.glPopMatrix();
    }  
}
