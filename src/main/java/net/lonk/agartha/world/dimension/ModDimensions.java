package net.lonk.agartha.world.dimension;

import net.lonk.agartha.AgarthaMod;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> AGARTHA_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(AgarthaMod.MOD_ID, "agartha"));

    public static final RegistryKey<World> AGARTHA_WORLD_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(AgarthaMod.MOD_ID, "agartha"));

    public static final RegistryKey<DimensionType> AGARTHA_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(AgarthaMod.MOD_ID, "agartha_type"));

    public static void boostrapType(Registerable<DimensionType> context) {
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
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)
        ));
    }
}