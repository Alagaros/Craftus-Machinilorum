package com.dalthow.machinilorum.packet.mobradar;

import com.dalthow.machinilorum.packet.AbstractPacket;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class PacketMobRadarRadius extends AbstractPacket 
{
	// Declaration for the position of the block.
	int xPos;
	int yPos;
	int zPos;

	// Declaring radius that is entered.
	int radius;


	// Constructors.
	public PacketMobRadarRadius(){}
	
	public PacketMobRadarRadius(int xPos, int yPos, int zPos, int radius) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		
		this.radius = radius;
	}


	// Encodes the packet.
	@Override
	public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		buffer.writeInt(xPos);
		buffer.writeInt(yPos);
		buffer.writeInt(zPos);
		
		buffer.writeInt(radius);
	}

	// Decodes the packet.
	@Override
	public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) 
	{
		this.xPos = buffer.readInt();
		this.yPos = buffer.readInt();
		this.zPos = buffer.readInt();
		
		this.radius = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.radius = radius;
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)player.worldObj.getTileEntity(xPos, yPos, zPos);
		
		tile.radius = radius;
	}
}
