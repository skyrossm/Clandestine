package skyrossm.clandestine.init;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import skyrossm.clandestine.blocks.ArcanePortalBlock;
import skyrossm.clandestine.blocks.ErthaBlock;
import skyrossm.clandestine.blocks.SymbolChestBlock;

public class ModBlocks {
	
	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ERTHA_BLOCK = new ErthaBlock("ertha_block", Material.CRAFTED_SNOW);
	public static final Block ARCANE_PORTAL_BLOCK = new ArcanePortalBlock("arcane_portal_block", Material.PORTAL);
	
	public static final Block SYMBOL_CHEST_BLOCK = new SymbolChestBlock("symbol_chest_block", Material.WOOD);

	//public static final Block ARCANE_FLUID_BLOCK = new ErthaBlock("arcane_fluid_block", Material.WATER);
}
