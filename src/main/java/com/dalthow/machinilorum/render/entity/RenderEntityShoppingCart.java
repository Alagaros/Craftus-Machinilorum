package com.dalthow.machinilorum.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.entity.EntityShoppingCart;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class RenderEntityShoppingCart extends Render 
{
	// Declaration of the model files.
	private IModelCustom model;

	private final ResourceLocation texture = new ResourceLocation(Reference.modId, "textures/models/shopping cart.png");
	 
	
	// Constructor.
	public RenderEntityShoppingCart()
	{
		model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.modId, "models/shopping cart.obj"));
	}
	
	
	// Renders the shopping cart model on the entity.
	public void doRender(EntityShoppingCart entity, double par1, double par2, double par3, float par4, float par5)
	{
        GL11.glPushMatrix();
       
		float scale = 0.55F;

        float timeSinceLastHit = (float)entity.getTimeSinceHit() - par5;
        float damageTaken = entity.getDamageTaken() - par5;

		GL11.glTranslated(par1, par2 + 0.44F, par3 + 0.4);
        GL11.glScalef(scale, scale, scale);
        
        if(damageTaken < 0.0F)
        {
        	damageTaken = 0.0F;
        }
        
        if(timeSinceLastHit > 0.0F)
        {
        	GL11.glRotatef(MathHelper.sin(timeSinceLastHit) * timeSinceLastHit *  damageTaken / 10.0F * 1, 0.0F, 0.0F, 1.0F);
        }
        
        if(entity.riddenByEntity != null)
        {
        	GL11.glTranslated(-par1, -par2, -par3 - 0.4);
            GL11.glRotatef(-entity.riddenByEntity.rotationYaw, 0, 1, 0);
            GL11.glTranslated(par1, par2, par3 + 0.2);
        }
        
        else
        {
        	GL11.glTranslated(0, 0, -1.4);
        }
                
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
       
        model.renderAll();
        
        GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double par1, double par2, double par3, float par4, float par5)
    {
		doRender((EntityShoppingCart)entity, par1, par2, par3, par4, par5);
    }
	
	// Returns the location of the image.
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return texture;
	}
}
