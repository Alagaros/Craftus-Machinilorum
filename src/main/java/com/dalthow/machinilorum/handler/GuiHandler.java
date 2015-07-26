/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class GuiHandler.java
 * 
 **/

package com.dalthow.machinilorum.handler;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.container.ContainerFragmentizer;
import com.dalthow.machinilorum.container.ContainerMobRadar;
import com.dalthow.machinilorum.container.ContainerStoneCutter;
import com.dalthow.machinilorum.gui.GuiFragmentizer;
import com.dalthow.machinilorum.gui.GuiMobRadar;
import com.dalthow.machinilorum.gui.GuiStoneCutter;
import com.dalthow.machinilorum.tile.TileEntityFragmentizer;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	// Whenever a player opens a interface it opens the container on the server side
	
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int xPos, int yPos, int zPos) 
	{	
		TileEntity tile = world.getTileEntity(xPos, yPos, zPos);
		
		if(tile != null)
		{
			switch(id)
			{
				case 0: if(tile instanceof TileEntityFragmentizer)
						{
							return new ContainerFragmentizer(player.inventory, (TileEntityFragmentizer) tile);
						}
						
						else
						{
							return null;
						}
				
				case 2: if(tile instanceof TileEntityMobRadar)
						{
							return new ContainerMobRadar((TileEntityMobRadar) tile);
						}
						
						else
						{
							return null;
						}
			}			
		}

		if(id == 1)
		{
			return id == 1 && world.getBlock(xPos, yPos, zPos) == Main.blockStoneCutter ? new ContainerStoneCutter(player.inventory, world, xPos, yPos, zPos) : null;
		}
		
		return null;
	}
	
	
	// Whenever a player opens a interface it opens the user interface on the client side
	
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int xPos, int yPos, int zPos) 
	{
		TileEntity tile = world.getTileEntity(xPos, yPos, zPos);
	
		if(tile != null)
		{
			switch(id)
			{
				case 0: if(tile instanceof TileEntityFragmentizer)
						{
							return new GuiFragmentizer(player.inventory, (TileEntityFragmentizer) tile);
						}
						
						else
						{
							return null;
						}
				
				case 2: if(tile instanceof TileEntityMobRadar)
						{
							return new GuiMobRadar((TileEntityMobRadar) tile);
						}
						
						else
						{
							return null;
						}
			}
		}
		
		if(id == 1) 
		{
			return id == 1 && world.getBlock(xPos, yPos, zPos) == Main.blockStoneCutter ? new GuiStoneCutter(player.inventory, world, xPos, yPos, zPos) : null;
		}
		
		return null;
	}
}
