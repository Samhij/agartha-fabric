package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(
                        ModItems.WHITE_MONSTER,
                        Component.translatable("advancements.agartha.white_monster.title"),
                        Component.translatable("advancements.agartha.white_monster.description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/adventure.png"),
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                ).addCriterion("got_white_monster", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WHITE_MONSTER))
                .save(consumer, AgarthaMod.MOD_ID + "/root");
    }
}
