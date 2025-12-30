package net.lonk.agartha.entity.client;

import net.lonk.agartha.AgarthaMod;
import net.lonk.agartha.entity.custom.YakubEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class YakubModel extends EntityModel<YakubEntity> {
	private final ModelPart bb_main;
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(Identifier.of(AgarthaMod.MOD_ID, "yakub"), "main");

	public YakubModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(-68, -2).cuboid(-32.0F, -64.0F, -1.0F, 64.0F, 64.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(YakubEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}