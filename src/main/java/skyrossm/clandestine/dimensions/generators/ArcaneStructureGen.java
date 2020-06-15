package skyrossm.clandestine.dimensions.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import skyrossm.clandestine.dimensions.ArcaneWorldProvider;
import skyrossm.clandestine.dimensions.biomes.ErthaBiome;
import skyrossm.clandestine.init.ModBlocks;

public class ArcaneStructureGen implements IWorldGenerator{

	public static final ArcaneStructure SPAWN_PORTAL = new ArcaneStructure("spawnPortal");
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider instanceof ArcaneWorldProvider) {
			generateStructure(SPAWN_PORTAL, world, random, chunkX, chunkZ, 25, ModBlocks.ERTHA_BLOCK, ErthaBiome.class);
		}
	}
	
	private void generateStructure(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes) {
		//Biomes list
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock) - 1;
		if(y == -1) return;
		BlockPos pos = new BlockPos(x, y, z);
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if(world.getWorldType() != WorldType.FLAT) {
			if(classesList.contains(biome)) {
				if(random.nextInt(chance) == 0) {
					gen.generate(world, random, pos);
				}
			}
		}
	}
	
	public static int calculateGenerationHeight(World world, int x, int z, Block topBlock){
		int y = world.getHeight();
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}
	
}
