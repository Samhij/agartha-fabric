package net.lonk.agartha;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.lonk.agartha.block.ModBlocks;
import net.lonk.agartha.entity.ModEntities;
import net.lonk.agartha.entity.custom.YakubEntity;
import net.lonk.agartha.event.YakubSpawnHandler;
import net.lonk.agartha.gamerule.ModGameRules;
import net.lonk.agartha.item.ModItemGroups;
import net.lonk.agartha.item.ModItems;
import net.lonk.agartha.sound.ModSounds;
import net.lonk.agartha.world.gen.ModWorldGeneration;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgarthaMod implements ModInitializer {
	public static final String MOD_ID = "agartha";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerItems();
		ModBlocks.registerBlocks();

		ModEntities.registerEntities();
		ModSounds.registerSounds();

		ModWorldGeneration.generateWorldGen();
		ModGameRules.registerGameRules();

		ServerTickEvents.END_SERVER_TICK.register(YakubSpawnHandler::onServerTick);

		FabricDefaultAttributeRegistry.register(ModEntities.YAKUB, YakubEntity.createAttributes());

		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.GLOWSTONE)
				.lightWithItem(ModItems.WHITE_MONSTER)
				.destDimID(Identifier.of(MOD_ID, "agartha"))
				.tintColor(0xFFFFFF)
				.registerPortal();
	}
}