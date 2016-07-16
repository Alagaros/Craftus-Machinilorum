package com.dalthow.machinilorum.packet.mobradar;

import com.dalthow.machinilorum.packet.IPacket;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class PacketMobRadarSignal extends IPacket
{
	// Declaration for the position of the block.
	int xPos;
	int yPos;
	int zPos;

	// Declaring signal that is set.
	int signal;

	// Constructors.
	public PacketMobRadarSignal(){}
	
	public PacketMobRadarSignal(int xPos, int yPos, int zPos, int signal) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		
		this.signal = signal;
	}

	// Encodes the packet.
	@Override
	public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		buffer.writeInt(xPos);
		buffer.writeInt(yPos);
		buffer.writeInt(zPos);
		
		buffer.writeInt(signal);
	}

	// Decodes the packet.
	@Override
	public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		this.xPos = buffer.readInt();
		this.yPos = buffer.readInt();
		this.zPos = buffer.readInt();
		
		this.signal = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.signal = signal;
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.signal = signal;
	}
}
