package net.lonk.agartha.mixin;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.world.dimension.ModDimensions;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerRenderer.class)
public class VillagerEntityRendererMixin {
    @Shadow
    @Final
    private static ResourceLocation VILLAGER_BASE_SKIN;

    @Unique
    private static final ResourceLocation TEXTURE_AGARTHA = ResourceLocation.tryBuild(AgarthaMod.MOD_ID, "textures/entity/villager_agartha.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/npc/Villager;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void getTexture(Villager entity, CallbackInfoReturnable<ResourceLocation> info) {
        if (entity.level().dimension().equals(ModDimensions.AGARTHA_WORLD_KEY)) {
            info.setReturnValue(TEXTURE_AGARTHA);
        } else {
            info.setReturnValue(VILLAGER_BASE_SKIN);
        }
    }
}