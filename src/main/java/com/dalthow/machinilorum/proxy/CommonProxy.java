/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class CommonProxy.java
 * 
 **/

package com.dalthow.machinilorum.proxy;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class CommonProxy 
{
	// Binds and registers all the models to their items, blocks or entities 
	
	public void loadRenderers() 
	{
		
	}
	
	
	// Is used to remove vanilla recipes
	
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
