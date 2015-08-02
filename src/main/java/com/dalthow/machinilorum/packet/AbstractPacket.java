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
	/**
     * loadRenderers Encodes a packet.
     * 
     * @param  {ChannelHandlerContext} context Enables a ChannelHandler to interact with its ChannelPipeline and other handlers.
     * @param  {ByteBuf} buffer A random and sequential accessible sequence of zero or more bytes.
     * 
     * @return {void}
     */
    public abstract void encodeInto(ChannelHandlerContext context, ByteBuf buffer);
    
    
    /**
     * decodeInto Decodes a packet.
     * 
     * @param  {ChannelHandlerContext} context Enables a ChannelHandler to interact with its ChannelPipeline and other handlers.
     * @param  {ByteBuf} buffer A random and sequential accessible sequence of zero or more bytes.
     * 
     * @return {void}
     */
    public abstract void decodeInto(ChannelHandlerContext context, ByteBuf buffer);

    
    /**
     * handleClientSide Sends the packet to a player.
     * 
     * @param  {EntityPlayer} The player that should receive the packet.
     * 
     * @return {void}
     */
    public abstract void handleClientSide(EntityPlayer player);
    
    
    /**
     * handleServerSide Sends the packet to the server.
     * 
     * @param  {EntityPlayer} The player that sends the packet.
     * 
     * @return {void}
     */
    public abstract void handleServerSide(EntityPlayer player);
}
