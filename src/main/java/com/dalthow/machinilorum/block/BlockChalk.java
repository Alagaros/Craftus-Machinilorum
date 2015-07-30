package com.dalthow.machinilorum.block;

import java.util.List;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class BlockChalk.java
 * 
 **/

public class BlockChalk extends Block
{
	// Declaration of all the sub blocks.
	
	final String[] subBlocks = new String[] {"raw", "bricks"};
	
	
	// Constructor that adds data to the block.
	
	public BlockChalk()
	{
		super(Material.rock);
		
		setCreativeTab(Main.tabMachinilorumMachines);
		setBlockTextureName(Reference.modId + ":" + "chalk/");
		setHardness(0.50F);
	}
	
	
	// Only make the texture variable on the client side so the server ignores this.
	
	@SideOnly(Side.CLIENT)
	private IIcon[] texture;

	
	// Loads the different textures also gets ignored by the server.
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) 
	{
		texture = new IIcon[subBlocks.length];

		
		// Looping trough all the sub blocks and giving them their appropriate texture. 
		
		for(int i = 0; i < subBlocks.length; i++) 
		{
			texture[i] = registry.registerIcon(getTextureName() + subBlocks[i]);
		}
	}

	
	// Returns a list of blocks with different meta data.
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tabs, List list) 
	{
		for(int i = 0; i < subBlocks.length; i++) 
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	

	// Determines the textures displayed on the blocks based on the side and metadata also gets ignored by the server.
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) 
	{
		return texture[meta];
	}

	
	// Makes sure that if the players breaks one of the blocks they get the right one.
	
	public int damageDropped(int meta) 
	{
		return meta;
	}
}
