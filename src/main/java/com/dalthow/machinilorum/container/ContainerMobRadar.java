package com.dalthow.machinilorum.container;

import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class ContainerMobRadar extends Container 
{
	// Declaration of the TileEntity.
	private TileEntityMobRadar tile;

	// Declaring some other variables.
	public int lastMobId;
	public int lastRadius;
	public int lastSignal;


	// Constructor that sets the local TileEntity to the one that is provided as parameter.
	public ContainerMobRadar(TileEntityMobRadar tile) 
	{
		this.tile = tile;
	}
	
	
	// Detects and send changes to the gui from the tile entity.
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < crafters.size(); i++) 
		{
			ICrafting var1 = (ICrafting) crafters.get(i);

			if(lastMobId != tile.mobId) 
			{
				var1.sendProgressBarUpdate(this, 0, tile.mobId);
			}
			
			if(lastRadius != tile.radius) 
			{
				var1.sendProgressBarUpdate(this, 1, tile.radius);
			}
			
			if(lastSignal != tile.signal) 
			{
				var1.sendProgressBarUpdate(this, 2, tile.signal);
			}
		}

		lastMobId = tile.mobId;
		lastRadius = tile.radius;
		lastSignal = tile.signal;
	}

	// Updates the progress bar in the interface.
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if(par1 == 0) 
        {
            tile.mobId = par2;
        }
        
        if(par1 == 1) 
        {
            tile.radius = par2;
        }

        if(par1 == 2) 
        {
            tile.signal = par2;
        }
    }

	// Makes the container able to interact with the player.
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return true;
	}
}