package net.lonk.agartha.entity;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.entity.custom.YakubEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<YakubEntity> YAKUB = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "yakub"),
            EntityType.Builder.of(YakubEntity::new, MobCategory.CREATURE).sized(0.6f, 1.8f).build("yakub"));

    public static void registerEntities() {
        AgarthaMod.LOGGER.info("Registering Entities for " + AgarthaMod.MOD_ID);
    }
}
