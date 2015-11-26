package com.dalthow.machinilorum.block.stonecutter;

import java.util.Comparator;

import net.minecraft.item.crafting.IRecipe;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class StoneCutterRecipeSorter implements Comparator<Object> 
{
	// Declaration.
	final StoneCutterRecipeManager stoneCutterRecipeManager;

	// Creating a new instance.
	public StoneCutterRecipeSorter(StoneCutterRecipeManager stoneCutterRecipeManager) 
	{
		this.stoneCutterRecipeManager = stoneCutterRecipeManager;
	}


	/**
     * Checks if the recipes are no duplicates.
     * 
     * @param firstRecipe  The first recipe.
     * @param secondRecipe The second recipe.
     *
     * @return int
     */
	public int compareRecipes(IRecipe firstRecipe, IRecipe secondRecipe) 
	{
		return firstRecipe instanceof StoneCutterShapelessRecipes && secondRecipe instanceof StoneCutterShapedRecipes ? 1 : (secondRecipe instanceof StoneCutterShapelessRecipes && firstRecipe instanceof StoneCutterShapedRecipes ? -1 : (secondRecipe.getRecipeSize() < firstRecipe.getRecipeSize() ? -1 : (secondRecipe.getRecipeSize() > firstRecipe.getRecipeSize() ? 1 : 0)));
	}

	/**
     * A shortcut for compareRecipes.
     * 
     * @param firstObject  The first object.
     * @param secondObject The second object.
     * 
     * @return int
     */
	public int compare(Object firstObject, Object secondObject) 
	{
		return compareRecipes((IRecipe)firstObject, (IRecipe)secondObject);
	}
}