package com.dalthow.machinilorum.tile;

import java.util.List;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class TileEntityConveyorBelt.java
 * 
 **/

public class TileEntityConveyorBelt extends TileEntity
{
	// Declaration of isActive flag.
	
	public boolean isActive;
    
	
	// Empty constructor.
	
	public TileEntityConveyorBelt()
	{
		
	}


	// Validates the tile entity.
	
	@Override
	public void validate()
	{
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, Main.blockEggIncubator, 0, 0);
	}
	
	
	// Gets triggered 20 times every second.
	
    public void updateEntity()  
    {
    	// Checks if a redstone signal powers the block, if so don't continue.
    	
		if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
	    	isActive = true;
	    		
	    	AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1); 
			List <?> allEntitiesInAxis = getWorldObj().getEntitiesWithinAABB(Entity.class, axis);
			
			
			// Getting all the entities on top of this block.
			
			for(int i = 0; i < allEntitiesInAxis.size(); i++)
		    {
				Entity currentEntity = (Entity)allEntitiesInAxis.get(i);
			   
			    if((currentEntity instanceof Entity))
			    {
			    	// Switches the position entities are send in based on the block's rotation.
			    	
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
