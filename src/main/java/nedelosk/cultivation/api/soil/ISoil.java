package nedelosk.cultivation.api.soil;

import net.minecraft.nbt.NBTTagCompound;

public interface ISoil {

	EnumNutrition getNutrition();

	EnumAcidity getAcidity();

	EnumMoisture getMoisture();

	ISoil withNutrition(EnumNutrition nutrition);

	ISoil withAcidity(EnumAcidity acidity);

	ISoil withMoisture(EnumMoisture moisture);

	ISoil withType(ISoilType type);

	boolean isEmpty();

	ISoilType getType();

	NBTTagCompound writeToNBT(NBTTagCompound compound);
}
