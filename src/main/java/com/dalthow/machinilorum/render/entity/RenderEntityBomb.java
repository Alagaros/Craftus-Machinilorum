package com.dalthow.machinilorum.render.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class RenderEntityBomb.java
 * 
 **/

@SideOnly(Side.CLIENT)
public class RenderEntityBomb extends Render
{
	// Declaration of the bomb item and metadata.
	
    private Item item;
    
    private int metadata;
  
    
    // Constructor that sets the declared variables.
    
    public RenderEntityBomb(Item item, int metadata)
    {
        this.item = item;
        this.metadata = metadata;
    }

    public RenderEntityBomb(Item Item)
    {
        this(Item, 0);
    }

    
    // Draws the bomb icon on the entity.
    
    public void doRender(Entity entity, double par1, double par2, double par3, float par4, float par5)
    {
        IIcon icon = item.getIconFromDamage(metadata);

        if(icon != null)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par1, (float)par2, (float)par3);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
           
            bindEntityTexture(entity);
          
            Tessellator Tess = Tessellator.instance;

            doTurn(Tess, icon);
            
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }


    /**
     * getEntityTexture Gets the texture of a certain entity.
     * 
     * @param  {Entity} entity The entity.
     * 
     * @return {ResourceLocation} The texture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TextureMap.locationItemsTexture;
    }
    
    
    /**
     * doTurn Turns the icon based on the point of view.
     * 
     * @param  {Tessellator} tessellator The tessellator.
     * @param  {IIcon} The icon.
     * 
     * @return {void}
     */
    private void doTurn(Tessellator tessellator, IIcon icon)
    {
        float par1 = icon.getMinU();
        float par2 = icon.getMaxU();
        float par3 = icon.getMinV();
        float par4 = icon.getMaxV();
        float par5 = 1.0F;
        float par6 = 0.5F;
        float par7 = 0.25F;
        
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double)(0.0F - par6), (double)(0.0F - par7), 0.0D, (double)par1, (double)par4);
        tessellator.addVertexWithUV((double)(par5 - par6), (double)(0.0F - par7), 0.0D, (double)par2, (double)par4);
        tessellator.addVertexWithUV((double)(par5 - par6), (double)(par5 - par7), 0.0D, (double)par2, (double)par3);
        tessellator.addVertexWithUV((double)(0.0F - par6), (double)(par5 - par7), 0.0D, (double)par1, (double)par3);
        tessellator.draw();
    }
}