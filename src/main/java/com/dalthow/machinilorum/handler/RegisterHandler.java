package com.dalthow.machinilorum.handler;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class RegisterHandler.java
 * 
 **/

public class RegisterHandler 
{
	// Registers a item so that it gets loaded in the game.
	
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, Reference.modId + "_" + item.getUnlocalizedName().substring(5));
	}
	
	
	// Registers a block so that it gets loaded in the game.
	
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, Reference.modId + "_" + block.getUnlocalizedName().substring(5));
	}
	
	
	// Registers a block so that it gets loaded in the game.
	
	public static void registerBlockWithItem(Block block, Class<? extends ItemBlock> itemclass)
	{
		GameRegistry.registerBlock(block, itemclass, Reference.modId + "_" + block.getUnlocalizedName().substring(5));
	}
	
	
	// Registers a tile entity so it gets loaded in the game.
	
	public static void registerTileEntity(Class title, String name)
	{
		GameRegistry.registerTileEntity(title, name);
	}
	
	
	// Registers a entity so it gets loaded in the game.
	
	public static void registerEntity(Class title, String name)
	{
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		
		EntityRegistry.registerGlobalEntityID(title, name, entityId);
		EntityRegistry.registerModEntity(title, name, entityId, Main.instance, 64, 1, true);
	}
}