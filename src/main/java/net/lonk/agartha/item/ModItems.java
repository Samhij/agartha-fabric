package net.lonk.agartha.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.item.custom.WhiteMonsterItem;
import net.lonk.agartha.sound.ModSounds;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item WHITE_MONSTER = registerItem("white_monster",
            new WhiteMonsterItem(new FabricItemSettings().rarity(Rarity.EPIC).maxDamage(5)));

    public static final Item DOWN_UNDER_MUSIC_DISC = registerItem("down_under_music_disc",
            new MusicDiscItem(7, ModSounds.DOWN_UNDER, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 158));

    public static final Item AGARTHIUM = registerItem("agarthium",
            new Item(new FabricItemSettings()));

    public static final Item AGARTHAN_RESIDUE = registerItem("agarthan_residue",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AgarthaMod.MOD_ID, name), item);
    }

    public static void registerItems() {
        AgarthaMod.LOGGER.info("Registering Items for " + AgarthaMod.MOD_ID);
    }
}
