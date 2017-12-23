package nedelosk.cultivation.client.models;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.property.IExtendedBlockState;

import nedelosk.cultivation.Cultivation;
import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilModelProvider;
import nedelosk.cultivation.apiimp.GardeningManager;
import nedelosk.cultivation.blocks.properties.PropertySoil;
import nedelosk.cultivation.utils.RenderUtil;

public class GardeningBoxModelBaked extends BakedModelWrapper {
	private static final String SOIL_MODEL = Cultivation.MOD_ID + ":gardening_box_soil";
	private static final Cache<Key, IBakedModel> soilModels = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).build();
	@Nullable
	private static IModel soilModel;

	public static void onBakeModel(ModelBakeEvent event){
		soilModel = null;
		soilModels.invalidateAll();
	}

	private final String variant;

	public GardeningBoxModelBaked(IBakedModel original, String variant) {
		super(original);
		this.variant = variant;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		if(state == null){
			return super.getQuads(null, side, rand);
		}
		ISoil soil = GardeningManager.INSTANCE.getDefaultSoil();
		if(state instanceof IExtendedBlockState){
			IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
			soil = extendedBlockState.getValue(PropertySoil.INSTANCE);
		}
		Key key = new Key(soil, state);
		IBakedModel bakedSoil = soilModels.getIfPresent(key);
		if(bakedSoil == null){
			ISoilModelProvider provider = soil.getType().getModelProvider();
			String modelLocation = provider.getModel(soil);
			IModel model;
			if(modelLocation == null){
				IModel soilModel = ModelLoaderRegistry.getModelOrMissing(new ModelResourceLocation(SOIL_MODEL, variant));
				model = soilModel;
				String texture = provider.getTexture(soil);
				if(texture != null) {
					model = soilModel.retexture(ImmutableMap.of("soil", texture));
				}
			}else{
				model = ModelLoaderRegistry.getModelOrMissing(new ModelResourceLocation(modelLocation));
			}

			bakedSoil = model.bake(ModelRotation.X0_Y0, DefaultVertexFormats.BLOCK, RenderUtil::getSprite);
			soilModels.put(key, bakedSoil);
		}
		ImmutableList.Builder<BakedQuad> builder = new ImmutableList.Builder<>();
		builder.addAll(bakedSoil.getQuads(state, side, rand));
		builder.addAll(super.getQuads(state, side, rand));
		return builder.build();
	}

	private static class Key{
		private final ISoil soil;
		private final IBlockState blockState;

		public Key(ISoil soil, IBlockState blockState) {
			this.soil = soil;
			this.blockState = blockState;
		}

		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Key)){
				return true;
			}
			Key key = (Key) obj;
			return soil.equals(key.soil) && blockState.equals(key.blockState);
		}

		@Override
		public int hashCode() {
			int hash = 1;
			hash=hash * 31 + soil.hashCode();
			hash=hash * 31 + blockState.hashCode();
			return hash;
		}
	}
}
