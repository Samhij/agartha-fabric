package net.lonk.agartha.block;

import net.lonk.agartha.AgarthaMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static Block AGARTHIUM_BLOCK = registerBlock("agarthium_block",
            new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).sounds(BlockSoundGroup.METAL)));

    public static Block AGARTHIUM_ORE = registerBlock("agarthium_ore",
            new Block(AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(AgarthaMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(AgarthaMod.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks() {
        AgarthaMod.LOGGER.info("Registering Blocks for " + AgarthaMod.MOD_ID);
    }
}
