/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class AbstractPacket.java
 * 
 **/

package com.dalthow.machinilorum.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket 
{
	// Abstract network methods
	
    public abstract void encodeInto(ChannelHandlerContext context, ByteBuf buffer);
    public abstract void decodeInto(ChannelHandlerContext context, ByteBuf buffer);

    public abstract void handleClientSide(EntityPlayer player);
    public abstract void handleServerSide(EntityPlayer player);
}
