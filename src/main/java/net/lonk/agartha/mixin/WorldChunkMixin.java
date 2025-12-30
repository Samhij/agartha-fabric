package net.lonk.agartha.mixin;

import net.lonk.agartha.world.biome.ModBiomes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldChunk.class)
public class WorldChunkMixin {
    @ModifyVariable(method = "setBlockState", at = @At("HEAD"), argsOnly = true)
    private BlockState replaceSurfaceBlocks(BlockState state, BlockPos pos, BlockState state2, boolean moved) {
        WorldChunk chunk = (WorldChunk)(Object)this;

        // Get the biome at this position
        RegistryEntry<Biome> biomeEntry = chunk.getBiomeForNoiseGen(
                pos.getX() >> 2,
                pos.getY() >> 2,
                pos.getZ() >> 2
        );

        // Check if we're in the snowy caves biome
        if (biomeEntry.matchesKey(ModBiomes.SNOWY_CAVES)) {
            // Replace grass blocks with snow blocks
            if (state.isOf(Blocks.GRASS_BLOCK)) {
                return Blocks.SNOW_BLOCK.getDefaultState();
            }
            // Replace dirt with snow blocks
            else if (state.isOf(Blocks.DIRT)) {
                return Blocks.SNOW_BLOCK.getDefaultState();
            }
        }

        return state;
    }
}
