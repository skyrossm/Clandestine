package skyrossm.clandestine.dimensions;

import net.minecraft.world.biome.Biome;
import skyrossm.clandestine.init.ModBlocks;

public class ErthaBiome extends Biome{
	

	public ErthaBiome() {
		super(new BiomeProperties("Ertha").setBaseHeight(0.5F).setTemperature(0.4F).setRainDisabled().setWaterColor(16711680));
		
		topBlock = ModBlocks.ERTHA_BLOCK.getDefaultState();
		fillerBlock = ModBlocks.ERTHA_BLOCK.getDefaultState();
		
		this.decorator.treesPerChunk = 1;
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
	}

}
