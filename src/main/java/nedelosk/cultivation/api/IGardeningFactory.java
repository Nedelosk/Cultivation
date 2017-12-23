package nedelosk.cultivation.api;

import net.minecraft.nbt.NBTTagCompound;

import nedelosk.cultivation.api.plant.ICultivationPlant;
import nedelosk.cultivation.api.plant.IPlantContainer;
import nedelosk.cultivation.api.plant.IPlantLogic;
import nedelosk.cultivation.api.soil.EnumAcidity;
import nedelosk.cultivation.api.soil.EnumMoisture;
import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilType;

public interface IGardeningFactory {

	IPlantLogic createLogic(IPlantContainer container);

	ISoil createSoil(EnumNutrition nutrition, EnumAcidity acidity, EnumMoisture moisture, ISoilType type);

	ICultivationPlant createPlant(NBTTagCompound compound);
}