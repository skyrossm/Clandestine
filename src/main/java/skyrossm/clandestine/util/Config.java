package skyrossm.clandestine.util;

import net.minecraftforge.common.config.Configuration;
import skyrossm.clandestine.ClandestineMain;

public class Config {
	private static final String CATEGORY_DIMENSIONS  = "dimensions";
	
	public static int arcaneDimIdStart = 886;
	public static int arcaneDimStars = 2500;
	public static boolean enableSafeFall = false;
	public static boolean enableArcaneMusic = true;
	
	public static void readConfig() {
        Configuration cfg = ClandestineMain.config;
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
		 arcaneDimStars = cfg.getInt("arcaneDimStars", CATEGORY_DIMENSIONS, arcaneDimStars, 1, 20000, "Allows you to change the number of stars rendered in Arcane dimensions. Smaller number will greatly increase FPS");
		 enableSafeFall = cfg.getBoolean("enableSafeFall", CATEGORY_DIMENSIONS, enableSafeFall, "Prevents falling into the void in Arcane dimensions. (might be bugged, dont rely on it)");
		 enableArcaneMusic = cfg.getBoolean("enableArcaneMusic", CATEGORY_DIMENSIONS, enableArcaneMusic, "Enable custom music in Arcane dimensions");
	 }
}
