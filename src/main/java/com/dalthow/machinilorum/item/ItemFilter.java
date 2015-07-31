package com.dalthow.machinilorum.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.util.MathHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class ItemFilter.java
 * 
 **/

public class ItemFilter extends Item 
{
	// Constructor that adds data to the item.
	
	public ItemFilter()
	{
		super();
		setUnlocalizedName("filter");
		setTextureName(Reference.modId + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(Main.tabMachinilorumTools);
		setMaxDamage(25 - 1);
		setMaxStackSize(1);
	}
	
	
	// Gets triggered when you use the item on a block.
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xPos, int yPos, int zPos, int par1, float par2, float par3, float par4)
	{
		// Checking if we are on the client side.
		
		if(!world.isRemote)
		{
			// Checking if the block targeted is clay, if so there also needs to be water above it.
			
			if((world.getBlock(xPos, yPos, zPos) == Blocks.clay) && (world.getBlock(xPos, yPos + 1, zPos) == Blocks.water))
			{
				world.setBlock(xPos, yPos, zPos, Blocks.sand);
				
				
				// Checks if the user is lucky.
				
				if(MathHelper.randomNumber(1, 3) == 1)
				{
					// Create a gold nugget at the position the block is.
					
					EntityItem Reward = new EntityItem(world, xPos, yPos, zPos, new ItemStack(Items.gold_nugget, 1));
					
					world.spawnEntityInWorld(Reward);
				}
			}
			
			else if(world.getBlock(xPos, yPos, zPos) == Blocks.gravel)
			{
				// Transform the gravel into sand and drop a piece of flint.
				
				world.setBlock(xPos, yPos, zPos, Blocks.sand);
				
				EntityItem Reward = new EntityItem(world, xPos, yPos, zPos, new ItemStack(Items.flint, 1));
				
				world.spawnEntityInWorld(Reward);
			}
			
			itemStack.damageItem(1, player);
		}
		
		return true;
	}
	
	
   // Adds a tool-tip to the item.

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean isValid)
    {
		list.add(((itemStack.getMaxDamage() + 1) - itemStack.getItemDamage()) + "/" + (itemStack.getMaxDamage() + 1) + " " + "Uses remaining.");
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.clear();
		
			list.add(I18n.format("item.filter.name", new Object[0]));
	    	list.add("Used to turn clay and gravel in to");
	    	list.add("sand. During this process you may");
	    	list.add("find some resources like golden");
	    	list.add("nuggets or flint!");
		}
    }
}
