package net.lonk.agartha.sound;

import net.lonk.agartha.AgarthaMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent DOWN_UNDER = registerSoundEvent("down_under");
    public static final SoundEvent YAKUB_SEEING = registerSoundEvent("yakub_seeing");

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, name),
                SoundEvent.createVariableRangeEvent(ResourceLocation.tryBuild(AgarthaMod.MOD_ID, name)));
    }

    public static void registerSounds() {
        AgarthaMod.LOGGER.info("Registering Sounds for " + AgarthaMod.MOD_ID);
    }
}
