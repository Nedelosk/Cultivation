package nedelosk.cultivation.api.soil;

import net.minecraft.item.ItemStack;

import net.minecraftforge.registries.IForgeRegistryEntry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ISoilType extends IForgeRegistryEntry<ISoilType> {
	boolean isValidItem(ItemStack itemStack);

	EnumMoisture getMoisture();

	EnumAcidity getAcidity();

	EnumNutrition getNutrition();

	@SideOnly(Side.CLIENT)
	ISoilModelProvider getModelProvider();
}
