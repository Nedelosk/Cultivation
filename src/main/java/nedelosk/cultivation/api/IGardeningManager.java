package nedelosk.cultivation.api;

import javax.annotation.Nullable;
import java.util.Collection;

import nedelosk.cultivation.api.plant.IPlantType;
import nedelosk.cultivation.api.soil.ISoil;

public interface IGardeningManager {

	ISoil getDefaultSoil();

	void registerType(IPlantType type);

	Collection<IPlantType> getTypes();

	@Nullable
	IPlantType getType(String name);
}
