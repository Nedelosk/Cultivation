package nedelosk.cultivation.apiimp.soil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import net.minecraftforge.registries.IForgeRegistryEntry;

import nedelosk.cultivation.api.soil.EnumAcidity;
import nedelosk.cultivation.api.soil.EnumMoisture;
import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilModelProvider;
import nedelosk.cultivation.api.soil.ISoilType;
import nedelosk.cultivation.utils.RenderUtil;

public class SoilType extends IForgeRegistryEntry.Impl<ISoilType> implements ISoilType {
	private final ItemStack itemStack;
	private final EnumNutrition nutrition;
	private final EnumAcidity acidity;
	private final EnumMoisture moisture;
	private final ISoilModelProvider modelProvider;

	public SoilType(ItemStack itemStack, EnumNutrition nutrition, EnumAcidity acidity, EnumMoisture moisture, IBlockState blockState) {
		this.itemStack = itemStack;
		this.nutrition = nutrition;
		this.acidity = acidity;
		this.moisture = moisture;
		this.modelProvider = new ModelProviderBlock(blockState);
	}

	public SoilType(ItemStack itemStack, EnumNutrition nutrition, EnumAcidity acidity, EnumMoisture moisture, String texture) {
		this.itemStack = itemStack;
		this.nutrition = nutrition;
		this.acidity = acidity;
		this.moisture = moisture;
		this.modelProvider = new ModelProviderTexture(texture);
	}

	public SoilType(ItemStack itemStack, EnumNutrition nutrition, EnumAcidity acidity, EnumMoisture moisture, ISoilModelProvider modelProvider) {
		this.itemStack = itemStack;
		this.nutrition = nutrition;
		this.acidity = acidity;
		this.moisture = moisture;
		this.modelProvider = modelProvider;
	}

	@Override
	public boolean isValidItem(ItemStack itemStack) {
		return ItemStack.areItemStacksEqual(itemStack, this.itemStack);
	}

	@Override
	public EnumMoisture getMoisture() {
		return moisture;
	}

	@Override
	public EnumAcidity getAcidity() {
		return acidity;
	}

	@Override
	public EnumNutrition getNutrition() {
		return nutrition;
	}

	@Override
	public ISoilModelProvider getModelProvider() {
		return modelProvider;
	}

	private static class ModelProviderBlock implements ISoilModelProvider{
		private final IBlockState blockState;

		public ModelProviderBlock(IBlockState blockState) {
			this.blockState = blockState;
		}

		@Override
		public String getTexture(ISoil soil) {
			return RenderUtil.getSprite(blockState).getIconName();
		}
	}

	private static class ModelProviderTexture implements ISoilModelProvider{
		private final String texture;

		public ModelProviderTexture(String texture) {
			this.texture = texture;
		}

		@Override
		public String getTexture(ISoil soil) {
			return texture;
		}
	}
}
