package nedelosk.cultivation.apiimp;

import net.minecraft.nbt.NBTTagCompound;

import nedelosk.cultivation.api.IGardeningFactory;
import nedelosk.cultivation.api.plant.ICultivationPlant;
import nedelosk.cultivation.api.plant.IPlantContainer;
import nedelosk.cultivation.api.plant.IPlantLogic;
import nedelosk.cultivation.api.soil.EnumAcidity;
import nedelosk.cultivation.api.soil.EnumMoisture;
import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilType;
import nedelosk.cultivation.apiimp.plant.PlantLogic;
import nedelosk.cultivation.apiimp.soil.Soil;

public enum GardeningFactory implements IGardeningFactory {
	INSTANCE;

	@Override
	public IPlantLogic createLogic(IPlantContainer container) {
		return new PlantLogic(container);
	}

	@Override
	public ISoil createSoil(EnumNutrition nutrition, EnumAcidity acidity, EnumMoisture moisture, ISoilType type) {
		return new Soil(nutrition, acidity, moisture, type);
	}

	@Override
	public ICultivationPlant createPlant(NBTTagCompound compound) {
		return null;
	}
}
