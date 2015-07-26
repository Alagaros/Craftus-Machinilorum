/**
 * Craftus Machinilorum
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class BlockStoneCutter.java
 * 
 **/

package com.dalthow.machinilorum.block;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStoneCutter extends Block 
{
	// Constructor
	
	public BlockStoneCutter()
	{
		super(Material.rock);
		
		setCreativeTab(Main.tabMachinilorumMachines);
		setBlockTextureName(Reference.modId + ":" + "stonecutter/");
		setHardness(2.5F);
		setHarvestLevel("pickaxe", 1);
	}
	
	
	// Only make the texture variables on the client side so the server ignores this 
	
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;
	
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	
	
	// Determines the textures displayed on the blocks based on the side and meta data also gets ignored by the server
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? topIcon : side == 0 ? Blocks.furnace.getBlockTextureFromSide(side) : (side == 2 || side == 3 ? frontIcon : Blocks.furnace.getBlockTextureFromSide(side));
	}
	
	
	// Loads the different textures also gets ignored by the server
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry)
	{
		topIcon = registry.registerIcon(getTextureName() + "top");
		frontIcon = registry.registerIcon(getTextureName() + "front");
	}
	
	
	// Opens the user interface 
    
    @Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int par1, float par2, float par3, float par4)  
    {
    	if(!world.isRemote)
    	{
	    	player.openGui(Main.instance, 1, world, xPos, yPos, zPos);
	    	
			return true;
    	}
    	
    	else
    	{
    		return false;
    	}
    }
}
