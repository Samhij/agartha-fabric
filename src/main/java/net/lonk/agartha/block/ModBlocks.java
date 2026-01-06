package net.lonk.agartha.block;

import net.lonk.agartha.AgarthaMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static Block AGARTHIUM_BLOCK = registerBlock("agarthium_block",
            new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).sound(SoundType.METAL)));

    public static Block AGARTHIUM_ORE = registerBlock("agarthium_ore",
            new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, name), new BlockItem(block, new Item.Properties()));
    }

    public static void registerBlocks() {
        AgarthaMod.LOGGER.info("Registering Blocks for " + AgarthaMod.MOD_ID);
    }
}
