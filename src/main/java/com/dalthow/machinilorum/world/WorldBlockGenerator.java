package com.dalthow.machinilorum.world;

import java.util.Random;

import com.dalthow.machinilorum.base.Main;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * Craftus Machinilorum
 *
 * 
 * @author Dalthow Game Studios
 * @class WorldBlockGenerator.java 
 * 
 **/

public class WorldBlockGenerator implements IWorldGenerator
{
	// Specifies in which dimension the "ore" should spawn.
	
	public void generate(Random random, int xChunk, int zChunk, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
			case 0: generateSurface(world, random, xChunk * 16, zChunk * 16);
		}
	}
	
	
	// Generates chalk in the world.
	
	private void generateSurface(World world, Random random, int xChunk, int zChunk)
	{
		if(world.provider.getBiomeGenForCoords(xChunk, zChunk) instanceof BiomeGenOcean)
		{
			for (int i = 0; i < 16; i++)
			{
				int randPosX = xChunk + random.nextInt(16);
				int randPosY = (32 + random.nextInt(64));
				int randPosZ = zChunk + random.nextInt(16);
	
				(new WorldGenMinable(Main.blockChalk, 32)).generate(world, random, randPosX, randPosY, randPosZ);
			}
		}
	}
}