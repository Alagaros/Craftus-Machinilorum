/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class PlayerModelOverride.java
 * 
 **/

package com.dalthow.machinilorum.render.player;

import com.dalthow.machinilorum.entity.EntityShoppingCart;

import net.minecraft.entity.Entity;
import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;

public class PlayerModelOverride extends ModelPlayerBase
{
	// Constructor
	
	public PlayerModelOverride(ModelPlayerAPI modelPlayerAPI)
	{
		super(modelPlayerAPI);
	}
	  
	
	// Gets triggered after the player model's initial rotations are set
	
	public void afterSetRotationAngles(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Entity paramEntity)
	{
		if((paramEntity.ridingEntity != null) && ((paramEntity.ridingEntity instanceof EntityShoppingCart)))
		{
			EntityShoppingCart cart = (EntityShoppingCart) paramEntity.ridingEntity;
			
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