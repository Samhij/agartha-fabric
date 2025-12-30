package net.lonk.agartha.world.biome;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.sound.ModSounds;
import net.lonk.agartha.world.ModPlacedFeatures;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> SNOWY_CAVES = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(AgarthaMod.MOD_ID, "snowy_caves"));
    public static final RegistryKey<Biome> LUSH_MEADOW = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(AgarthaMod.MOD_ID, "lush_meadow"));

    public static void bootstrap(Registerable<Biome> context) {
        context.register(SNOWY_CAVES, snowyCaves(context));
        context.register(LUSH_MEADOW, lushMeadow(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
    }

    public static Biome snowyCaves(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.GOAT, 5, 4, 6));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        );

        // Global features
        globalOverworldGeneration(biomeBuilder);

        // Dripstone features for ceiling
        DefaultBiomeFeatures.addDripstone(biomeBuilder);

        // Frozen peaks features for ground
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);

        // ADD THIS: Snow cover feature to hide grass
        biomeBuilder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.SNOW_COVER_PLACED));

        return new Biome.Builder()
                .precipitation(true)
                .temperature(-1.0f)
                .downfall(0.9f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x3938C9)
                        .waterFogColor(0x050533)
                        .skyColor(0x8CB8FF)
                        .grassColor(0x80B497)
                        .foliageColor(0x60A17B)
                        .fogColor(0xC0D8FF)
                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.DOWN_UNDER)))
                        .build())
                .spawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }

    public static Biome lushMeadow(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        );

        // Global features
        globalOverworldGeneration(biomeBuilder);

        // Lush caves features for ceiling
        DefaultBiomeFeatures.addLushCavesDecoration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);

        // Meadow features for ground (includes trees!)
        DefaultBiomeFeatures.addMeadowFlowers(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);
        DefaultBiomeFeatures.addExtraDefaultFlowers(biomeBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.5f) // Mild temperature
                .downfall(0.8f) // High rainfall for lush vegetation
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x44aff5)
                        .waterFogColor(0x041633)
                        .skyColor(0x77adff) // Bright meadow sky
                        .grassColor(0x6ab545) // Vibrant green grass
                        .foliageColor(0x6ab545)
                        .fogColor(0xc0d8ff)
                        .music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.DOWN_UNDER)))
                        .build())
                .spawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }
}
