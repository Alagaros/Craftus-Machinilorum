package com.dalthow.machinilorum.block;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.tile.TileEntitySprinkler;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class BlockSprinkler extends Block implements ITileEntityProvider
{
	// Constructor that adds data to the block.
	public BlockSprinkler()
	{
		super(Material.rock);
		
		setCreativeTab(Main.tabMachinilorumMachines);
		setHardness(2.5F);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.78F, 1.0F);
		setHarvestLevel("pickaxe", 1);
	}

	// Determines the textures displayed on the blocks based on the side and meta data also gets ignored by the server.
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? Blocks.furnace.getBlockTextureFromSide(side) : side == 0 ? Blocks.furnace.getBlockTextureFromSide(side) : Blocks.furnace.getBlockTextureFromSide(side);
	}
	
	// If a redstone wire is next to this block it connects.
	@Override
	public boolean canConnectRedstone(IBlockAccess access, int xPos, int yPos, int zPos, int par1)
	{
	    return true;
	}

	// Creates a TileEntity when you place it down.
	public TileEntity createNewTileEntity(World world, int par1) 
	{
		TileEntitySprinkler tile = new TileEntitySprinkler(); 
        
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

	// Tells the game that a comparator can be used on this block.
	public boolean hasComparatorInputOverride()
    {
        return true;
    }

	// Tells the game how the comparator can be used on this block.
    public int getComparatorInputOverride(World world, int xPos, int yPos, int zPos, int par1)
    {
    	// Getting the TileEntity bound to this block.
    	TileEntity tile = world.getTileEntity(xPos, yPos, zPos);
    	
    	if(!((tile) instanceof TileEntitySprinkler))
			return 0;
    	
    	TileEntitySprinkler tileEntitySprinkler = (TileEntitySprinkler) tile;
    	
        return tileEntitySprinkler.getBlockMetadata();
    }

    // Makes something happen when you click the block.
	public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int par1, float par2, float par3, float par4) 
    {  
		// Rotating the block if its clicked with an empty hand.
    	if(player.getHeldItem() == null) 
        { 
			if(world.getBlockMetadata(xPos, yPos, zPos) < 3)
				world.setBlockMetadataWithNotify(xPos, yPos, zPos, (world.getBlockMetadata(xPos, yPos, zPos) + 1), 1);
			
			else
				world.setBlockMetadataWithNotify(xPos, yPos, zPos, 0, 1);
			
			return true;
        }
		
		return false;
    }
}
