package com.dalthow.machinilorum.packet.mobradar;

import com.dalthow.machinilorum.packet.AbstractPacket;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class PacketMobRadarId.java
 * 
 **/

public class PacketMobRadarId extends AbstractPacket 
{
	// Declaration for the position of the block.
	
	int xPos;
	int yPos;
	int zPos;
	
	
	// Declaring the kind of mob that is selected.
	
	int mobId;

	
	// For some reason packets need an empty constructor, don't ask me why.
	
	public PacketMobRadarId(){}
	
	public PacketMobRadarId(int xPos, int yPos, int zPos, int mobId) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		
		this.mobId = mobId;
	}
	
	
	// Encodes the packet.
	
	@Override
	public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		buffer.writeInt(xPos);
		buffer.writeInt(yPos);
		buffer.writeInt(zPos);
		
		buffer.writeInt(mobId);
	}
	
	
	// Decodes the packet.

	@Override
	public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		this.xPos = buffer.readInt();
		this.yPos = buffer.readInt();
		this.zPos = buffer.readInt();
		
		this.mobId = buffer.readInt();
	}

	
	// Handles the client side.
	
	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.mobId = mobId;
	}

	
	// Handles the server side.
	
	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.mobId = mobId;
	}
}
