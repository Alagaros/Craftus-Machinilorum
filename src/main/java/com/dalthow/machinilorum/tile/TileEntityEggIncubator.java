package com.dalthow.machinilorum.tile;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class TileEntityEggIncubator extends TileEntity
{
	// Declaration of the tickToHatch counter and canPutEgg in flag.
    public int tickToHatch; 
    
    public boolean canPutEggIn; 
    
	
	// Constructor.
	public TileEntityEggIncubator()
	{
		canPutEggIn = true;
		tickToHatch = 0; 
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
    	// Checks if we are not on a server.
        if(!worldObj.isRemote) 
        {
        	// Checks if its time to hatch.
            if(tickToHatch <= 0) 
            { 
                if(canPutEggIn == false) 
                { 
                	// Spawns a baby chicken on the correct position, based on the rotation.
                	switch(getBlockMetadata())
                    {
            	        case 0: spawnBabyChicken(worldObj, xCoord + 0.5F, yCoord + 1F, zCoord + 0.5F); 
            	        		
            	        break;
            	        
            	        case 1: spawnBabyChicken(worldObj, xCoord + 0.5F, yCoord - 1F, zCoord + 0.5F); 
            	        
            	        break;
            	        		
            	        case 2: spawnBabyChicken(worldObj, xCoord - 0.5F, yCoord, zCoord + 0.5F); 
            			
            	        break;
            	        	
            	        case 3: spawnBabyChicken(worldObj, xCoord + 1.5F, yCoord, zCoord + 0.5F); 
            			
            	        break;
            					
            	        case 4: spawnBabyChicken(worldObj, xCoord + 0.5F, yCoord, zCoord + 1.5F); 
            			
            	        break;
            					
            	        case 5: spawnBabyChicken(worldObj, xCoord + 0.5F, yCoord, zCoord - 0.5F); 
            			
            	        break;
                    }
                	
                    canPutEggIn = true; 
                } 
            } 
              
            else
            { 
                tickToHatch--; 
            } 
        }  
    }

    public void readFromNBT(NBTTagCompound tag) 
    { 
        canPutEggIn = tag.getBoolean("canPutEggIn"); 
        tickToHatch = tag.getInteger("tickToHatch"); 
       
        super.readFromNBT(tag);     
    } 

    public void writeToNBT(NBTTagCompound tag) 
    { 
    	tag.setBoolean("canPutEggIn", canPutEggIn);  
    	tag.setInteger("tickToHatch", tickToHatch); 
       
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
     * Spawns a chicken at the block location and setting his age to a baby.
     *
     * @param world The world object.
     * @param xPos  The x position of the desired spawn location.
     * @param yPos  The y position of the desired spawn location.
     * @param zPos  The z position of the desired spawn location.
     */
    public static void spawnBabyChicken(World world, float xPos, float yPos, float zPos)
    {
        EntityChicken chicken = new EntityChicken(world);

        chicken.setPosition(xPos, yPos, zPos);
        chicken.setGrowingAge(-24000);

        world.spawnEntityInWorld(chicken);
        world.markBlockForUpdate((int)xPos, (int)yPos, (int)zPos);
    }
}
