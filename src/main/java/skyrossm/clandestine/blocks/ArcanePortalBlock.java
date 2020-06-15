package skyrossm.clandestine.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ArcanePortalBlock extends BlockBase {

	public ArcanePortalBlock(String name, Material mat) {
		super(name, mat);
		
		this.setSoundType(SoundType.ANVIL);
		this.setHardness(999999.0F);
		this.setResistance(999999.0F);
		this.setLightLevel(0.2F);
		this.setBlockUnbreakable();
	}

}
