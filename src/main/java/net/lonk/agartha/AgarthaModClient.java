package net.lonk.agartha;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.lonk.agartha.entity.ModEntities;
import net.lonk.agartha.entity.client.YakubModel;
import net.lonk.agartha.entity.client.YakubRenderer;

public class AgarthaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(YakubModel.LAYER_LOCATION, YakubModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.YAKUB, YakubRenderer::new);
    }
}
