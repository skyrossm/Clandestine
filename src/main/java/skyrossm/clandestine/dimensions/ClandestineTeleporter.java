package skyrossm.clandestine.dimensions;

import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import skyrossm.clandestine.dimensions.generators.ArcaneStructureGen;
import skyrossm.clandestine.util.Config;

public class ClandestineTeleporter extends Teleporter {

	public ClandestineTeleporter(WorldServer worldIn, double x, double y, double z) {
		super(worldIn);
		this.worldServer = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	private final WorldServer worldServer;
	private double x;
	private double y;
	private double z;

	@Override
	public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
		BlockPos pos = new BlockPos((int) this.x, (int) this.y, (int) this.z);
		if(this.worldServer.provider instanceof ArcaneWorldProvider) {
			BlockPos underPos = new BlockPos(pos.getX(), pos.getY() - 3, pos.getZ());
			IBlockState underBlock = this.world.getBlockState(underPos);
			if (underBlock != Blocks.BEDROCK.getDefaultState()) {
				System.out.println("Under block:" + underBlock.toString());
				BlockPos portalPos = new BlockPos(pos.getX() - 2, pos.getY() - 3, pos.getZ() - 15);
				ArcaneStructureGen.SPAWN_PORTAL.generate(this.worldServer, new Random(), portalPos);
			}
		}
		entity.setRotationYawHead(-90.0F);
		entity.setPositionAndUpdate(this.x, this.y, this.z);
		entity.motionX = 0.0f;
		entity.motionY = 0.0f;
		entity.motionZ = 0.0f;
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entity, float yaw) {
        entity.motionX = 0.0F;
        entity.motionZ = 0.0F;
        entity.rotationYaw = yaw;
        if (entity instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP)entity).connection.setPlayerLocation(this.x, this.y, this.z, entity.rotationYaw, entity.rotationPitch);
        }
        else
        {
        	entity.setLocationAndAngles(this.x, this.y, this.z, entity.rotationYaw, entity.rotationPitch);
        }
        return true;
	}

	public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
		int oldDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		player.addExperienceLevel(0);

		if (worldServer == null || worldServer.getMinecraftServer() == null) { // Dimension doesn't exist
			throw new IllegalArgumentException("Dimension: " + dimension + " doesn't exist!");
		}

		worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension,
				new ClandestineTeleporter(worldServer, x, y + 1, z));
		player.setPositionAndUpdate(x, y + 1, z);
		if (oldDimension == 1 || oldDimension == Config.arcaneDimIdStart) {
			player.setPositionAndUpdate(x, y + 2, z);
			worldServer.spawnEntity(player);
			worldServer.updateEntityWithOptionalForce(player, false);
		}
	}

}
