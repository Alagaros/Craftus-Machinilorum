/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class PacketMobRadarRadius.java
 * 
 **/

package com.dalthow.machinilorum.packet.mobradar;

import com.dalthow.machinilorum.packet.AbstractPacket;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMobRadarRadius extends AbstractPacket 
{
	// Declaration
	
	int xPos;
	int yPos;
	int zPos;
	
	int radius;

	
	// Constructors
	
	public PacketMobRadarRadius() 
	{
		
	}
	
	public PacketMobRadarRadius(int xPos, int yPos, int zPos, int radius) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		
		this.radius = radius;
	}
	
	
	// Encodes the packet
	
	@Override
	public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		buffer.writeInt(xPos);
		buffer.writeInt(yPos);
		buffer.writeInt(zPos);
		
		buffer.writeInt(radius);
	}
	
	
	// Decodes the packet

	@Override
	public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		this.xPos = buffer.readInt();
		this.yPos = buffer.readInt();
		this.zPos = buffer.readInt();
		
		this.radius = buffer.readInt();
	}

	
	// Handles the client side
	
	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.radius = radius;
	}

	
	// Handles the server side
	
	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.radius = radius;
	}
}
