package com.dalthow.machinilorum.tile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class TileEntityMobRadar extends TileEntity 
{
	public int mobId;
	public int radius;
	public int signal;
	
	public int signalStrength;
	
	public int rotationAngle;
	
	// Constructor.
	public TileEntityMobRadar()
	{
		mobId = 0;
		radius = 5;
		signal = 1;
		
		signalStrength = 0;
		
		rotationAngle = 0;
	}

	public void updateEntity() 
	{
		AxisAlignedBB axis = AxisAlignedBB.getBoundingBox((xCoord - radius), (yCoord - 3), (zCoord - radius), (xCoord + 1 + radius), (yCoord + 4), (zCoord + 1 + radius)); 

		if(rotationAngle < 360)
			rotationAngle++;
		
		else
			rotationAngle = 0;
		
		switch(mobId)
		{
			case 0: List <?> cowEntities = getWorldObj().getEntitiesWithinAABB(EntityCow.class, axis);
			
					if(signal == 0)
						this.signalStrength = 15 - cowEntities.size();
					else
						this.signalStrength = cowEntities.size();
			break;
		
			case 1:	List <?> pigEntities = getWorldObj().getEntitiesWithinAABB(EntityPig.class, axis);
					
					if(signal == 0)
						this.signalStrength = 15 - pigEntities.size();
					else
						this.signalStrength = pigEntities.size();
			break;
		
			case 2: List <?> sheepEntities = getWorldObj().getEntitiesWithinAABB(EntitySheep.class, axis);
		
					if(signal == 0)
						this.signalStrength = 15 - sheepEntities.size();
					else
						this.signalStrength = sheepEntities.size();
		
			break;
	
			case 3: List <?> playerEntities = getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, axis);
			
					if(signal == 0)
						this.signalStrength = 15 - playerEntities.size();
					else
						this.signalStrength = playerEntities.size();
			break;
			
			case 4: List <?> zombieEntities = getWorldObj().getEntitiesWithinAABB(EntityZombie.class, axis);
					
					if(signal == 0)
						this.signalStrength = 15 - zombieEntities.size();
					else
						this.signalStrength = zombieEntities.size();
			break;
			
			case 5: List <?> creeperEntities = getWorldObj().getEntitiesWithinAABB(EntityCreeper.class, axis);
			
					if(signal == 0)
						this.signalStrength = 15 - creeperEntities.size();
					else
						this.signalStrength = creeperEntities.size();
			break;
			
			case 6: List <?> spiderEntities = getWorldObj().getEntitiesWithinAABB(EntitySpider.class, axis);
			
					if(signal == 0)
						this.signalStrength = 15 - spiderEntities.size();
					else
						this.signalStrength = spiderEntities.size();
			break;
			
			case 7: List <?> skeletonEntities = getWorldObj().getEntitiesWithinAABB(EntitySkeleton.class, axis);
			
					if(signal == 0)
						this.signalStrength = 15 - skeletonEntities.size();
					else
						this.signalStrength = skeletonEntities.size();
			break;
			
			case 8: List <?> blazeEntities = getWorldObj().getEntitiesWithinAABB(EntityBlaze.class, axis);
			
					if(signal == 0)
						this.signalStrength = 15 - blazeEntities.size();
					else
						this.signalStrength = blazeEntities.size();
			break;
			
			case 9: List <?> endermanEntities = getWorldObj().getEntitiesWithinAABB(EntityEnderman.class, axis);
			
					if(signal == 0)
						this.signalStrength = 15 - endermanEntities.size();
					
					else
						this.signalStrength = endermanEntities.size();
			break;
		}		
		
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
	}

    public void readFromNBT(NBTTagCompound tag) 
    { 
    	mobId = tag.getInteger("mobId"); 
    	signal = tag.getInteger("signal");
    	radius = tag.getInteger("radius");
        rotationAngle = tag.getInteger("rotationAngle");
    	
        super.readFromNBT(tag);     
    } 

    public void writeToNBT(NBTTagCompound tag) 
    { 
    	tag.setInteger("mobId", mobId); 
    	tag.setInteger("signal", signal);
    	tag.setInteger("radius", radius);
    	tag.setInteger("rotationAngle", rotationAngle);
    	
        super.writeToNBT(tag); 
    } 

 	@Override
 	public boolean canUpdate()
    {
         return true;
    }

 	@Override
 	public Block getBlockType()
 	{
		return Blocks.beacon;
 	}

 	@Override
 	public Packet getDescriptionPacket() 
 	{
         NBTTagCompound tag = new NBTTagCompound();

         writeToNBT(tag);

         return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
     }
 	
 	@Override
 	public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet)
    {	
 		this.readFromNBT(packet.func_148857_g());
    }
}
