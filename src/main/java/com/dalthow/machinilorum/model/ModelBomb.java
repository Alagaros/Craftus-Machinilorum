package com.dalthow.machinilorum.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class ModelBomb extends ModelBase
{
	// Declaration of the model parts.
	public ModelRenderer round1;
	public ModelRenderer round2;
	public ModelRenderer round3;
	public ModelRenderer round4;
	
	public ModelRenderer defuser;
	
	
	// Registering all the model parts.
	public ModelBomb()
	{
		textureWidth = 256;
		textureHeight = 256;
		
		round1 = new ModelRenderer(this, 0, 0);
		round1.addBox(0F, 0F, 0F, 3, 1, 3);
		round1.setRotationPoint(0F, -4F, 0F);
		round1.setTextureSize(256, 256);
		round1.mirror = true;
		
		setRotation(round1, 0F, 0F, 0F);
				
		round2 = new ModelRenderer(this, 25, 0);
		round2.addBox(0F, 0F, 0F, 5, 3, 3);
		round2.setRotationPoint(-1F, -3F, 0F);
		round2.setTextureSize(256, 256);
		round2.mirror = true;
		
		setRotation(round2, 0F, 0F, 0F);
		
		round3 = new ModelRenderer(this, 50, 0);
		round3.addBox(0F, 0F, 0F, 3, 3, 5);
		round3.setRotationPoint(0F, -3F, -1F);
		round3.setTextureSize(256, 256);
		round3.mirror = true;
		
		setRotation(round3, 0F, 0F, 0F);
		
		round4 = new ModelRenderer(this, 100, 0);
		round4.addBox(0F, 0F, 0F, 3, 1, 3);
		round4.setRotationPoint(0F, 0F, 0F);
		round4.setTextureSize(256, 256);
		round4.mirror = true;
		
		setRotation(round4, 0F, 0F, 0F);
		
		defuser = new ModelRenderer(this, 75, 0);
		defuser.addBox(0F, 0F, 0F, 1, 1, 1);
		defuser.setRotationPoint(1F, -5F, 1F);
		defuser.setTextureSize(256, 256);
		defuser.mirror = true;
		
		setRotation(defuser, 0F, 0F, 0F);
	}


	// Renders the model.
	public void render(Entity entity, float par1, float par2, float par3, float par4, float par5, float par6)
	{
		super.render(entity, par1, par2, par3, par4, par5, par6);
		setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
		
		round1.render(par6);
		round2.render(par6);
		round3.render(par6);
		round4.render(par6);
		
		defuser.render(par6);
	}
	
	
	/**
     * Sets the rotation of the model.
     * 
     * @param renderer The model renderer.
     * @param par1     X axis rotation.
     * @param par2 	   Y axis rotation.
     * @param par3     Z axis rotation.
     */
	private void setRotation(ModelRenderer renderer, float par1, float par2, float par3)
	{
		renderer.rotateAngleX = par1;
		renderer.rotateAngleY = par2;
		renderer.rotateAngleZ = par3;
	}


	/**
	 * Sets the rotation angles of the model.
	 *
	 * @param par1
	 * @param par2
	 * @param par3
	 * @param par4
	 * @param par5
	 * @param par6
     * @param entity
     */
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
    	super.setRotationAngles(par6, par1, par2, par3, par4, par5, entity);
    }
}
