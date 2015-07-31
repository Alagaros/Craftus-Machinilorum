package com.dalthow.machinilorum.container;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.block.stonecutter.StoneCutterRecipeManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class ContainerStoneCutter.java
 * 
 **/

public class ContainerStoneCutter extends Container 
{
	// Declaration of the World object.
	
	private World world;
	
	
	// Declaration of the position the block is at.
	
	private int xPos;
	private int yPos;
	private int zPos;

	
	// Declaring some other variables.
	
	public InventoryCrafting craftMatrix;
	public IInventory craftResult;
	
	
	// Constructor that fills the local value's with the one's provided in the parameters.
	
	public ContainerStoneCutter(InventoryPlayer inventoryPlayer, World world, int xPos, int yPos, int zPos) 
	{
		craftMatrix = new InventoryCrafting(this, 5, 5);
		craftResult = new InventoryCraftResult();
		
		this.world = world;
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;

		addSlotToContainer(new SlotCrafting(inventoryPlayer.player, craftMatrix, craftResult, 0, 141, 43));

		
		// Creating a 5 by 5 crafting grid.
		
		for (int i = 0; i < 5; i++) 
		{
			for(int j = 0; j < 5; j++) 
			{
				addSlotToContainer(new Slot(craftMatrix, j + i * 5, 8 + j * 18, 8 + i * 18));
			}
		}
		
		
		// Looping trough the inventory slots.
		
		for (int i = 0; i < 3; i++) 
		{
			for(int j = 0; j < 9; j++) 
			{
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 102 + i * 18));
			}
		}
		
		
		// Looping trough the hotbar.

		for (int i = 0; i < 9; i++) 
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 160));
		}

		onCraftMatrixChanged(craftMatrix);
	}


	// Callback for when the crafting matrix is changed.
	
	public void onCraftMatrixChanged(IInventory iiventory) 
	{
		craftResult.setInventorySlotContents(0, StoneCutterRecipeManager.getInstance().findMatchingRecipe(craftMatrix, world));
	}

	
	// Checks if the player is in range of the container.

	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		if(world.getBlock(xPos, yPos, zPos) != Main.blockStoneCutter)
		{
			return false;
		}
		
		else
		{
			return player.getDistanceSq((double)xPos + 0.5D, (double)yPos + 0.5D, (double)zPos + 0.5D) <= 64.0D;
		}
	}

	
	// Called when the container is closed.
	
	public void onContainerClosed(EntityPlayer par1EntityPlayer) 
	{
        super.onContainerClosed(par1EntityPlayer);

        if(!world.isRemote)
        {
            for(int i = 0; i < 25; ++i)
            {
                ItemStack var1 = craftMatrix.getStackInSlotOnClosing(i);

                if(var1 != null)
                {
                    par1EntityPlayer.dropPlayerItemWithRandomChoice(var1, false);
                }
            }
        }
    }

	
	// Allows shift clicking from the players inventory into the other slots.
	
	public ItemStack transferStackInSlot(EntityPlayer player, int par1) 
	{
        ItemStack var1 = null;
        Slot var2 = (Slot)inventorySlots.get(par1);

        if(var2 != null && var2.getHasStack())
        {
            ItemStack var3 = var2.getStack();
            var1 = var3.copy();

            if(par1 == 0)
            {
                if(!mergeItemStack(var3, 10, 46, true))
                {
                    return null;
                }

                var2.onSlotChange(var3, var1);
            }
            
            else if(par1 >= 10 && par1 < 37)
            {
                if(!mergeItemStack(var3, 37, 46, false))
                {
                    return null;
                }
            }
            
            else if(par1 >= 37 && par1 < 46)
            {
                if(!mergeItemStack(var3, 10, 37, false))
                {
                    return null;
                }
            }
            
            else if(!mergeItemStack(var3, 10, 46, false))
            {
                return null;
            }

            if(var3.stackSize == 0)
            {
                var2.putStack((ItemStack)null);
            }
            
            else
            {
                var2.onSlotChanged();
            }

            if(var3.stackSize == var1.stackSize)
            {
                return null;
            }

            var2.onPickupFromSlot(player, var3);
        }

        return var1;
    }
}