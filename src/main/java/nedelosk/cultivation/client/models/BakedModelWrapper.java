package nedelosk.cultivation.client.models;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public abstract class BakedModelWrapper implements IBakedModel {
	protected final IBakedModel original;

	public BakedModelWrapper(IBakedModel original) {
		this.original = original;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		return original.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return original.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return original.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return original.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return original.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return original.getItemCameraTransforms();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return original.getOverrides();
	}


}
