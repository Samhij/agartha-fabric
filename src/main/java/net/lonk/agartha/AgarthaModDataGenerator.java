package net.lonk.agartha;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.lonk.agartha.datagen.*;
import net.lonk.agartha.world.ModConfiguredFeatures;
import net.lonk.agartha.world.ModPlacedFeatures;
import net.lonk.agartha.world.biome.ModBiomes;
import net.lonk.agartha.world.dimension.ModDimensions;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class AgarthaModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModAdvancementProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModDynamicRegistryProvider::new);
		pack.addProvider(ModEnglishLangProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, ModDimensions::boostrapType);
	}
}
