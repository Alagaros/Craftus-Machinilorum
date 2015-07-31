package com.dalthow.machinilorum.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.entity.EntityBomb;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class ItemBomb.java
 * 
 **/

public class ItemBomb extends Item 
{
	// Constructor that adds data to the item.
	
	public ItemBomb()
	{
		super();
		setUnlocalizedName("bomb");
		setTextureName(Reference.modId + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(Main.tabMachinilorumTools);
		setMaxStackSize(16);
	}
	
	
	// Throws a new entity on a right click event.
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(!player.capabilities.isCreativeMode)
		{
		    itemStack.stackSize--;
		}
		
		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote)
		{
		    world.spawnEntityInWorld(new EntityBomb(world, player));
		}
		
		return itemStack;
	}
	
	
	// Adds a tool-tip to the item.

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean isValid)
    {
		list.add("Explosion radius:" + " " + 2);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.clear();
		
			list.add(I18n.format("item.bomb.name", new Object[0]));
			list.add("Used to throw and blow things up.");
		}
    }
}
