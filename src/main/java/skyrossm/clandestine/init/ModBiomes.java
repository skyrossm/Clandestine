package skyrossm.clandestine.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import skyrossm.clandestine.dimensions.biomes.ErthaBiome;

public class ModBiomes {

	public static final Biome ERTHA = new ErthaBiome();
	
	public static void registerBiomes() {
		initBiome(ERTHA, "Ertha", BiomeType.ICY, Type.HILLS, Type.MAGICAL, Type.DRY);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		//BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		//BiomeManager.addVillageBiome(biome, true);
		return biome;
	}
}
