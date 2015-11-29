package com.dalthow.machinilorum.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

// TODO: Add support for multiple lines.

public class TileEntityChalkboard extends TileEntity
{
	// Declaration of some positional variables.
	public float startPosX = 0;
	public float startPosY = 0;
	public float startPosZ = 0;
	
	public float endPosX = 0;
	public float endPosY = 0;
	public float endPosZ = 0;
	
	public boolean startDefined = false;
	
	
    // Reading from the tag compound.
    public void readFromNBT(NBTTagCompound tag) 
    { 
    	startPosX = tag.getFloat("startPosX"); 
    	startPosY = tag.getFloat("startPosY");
    	startPosZ = tag.getFloat("startPosZ");
    	
    	endPosX = tag.getFloat("endPosX"); 
    	endPosY = tag.getFloat("endPosY");
    	endPosZ = tag.getFloat("endPosZ");
    	
    	startDefined = tag.getBoolean("startDefined");
        
        super.readFromNBT(tag);     
    } 

    // Writing to the tag compound.
    public void writeToNBT(NBTTagCompound tag) 
    { 
    	tag.setFloat("startPosX", startPosX); 
    	tag.setFloat("startPosY", startPosY);
    	tag.setFloat("startPosZ", startPosZ);
    	
    	tag.setFloat("endPosX", endPosX); 
    	tag.setFloat("endPosY", endPosY);
    	tag.setFloat("endPosZ", endPosZ);
    	
    	tag.setBoolean("startDefined", startDefined);
    	
        super.writeToNBT(tag); 
    } 

 	// Tells the game that the tile can update.
 	@Override
 	public boolean canUpdate()
    {
         return true;
    }

 	// Used for reading packets.
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