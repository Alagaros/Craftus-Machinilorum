package com.dalthow.machinilorum.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public abstract class AbstractPacket 
{	
	/**
     * Encodes a packet.
     * 
     * @param context Enables a ChannelHandler to interact with its ChannelPipeline and other handlers.
     * @param buffer A random and sequential accessible sequence of zero or more bytes.
     */
    public abstract void encodeInto(ChannelHandlerContext context, ByteBuf buffer);
    
    /**
     * Decodes a packet.
     * 
     * @param context Enables a ChannelHandler to interact with its ChannelPipeline and other handlers.
     * @param buffer A random and sequential accessible sequence of zero or more bytes.
     */
    public abstract void decodeInto(ChannelHandlerContext context, ByteBuf buffer);

    /**
     * Sends the packet to a player.
     * 
     * @param player The player that should receive the packet.
     */
    public abstract void handleClientSide(EntityPlayer player);

    /**
     * Sends the packet to the server.
     * 
     * @param player The player that sends the packet.
     */
    public abstract void handleServerSide(EntityPlayer player);
}
