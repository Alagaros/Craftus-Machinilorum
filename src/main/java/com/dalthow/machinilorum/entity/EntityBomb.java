package com.dalthow.machinilorum.entity; 

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
* Craftus Machinilorum
*
* 
* @author Dalthow Game Studios 
* @class EntityBomb.java
* 
**/

public class EntityBomb extends EntityThrowable 
{ 
	// Declarations for the fuse time and explosion radius.
	
	private int fuse; 
	
	public static float explosionRadius;
	
	
	// Constructors, the first one adds data to the entity.
	
	public EntityBomb(World world) 
	{ 
		super(world); 
		
		setSize(0.5F, 0.5F); 
		yOffset = (height / -0.6F); 
		fuse = 0; 
		explosionRadius = 2.5F;
	} 
	
	public EntityBomb(World world, EntityPlayer player) 
	{ 
		super(world, player); 
	} 
	
	public EntityBomb(World world, float xPos, float yPos, float zPos) 
	{ 
		super(world, xPos, yPos, zPos); 
	} 
	
	
	// Setting up how the bomb acts when its on ground or in the air.
	
	public void onUpdate() 
	{ 
		prevPosX = posX; 
		prevPosY = posY; 
		prevPosZ = posZ; 
		motionY -= 0.03999999910593033D; 
		
		moveEntity(motionX, motionY, motionZ); 
		
		motionX *= 0.9800000190734863F; 
		motionY *= 0.9800000190734863F; 
		motionZ *= 0.9800000190734863F; 
		
		if(onGround) 
		{ 
			motionX *= 0.699999988079071F; 
			motionZ *= 0.699999988079071F; 
			motionY *= -0.5F; 
			
		} 
		
		if(fuse++ >= 50) 
		{ 
			setDead(); 
			
			if (!worldObj.isRemote) 
			{ 
				explode(); 
			} 
		} 
		
		else
		{ 
			worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D); 
		} 
	} 
	
	
	// Do the explosion.
	
	private void explode() 
	{ 
		worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, explosionRadius, true); 
	} 
	
	
	// Writing the bomb fuse time to the world file.
	
	public void writeEntityToNBT(NBTTagCompound tag) 
	{ 
		super.writeEntityToNBT(tag); 
		tag.setByte("fuse", (byte)fuse); 
	} 
	
	
	// Reading the bomb fuse time from the world file.
	
	public void readEntityFromNBT(NBTTagCompound tag) 
	{ 
		super.readEntityFromNBT(tag); 
		fuse = tag.getByte("fuse"); 
	} 
	
	
	// If the bomb hits a other entity.
	
	public void onCollideWithEntity(EntityBomb entity) 
	{ 
		entity.explode(); 
	} 
		
	@Override
	protected void onImpact(MovingObjectPosition movingPosition) 
	{
		
	} 
}