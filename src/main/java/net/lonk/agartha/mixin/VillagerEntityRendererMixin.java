package net.lonk.agartha.mixin;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.world.dimension.ModDimensions;
import net.minecraft.client.render.entity.VillagerEntityRenderer;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntityRenderer.class)
public class VillagerEntityRendererMixin {
    @Shadow
    @Final
    private static Identifier TEXTURE;

    @Unique
    private static final Identifier TEXTURE_AGARTHA = Identifier.of(AgarthaMod.MOD_ID, "textures/entity/villager_agartha.png");

    @Inject(method = "getTexture(Lnet/minecraft/entity/passive/VillagerEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    private void getTexture(VillagerEntity entity, CallbackInfoReturnable<Identifier> info) {
        if (entity.getWorld().getRegistryKey().equals(ModDimensions.AGARTHA_WORLD_KEY)) {
            info.setReturnValue(TEXTURE_AGARTHA);
        } else {
            info.setReturnValue(TEXTURE);
        }
    }
}