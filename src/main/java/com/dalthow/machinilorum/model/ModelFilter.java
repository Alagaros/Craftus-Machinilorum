package com.dalthow.machinilorum.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class ModelFilter.java
 * 
 **/

public class ModelFilter extends ModelBase
{
	// Declaration of the model parts.
	
	public ModelRenderer handle;
	
	public ModelRenderer circle1;
	public ModelRenderer circle2;
	public ModelRenderer circle3;
	public ModelRenderer circle4;
	public ModelRenderer circle5;
	public ModelRenderer circle6;
	public ModelRenderer circle7;
	public ModelRenderer circle8;
	
	public ModelRenderer filter2;
	public ModelRenderer filter1;
	public ModelRenderer filter3;
	public ModelRenderer filter4;
	
	public ModelRenderer bottom;
	
	
	// Registering all the model parts.
	
	public ModelFilter()
	{
		textureWidth = 256;
		textureHeight = 256;
		
		handle = new ModelRenderer(this, 25, 0);
		handle.addBox(0F, 0F, 0F, 5, 1, 1);
		handle.setRotationPoint(-1F, 0F, 0F);
		handle.setTextureSize(256, 256);
		handle.mirror = true;
		
		setRotation(handle, 0F, 0F, -0.2974289F);
		
		circle1 = new ModelRenderer(this, 50, 0);
		circle1.addBox(-1F, 0F, -1F, 1, 1, 3);
		circle1.setRotationPoint(0F, 0F, 0F);
		circle1.setTextureSize(256, 256);
		circle1.mirror = true;
		
		setRotation(circle1, 0F, 0F, 0F);
		
		circle2 = new ModelRenderer(this, 75, 0);
		circle2.addBox(-2F, 0F, 0F, 1, 1, 1);
		circle2.setRotationPoint(0F, 0F, 2F);
		circle2.setTextureSize(256, 256);
		circle2.mirror = true;
		
		setRotation(circle2, 0F, 0F, 0F);
		
		circle3 = new ModelRenderer(this, 100, 0);
		circle3.addBox(0F, 0F, 0F, 1, 1, 1);
		circle3.setRotationPoint(-2F, 0F, -2F);
		circle3.setTextureSize(256, 256);
		circle3.mirror = true;
		
		setRotation(circle3, 0F, 0F, 0F);
		
		circle4 = new ModelRenderer(this, 125, 0);
		circle4.addBox(0F, 0F, 0F, 3, 1, 1);
		circle4.setRotationPoint(-5F, 0F, 3F);
		circle4.setTextureSize(256, 256);
		circle4.mirror = true;
		
		setRotation(circle4, 0F, 0F, 0F);
		
		circle5 = new ModelRenderer(this, 150, 0);
		circle5.addBox(0F, 0F, -3F, 3, 1, 1);
		circle5.setRotationPoint(-5F, 0F, 0F);
		circle5.setTextureSize(256, 256);
		circle5.mirror = true;
		
		setRotation(circle5, 0F, 0F, 0F);
		
		circle6 = new ModelRenderer(this, 175, 0);
		circle6.addBox(0F, 0F, 2F, 1, 1, 1);
		circle6.setRotationPoint(-6F, 0F, 0F);
		circle6.setTextureSize(256, 256);
		circle6.mirror = true;
		
		setRotation(circle6, 0F, 0F, 0F);
		
		circle7 = new ModelRenderer(this, 200, 0);
		circle7.addBox(0F, 0F, 0F, 1, 1, 1);
		circle7.setRotationPoint(-6F, 0F, -2F);
		circle7.setTextureSize(256, 256);
		circle7.mirror = true;
		
		setRotation(circle7, 0F, 0F, 0F);
		
		circle8 = new ModelRenderer(this, 200, 3);
		circle8.addBox(0F, 0F, 0F, 1, 1, 3);
		circle8.setRotationPoint(-7F, 0F, -1F);
		circle8.setTextureSize(256, 256);
		circle8.mirror = true;
		
		setRotation(circle8, 0F, 0F, 0F);
		
		filter2 = new ModelRenderer(this, 50, 25);
		filter2.addBox(0F, 0F, 0F, 3, 1, 1);
		filter2.setRotationPoint(-5F, 1F, 2F);
		filter2.setTextureSize(256, 256);
		filter2.mirror = true;
		
		setRotation(filter2, 0F, 0F, 0F);
		
		filter1 = new ModelRenderer(this, 25, 25);
		filter1.addBox(0F, 0F, 0F, 3, 1, 1);
		filter1.setRotationPoint(-5F, 1F, -2F);
		filter1.setTextureSize(256, 256);
		filter1.mirror = true;
		
		setRotation(filter1, 0F, 0F, 0F);
		
		filter3 = new ModelRenderer(this, 75, 25);
		filter3.addBox(0F, 0F, 0F, 1, 1, 3);
		filter3.setRotationPoint(-6F, 1F, -1F);
		filter3.setTextureSize(256, 256);
		filter3.mirror = true;
		
		setRotation(filter3, 0F, 0F, 0F);
		
		filter4 = new ModelRenderer(this, 100, 25);
		filter4.addBox(0F, 0F, 0F, 1, 1, 3);
		filter4.setRotationPoint(-2F, 1F, -1F);
		filter4.setTextureSize(256, 256);
		filter4.mirror = true;
		
		setRotation(filter4, 0F, 0F, 0F);
		
		bottom = new ModelRenderer(this, 125, 25);
		bottom.addBox(0F, 0F, 0F, 3, 1, 3);
		bottom.setRotationPoint(-5F, 2F, -1F);
		bottom.setTextureSize(256, 256);
		bottom.mirror = true;
		
		setRotation(bottom, 0F, 0F, 0F);
	}
	
	
	// Renders the model.
	
	public void render(Entity entity, float par1, float par2, float par3, float par4, float par5, float par6)
	{
		super.render(entity, par1, par2, par3, par4, par5, par6);
		setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
		
		handle.render(par6);
		
		circle1.render(par6);
		circle2.render(par6);
		circle3.render(par6);
		circle4.render(par6);
		circle5.render(par6);
		circle6.render(par6);
		circle7.render(par6);
		circle8.render(par6);
		
		filter1.render(par6); 
		filter2.render(par6);
		filter3.render(par6);
		filter4.render(par6);
		
		bottom.render(par6);
	}
	
	
	// Sets the rotation of the model.
	
	private void setRotation(ModelRenderer Renderer, float par1, float par2, float par3)
	{
		Renderer.rotateAngleX = par1;
		Renderer.rotateAngleY = par2;
		Renderer.rotateAngleZ = par3;
	}
	
	
	// Sets the rotation angles of the model.
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.setRotationAngles(par6, par1, par2, par3, par4, par5, entity);
	}
}
