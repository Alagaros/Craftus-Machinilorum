package com.dalthow.machinilorum.tab;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class TabItems.java
 * 
 **/

public class TabTools extends CreativeTabs 
{
	// Constructor that adds data to the tab.
	
	public TabTools(int identifier, String name) 
	{
		super(identifier, name);
	}
	
	
	// Sets the icon for the creative tab.
	
	@Override
	public Item getTabIconItem() 
	{
		return Main.itemBomb;
	}
}