package net.lonk.agartha.event;

import net.lonk.agartha.entity.ModEntities;
import net.lonk.agartha.entity.custom.YakubEntity;
import net.lonk.agartha.gamerule.ModGameRules;
import net.lonk.agartha.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class YakubSpawnHandler {
    private static boolean canSpawn = true;

    public static void onServerTick(MinecraftServer server) {
        // Check both overworld and agartha dimension
        for (ServerLevel world : server.getAllLevels()) {
            handleWorldSpawns(world);
        }
    }

    private static void handleWorldSpawns(ServerLevel world) {
        if (world == null || !canSpawn) {
            return;
        }

        // Check if we're in Agartha dimension
        boolean isAgartha = world.dimension().equals(ModDimensions.AGARTHA_WORLD_KEY);

        // In overworld: only spawn at night
        // In Agartha: spawn anytime
        if (!isAgartha && !world.isNight()) {
            return;
        }

        // Get spawn chance from game rules
        int baseChance = world.getGameRules().getInt(ModGameRules.YAKUB_SPAWN_CHANCE);

        // In Agartha, make spawn chance 10x higher (divide by 10)
        int chance = isAgartha ? Math.max(1, baseChance / 10) : baseChance;

        if (world.random.nextInt(chance) != 0) {
            return;
        }

        for (ServerPlayer player : world.players()) {
            // In Agartha, 50% chance per player instead of 10%
            float playerChance = isAgartha ? 0.5f : 0.1f;

            if (world.random.nextFloat() >= playerChance) {
                continue;
            }

            BlockPos spawnPos = findValidSpawnPos(world, player.blockPosition());
            if (spawnPos != null) {
                YakubEntity yakub = new YakubEntity(ModEntities.YAKUB, world);
                yakub.setPosRaw(
                        spawnPos.getX() + 0.5,
                        spawnPos.getY(),
                        spawnPos.getZ() + 0.5
                );
                yakub.setYRot(world.getRandom().nextFloat() * 360);
                yakub.setXRot(0);

                if (world.addFreshEntity(yakub)) {
                    canSpawn = false;
                    return; // Exit after spawning one
                }
            }
        }
    }

    private static BlockPos findValidSpawnPos(ServerLevel world, BlockPos center) {
        for (int attempts = 0; attempts < 50; attempts++) {
            int dx = world.random.nextInt(50) - 25;
            int dy = world.random.nextInt(10) - 5;
            int dz = world.random.nextInt(50) - 25;

            BlockPos pos = center.offset(dx, dy, dz);

            BlockPos below = pos.below();
            if (world.getBlockState(below).isRedstoneConductor(world, below)
                    && world.getBlockState(pos).isAir()
                    && world.getBlockState(pos.above()).isAir()) {
                return pos;
            }
        }
        return null;
    }

    public static void resetSpawnFlag() {
        canSpawn = true;
    }
}