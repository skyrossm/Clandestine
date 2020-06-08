package skyrossm.clandestine.proxy;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import skyrossm.clandestine.init.ModBiomes;
import skyrossm.clandestine.init.ModDimensions;
import skyrossm.clandestine.util.Config;

public class CommonProxy {
	
	public static Configuration config;
	
	public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "clandestine.cfg"));
        Config.readConfig();
        
        ModBiomes.registerBiomes();
        ModDimensions.init();
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }
    
	public void registerItemRenderer(Item item, int meta, String id) {}

	
}
