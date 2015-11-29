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
 * @author Trevi Awater
 **/

public class RegisterHandler 
{
	/**
     * Registers a item so that it gets loaded in the game.
     * 
     * @param item The item that should get registered.
     */
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, Reference.modId + "_" + item.getUnlocalizedName().substring(5));
	}

	/**
     * Registers a block so that it gets loaded in the game.
     * 
     * @param block The block that should get registered.
     */
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, Reference.modId + "_" + block.getUnlocalizedName().substring(5));
	}

	/**
     * Registers a block with an item, this is used for custom rendered blocks.
     * 
     * @param block     The block that should get registered.
     * @param itemBlock The item that should get bound to the block.
     */
	public static void registerBlockWithItem(Block block, Class<? extends ItemBlock> itemBlock)
	{
		GameRegistry.registerBlock(block, itemBlock, Reference.modId + "_" + block.getUnlocalizedName().substring(5));
	}

	/**
     * Registers a TileEntity so it can tick in the game.
     * 
     * @param tile The class where the TileEntity is in.
     * @param name The name of the TileEntity.
     */
	public static void registerTileEntity(Class tile, String name)
	{
		GameRegistry.registerTileEntity(tile, name);
	}

	/**
     * registerEntity Registers a Entity so it can tick in the game.
     * 
     * @param entity The class where the Entity is in.
     * @param name   The name of the Entity.
     */
	public static void registerEntity(Class entity, String name)
	{
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		
		EntityRegistry.registerGlobalEntityID(entity, name, entityId);
		EntityRegistry.registerModEntity(entity, name, entityId, Main.instance, 64, 1, true);
	}
}