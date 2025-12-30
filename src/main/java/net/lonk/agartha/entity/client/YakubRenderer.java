package net.lonk.agartha.entity.client;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.entity.custom.YakubEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class YakubRenderer extends MobEntityRenderer<YakubEntity, YakubModel> {
    private static final Identifier TEXTURE = Identifier.of(AgarthaMod.MOD_ID, "textures/entity/yakub.png");

    public YakubRenderer(EntityRendererFactory.Context context) {
        super(context, new YakubModel(context.getPart(YakubModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTexture(YakubEntity entity) {
        return TEXTURE;
    }
}
