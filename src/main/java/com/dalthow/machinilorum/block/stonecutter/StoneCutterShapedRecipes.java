package com.dalthow.machinilorum.block.stonecutter;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class StoneCutterShapedRecipes implements IRecipe
{ 
	// Declaration of the crafting output.
	private ItemStack recipeOutput;

	// Declaring some other value's
	private boolean flag;

    public final int recipeWidth;
    public final int recipeHeight;

    // Declaring the items that can now be crafted.
    public final ItemStack[] recipeItems;


    // Constructor that sets the local variables based on its parameters.
    public StoneCutterShapedRecipes(int recipeWidth, int recipeHeight, ItemStack[] recipeItems, ItemStack recipeOutput)
    {
        this.recipeWidth = recipeWidth;
        this.recipeHeight = recipeHeight;
        this.recipeItems = recipeItems;
        this.recipeOutput = recipeOutput;
    }


    /**
     * Returns the output of a recipe.
     * 
     * @return ItemStack
     */
    public ItemStack getRecipeOutput()
    {
        return recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory.
     * 
     * @param inventoryCrafting The crafting recipe that has been used.
     * @param world				The world object.
     * 
     * @return ItemStack
     */
    public boolean matches(InventoryCrafting inventoryCrafting, World world)
    {
        for(int i = 0; i <= 5 - recipeWidth; ++i)
        {
            for(int j = 0; j <= 5 - recipeHeight; ++j)
            {
                if(checkMatch(inventoryCrafting, i, j, true))
                {
                    return true;
                }

                if(checkMatch(inventoryCrafting, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
    }
    
    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     * 
     * @param inventoryCrafting The crafting recipe that has been used.
     * @param par1				X position in crafting grid.
     * @param par2				Y position in crafting grid.
     * @param par3				?
     * 
     * @return {ItemStack}
     */
    private boolean checkMatch(InventoryCrafting inventoryCrafting, int par1, int par2, boolean par3)
    {
        for(int i = 0; i < 5; ++i)
        {
            for(int j = 0; j < 5; ++j)
            {
                int k = i - par1;
                int l = j - par2;
                
                ItemStack var1 = null;

                if(k >= 0 && l >= 0 && k < recipeWidth && l < recipeHeight)
                {
                    if(par3)
                    {
                        var1 = recipeItems[recipeWidth - k - 1 + l * recipeWidth];
                    }
                    
                    else
                    {
                        var1 = recipeItems[k + l * recipeWidth];
                    }
                }

                ItemStack var2 = inventoryCrafting.getStackInRowAndColumn(i, j);

                if(var2 != null || var1 != null)
                {
                    if(var2 == null && var1 != null || var2 != null && var1 == null)
                    {
                        return false;
                    }

                    if(var1.getItem() != var2.getItem())
                    {
                        return false;
                    }

                    if(var1.getItemDamage() != 32767 && var1.getItemDamage() != var2.getItemDamage())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    /**
     * Returns an ItemStack that is the result of this recipe.
     * 
     * @param inventoryCrafting The crafting recipe that has been used.
     * 
     * @return ItemStack
     */
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting)
    {
        ItemStack var1 = getRecipeOutput().copy();

        if(flag)
        {
            for(int i = 0; i < inventoryCrafting.getSizeInventory(); ++i)
            {
                ItemStack var2 = inventoryCrafting.getStackInSlot(i);

                if(var2 != null && var2.hasTagCompound())
                {
                    var1.setTagCompound((NBTTagCompound)var2.stackTagCompound.copy());
                }
            }
        }

        return var1;
    }

    /**
     * Gets the size of a recipe.
     * 
     * @return int
     */
    public int getRecipeSize()
    {
        return recipeWidth * recipeHeight;
    }
}