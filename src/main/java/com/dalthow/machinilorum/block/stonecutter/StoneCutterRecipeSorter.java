/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class StoneCutterRecipeSorter.java
 * 
 **/

package com.dalthow.machinilorum.block.stonecutter;

import java.util.Comparator;

import net.minecraft.item.crafting.IRecipe;

public class StoneCutterRecipeSorter implements Comparator<Object> 
{
	// Declaration
	
	final StoneCutterRecipeManager stoneCutterRecipeManager;

	
	// Constructor
	
	public StoneCutterRecipeSorter(StoneCutterRecipeManager stoneCutterRecipeManager) 
	{
		this.stoneCutterRecipeManager = stoneCutterRecipeManager;
	}

	
	// Checks if the recipes are no duplicates
	
	public int compareRecipes(IRecipe firstRecipe, IRecipe secondRecipe) 
	{
		return firstRecipe instanceof StoneCutterShapelessRecipes && secondRecipe instanceof StoneCutterShapedRecipes ? 1 : (secondRecipe instanceof StoneCutterShapelessRecipes && firstRecipe instanceof StoneCutterShapedRecipes ? -1 : (secondRecipe.getRecipeSize() < firstRecipe.getRecipeSize() ? -1 : (secondRecipe.getRecipeSize() > firstRecipe.getRecipeSize() ? 1 : 0)));
	}

	public int compare(Object firstObject, Object secondObject) 
	{
		return compareRecipes((IRecipe)firstObject, (IRecipe)secondObject);
	}
}