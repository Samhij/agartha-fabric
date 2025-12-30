package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.lonk.agartha.block.ModBlocks;
import net.lonk.agartha.item.ModItems;
import net.minecraft.data.client.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AGARTHIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AGARTHIUM_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WHITE_MONSTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DOWN_UNDER_MUSIC_DISC, Models.GENERATED);

        itemModelGenerator.register(ModItems.AGARTHIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.AGARTHAN_RESIDUE, Models.GENERATED);
    }
}
