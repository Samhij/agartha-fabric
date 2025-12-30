package net.lonk.agartha.world;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> AGARTHIUM_ORE_KEY = registerKey("agarthium_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SNOW_COVER = registerKey("snow_cover");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> agarthiumOres = List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.AGARTHIUM_ORE.getDefaultState()));
        register(context, AGARTHIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(agarthiumOres, 4)); // 4 = ore blocks per vein

        register(context, SNOW_COVER, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        96, // tries
                        7,     // xz spread
                        3,     // y spread
                        PlacedFeatures.createEntry(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(
                                        BlockStateProvider.of(Blocks.SNOW_BLOCK)
                                )
                        )
                ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(AgarthaMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
