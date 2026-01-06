package net.lonk.agartha.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    public static final CreativeModeTab AGARTHAN_ITEMS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "agarthan_items"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.agarthan_items"))
                    .icon(() -> new ItemStack(ModItems.WHITE_MONSTER))
                    .displayItems((displayContext, entries) -> {
                        entries.accept(ModItems.WHITE_MONSTER);
                        entries.accept(ModItems.CONCENTRATED_CAFFEINE);
                        entries.accept(ModItems.DOWN_UNDER_MUSIC_DISC);

                        entries.accept(ModItems.AGARTHIUM);
                        entries.accept(ModItems.AGARTHAN_RESIDUE);
                    }).build());

    public static final CreativeModeTab AGARTHAN_BLOCKS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "agarthan_blocks"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.agarthan_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.AGARTHIUM_BLOCK))
                    .displayItems((displayContext, entries) -> {
                        entries.accept(ModBlocks.AGARTHIUM_BLOCK);
                        entries.accept(ModBlocks.AGARTHIUM_ORE);
                    }).build());

    public static void registerItemGroups() {
        AgarthaMod.LOGGER.info("Registering Item Groups for " + AgarthaMod.MOD_ID);
    }
}
