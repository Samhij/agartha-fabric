package net.lonk.agartha.entity.custom;

import net.lonk.agartha.event.YakubSpawnHandler;
import net.lonk.agartha.gamerule.ModGameRules;
import net.lonk.agartha.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class YakubEntity extends PathAwareEntity {
    private int despawnTimer = 0;
    private final int despawnTime;

    private final ServerBossBar bossBar = new ServerBossBar(getDisplayName(), BossBar.Color.WHITE, BossBar.Style.PROGRESS);

    private MovingSoundInstance seeingSound;

    public YakubEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        despawnTime = world.getGameRules().getInt(ModGameRules.YAKUB_DESPAWN_TIME);

        if (!world.isClient()) {
            // Create the boots item stack
            ItemStack boots = new ItemStack(Items.LEATHER_BOOTS);
            // Prepare enchantments map
            Map<Enchantment, Integer> enchantments = new HashMap<>();

            // Add enchantments to the map
            enchantments.put(Enchantments.FROST_WALKER, 2);
            enchantments.put(Enchantments.UNBREAKING, 3);

            // Apply enchantments to the boots
            EnchantmentHelper.set(enchantments, boots);

            // Equip the boots to the entity
            equipStack(EquipmentSlot.FEET, boots);
            // Set drop chance to 0 to prevent players from obtaining them
            setEquipmentDropChance(EquipmentSlot.FEET, 0);
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 500.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 150.0D);
    }

    @Override
    protected void initGoals() {
        // 1. Target the nearest player (highest priority)
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, MobEntity.class, false));

        // 2. Melee attack goal (high priority)
        // This makes it aggressively pursue the player
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, true));

        // 3. Fallback: If no target, wander around (low priority)
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));

        // 4. Look around
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        World world = this.getWorld();

        if (world.isClient()) {
            this.tickClientSounds();
        }

        if (!world.isClient()) {
            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

            if (despawnTimer >= despawnTime) {
                despawnTimer = 0;
                this.remove(RemovalReason.DISCARDED);
            }

            if (this.isSubmergedInWater()) {
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    private void tickClientSounds() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        boolean canSeePlayer = this.canSee(client.player);

        if (canSeePlayer) {
            if (this.seeingSound == null || !client.getSoundManager().isPlaying(this.seeingSound)) {
                this.startSeeingSound(client);
            }
        } else {
            this.stopSeeingSound(client);
        }
    }

    private void startSeeingSound(MinecraftClient client) {
        this.stopSeeingSound(client);
        this.seeingSound = new SeeingSoundInstance(this, ModSounds.YAKUB_SEEING);
        client.getSoundManager().play(this.seeingSound);
    }

    private void stopSeeingSound(MinecraftClient client) {
        if (this.seeingSound != null) {
            client.getSoundManager().stop(this.seeingSound);
            this.seeingSound = null;
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.horizontalCollision) {
            Vec3d initialVec = this.getVelocity();
            Vec3d climbVec = new Vec3d(initialVec.x, 0.2D, initialVec.z);
            this.setVelocity(climbVec);
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (target instanceof PlayerEntity playerEntity) {
            if (playerEntity.isDead()) {
                this.remove(RemovalReason.DISCARDED);
            }
        }
        return super.tryAttack(target);
    }

    @Override
    public void onRemoved() {
        super.onRemoved();
        YakubSpawnHandler.resetSpawnFlag();
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        YakubSpawnHandler.resetSpawnFlag();
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    public static class SeeingSoundInstance extends MovingSoundInstance {
        private final YakubEntity entity;
        private final MinecraftClient client;

        protected SeeingSoundInstance(YakubEntity entity, SoundEvent soundEvent) {
            super(soundEvent, SoundCategory.HOSTILE, Random.create());
            this.client = MinecraftClient.getInstance();
            this.entity = entity;
            this.repeat = true;
            this.repeatDelay = 0;
            this.relative = false;
            this.volume = 3.0f;
            this.pitch = 1.0f;
            this.updatePosition();
        }

        private void updatePosition() {
            this.x = entity.getX();
            this.y = entity.getY();
            this.z = entity.getZ();
        }

        @Override
        public void tick() {
            if (this.entity.isRemoved() || !this.entity.canSee(this.client.player)) {
                this.volume = 0.0f;
                return;
            }
            this.updatePosition();
        }

        @Override
        public boolean isDone() {
            return this.getVolume() <= 0.0f;
        }
    }
}




















