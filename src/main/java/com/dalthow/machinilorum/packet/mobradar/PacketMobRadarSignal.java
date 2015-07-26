/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class PacketMobRadarSignal.java
 * 
 **/

package com.dalthow.machinilorum.packet.mobradar;

import com.dalthow.machinilorum.packet.AbstractPacket;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMobRadarSignal extends AbstractPacket 
{
	// Declaration
	
	int xPos;
	int yPos;
	int zPos;
	
	int signal;

	
	// Constructors
	
	public PacketMobRadarSignal() 
	{
		
	}
	
	public PacketMobRadarSignal(int xPos, int yPos, int zPos, int signal) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		
		this.signal = signal;
	}
	
	
	// Encodes the packet
	
	@Override
	public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		buffer.writeInt(xPos);
		buffer.writeInt(yPos);
		buffer.writeInt(zPos);
		
		buffer.writeInt(signal);
	}
	
	
	// Decodes the packet

	@Override
	public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		this.xPos = buffer.readInt();
		this.yPos = buffer.readInt();
		this.zPos = buffer.readInt();
		
		this.signal = buffer.readInt();
	}

	
	// Handles the client side
	
	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.signal = signal;
	}

	
	// Handles the server side
	
	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.signal = signal;
	}
}
