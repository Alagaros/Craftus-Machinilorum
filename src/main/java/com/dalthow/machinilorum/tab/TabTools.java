/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class TabItems.java
 * 
 **/

package com.dalthow.machinilorum.tab;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabTools extends CreativeTabs 
{
	// Constructor
	
	public TabTools(int identifier, String name) 
	{
		super(identifier, name);
	}
	
	
	// Sets the icon for the creative tab
	
	@Override
	public Item getTabIconItem() 
	{
		return Main.itemBomb;
	}
}