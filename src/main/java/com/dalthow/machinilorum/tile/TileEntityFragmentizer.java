package com.dalthow.machinilorum.tile;

import com.dalthow.machinilorum.block.fragmentizer.FragmentingHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

public class TileEntityFragmentizer extends TileEntity implements ISidedInventory 
{
	private String localizedName;

	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;

	// Declaration of the slots.
	private final int[] slotsTop = new int[]{0};
	private final int[] slotsBottom = new int[]{2, 1};
	private final int[] slotsSide = new int[]{1};

	private ItemStack[] slots = new ItemStack [3];

	// Declaration of the furnace speed.
	public int furnaceSpeed = 100;

	// Gets the display name of the gui.
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? localizedName : "container.fragmentizer";
	}

	// Checks if the display name of the gui is custom
	public boolean hasCustomInventoryName()
	{
		return localizedName != null && localizedName.length() > 0;
	}

	// Returns the amount of slots in the container.
	public int getSizeInventory()
	{
		return slots.length;
	}

	// Returns the current item in a slot.
	public ItemStack getStackInSlot(int par1)
	{
		return slots[par1];
	}

	// Decreases the stack size.
	public ItemStack decrStackSize(int par1, int par2)
	{
		if(slots[par1] != null)
		{
			ItemStack var1;

			if(slots[par1].stackSize <= par2 )
			{
				var1 = slots[par1];
				slots[par1] = null;

				return var1;
			}

			else
			{
				var1 = slots[par1].splitStack(par2);

				if(slots[par1].stackSize == 0)
					slots[par1] = null;

				return var1;
			}
		}

		else
			return null;
	}

	// When some containers are closed they call this on each slot, then drop whatever it returns .
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if(slots[par1]!= null)
		{
			ItemStack var1 = slots[par1];
			slots[par1] = null;

			return var1;
		}

		return null;
	}

	// Sets the slots content
	public void setInventorySlotContents(int var1, ItemStack itemStack)
	{
		slots[var1] = itemStack;

		if(itemStack != null && itemStack.stackSize > getInventoryStackLimit())
			itemStack.stackSize = getInventoryStackLimit();
	}

	// Gets how many items are allowed in the custom slots.
	public int getInventoryStackLimit()
	{
		return 64;
	}

	// Checks if the player is in range of the container.
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
	}

	// Gets called when the inventory gets opened.
	public void openInventory()
	{

	}

	// Gets called when the inventory gets closed.
	public void closeInventory()
	{

	}

	// Checks if the item can fit in the slot with a use.
	public boolean isItemValidForSlot(int var1, ItemStack itemStack)
	{
		return var1 == 2 ? false : (var1 == 1 ? isItemFuel(itemStack) : true);
	}

	// Checks if the item is a fuel.
	public static boolean isItemFuel(ItemStack itemStack)
	{
		return getItemBurnTime(itemStack) > 0;
	}

	// Returns the burn time from a certain item.
	private static int getItemBurnTime(ItemStack itemStack)
	{
		if(itemStack == null)
		{
			return 0;
		}

		else
		{
			Item item = itemStack.getItem();

			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
			{
				Block block = Block.getBlockFromItem(item);

				if(block == Blocks.wooden_slab)
					return 150;

				if(block.getMaterial() == Material.wood)
					return 300;

				if(block == Blocks.coal_block)
					return 16000;
			}

			if(item == Items.stick)
				return 100;

			if(item == Items.coal)
				return 1600;

			if(item == Items.lava_bucket)
				return 20000;

			if(item == Item.getItemFromBlock(Blocks.sapling))
				return 100;

			if(item == Items.blaze_rod)
				return 2400;

			return GameRegistry.getFuelValue(itemStack);
		}
	}

	public boolean isFragmenting()
	{
		return burnTime > 0;
	}

	public void updateEntity()
	{
		if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
			boolean flag = false;

			if(isFragmenting())
				burnTime -= (furnaceSpeed / 32);

			if(!worldObj.isRemote)
			{
				if(burnTime <= 0 && canFragment())
				{
					currentItemBurnTime = burnTime = getItemBurnTime(slots[1]);

					if(isFragmenting())
					{
						flag = true;

						if(slots[1] != null)
						{
							slots[1].stackSize--;

							if(slots[1].stackSize == 0)
								slots[1] = slots[1].getItem().getContainerItem(slots[1]);
						}
					}
				}

				if(isFragmenting() && canFragment())
				{
					cookTime++;

					if(cookTime == furnaceSpeed)
					{
						cookTime = 0;
						fragmentItem();
						flag = true;
					}
				}

				else
					cookTime = 0;
			}

			if(flag)
				markDirty();
		}
	}

	// Checks if with the current input something can be processed.
	public boolean canFragment()
	{
		if(slots[0] == null)
			return false;

		else
		{
			ItemStack var1 = FragmentingHandler.fragmenting().getFragmentingResult(slots[0]);

			if(var1 == null)
				return false;

			if(slots[2] == null)
				return true;

			if(!slots[2].isItemEqual(var1))
				return false;

			int var2 = slots[2].stackSize + var1.stackSize;

			return (var2 <= getInventoryStackLimit() && var2 <= var1.getMaxStackSize());
		}
	}

	// Fragments the input item.
	public void fragmentItem()
	{
		if(canFragment())
		{
			ItemStack var1 = FragmentingHandler.fragmenting().getFragmentingResult(slots[0]);

			if(slots[2] == null)
				slots[2] = var1.copy();

			else if(slots[2].isItemEqual(var1))
				slots[2].stackSize += var1.stackSize;

			slots[0].stackSize--;

			if(slots[0].stackSize <= 0)
				slots[0] = null;
		}
	}

	// Methods used for automatic importing and exporting with hoppers or pipes.
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSide);
	}

	public boolean canInsertItem(int par1, ItemStack itemStack, int par2)
	{
		return isItemValidForSlot(par1, itemStack);
	}

	public boolean canExtractItem(int par1, ItemStack itemStack, int par2)
	{
		return par2 != 0 || par1 != 1 || itemStack.getItem() == Items.bucket;
	}

	// Methods used to get the correct values in the gui.
	public int getBurnTimeRemainingScaled(int par1)
	{
		if(currentItemBurnTime == 0)
			currentItemBurnTime = furnaceSpeed;

		return burnTime * par1 / currentItemBurnTime;
	}

	public int getCookProgressScaled(int par1)
	{
		return cookTime * par1 / furnaceSpeed;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		NBTTagList var1 = tag.getTagList("items", 10);

		slots = new ItemStack[getSizeInventory()];

		for(int i = 0; i < var1.tagCount(); i++)
		{
			NBTTagCompound var2 = (NBTTagCompound) var1.getCompoundTagAt(i);

			byte var3 = var2.getByte("slot");

			if(var3 >= 0 && var3 < slots.length)
				slots[var3] = ItemStack.loadItemStackFromNBT(var2);
		}

		burnTime = (int)tag.getShort("burnTime");
		cookTime = (int)tag.getShort("cookTime");
		currentItemBurnTime = (int)tag.getShort("currentBurnTime");

		if(tag.hasKey("customName"))
			localizedName = tag.getString("customName");
	}

	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setShort("burnTime", (short)burnTime);
		tag.setShort("cookTime", (short)cookTime);
		tag.setShort("currentBurnTime", (short)currentItemBurnTime);

		NBTTagList var1 = new NBTTagList();

		for(int i = 0; i < slots.length; i++)
			if(slots[i] != null)
			{
				NBTTagCompound var2 = new NBTTagCompound();
				var2.setByte("slot", (byte)i);
				slots[i].writeToNBT(var2);
				var1.appendTag(var2);
			}

		tag.setTag("items", var1);

		if(hasCustomInventoryName())
			tag.setString("customName", localizedName);
	}
}