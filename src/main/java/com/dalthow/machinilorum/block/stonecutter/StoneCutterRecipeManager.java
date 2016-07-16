package com.dalthow.machinilorum.block.stonecutter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class StoneCutterRecipeManager
{
	// Declaration for recipe list.
    private List<IRecipe> recipes = new ArrayList<IRecipe>();

    // Creating a new instance.
    private static final StoneCutterRecipeManager instance = new StoneCutterRecipeManager();

    public static final StoneCutterRecipeManager getInstance()
    {
        return instance;
    }

    // Constructor.
    private StoneCutterRecipeManager()
    {
        // Adding the block recipe's.
        addRecipe(new ItemStack(Blocks.cobblestone, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 3)});
        addRecipe(new ItemStack(Blocks.stone, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 0)});
        addRecipe(new ItemStack(Blocks.sandstone, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 1)});
        addRecipe(new ItemStack(Blocks.brick_block, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 4)});
        addRecipe(new ItemStack(Blocks.stonebrick, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 5)});
        addRecipe(new ItemStack(Blocks.nether_brick, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 6)});
        addRecipe(new ItemStack(Blocks.quartz_block, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.stone_slab, 1, 7)});
        addRecipe(new ItemStack(Blocks.quartz_block, 1), new Object[] {"##", "##", '#', Items.quartz});
        addRecipe(new ItemStack(Blocks.sandstone, 1), new Object[] {"##", "##", '#', Blocks.sand});
        addRecipe(new ItemStack(Blocks.stonebrick, 4), new Object[] {"##", "##", '#', Blocks.stone});   
        addRecipe(new ItemStack(Blocks.sandstone, 4, 1), new Object[] {"##", "##", '#', new ItemStack(Blocks.sandstone, 1, 0)});
        addRecipe(new ItemStack(Blocks.sandstone, 4, 2), new Object[] {"##", "##", '#', new ItemStack(Blocks.sandstone, 1, 1)});
        addRecipe(new ItemStack(Main.blockChalk, 4, 1), new Object[] {"##", "##", '#', new ItemStack(Main.blockChalk, 1, 0)});
        addRecipe(new ItemStack(Blocks.nether_brick, 1), new Object[] {"##", "##", '#', new ItemStack(Items.netherbrick)});

        // Adding the slab recipe's.
        addRecipe(new ItemStack(Blocks.stone_slab, 6, 3), new Object[] {"###", '#', Blocks.cobblestone});
        addRecipe(new ItemStack(Blocks.stone_slab, 6, 0), new Object[] {"###", '#', Blocks.stone});
        addRecipe(new ItemStack(Blocks.stone_slab, 6, 1), new Object[] {"###", '#', Blocks.sandstone});
        addRecipe(new ItemStack(Blocks.stone_slab, 6, 4), new Object[] {"###", '#', Blocks.brick_block});
        addRecipe(new ItemStack(Blocks.stone_slab, 6, 5), new Object[] {"###", '#', Blocks.stonebrick});
        addRecipe(new ItemStack(Blocks.stone_slab, 6, 6), new Object[] {"###", '#', Blocks.nether_brick});
        addRecipe(new ItemStack(Blocks.stone_slab, 6, 7), new Object[] {"###", '#', Blocks.quartz_block});

        // Adding the stair recipe's.
        addRecipe(new ItemStack(Blocks.stone_stairs, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.cobblestone});
        addRecipe(new ItemStack(Blocks.brick_stairs, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.brick_block});
        addRecipe(new ItemStack(Blocks.stone_brick_stairs, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.stonebrick});
        addRecipe(new ItemStack(Blocks.nether_brick_stairs, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.nether_brick});
        addRecipe(new ItemStack(Blocks.sandstone_stairs, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.sandstone});
        addRecipe(new ItemStack(Blocks.quartz_stairs, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.quartz_block});

        // Adding the wall recipe's.
        addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, 0), new Object[] {"###", "###", '#', Blocks.cobblestone});
        addRecipe(new ItemStack(Blocks.cobblestone_wall, 6, 1), new Object[] {"###", "###", '#', Blocks.mossy_cobblestone});

        // Giving the sorter the recipe list.
        Collections.sort(recipes, new StoneCutterRecipeSorter(this));
    }

    /**
     * Adds a shaped recipe to the recipe list.
     * 
     * @param itemStack     The item that should be crafted.
     * @param arrayOfObject The crafting recipe.
     * 
     * @return StoneCutterShapedRecipes
     */
    public StoneCutterShapedRecipes addRecipe(ItemStack itemStack, Object ... arrayOfObject)
    {
        String var1 = "";
        
        int var2 = 0;
        int var3 = 0;
        int var4 = 0;

        if(arrayOfObject[var2] instanceof String[])
        {
            String[] recipeArray = (String[])((String[])arrayOfObject[var2++]);

            for(int i = 0; i < recipeArray.length; ++i)
            {
                String var5 = recipeArray[i];
                ++var4;
                var3 = var5.length();
                var1 = var1 + var5;
            }
        }
        
        else
        {
            while(arrayOfObject[var2] instanceof String)
            {
                String var6 = (String)arrayOfObject[var2++];
                ++var4;
                var3 = var6.length();
                var1 = var1 + var6;
            }
        }

        HashMap<Character, ItemStack> map;

        for(map = new HashMap<Character, ItemStack>(); var2 < arrayOfObject.length; var2 += 2)
        {
            Character character = (Character)arrayOfObject[var2];
            ItemStack var7 = null;

            if(arrayOfObject[var2 + 1] instanceof Item)
                var7 = new ItemStack((Item)arrayOfObject[var2 + 1]);
            
            else if(arrayOfObject[var2 + 1] instanceof Block)
                var7 = new ItemStack((Block)arrayOfObject[var2 + 1], 1, 32767);
            
            else if(arrayOfObject[var2 + 1] instanceof ItemStack)
                var7 = (ItemStack)arrayOfObject[var2 + 1];

            map.put(character, var7);
        }

        ItemStack[] var8 = new ItemStack[var3 * var4];

        for(int i = 0; i < var3 * var4; ++i)
        {
            char var9 = var1.charAt(i);

            if (map.containsKey(Character.valueOf(var9)))
                var8[i] = ((ItemStack)map.get(Character.valueOf(var9))).copy();
            
            else
                var8[i] = null;
        }

        StoneCutterShapedRecipes shapedrecipes = new StoneCutterShapedRecipes(var3, var4, var8, itemStack);
        recipes.add(shapedrecipes);
      
        return shapedrecipes;
    }

    /**
     * Adds a shapeless recipe to the recipe list.
     * 
     * @param itemStack     The item that should be crafted.
     * @param arrayOfObject The crafting recipe.
     */
    public void addShapelessRecipe(ItemStack itemStack, Object ... arrayOfObject)
    {
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        Object[] aobject = arrayOfObject;
        
        int var1 = arrayOfObject.length;

        for(int i = 0; i < var1; ++i)
        {
            Object var2 = aobject[i];

            if(var2 instanceof ItemStack)
                arraylist.add(((ItemStack)var2).copy());
            
            else if(var2 instanceof Item)
                arraylist.add(new ItemStack((Item)var2));
            
            else
            {
                if(!(var2 instanceof Block))
                    throw new RuntimeException("Invalid shapeless recipe!");

                arraylist.add(new ItemStack((Block)var2));
            }
        }

        recipes.add(new ShapelessRecipes(itemStack, arraylist));
    }
    
    /**
     * Finds the correct recipe based on the container input.
     * 
     * @param inventoryCrafting Standard class for crafting recipe's.
     * @param world             The world object.
     * 
     * @return ItemStack
     */
    public ItemStack findMatchingRecipe(InventoryCrafting inventoryCrafting, World world)
    {
        int var1 = 0;
        int var2;

        ItemStack var3 = null;
        ItemStack var4 = null;
        
        for(var2 = 0; var2 < inventoryCrafting.getSizeInventory(); ++var2)
        {
            ItemStack var5 = inventoryCrafting.getStackInSlot(var2);

            if (var5 != null)
            {
                if (var1 == 0)
                    var3 = var5;

                if (var1 == 1)
                    var4 = var5;

                ++var1;
            }
        }

        if(var1 == 2 && var3.getItem() == var4.getItem() && var3.stackSize == 1 && var4.stackSize == 1 && var3.getItem().isRepairable())
        {
            Item var6 = var3.getItem();
           
            int var7 = var6.getMaxDamage() - var3.getItemDamageForDisplay();
            int var8 = var6.getMaxDamage() - var4.getItemDamageForDisplay();
            int var9 = var7 + var8 + var6.getMaxDamage() * 5 / 100;
            int var10 = var6.getMaxDamage() - var9;

            if(var10 < 0)
                var10 = 0;

            return new ItemStack(var3.getItem(), 1, var10);
        }
        
        else
        {
            for(var2 = 0; var2 < recipes.size(); ++var2)
            {
                IRecipe var11 = (IRecipe)recipes.get(var2);

                if(var11.matches(inventoryCrafting, world))
                    return var11.getCraftingResult(inventoryCrafting);
            }

            return null;
        }
    }

    /**
     * Returns a list of all the recipes.
     * 
     * @return List<IRecipe>
     */
    public List<IRecipe> getRecipeList()
    {
        return recipes;
    }
}