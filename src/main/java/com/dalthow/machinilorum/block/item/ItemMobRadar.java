package com.dalthow.machinilorum.block.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class ItemMobRadar.java
 * 
 **/

public class ItemMobRadar extends ItemBlock
{
	// Constructor.
	
	public ItemMobRadar(Block block) 
	{
		super(block);
	}
	
	
	// Adds a tool-tip to the item.

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean isValid)
    {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.clear();
		
			list.add(I18n.format("tile.mobRadar.name", new Object[0]));
			list.add("Used to detect mobs in a");
	    	list.add("certain radius.");
		}
    }
}
