package net.lonk.agartha.world.dimension;

import net.lonk.agartha.AgarthaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> AGARTHA_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "agartha"));

    public static final ResourceKey<Level> AGARTHA_WORLD_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "agartha"));

    public static final ResourceKey<DimensionType> AGARTHA_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "agartha_type"));

    public static void boostrapType(BootstapContext<DimensionType> context) {
        context.register(AGARTHA_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkyLight
                true, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                128, // height - Changed from 256 to 128 for Nether-like dimensions
                128, // logicalHeight - Changed from 256 to 128 (this prevents snow on bedrock!)
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)
        ));
    }
}