package com.dalthow.machinilorum.proxy;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.entity.EntityBomb;
import com.dalthow.machinilorum.entity.EntityShoppingCart;
import com.dalthow.machinilorum.render.block.RenderChalkboard;
import com.dalthow.machinilorum.render.block.RenderConveyorBelt;
import com.dalthow.machinilorum.render.block.RenderEggIncubator;
import com.dalthow.machinilorum.render.block.RenderMobRadar;
import com.dalthow.machinilorum.render.block.RenderSprinkler;
import com.dalthow.machinilorum.render.block.RenderWoodCutter;
import com.dalthow.machinilorum.render.entity.RenderEntityBomb;
import com.dalthow.machinilorum.render.entity.RenderEntityShoppingCart;
import com.dalthow.machinilorum.render.item.RenderBomb;
import com.dalthow.machinilorum.render.item.RenderFilter;
import com.dalthow.machinilorum.render.item.inventory.RenderItemChalkboard;
import com.dalthow.machinilorum.render.item.inventory.RenderItemConveyorBelt;
import com.dalthow.machinilorum.render.item.inventory.RenderItemEggIncubator;
import com.dalthow.machinilorum.render.item.inventory.RenderItemMobRadar;
import com.dalthow.machinilorum.render.item.inventory.RenderItemShoppingCart;
import com.dalthow.machinilorum.render.item.inventory.RenderItemSprinkler;
import com.dalthow.machinilorum.render.item.inventory.RenderItemWoodCutter;
import com.dalthow.machinilorum.tile.TileEntityChalkboard;
import com.dalthow.machinilorum.tile.TileEntityConveyorBelt;
import com.dalthow.machinilorum.tile.TileEntityEggIncubator;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;
import com.dalthow.machinilorum.tile.TileEntitySprinkler;
import com.dalthow.machinilorum.tile.TileEntityWoodCutter;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class ClientProxy extends CommonProxy
{
	/**
     * Registers all custom renders in this mod.
     */
	@SideOnly(Side.CLIENT) 
	@Override
	public void loadRenderers() 
	{ 
	    super.loadRenderers();
	    
		// Registering the item renders.
		MinecraftForgeClient.registerItemRenderer(Main.itemFilter, new RenderFilter());
		MinecraftForgeClient.registerItemRenderer(Main.itemBomb, new RenderBomb()); 
		MinecraftForgeClient.registerItemRenderer(Main.itemShoppingCart, new RenderItemShoppingCart());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Main.blockWoodCutter), new RenderItemWoodCutter());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Main.blockEggIncubator), new RenderItemEggIncubator());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Main.blockMobRadar), new RenderItemMobRadar());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Main.blockConveyorBelt), new RenderItemConveyorBelt());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Main.blockSprinkler), new RenderItemSprinkler());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(Main.blockChalkboard), new RenderItemChalkboard());
		
		// Registering the TileEntity renders.
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEggIncubator.class, new RenderEggIncubator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodCutter.class, new RenderWoodCutter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMobRadar.class, new RenderMobRadar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityConveyorBelt.class, new RenderConveyorBelt());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySprinkler.class, new RenderSprinkler());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChalkboard.class, new RenderChalkboard());
		
		// Registering the entity renders.
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderEntityBomb(Main.itemBomb));    
		RenderingRegistry.registerEntityRenderingHandler(EntityShoppingCart.class, new RenderEntityShoppingCart());
	} 
}
 