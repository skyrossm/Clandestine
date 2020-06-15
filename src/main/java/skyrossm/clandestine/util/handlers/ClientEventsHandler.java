package skyrossm.clandestine.util.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import skyrossm.clandestine.dimensions.ArcaneWorldProvider;
import skyrossm.clandestine.util.Config;

public class ClientEventsHandler {
	@SubscribeEvent
	public void onSoundPlay(PlaySoundEvent event) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if (Config.enableArcaneMusic && player != null && player.world != null && player.world.provider instanceof ArcaneWorldProvider) {
			SoundCategory cat = event.getSound().getCategory();
			if (cat == SoundCategory.MUSIC) {
				if (SoundsHandler.ARCANE_MUSIC_BGM_ISOUND == null || !event.getManager().isSoundPlaying(SoundsHandler.ARCANE_MUSIC_BGM_ISOUND)) {
					PositionedSoundRecord newSound = PositionedSoundRecord.getMusicRecord(SoundsHandler.ARCANE_MUSIC_BGM);
					event.setResultSound(newSound);
					SoundsHandler.ARCANE_MUSIC_BGM_ISOUND = newSound;
				}else {
					event.setResultSound(null);
				}
			}
		}
	}
}
