package skyrossm.clandestine.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import skyrossm.clandestine.dimensions.ArcaneWorldProvider;
import skyrossm.clandestine.util.Config;
import skyrossm.clandestine.util.Reference;

public class ModDimensions {

	public static DimensionType ARCANE;
	
	public static void init() {
		registerDimensionTypes();
		registerDimensions();
	}
	
	private static void registerDimensionTypes() {
		ARCANE = DimensionType.register(Reference.MOD_ID, "_arcane", Config.arcaneDimIdStart, ArcaneWorldProvider.class, false);
	}
	
	private static void registerDimensions() {
		DimensionManager.registerDimension(Config.arcaneDimIdStart, ARCANE);
	}
}
