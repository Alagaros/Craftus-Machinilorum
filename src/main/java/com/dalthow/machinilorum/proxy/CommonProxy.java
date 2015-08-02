package com.dalthow.machinilorum.proxy;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class CommonProxy.java
 * 
 **/

public class CommonProxy 
{
	/**
     * loadRenderers Gets extended in the ClientProxy.
     * 
     * @return {void}
     */
	public void loadRenderers() 
	{
		
	}
	
	
	/**
     * removeRecipe Removes the crafting recipe of an item.
     *
     * @param  {Item} item The item that should have its recipe removed.
     *
     * @return {void}
     */
	public static void removeRecipe(Item item)
	{
	    List<IRecipe> recipesList = CraftingManager.getInstance().getRecipeList();

	    Iterator<IRecipe> removerIterator = recipesList.iterator();

	    while(removerIterator.hasNext())
	    {
	    	ItemStack itemStack = removerIterator.next().getRecipeOutput();
	    	
	    	if(itemStack != null && itemStack.getItem() == item)
	    	{
	            removerIterator.remove();
	    	}
	    }
	}
}
