package net.lonk.agartha.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class WhiteMonsterItem extends HoneyBottleItem {
    public WhiteMonsterItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 12000, 2));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 20, 10));
            stack.damage(1, user, (player) -> player.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            return stack;
        } else {
            return super.finishUsing(stack, world, user);
        }

    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
}
