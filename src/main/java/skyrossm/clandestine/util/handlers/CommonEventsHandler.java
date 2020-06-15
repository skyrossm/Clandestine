package skyrossm.clandestine.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import skyrossm.clandestine.dimensions.ArcaneWorldProvider;
import skyrossm.clandestine.util.Config;

public class CommonEventsHandler {

	/*
	 * Teleport the player to avoid void damage if in Arcane world.
	 */
	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event) {
		if (Config.enableSafeFall && !event.getEntity().world.isRemote && event.getEntity().world.provider instanceof ArcaneWorldProvider) {
			if (event.getSource() == DamageSource.OUT_OF_WORLD && event.getSource().isDamageAbsolute()) {
				if(event.getEntity() instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) event.getEntity();
					
					player.fallDistance = 0.0F;
					player.onGround = true;
					
					event.setCanceled(true);
					
					BlockPos safeBlock = player.world.getTopSolidOrLiquidBlock(player.world.getSpawnPoint());
					//This doesn't work outside a certain distance...
					player.setPositionAndUpdate(safeBlock.getX(), safeBlock.getY(), safeBlock.getZ());
					player.playSound(SoundEvents.ENTITY_ENDERPEARL_THROW, 0.5f, 0.5f);
					player.world.getMinecraftServer().getWorld(player.world.provider.getDimension()).spawnParticle(EnumParticleTypes.PORTAL, safeBlock.getX(), safeBlock.getY(), safeBlock.getZ(), 0, 10, 0, 0);
					player.sendMessage(new TextComponentString("§bThe arcane force surrounding this world protected you..."));
				}
				
			}
		}
	}


}
