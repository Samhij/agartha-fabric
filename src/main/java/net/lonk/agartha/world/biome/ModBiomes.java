package net.lonk.agartha.world.biome;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.sound.ModSounds;
import net.lonk.agartha.world.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> SNOWY_CAVES = ResourceKey.create(Registries.BIOME, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "snowy_caves"));
    public static final ResourceKey<Biome> LUSH_MEADOW = ResourceKey.create(Registries.BIOME, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "lush_meadow"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(SNOWY_CAVES, snowyCaves(context));
        context.register(LUSH_MEADOW, lushMeadow(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
    }

    public static Biome snowyCaves(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 5, 4, 6));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER)
        );

        // Global features
        globalOverworldGeneration(biomeBuilder);

        // Dripstone features for ceiling
        BiomeDefaultFeatures.addDripstone(biomeBuilder);

        // Frozen peaks features for ground
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        // ADD THIS: Snow cover feature to hide grass
        biomeBuilder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModPlacedFeatures.SNOW_COVER_PLACED));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-1.0f)
                .downfall(0.9f)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3938C9)
                        .waterFogColor(0x050533)
                        .skyColor(0x8CB8FF)
                        .grassColorOverride(0x80B497)
                        .foliageColorOverride(0x60A17B)
                        .fogColor(0xC0D8FF)
                        .backgroundMusic(Musics.createGameMusic(Holder.direct(ModSounds.DOWN_UNDER)))
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }

    public static Biome lushMeadow(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER)
        );

        // Global features
        globalOverworldGeneration(biomeBuilder);

        // Lush caves features for ceiling
        BiomeDefaultFeatures.addLushCavesVegetationFeatures(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);

        // Meadow features for ground (includes trees!)
        BiomeDefaultFeatures.addMeadowVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addWarmFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5f) // Mild temperature
                .downfall(0.8f) // High rainfall for lush vegetation
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x44aff5)
                        .waterFogColor(0x041633)
                        .skyColor(0x77adff) // Bright meadow sky
                        .grassColorOverride(0x6ab545) // Vibrant green grass
                        .foliageColorOverride(0x6ab545)
                        .fogColor(0xc0d8ff)
                        .backgroundMusic(Musics.createGameMusic(Holder.direct(ModSounds.DOWN_UNDER)))
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .build();
    }
}
