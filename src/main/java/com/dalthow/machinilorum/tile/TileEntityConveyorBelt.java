/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class TileEntityConveyorBelt.java
 * 
 **/

package com.dalthow.machinilorum.tile;

import java.util.List;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityConveyorBelt extends TileEntity
{
	// Declaration
	
	public boolean isActive;
    
	
	// Constructor
	
	public TileEntityConveyorBelt()
	{
		
	}


	// Validates the tile entity
	
	@Override
	public void validate()
	{
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, Main.blockEggIncubator, 0, 0);
	}
	
	
	// Gets triggered 20 times every second
	
    public void updateEntity()  
    { 
		if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
	    	isActive = true;
	    		
	    	AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1); 
			List <?> allEntitiesInAxis = getWorldObj().getEntitiesWithinAABB(Entity.class, axis);
			
			for(int i = 0; i < allEntitiesInAxis.size(); i++)
		    {
				Entity currentEntity = (Entity)allEntitiesInAxis.get(i);
			   
			    if((currentEntity instanceof Entity))
			    {
			    	switch(getBlockMetadata())
			    	{
				    	case 0: currentEntity.addVelocity(0F, 0F, -0.1F);
				    			
				    	break;
				
				    	case 1: currentEntity.addVelocity(0F, 0F, 0.1F);
								
				    	break;
			    	
			    		case 2: currentEntity.addVelocity(-0.1F, 0F, 0F);
			    				
			    		break;
			    		
			    		case 3: currentEntity.addVelocity(0.1F, 0F, 0F);
			    				
			    		break;
			    	}
			    }
		    }
		}
		
		else
    	{
    		isActive = false;
    	}
    } 
    
    
	// Tells the game that the tile can update
	
	@Override
	public boolean canUpdate()
    {
        return true;
    }
}
