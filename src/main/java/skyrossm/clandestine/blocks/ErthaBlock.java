package skyrossm.clandestine.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ErthaBlock extends BlockBase {

	public ErthaBlock(String name, Material mat) {
		super(name, mat);
		
		this.setSoundType(SoundType.SNOW);
		this.setHardness(0.5F);
		this.setResistance(2.5F);
		this.setLightLevel(0.05F);
		this.setHarvestLevel("shovel", -1);
	}

}
