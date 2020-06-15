package skyrossm.clandestine.dimensions.generators;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import skyrossm.clandestine.util.Reference;
import skyrossm.clandestine.util.interfaces.IStructure;

public class ArcaneStructure extends WorldGenerator implements IStructure{
	
	public String strucutreName;
	
	public ArcaneStructure(String name) {
		this.strucutreName = name;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		this.generateStructure(worldIn, position);
		return true;
	}
	
	public void generateStructure(World world, BlockPos pos) {
		MinecraftServer mcServer = world.getMinecraftServer();
		TemplateManager manager = worldServer.getStructureTemplateManager();
		ResourceLocation loc = new ResourceLocation(Reference.MOD_ID, strucutreName);
		
		Template template = manager.get(mcServer, loc);
		if(template != null) {
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			template.addBlocksToWorldChunk(world, pos, settings);
		}
	}

}
