package com.dalthow.machinilorum.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class AbstractPacket.java
 * 
 **/

public abstract class AbstractPacket 
{
	// Abstract network methods.
	
    public abstract void encodeInto(ChannelHandlerContext context, ByteBuf buffer);
    public abstract void decodeInto(ChannelHandlerContext context, ByteBuf buffer);

    public abstract void handleClientSide(EntityPlayer player);
    public abstract void handleServerSide(EntityPlayer player);
}
