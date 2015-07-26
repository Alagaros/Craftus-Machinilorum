/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class TabBlocks.java
 * 
 **/

package com.dalthow.machinilorum.tab;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabMachines extends CreativeTabs 
{
	// Constructor
	
	public TabMachines(int identifier, String name) 
	{
		super(identifier, name);
	}

	
	// Sets the icon for the creative tab
	
	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(Main.blockStoneCutter);
	}
}