package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.world.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DeathMessageType;
import java.util.concurrent.CompletableFuture;

public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final ResourceKey<DamageType> AGARTHAN_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "agarthan"));
    public static final ResourceKey<DamageType> HEART_ATTACK = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "heart_attack"));

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        // Damage types
        entries.add(AGARTHAN_DAMAGE, new DamageType(
                "agarthan",
                DamageScaling.NEVER,
                0.1f,
                DamageEffects.HURT,
                DeathMessageType.DEFAULT
        ));

        entries.add(HEART_ATTACK, new DamageType(
                "heart_attack",
                DamageScaling.NEVER,
                0.1f,
                DamageEffects.HURT,
                DeathMessageType.DEFAULT
        ));

        // World generation
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
        entries.addAll(registries.lookupOrThrow(Registries.DIMENSION_TYPE));
        entries.addAll(registries.lookupOrThrow(Registries.NOISE_SETTINGS));
    }

    @Override
    public String getName() {
        return "World Gen";
    }
}
