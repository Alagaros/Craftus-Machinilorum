package com.dalthow.machinilorum.entity.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.entity.EntityShoppingCart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class ItemShoppingCart extends Item 
{
	// Constructor.
	public ItemShoppingCart()
	{
		super();
		
		setUnlocalizedName("shoppingCart");
		setCreativeTab(Main.tabMachinilorumMachines);
	}

	
	// Gets triggered when you use the item on a block.
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xPos, int yPos, int zPos, int par1, float par2, float par3, float par4)
	{
		if(!world.isRemote)
		{
			EntityShoppingCart shoppingCart = new EntityShoppingCart(world); 
	        
	        shoppingCart.setPosition(xPos + 0.5F, yPos + 1, zPos + 0.5F); 
	        
	        world.spawnEntityInWorld(shoppingCart); 
	
			itemStack.stackSize--;
		}
		
		return true;
	}

    // Adds a tool-tip to the item.
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean isValid)
    {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.clear();
		
			list.add(I18n.format("item.shoppingCart.name"));
	    	list.add("Used to store items and");
	    	list.add("push it around.");
		}
    }
}