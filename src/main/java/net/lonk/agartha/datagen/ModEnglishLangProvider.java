package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.lonk.agartha.block.ModBlocks;
import net.lonk.agartha.entity.ModEntities;
import net.lonk.agartha.gamerule.ModGameRules;
import net.lonk.agartha.item.ModItems;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.WHITE_MONSTER, "White Monster");
        translationBuilder.add(ModItems.CONCENTRATED_CAFFEINE, "Concentrated Caffeine");
        translationBuilder.add(ModItems.DOWN_UNDER_MUSIC_DISC, "Music Disc");
        translationBuilder.add(ModItems.DOWN_UNDER_MUSIC_DISC.getDescriptionId() + ".desc", "YourLocalSchizo - Down Under - Agartha Remix");

        translationBuilder.add(ModItems.AGARTHIUM, "Agarthium");
        translationBuilder.add(ModItems.AGARTHAN_RESIDUE, "Agarthan Residue");

        translationBuilder.add(ModBlocks.AGARTHIUM_BLOCK, "Block of Agarthium");
        translationBuilder.add(ModBlocks.AGARTHIUM_ORE, "Agarthium Ore");

        translationBuilder.add(ModEntities.YAKUB, "Yakub");

        translationBuilder.add("death.attack.agarthan", "%1$s was not allowed into Agartha.");
        translationBuilder.add("death.attack.agarthan.player", "%1$s was not allowed into Agartha by %2$s.");

        translationBuilder.add("death.attack.heart_attack", "%1$s had a heart attack.");

        translationBuilder.add("itemGroup.agarthan_items", "Agarthan Items");
        translationBuilder.add("itemGroup.agarthan_blocks", "Agarthan Blocks");

        translationBuilder.add("advancements.agartha.white_monster.title", "The Good Stuff");
        translationBuilder.add("advancements.agartha.white_monster.description", "Kill Yakub and obtain a can of White Monster");

        translationBuilder.add("gamerule.category.agartha", "Agartha");
    }
}
