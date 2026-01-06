package net.lonk.agartha.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WhiteMonsterItem extends HoneyBottleItem {
    public WhiteMonsterItem(Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (!world.isClientSide()) {
            user.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000, 2));
            user.addEffect(new MobEffectInstance(MobEffects.SATURATION, 20, 10));
            stack.hurtAndBreak(1, user, (player) -> player.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            return stack;
        } else {
            return super.finishUsingItem(stack, world, user);
        }

    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
