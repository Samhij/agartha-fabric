package net.lonk.agartha.entity.custom;

import net.lonk.agartha.datagen.ModDynamicRegistryProvider;
import net.lonk.agartha.event.YakubSpawnHandler;
import net.lonk.agartha.gamerule.ModGameRules;
import net.lonk.agartha.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.HashMap;
import java.util.Map;

public class YakubEntity extends PathfinderMob {
    private int despawnTimer = 0;
    private final int despawnTime;

    private final ServerBossEvent bossBar = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);

    private AbstractTickableSoundInstance seeingSound;

    public YakubEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
        despawnTime = world.getGameRules().getInt(ModGameRules.YAKUB_DESPAWN_TIME);

        if (!world.isClientSide()) {
            // Create the boots item stack
            ItemStack boots = new ItemStack(Items.LEATHER_BOOTS);
            // Prepare enchantments map
            Map<Enchantment, Integer> enchantments = new HashMap<>();

            // Add enchantments to the map
            enchantments.put(Enchantments.FROST_WALKER, 2);
            enchantments.put(Enchantments.UNBREAKING, 3);

            // Apply enchantments to the boots
            EnchantmentHelper.setEnchantments(enchantments, boots);

            // Equip the boots to the entity
            setItemSlot(EquipmentSlot.FEET, boots);
            // Set drop chance to 0 to prevent players from obtaining them
            setDropChance(EquipmentSlot.FEET, 0);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 500.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.FOLLOW_RANGE, 150.0D);
    }

    @Override
    protected void registerGoals() {
        // 1. Target the nearest player (highest priority)
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, false));

        // 2. Melee attack goal (high priority)
        // This makes it aggressively pursue the player
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, true));

        // 3. Fallback: If no target, wander around (low priority)
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        // 4. Look around
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        Level world = this.level();

        if (world.isClientSide()) {
            this.tickClientSounds();
        }

        if (!world.isClientSide()) {
            this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());

            if (despawnTimer >= despawnTime) {
                despawnTimer = 0;
                this.remove(RemovalReason.DISCARDED);
            }

            if (this.isUnderWater()) {
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    private void tickClientSounds() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;

        boolean canSeePlayer = this.hasLineOfSight(client.player);

        if (canSeePlayer) {
            if (this.seeingSound == null || !client.getSoundManager().isActive(this.seeingSound)) {
                this.startSeeingSound(client);
            }
        } else {
            this.stopSeeingSound(client);
        }
    }

    private void startSeeingSound(Minecraft client) {
        this.stopSeeingSound(client);
        this.seeingSound = new SeeingSoundInstance(this, ModSounds.YAKUB_SEEING);
        client.getSoundManager().play(this.seeingSound);
    }

    private void stopSeeingSound(Minecraft client) {
        if (this.seeingSound != null) {
            client.getSoundManager().stop(this.seeingSound);
            this.seeingSound = null;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.horizontalCollision) {
            Vec3 initialVec = this.getDeltaMovement();
            Vec3 climbVec = new Vec3(initialVec.x, 0.2D, initialVec.z);
            this.setDeltaMovement(climbVec);
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (target instanceof Player player) {
            DamageSource source = new DamageSource(
                    this.level().registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDynamicRegistryProvider.AGARTHAN_DAMAGE)
            );

            target.hurt(source, (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));

            if (player.isDeadOrDying()) {
                this.remove(RemovalReason.DISCARDED);
            }

            return false;
        }

        return super.doHurtTarget(target);
    }

    @Override
    public void onClientRemoval() {
        super.onClientRemoval();
        YakubSpawnHandler.resetSpawnFlag();
    }

    @Override
    public void die(DamageSource damageSource) {
        super.die(damageSource);
        YakubSpawnHandler.resetSpawnFlag();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossBar.removePlayer(player);
    }

    public static class SeeingSoundInstance extends AbstractTickableSoundInstance {
        private final YakubEntity entity;
        private final Minecraft client;

        protected SeeingSoundInstance(YakubEntity entity, SoundEvent soundEvent) {
            super(soundEvent, SoundSource.HOSTILE, RandomSource.create());
            this.client = Minecraft.getInstance();
            this.entity = entity;
            this.looping = true;
            this.delay = 0;
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
            if (this.entity.isRemoved() || !this.entity.hasLineOfSight(this.client.player)) {
                this.volume = 0.0f;
                return;
            }
            this.updatePosition();
        }

        @Override
        public boolean isStopped() {
            return this.getVolume() <= 0.0f;
        }
    }
}




















