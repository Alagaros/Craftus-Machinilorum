package com.dalthow.machinilorum.container;

import com.dalthow.machinilorum.block.fragmentizer.FragmentingHandler;
import com.dalthow.machinilorum.tile.TileEntityFragmentizer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class ContainerFragmentizer extends Container
{
	// Declaration of the TileEntity.
	private TileEntityFragmentizer tile;

	// Declaring some other variables.
	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;
	public int lastHeat;

	// Constructor that sets the local TileEntity and InventoryPlayer to the one's that are provide in the parameters.
	public ContainerFragmentizer(InventoryPlayer inventory, TileEntityFragmentizer tile) 
	{
		this.tile = tile;

		addSlotToContainer(new Slot(tile, 0, 56, 17));
		addSlotToContainer(new Slot(tile, 1, 56, 53));
		addSlotToContainer(new SlotFurnace(inventory.player, tile, 2, 116, 35));

		// Looping trough the inventory slots.
		for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		// Looping trough the hotbar.
		for(int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}


	// Tells the container to keep track of certain values in the tile entity.
	public void addCraftingToCrafters (ICrafting iCrafting) 
	{
		super.addCraftingToCrafters(iCrafting);
		
		iCrafting.sendProgressBarUpdate(this, 0, tile.cookTime);
		iCrafting.sendProgressBarUpdate(this, 1, tile.burnTime);
		iCrafting.sendProgressBarUpdate(this, 2, tile.currentItemBurnTime);
	}

	// Detects and send changes to the gui from the tile entity.
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < crafters.size(); i++) 
		{
			ICrafting var1 = (ICrafting) crafters.get(i);

			if(lastCookTime != tile.cookTime)
                var1.sendProgressBarUpdate(this, 0, tile.cookTime);

			if(lastBurnTime != tile.burnTime)
                var1.sendProgressBarUpdate(this, 1, tile.burnTime);

			if(lastCurrentItemBurnTime != tile.currentItemBurnTime)
                var1.sendProgressBarUpdate(this, 2, tile.currentItemBurnTime);
		}

		lastCookTime = tile.cookTime;
		lastBurnTime = tile.burnTime;
		lastCurrentItemBurnTime = tile.currentItemBurnTime;
	}

	// Updates the progress bar in the interface.
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if(par1 == 0)
            tile.cookTime = par2;

        if(par1 == 1)
            tile.burnTime = par2;

        if(par1 == 2)
            tile.currentItemBurnTime = par2;
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

            if(par1 == 2) 
            {
                if(!mergeItemStack(var3, 3, 39, true))
                    return null;

                var2.onSlotChange(var3, var1);
            }
            
            else if(par1 != 1 && par1 != 0) 
            {
                if(FragmentingHandler.fragmenting().getFragmentingResult(var3) != null)
                    if(!mergeItemStack(var3, 0, 1, false))
                        return null;
                
                else if(TileEntityFragmentizer.isItemFuel(var3))
                    if(!mergeItemStack(var3, 1, 2, false))
                        return null;
                
                else if(par1 >= 3 && par1 < 30)
                    if(!mergeItemStack(var3, 30, 39, false))
                        return null;
                
                else if(par1 >= 30 && par1 < 39 && !mergeItemStack(var3, 3, 30, false))
                    return null;
            } 
            
            else if(!mergeItemStack(var3, 3, 39, false))
                return null;

            if(var3.stackSize == 0)
                var2.putStack((ItemStack)null);
            
            else
                var2.onSlotChanged();

            if(var3.stackSize == var1.stackSize)
                return null;

            var2.onPickupFromSlot(player, var3);
        }

        return var1;
    }

	// Makes the container able to interact with the player.
	public boolean canInteractWith(EntityPlayer player) 
	{
		return true;
	}
}