package net.lonk.agartha.item.custom;

import net.lonk.agartha.datagen.ModDynamicRegistryProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ConcentratedCaffeineItem extends HoneyBottleItem {
    public ConcentratedCaffeineItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            DamageSource source = new DamageSource(
                    world.getRegistryManager()
                    .get(RegistryKeys.DAMAGE_TYPE)
                    .entryOf(ModDynamicRegistryProvider.HEART_ATTACK)
            );

            user.damage(source, Float.MAX_VALUE);
            return stack;
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
}
