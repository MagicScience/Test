package com.jiffy.generation;

import java.util.*;

import com.jiffy.block.ModBlocks;

import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import net.minecraft.world.gen.feature.*;
import cpw.mods.fml.common.*;

public class WorldGeneration implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch(world.provider.dimensionId){
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
		}




	}

	private void generateEnd(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private void generateSurface(World world, Random rand, int i, int j) {
		for(int k = 0; k < 10; k++){
        	int x = i + rand.nextInt(16);
        	int y = 5+rand.nextInt(6);
        	int z = j + rand.nextInt(16);
        	
        	new WorldGenMinable(ModBlocks.sapphireOre.blockID, 5+rand.nextInt(16)).generate(world, rand, x, y, z);
		}
		
	}

	private void generateNether(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
