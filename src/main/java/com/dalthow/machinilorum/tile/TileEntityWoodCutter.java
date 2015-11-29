package com.dalthow.machinilorum.tile;

import java.util.List;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class TileEntityWoodCutter extends TileEntity
{
	private int xTarget;
	private int yTarget;
	private int zTarget;
	
	public int tickToCut;

	public int rotationAngle;
	
	public boolean hasCutted;

	
	// Constructor.
	public TileEntityWoodCutter()
	{
		tickToCut = 36;
		rotationAngle = 0;
		
		hasCutted = false;
	}


	@Override
	public void validate()
	{
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, Main.blockWoodCutter, 0, 0);
	}

    public void updateEntity()  
    { 
    	if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
			if(rotationAngle < 360)
    		{
    			rotationAngle += 15;
    		}
    		
    		else
    		{
    			rotationAngle = 0;
    		}
		}
    	
    	if(!worldObj.isRemote)
    	{
    		if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
    		{
    			setTarget(xCoord, yCoord, zCoord);

    			AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(xTarget, yTarget, zTarget, xTarget + 1, yTarget + 2, zTarget + 1); 
    			List <?> allEntitiesInAxis = getWorldObj().getEntitiesWithinAABB(Entity.class, axis);
    			
    			for(int i = 0; i < allEntitiesInAxis.size(); i++)
    		    {
    				Entity currentEntity = (Entity)allEntitiesInAxis.get(i);
    			   
    			    if(!(currentEntity instanceof EntityItem))
    			    {
    			    	currentEntity.attackEntityFrom(DamageSource.generic, 2F);
    			    }
    		    }
    			
    			if(tickToCut <= 0)
    			{
					worldObj.playSoundAtEntity(worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 25), "dig.wood", 1F, 1F);
					worldObj.getBlock(xTarget, yTarget, zTarget).dropBlockAsItemWithChance(worldObj, xTarget, yTarget, zTarget, worldObj.getBlockMetadata(xTarget, yTarget, zTarget), 1, 1);
					worldObj.setBlockToAir(xTarget, yTarget, zTarget);
				
    				tickToCut = 36;
    				
    				hasCutted = true;
    			
    			}
    			
    			if(tickToCut >= 0 && worldObj.getBlock(xTarget, yTarget, zTarget) != null && worldObj.getBlock(xTarget, yTarget, zTarget).getMaterial() == Material.wood)
    			{
    				tickToCut--;
    			}
    			
    			if(tickToCut == 35)
    			{
    				worldObj.playSoundAtEntity(worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 25), "minecart.base", 0.25F, 1F);
    			}
    		}
    		
    		else
    		{
    			tickToCut = 36;
    		}
    	}
    }

    public void readFromNBT(NBTTagCompound tag) 
    { 
        tickToCut = tag.getInteger("tickToCut");
        rotationAngle = tag.getInteger("rotationAngle");
       
        super.readFromNBT(tag);     
    } 

    public void writeToNBT(NBTTagCompound tag) 
    { 
    	tag.setInteger("tickToCut", tickToCut);
    	tag.setInteger("rotationAngle", rotationAngle);
    	
        super.writeToNBT(tag); 
    } 

	@Override
	public boolean canUpdate()
    {
        return true;
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


	/**
	 * Sets the target for the Wood Cutter.
	 *
	 * @param xTarget the targets x position.
	 * @param yTarget the targets y position.
	 * @param zTarget the targets z position.
	 */
	public void setTarget(int xTarget, int yTarget, int zTarget)
	{
		switch(getBlockMetadata())
		{
			case 0: this.xTarget = xTarget;
					this.yTarget = yTarget + 1;
					this.zTarget = zTarget;
			break;

			case 1: this.xTarget = xTarget;
					this.yTarget = yTarget - 1;
					this.zTarget = zTarget;
			break;

			case 2: this.xTarget = xTarget - 1;
					this.yTarget = yTarget;
					this.zTarget = zTarget;
			break;

			case 3: this.xTarget = xTarget + 1;
					this.yTarget = yTarget;
					this.zTarget = zTarget;
			break;

			case 4: this.xTarget = xTarget;
					this.yTarget = yTarget;
					this.zTarget = zTarget + 1;
			break;

			case 5: this.xTarget = xTarget;
					this.yTarget = yTarget;
					this.zTarget = zTarget - 1;
			break;
		}
	}
}
