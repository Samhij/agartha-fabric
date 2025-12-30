package net.lonk.agartha.entity;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.entity.custom.YakubEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<YakubEntity> YAKUB = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(AgarthaMod.MOD_ID, "yakub"),
            EntityType.Builder.create(YakubEntity::new, SpawnGroup.CREATURE).setDimensions(0.6f, 1.8f).build("yakub"));

    public static void registerEntities() {
        AgarthaMod.LOGGER.info("Registering Entities for " + AgarthaMod.MOD_ID);
    }
}
