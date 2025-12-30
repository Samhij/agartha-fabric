package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.item.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(
                        ModItems.WHITE_MONSTER,
                        Text.translatable("advancements.agartha.white_monster.title"),
                        Text.translatable("advancements.agartha.white_monster.description"),
                        new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                ).criterion("got_white_monster", InventoryChangedCriterion.Conditions.items(ModItems.WHITE_MONSTER))
                .build(consumer, AgarthaMod.MOD_ID + "/root");
    }
}
