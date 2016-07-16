package com.dalthow.machinilorum.tab;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class TabMachines extends CreativeTabs 
{
	// Constructor that adds data to the tab.
	public TabMachines(int identifier, String name) 
	{
		super(identifier, name);
	}

	// Sets the icon for the creative tab.
	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(Main.blockStoneCutter);
	}
}