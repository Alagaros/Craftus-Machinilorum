package com.dalthow.machinilorum.block;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.tile.TileEntityEggIncubator;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios 
 * @class BlockEggIncubator.java
 * 
 **/

public class BlockEggIncubator extends Block implements ITileEntityProvider
{
	// Constructor that adds data to the block.
	
	public BlockEggIncubator()
	{
		super(Material.rock);
		
		setCreativeTab(Main.tabMachinilorumMachines);
		setHardness(2.5F);
		setHarvestLevel("pickaxe", 1);
	}
	
	
	// Determines the textures displayed on the blocks based on the side and meta data also gets ignored by the server.
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? Blocks.glass.getBlockTextureFromSide(side) : side == 0 ? Blocks.furnace.getBlockTextureFromSide(side) : Blocks.furnace.getBlockTextureFromSide(side);
	}
	
	
	// Creates a tile entity when you place it down.
	
	public TileEntity createNewTileEntity(World world, int par1) 
	{
		TileEntityEggIncubator tile = new TileEntityEggIncubator(); 
        
		return tile;
	}
	
	
	// Makes you not see trough the world because the block isn't full.
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	
	// Makes the block render as a custom block.
	
	@Override
    public int getRenderType()
    {
        return -1;
    }

	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	
	// Makes something happen when you click the block.
	
    @Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int par1, float par2, float par3, float par4) 
    {  
        TileEntityEggIncubator tile = (TileEntityEggIncubator)world.getTileEntity(xPos, yPos, zPos); 
        
        
        // Rotating the block if its clicked with an empty hand.
        
        if(player.getHeldItem() == null) 
        { 
        	if(world.getBlockMetadata(xPos, yPos, zPos) < 5)
    		{
    			world.setBlockMetadataWithNotify(xPos, yPos, zPos, (world.getBlockMetadata(xPos, yPos, zPos) + 1), 1);
    		}
    		
    		else
    		{
    			world.setBlockMetadataWithNotify(xPos, yPos, zPos, 0, 1);
    		}
        	
            return true; 
        }
        
        
        // Speeding up the hatching process if the item is wheat.
        
        if(player.getHeldItem().getItem() == Items.wheat) 
        { 
        	world.spawnParticle("heart", xPos + 0.5F, yPos + 1.2F, zPos + 0.5F, 0, 0, 0);
            
        	if(tile.canPutEggIn == true) 
            { 
                if(!world.isRemote) 
                { 
                	 player.inventory.consumeInventoryItem(Items.wheat); 
                     
                     EntityItem itemWheat = new EntityItem(world, xPos + 0.5, yPos + 0.5, zPos + 0.5, new ItemStack(Items.wheat)); 
                     
                     world.spawnEntityInWorld(itemWheat); 
                }
            }
            
            else
            {
            	tile.tickToHatch -= (100 + world.rand.nextInt(50));
            	
            	player.inventory.consumeInventoryItem(Items.wheat); 
            }
            
          	return true;
        }
        
        
        // Inserting or resetting if the item is an egg.
        
        if(player.getHeldItem().getItem() == Items.egg) 
        { 
            if(tile.canPutEggIn == false) 
            { 
                if(!world.isRemote) 
                { 
                    player.inventory.consumeInventoryItem(Items.egg); 
      
                    EntityItem itemEgg = new EntityItem(world, xPos + 0.5, yPos + 0.5, zPos + 0.5, new ItemStack(Items.egg)); 
                    world.spawnEntityInWorld(itemEgg); 
                } 
                
                tile.tickToHatch = 500 + world.rand.nextInt(250); 
            } 
              
            else
            { 
                if(!world.isRemote) 
                { 
                	player.inventory.consumeInventoryItem(Items.egg); 
                     
                    tile.canPutEggIn = false; 
                    tile.tickToHatch = 500 + world.rand.nextInt(250); 
                    
                    world.markBlockForUpdate(xPos, yPos, zPos);  
                } 
            }  
              
            return true; 
        }
        
        return false; 
    } 
}