package com.dalthow.machinilorum.block.fragmentizer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class FragmentingHandler.java
 * 
 **/

public class FragmentingHandler
{
	// Declaration for data lists.
	
    private Map<ItemStack, ItemStack> fragmentingList = new HashMap<ItemStack, ItemStack>();
    private Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

    
    // Creating a new instance.
    
    private static final FragmentingHandler fragmentingBase = new FragmentingHandler();

    
    // Constructors.
    
    public static FragmentingHandler fragmenting()
    {
    	// Returning the new instance.
    	
        return fragmentingBase;
    }
    
    private FragmentingHandler()
    {
    	// Adding the block recipe's.
    	
        addBlock(Blocks.stone, new ItemStack(Blocks.cobblestone), 0.5F);
        addBlock(Blocks.cobblestone, new ItemStack(Blocks.sand), 0.5F);
        addBlock(Blocks.glass, new ItemStack(Blocks.sand), 0.5F);
        addBlock(Blocks.stained_glass, new ItemStack(Blocks.sand), 0.5F);
        addBlock(Blocks.quartz_block, new ItemStack(Items.quartz, 4), 0.5F);
        addBlock(Blocks.brick_block, new ItemStack(Items.brick, 4), 0.5F);
        addBlock(Blocks.ice, new ItemStack(Items.snowball, 1), 0.5F);
        addBlock(Blocks.wool, new ItemStack(Items.string, 4), 0.5F);
        addBlock(Blocks.glowstone, new ItemStack(Items.glowstone_dust, 4), 0.5F);
        addBlock(Blocks.gravel, new ItemStack(Items.flint, 1), 0.5F);
     
        
        // Adding the item recipe's
        
        addItem(Items.reeds, new ItemStack(Items.sugar, 3), 0.5F);
        addItem(Items.bone, new ItemStack(Items.dye, 6, 15), 0.5F);
        addItem(Items.blaze_rod, new ItemStack(Items.blaze_powder, 4), 0.5F);
    }

    
    // Adds a block to the recipe list.
    
    public void addBlock(Block block, ItemStack itemStack, float experience)
    {
        addItem(Item.getItemFromBlock(block), itemStack, experience);
    }

    
    // Adds a item to the recipe list.
    
    public void addItem(Item item, ItemStack itemStack, float experience)
    {
        putItemInList(new ItemStack(item, 1, 32767), itemStack, experience);
    }

    
    // The method used to put objects in the list.
    
    public void putItemInList(ItemStack inputStack, ItemStack outputStack, float experience)
    {
        fragmentingList.put(inputStack, outputStack);
        experienceList.put(outputStack, Float.valueOf(experience));
    }

    
    // Returns the recipes from the list.
    
    public ItemStack getFragmentingResult(ItemStack itemStack)
    {
        Iterator<?> iterator = fragmentingList.entrySet().iterator();
        Entry<?, ?> entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry<?, ?>)iterator.next();
        }
        
        while(!inputEqualToOutput(itemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }
    
    
    // Checks if the input is equal to the output.

    private boolean inputEqualToOutput(ItemStack inputStack, ItemStack outputStack)
    {
        return outputStack.getItem() == inputStack.getItem() && (outputStack.getItemDamage() == 32767 || outputStack.getItemDamage() == inputStack.getItemDamage());
    }

    
    // Returns the fragmenting list.
    
    public Map<ItemStack, ItemStack> getFragmentingList()
    {
        return fragmentingList;
    }

    
    // Returns the amount you get from fragmenting something.
    
    public float getExperienceFromItemStack(ItemStack itemStack)
    {
        float var1 = itemStack.getItem().getSmeltingExperience(itemStack);
       
        if(var1 != -1)
    	{
        	return var1;
    	}

        Iterator<?> iterator = experienceList.entrySet().iterator();
        Entry<?, ?> entry;

        do
        {
            if(!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry<?, ?>)iterator.next();
        }
        
        while(!inputEqualToOutput(itemStack, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}