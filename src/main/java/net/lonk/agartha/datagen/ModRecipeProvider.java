package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.lonk.agartha.block.ModBlocks;
import net.lonk.agartha.item.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, List.of(ModBlocks.AGARTHIUM_ORE), RecipeCategory.MISC, ModItems.AGARTHAN_RESIDUE, 0.25f, 200, "agarthium");
        oreBlasting(consumer, List.of(ModBlocks.AGARTHIUM_ORE), RecipeCategory.MISC, ModItems.AGARTHAN_RESIDUE, 0.25f, 100, "agarthium");

        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, ModItems.AGARTHIUM, RecipeCategory.MISC, ModBlocks.AGARTHIUM_BLOCK);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CONCENTRATED_CAFFEINE)
                .requires(ModItems.WHITE_MONSTER, 9)
                .unlockedBy(getHasName(ModItems.WHITE_MONSTER), has(ModItems.WHITE_MONSTER))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AGARTHIUM)
                .requires(ModItems.AGARTHAN_RESIDUE, 4)
                .requires(Items.AMETHYST_SHARD, 4)
                .unlockedBy(getHasName(ModItems.AGARTHAN_RESIDUE), has(ModItems.AGARTHAN_RESIDUE))
                .save(consumer, "agarthium_from_agarthan_residue");
    }
}
