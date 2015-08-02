package com.dalthow.machinilorum.render.player;

import com.dalthow.machinilorum.entity.EntityShoppingCart;

import net.minecraft.entity.Entity;
import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class PlayerModelOverride.java
 * 
 **/

public class PlayerModelOverride extends ModelPlayerBase
{
	// Constructor.
	
	public PlayerModelOverride(ModelPlayerAPI modelPlayerAPI)
	{
		super(modelPlayerAPI);
	}
	  
	
	// Gets triggered after the player model's initial rotations are set.
	
	public void afterSetRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		if((entity.ridingEntity != null) && ((entity.ridingEntity instanceof EntityShoppingCart)))
		{
			EntityShoppingCart cart = (EntityShoppingCart) entity.ridingEntity;
			
			modelPlayer.bipedLeftLeg.rotateAngleY = -0.1F;
			modelPlayer.bipedRightLeg.rotateAngleY = 0.1F;
			
			modelPlayer.bipedLeftLeg.rotateAngleX = -1.57F;
			modelPlayer.bipedRightLeg.rotateAngleX = -1.57F;
			
			if(cart.isMoving())
			{
				modelPlayer.bipedLeftArm.rotateAngleX = -2.2F;
				modelPlayer.bipedRightArm.rotateAngleX = -2.2F;
				
				modelPlayer.bipedLeftArm.rotateAngleY = -0.2F;
				modelPlayer.bipedRightArm.rotateAngleY = 0.2F;
			}
			
			else
			{
				modelPlayer.bipedLeftArm.offsetY = -0.1F;
				modelPlayer.bipedLeftArm.rotateAngleX = -1.45F;
				
				modelPlayer.bipedRightArm.offsetY = -0.1F;
				modelPlayer.bipedRightArm.rotateAngleX = -1.45F;
			}
		}
		    
		else
		{
			modelPlayer.bipedLeftLeg.rotateAngleZ = 0.0F;
			modelPlayer.bipedRightLeg.rotateAngleZ = 0.0F;
			
			modelPlayer.bipedLeftArm.offsetY = 0F;
			modelPlayer.bipedRightArm.offsetY = 0F;
		}
	}
}