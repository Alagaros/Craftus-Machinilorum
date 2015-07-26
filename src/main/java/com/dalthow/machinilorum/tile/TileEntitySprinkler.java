/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class TileEntitySprinkler.java
 * 
 **/

package com.dalthow.machinilorum.tile;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySprinkler extends TileEntity
{
	// Declaration
	
	private boolean hasPulsed;
	
	
	// Validates the tile entity
	
	@Override
	public void validate()
	{
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, Main.blockSprinkler, 0, 0);
	}
	
	
	// Gets triggered 20 times every second
	
	public void updateEntity() 
	{
		if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
			if(hasPulsed == false)
			{
				if(this.blockMetadata < 3)
				{
					this.blockMetadata++;
				}
				
				else
				{
					this.blockMetadata = 0;
				}
			}
			
			hasPulsed = true;
		}
		
		else
		{
			hasPulsed = false;
		}
		
		switch(getBlockMetadata())
		{
			case 0: for(float i = 0; i < 1; i += 0.05F)
					{
						worldObj.spawnParticle("splash", xCoord + 0.5F, yCoord + 1.35F, zCoord, i, 0, -1.5F); 
						worldObj.spawnParticle("splash", xCoord + 0.5F, yCoord + 1.35F, zCoord, -i, 0, -1.5F); 
					}
			
					for(int i = 0; i < 22; i++)
					{
						for(int j = 0; j < 15; j++)
						{
							if(worldObj.getBlock(xCoord + 10 - i, yCoord - 1, zCoord - 1 - j) == Blocks.farmland && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlockMetadataWithNotify(xCoord + 10 - i, yCoord - 1, zCoord - 1 - j, 2, 0);
							}
							
							if(worldObj.getBlock(xCoord + 10 - i, yCoord, zCoord - 1 - j) == Blocks.fire && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlock(xCoord + 10 - i, yCoord, zCoord - 1 - j, Blocks.air);
							}
						}
					}	
			break;
			
			case 1: for(float i = 0; i < 1; i += 0.05F)
					{
						worldObj.spawnParticle("splash", xCoord + 1.0F, yCoord + 1.35F, zCoord + 0.2F, 1.5F, 0, i); 
						worldObj.spawnParticle("splash", xCoord + 1.0F, yCoord + 1.35F, zCoord + 0.2F, 1.5F, 0, -i); 
					}
			
					for(int i = 0; i < 15; i++)
					{
						for(int j = 1; j < 22; j++)
						{
							if(worldObj.getBlock(xCoord + i, yCoord - 1, zCoord - 11 + j) == Blocks.farmland && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlockMetadataWithNotify(xCoord + i, yCoord - 1, zCoord - 11 + j, 2, 0);
							}
							
							if(worldObj.getBlock(xCoord + i, yCoord, zCoord - 11 + j) == Blocks.fire && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlock(xCoord + i, yCoord, zCoord - 11 + j, Blocks.air);
							}
						}
					}	
			break;
			
			case 2: for(float i = 0; i < 1; i += 0.05F)
					{
						worldObj.spawnParticle("splash", xCoord + 0.5F, yCoord + 1.35F, zCoord + 0.6F, i, 0, 1.5F); 
						worldObj.spawnParticle("splash", xCoord + 0.5F, yCoord + 1.35F, zCoord + 0.6F, -i, 0, 1.5F); 
					}
			
					for(int i = 0; i < 22; i++)
					{
						for(int j = 0; j < 15; j++)
						{
							if(worldObj.getBlock(xCoord + 10 - i, yCoord - 1, zCoord + 1 + j) == Blocks.farmland && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlockMetadataWithNotify(xCoord + 10 - i, yCoord - 1, zCoord + 1 + j, 2, 0);
							}
							
							if(worldObj.getBlock(xCoord + 10 - i, yCoord, zCoord + 1 + j) == Blocks.fire && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlock(xCoord + 10 - i, yCoord, zCoord + 1 + j, Blocks.air);
							}
						}
					}	
			break;
			
			case 3: for(float i = 0; i < 1; i += 0.05F)
					{
						worldObj.spawnParticle("splash", xCoord, yCoord + 1.35F, zCoord + 0.2F, -1.5F, 0, i); 
						worldObj.spawnParticle("splash", xCoord, yCoord + 1.35F, zCoord + 0.2F, -1.5F, 0, -i); 
					}
			
					for(int i = 0; i < 15; i++)
					{
						for(int j = 1; j < 22; j++)
						{
							if(worldObj.getBlock(xCoord - i, yCoord - 1, zCoord - 11 + j) == Blocks.farmland && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlockMetadataWithNotify(xCoord - i, yCoord - 1, zCoord - 11 + j, 2, 0);
							}
							
							if(worldObj.getBlock(xCoord - i, yCoord, zCoord - 11 + j) == Blocks.fire && worldObj.rand.nextInt(15) == 1)
							{
								worldObj.setBlock(xCoord - i, yCoord, zCoord - 11 + j, Blocks.air);
							}
						}
					}
			break;
		}
	}
}