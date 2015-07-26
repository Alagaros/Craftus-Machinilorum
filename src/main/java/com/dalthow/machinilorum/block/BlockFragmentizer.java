/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios
 * @Class BlockFragmentizer.java 
 * 
 **/

package com.dalthow.machinilorum.block;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;
import com.dalthow.machinilorum.tile.TileEntityFragmentizer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFragmentizer extends BlockContainer
{
	// Constructor
	
	public BlockFragmentizer() 
	{
		super(Material.rock);

		setCreativeTab(Main.tabMachinilorumMachines);
		setBlockTextureName(Reference.modId + ":" + "fragmentizer/");
		setHardness(2.5F);
		setHarvestLevel("pickaxe", 1);
	}

	
	// Only make the texture variables on the client side so the server ignores this 
	
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;

	
	// Determines the textures displayed on the blocks based on the side and meta data also gets ignored by the server
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? Main.blockStoneCutter.getBlockTextureFromSide(side) : side == 0 ? Blocks.furnace.getBlockTextureFromSide(side) : (side == 3 ? frontIcon : Blocks.furnace.getBlockTextureFromSide(side));
	}
	
	
	// Loads the different textures also gets ignored by the server
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry)
	{
		frontIcon = registry.registerIcon(getTextureName() + "front");
	}
	
	
	// If a redstone wire is next to this block it connects
	
	@Override
	public boolean canConnectRedstone(IBlockAccess access, int xPos, int yPos, int zPos, int par1)
	{
	    return true;
	}
	
	
	// Tells the game that a comparator can be used on this block
	
	public boolean hasComparatorInputOverride()
    {
        return true;
    }
	
	
	// Tells the game how the comparator can be used on this block
	
    public int getComparatorInputOverride(World world, int xPos, int yPos, int zPos, int par1)
    {
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(xPos, yPos, zPos));
    }
	    
	
	// Checks if there is a hopper on top and a lever on the side, then it opens the user interface 
    
    @Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int par1, float par2, float par3, float par4)  
    {
		if(world.getBlock(xPos, yPos + 1, zPos) == Blocks.hopper)
		{
			if(!world.isRemote)
	    	{
				FMLNetworkHandler.openGui(player, Main.instance, 0, world, xPos, yPos, zPos);
				
				return true;
	    	}
			
			else
			{
				return false;
			}
		}
		
		else
		{
			return false;
		}
    }
   
    
	// Creates a tile entity when you place down this block
	
	public TileEntity createNewTileEntity(World world, int par1) 
	{
		TileEntityFragmentizer tile = new TileEntityFragmentizer(); 
        
		return tile;
	}
	
	
	// When the player breaks the block
	
	public void breakBlock(World world, int xPos, int yPos, int zPos, Block block, int meta) 
	{
		TileEntityFragmentizer tile = (TileEntityFragmentizer)world.getTileEntity(xPos, yPos, zPos);
	
		if(tile != null) 
		{
			for(int i = 0; i < tile.getSizeInventory(); i++) 
			{
				ItemStack itemStack = tile.getStackInSlot(i);
			
				if(itemStack != null) 
				{
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
				
					while(itemStack.stackSize > 0) 
					{
						int j = world.rand.nextInt(21) + 10;
					
						if(j > itemStack.stackSize) 
						{
							j = itemStack.stackSize;
						}
					
						itemStack.stackSize -= j;
					
						EntityItem item = new EntityItem(world, (double)((float)xPos + f), (double)((float)yPos + f1), (double)((float)zPos + f2), new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
					
						if(itemStack.hasTagCompound()) 
						{
							item.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
						}
					
						world.spawnEntityInWorld(item);
					}
				}
			}
			
			world.func_147453_f(xPos, yPos, zPos, block);
		}
		
		super.breakBlock(world, xPos, yPos, zPos, block, meta);
	}
}