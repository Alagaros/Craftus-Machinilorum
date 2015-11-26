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
 * @author Trevi Awater
 **/

public class ItemWoodCutter extends ItemBlock
{
	// Constructor.
	public ItemWoodCutter(Block block) 
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
		
			list.add(I18n.format("tile.woodCutter.name"));
			list.add("Used to break blocks made from");
	    	list.add("wood. Requires a redstone");
	    	list.add("signal to be active. Can be");
	    	list.add("turned by right clicking");
	    	list.add("with a empty hand.");
		}
    }
}
