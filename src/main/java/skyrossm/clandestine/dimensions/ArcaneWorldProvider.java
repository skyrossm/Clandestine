package skyrossm.clandestine.dimensions;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import skyrossm.clandestine.init.ModBiomes;
import skyrossm.clandestine.init.ModDimensions;

public class ArcaneWorldProvider extends WorldProvider {
	
	public ArcaneWorldProvider() {
		this.biomeProvider = new BiomeProviderSingle(ModBiomes.ERTHA);
	}
	@Override
	public DimensionType getDimensionType() {
		return ModDimensions.ARCANE;
	}
	
	@SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        float f = MathHelper.cos(p_76562_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.627451F;
        float f2 = 0.5019608F;
        float f3 = 0.627451F;
        f1 = f1 * (f * 0.0F + 0.15F);
        f2 = f2 * (f * 0.0F + 0.15F);
        f3 = f3 * (f * 0.0F + 0.15F);
        return new Vec3d((double)f1, (double)f2, (double)f3);
    }
	
	@SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }
	
	@Override
	public float getCloudHeight() {
		return 0.0F;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ArcaneChunkGenerator(world, true, world.getSeed(), new BlockPos(0, 50, 0));
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public String getSaveFolder() {
		return "clandestine";
	}


}
