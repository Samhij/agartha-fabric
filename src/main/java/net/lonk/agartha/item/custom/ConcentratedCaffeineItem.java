package net.lonk.agartha.item.custom;

import net.lonk.agartha.datagen.ModDynamicRegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ConcentratedCaffeineItem extends HoneyBottleItem {
    public ConcentratedCaffeineItem(Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (!world.isClientSide()) {
            DamageSource source = new DamageSource(
                    world.registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(ModDynamicRegistryProvider.HEART_ATTACK)
            );

            user.hurt(source, Float.MAX_VALUE);
            return stack;
        }
        return super.finishUsingItem(stack, world, user);
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
