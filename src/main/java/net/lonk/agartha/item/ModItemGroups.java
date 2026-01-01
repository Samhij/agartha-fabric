package net.lonk.agartha.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup AGARTHAN_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(AgarthaMod.MOD_ID, "agarthan_items"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.agarthan_items"))
                    .icon(() -> new ItemStack(ModItems.WHITE_MONSTER))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WHITE_MONSTER);
                        entries.add(ModItems.CONCENTRATED_CAFFEINE);
                        entries.add(ModItems.DOWN_UNDER_MUSIC_DISC);

                        entries.add(ModItems.AGARTHIUM);
                        entries.add(ModItems.AGARTHAN_RESIDUE);
                    }).build());

    public static final ItemGroup AGARTHAN_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(AgarthaMod.MOD_ID, "agarthan_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.agarthan_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.AGARTHIUM_BLOCK))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.AGARTHIUM_BLOCK);
                        entries.add(ModBlocks.AGARTHIUM_ORE);
                    }).build());

    public static void registerItemGroups() {
        AgarthaMod.LOGGER.info("Registering Item Groups for " + AgarthaMod.MOD_ID);
    }
}
