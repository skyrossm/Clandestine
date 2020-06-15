package skyrossm.clandestine;

import java.io.File;

import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import skyrossm.clandestine.commands.DimTeleportCommand;
import skyrossm.clandestine.init.ModBiomes;
import skyrossm.clandestine.init.ModDimensions;
import skyrossm.clandestine.init.ModRecipes;
import skyrossm.clandestine.proxy.CommonProxy;
import skyrossm.clandestine.tiles.SymbolChestTileEntity;
import skyrossm.clandestine.util.Config;
import skyrossm.clandestine.util.Reference;
import skyrossm.clandestine.util.handlers.ClientEventsHandler;
import skyrossm.clandestine.util.handlers.CommonEventsHandler;
import skyrossm.clandestine.util.handlers.GuiHandler;
import skyrossm.clandestine.util.handlers.SoundsHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class ClandestineMain {
	
	@Instance
	public static ClandestineMain instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static Configuration config;
	
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "clandestine.cfg"));
        Config.readConfig();
        
        ModBiomes.registerBiomes();
        ModDimensions.init();
        
        
        //Register Tile Entities
        GameRegistry.registerTileEntity(SymbolChestTileEntity.class, new ResourceLocation(Reference.MOD_ID, "tiles/symbolChest"));
        
        
        //GameRegistry.registerWorldGenerator(new ArcaneStructureGen(), 0);
        
        //Events
        if(event.getSide().isClient())
        	MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
        MinecraftForge.EVENT_BUS.register(new CommonEventsHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(ClandestineMain.instance, new GuiHandler());
        if(event.getSide().isClient()) {
        	SoundsHandler.registerSounds();
        }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (config.hasChanged()) {
            config.save();
        }
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    	event.registerServerCommand(new DimTeleportCommand());
    }
    
}
