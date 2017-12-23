package nedelosk.cultivation.apiimp;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import nedelosk.cultivation.Cultivation;
import nedelosk.cultivation.api.IGardeningManager;
import nedelosk.cultivation.api.plant.IPlantType;
import nedelosk.cultivation.api.soil.EnumAcidity;
import nedelosk.cultivation.api.soil.EnumMoisture;
import nedelosk.cultivation.api.soil.EnumNutrition;
import nedelosk.cultivation.api.soil.ISoil;
import nedelosk.cultivation.api.soil.ISoilType;
import nedelosk.cultivation.apiimp.soil.SoilType;

public enum  GardeningManager implements IGardeningManager {
	INSTANCE;

	public static GardeningManager getInstance() {
		return INSTANCE;
	}

	private final Map<String, IPlantType> plantTypes = new HashMap<>();
	public final ForgeRegistry<ISoilType> typeRegistry;
	private final ISoil defaultSoil;

	GardeningManager() {
		typeRegistry = (ForgeRegistry<ISoilType>) new RegistryBuilder<ISoilType>().setType(ISoilType.class).setName(new ResourceLocation(Cultivation.MOD_ID, "soil")).setMaxID(128).create();
		registerDefaultTypes();
		defaultSoil = GardeningFactory.INSTANCE.createSoil(EnumNutrition.NORMAL, EnumAcidity.NEUTRAL, EnumMoisture.NORMAL, typeRegistry.getValue(new ResourceLocation(Cultivation.MOD_ID, "farmland")));
	}

	private void registerDefaultTypes(){
		typeRegistry.register(new SoilType(new ItemStack(Blocks.FARMLAND), EnumNutrition.NORMAL, EnumAcidity.NEUTRAL, EnumMoisture.DRY, "blocks/farmland_dry").setRegistryName("farmland"));
	}

	@Override
	public ISoil getDefaultSoil() {
		return defaultSoil;
	}
	@Override
	public void registerType(IPlantType type) {
		plantTypes.put(type.getName(), type);
	}

	@Override
	public Collection<IPlantType> getTypes() {
		return plantTypes.values();
	}

	@Nullable
	@Override
	public IPlantType getType(String name) {
		return plantTypes.get(name);
	}
}
