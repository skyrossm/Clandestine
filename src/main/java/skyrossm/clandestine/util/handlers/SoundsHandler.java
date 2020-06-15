package skyrossm.clandestine.util.handlers;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import skyrossm.clandestine.util.Reference;

public class SoundsHandler {
	public static SoundEvent ARCANE_MUSIC_BGM;
	public static ISound ARCANE_MUSIC_BGM_ISOUND;
	
	public static void registerSounds() {
		ARCANE_MUSIC_BGM = registerSound("music.arcane.bgm");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
