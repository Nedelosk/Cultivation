package nedelosk.cultivation.client.models;

import java.util.Collection;
import java.util.function.Function;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;

public class ModelGardeningBox implements IModel {
	private final String variant;

	public ModelGardeningBox(String variant) {
		this.variant = variant;
	}

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return null;
	}

	@Override
	public Collection<ResourceLocation> getDependencies() {
		return null;
	}

	@Override
	public Collection<ResourceLocation> getTextures() {
		return null;
	}


}
