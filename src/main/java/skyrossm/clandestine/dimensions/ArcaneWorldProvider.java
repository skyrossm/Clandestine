package skyrossm.clandestine.dimensions;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import skyrossm.clandestine.dimensions.sky.ArcaneCloudRenderer;
import skyrossm.clandestine.dimensions.sky.ArcaneSkyRenderer;
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

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		float f = MathHelper.cos(celestialAngle * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		float f1 = 0.627451F;
		float f2 = 0.5019608F;
		float f3 = 0.627451F;
		f1 = f1 * (f * 0.0F + 0.15F);
		f2 = f2 * (f * 0.0F + 0.15F);
		f3 = f3 * (f * 0.0F + 0.15F);
		return new Vec3d((double) f1, (double) f2, (double) f3);
	}

	@Override
	public BlockPos getRandomizedSpawnPoint() {
		return new BlockPos(0, 95, 0);
	}

	@Override
	public BlockPos getSpawnPoint() {
		return new BlockPos(0, 95, 0);
	}

	@Override
	public void setSpawnPoint(BlockPos pos) {
		super.setSpawnPoint(new BlockPos(0, 95, 0));
	}

	@Override
	public BlockPos getSpawnCoordinate() {
		return new BlockPos(0, 95, 0);
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return new ArcaneSkyRenderer();
	}

	@Override
	public IRenderHandler getCloudRenderer() {
		return new ArcaneCloudRenderer();
	}

	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return null;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return -90.0F;
	}

	@Override
	public boolean isSkyColored() {
		return true;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
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
