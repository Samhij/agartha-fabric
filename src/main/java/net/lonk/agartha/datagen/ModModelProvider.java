package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.lonk.agartha.block.ModBlocks;
import net.lonk.agartha.item.ModItems;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(ModBlocks.AGARTHIUM_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.AGARTHIUM_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.WHITE_MONSTER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CONCENTRATED_CAFFEINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DOWN_UNDER_MUSIC_DISC, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.AGARTHIUM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.AGARTHAN_RESIDUE, ModelTemplates.FLAT_ITEM);
    }
}
