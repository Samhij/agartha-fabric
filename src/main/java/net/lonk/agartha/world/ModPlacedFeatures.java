package net.lonk.agartha.world;

import net.lonk.agartha.AgarthaMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> AGARTHIUM_ORE_PLACED_KEY = registerKey("agarthium_ore_placed");
    public static final RegistryKey<PlacedFeature> SNOW_COVER_PLACED = registerKey("snow_cover_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, AGARTHIUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.AGARTHIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(2, // Veins per chunk
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(0), YOffset.fixed(256))));

        register(context, SNOW_COVER_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SNOW_COVER),
                List.of(
                        CountPlacementModifier.of(20), // Number of patches per chunk
                        SquarePlacementModifier.of(), // Randomly spread across chunk
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, // Place on surface
                        BiomePlacementModifier.of() // Only in biomes that specify this feature
                ));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(AgarthaMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
