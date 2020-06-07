package skyrossm.clandestine.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ErthaBlock extends BlockBase {

	public ErthaBlock(String name, Material mat) {
		super(name, mat);
		
		this.setSoundType(SoundType.SNOW);
		this.setHardness(1.0F);
		this.setResistance(7.5F);
		this.setLightLevel(0.05F);
	}

}
