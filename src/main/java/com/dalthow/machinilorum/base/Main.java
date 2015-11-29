package com.dalthow.machinilorum.base;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.dalthow.machinilorum.block.BlockChalk;
import com.dalthow.machinilorum.block.BlockChalkboard;
import com.dalthow.machinilorum.block.BlockConveyorBelt;
import com.dalthow.machinilorum.block.BlockEggIncubator;
import com.dalthow.machinilorum.block.BlockFragmentizer;
import com.dalthow.machinilorum.block.BlockMobRadar;
import com.dalthow.machinilorum.block.BlockSprinkler;
import com.dalthow.machinilorum.block.BlockStoneCutter;
import com.dalthow.machinilorum.block.BlockWoodCutter;
import com.dalthow.machinilorum.block.item.ItemChalk;
import com.dalthow.machinilorum.block.item.ItemChalkboard;
import com.dalthow.machinilorum.block.item.ItemConveyorBelt;
import com.dalthow.machinilorum.block.item.ItemEggIncubator;
import com.dalthow.machinilorum.block.item.ItemMobRadar;
import com.dalthow.machinilorum.block.item.ItemSprinkler;
import com.dalthow.machinilorum.block.item.ItemWoodCutter;
import com.dalthow.machinilorum.entity.EntityBomb;
import com.dalthow.machinilorum.entity.EntityShoppingCart;
import com.dalthow.machinilorum.entity.item.ItemShoppingCart;
import com.dalthow.machinilorum.handler.GuiHandler;
import com.dalthow.machinilorum.handler.RecipeHandler;
import com.dalthow.machinilorum.handler.RegisterHandler;
import com.dalthow.machinilorum.item.ItemBomb;
import com.dalthow.machinilorum.item.ItemChecker;
import com.dalthow.machinilorum.item.ItemFilter;
import com.dalthow.machinilorum.packet.PacketPipeline;
import com.dalthow.machinilorum.proxy.ClientProxy;
import com.dalthow.machinilorum.proxy.CommonProxy;
import com.dalthow.machinilorum.tab.TabMachines;
import com.dalthow.machinilorum.tab.TabTools;
import com.dalthow.machinilorum.tile.TileEntityChalkboard;
import com.dalthow.machinilorum.tile.TileEntityConveyorBelt;
import com.dalthow.machinilorum.tile.TileEntityEggIncubator;
import com.dalthow.machinilorum.tile.TileEntityFragmentizer;
import com.dalthow.machinilorum.tile.TileEntityMobRadar;
import com.dalthow.machinilorum.tile.TileEntitySprinkler;
import com.dalthow.machinilorum.tile.TileEntityWoodCutter;
import com.dalthow.machinilorum.world.WorldBlockGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Craftus Machinilorum
 *
 * @author Trevi Awater
 **/

@Mod(modid = Reference.modId, name = Reference.name, version = Reference.version)
public class Main 
{
	// Declaration for the configuration file variables.
	public static Property recipeObsidian;
	public static Property recipeMossCobblestone;
	public static Property recipeSaddle;
	public static Property recipeCobweb;
	public static Property recipeBone;
	public static Property recipeIce;
	
	public static Property recipeFilter;
	public static Property recipeBomb;
	public static Property recipeChecker;
	
	public static Property recipeStoneCutter;
	public static Property recipeFragmentizer;
	public static Property recipeEggIncubator;
	public static Property recipeWoodCutter;
	public static Property recipeMobRadar;
	public static Property recipeConveyorBelt;
	public static Property recipeSprinkler;
	public static Property recipeChalkboard;
	
	public static Property recipeShoppingCart;

	@Instance
	public static Main instance;

	// Registering the proxy's.
	@SidedProxy(clientSide = "com.dalthow.machinilorum.proxy.ClientProxy", serverSide = "com.dalthow.machinilorum.proxy.CommonProxy") 
	
	public static CommonProxy CommonProxy; 
    public static ClientProxy ClientProxy;

 	public static final PacketPipeline packetPipeline = new PacketPipeline();

	// Creative tabs declaration.
	public static CreativeTabs tabMachinilorumTools = new TabTools(CreativeTabs.getNextID(), "tabMachinilorumTools");
	public static CreativeTabs tabMachinilorumMachines = new TabMachines(CreativeTabs.getNextID(), "tabMachinilorumMachines");

	// Declaration for items.
	public static Item itemFilter;
	public static Item itemBomb;
	public static Item itemShoppingCart;
	public static Item itemChecker;

	// Declaration for blocks.
	public static Block blockStoneCutter;
	public static Block blockFragmentizer;
	public static Block blockEggIncubator;
	public static Block blockWoodCutter;
	public static Block blockMobRadar;
	public static Block blockConveyorBelt;
	public static Block blockChalk;
	public static Block blockSprinkler;
	public static Block blockChalkboard;
		
	
	// Initialization that happens before any of the world is loaded.
	@Mod.EventHandler
	public void preInit(FMLInitializationEvent event)
	{
		// Setting up the items.
		itemFilter  = new ItemFilter();
		itemBomb = new ItemBomb();
		itemShoppingCart = new ItemShoppingCart();
		itemChecker = new ItemChecker();

		// Setting up the blocks.
		blockStoneCutter = new BlockStoneCutter().setBlockName("stoneCutter");
		blockFragmentizer = new BlockFragmentizer().setBlockName("fragmentizer");
		blockEggIncubator = new BlockEggIncubator().setBlockName("eggIncubator");
		blockWoodCutter = new BlockWoodCutter().setBlockName("woodCutter");
		blockMobRadar = new BlockMobRadar().setBlockName("mobRadar");
		blockConveyorBelt = new BlockConveyorBelt().setBlockName("conveyorBelt");
		blockChalk = new BlockChalk().setBlockName("chalk");
		blockSprinkler = new BlockSprinkler().setBlockName("sprinkler");
		blockChalkboard = new BlockChalkboard().setBlockName("chalkboard");

		RegisterHandler.registerBlock(blockStoneCutter);
		RegisterHandler.registerBlock(blockFragmentizer);

		RegisterHandler.registerBlockWithItem(blockChalk, ItemChalk.class);
		RegisterHandler.registerBlockWithItem(blockEggIncubator, ItemEggIncubator.class);
		RegisterHandler.registerBlockWithItem(blockWoodCutter, ItemWoodCutter.class);
		RegisterHandler.registerBlockWithItem(blockMobRadar, ItemMobRadar.class);
		RegisterHandler.registerBlockWithItem(blockConveyorBelt, ItemConveyorBelt.class);
		RegisterHandler.registerBlockWithItem(blockSprinkler, ItemSprinkler.class);
		RegisterHandler.registerBlockWithItem(blockChalkboard, ItemChalkboard.class);

		RegisterHandler.registerTileEntity(TileEntityFragmentizer.class, "fragmentizer");
		RegisterHandler.registerTileEntity(TileEntityEggIncubator.class, "eggIncubator");
		RegisterHandler.registerTileEntity(TileEntityWoodCutter.class, "woodCutter");
		RegisterHandler.registerTileEntity(TileEntityMobRadar.class, "mobRadar");
		RegisterHandler.registerTileEntity(TileEntityConveyorBelt.class, "conveyorBelt");
		RegisterHandler.registerTileEntity(TileEntitySprinkler.class, "sprinkler");
		RegisterHandler.registerTileEntity(TileEntityChalkboard.class, "chalkboard");

		RegisterHandler.registerItem(itemFilter);
		RegisterHandler.registerItem(itemBomb);
		RegisterHandler.registerItem(itemShoppingCart);
		RegisterHandler.registerItem(itemChecker);

		RegisterHandler.registerEntity(EntityShoppingCart.class, "shoppingCart");
		RegisterHandler.registerEntity(EntityBomb.class, "bomb");

		GameRegistry.registerWorldGenerator(new WorldBlockGenerator(), 1);

		// Allowing some of the utility items to be spawned in randomly generated chests.
        ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(itemFilter), 1, 1, 40)); 
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(itemBomb), 1, 5, 25));          
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(itemChecker), 1, 1, 30));
        
        // Creates a configuration file if there isn't one already.
        Configuration config = new Configuration(new File("config/" + Reference.modId + ".cfg"));
		
		config.load();

		recipeObsidian = config.get("Recipes", "Obsidian", true);
		recipeMossCobblestone = config.get("Recipes", "Moss Cobblestone", true);
		recipeSaddle = config.get("Recipes", "Saddle", true);
		recipeCobweb = config.get("Recipes", "Cobweb", true);
		recipeBone = config.get("Recipes", "Bone", true);
		recipeIce = config.get("Recipes", "Ice", true);
		
		recipeFilter = config.get("Recipes", "Filter", true);
		recipeBomb = config.get("Recipes", "Bomb", true);
		recipeChecker = config.get("Recipes", "Checker", true);
		
		recipeStoneCutter = config.get("Recipes", "Stone Cutter", true);
		recipeFragmentizer = config.get("Recipes", "Fragmentizer", true);
		recipeEggIncubator = config.get("Recipes", "Egg Incubator", true);
		recipeWoodCutter = config.get("Recipes", "Wood Cutter", true);
		recipeMobRadar = config.get("Recipes", "Mob Radar", true);
		recipeConveyorBelt = config.get("Recipes", "Conveyor Belt", true);
		recipeSprinkler = config.get("Recipes", "Sprinkler", true);
		recipeChalkboard = config.get("Recipes", "Chalkboard", false);
		
		recipeShoppingCart = config.get("Recipes", "Shopping Cart", false);
		
		config.save();

		// Adding and removing custom recipe's to or out of the game.
		RecipeHandler.loadRecipes();
		RecipeHandler.removeRecipes();

		// Loading the custom models.
		CommonProxy.loadRenderers();
	}

	// Initialization that happens when the world is loaded.
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		packetPipeline.initialize();
	}
}