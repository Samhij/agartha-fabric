package net.lonk.agartha.sound;

import net.lonk.agartha.AgarthaMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent DOWN_UNDER = registerSoundEvent("down_under");
    public static final SoundEvent YAKUB_SEEING = registerSoundEvent("yakub_seeing");

    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(AgarthaMod.MOD_ID, name),
                SoundEvent.of(Identifier.of(AgarthaMod.MOD_ID, name)));
    }

    public static void registerSounds() {
        AgarthaMod.LOGGER.info("Registering Sounds for " + AgarthaMod.MOD_ID);
    }
}
