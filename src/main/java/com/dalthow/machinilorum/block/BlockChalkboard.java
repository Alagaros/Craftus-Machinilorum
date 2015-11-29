package com.dalthow.machinilorum.block;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.tile.TileEntityChalkboard;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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

public class BlockChalkboard extends Block implements ITileEntityProvider
{
	// Constructor that adds data to the block.
	public BlockChalkboard()
	{
		super(Material.wood);
		
		setCreativeTab(Main.tabMachinilorumMachines);
		setHardness(2.5F);
		setHarvestLevel("axe", 1);
	}
	
	
	// Sets the block bounds based on the meta data.
	public void setBlockBoundsBasedOnState(IBlockAccess access, int xPos, int yPos, int zPos)
    {
		int meta = access.getBlockMetadata(xPos, yPos, zPos);
		
		if(meta == 0)
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.12F, 1.0F,  1.0F);
		}
		
		if (meta == 1)
		{
		    setBlockBounds(0.0F, 0.0F, 1.0F - 0.12F,  1.0F, 1.0F, 1.0F);
		}
		
		if (meta == 2)
		{
			setBlockBounds(1.0F - 0.12F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		
		if (meta == 3)
		{
		    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.12F);
		}
    }

	// Determines the textures displayed on the blocks based on the side and meta data also gets ignored by the server.
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return Blocks.planks.getBlockTextureFromSide(side);
	}

	// Creates a tile entity when you place it down.
	public TileEntity createNewTileEntity(World world, int par1) 
	{
		TileEntityChalkboard tile = new TileEntityChalkboard(); 
        
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

	// Opens the user interface when the player uses this block.
	@Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int par1, float par2, float par3, float par4)  
    {
		// Obtaining the tile entity.
		TileEntityChalkboard tile = (TileEntityChalkboard)world.getTileEntity(xPos, yPos, zPos); 

		// Clearing the chalk board if the player is sneaking.
		if(player.isSneaking())
		{
			clearChalkBoard(tile);
			
			return true;
		}

		// Rotating it if the user has an empty hand.
		else if(player.getHeldItem() == null)
		{
			if(world.getBlockMetadata(xPos, yPos, zPos) < 3)
			{
				world.setBlockMetadataWithNotify(xPos, yPos, zPos, (world.getBlockMetadata(xPos, yPos, zPos) + 1), 1);
			}
			
			else
			{
				world.setBlockMetadataWithNotify(xPos, yPos, zPos, 0, 1);
			}
			
			clearChalkBoard(tile);
		}

		// Drawing a line on the chalk board.
		else if(player.getHeldItem().getItem() == Main.blockChalk.getItem(world, xPos, yPos, zPos))
		{
			if(tile.startDefined == false)
			{
				tile.startPosX = par2;
				tile.startPosY = par3;
				tile.startPosZ = par4;
				
				tile.startDefined = true;
			}
	
			else
			{
				tile.endPosX = par2;
				tile.endPosY = par3;
				tile.endPosZ = par4;
				
				tile.startDefined = false;

				// Checking if the player is in creative mode, if not consume a piece of chalk.
				if(!player.capabilities.isCreativeMode)
				{
					player.inventory.consumeInventoryItem(new ItemStack(Main.blockChalk).getItem());
				}
			}
				
			return true;
		}
		
		return false;
    }
	
	
	/**
     * Adds custom recipe's to the game if they are allowed to be crafted.
     *
     * @param tile The tile entity that is linked to the block that should be cleared.
     */
	private void clearChalkBoard(TileEntityChalkboard tile)
	{
		tile.startPosX = 0;
		tile.startPosY = 0;
		tile.startPosZ = 0;
		
		tile.endPosX = 0;
		tile.endPosY = 0;
		tile.endPosZ = 0;
		
		tile.startDefined = false;
	}
}