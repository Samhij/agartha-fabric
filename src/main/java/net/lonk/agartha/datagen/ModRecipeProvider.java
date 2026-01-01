package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.lonk.agartha.block.ModBlocks;
import net.lonk.agartha.item.ModItems;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        offerSmelting(consumer, List.of(ModBlocks.AGARTHIUM_ORE), RecipeCategory.MISC, ModItems.AGARTHAN_RESIDUE, 0.25f, 200, "agarthium");
        offerBlasting(consumer, List.of(ModBlocks.AGARTHIUM_ORE), RecipeCategory.MISC, ModItems.AGARTHAN_RESIDUE, 0.25f, 100, "agarthium");

        offerReversibleCompactingRecipes(consumer, RecipeCategory.MISC, ModItems.AGARTHIUM, RecipeCategory.MISC, ModBlocks.AGARTHIUM_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CONCENTRATED_CAFFEINE)
                .input(ModItems.WHITE_MONSTER, 9)
                .criterion(hasItem(ModItems.WHITE_MONSTER), conditionsFromItem(ModItems.WHITE_MONSTER))
                .offerTo(consumer);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AGARTHIUM)
                .input(ModItems.AGARTHAN_RESIDUE, 4)
                .input(Items.AMETHYST_SHARD, 4)
                .criterion(hasItem(ModItems.AGARTHAN_RESIDUE), conditionsFromItem(ModItems.AGARTHAN_RESIDUE))
                .offerTo(consumer, "agarthium_from_agarthan_residue");
    }
}
