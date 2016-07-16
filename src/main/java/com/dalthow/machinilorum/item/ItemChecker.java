package com.dalthow.machinilorum.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.dalthow.machinilorum.base.Main;
import com.dalthow.machinilorum.base.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class ItemChecker extends Item 
{
	// Constructor.
	public ItemChecker()
	{
		super();
		setUnlocalizedName("checker");
		setTextureName(Reference.modId + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(Main.tabMachinilorumTools);
		setMaxStackSize(16);
	}
	
	
	// Gets triggered when you use the item on a block.
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xPos, int yPos, int zPos, int par1, float par2, float par3, float par4)
	{
		if(!world.isRemote)
		{
			int metadata;
			
			if(world.getTileEntity(xPos, yPos, zPos) != null)
				metadata = world.getTileEntity(xPos, yPos, zPos).getBlockMetadata();
			
			else
				metadata = world.getBlockMetadata(xPos, yPos, zPos);
			
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Name:" + " " + world.getBlock(xPos, yPos, zPos).getUnlocalizedName().substring(5)));
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "Metadata:" + " " + metadata));
		}
		
		return true;
	}
	
	// Adds a tool-tip to the item.
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean isValid)
    {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.clear();
		
			list.add(I18n.format("item.checker.name"));
			list.add("Used to get the meta data of blocks,");
			list.add("Used for debugging only.");
		}
    }
}
