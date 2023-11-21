package org.violetmoon.quark.content.tools.client.render.entity;

import com.mojang.math.Axis;
import org.jetbrains.annotations.NotNull;

import org.violetmoon.quark.content.tools.entity.rang.AbstractPickarang;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;

public class PickarangRenderer extends EntityRenderer<AbstractPickarang<?>> {

	public PickarangRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(AbstractPickarang<?> entity, float yaw, float partialTicks, PoseStack matrix, @NotNull MultiBufferSource buffer, int light) {
		if (entity.tickCount >= 2) {
			matrix.pushPose();
			matrix.translate(0, 0.2, 0);
			matrix.mulPose(Axis.XP.rotationDegrees(90F));

			Minecraft mc = Minecraft.getInstance();
			float time = entity.tickCount + (mc.isPaused() ? 0 : partialTicks);
			matrix.mulPose(Axis.ZP.rotationDegrees(time * 20F));

			mc.getItemRenderer().renderStatic(entity.getStack(), TransformType.FIXED, light, OverlayTexture.NO_OVERLAY, matrix, buffer, 0);

			matrix.popPose();
		}
	}

	@NotNull
	@Override
	public ResourceLocation getTextureLocation(@NotNull AbstractPickarang<?> entity) {
		return TextureAtlas.LOCATION_BLOCKS;
	}

}
