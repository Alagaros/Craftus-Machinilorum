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

public class ItemConveyorBelt extends ItemBlock
{
	// Constructor.
	public ItemConveyorBelt(Block block) 
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
		
			list.add(I18n.format("tile.conveyorBelt.name"));
			list.add("Used to transfer entities");
	    	list.add("around the world. When provided");
	    	list.add("a redstone signal the Conveyor");
	    	list.add("Belt gets disabled.");
		}
    }
}
