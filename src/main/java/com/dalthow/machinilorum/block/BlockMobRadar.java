package com.dalthow.machinilorum.block;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class BlockMobRadar extends Block implements ITileEntityProvider
{
	// Constructor that adds data to the block.
	public BlockMobRadar()
	{
		super(Material.rock);
		
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.928F, 1.0F);
		setCreativeTab(Main.tabMachinilorumMachines);
		setHardness(2.5F);
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

	public boolean canProvidePower()
    {
        return true;
    }
	
	public int isProvidingWeakPower(IBlockAccess access, int xPos, int yPos, int zPos, int par1)
	{
		TileEntityMobRadar tile = (TileEntityMobRadar)access.getTileEntity(xPos, yPos, zPos);
		 
		return tile.signalStrength;
	}

	// Opens the user interface.
	@Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int par1, float par2, float par3, float par4)  
    {
		if(!world.isRemote)
    	{
			FMLNetworkHandler.openGui(player, Main.instance, 2, world, xPos, yPos, zPos);
			
			return true;
    	}
		
		else
		{
			return false;
		}
    }

	// Creates a TileEntity when you place it down.
	public TileEntity createNewTileEntity(World World, int par1) 
	{
		TileEntityMobRadar tile = new TileEntityMobRadar(); 
        
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
}
