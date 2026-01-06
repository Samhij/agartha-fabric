package net.lonk.agartha.gamerule;

import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.lonk.agartha.AgarthaMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static final CustomGameRuleCategory AGARTHA = new CustomGameRuleCategory(
            ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "agartha"),
            Component.translatable("gamerule.category.agartha"));

    public static final GameRules.Key<GameRules.IntegerValue> YAKUB_SPAWN_CHANCE =
            GameRuleRegistry.register("yakubSpawnChance", AGARTHA, GameRuleFactory.createIntRule(1000));

    public static final GameRules.Key<GameRules.IntegerValue> YAKUB_DESPAWN_TIME =
            GameRuleRegistry.register("yakubDespawnTime", AGARTHA, GameRuleFactory.createIntRule(2400));

    public static void registerGameRules() {
        AgarthaMod.LOGGER.info("Registering Game Rules for " + AgarthaMod.MOD_ID);
    }
}
