package net.lonk.agartha.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.item.custom.ConcentratedCaffeineItem;
import net.lonk.agartha.item.custom.WhiteMonsterItem;
import net.lonk.agartha.sound.ModSounds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

public class ModItems {
    public static final Item WHITE_MONSTER = registerItem("white_monster",
            new WhiteMonsterItem(new FabricItemSettings().rarity(Rarity.RARE).durability(5)));

    public static final Item CONCENTRATED_CAFFEINE = registerItem("concentrated_caffeine",
            new ConcentratedCaffeineItem(new FabricItemSettings().rarity(Rarity.EPIC)));

    public static final Item DOWN_UNDER_MUSIC_DISC = registerItem("down_under_music_disc",
            new RecordItem(7, ModSounds.DOWN_UNDER, new FabricItemSettings().stacksTo(1).rarity(Rarity.RARE), 158));

    public static final Item AGARTHIUM = registerItem("agarthium",
            new Item(new FabricItemSettings()));

    public static final Item AGARTHAN_RESIDUE = registerItem("agarthan_residue",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, name), item);
    }

    public static void registerItems() {
        AgarthaMod.LOGGER.info("Registering Items for " + AgarthaMod.MOD_ID);
    }
}
