package skyrossm.clandestine.init;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import skyrossm.clandestine.blocks.ErthaBlock;

public class ModBlocks {
	
	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ERTHA_BLOCK = new ErthaBlock("ertha_block", Material.CRAFTED_SNOW);
}
