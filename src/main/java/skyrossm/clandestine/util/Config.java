package skyrossm.clandestine.util;

import net.minecraftforge.common.config.Configuration;
import skyrossm.clandestine.proxy.CommonProxy;

public class Config {
	private static final String CATEGORY_DIMENSIONS  = "dimensions";
	
	public static int arcaneDimIdStart = 886;
	
	public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initDimensionConfig(cfg);
        } catch (Exception e1) {
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }
	
	 private static void initDimensionConfig(Configuration cfg) {
		 cfg.addCustomCategoryComment(CATEGORY_DIMENSIONS, "Dimension configuration");
		 arcaneDimIdStart = cfg.getInt("arcaneDimIdStart", CATEGORY_DIMENSIONS, arcaneDimIdStart, 4, 32000, "Starting dim id (increments up) for the Arcane dimensions");
	 }
}
