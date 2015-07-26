/**
* Craftus Machinilorum
*
* 
* @Author Dalthow Game Studios
* @Class RenderFilter.java 
* 
**/

package com.dalthow.machinilorum.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.model.ModelFilter;

public class RenderFilter implements IItemRenderer
{
	// Declaration
	
	private ModelFilter model;
	private ResourceLocation texture = new ResourceLocation(Reference.modId, "textures/models/filter.png");
	
	
	// Constructor
	
	public RenderFilter()
	{
		this.model = new ModelFilter();
	}
	
	
	// Tells the game when to render the model
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		switch(type) 
		{ 
			case EQUIPPED: return true; 
			case EQUIPPED_FIRST_PERSON: return true;
			
			default: return false; 
		} 
	}
	
	
	// Tells the game if it needs to use the render helper
	
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{		
		return false;
	}
	
	
	// Renders the actual model
	
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		switch(type) 
		{ 
			case EQUIPPED:  
			{ 
				GL11.glPushMatrix(); 
				
				Minecraft.getMinecraft().renderEngine.bindTexture(this.texture); 
				  
				float scale = 2.25F; 
				  
				GL11.glRotatef(140F, 1F, 0F, 0F); 
				GL11.glRotatef(-90F, 0F, 1F, 0F); 
				GL11.glRotatef(45F, 0F, 0F, 1F); 
				
				GL11.glTranslatef(-0.635F, -0.05F, -0.47F); 
				GL11.glScalef(scale, scale, scale); 
				
				model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F); 
				  
				GL11.glPopMatrix(); 
			} 
		
			break; 
		
			case EQUIPPED_FIRST_PERSON:  
			{ 
				GL11.glPushMatrix(); 
				
				Minecraft.getMinecraft().renderEngine.bindTexture(this.texture); 
				  
				float scale = 1.75F; 
				  
				GL11.glRotatef(150F, 1F, 0F, 0F); 
				GL11.glRotatef(-120F, 0F, 1F, 0F); 
				GL11.glRotatef(35F, 0F, 0F, 1F); 
				
				GL11.glTranslatef(-0.90F, 0.11F, -0.63F); 
				GL11.glScalef(scale, scale, scale); 
				
				model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F); 
				  
				GL11.glPopMatrix(); 
			} 
		
			break; 
			
			default: break; 
		} 
	}
}
