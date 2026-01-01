package net.lonk.agartha.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.world.biome.ModBiomes;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DeathMessageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final RegistryKey<DamageType> AGARTHAN_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(AgarthaMod.MOD_ID, "agarthan"));
    public static final RegistryKey<DamageType> HEART_ATTACK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(AgarthaMod.MOD_ID, "heart_attack"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
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
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.BIOME));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.DIMENSION_TYPE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CHUNK_GENERATOR_SETTINGS));
    }

    @Override
    public String getName() {
        return "World Gen";
    }
}
