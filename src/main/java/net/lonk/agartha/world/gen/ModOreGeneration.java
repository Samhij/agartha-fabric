package net.lonk.agartha.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.lonk.agartha.world.ModPlacedFeatures;
import net.lonk.agartha.world.biome.ModBiomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.LUSH_MEADOW, ModBiomes.SNOWY_CAVES),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.AGARTHIUM_ORE_PLACED_KEY);
    }
}
